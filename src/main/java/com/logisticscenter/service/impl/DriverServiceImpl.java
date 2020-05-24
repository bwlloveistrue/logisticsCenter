package com.logisticscenter.service.impl;

import java.util.*;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.ConvertService;
import com.javabean.DriverInfoBean;
import com.logisticscenter.mapper.DriverInfoDao;
import com.logisticscenter.model.ClientEntity;
import com.logisticscenter.model.DriverInfoEntity;
import com.logisticscenter.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverInfoDao driverInfoDao;

    @Override
    public Map getDriverInfo(Map params) {
        Map retMap = new HashMap();
        retMap.put("driver", driverInfoDao.getDriverInfo((String) params.get("id")));
        return retMap;
    }

    @Override
    public Map getDriverInfo(DriverInfoEntity selectInfo, String selectStatus) {
        return null;
    }

    @Override
    public Map getDriverInfoCount(DriverInfoEntity selectInfo, String selectStatus) {
        return null;
    }

    @Override
    public Map getAllDriverInfo() {
        Map retResult = new HashMap();
        List<DriverInfoEntity> entityList = new ArrayList<DriverInfoEntity>();
        List<Cache> cacheList = CacheManager.getCacheListInfo("driverEntity_CACHE");
        if(cacheList!=null && cacheList.size()>0){
            for(int i =0;i<cacheList.size();i++){
                entityList.add((DriverInfoEntity)cacheList.get(i).getValue());
            }
        }else{
            entityList = driverInfoDao.getAllDriverInfo();
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
            CacheManager.putCacheList("driverEntity_CACHE", beanCacheLst);
        }
        retResult.put("driverInfo",entityList);
        return retResult;
    }

    @Override
    public Map deleteDriverInfo(String id) {
        Map retMap = new HashMap();
        int count = driverInfoDao.deleteDriverInfo(id);
        retMap.put("count", count);
        retMap.put("status", true);
        return retMap;

    }

    @Override
    public Map updateDriverInfo(DriverInfoEntity updateInfo) {
        return null;
    }

    @Override
    public Map insertDriverInfo(DriverInfoEntity insertInfo) {
        return null;
    }

    @Override
    public Map getDriverInfo(String id) {
        Map retMap = new HashMap();
        DriverInfoEntity driverInfoEntity = driverInfoDao.getDriverInfo(id);
        retMap.putAll(driverInfoEntity.toMap());
        return retMap;
    }

//	@Override
//	public List<DriverInfoBean> getDriverInfo(DriverInfoBean selectInfo,String selectStatus) {
//		List<DriverInfoBean> beanList = new ArrayList<DriverInfoBean>();
//		try{
//			DriverInfoEntity driverInfoE = (DriverInfoEntity)ConvertService.convertBeanToEntity(selectInfo,new DriverInfoEntity());
//			List<DriverInfoEntity> entityList = new ArrayList<DriverInfoEntity>();
//			int pageSize =Integer.parseInt(driverInfoE.getPageSize());
//			int currentPage =Integer.parseInt(driverInfoE.getCurrentPage());
//			currentPage = (currentPage -1)*pageSize;
//			driverInfoE.setCurrentPage(currentPage+"");
//			entityList = driverInfoDao.getDriverInfo(driverInfoE,selectStatus);
//			for(int i=0;i<entityList.size(); i++){
//				DriverInfoBean dirverBean = (DriverInfoBean) ConvertService.convertEntityToBean(entityList.get(i), new DriverInfoBean());
//				beanList.add(dirverBean);
//			}
//		}catch(Exception e){e.printStackTrace();}
//		return beanList;
//
//	}
//
//	@Override
//	public String getDriverInfoCount(DriverInfoBean selectInfo,String selectStatus) {
//		String count = "";
//		try{
//			DriverInfoEntity driverInfoE = (DriverInfoEntity)ConvertService.convertBeanToEntity(selectInfo,new DriverInfoEntity());
//
//			count = driverInfoDao.getDriverInfoCount(driverInfoE,selectStatus);
//		}catch(Exception e){e.printStackTrace();}
//		return count;
//
//	}
//
//	@Override
//	public int updateDriverInfo(DriverInfoBean updateInfo) {
//		int count = 0;
//		DriverInfoEntity DriverInfoE = (DriverInfoEntity)ConvertService.convertBeanToEntity(updateInfo,new DriverInfoEntity());
//		DriverInfoE.setEditDate(ConvertService.getDate());
//		DriverInfoE.setEditTime(ConvertService.getTime());
//		count =  driverInfoDao.updateDriverInfo(DriverInfoE);
//		return count;
//	}
//
//	@Override
//	public void updateAllDriverInfo(DriverInfoBean updateInfo) {
//		DriverInfoEntity DriverInfoE = new DriverInfoEntity();
//		driverInfoDao.updateAllDriverInfo(DriverInfoE);
//
//	}
//
//	@Override
//	public int insertDriverInfo(DriverInfoBean insertInfo) {
//
//		DriverInfoEntity DriverInfoE = (DriverInfoEntity) ConvertService.convertBeanToEntity(insertInfo, new DriverInfoEntity());
//		DriverInfoE.setCreateDate(ConvertService.getDate());
//		DriverInfoE.setCreateTime(ConvertService.getTime());
//		int statusFlg = driverInfoDao.insertDriverInfo(DriverInfoE);
//		// TODO Auto-generated method stub
//		return statusFlg;
//	}
//
//	public List<DriverInfoEntity> getAllDriverInfo(){
//		List<DriverInfoEntity> entityList = new ArrayList<DriverInfoEntity>();
//
//		entityList = driverInfoDao.getAllDriverInfo();
//		return entityList;
//	}

}
