package com.logisticscenter.service;

import java.util.Map;

public interface SubscribeService {

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getCondition(Map<String, Object> params);


	/**
	 * @param params
	 * @return
	 */
	public abstract Map getTableInfoList(Map<String, Object> params);
}
