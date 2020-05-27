package com.logisticscenter.service.impl;

import java.util.*;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.ConvertService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.logisticscenter.mapper.ClientDao;
import com.logisticscenter.model.ClientEntity;
import com.logisticscenter.service.ClientService;
import com.splitPage.EditTableBean;
import com.splitPage.OrderTakerSplitPage;
import com.splitPage.pageInterface.SplitPageInterface;
import com.util.FileldsUtil.FieldUtil;
import com.util.FileldsUtil.SearchConditionOption;
import com.util.Utils;
import com.util.optionUtil.SelectOptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	ClientDao clientDao;

	@Autowired
	SelectOptionUtils selectOptionUtils;

	@Override
	public Map getCondition(Map<String, Object> params) {
		Map retMap = new HashMap();
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForInput("clientName", "客户名称", "", 2));
		itemlist.add(FieldUtil.getFormItemForInput("mobile", "手机", "", 2));
		itemlist.add(FieldUtil.getFormItemForInput("address", "地址", "", 2));
		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 6);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);
		retMap.put("data",grouplist);
		return retMap;
	}

	public Map getTableInfoList(Map<String, Object> params){
		Map retMap = new HashMap();
		List<ClientEntity> entityList = new ArrayList<ClientEntity>();
		int page = Utils.getIntValue(Utils.null2String(params.get("currentPage")) ,1) ;
		int pageSize = Utils.getIntValue(Utils.null2String(params.get("pageSize")) ,10) ;
		PageHelper.startPage(page,pageSize);
		ClientEntity searchEntity = new ClientEntity();

		String clientName = Utils.null2String(params.get("clientName"));
		String mobile = Utils.null2String(params.get("mobile"));
		String address = Utils.null2String(params.get("address"));
		searchEntity.setClientName(clientName);
		searchEntity.setMobile(mobile);
		searchEntity.setAddress(address);
		entityList = clientDao.getClient(searchEntity);
		PageInfo pageInfo = new PageInfo(entityList);
		SplitPageInterface splitPageInterface = new OrderTakerSplitPage();
		splitPageInterface.init(pageInfo);
		retMap.put("columns",splitPageInterface.splitPageBean.getColumns());
		retMap.put("data",splitPageInterface.splitPageBean.getData());
		return retMap;
	}

	@Override
	public Map getClientInfoFields(Map<String, Object> params) {
		Map retMap = new HashMap();
		int id = Utils.getIntValue(Utils.null2String(params.get("id")),0) ;
		ClientEntity clientValueEntity = new ClientEntity();
		if(id > 0){
			ClientEntity searchEntity = new ClientEntity();
			searchEntity.setId(id);
			List<ClientEntity> searchList  = clientDao.getClient(searchEntity);
			if(searchList.size()>0){
				clientValueEntity = searchList.get(0);
			}
		}
		String clientName = Utils.null2String(clientValueEntity.getClientName());
		String contant = Utils.null2String(clientValueEntity.getContant());
		String mobile = Utils.null2String(clientValueEntity.getMobile());
		String fax = Utils.null2String(clientValueEntity.getFax());
		String address = Utils.null2String(clientValueEntity.getAddress());
		String products = Utils.null2String(clientValueEntity.getProducts());

		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		List<SearchConditionOption> goodsTypeOptions = selectOptionUtils.getGoodsTypeOptions();
		itemlist.add(FieldUtil.getFormItemForInput("clientName", "客户名称", clientName, 3));
		itemlist.add(FieldUtil.getFormItemForInput("contant", "联系方式", contant, 2));
		itemlist.add(FieldUtil.getFormItemForInput("mobile", "手机", mobile, 3));
		itemlist.add(FieldUtil.getFormItemForInput("fax", "传真", fax, 3));
		itemlist.add(FieldUtil.getFormItemForInput("address", "地址", address, 3));
		itemlist.add(FieldUtil.getFormItemForSelect("products", "主要货物", products,2, 2,goodsTypeOptions));
		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 3);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);
		retMap.put("data",grouplist);

		return retMap;
	}


	@Override
	public Map getAllClient() {
		List<ClientEntity> entityList = new ArrayList<ClientEntity>();
		List<Cache> cacheList = CacheManager.getCacheListInfo("clientEntity_CACHE");
		Map retResult = new HashMap();
		if(cacheList!=null && cacheList.size()>0){
			for(int i =0;i<cacheList.size();i++){
				entityList.add((ClientEntity)cacheList.get(i).getValue());
			}
		}else{
			entityList = clientDao.getAllClient();
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
			CacheManager.putCacheList("clientEntity_CACHE", beanCacheLst);
		}

		retResult.put("clientInfo",entityList);
		return retResult;
	}

	@Override
	public Map insertClient(Map<String, Object> params) {
		Map retResult = new HashMap();
		ClientEntity clientE = new ClientEntity();
		clientE.setClientName((String)params.get("clientName"));
		clientE.setContant((String)params.get("contant"));
		clientE.setMobile((String)params.get("mobile"));
		clientE.setFax((String)params.get("fax"));
		clientE.setAddress((String)params.get("address"));
		clientE.setProducts((String)params.get("products"));
		int maxId = clientDao.insertClient(clientE);
		int c     = clientE.getId();
		CacheManager.clearOnly("clientEntity_CACHE");
		retResult.put("id",maxId);
		retResult.put("c",c);
		return retResult;
	}


	@Override
	public Map updateClient(Map<String, Object> params) {
		int count=0;
		Map retResult = new HashMap();
		ClientEntity clientE = new ClientEntity();
		clientE.setId(Integer.parseInt((String)params.get("id")));
		clientE.setClientName((String)params.get("clientName"));
		clientE.setContant((String)params.get("contant"));
		clientE.setMobile((String)params.get("mobile"));
		clientE.setFax((String)params.get("fax"));
		clientE.setAddress((String)params.get("address"));
		clientE.setProducts((String)params.get("products"));
		count = clientDao.updateClient(clientE);
		CacheManager.clearOnly("clientEntity_CACHE");
		retResult.put("count",count);
		return retResult;
	}

	@Override
	public Map deleteClient(Map<String, Object> params) {
		Map retMap = new HashMap();
		String delids = Utils.null2String(params.get("delIds"));
		if(!delids.equals("")){
			Arrays.asList(delids.split(",")).stream().filter(item->!item.equals("")).forEach(item->{
				clientDao.deleteClient(item);
			});
		}
		retMap.put("status",true);
		return retMap;

	}

}
