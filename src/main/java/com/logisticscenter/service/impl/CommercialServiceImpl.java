package com.logisticscenter.service.impl;

import java.util.*;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.ConvertService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javabean.CommercialBean;
import com.logisticscenter.mapper.CommercialDao;
import com.logisticscenter.model.CommercialEntity;
import com.logisticscenter.model.CommercialEntity;
import com.logisticscenter.service.CommercialService;
import com.splitPage.CommercialSplitPage;
import com.splitPage.pageInterface.SplitPageInterface;
import com.util.FileldsUtil.FieldUtil;
import com.util.FileldsUtil.SearchConditionOption;
import com.util.Utils;
import com.util.optionUtil.SelectOptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CommercialServiceImpl implements CommercialService {

	@Autowired
	CommercialDao commercialDao;

	@Autowired
	SelectOptionUtils selectOptionUtils;

	@Override
	public Map getCondition(Map<String, Object> params) {
		Map retMap = new HashMap();
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		List<SearchConditionOption> truckNumberOptions = selectOptionUtils.getTruckOptions(false);
		itemlist.add(FieldUtil.getFormItemForSelect("truckNumber", "车牌号", "", 2, 3, truckNumberOptions));
		itemlist.add(FieldUtil.getFormItemForSwitch("isNew", "启用", "1", 2));

		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 6);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);
		retMap.put("data",grouplist);
		return retMap;
	}

	@Override
	public Map insertCommercial(Map<String, Object> params) {
		Map retResult = new HashMap();
		String truckNumber = Utils.null2String(params.get("truckNumber"));
		String startDate = Utils.null2String(params.get("startDate"));
		String endDate = Utils.null2String(params.get("endDate"));
		int isNew = Utils.getIntValue( Utils.null2String(params.get("isNew")));
		CommercialEntity commercialEntity = new CommercialEntity();
		commercialEntity.setTruckNumber(truckNumber);
		commercialEntity.setIsNew(isNew);
		commercialEntity.setStartDate(startDate);
		commercialEntity.setEndDate(endDate);
		commercialDao.insertCommercialInfo(commercialEntity);
		retResult.put("status",true);
		retResult.put("ret",true);
		CacheManager.clearOnly("commercialEntity_CACHE");
		this.getAllCommercial();
		return retResult;
	}

	@Override
	public Map getTableInfoList(Map<String, Object> params) {
		Map retMap = new HashMap();
		List<CommercialEntity> entityList = new ArrayList<CommercialEntity>();
		int page = Utils.getIntValue(Utils.null2String(params.get("currentPage")) ,1) ;
		int pageSize = Utils.getIntValue(Utils.null2String(params.get("pageSize")) ,10) ;
		PageHelper.startPage(page,pageSize);
		CommercialEntity searchEntity = new CommercialEntity();

		String truckNumber = Utils.null2String(params.get("truckNumber"));
		searchEntity.setTruckNumber(truckNumber);
		entityList = commercialDao.getCommercialInfo(searchEntity);
		PageInfo pageInfo = new PageInfo(entityList);
		SplitPageInterface splitPageInterface = new CommercialSplitPage();
		splitPageInterface.init(pageInfo);
		retMap.put("columns",splitPageInterface.splitPageBean.getColumns());
		retMap.put("data",splitPageInterface.splitPageBean.getData());
		return retMap;
	}

	@Override
	public Map getCommercialFields(Map<String, Object> params) {
		Map retMap = new HashMap();
		int id = Utils.getIntValue(Utils.null2String(params.get("id")),0) ;
		CommercialEntity commercialValueEntity = new CommercialEntity();
		if(id > 0){
			CommercialEntity searchEntity = new CommercialEntity();
			searchEntity.setId(id);
			List<CommercialEntity> commercialEntities = commercialDao.getCommercialInfo(searchEntity);
			if(commercialEntities.size() > 0){
				commercialValueEntity = commercialEntities.get(0);
			}
		}
		String truckNumber = Utils.null2String(commercialValueEntity.getTruckNumber());
		String isNew = Utils.null2String(commercialValueEntity.getIsNew());
		String startDate = Utils.null2String(params.get("startDate"));
		String endDate = Utils.null2String(params.get("endDate"));
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		List<SearchConditionOption> truckNumberOptions = selectOptionUtils.getTruckOptions(false);
		itemlist.add(FieldUtil.getFormItemForSelect("truckNumber", "车牌号", truckNumber, 3, 3, truckNumberOptions));
		itemlist.add(FieldUtil.getFormItemForSwitch("isNew", "启用", isNew, 3));
		itemlist.add(FieldUtil.getFormItemForDate("startDate", "有效开始日期", startDate, 3,false));
		itemlist.add(FieldUtil.getFormItemForDate("endDate", "有效结束日期", endDate, 3,false));
		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 3);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);
		retMap.put("data",grouplist);
		return retMap;
	}

	public Map getAllCommercial(){
		Map retMap = new HashMap();
		List<CommercialEntity> entityList = new ArrayList<CommercialEntity>();
		List<Cache> cacheList = CacheManager.getCacheListInfo("commercialEntity_CACHE");
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((CommercialEntity)cacheList.get(i).getValue());
			}
		}else{
			entityList = commercialDao.getAllCommercial();
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
			CacheManager.putCacheList("commercialEntity_CACHE", beanCacheLst);
		}
		if(entityList == null){
			entityList = new ArrayList<CommercialEntity>();
		}
		retMap.put("commercialInfo",entityList);
		return retMap;
	}

	@Override
	public Map deleteCommercial(Map<String, Object> params) {
		Map retMap = new HashMap();
		String delids = Utils.null2String(params.get("delIds"));
		if(!delids.equals("")){
			Arrays.asList(delids.split(",")).stream().filter(item->!item.equals("")).forEach(item->{
				commercialDao.deleteCommercialInfo(item);
			});
		}
		retMap.put("status",true);
		CacheManager.clearOnly("commercialEntity_CACHE");
		this.getAllCommercial();
		return retMap;
	}

	@Override
	public Map updateCommercial(Map<String, Object> params) {
		Map retResult = new HashMap();
		int id = Utils.getIntValue(Utils.null2String(params.get("id"))) ;
		String truckNumber = Utils.null2String(params.get("truckNumber"));
		String startDate = Utils.null2String(params.get("startDate"));
		String endDate = Utils.null2String(params.get("endDate"));
		int isNew = Utils.getIntValue( Utils.null2String(params.get("isNew")));
		CommercialEntity commercialEntity = new CommercialEntity();
		commercialEntity.setTruckNumber(truckNumber);
		commercialEntity.setIsNew(isNew);
		commercialEntity.setStartDate(startDate);
		commercialEntity.setEndDate(endDate);
		commercialEntity.setId(id);
		commercialDao.updateCommercialInfo(commercialEntity);
		CacheManager.clearOnly("commercialEntity_CACHE");
		this.getAllCommercial();
		retResult.put("status",true);
		retResult.put("ret",true);

		return retResult;
	}

}
