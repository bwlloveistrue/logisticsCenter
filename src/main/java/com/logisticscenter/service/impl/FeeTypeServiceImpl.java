package com.logisticscenter.service.impl;

import java.util.*;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.ConvertService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javabean.FeeTypeBean;
import com.logisticscenter.mapper.FeeTypeDao;
import com.logisticscenter.model.DriverInfoEntity;
import com.logisticscenter.model.FeeTypeEntity;
import com.logisticscenter.model.FeeTypeEntity;
import com.logisticscenter.service.FeeTypeService;
import com.splitPage.FeeTypeSplitPage;
import com.splitPage.OrderTakerSplitPage;
import com.splitPage.pageInterface.SplitPageInterface;
import com.util.FileldsUtil.FieldUtil;
import com.util.FileldsUtil.SearchConditionOption;
import com.util.Utils;
import com.util.optionUtil.SelectOptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class FeeTypeServiceImpl implements FeeTypeService {

	@Autowired
	FeeTypeDao feeTypeDao;
	@Autowired
	SelectOptionUtils selectOptionUtils;


	@Override
	public Map getCondition(Map<String, Object> params) {
		Map retMap = new HashMap();
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForInput("feeName", "货物名称", "", 3));

		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 6);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);
		retMap.put("data",grouplist);
		return retMap;
	}

	@Override
	public Map insertFeeType(Map<String, Object> params) {
		Map retResult = new HashMap();
		String feeName = Utils.null2String(params.get("feeName"));
		int isUse = Utils.getIntValue( Utils.null2String(params.get("isUse")));
		String feeTypeColumn = Utils.null2String(params.get("feeTypeColumn"));
		int showType = Utils.getIntValue( Utils.null2String(params.get("showType")));
		FeeTypeEntity feeTypeEntity = new FeeTypeEntity();
		feeTypeEntity.setFeeName(feeName);
		feeTypeEntity.setIsUse(isUse);
		feeTypeEntity.setFeeTypeColumn(feeTypeColumn);
		feeTypeEntity.setShowType(showType);
		feeTypeDao.insertFeeTypeInfo(feeTypeEntity);
		retResult.put("status",true);
		retResult.put("ret",true);
		CacheManager.clearOnly("feeTypeEntity_CACHE");
		this.getAllFeeType();
		return retResult;
	}

	@Override
	public Map getTableInfoList(Map<String, Object> params) {
		Map retMap = new HashMap();
		List<FeeTypeEntity> entityList = new ArrayList<FeeTypeEntity>();
		int page = Utils.getIntValue(Utils.null2String(params.get("currentPage")) ,1) ;
		int pageSize = Utils.getIntValue(Utils.null2String(params.get("pageSize")) ,10) ;
		PageHelper.startPage(page,pageSize);
		FeeTypeEntity searchEntity = new FeeTypeEntity();

		String feeName = Utils.null2String(params.get("feeName"));
		searchEntity.setFeeName(feeName);
		entityList = feeTypeDao.getFeeTypeInfo(searchEntity);
		PageInfo pageInfo = new PageInfo(entityList);
		SplitPageInterface splitPageInterface = new FeeTypeSplitPage();
		splitPageInterface.init(pageInfo);
		retMap.put("columns",splitPageInterface.splitPageBean.getColumns());
		retMap.put("data",splitPageInterface.splitPageBean.getData());
		return retMap;
	}

	@Override
	public Map getFeeTypeFields(Map<String, Object> params) {
		Map retMap = new HashMap();
		int id = Utils.getIntValue(Utils.null2String(params.get("id")),0) ;
		FeeTypeEntity feeTypeValueEntity = new FeeTypeEntity();
		if(id > 0){
			FeeTypeEntity searchEntity = new FeeTypeEntity();
			searchEntity.setId(id);
			List<FeeTypeEntity> driverInfoList = feeTypeDao.getFeeTypeInfo(searchEntity);
			if(driverInfoList.size() > 0){
				feeTypeValueEntity = driverInfoList.get(0);
			}
		}
		String name = Utils.null2String(feeTypeValueEntity.getFeeName());
		String isUse = Utils.null2String(feeTypeValueEntity.getIsUse());
		String feeTypeColumn = Utils.null2String(feeTypeValueEntity.getFeeTypeColumn());
		String showType = Utils.null2String(feeTypeValueEntity.getShowType());
		List<SearchConditionOption> getFeeShowTypeOptions = selectOptionUtils.getFeeShowTypeOptions();
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForInput("feeName", "费用名称",name , 3));
		itemlist.add(FieldUtil.getFormItemForInput("feeTypeColumn", "费用字段",feeTypeColumn , 3));
		itemlist.add(FieldUtil.getFormItemForSelect("showType", "类型",showType , 3,2,getFeeShowTypeOptions));
		itemlist.add(FieldUtil.getFormItemForSwitch("isUse", "启用", isUse, 3));
		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 3);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);
		retMap.put("data",grouplist);
		return retMap;
	}

	public Map getAllFeeType(){
		Map retMap = new HashMap();
		List<FeeTypeEntity> entityList = new ArrayList<FeeTypeEntity>();
		List<Cache> cacheList = CacheManager.getCacheListInfo("feeTypeEntity_CACHE");
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((FeeTypeEntity)cacheList.get(i).getValue());
			}
		}else{
			entityList = feeTypeDao.getAllFeeType();
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
			CacheManager.putCacheList("feeTypeEntity_CACHE", beanCacheLst);
		}
		if(entityList == null){
			entityList = new ArrayList<FeeTypeEntity>();
		}
		retMap.put("feeTypeInfo",entityList);
		return retMap;
	}

	@Override
	public Map deleteFeeType(Map<String, Object> params) {
		Map retMap = new HashMap();
		String delids = Utils.null2String(params.get("delIds"));
		if(!delids.equals("")){
			Arrays.asList(delids.split(",")).stream().filter(item->!item.equals("")).forEach(item->{
				feeTypeDao.deleteFeeTypeInfo(item);
			});
		}
		retMap.put("status",true);
		CacheManager.clearOnly("feeTypeEntity_CACHE");
		this.getAllFeeType();
		return retMap;
	}

	@Override
	public Map updateFeeType(Map<String, Object> params) {
		Map retResult = new HashMap();
		int id = Utils.getIntValue(Utils.null2String(params.get("id"))) ;
		String feeName = Utils.null2String(params.get("feeName"));
		int isUse = Utils.getIntValue( Utils.null2String(params.get("isUse")));
		String feeTypeColumn = Utils.null2String(params.get("feeTypeColumn"));
		int showType = Utils.getIntValue( Utils.null2String(params.get("showType")));
		FeeTypeEntity feeTypeEntity = new FeeTypeEntity();
		feeTypeEntity.setFeeName(feeName);
		feeTypeEntity.setIsUse(isUse);
		feeTypeEntity.setFeeTypeColumn(feeTypeColumn);
		feeTypeEntity.setShowType(showType);
		feeTypeEntity.setId(id);
		feeTypeDao.updateFeeTypeInfo(feeTypeEntity);
		CacheManager.clearOnly("feeTypeEntity_CACHE");
		this.getAllFeeType();
		retResult.put("status",true);
		retResult.put("ret",true);

		return retResult;
	}

}
