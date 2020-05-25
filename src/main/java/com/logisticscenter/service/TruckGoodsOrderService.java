package com.logisticscenter.service;

import java.util.List;
import java.util.Map;

import com.javabean.TruckGoodsOrderDetailBean;
import com.javabean.TruckGoodsOrderTakerBean;

public interface TruckGoodsOrderService {
	
	/**
	 * @param params
	 * @return
	 */
	public abstract Map getCondition(Map params);
	
	/**
	 * 预录车辆详细信息
	 * @param params
	 * @return
	 */
	public abstract Map getTableInfoList(Map params);

	/**
	 * 预录车辆详细信息
	 * @param params
	 * @return
	 */
	public abstract Map getOrderTakersInfoFields(Map params);


	

}
