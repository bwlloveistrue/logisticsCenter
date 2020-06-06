package com.logisticscenter.service;

import java.util.Map;

public interface PartnerService {

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getCondition(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map insertPartner(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getTableInfoList(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getPartnerInfoFields(Map<String, Object> params);

	/**
	 * @return
	 */
	public abstract Map getAllPartner();

	/**
	 * @param params
	 * @return
	 */
	public abstract Map deletePartner(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map updatePartner(Map<String, Object> params);


}
