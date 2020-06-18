package com.logisticscenter.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<ImageFileBean> beanList = new ArrayList<ImageFileBean>();
		String id = Utils.null2String(params.get("id")) ;
		entityList = imageFileDao.getImageFileBy(id);
		for(int i=0;i<entityList.size(); i++){
			ImageFileBean imageFileBean = (ImageFileBean) ConvertService.convertEntityToBean(entityList.get(i), new ImageFileBean());
			beanList.add(imageFileBean);
		}
		retResult.put("imageInfo",beanList);
		return retResult;
	}

	/* (non-Javadoc)
	 * @see com.service.ImageFileService#insertImageFile(com.javabean.ImageFileBean)
	 */
	@Override
	public Map insertImageFile(Map params) {
		Map retResult = new HashMap();
		ImageFileEntity imageFileEntity = new ImageFileEntity();
		imageFileEntity.setCreateDate(ConvertService.getDate());
		imageFileEntity.setCreateTime(ConvertService.getTime());
		int id = 0;
		id = imageFileDao.insertImageFile(imageFileEntity);
		retResult.put("id",id);
		return retResult;
	}

	/**
	 * 存储文件到系统
	 *
	 * @param file 文件
	 * @return 文件名
	 */
	@Override
	public String storeFile(MultipartFile file) {
		this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileException("Could not create the directory where the uploaded files will be stored.", ex);
		}
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if(fileName.contains("..")) {
				throw new FileException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
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
			Path filePath = this.fileStorageLocation.resolve(pathId).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if(resource.exists()) {
				return resource;
			} else {
				throw new FileException("File not found " + pathId);
			}
		} catch (MalformedURLException ex) {
			throw new FileException("File not found " + pathId, ex);
		}
	}

}
