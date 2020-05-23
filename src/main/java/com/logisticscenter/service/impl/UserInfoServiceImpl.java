package com.logisticscenter.service.impl;

import com.logisticscenter.mapper.UserDao;
import com.logisticscenter.model.LoginInfoEntity;
import com.logisticscenter.model.UserEntity;
import com.logisticscenter.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	LoginInfoEntity loginInfoEntity = new LoginInfoEntity();

	public LoginInfoEntity getLoginInfoEntity() {
		return loginInfoEntity;
	}

	public void setLoginInfoEntity(LoginInfoEntity loginInfoEntity) {
		this.loginInfoEntity = loginInfoEntity;
	}

	@Autowired
	UserDao userDao;


	@Override
	public Map getUsers(Map<String, Object> params) {
		Map retMap = new HashMap();
		List<UserEntity> userEntity = (List<UserEntity>)userDao.getCurrentUser(params);
		return retMap;
	}

	@Override
	public Map getCurrentUser(Map<String, Object> params) {
		Map retMap = new HashMap();
		String id = loginInfoEntity.getId()+"";
//		userDao.getUsers(params);
		retMap.put("address","西湖区工专路 77 号");
		retMap.put("avatar","https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
		retMap.put("country","China");
		retMap.put("email","antdesign@alipay.com");
		Map<String,Map> geographicMap = new HashMap<String,Map>();
		Map childCity = new HashMap();
		childCity.put("key","330100");
		childCity.put("label","杭州市");
		Map childprovince = new HashMap();
		childCity.put("key","330000");
		childCity.put("label","浙江省");
		geographicMap.put("city",childCity);
		geographicMap.put("province",childprovince);
		retMap.put("geographic",geographicMap);
		retMap.put("group","蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED");
		retMap.put("name","Serati Ma");
		retMap.put("phone","0752-268888888");
		retMap.put("signature","海纳百川，有容乃大");
		List tagsList = new ArrayList();
		Map tagMap = new HashMap();
		tagMap.put("key","0");
		tagMap.put("label","xxxxx");
		tagsList.add(tagMap);
		tagMap = new HashMap();
		tagMap.put("key","1");
		tagMap.put("label","xxxxx");
		tagsList.add(tagMap);
		tagMap = new HashMap();
		tagMap.put("key","2");
		tagMap.put("label","xxxxx");
		tagsList.add(tagMap);
		retMap.put("tags",tagsList);
		retMap.put("title","交互专家");
		retMap.put("userid","00000001");
		return retMap;
	}
}
