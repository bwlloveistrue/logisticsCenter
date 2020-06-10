package com.logisticscenter.model;

import com.common.CommonTransMethod;
import com.splitPage.OrderReceiptSplitPage;
import com.splitPage.OrderTakerDetailSplitPage;
import com.splitPage.PageCell;
import com.splitPage.pageInterface.SplitPageInterface;
import com.util.Utils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class TruckGoodsReportEntity {
	SplitPageInterface splitPageInterface = new OrderTakerDetailSplitPage();

	public TruckGoodsReportEntity() {
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
	//订单编号
	private String reportNumber;

	//是否包车
	private String packageFlg;
	private String packageFlgShow;
	//包车价格
	private BigDecimal packagePrice;
	//发货状态
	private int truckPart;
	//伙伴
	private String partner;
	//伙伴价格
	private String partnerPrice;
	//伙伴载重
	private String partnerCarry;
	//车牌号码
	private String truckNumber;
	//车牌号码
	private String truckNumberName;
	//预录订单号
	private int reportId;

	// 删除标识
	private int deleteFlg;

	//发车时间
	private String beginDate;
	//预计到货时间
	private String expectedDate;
	//实际到货时间
	private String endDate;
	//司机
	private String driver;
	//司机
	private String driverName;
	//客户
	private String client;
	//客户
	private String clientName;
	//是否预支费用
	private int prepaidFlg;
	//预支费用
	private BigDecimal prepaidExpress;
	//预支费用说明
	private String prepaidDesc;
	//客户货物类型
	private String goodsType;
	//客户货物类型
	private String goodsTypeName;
	//状态
	private int reportStatus;
	//是否迟到
	private int isLater;

	// 缴费单
	private String payAccessory;

	//签收单
	private String signAccessory;

	//出库单
	private String checkOutAccessory;

	//迟到原因
	private String laterReason;
	//运费金额
	private BigDecimal expensens;
	//盈利
	private BigDecimal profit;
	//费用总计
	private BigDecimal cost;
	//托运单号
	private String carryNumber;
	//区域
	private String workPlace;
	//备注
	private String remark;
	//客户订单编号
	private String customerOrder;
	//是否结算运费
	private int settlement;
	// 吊费
	private BigDecimal lifitingCost ;
	// 加工费
	private BigDecimal processingFee ;
	// 装货费用是否结清
	private int loadIsSettle ;
	//油费
	private BigDecimal oilFee ;
	//过路费
	private BigDecimal tollFee ;
	//修车费
	private BigDecimal repairFee ;
	//轮胎费
	private BigDecimal tyreFee ;
	//带路费
	private BigDecimal guideWayFee ;
	//其他
	private BigDecimal otherFee ;
	//补助
	private BigDecimal allowance ;
	//罚款
	private BigDecimal forfeit ;
	//配件
	private BigDecimal parts ;
	//breakRulesFee
	private BigDecimal breakRulesFee ;

	//创建日期
	private String createDate;
	//创建时间
	private String createTime;
	
	//修改日期
	private String editDate;
	//修改时间
	private String editTime;

	//用作列表主数据的明细显示
	private List<TruckGoodsOrderDetailEntity> childInfo;
	//用作列表主数据的明细显示
	private List<PageCell>  childColumns = splitPageInterface.createColumn() ;

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
	
	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public String getPackageFlg() {
		return packageFlg;
	}

	public void setPackageFlg(String packageFlg) {
		this.packageFlg = packageFlg;
		this.setPackageFlgShow(packageFlg+"");
	}

	public BigDecimal getPackagePrice() {
		return packagePrice;
	}

	public void setPackagePrice(BigDecimal packagePrice) {
		this.packagePrice = packagePrice;
	}
	
	public int getTruckPart() {
		return truckPart;
	}

	public void setTruckPart(int truckPart) {
		this.truckPart = truckPart;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getPartnerPrice() {
		return partnerPrice;
	}

	public void setPartnerPrice(String partnerPrice) {
		this.partnerPrice = partnerPrice;
	}
	
	public String getPartnerCarry() {
		return partnerCarry;
	}

	public void setPartnerCarry(String partnerCarry) {
		this.partnerCarry = partnerCarry;
	}

	public String getTruckNumber() {
		return truckNumber;
	}

	public void setTruckNumber(String truckNumber) {
		this.truckNumber = truckNumber;
		this.setTruckNumberName(truckNumber);
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
		this.setDriverName(driver+"");
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
		this.setClientName(client+"");
	}

	public int getPrepaidFlg() {
		return prepaidFlg;
	}

	public void setPrepaidFlg(int prepaidFlg) {
		this.prepaidFlg = prepaidFlg;
	}

	public BigDecimal getPrepaidExpress() {
		return prepaidExpress;
	}

	public void setPrepaidExpress(BigDecimal prepaidExpress) {
		this.prepaidExpress = prepaidExpress;
	}

	public String getPrepaidDesc() {
		return prepaidDesc;
	}

	public void setPrepaidDesc(String prepaidDesc) {
		this.prepaidDesc = prepaidDesc;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
		this.setGoodsTypeName(goodsType);
	}

	public int getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(int reportStatus) {
		this.reportStatus = reportStatus;
	}

	public int getIsLater() {
		return isLater;
	}

	public void setIsLater(int isLater) {
		this.isLater = isLater;
	}

	public String getLaterReason() {
		return laterReason;
	}

	public void setLaterReason(String laterReason) {
		this.laterReason = laterReason;
	}

	public BigDecimal getExpensens() {
		return expensens;
	}

	public void setExpensens(BigDecimal expensens) {
		this.expensens = expensens;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getCarryNumber() {
		return carryNumber;
	}

	public void setCarryNumber(String carryNumber) {
		this.carryNumber = carryNumber;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(String customerOrder) {
		this.customerOrder = customerOrder;
	}

	public int getSettlement() {
		return settlement;
	}

	public void setSettlement(int settlement) {
		this.settlement = settlement;
	}

	public String getPackageFlgShow() {
		return packageFlgShow;
	}

	public void setPackageFlgShow(String packageFlgShow) {
		this.packageFlgShow = CommonTransMethod.getIsOrNotString(packageFlgShow);
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = CommonTransMethod.getClientName(clientName);
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = CommonTransMethod.getGoodsTypeName(goodsTypeName);
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = CommonTransMethod.getDriverName(driverName);;
	}

	public String getTruckNumberName() {
		return truckNumberName;
	}

	public void setTruckNumberName(String truckNumberName) {
		this.truckNumberName = CommonTransMethod.getTruckNumberName(truckNumberName);
	}

	public String getPayAccessory() {
		return payAccessory;
	}

	public void setPayAccessory(String payAccessory) {
		this.payAccessory = payAccessory;
	}

	public String getSignAccessory() {
		return signAccessory;
	}

	public void setSignAccessory(String signAccessory) {
		this.signAccessory = signAccessory;
	}

	public String getCheckOutAccessory() {
		return checkOutAccessory;
	}

	public void setCheckOutAccessory(String checkOutAccessory) {
		this.checkOutAccessory = checkOutAccessory;
	}

	public BigDecimal getLifitingCost() {
		return lifitingCost;
	}

	public void setLifitingCost(BigDecimal lifitingCost) {
		this.lifitingCost = lifitingCost;
	}

	public BigDecimal getProcessingFee() {
		return processingFee;
	}

	public void setProcessingFee(BigDecimal processingFee) {
		this.processingFee = processingFee;
	}

	public int getLoadIsSettle() {
		return loadIsSettle;
	}

	public void setLoadIsSettle(int loadIsSettle) {
		this.loadIsSettle = loadIsSettle;
	}

	public BigDecimal getOilFee() {
		return oilFee;
	}

	public void setOilFee(BigDecimal oilFee) {
		this.oilFee = oilFee;
	}

	public BigDecimal getTollFee() {
		return tollFee;
	}

	public void setTollFee(BigDecimal tollFee) {
		this.tollFee = tollFee;
	}

	public BigDecimal getRepairFee() {
		return repairFee;
	}

	public void setRepairFee(BigDecimal repairFee) {
		this.repairFee = repairFee;
	}

	public BigDecimal getTyreFee() {
		return tyreFee;
	}

	public void setTyreFee(BigDecimal tyreFee) {
		this.tyreFee = tyreFee;
	}

	public BigDecimal getGuideWayFee() {
		return guideWayFee;
	}

	public void setGuideWayFee(BigDecimal guideWayFee) {
		this.guideWayFee = guideWayFee;
	}

	public BigDecimal getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(BigDecimal otherFee) {
		this.otherFee = otherFee;
	}

	public BigDecimal getAllowance() {
		return allowance;
	}

	public void setAllowance(BigDecimal allowance) {
		this.allowance = allowance;
	}

	public BigDecimal getForfeit() {
		return forfeit;
	}

	public void setForfeit(BigDecimal forfeit) {
		this.forfeit = forfeit;
	}

	public BigDecimal getParts() {
		return parts;
	}

	public void setParts(BigDecimal parts) {
		this.parts = parts;
	}

	public BigDecimal getBreakRulesFee() {
		return breakRulesFee;
	}

	public void setBreakRulesFee(BigDecimal breakRulesFee) {
		this.breakRulesFee = breakRulesFee;
	}

	public List<TruckGoodsOrderDetailEntity> getChildInfo() {
		return childInfo;
	}

	public void setChildInfo(List<TruckGoodsOrderDetailEntity> childInfo) {
		this.childInfo = childInfo;
	}

	public List<PageCell> getChildColumns() {
		return childColumns;
	}

	public void setChildColumns(List<PageCell> childColumns) {
		this.childColumns = childColumns;
	}

	public int getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
}
