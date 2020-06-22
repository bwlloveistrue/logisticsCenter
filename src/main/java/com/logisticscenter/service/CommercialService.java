package com.logisticscenter.service;

import java.util.List;
import java.util.Map;

import com.javabean.CommercialBean;

public interface CommercialService {

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getCondition(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map insertCommercial(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getTableInfoList(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getCommercialFields(Map<String, Object> params);

	/**
	 * @return
	 */
	public abstract Map getAllCommercial();

	/**
	 * @param params
	 * @return
	 */
	public abstract Map deleteCommercial(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map updateCommercial(Map<String, Object> params);

}
