package com.logisticscenter.model;

import com.util.Utils;

import java.math.BigDecimal;
import java.util.Calendar;

public class TruckGoodsReportDetailEntity {
	
	public TruckGoodsReportDetailEntity(){
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
	//标识ID
	private int key;
	//对应预录车辆信息中的id
	private int truckOrder;
	//对应回执中的id
	private int reportId;
	//客户货物类型
	private String goodsType;

	private String truckNumber;

	//司机
	private String driver;

	// 伙伴总重
	private BigDecimal partnerCarry;

	// 伙伴价格
	private BigDecimal partnerPrice;

	//伙伴
	private int partner;

	// 分配状态
	private int truckPart;

	//是否删除
	private int deleteFlg;



	//创建日期
	private String createDate;
	//创建时间
	private String createTime;
	
	//修改日期
	private String editDate;
	//修改时间
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
	
	public int getTruckOrder() {
		return truckOrder;
	}
	public void setTruckOrder(int truckOrder) {
		this.truckOrder = truckOrder;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getTruckNumber() {
		return truckNumber;
	}

	public void setTruckNumber(String truckNumber) {
		this.truckNumber = truckNumber;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public BigDecimal getPartnerCarry() {
		return partnerCarry;
	}

	public void setPartnerCarry(BigDecimal partnerCarry) {
		this.partnerCarry = partnerCarry;
	}

	public BigDecimal getPartnerPrice() {
		return partnerPrice;
	}

	public void setPartnerPrice(BigDecimal partnerPrice) {
		this.partnerPrice = partnerPrice;
	}

	public int getPartner() {
		return partner;
	}

	public void setPartner(int partner) {
		this.partner = partner;
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
	public int getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public int getTruckPart() {
		return truckPart;
	}

	public void setTruckPart(int truckPart) {
		this.truckPart = truckPart;
	}
}
