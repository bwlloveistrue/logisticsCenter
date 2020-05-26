package com.logisticscenter.mapper;

import com.logisticscenter.model.DriverInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DriverInfoDao {
	
	public abstract int insertDriverInfo(DriverInfoEntity insertInfo);

	public abstract List<DriverInfoEntity> getDriverInfo(DriverInfoEntity selectInfo);

	public abstract List<DriverInfoEntity> getAllDriverInfo();
	
	public abstract int deleteDriverInfo(String id);
	
	public abstract int updateDriverInfo(DriverInfoEntity selectInfo);

}
