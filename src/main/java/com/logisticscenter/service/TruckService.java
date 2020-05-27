package com.logisticscenter.service;

import java.util.List;
import java.util.Map;

import com.javabean.TruckBean;
import com.logisticscenter.model.TruckEntity;

public interface TruckService {

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getCondition(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map insertTruck(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getTableInfoList(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getTruckFields(Map<String, Object> params);

	/**
	 * @return
	 */
	public abstract Map getAllTruck();

	/**
	 * @param params
	 * @return
	 */
	public abstract Map deleteTruck(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map updateTruck(Map<String, Object> params);


}
