package com.logisticscenter.service;

import com.logisticscenter.model.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

	/**
	 * 获取用户列表
	 * @param params
	 * @return
	 */
	public abstract Map getUsers(Map<String, Object> params);

	/**
	 * 获取当前用户
	 * @param params
	 * @return
	 */
	public abstract Map getCurrentUser(Map<String, Object> params);

}
