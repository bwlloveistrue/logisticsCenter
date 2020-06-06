package com.logisticscenter.mapper;

import com.logisticscenter.model.PartnerEntity;
import com.logisticscenter.model.SendTypeEntity;
import com.logisticscenter.model.WorkflowTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PartnerDao {
	
	public abstract int insertPartnerInfo(PartnerEntity insertInfo);

	public abstract List<PartnerEntity> getPartnerInfo(PartnerEntity selectInfo);

	public abstract List<PartnerEntity> getAllPartnerInfo();
	
	public abstract int deletePartnerInfo(String id);
	
	public abstract int updatePartnerInfo(PartnerEntity selectInfo);
	
}
