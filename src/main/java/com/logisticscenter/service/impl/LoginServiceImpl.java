package com.logisticscenter.service.impl;

import java.util.*;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.consatnt.CacheConstant;
import com.common.consatnt.SessionConstant;
import com.logisticscenter.mapper.LoginInfoDao;
import com.logisticscenter.mapper.TruckSetDao;
import com.logisticscenter.model.LoginInfoEntity;
import com.logisticscenter.model.TruckSetEntity;
import com.logisticscenter.service.LoginService;
import com.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class LoginServiceImpl implements LoginService {

	private static List<Map<String,LoginInfoEntity>> systemInfo = new ArrayList<Map<String,LoginInfoEntity>>();

	@Autowired
	LoginInfoDao loginInfoDao;

	@Autowired
	TruckSetDao truckSetDao;

	@Override
	public Map deleteSystemInfo(Map<String, Object> params) {
		Map retResult = new HashMap();
		String id = Utils.null2String((String)params.get("id"),"-1");
		loginInfoDao.deleteSystemInfo(id);
		return retResult;
	}

	@Override
	public Map getSystemInfo(Map<String, Object> params) {
		Map retResult = new HashMap();
		String loginid = (String)params.get("userName");
		String password = (String)params.get("password");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			LoginInfoEntity loginInfoEntity = loginInfoDao.getSystemInfo((String)params.get("userName"));
			Map result = new HashMap();
			int status = 0;
			if(loginInfoEntity.getPassword().equals(password)){
				status = setUserBeanCacheAndSession(request,loginInfoEntity);

			}else if(!loginInfoEntity.getPassword().equals(password)){
				status = 1;
			}else if("".equals(loginInfoEntity.getPassword())){
				status = 2;
			}
			retResult.put("status",status);
			retResult.put("currentAuthority",loginid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//返回json对象
		return retResult;
	}


	@Override
	public Map updateSystemInfo(Map<String, Object> params) {
//		LoginInfoEntity systemE = new LoginInfoEntity();
//		systemDao.updateSystemInfo(systemE);
		return null;
	}
	
	@Override
	public Map updateAllSystemInfo(Map<String, Object> params) {
//		LoginInfoEntity systemE = new LoginInfoEntity();
//		systemDao.updateAllSystemInfo(systemE);
		return null;
	}

	@Override
	public Map insertSystemInfo(Map<String, Object> params) {
//		LoginInfoEntity systemE = (LoginInfoEntity) ConvertService.convertBeanToEntity(insertInfo, new LoginInfoEntity());
//		systemDao.insertSystemInfo(systemE);
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 设置缓存
	 * @param request
	 * @return
	 */
	private int setUserBeanCacheAndSession(HttpServletRequest request,LoginInfoEntity loginInfoEntity){
		int status = 0;
		//获得是否可以重复登录
		Cache cache = CacheManager.getCacheInfo(CacheConstant.TRUCKSET_BEAN_CACHE);
		TruckSetEntity truckSetEntity = new TruckSetEntity();
		Date date = new Date();
		if(cache == null){
			truckSetEntity = truckSetDao.getTruckSet();
			cache = new Cache();
			cache.setKey(CacheConstant.TRUCKSET_BEAN);
			cache.setTimeOut(date.getTime());
			cache.setValue(truckSetEntity);
			CacheManager.putCache(CacheConstant.TRUCKSET_BEAN_CACHE, cache);
		}else{
			truckSetEntity = (TruckSetEntity)cache.getValue();
		}


		//设置登陆缓存
		cache = new Cache();
		cache.setKey(CacheConstant.USER_BEAN);
		cache.setTimeOut(date.getTime());
		cache.setValue(loginInfoEntity);
		CacheManager.putCache(CacheConstant.USER_BEAN_CACHE, cache);
//		//检查是否允许重复登陆
//		int reLogin = truckSetEntity.getReLogin();
//		//预留接口:是否允许重复登陆
//		if(reLogin == 1){
//			status = getIsLogin(request);
//		}
//		//设置session
//		if(status > -1){
//			setLoginSession(request,loginInfoEntity);
//		}

		return status;
	}

	/**
	 * 是否已经登录
	 * @param request
	 * @return
	 */
	private int getIsLogin(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		LoginInfoEntity loginInfoEntity = (LoginInfoEntity)session.getAttribute(SessionConstant.USER_SESSION);
		if(loginInfoEntity == null){
			return 0;
		}else{
			return -1;
		}

	}


	/**
	 * 设置登陆session
	 * @param request
	 * @return 是否已经登录
	 */
	public void setLoginSession(HttpServletRequest request,LoginInfoEntity loginInfoEntity){
		HttpSession session = request.getSession();
		session.setAttribute(SessionConstant.USER_SESSION,loginInfoEntity);

	}

}
