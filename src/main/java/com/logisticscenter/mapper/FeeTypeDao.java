package com.logisticscenter.mapper;

import com.logisticscenter.model.FeeTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeeTypeDao {

	public abstract int insertFeeTypeInfo(FeeTypeEntity insertInfo);

	public abstract List<FeeTypeEntity> getFeeTypeInfo(FeeTypeEntity selectInfo);

	public abstract List<FeeTypeEntity> getAllFeeType();

	public abstract int deleteFeeTypeInfo(String id);

	public abstract void updateFeeTypeInfo(FeeTypeEntity selectInfo);

}
