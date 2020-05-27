package com.logisticscenter.mapper;

import com.logisticscenter.model.GoodsTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsTypeDao {
	
	public abstract int insertGoodsType(GoodsTypeEntity insertInfo);

	public abstract List<GoodsTypeEntity> getGoodsType(GoodsTypeEntity selectInfo);
	
	public abstract List<GoodsTypeEntity> getAllGoodsType();
	
	public abstract int deleteGoodsType(String id);
	
	public abstract void updateGoodsType(GoodsTypeEntity selectInfo);

}
