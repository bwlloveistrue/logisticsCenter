package com.logisticscenter.model;

import com.util.Utils;

import java.util.Calendar;

/**
 * 微信推送消息entity
 */
public class MessageEntity {

	public MessageEntity() {

	}

	//标识ID
	private int id;
	//id
	private int key;
	//发送对象
	private String sendType;
	//发送对象
	private String sendTypeName;
	//发送类型
	private String workflowType;
	//发送类型
	private String workflowTypeName;
	//模板消息ID
	private String mouldId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		this.key = id;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
		this.setSendTypeName(sendType);
	}

	public String getSendTypeName() {
		return sendTypeName;
	}

	public void setSendTypeName(String sendTypeName) {
		this.sendTypeName = sendTypeName;
	}

	public String getWorkflowType() {
		return workflowType;
	}

	public void setWorkflowType(String workflowType) {
		this.workflowType = workflowType;
		this.setWorkflowTypeName(workflowType);
	}

	public String getWorkflowTypeName() {
		return workflowTypeName;
	}

	public void setWorkflowTypeName(String workflowTypeName) {
		this.workflowTypeName = workflowTypeName;
	}

	public String getMouldId() {
		return mouldId;
	}

	public void setMouldId(String mouldId) {
		this.mouldId = mouldId;
	}
}
