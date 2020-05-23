package com.logisticscenter.service.impl;

import java.util.*;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.ConvertService;
import com.javabean.DriverInfoBean;
import com.logisticscenter.mapper.DriverInfoDao;
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
		retMap.put("driver",driverInfoDao.getDriverInfo((String)params.get("id")));
		return retMap;
	}

	@Override
	public Map deleteDriverInfo(String id) {
		Map retMap = new HashMap();
		int count = driverInfoDao.deleteDriverInfo(id);
		retMap.put("count",count);
		retMap.put("status",true);
		return retMap;
		
	}

	@Override
	public Map getDriverInfo(String id) {
		Map retMap = new HashMap();
		DriverInfoEntity driverInfoEntity = driverInfoDao.getDriverInfo(id);
		retMap.putAll(driverInfoEntity.toMap());
		return retMap;
	}

	@Override
	public List<DriverInfoBean> getDriverInfo(DriverInfoBean selectInfo,String selectStatus) {
		List<DriverInfoBean> beanList = new ArrayList<DriverInfoBean>();
		try{
			DriverInfoEntity driverInfoE = (DriverInfoEntity)ConvertService.convertBeanToEntity(selectInfo,new DriverInfoEntity());
			List<DriverInfoEntity> entityList = new ArrayList<DriverInfoEntity>();
			int pageSize =Integer.parseInt(driverInfoE.getPageSize());
			int currentPage =Integer.parseInt(driverInfoE.getCurrentPage());
			currentPage = (currentPage -1)*pageSize;
			driverInfoE.setCurrentPage(currentPage+"");
			entityList = driverInfoDao.getDriverInfo(driverInfoE,selectStatus);
			for(int i=0;i<entityList.size(); i++){
				DriverInfoBean dirverBean = (DriverInfoBean) ConvertService.convertEntityToBean(entityList.get(i), new DriverInfoBean());
				beanList.add(dirverBean);
			}
		}catch(Exception e){e.printStackTrace();}
		return beanList;
		
	}
	
	@Override
	public String getDriverInfoCount(DriverInfoBean selectInfo,String selectStatus) {
		String count = "";
		try{
			DriverInfoEntity driverInfoE = (DriverInfoEntity)ConvertService.convertBeanToEntity(selectInfo,new DriverInfoEntity());
			
			count = driverInfoDao.getDriverInfoCount(driverInfoE,selectStatus);
		}catch(Exception e){e.printStackTrace();}
		return count;
		
	}

	@Override
	public int updateDriverInfo(DriverInfoBean updateInfo) {
		int count = 0;
		DriverInfoEntity DriverInfoE = (DriverInfoEntity)ConvertService.convertBeanToEntity(updateInfo,new DriverInfoEntity());
		DriverInfoE.setEditDate(ConvertService.getDate());
		DriverInfoE.setEditTime(ConvertService.getTime());
		count =  driverInfoDao.updateDriverInfo(DriverInfoE);
		return count;
	}
	
	@Override
	public void updateAllDriverInfo(DriverInfoBean updateInfo) {
		DriverInfoEntity DriverInfoE = new DriverInfoEntity();
		driverInfoDao.updateAllDriverInfo(DriverInfoE);
		
	}

	@Override
	public int insertDriverInfo(DriverInfoBean insertInfo) {
		
		DriverInfoEntity DriverInfoE = (DriverInfoEntity) ConvertService.convertBeanToEntity(insertInfo, new DriverInfoEntity());
		DriverInfoE.setCreateDate(ConvertService.getDate());
		DriverInfoE.setCreateTime(ConvertService.getTime());
		int statusFlg = driverInfoDao.insertDriverInfo(DriverInfoE);
		// TODO Auto-generated method stub
		return statusFlg;
	}
	
	public List<DriverInfoEntity> getAllDriverInfo(){
		List<DriverInfoEntity> entityList = new ArrayList<DriverInfoEntity>();

		entityList = driverInfoDao.getAllDriverInfo();
		return entityList;
	}

}
