package com.logisticscenter.model;

import com.javabean.TruckBean;
import com.util.Utils;

import java.math.BigDecimal;
import java.util.Calendar;

public class TruckEntity {
	
	public TruckEntity(){
		Calendar today = Calendar.getInstance();
		String currentdate = Utils.add0(today.get(Calendar.YEAR), 4) + "-" + Utils.add0(today.get(Calendar.MONTH) + 1, 2) + "-" + Utils.add0(today.get(Calendar.DAY_OF_MONTH), 2);
		String currenttime = Utils.add0(today.get(Calendar.HOUR_OF_DAY), 2) + ":" + Utils.add0(today.get(Calendar.MINUTE), 2) + ":" + Utils.add0(today.get(Calendar.SECOND), 2);
		this.editDate = currentdate;
		this.editTime = currenttime;
		this.createDate = currentdate;
		this.createTime = currenttime;
	}

	//id
	private int id;
	
	//车牌号码
	private String  truckNumber ;
	
	//户主
	private String  truckOwner ;
	
	//车牌型号
	private String  truckBrand ;
	
	//车辆名称
	private String  truckName ;
	
	//司机联系方式
	private String  contactNumber ;
	
	//车辆类型
	private int  truckType ;
	
	//司机
	private int  driver ;
	
	//车辆颜色
	private String  truckColor ;
	
	//车辆长度
	private BigDecimal  truckLength ;
	
	//车辆宽度
	private BigDecimal  truckWidth ;
	
	//车辆高度
	private BigDecimal  truckHeight ;
	
	//标准载重
	private BigDecimal  standardWeight ;
	
	//驾驶证号
	private String  driverLicense ;
	
	//发动机号
	private String  engineNumber ;
	
	//生产日期
	private String  madeDate ;
	
	//买进日期
	private String  buyDate ;
	
	//原值
	private BigDecimal  worth ;
	
	//买进价格
	private BigDecimal  buyCost ;
	
	//备注
	private String  remark ;
	
	//创建日期
	private String createDate;
	
	//创建时间
	private String createTime;
	
	//编辑日期
	private String editDate;
	
	//编辑时间
	private String editTime;
	
	//查询获得交商业险时间
	private String commercialDate;
	
	//查询获得交强制险时间
	private String compulsoryDate;
	
	//查询用买进时间段(开始时间)
	private String buyStartDate;
	
	//查询用买进时间段(开始时间)
	private String buyEndDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTruckNumber() {
		return truckNumber;
	}
	public void setTruckNumber(String truckNumber) {
		this.truckNumber = truckNumber;
	}
	public String getTruckOwner() {
		return truckOwner;
	}
	public void setTruckOwner(String truckOwner) {
		this.truckOwner = truckOwner;
	}
	public String getTruckBrand() {
		return truckBrand;
	}
	public void setTruckBrand(String truckBrand) {
		this.truckBrand = truckBrand;
	}
	public String getTruckName() {
		return truckName;
	}
	public void setTruckName(String truckName) {
		this.truckName = truckName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public int getTruckType() {
		return truckType;
	}
	public void setTruckType(int truckType) {
		this.truckType = truckType;
	}
	public int getDriver() {
		return driver;
	}
	public void setDriver(int driver) {
		this.driver = driver;
	}
	public String getTruckColor() {
		return truckColor;
	}
	public void setTruckColor(String truckColor) {
		this.truckColor = truckColor;
	}
	public BigDecimal getTruckLength() {
		return truckLength;
	}
	public void setTruckLength(BigDecimal truckLength) {
		this.truckLength = truckLength;
	}
	public BigDecimal getTruckWidth() {
		return truckWidth;
	}
	public void setTruckWidth(BigDecimal truckWidth) {
		this.truckWidth = truckWidth;
	}
	public BigDecimal getTruckHeight() {
		return truckHeight;
	}
	public void setTruckHeight(BigDecimal truckHeight) {
		this.truckHeight = truckHeight;
	}
	public BigDecimal getStandardWeight() {
		return standardWeight;
	}
	public void setStandardWeight(BigDecimal standardWeight) {
		this.standardWeight = standardWeight;
	}
	public String getDriverLicense() {
		return driverLicense;
	}
	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	public String getMadeDate() {
		return madeDate;
	}
	public void setMadeDate(String madeDate) {
		this.madeDate = madeDate;
	}
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public BigDecimal getWorth() {
		return worth;
	}
	public void setWorth(BigDecimal worth) {
		this.worth = worth;
	}
	public BigDecimal getBuyCost() {
		return buyCost;
	}
	public void setBuyCost(BigDecimal buyCost) {
		this.buyCost = buyCost;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getCommercialDate() {
		return commercialDate;
	}

	public void setCommercialDate(String commercialDate) {
		this.commercialDate = commercialDate;
	}

	public String getCompulsoryDate() {
		return compulsoryDate;
	}

	public void setCompulsoryDate(String compulsoryDate) {
		this.compulsoryDate = compulsoryDate;
	}

	public String getBuyStartDate() {
		return buyStartDate;
	}

	public void setBuyStartDate(String buyStartDate) {
		this.buyStartDate = buyStartDate;
	}

	public String getBuyEndDate() {
		return buyEndDate;
	}

	public void setBuyEndDate(String buyEndDate) {
		this.buyEndDate = buyEndDate;
	}
	
	
	
}
