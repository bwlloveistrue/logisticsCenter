package com.logisticscenter.service.impl;

import com.cache.Cache;
import com.cache.CacheManager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.logisticscenter.mapper.MessageDao;
import com.logisticscenter.model.MessageEntity;
import com.logisticscenter.model.SendTypeEntity;
import com.logisticscenter.model.WorkflowTypeEntity;
import com.logisticscenter.service.MessageService;
import com.splitPage.MessageSplitPage;
import com.splitPage.pageInterface.SplitPageInterface;
import com.util.FileldsUtil.FieldUtil;
import com.util.FileldsUtil.SearchConditionOption;
import com.util.Utils;
import com.util.optionUtil.SelectOptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageDao messageDao;

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

		itemlist.add(FieldUtil.getFormItemForSelect("sendType", "发送对象", "",2, 2,sendTypeOptions));
		itemlist.add(FieldUtil.getFormItemForSelect("workflowType", "发送类型", "",2, 2,workflowTypeOptions));
		itemlist.add(FieldUtil.getFormItemForInput("mouldId", "模板消息ID", "", 2));

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
		List<MessageEntity> entityList = new ArrayList<MessageEntity>();
		int page = Utils.getIntValue(Utils.null2String(params.get("currentPage")) ,1) ;
		int pageSize = Utils.getIntValue(Utils.null2String(params.get("pageSize")) ,10) ;
		PageHelper.startPage(page,pageSize);
		MessageEntity searchEntity = new MessageEntity();

		String sendType = Utils.null2String(params.get("sendType"));
		String workflowType = Utils.null2String(params.get("workflowType"));
		String mouldId = Utils.null2String(params.get("mouldId"));
		searchEntity.setSendType(sendType);
		searchEntity.setWorkflowType(workflowType);
		searchEntity.setMouldId(mouldId);
		entityList = messageDao.getMessageInfo(searchEntity);
		PageInfo pageInfo = new PageInfo(entityList);
		SplitPageInterface splitPageInterface = new MessageSplitPage();
		splitPageInterface.init(pageInfo);
		retResult.put("columns",splitPageInterface.splitPageBean.getColumns());
		retResult.put("data",splitPageInterface.splitPageBean.getData());
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}

	@Override
	public Map getMessageInfoFields(Map<String, Object> params) {
		Map retResult = new HashMap();
		int id = Utils.getIntValue(Utils.null2String(params.get("id")),0) ;
		MessageEntity messageValueEntity = new MessageEntity();
		if(id > 0){
			MessageEntity searchEntity = new MessageEntity();
			searchEntity.setId(id);
			List<MessageEntity> searchList  = messageDao.getMessageInfo(searchEntity);
			if(searchList.size()>0){
				messageValueEntity = searchList.get(0);
			}
		}
		String sendType = Utils.null2String(messageValueEntity.getSendType());
		String workflowType = Utils.null2String(messageValueEntity.getWorkflowType());
		String mouldId = Utils.null2String(messageValueEntity.getMouldId());


		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		List<SearchConditionOption> sendTypeOptions = selectOptionUtils.getSendTypeOptions();

		List<SearchConditionOption> workflowTypeOptions = selectOptionUtils.getWorkflowTypeOptions();

		itemlist.add(FieldUtil.getFormItemForSelect("sendType", "发送对象", sendType,3, 2,sendTypeOptions));
		itemlist.add(FieldUtil.getFormItemForSelect("workflowType", "发送类型", workflowType,3, 2,workflowTypeOptions));
		itemlist.add(FieldUtil.getFormItemForInput("mouldId", "模板消息ID", mouldId, 3));
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
	public Map getAllMessage() {
		List<MessageEntity> entityList = new ArrayList<MessageEntity>();
		List<Cache> cacheList = CacheManager.getCacheListInfo("messageEntity_CACHE");
		Map retResult = new HashMap();
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((MessageEntity)cacheList.get(i).getValue());
			}
		}else{
			entityList = messageDao.getAllMessageInfo();
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
			CacheManager.putCacheList("messageEntity_CACHE", beanCacheLst);
		}

		retResult.put("messageInfo",entityList);
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}

	@Override
	public Map insertMessage(Map<String, Object> params) {
		Map retResult = new HashMap();
		MessageEntity messageE = new MessageEntity();
		messageE.setSendType(Utils.null2String(params.get("sendType")));
		messageE.setWorkflowType(Utils.null2String(params.get("workflowType")));
		messageE.setMouldId(Utils.null2String(params.get("mouldId")));
		int maxId = messageDao.insertMessageInfo(messageE);
		int c     = messageE.getId();
		CacheManager.clearOnly("messageEntity_CACHE");
		retResult.put("id",maxId);
		retResult.put("c",c);
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}


	@Override
	public Map updateMessage(Map<String, Object> params) {
		int count=0;
		Map retResult = new HashMap();
		MessageEntity messageE = new MessageEntity();
		messageE.setSendType(Utils.null2String(params.get("sendType")));
		messageE.setWorkflowType(Utils.null2String(params.get("workflowType")));
		messageE.setMouldId(Utils.null2String(params.get("mouldId")));
		count = messageDao.updateMessageInfo(messageE);
		CacheManager.clearOnly("messageEntity_CACHE");
		retResult.put("count",count);
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}

	@Override
	public Map deleteMessage(Map<String, Object> params) {
		Map retResult = new HashMap();
		String delids = Utils.null2String(params.get("delIds"));
		if(!delids.equals("")){
			Arrays.asList(delids.split(",")).stream().filter(item->!item.equals("")).forEach(item->{
				messageDao.deleteMessageInfo(item);
			});
		}
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}

	@Override
	public Map getAllSendType() {
		List<SendTypeEntity> entityList = new ArrayList<SendTypeEntity>();
		List<Cache> cacheList = CacheManager.getCacheListInfo("sendTypeEntity_CACHE");
		Map retResult = new HashMap();
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((SendTypeEntity)cacheList.get(i).getValue());
			}
		}else{
			entityList = messageDao.getAllSendType();
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
			CacheManager.putCacheList("sendTypeEntity_CACHE", beanCacheLst);
		}

		retResult.put("sendTypeInfo",entityList);
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}

	@Override
	public Map getAllWorkflowType() {
		List<WorkflowTypeEntity> entityList = new ArrayList<WorkflowTypeEntity>();
		List<Cache> cacheList = CacheManager.getCacheListInfo("workflowTypeEntity_CACHE");
		Map retResult = new HashMap();
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((WorkflowTypeEntity)cacheList.get(i).getValue());
			}
		}else{
			entityList = messageDao.getAllWorkflowType();
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
			CacheManager.putCacheList("workflowTypeEntity_CACHE", beanCacheLst);
		}

		retResult.put("workflowTypeInfo",entityList);
		retResult.put("status",true);
		retResult.put("ret",true);
		return retResult;
	}

}
