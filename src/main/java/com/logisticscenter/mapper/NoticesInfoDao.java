package com.logisticscenter.mapper;

import com.logisticscenter.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticesInfoDao {
	
	public abstract UserEntity getNotices(Map params);
	
	public abstract List<UserEntity> clearNotices(Map params);

}
