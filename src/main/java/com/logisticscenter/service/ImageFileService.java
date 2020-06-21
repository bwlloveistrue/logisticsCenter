package com.logisticscenter.service;

import java.util.List;
import java.util.Map;

import com.javabean.ImageFileBean;
import com.logisticscenter.model.ImageFileEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageFileService {
	
	
	/**
	 * @param params
	 * @return
	 */
	public abstract Map getImageFileBy(Map<String, Object> params);
	
	/**
	 * @param params
	 * @return
	 */
	public abstract Map insertImageFile(Map<String, Object> params);

	/**
	 * @param file
	 * @return
	 */
	public abstract ImageFileEntity storeFile(MultipartFile file);

	public abstract Resource loadFileAsResource(String pathId);

	public abstract String getFilePath(String pathId);

}
