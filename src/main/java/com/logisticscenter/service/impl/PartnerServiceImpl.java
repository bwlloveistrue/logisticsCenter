package com.logisticscenter.service.impl;

import com.cache.Cache;
import com.cache.CacheManager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.logisticscenter.mapper.MessageDao;
import com.logisticscenter.mapper.PartnerDao;
import com.logisticscenter.model.PartnerEntity;
import com.logisticscenter.model.PartnerEntity;
import com.logisticscenter.model.SendTypeEntity;
import com.logisticscenter.model.WorkflowTypeEntity;
import com.logisticscenter.service.MessageService;
import com.logisticscenter.service.PartnerService;
import com.splitPage.MessageSplitPage;
import com.splitPage.PartnerSplitPage;
import com.splitPage.pageInterface.SplitPageInterface;
import com.util.FileldsUtil.FieldUtil;
import com.util.FileldsUtil.SearchConditionOption;
import com.util.Utils;
import com.util.optionUtil.SelectOptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PartnerServiceImpl implements PartnerService {

	@Autowired
	PartnerDao partnerDao;

	@Autowired
	SelectOptionUtils selectOptionUtils;

	@Override
	public Map getCondition(Map<String, Object> params) {
		Map retResult = new HashMap();
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		List<SearchConditionOption> sendTypeOptions = selectOptionUtils.getSendTypeOptions();

		List<SearchConditionOption> workflowTypeOptions = selectOptionUtils.getWorkflowTypeOptions();

		itemlist.add(FieldUtil.getFormItemForInput("partner", "伙伴", "",2));

		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 3);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);
		retResult.put("data",grouplist);
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}

	public Map getTableInfoList(Map<String, Object> params){
		Map retResult = new HashMap();
		List<PartnerEntity> entityList = new ArrayList<PartnerEntity>();
		int page = Utils.getIntValue(Utils.null2String(params.get("currentPage")) ,1) ;
		int pageSize = Utils.getIntValue(Utils.null2String(params.get("pageSize")) ,10) ;
		PageHelper.startPage(page,pageSize);
		PartnerEntity searchEntity = new PartnerEntity();

		String partner = Utils.null2String(params.get("partner"));
		searchEntity.setPartner(partner);
		entityList = partnerDao.getPartnerInfo(searchEntity);
		PageInfo pageInfo = new PageInfo(entityList);
		SplitPageInterface splitPageInterface = new PartnerSplitPage();
		splitPageInterface.init(pageInfo);
		retResult.put("columns",splitPageInterface.splitPageBean.getColumns());
		retResult.put("data",splitPageInterface.splitPageBean.getData());
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}

	@Override
	public Map getPartnerInfoFields(Map<String, Object> params) {
		Map retResult = new HashMap();
		int id = Utils.getIntValue(Utils.null2String(params.get("id")),0) ;
		PartnerEntity partnerValueEntity = new PartnerEntity();
		if(id > 0){
			PartnerEntity searchEntity = new PartnerEntity();
			searchEntity.setId(id);
			List<PartnerEntity> searchList  = partnerDao.getPartnerInfo(searchEntity);
			if(searchList.size()>0){
				partnerValueEntity = searchList.get(0);
			}
		}
		String partner = Utils.null2String(partnerValueEntity.getPartner());
		String address = Utils.null2String(partnerValueEntity.getAddress());
		String mobile = Utils.null2String(partnerValueEntity.getMobile());


		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();

		itemlist.add(FieldUtil.getFormItemForInput("partner", "伙伴", partner,3));
		itemlist.add(FieldUtil.getFormItemForInput("address", "地址", address,3));
		itemlist.add(FieldUtil.getFormItemForInput("mobile", "联系方式", mobile, 3));
		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 1);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);
		retResult.put("data",grouplist);
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}


	@Override
	public Map getAllPartner() {
		List<PartnerEntity> entityList = new ArrayList<PartnerEntity>();
		List<Cache> cacheList = CacheManager.getCacheListInfo("partnerEntity_CACHE");
		Map retResult = new HashMap();
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((PartnerEntity)cacheList.get(i).getValue());
			}
		}else{
			entityList = partnerDao.getAllPartnerInfo();
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
			CacheManager.putCacheList("partnerEntity_CACHE", beanCacheLst);
		}

		retResult.put("partnerInfo",entityList);
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}

	@Override
	public Map insertPartner(Map<String, Object> params) {
		Map retResult = new HashMap();
		PartnerEntity partnerE = new PartnerEntity();
		partnerE.setPartner(Utils.null2String(params.get("partner")));
		partnerE.setAddress(Utils.null2String(params.get("address")));
		partnerE.setMobile(Utils.null2String(params.get("mobile")));
		int maxId = partnerDao.insertPartnerInfo(partnerE);
		int c     = partnerE.getId();
		CacheManager.clearOnly("partnerEntity_CACHE");
		retResult.put("id",maxId);
		retResult.put("c",c);
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}


	@Override
	public Map updatePartner(Map<String, Object> params) {
		int count=0;
		Map retResult = new HashMap();
		PartnerEntity partnerE = new PartnerEntity();
		partnerE.setPartner(Utils.null2String(params.get("partner")));
		partnerE.setAddress(Utils.null2String(params.get("address")));
		partnerE.setMobile(Utils.null2String(params.get("mobile")));
		count = partnerDao.updatePartnerInfo(partnerE);
		CacheManager.clearOnly("partnerEntity_CACHE");
		retResult.put("count",count);
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}

	@Override
	public Map deletePartner(Map<String, Object> params) {
		Map retResult = new HashMap();
		String delids = Utils.null2String(params.get("delIds"));
		if(!delids.equals("")){
			Arrays.asList(delids.split(",")).stream().filter(item->!item.equals("")).forEach(item->{
				partnerDao.deletePartnerInfo(item);
			});
		}
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}



}
