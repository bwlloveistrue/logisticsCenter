package com.logisticscenter.service.impl;

import java.util.*;

import com.common.ConvertService;
import com.configBean.FileException;
import com.configBean.FileProperties;
import com.javabean.ImageFileBean;
import com.logisticscenter.mapper.ImageFileDao;
import com.logisticscenter.model.ImageFileEntity;
import com.logisticscenter.service.ImageFileService;
import com.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageFileServiceImpl implements ImageFileService {

	@Autowired
	ImageFileDao imageFileDao;

	private  Path fileStorageLocation; // 文件在本地存储的地址

	@Autowired
	FileProperties fileProperties;


	@Override
	public Map getImageFileBy(Map params) {
		Map retResult = new HashMap();
		List<ImageFileEntity> entityList = new ArrayList<ImageFileEntity>();
		String ids = Utils.null2String(params.get("id")) ;
		entityList = imageFileDao.getImageFileBy(Arrays.asList(ids.split(",")));
		retResult.put("imageInfo",entityList);
		return retResult;
	}

	/* (non-Javadoc)
	 * @see com.service.ImageFileService#insertImageFile(com.javabean.ImageFileBean)
	 */
	@Override
	public Map insertImageFile(Map params) {
		Map retResult = new HashMap();
		String fileName = Utils.null2String(params.get("fileName"));
		String realFilePath = Utils.null2String(params.get("realFilePath"));
		String contentType = Utils.null2String(params.get("contentType"));
		int imagefileused = Utils.getIntValue(Utils.null2String(params.get("imagefileused")),0);
		String iszip = Utils.null2String(params.get("iszip"));
		int downloads = Utils.getIntValue(Utils.null2String(params.get("downloads")),0);
		ImageFileEntity imageFileEntity = new ImageFileEntity();
		imageFileEntity.setDownloads(downloads);
		imageFileEntity.setImageFileName(fileName);
		imageFileEntity.setFilerealpath(realFilePath);
		imageFileEntity.setImagefiletype(contentType);
		imageFileEntity.setImagefileused(imagefileused);
		imageFileEntity.setIszip(iszip);
		imageFileDao.insertImageFile(imageFileEntity);
		retResult.put("id",imageFileEntity.getId());
		retResult.put("status",true);
		return retResult;
	}

	/**
	 * 存储文件到系统
	 *
	 * @param file 文件
	 * @return 文件名
	 */
	@Override
	public ImageFileEntity storeFile(MultipartFile file) {
		Calendar today = Calendar.getInstance();
		String currentdate = Utils.add0(today.get(Calendar.YEAR), 4) + "-" + Utils.add0(today.get(Calendar.MONTH) + 1, 2) + "-" + Utils.add0(today.get(Calendar.DAY_OF_MONTH), 2);
		String nowUploadDir = fileProperties.getUploadDir()+"/"+currentdate;
		this.fileStorageLocation = Paths.get(nowUploadDir).toAbsolutePath().normalize();
		String contentType = "";
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
		}
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileNameArr[] = fileName.split("\\.");
		String realFileName = UUID.randomUUID().toString().replaceAll("-", "")+"."+fileNameArr[1];

		try {
			if(fileName.contains("..")) {
				throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(realFileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			contentType = Files.probeContentType(targetLocation);
			targetLocation.toString();
			Map insertParams = new HashMap();
			insertParams.put("fileName",fileName);
			insertParams.put("realFilePath",targetLocation.toString());
			insertParams.put("contentType",contentType);
			insertParams.put("imagefileused",0);
			insertParams.put("iszip","0");
			insertParams.put("downloads",0);
			Map retMap = insertImageFile(insertParams);
			String id = Utils.null2String(retMap.get("id"));
			Map selectMap = new HashMap();
			selectMap.put("id",id);
			retMap = getImageFileBy(selectMap);
			List<ImageFileEntity> entityList = (List<ImageFileEntity>)retMap.get("imageInfo");
			ImageFileEntity imageFileEntity = new ImageFileEntity();
			if(entityList.size() > 0){
				imageFileEntity = entityList.get(0);
			}
			return imageFileEntity;
		} catch (IOException ex) {
			throw new FileException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	/**
	 * 加载文件
	 * @param pathId 文件ID
	 * @return 文件
	 */
	@Override
	public Resource loadFileAsResource(String pathId) {
		try {
			Map selectMap = new HashMap();
			selectMap.put("id",pathId);
			Map retMap = getImageFileBy(selectMap);
			List<ImageFileEntity> entityList = (List<ImageFileEntity>)retMap.get("imageInfo");
			ImageFileEntity imageFileEntity = new ImageFileEntity();
			if(entityList.size() > 0){
				imageFileEntity = entityList.get(0);
			}
			this.fileStorageLocation = Paths.get(imageFileEntity.getFilerealpath()).toAbsolutePath().normalize();
			Resource resource = new UrlResource(fileStorageLocation.toUri());
			if(resource.exists()) {
				return resource;
			} else {
				throw new FileException("File not found " + pathId);
			}
		} catch (MalformedURLException ex) {
			throw new FileException("File not found " + pathId, ex);
		}
	}

	@Override
	public String getFilePath(String pathId) {
		Map selectMap = new HashMap();
		selectMap.put("id",pathId);
		Map retMap = getImageFileBy(selectMap);
		List<ImageFileEntity> entityList = (List<ImageFileEntity>)retMap.get("imageInfo");
		ImageFileEntity imageFileEntity = new ImageFileEntity();
		if(entityList.size() > 0){
			imageFileEntity = entityList.get(0);
		}
		return imageFileEntity.getFilerealpath();
	}

}
