package com.logisticscenter.service.impl;

import java.util.*;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.ConvertService;
import com.common.consatnt.CacheConstant;
import com.javabean.TruckBean;
import com.logisticscenter.mapper.DriverInfoDao;
import com.logisticscenter.mapper.TruckInfoDao;
import com.logisticscenter.model.DriverInfoEntity;
import com.logisticscenter.model.TruckEntity;
import com.logisticscenter.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TruckServiceImpl implements TruckService {

	@Autowired
	TruckInfoDao truckDao;

	@Autowired
	DriverInfoDao driverDao;

	@Override
	public int deleteTruck(String id) {
		int count =  truckDao.deleteTruck(id);
		return count;
	}

	@Override
	public TruckBean getTruckInfo(String id) {
		TruckBean dirverBean = (TruckBean) ConvertService.convertEntityToBean(truckDao.getTruckInfo(id), new TruckBean());
		return dirverBean;
	}

	@Override
	public List<TruckBean> getTruckInfo(TruckBean selectInfo) {
		TruckEntity TruckE = (TruckEntity)ConvertService.convertBeanToEntity(selectInfo,new TruckEntity());
		List<TruckEntity> entityList = new ArrayList<TruckEntity>();
		List<TruckBean> beanList = new ArrayList<TruckBean>();
		int pageSize =Integer.parseInt(TruckE.getPageSize());
		int currentPage =Integer.parseInt(TruckE.getCurrentPage());
		currentPage = (currentPage -1)*pageSize;
		TruckE.setCurrentPage(currentPage+"");
		entityList = truckDao.getTruckInfo(TruckE);
		for(int i=0;i<entityList.size(); i++){
			TruckBean dirverBean = (TruckBean) ConvertService.convertEntityToBean(entityList.get(i), new TruckBean());
			beanList.add(dirverBean);
		}
		return beanList;
		
	}
	
	@Override
	public String getTruckInfoCount(TruckBean selectInfo) {
		TruckEntity TruckE = (TruckEntity)ConvertService.convertBeanToEntity(selectInfo,new TruckEntity());
		String count = "";
		count = truckDao.getTruckInfoCount(TruckE);
		return count;
		
	}

	@Override
	public int updateTruck(TruckBean updateInfo) {
		int updId=0;
		TruckEntity truckE = (TruckEntity) ConvertService.convertBeanToEntity(updateInfo, new TruckEntity());
		truckE.setEditDate(ConvertService.getDate());
		truckE.setEditTime(ConvertService.getTime());
		updId = truckDao.updateTruck(truckE);
		return updId;
		
	}
	
	@Override
	public void updateAllTruck(TruckBean updateInfo) {
		TruckEntity truckE = (TruckEntity) ConvertService.convertBeanToEntity(updateInfo, new TruckEntity());
		truckE.setCreateDate(ConvertService.getDate());
		truckE.setCreateTime(ConvertService.getTime());
		truckDao.updateAllTruck(truckE);
		
	}

	@Override
	public int insertTruck(TruckBean insertInfo) {
		
		TruckEntity truckE = (TruckEntity) ConvertService.convertBeanToEntity(insertInfo, new TruckEntity());
		truckE.setCreateDate(ConvertService.getDate());
		truckE.setCreateTime(ConvertService.getTime());
		int statusFlg = truckDao.insertTruck(truckE);
		// TODO Auto-generated method stub
		return statusFlg;
	}

	@Override
	public Map getTruck(Map params){
		Map retMap = new HashMap();
		List<TruckEntity> entityList = new ArrayList<TruckEntity>();
		List<Cache> cacheList = CacheManager.getCacheListInfo("truckEntity_CACHE");
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((TruckEntity)cacheList.get(i).getValue());
			}
		}else{
			entityList = truckDao.getTruck();
			Cache cache = null;
			Date date = new Date();
			List <Cache> beanCacheLst = new ArrayList<Cache>();
			//货物类型设置缓存
			for(int i = 0;i<entityList.size();i++){
				cache = new Cache();
				cache.setKey(entityList.get(i).getId()+"");
				cache.setTimeOut(date.getTime());
				cache.setValue(entityList.get(i));
				beanCacheLst.add(cache);
			}
			CacheManager.putCacheList("truckBean_CACHE", beanCacheLst);
		}
		retMap.put("list",entityList);

		
		return retMap;
	}

	@Override
	public  Map getAdvancedForm(Map params){
		Map retMap = new HashMap();
		List itemList = new ArrayList();
		Map itemChild = new HashMap();
		//车牌
		itemChild.put("type","input");
		itemChild.put("placeholder","请输入车牌");
		itemChild.put("label","车牌");
		itemChild.put("key","truckNumber");
		itemChild.put("rules",new ArrayList());
		itemList.add(itemChild);
		//驾驶员
		List<Cache> cacheList = CacheManager.getCacheListInfo(CacheConstant.Driver_BEAN_CACHE);
		List<DriverInfoEntity> entityList = new ArrayList<DriverInfoEntity>();
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((DriverInfoEntity)cacheList.get(i).getValue());
			}
		}else{
			entityList = driverDao.getAllDriverInfo();
			Cache cache = null;
			Date date = new Date();
			List <Cache> beanCacheLst = new ArrayList<Cache>();
//			//货物类型设置缓存
//			for(int i = 0;i<entityList.size();i++){
//				cache = new Cache();
//				cache.setKey(entityList.get(i).getId()+"");
//				cache.setTimeOut(date.getTime());
//				cache.setValue(entityList.get(i));
//				beanCacheLst.add(cache);
//			}
			CacheManager.putCacheList(CacheConstant.Driver_BEAN_CACHE, beanCacheLst);
		}
		itemChild = new HashMap();
		itemChild.put("type","select");
		itemChild.put("label","车牌");
		itemChild.put("key","driver");
		itemChild.put("option","driver");
		itemChild.put("rules",new ArrayList());
		itemList.add(itemChild);

		retMap.put("item",itemList);
		return retMap;
	}

}
