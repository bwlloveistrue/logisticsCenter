package com.logisticscenter.service.impl;

import java.util.*;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.ConvertService;
import com.logisticscenter.mapper.ClientDao;
import com.logisticscenter.model.ClientEntity;
import com.logisticscenter.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientDao clientDao;

	public Map getClient(Map<String, Object> params){
		List<ClientEntity> entityList = new ArrayList<ClientEntity>();
		List<Cache> cacheList = CacheManager.getCacheListInfo("clientEntity_CACHE");
		Map retResult = new HashMap();
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((ClientEntity)cacheList.get(i).getValue());
			}
		}else{
			entityList = clientDao.getAllClient();
			Cache cache = null;
			Date date = new Date();
			List <Cache> beanCacheLst = new ArrayList<Cache>();
			for(int i = 0;i<entityList.size();i++){
				cache = new Cache();
				cache.setKey(entityList.get(i).getId()+"");
				cache.setTimeOut(date.getTime());
				cache.setValue(entityList.get(i));
				beanCacheLst.add(cache);
			}
			CacheManager.putCacheList("clientEntity_CACHE", beanCacheLst);
		}

		retResult.put("clientInfo",entityList);
		return retResult;
	}

	@Override
	public Map getAllClient() {
		List<ClientEntity> entityList = new ArrayList<ClientEntity>();
		List<Cache> cacheList = CacheManager.getCacheListInfo("clientEntity_CACHE");
		Map retResult = new HashMap();
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((ClientEntity)cacheList.get(i).getValue());
			}
		}else{
			entityList = clientDao.getAllClient();
			Cache cache = null;
			Date date = new Date();
			List <Cache> beanCacheLst = new ArrayList<Cache>();
			for(int i = 0;i<entityList.size();i++){
				cache = new Cache();
				cache.setKey(entityList.get(i).getId()+"");
				cache.setTimeOut(date.getTime());
				cache.setValue(entityList.get(i));
				beanCacheLst.add(cache);
			}
			CacheManager.putCacheList("clientEntity_CACHE", beanCacheLst);
		}

		retResult.put("clientInfo",entityList);
		return retResult;
	}

	@Override
	public Map insertClient(Map<String, Object> params) {
		Map retResult = new HashMap();
		ClientEntity clientE = new ClientEntity();
		clientE.setClientName((String)params.get("clientName"));
		clientE.setContant((String)params.get("contant"));
		clientE.setMobile((String)params.get("mobile"));
		clientE.setFax((String)params.get("fax"));
		clientE.setAddress((String)params.get("address"));
		clientE.setProducts((String)params.get("products"));
		clientE.setCreateDate(ConvertService.getDate());
		clientE.setCreateTime(ConvertService.getTime());
		int maxId = clientDao.insertClient(clientE);
		int c     = clientE.getId();
		CacheManager.clearOnly("clientBean_CACHE");
		retResult.put("id",maxId);
		retResult.put("c",c);
		return retResult;
	}


	@Override
	public Map updateClient(Map<String, Object> params) {
		int count=0;
		Map retResult = new HashMap();
		ClientEntity clientE = new ClientEntity();
		clientE.setId(Integer.parseInt((String)params.get("id")));
		clientE.setClientName((String)params.get("clientName"));
		clientE.setContant((String)params.get("contant"));
		clientE.setMobile((String)params.get("mobile"));
		clientE.setFax((String)params.get("fax"));
		clientE.setAddress((String)params.get("address"));
		clientE.setProducts((String)params.get("products"));
		clientE.setCreateDate(ConvertService.getDate());
		clientE.setCreateTime(ConvertService.getTime());
		count = clientDao.updateClient(clientE);
		CacheManager.clearOnly("clientBean_CACHE");
		retResult.put("count",count);
		return retResult;
	}

	@Override
	public Map deleteClient(Map<String, Object> params) {
		Map retResult = new HashMap();
//		int count = clientDao.deleteClient(Arrays.asList((String[])params.get("id")));
//		retResult.put("count",count);
		CacheManager.clearOnly("clientBean_CACHE");
		return retResult;

	}

}
