package com.javabean;

import java.math.BigDecimal;

import com.common.consatnt.ParamConstant;
import com.util.Utils;

public class TruckBean {
	
	public TruckBean(){
		
	}
	
	public TruckBean(int id2, String truckNumber2, String truckOwner2,
			String truckBrand2, String truckName2, String contactNumber2,
			int truckType2, int driver2, String truckColor2,
			String trucklength2, String truckWidth2, String truckHeight2,
			String standardWeight2, String driverLicense2,
			String engineNumber2, String madeDate2, String buyDate2,
			String worth2, String buyCost2, String remark2) {
		id = id2;
		truckNumber = Utils.null2String(truckNumber2);
		truckOwner = Utils.null2String(truckOwner2);
		truckBrand = Utils.null2String(truckBrand2);
		truckName = Utils.null2String(truckName2);
		contactNumber = Utils.null2String(contactNumber2);
		truckType = truckType2;
		driver = driver2;
		truckColor = Utils.null2String(truckColor2);
		if(!"".equals(Utils.null2String(trucklength2))){
			truckLength = Utils.toDecimal(trucklength2,2);
		}else{
			truckLength = ParamConstant.defaultDecimal;
		}
		if(!"".equals(Utils.null2String(truckWidth2))){
			truckWidth = Utils.toDecimal(truckWidth2,2);
		}else{
			truckWidth = ParamConstant.defaultDecimal;
		}
		if(!"".equals(Utils.null2String(truckHeight2))){
			truckHeight = Utils.toDecimal(truckHeight2,2);
		}else{
			truckHeight = ParamConstant.defaultDecimal;
		}
		if(!"".equals(Utils.null2String(standardWeight2))){
			standardWeight = Utils.toDecimal(standardWeight2,2);
		}else{
			standardWeight = ParamConstant.defaultDecimal;
		}
		driverLicense = Utils.null2String(driverLicense2);
		engineNumber = Utils.null2String(engineNumber2);
		madeDate = Utils.null2String(madeDate2);
		buyDate = Utils.null2String(buyDate2);
		if(!"".equals(Utils.null2String(worth2))){
			worth = Utils.toDecimal(worth2,2);
		}else{
			worth = ParamConstant.defaultDecimal;
		}
		if(!"".equals(Utils.null2String(buyCost2))){
			buyCost = Utils.toDecimal(buyCost2,2);
		}else{
			buyCost = ParamConstant.defaultDecimal;
		}
		remark = Utils.null2String(remark2);
	}
	
	public TruckBean(String truckNumber2,String truckBrand2,int driver2,String engineNumber2,String buyStartDate2,String buyEndDate2,String pageSize,String currentPage) {
		this.truckNumber = Utils.null2String(truckNumber2);
		this.truckBrand = Utils.null2String(truckBrand2);
		this.driver = driver2;
		this.engineNumber = engineNumber2;
		this.buyStartDate = buyStartDate2;
		this.buyEndDate = buyEndDate2;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		
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
	
	//交商业险时间
	private String commercialDate;
	
	//交强制险时间
	private String compulsoryDate;
	
	//查询用买进时间段(开始时间)
	private String buyStartDate;
	
	//查询用买进时间段(开始时间)
	private String buyEndDate;
	
	//pageSize
	private String pageSize;
	
	//currentPage
	private String currentPage;
	
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
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
