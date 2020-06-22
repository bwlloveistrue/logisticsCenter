package com.logisticscenter.service;

import java.util.List;
import java.util.Map;

import com.javabean.CompulsoryBean;
import com.javabean.CompulsoryBean;

public interface CompulsoryService {

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getCondition(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map insertCompulsory(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getTableInfoList(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getCompulsoryFields(Map<String, Object> params);

	/**
	 * @return
	 */
	public abstract Map getAllCompulsory();

	/**
	 * @param params
	 * @return
	 */
	public abstract Map deleteCompulsory(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map updateCompulsory(Map<String, Object> params);

}
