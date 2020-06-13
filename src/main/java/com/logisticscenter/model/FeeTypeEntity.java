package com.logisticscenter.model;

import com.common.CommonTransMethod;

public class FeeTypeEntity {
	//标识ID
	private int id;
	//标识ID
	private int key;
	//货物名称
	private String feeName;
	//类型
	private int showType;
	//类型
	private String showTypeName;
	//是否启用
	private int isUse;

	//是否启用
	private String isUseName;

	//创建日期
	private String createDate;
	//创建时间
	private String createTime;
	//修改日期
	private String editDate;
	//修改时间
	private String editTime;
	//费用类型字段
	private String feeTypeColumn;

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

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public int getShowType() {
		return showType;
	}

	public void setShowType(int showType) {
		this.showType = showType;
		this.setShowTypeName(showType+"");
	}

	public String getShowTypeName() {
		return showTypeName;
	}

	public void setShowTypeName(String showTypeName) {
		this.showTypeName = CommonTransMethod.getFeeShowTypeName(showTypeName);
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
		this.setIsUseName(isUse+"");
	}

	public String getIsUseName() {
		return isUseName;
	}

	public void setIsUseName(String isUseName) {
		this.isUseName = CommonTransMethod.getIsOrNotString(isUseName);
	}
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEditDate() {
		return editDate;
	}

	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}

	public String getEditTime() {
		return editTime;
	}

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getFeeTypeColumn() {
		return feeTypeColumn;
	}

	public void setFeeTypeColumn(String feeTypeColumn) {
		this.feeTypeColumn = feeTypeColumn;
	}
	
}
