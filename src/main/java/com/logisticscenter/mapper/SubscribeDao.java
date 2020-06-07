package com.logisticscenter.mapper;

import com.logisticscenter.model.MessageEntity;
import com.logisticscenter.model.SubscribeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubscribeDao {

	public abstract List<SubscribeEntity> getSubscribeInfo(SubscribeEntity selectInfo);

	public abstract List<SubscribeEntity> getAllSubscribeInfo();

	public abstract int insertSubscribeInfo(List<SubscribeEntity> insertInfo);

	public abstract int deleteSubscribeInfo(String id);

	public abstract int deleteAllSubscribInfo();

}
