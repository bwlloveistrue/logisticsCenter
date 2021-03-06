package com.logisticscenter.service;

import java.util.Map;

public interface TruckOrderApportionService {
	
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
	public abstract Map getOrderApportionFields(Map params);

	/**
	 * 保存预录车辆详细信息
	 * @param params
	 * @return
	 */
	public abstract Map saveOrderApportion(Map params);

	/**
	 * 分配车辆详细信息
	 * @param params
	 * @return
	 */
	public abstract Map dispatchOrderApportion(Map params);

	/**
	 * 删除预录车辆详细信息
	 * @param params
	 * @return
	 */
	public abstract Map deleteOrderApportion(Map params);


	

}
