package com.logisticscenter.model;

import com.util.Utils;

import java.math.BigDecimal;
import java.util.Calendar;

public class TruckGoodsOrderDetailEntity {
	
	public TruckGoodsOrderDetailEntity(){
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

	//对应预录车辆信息中的reportId
	private int reportId;
	//客户货物类型
	private int goodsType;
	//单价
	private BigDecimal price;
	//是否删除
	private int deleteFlg;
	//载重
	private BigDecimal realCarry;
	//是否开票
	private int invoiceFlg;
	//始发地
	private String startPlace;
	//目的地
	private String endPlace;
	//区域
	private String workPlace;

	//创建日期
	private String createDate;
	//创建时间
	private String createTime;
	
	//修改日期
	private String editDate;
	//修改时间
	private String editTime;
	
	//检索用货物类型
	private String goodsTypes;

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

	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public int getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(int goodsType) {
		this.goodsType = goodsType;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getRealCarry() {
		return realCarry;
	}
	public void setRealCarry(BigDecimal realCarry) {
		this.realCarry = realCarry;
	}
	public int getInvoiceFlg() {
		return invoiceFlg;
	}
	public void setInvoiceFlg(int invoiceFlg) {
		this.invoiceFlg = invoiceFlg;
	}
	public String getStartPlace() {
		return startPlace;
	}
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}
	public String getEndPlace() {
		return endPlace;
	}
	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
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


}
