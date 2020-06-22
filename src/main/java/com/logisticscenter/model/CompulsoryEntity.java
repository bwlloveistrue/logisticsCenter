package com.logisticscenter.model;

import com.common.CommonTransMethod;
import com.util.Utils;

import java.util.Calendar;

public class CompulsoryEntity {

	public CompulsoryEntity() {
		Calendar today = Calendar.getInstance();
		String currentdate = Utils.add0(today.get(Calendar.YEAR), 4) + "-" + Utils.add0(today.get(Calendar.MONTH) + 1, 2) + "-" + Utils.add0(today.get(Calendar.DAY_OF_MONTH), 2);

		String currenttime = Utils.add0(today.get(Calendar.HOUR_OF_DAY), 2) + ":" + Utils.add0(today.get(Calendar.MINUTE), 2) ;
		this.editDate = currentdate;
		this.editTime = currenttime;
		this.createDate = currentdate;
		this.createTime = currenttime;
	}

	//标识ID
	private int id;
	//id
	private int key;
	//车牌号
	private String truckNumber;
	//车牌号码
	private String truckNumberName;
	//车牌号
	private int isNew;

	private String isNewShow;
	//开始日期
	private String startDate;
	//结束日期
	private String endDate;
	//创建日期
	private String createDate;
	//创建时间
	private String createTime;

	//修改日期
	private String editDate;
	//修改时间
	private String editTime;

	//pageSize
	private String pageSize;

	//currentPage
	private String currentPage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		this.setKey(id);
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getTruckNumber() {
		return truckNumber;
	}

	public void setTruckNumber(String truckNumber) {
		this.truckNumber = truckNumber;
		this.setTruckNumberName(truckNumber);
	}

	public String getTruckNumberName() {
		return truckNumberName;
	}

	public void setTruckNumberName(String truckNumberName) {
		this.truckNumberName = CommonTransMethod.getTruckNumber(truckNumberName);
	}

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
		this.setIsNewShow(isNew+"");
	}

	public String getIsNewShow() {
		return isNewShow;
	}

	public void setIsNewShow(String isNewShow) {
		this.isNewShow = CommonTransMethod.getIsOrNotString(isNewShow);
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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


}
