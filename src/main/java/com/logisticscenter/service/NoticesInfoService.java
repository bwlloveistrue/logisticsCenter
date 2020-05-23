package com.logisticscenter.service;

import java.util.Map;

public interface NoticesInfoService {

	/**
	 * 获取通知
	 * @param params
	 * @return
	 */
	public abstract Map getNotices(Map<String, Object> params);

	/**
	 * 清空通知
	 * @param params
	 * @return
	 */
	public abstract Map clearNotices(Map<String, Object> params);

}
