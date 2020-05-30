package com.logisticscenter.service.impl;

import java.util.*;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.ConvertService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javabean.GoodsTypeBean;
import com.logisticscenter.mapper.GoodsTypeDao;
import com.logisticscenter.model.DriverInfoEntity;
import com.logisticscenter.model.GoodsTypeEntity;
import com.logisticscenter.model.GoodsTypeEntity;
import com.logisticscenter.service.GoodsTypeService;
import com.splitPage.GoodsTypeSplitPage;
import com.splitPage.OrderTakerSplitPage;
import com.splitPage.pageInterface.SplitPageInterface;
import com.util.FileldsUtil.FieldUtil;
import com.util.FileldsUtil.SearchConditionOption;
import com.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

	@Autowired
	GoodsTypeDao goodsTypeDao;


	@Override
	public Map getCondition(Map<String, Object> params) {
		Map retMap = new HashMap();
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForInput("goodsName", "货物名称", "", 3));

		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 6);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);
		retMap.put("data",grouplist);
		return retMap;
	}

	@Override
	public Map insertGoodsType(Map<String, Object> params) {
		Map retResult = new HashMap();
		String goodsName = Utils.null2String(params.get("goodsName"));
		int isUse = Utils.getIntValue( Utils.null2String(params.get("isUse")));
		GoodsTypeEntity goodsTypeEntity = new GoodsTypeEntity();
		goodsTypeEntity.setGoodsName(goodsName);
		goodsTypeEntity.setIsUse(isUse);
		goodsTypeDao.insertGoodsTypeInfo(goodsTypeEntity);
		retResult.put("status",true);
		retResult.put("ret",true);
		CacheManager.clearOnly("goodsTypeEntity_CACHE");
		return retResult;
	}

	@Override
	public Map getTableInfoList(Map<String, Object> params) {
		Map retMap = new HashMap();
		List<GoodsTypeEntity> entityList = new ArrayList<GoodsTypeEntity>();
		int page = Utils.getIntValue(Utils.null2String(params.get("currentPage")) ,1) ;
		int pageSize = Utils.getIntValue(Utils.null2String(params.get("pageSize")) ,10) ;
		PageHelper.startPage(page,pageSize);
		GoodsTypeEntity searchEntity = new GoodsTypeEntity();

		String goodsName = Utils.null2String(params.get("goodsName"));
		searchEntity.setGoodsName(goodsName);
		entityList = goodsTypeDao.getGoodsTypeInfo(searchEntity);
		PageInfo pageInfo = new PageInfo(entityList);
		SplitPageInterface splitPageInterface = new GoodsTypeSplitPage();
		splitPageInterface.init(pageInfo);
		retMap.put("columns",splitPageInterface.splitPageBean.getColumns());
		retMap.put("data",splitPageInterface.splitPageBean.getData());
		return retMap;
	}

	@Override
	public Map getGoodsTypeFields(Map<String, Object> params) {
		Map retMap = new HashMap();
		int id = Utils.getIntValue(Utils.null2String(params.get("id")),0) ;
		GoodsTypeEntity goodsTypeValueEntity = new GoodsTypeEntity();
		if(id > 0){
			GoodsTypeEntity searchEntity = new GoodsTypeEntity();
			searchEntity.setId(id);
			List<GoodsTypeEntity> driverInfoList = goodsTypeDao.getGoodsTypeInfo(searchEntity);
			if(driverInfoList.size() > 0){
				goodsTypeValueEntity = driverInfoList.get(0);
			}
		}
		String name = Utils.null2String(goodsTypeValueEntity.getGoodsName());
		String isUse = Utils.null2String(goodsTypeValueEntity.getIsUse());
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForInput("goodsName", "货物名称",name , 3));
		itemlist.add(FieldUtil.getFormItemForSwitch("isUse", "启用", isUse, 3));
		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 3);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);
		retMap.put("data",grouplist);
		return retMap;
	}

	public Map getAllGoodsType(){
		Map retMap = new HashMap();
		List<GoodsTypeEntity> entityList = new ArrayList<GoodsTypeEntity>();
		List<Cache> cacheList = CacheManager.getCacheListInfo("goodsTypeEntity_CACHE");
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((GoodsTypeEntity)cacheList.get(i).getValue());
			}
		}else{
			entityList = goodsTypeDao.getAllGoodsType();
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
			CacheManager.putCacheList("goodsTypeEntity_CACHE", beanCacheLst);
		}
		retMap.put("goodsTypeInfo",entityList);
		return retMap;
	}

	@Override
	public Map deleteGoodsType(Map<String, Object> params) {
		Map retMap = new HashMap();
		String delids = Utils.null2String(params.get("delIds"));
		if(!delids.equals("")){
			Arrays.asList(delids.split(",")).stream().filter(item->!item.equals("")).forEach(item->{
				goodsTypeDao.deleteGoodsTypeInfo(item);
			});
		}
		retMap.put("status",true);
		CacheManager.clearOnly("goodsTypeEntity_CACHE");
		return retMap;
	}

	@Override
	public Map updateGoodsType(Map<String, Object> params) {
		Map retResult = new HashMap();
		int id = Utils.getIntValue(Utils.null2String(params.get("id"))) ;
		String goodsName = Utils.null2String(params.get("goodsName"));
		int isUse = Utils.getIntValue( Utils.null2String(params.get("isUse")));
		GoodsTypeEntity goodsTypeEntity = new GoodsTypeEntity();
		goodsTypeEntity.setId(id);
		goodsTypeEntity.setGoodsName(goodsName);
		goodsTypeEntity.setIsUse(isUse);
		goodsTypeDao.updateGoodsTypeInfo(goodsTypeEntity);
		CacheManager.clearOnly("goodsTypeEntity_CACHE");
		retResult.put("status",true);
		retResult.put("ret",true);

		return retResult;
	}

}
