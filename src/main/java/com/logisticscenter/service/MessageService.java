package com.logisticscenter.service;

import java.util.Map;

public interface MessageService {

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getCondition(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map insertMessage(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getTableInfoList(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map getMessageInfoFields(Map<String, Object> params);

	/**
	 * @return
	 */
	public abstract Map getAllMessage();

	/**
	 * @param params
	 * @return
	 */
	public abstract Map deleteMessage(Map<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public abstract Map updateMessage(Map<String, Object> params);

	/**
	 * @return
	 */
	public abstract Map getAllSendType();

	/**
	 * @return
	 */
	public abstract Map getAllWorkflowType();
}
