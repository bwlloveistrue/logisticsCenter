package com.logisticscenter.mapper;

import com.logisticscenter.model.TruckEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TruckInfoDao {
	
	public abstract int insertTruck(TruckEntity insertInfo);
	
	public abstract List<TruckEntity> getTruckInfo(TruckEntity selectInfo);
	
	public abstract List<TruckEntity> getAllTruck();
	
	public abstract int deleteTruck(String id);
	
	public abstract int updateTruck(TruckEntity selectInfo);

}
