package com.logisticscenter.service;

import java.util.List;
import java.util.Map;

import com.javabean.TruckGoodsOrderDetailBean;
import com.javabean.TruckGoodsOrderTakerBean;

public interface TruckGoodsOrderService {
	
	/**
	 * 获取高级搜索
	 * @param params
	 * @return
	 */
	public abstract Map getCondition(Map params);
	
	/**
	 * 获取录入列表详细信息
	 * @param params
	 * @return
	 */
	public abstract Map getTableInfoList(Map params);

	/**
	 * 预录订单详细信息
	 * @param params
	 * @return
	 */
	public abstract Map getOrderTakersInfoFields(Map params);

	/**
	 * 保存预录车辆详细信息
	 * @param params
	 * @return
	 */
	public abstract Map addOrderTaker(Map params);

	/**
	 * 更新预录车辆详细信息
	 * @param params
	 * @return
	 */
	public abstract Map updateOrderTaker(Map params);

	/**
	 * 删除预录车辆详细信息
	 * @param params
	 * @return
	 */
	public abstract Map deleteOrderTaker(Map params);


	

}
