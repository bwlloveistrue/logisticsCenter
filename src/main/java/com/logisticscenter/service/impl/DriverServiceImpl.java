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
import com.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverInfoDao driverInfoDao;


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
    public Map getCondition(Map<String, Object> params) {
        return null;
    }

    @Override
    public Map insertDriverInfo(Map<String, Object> params) {
        return null;
    }

    @Override
    public Map getTableInfoList(Map<String, Object> params) {
        return null;
    }

    @Override
    public Map updateDriverInfo(Map<String, Object> params) {
        return null;
    }

    @Override
    public Map deleteDriverInfo(Map<String, Object> params) {
        Map retMap = new HashMap();
        String delids = Utils.null2String(params.get("delIds"));
        if(!delids.equals("")){
            Arrays.asList(delids.split(",")).stream().filter(item->!item.equals("")).forEach(item->{
                driverInfoDao.deleteDriverInfo(item);
            });
        }
        retMap.put("status",true);
        return retMap;
    }





    @Override
    public Map getDriverInfoFields(Map<String, Object> params) {
        Map retMap = new HashMap();
        DriverInfoEntity searchEntity = new DriverInfoEntity();
        List<DriverInfoEntity> driverInfoList = driverInfoDao.getDriverInfo(searchEntity);

        return retMap;
    }

}
