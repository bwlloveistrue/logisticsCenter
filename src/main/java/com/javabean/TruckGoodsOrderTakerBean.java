package com.javabean;

import java.math.BigDecimal;

import com.common.ConvertService;
import com.util.ConstantUtils;

public class TruckGoodsOrderTakerBean {
	
	public TruckGoodsOrderTakerBean(){}
	//查询/新增用
	public TruckGoodsOrderTakerBean(int id,int client, String goodsType, int packageFlg, BigDecimal packagePrice, String beginDate, String startPlace, String endPlace,int orderStatus,String workPlace,String pageSize,String currentPage){
		this.id = id;
		this.client = client;
		this.goodsType = goodsType ;
		this.packageFlg = packageFlg ;
		this.packagePrice = ConvertService.getDecimalValue(packagePrice+"", ConstantUtils.defaultDecimal) ;
		this.beginDate = beginDate ;
		this.startPlace = startPlace;
		this.endPlace = endPlace;
		this.orderStatus = orderStatus;
		this.workPlace = workPlace;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	/**
	 * 用于更新orderStatus
	 * @param id
	 * @param orderStatus
	 */
	public TruckGoodsOrderTakerBean(int id, int orderStatus){
		this.id = id;
		this.orderStatus = orderStatus;
		
	}
	
	//标识ID
	private int id;
	//是否包车
	private int packageFlg;
	//包车价格
	private BigDecimal packagePrice;
	//发车时间
	private String beginDate;
	//客户
	private int client;
	//是否删除
	private int deleteFlg;
	//客户货物类型
	private String goodsType;
	private String startPlace;
	private String endPlace;
	//预录状态
	private int orderStatus;
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
	
	//pageSize
	private String pageSize;
	
	//currentPage
	private String currentPage;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPackageFlg() {
		return packageFlg;
	}
	
	public void setPackageFlg(int packageFlg) {
		this.packageFlg = packageFlg;
	}
	
	public BigDecimal getPackagePrice() {
		return packagePrice;
	}
	public void setPackagePrice(BigDecimal packagePrice) {
		this.packagePrice = packagePrice;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}


	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}


	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
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
	public int getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
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
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}	

}
