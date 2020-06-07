package com.logisticscenter.model;

/**
 * 发送类型entity
 */
public class SendTypeEntity {

	public SendTypeEntity() {

	}

	//标识ID
	private int id;
	//id
	private int key;
	//发送对象
	private String sendType;

	private String description;

	private String sendTypeDesc;

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
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		this.setSendTypeDesc(description);

	}

	public String getSendTypeDesc() {
		return sendTypeDesc;
	}

	public void setSendTypeDesc(String sendTypeDesc) {
		this.sendTypeDesc = sendTypeDesc;
	}
}
