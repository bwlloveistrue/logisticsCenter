package com.logisticscenter.service;

import java.util.List;
import java.util.Map;

import com.javabean.FeeTypeBean;

public interface FeeTypeService {

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getCondition(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map insertFeeType(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getTableInfoList(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getFeeTypeFields(Map<String, Object> params);

	/**
	 * @return
	 */
	public abstract Map getAllFeeType();

	/**
	 * @param params
	 * @return
	 */
	public abstract Map deleteFeeType(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map updateFeeType(Map<String, Object> params);

}
