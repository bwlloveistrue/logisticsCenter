package com.logisticscenter.mapper;

import com.logisticscenter.model.MessageEntity;
import com.logisticscenter.model.SendTypeEntity;
import com.logisticscenter.model.WorkflowTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageDao {
	
	public abstract int insertMessageInfo(MessageEntity insertInfo);

	public abstract List<MessageEntity> getMessageInfo(MessageEntity selectInfo);

	public abstract List<MessageEntity> getAllMessageInfo();
	
	public abstract int deleteMessageInfo(String id);
	
	public abstract int updateMessageInfo(MessageEntity selectInfo);

	public abstract List<WorkflowTypeEntity> getAllWorkflowType();

	public abstract List<SendTypeEntity> getAllSendType();

}
