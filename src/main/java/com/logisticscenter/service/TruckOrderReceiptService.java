package com.logisticscenter.service;

import java.util.Map;

public interface TruckOrderReceiptService {
	
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
	public abstract Map getOrderReceiptInfoFields(Map params);

	/**
	 * 保存预录车辆详细信息
	 * @param params
	 * @return
	 */
	public abstract Map addOrderReceipt(Map params);

	/**
	 * 更新预录车辆详细信息
	 * @param params
	 * @return
	 */
	public abstract Map updateOrderReceipt(Map params);

	/**
	 * 删除预录车辆详细信息
	 * @param params
	 * @return
	 */
	public abstract Map deleteOrderReceipt(Map params);


	

}
