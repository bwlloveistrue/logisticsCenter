package com.logisticscenter.service.impl;

import java.util.*;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.ConvertService;
import com.javabean.FeeTypeBean;
import com.logisticscenter.mapper.FeeTypeDao;
import com.logisticscenter.model.FeeTypeEntity;
import com.logisticscenter.service.FeeTypeService;
import com.util.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class FeeTypeServiceImpl implements FeeTypeService {

	@Autowired
	FeeTypeDao feeTypeDao;

	/* (non-Javadoc)
	 * @see com.service.FeeTypeService#getFeeType(com.javabean.FeeTypeBean)
	 */
	@Override
	public List<FeeTypeBean> getFeeType(FeeTypeBean selectInfo) {
		List<FeeTypeEntity> entityList = new ArrayList<FeeTypeEntity>();
		FeeTypeEntity FeeTypeE = (FeeTypeEntity)ConvertService.convertBeanToEntity(selectInfo, new FeeTypeEntity());
		List<FeeTypeBean> beanList = new ArrayList<FeeTypeBean>();
		int pageSize =Integer.parseInt(FeeTypeE.getPageSize());
		int currentPage =Integer.parseInt(FeeTypeE.getCurrentPage());
		currentPage = (currentPage -1)*pageSize;
		FeeTypeE.setCurrentPage(currentPage+"");
		entityList = feeTypeDao.getFeeType(FeeTypeE);
		for(int i=0;i<entityList.size(); i++){
			FeeTypeBean dirverBean = (FeeTypeBean) ConvertService.convertEntityToBean(entityList.get(i), new FeeTypeBean());
			beanList.add(dirverBean);
		}
		return beanList;
	}
	
	/* (non-Javadoc)
	 * @see com.service.FeeTypeService#getFeeType(com.javabean.FeeTypeBean)
	 */
	@Override
	public Map getFeeTypeCount(FeeTypeBean selectInfo) {
		Map retMap = new HashMap();
		String count = "";
		try{
			List<FeeTypeEntity> entityList = new ArrayList<FeeTypeEntity>();
			FeeTypeEntity FeeTypeE = (FeeTypeEntity)ConvertService.convertBeanToEntity(selectInfo, new FeeTypeEntity());
			List<FeeTypeBean> beanList = new ArrayList<FeeTypeBean>();
			count = feeTypeDao.getFeeTypeCount(FeeTypeE);
		}catch(Exception e){
			e.printStackTrace();
		}
		retMap.put("count",count);
		return retMap;
	}

	@Override
	public Map updateFeeType(FeeTypeBean updateInfo) {
		Map retMap = new HashMap();
		FeeTypeEntity FeeTypeE = (FeeTypeEntity) ConvertService.convertBeanToEntity(updateInfo, new FeeTypeEntity());
		FeeTypeE.setEditDate(ConvertService.getDate());
		FeeTypeE.setEditTime(ConvertService.getTime());
		feeTypeDao.updateFeeType(FeeTypeE);
		retMap.put("status",true);
		return retMap;
	}

	@Override
	public Map getAllFeeType(Map params) {
		Map retMap = new HashMap();
		List<FeeTypeEntity> entityList= new ArrayList<FeeTypeEntity>();
		List<Cache> cacheList = CacheManager.getCacheListInfo("feeTypeEntity_CACHE");
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((FeeTypeEntity)cacheList.get(i).getValue());
			}
		}else{
			Cache cache = null;
			Date date = new Date();
			List <Cache> beanCacheLst = new ArrayList<Cache>();
			entityList = feeTypeDao.getAllFeeType();
			for(int i=0;i<entityList.size(); i++){
				FeeTypeEntity feeTypeEntity = entityList.get(i);
				//设置缓存
				cache = new Cache();
				cache.setKey(feeTypeEntity.getId()+"");
				cache.setTimeOut(date.getTime());
				cache.setValue(feeTypeEntity);
				beanCacheLst.add(cache);
				if(feeTypeEntity.getIsUse() == 0 || feeTypeEntity.getShowType() == 3) continue;
			}
			CacheManager.putCacheList("feeTypeEntity_CACHE", beanCacheLst);
		}
		retMap.put("feeTypeInfo",entityList);
		return retMap;
	}

	/* (non-Javadoc)
	 * @see com.service.FeeTypeService#insertFeeType(com.javabean.FeeTypeBean)
	 */
	@Override
	public Map insertFeeType(Map map) {
		Map retMap = new HashMap();
		FeeTypeBean insertInfo = new FeeTypeBean();
		//插入费用类型
		FeeTypeEntity FeeTypeE = (FeeTypeEntity) ConvertService.convertBeanToEntity(insertInfo, new FeeTypeEntity());
		FeeTypeE.setCreateDate(ConvertService.getDate());
		FeeTypeE.setCreateTime(ConvertService.getTime());
		int statusFlg = feeTypeDao.insertFeeType(FeeTypeE);
		retMap.put("statusFlg",statusFlg);
		retMap.put("status",true);
		return retMap;
	}

	@Override
	public Map getFeeType(Map params) {
		Map retMap = new HashMap();
		List<FeeTypeEntity> entityList= new ArrayList<FeeTypeEntity>();
		List<Cache> cacheList = CacheManager.getCacheListInfo("feeTypeEntity_CACHE");
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((FeeTypeEntity)cacheList.get(i).getValue());
			}
		}else{
			Cache cache = null;
			Date date = new Date();
			List <Cache> beanCacheLst = new ArrayList<Cache>();
			entityList = feeTypeDao.getAllFeeType();
			for(int i=0;i<entityList.size(); i++){
				FeeTypeEntity feeTypeEntity = entityList.get(i);
				//设置缓存
				cache = new Cache();
				cache.setKey(feeTypeEntity.getId()+"");
				cache.setTimeOut(date.getTime());
				cache.setValue(feeTypeEntity);
				beanCacheLst.add(cache);
				if(feeTypeEntity.getIsUse() == 0 || feeTypeEntity.getShowType() == 3) continue;
			}
			CacheManager.putCacheList("feeTypeEntity_CACHE", beanCacheLst);
		}
		retMap.put("feeTypeInfo",entityList);
		return retMap;
	}

	@Override
	public Map deleteFeeType(Map params) {
		return null;
	}

}
