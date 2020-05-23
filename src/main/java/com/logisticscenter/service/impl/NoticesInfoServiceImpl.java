package com.logisticscenter.service.impl;

import com.logisticscenter.mapper.NoticesInfoDao;
import com.logisticscenter.mapper.UserDao;
import com.logisticscenter.model.LoginInfoEntity;
import com.logisticscenter.model.UserEntity;
import com.logisticscenter.service.NoticesInfoService;
import com.logisticscenter.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticesInfoServiceImpl implements NoticesInfoService {

	LoginInfoEntity loginInfoEntity = new LoginInfoEntity();

	public LoginInfoEntity getLoginInfoEntity() {
		return loginInfoEntity;
	}

	public void setLoginInfoEntity(LoginInfoEntity loginInfoEntity) {
		this.loginInfoEntity = loginInfoEntity;
	}

	@Autowired
	NoticesInfoDao noticesInfoDao;


	@Override
	public Map getNotices(Map<String, Object> params) {
		Map retMap = new HashMap();
		retMap.put("notifyCount","12");
		retMap.put("unreadCount","15");

		List datasList = new ArrayList();
		Map childMap = new HashMap();
		childMap.put("avatar","https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png");
		childMap.put("datetime","2017-08-09");
		childMap.put("id","000000001");
		childMap.put("title","你收到了 14 份新周报");
		childMap.put("type","notification");
		datasList.add(childMap);
		childMap = new HashMap();

		childMap.put("avatar","https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png");
		childMap.put("datetime","2017-08-09");
		childMap.put("id","000000004");
		childMap.put("title","你收到了 14 份新周报");
		childMap.put("type","notification");
		datasList.add(childMap);
		childMap = new HashMap();
		childMap.put("avatar","https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png");
		childMap.put("datetime","2017-08-09");
		childMap.put("id","000000003");
		childMap.put("title","你收到了 14 份新周报");
		childMap.put("type","notification");
		datasList.add(childMap);
		childMap = new HashMap();
		childMap.put("avatar","https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png");
		childMap.put("datetime","2017-08-09");
		childMap.put("id","000000002");
		childMap.put("title","你收到了 14 份新周报");
		childMap.put("type","notification");
		datasList.add(childMap);
		retMap.put("datas",datasList);
		return retMap;
	}

	@Override
	public Map clearNotices(Map<String, Object> params) {
		return null;
	}
}
