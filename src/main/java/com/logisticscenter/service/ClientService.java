package com.logisticscenter.service;

import java.util.List;
import java.util.Map;

import com.javabean.ClientBean;
import com.javabean.FeeTypeBean;

public interface ClientService {

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getCondition(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map insertClient(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getTableInfoList(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getClientInfoFields(Map<String, Object> params);

	/**
	 * @return
	 */
	public abstract Map getAllClient();

	/**
	 * @param params
	 * @return
	 */
	public abstract Map deleteClient(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map updateClient(Map<String, Object> params);
}
