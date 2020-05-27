package com.logisticscenter.service;

import java.util.List;
import java.util.Map;

import com.javabean.GoodsTypeBean;

public interface GoodsTypeService {

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getCondition(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map insertGoodsType(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getTableInfoList(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getGoodsTypeFields(Map<String, Object> params);

	/**
	 * @return
	 */
	public abstract Map getAllGoodsType();

	/**
	 * @param params
	 * @return
	 */
	public abstract Map deleteGoodsType(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map updateGoodsType(Map<String, Object> params);

}
