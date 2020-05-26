package com.logisticscenter.service;

import java.util.List;
import java.util.Map;

//import com.entity.DriverInfoEntity;
import com.javabean.DriverInfoBean;
import com.logisticscenter.model.DriverInfoEntity;

public interface DriverService {

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getCondition(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map insertDriverInfo(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getTableInfoList(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getDriverInfoFields(Map<String, Object> params);

	/**
	 * @return
	 */
	public abstract Map getAllDriverInfo();

	/**
	 * @param params
	 * @return
	 */
	public abstract Map deleteDriverInfo(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map updateDriverInfo(Map<String, Object> params);

}
