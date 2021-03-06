package com.logisticscenter.mapper;

import com.logisticscenter.model.ImageFileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author 卜
 *
 */
@Mapper
public interface ImageFileDao {

	/**
	 * @param ids
	 * @return
	 */
	public abstract List<ImageFileEntity> getImageFileBy(List ids);
	
	/**
	 * @param imageFileEntity
	 * @return
	 */
	public abstract int insertImageFile(ImageFileEntity imageFileEntity);
	
}
