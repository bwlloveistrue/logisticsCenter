package com.logisticscenter.mapper;

import com.logisticscenter.model.GoodsTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsTypeDao {
	
	public abstract int insertGoodsTypeInfo(GoodsTypeEntity insertInfo);

	public abstract List<GoodsTypeEntity> getGoodsTypeInfo(GoodsTypeEntity selectInfo);
	
	public abstract List<GoodsTypeEntity> getAllGoodsType();
	
	public abstract int deleteGoodsTypeInfo(String id);
	
	public abstract void updateGoodsTypeInfo(GoodsTypeEntity selectInfo);

}
