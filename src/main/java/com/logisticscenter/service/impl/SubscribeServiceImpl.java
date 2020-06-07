package com.logisticscenter.service.impl;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.CommonTransMethod;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.logisticscenter.mapper.MessageDao;
import com.logisticscenter.mapper.SubscribeDao;
import com.logisticscenter.model.MessageEntity;
import com.logisticscenter.model.SendTypeEntity;
import com.logisticscenter.model.SubscribeEntity;
import com.logisticscenter.model.WorkflowTypeEntity;
import com.logisticscenter.service.MessageService;
import com.logisticscenter.service.SubscribeService;
import com.splitPage.MessageSplitPage;
import com.splitPage.SubscribeSplitPage;
import com.splitPage.pageInterface.SplitPageInterface;
import com.util.FileldsUtil.FieldUtil;
import com.util.FileldsUtil.SearchConditionOption;
import com.util.Utils;
import com.util.optionUtil.SelectOptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubscribeServiceImpl implements SubscribeService {

	@Autowired
    SubscribeDao subscribeDao;

	@Autowired
	SelectOptionUtils selectOptionUtils;

	@Autowired
	CommonTransMethod commonTransMethod;

	@Override
	public Map getCondition(Map<String, Object> params) {
		Map retResult = new HashMap();
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		List<SearchConditionOption> sendTypeOptions = selectOptionUtils.getSendTypeOptions();

		List<SearchConditionOption> workflowTypeOptions = selectOptionUtils.getWorkflowTypeOptions();

		itemlist.add(FieldUtil.getFormItemForInput("nickname", "昵称", "", 2));

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
		// 先删除所有
		subscribeDao.deleteAllSubscribInfo();
		// 再插入所有
		List <SubscribeEntity> allSubscribeInfo = new ArrayList<SubscribeEntity>();
		List<SubscribeEntity> allSubscribe = commonTransMethod.getAllSubscribe();
		subscribeDao.insertSubscribeInfo(allSubscribe);
		// 再获取所有
		subscribeDao.getAllSubscribeInfo();
		List<SubscribeEntity> entityList = new ArrayList<SubscribeEntity>();
		int page = Utils.getIntValue(Utils.null2String(params.get("currentPage")) ,1) ;
		int pageSize = Utils.getIntValue(Utils.null2String(params.get("pageSize")) ,10) ;
		PageHelper.startPage(page,pageSize);
        SubscribeEntity searchEntity = new SubscribeEntity();

		String nickname = Utils.null2String(params.get("nickname"));
        searchEntity.setNickname(nickname);
		entityList = subscribeDao.getSubscribeInfo(searchEntity);
		PageInfo pageInfo = new PageInfo(entityList);
		SplitPageInterface splitPageInterface = new SubscribeSplitPage();
		splitPageInterface.init(pageInfo);
		retResult.put("columns",splitPageInterface.splitPageBean.getColumns());
		retResult.put("data",splitPageInterface.splitPageBean.getData());
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}



}
