package com.logisticscenter.mapper;


import com.logisticscenter.model.CommercialEntity;
import com.logisticscenter.model.CommercialEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 卜
 *
 */
@Mapper
public interface CommercialDao {

	public abstract int insertCommercialInfo(CommercialEntity insertInfo);

	public abstract List<CommercialEntity> getCommercialInfo(CommercialEntity selectInfo);

	public abstract List<CommercialEntity> getAllCommercial();

	public abstract int deleteCommercialInfo(String id);

	public abstract void updateCommercialInfo(CommercialEntity selectInfo);
	
}
