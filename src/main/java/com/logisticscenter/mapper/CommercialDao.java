package com.logisticscenter.mapper;


import com.logisticscenter.model.CommercialEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 卜
 *
 */
@Mapper
public interface CommercialDao {

	/**
	 * @param insertInfo
	 * @return
	 */
	public abstract int insertCommercial(CommercialEntity insertInfo);
	
	/**
	 * @param selectInfo
	 * @return
	 */
	public abstract List<CommercialEntity> getCommercial(CommercialEntity selectInfo);
	
	/**
	 * @param selectInfo
	 * @return
	 */
	public abstract String getCommercialCount(CommercialEntity selectInfo);
	
	/**
	 * @param id
	 * @return
	 */
	public abstract CommercialEntity getCommercial(String id);
	
	/**
	 * @param id
	 * @return
	 */
	public abstract int deleteCommercial(String id);
	
	/**
	 * @param insertInfo
	 */
	public abstract void updateCommercial(CommercialEntity insertInfo);
	
	/**
	 * @param days
	 * @return
	 */
	public abstract List<CommercialEntity> getWarnCommercial(String days);
	
}
