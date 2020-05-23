package com.logisticscenter.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.ConvertService;
import com.javabean.TruckGoodsOrderDetailBean;
import com.javabean.TruckGoodsOrderTakerBean;
import com.logisticscenter.mapper.TruckGoodsOrderDao;
import com.logisticscenter.model.TruckGoodsOrderDetailEntity;
import com.logisticscenter.model.TruckGoodsOrderTakerEntity;
import com.logisticscenter.service.TruckGoodsOrderService;
import com.util.FileldsUtil.FieldUtil;
import com.util.FileldsUtil.SearchConditionOption;
import com.util.optionUtil.SelectOptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TruckGoodsOrderServiceImpl implements TruckGoodsOrderService {

	@Autowired
	TruckGoodsOrderDao truckGoodsOrderDao;

	@Autowired
	SelectOptionUtils selectOptionUtils;


	@Override
	public Map getCondition(Map params) {
		Map retMap = new HashMap();
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		List<SearchConditionOption> orderStatusOptions = selectOptionUtils.getOrderStatusOptions();
		itemlist.add(FieldUtil.getFormItemForSelect("orderStatus", "状态", "", 3, 2, orderStatusOptions));
		List<SearchConditionOption> clientOptions = selectOptionUtils.getClientOptions();
		itemlist.add(FieldUtil.getFormItemForSelect("client", "客户", "", 3, 2, orderStatusOptions));
		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 6);
		groupitem.put("items", itemlist);
		groupitem.put("key", "calendarSet");//日历设置
		grouplist.add(groupitem);
		retMap.put("datas",grouplist);
		return retMap;
	}

	@Override
	public Map getTableInfoList(Map params) {
		return null;
	}
}
