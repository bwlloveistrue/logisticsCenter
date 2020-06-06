package com.logisticscenter.model;

import com.common.CommonTransMethod;
import com.util.Utils;

import java.util.Calendar;

/**
 * 微信推送消息entity
 */
public class PartnerEntity {

	public PartnerEntity() {
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
	//伙伴
	private String partner;

	//伙伴
	private String partnerName;

	//伙伴
	private String address;

	//伙伴
	private String mobile;

	//创建日期
	private String createDate;
	//创建时间
	private String createTime;

	//编辑日期
	private String editDate;
	//编辑时间
	private String editTime;

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

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
		this.setPartnerName(partner);
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = CommonTransMethod.getPartnerName(partnerName);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
