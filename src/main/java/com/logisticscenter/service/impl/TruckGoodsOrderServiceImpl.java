package com.logisticscenter.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javabean.TruckGoodsOrderDetailBean;
import com.javabean.TruckGoodsOrderTakerBean;
import com.logisticscenter.mapper.TruckGoodsOrderDao;
import com.logisticscenter.model.TruckGoodsOrderDetailEntity;
import com.logisticscenter.model.TruckGoodsOrderTakerEntity;
import com.logisticscenter.service.TruckGoodsOrderService;
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
		itemlist.add(FieldUtil.getFormItemForSelect("orderStatus", "状态", "", 2, 2, orderStatusOptions));
		List<SearchConditionOption> clientOptions = selectOptionUtils.getClientOptions();
		itemlist.add(FieldUtil.getFormItemForSelect("client", "客户", "", 2, 2, clientOptions));
		List<SearchConditionOption> driverOptions = selectOptionUtils.getDriverOptions();
		itemlist.add(FieldUtil.getFormItemForSelect("driver", "司机", "", 2, 2, driverOptions));
		List<SearchConditionOption> goodsTypeOptions = selectOptionUtils.getGoodsTypeOptions();
		itemlist.add(FieldUtil.getFormItemForSelect("goodsType", "货物类型", "", 2, 2, goodsTypeOptions));
		itemlist.add(FieldUtil.getFormItemForSelectDate("timeSag,beginDate", "出发时间", "", 2));
		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 6);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);
		retMap.put("data",grouplist);
		return retMap;
	}

	@Override
	public Map getTableInfoList(Map params) {
		Map retMap = new HashMap();
		int page = Utils.getIntValue(Utils.null2String(params.get("currentPage")) ,1) ;
		int pageSize = Utils.getIntValue(Utils.null2String(params.get("pageSize")) ,10) ;
		PageHelper.startPage(page,pageSize);

		TruckGoodsOrderTakerEntity truckGoodsOrderTakerEntity = new TruckGoodsOrderTakerEntity();
		List<TruckGoodsOrderTakerEntity> truckGoodsOrderTakerEntityList = truckGoodsOrderDao.getTruckGoodsOrderTaker(truckGoodsOrderTakerEntity);
		PageInfo pageInfo = new PageInfo(truckGoodsOrderTakerEntityList);
		SplitPageInterface splitPageInterface = new OrderTakerSplitPage();
		splitPageInterface.init(pageInfo);
		retMap.put("columns",splitPageInterface.splitPageBean.getColumns());
		retMap.put("data",splitPageInterface.splitPageBean.getData());
		return retMap;
	}

	@Override
	public Map getOrderTakersInfoFields(Map params) {
		Map retMap = new HashMap();
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		List<SearchConditionOption> clientOptions = selectOptionUtils.getClientOptions();
		itemlist.add(FieldUtil.getFormItemForSelect("client", "客户", "", 3, 2, clientOptions));
		itemlist.add(FieldUtil.getFormItemForDate("beginDate", "出发时间", "", 3,false));
		itemlist.add(FieldUtil.getFormItemForCheckbox("packageFlg", "是否包车", "", 3));
		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 3);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);
		retMap.put("data",grouplist);

		List<EditTableBean> editTableBeanList = new ArrayList<EditTableBean>();
		//货物类型
		List<SearchConditionOption> goodsTypeOptions = selectOptionUtils.getGoodsTypeOptions();
		EditTableBean editTableBean = new EditTableBean("货物类型","goodsType","20%",true);
		Map goodsTypeMap = FieldUtil.getFormItemForSelect("goodsType", "货物类型", "", 3, 2, goodsTypeOptions);
		editTableBean.setCell(goodsTypeMap);
		editTableBeanList.add(editTableBean);

		editTableBean = new EditTableBean("始发地","startPlace","20%",true);
		Map startPlaceMap = FieldUtil.getFormItemForInput("startPlace", "始发地", "", 3);
		editTableBean.setCell(startPlaceMap);
		editTableBeanList.add(editTableBean);

		editTableBean = new EditTableBean("目的地","endPlace","20%",true);
		Map endPlaceMap = FieldUtil.getFormItemForInput("endPlace", "目的地", "", 3);
		editTableBean.setCell(endPlaceMap);
		editTableBeanList.add(editTableBean);

		editTableBean = new EditTableBean("票据","invoiceFlg","20%",true);
		Map invoiceFlgMap = FieldUtil.getFormItemForCheckbox("invoiceFlg", "票据", "", 3);
		editTableBean.setCell(invoiceFlgMap);
		editTableBeanList.add(editTableBean);

		editTableBean = new EditTableBean("单价","price","15%",true);
		Map priceMap = FieldUtil.getFormItemForInputNumber("price", "单价", "", 0,999,3);
		editTableBean.setCell(priceMap);
		editTableBeanList.add(editTableBean);

		editTableBean = new EditTableBean("重量","realCarry","15%",true);
		Map realCarryMap = FieldUtil.getFormItemForInputNumber("realCarry", "重量", "", 0, 999, 3);
		editTableBean.setCell(realCarryMap);
		editTableBeanList.add(editTableBean);

		retMap.put("editcolumns",editTableBeanList);

		List editTableDatasList = new ArrayList();
		retMap.put("editdatas",editTableDatasList);

		return retMap;
	}

	@Override
	public Map addOrderTaker(Map params) {
		Map retMap = new HashMap();
		retMap.put("status",true);
		String orderTakerInfo = Utils.null2String(params.get("orderTakerInfo"));
		String mainInfo = Utils.null2String(params.get("mainInfo"));
		if(!mainInfo.equals("")){
			JSONObject orderTakerInfoJson = JSONObject.parseObject(mainInfo);
			TruckGoodsOrderTakerEntity truckGoodsOrderTakerEntity = new TruckGoodsOrderTakerEntity();
			truckGoodsOrderTakerEntity.setClient(Utils.getIntValue(Utils.null2String(orderTakerInfoJson.get("client"))));
			truckGoodsOrderTakerEntity.setBeginDate(Utils.null2String(orderTakerInfoJson.get("beginDate")));
			truckGoodsOrderTakerEntity.setPackageFlg(Utils.getIntValue(Utils.null2String(orderTakerInfoJson.get("packageFlg"))));
			int orderId = truckGoodsOrderDao.insertTruckGoodsOrderTaker(truckGoodsOrderTakerEntity);
			if(!orderTakerInfo.equals("") && orderId > 0){
				JSONArray orderTakerInfoArr = JSONArray.parseArray(orderTakerInfo);
				Iterator iterator = orderTakerInfoArr.iterator();
				while (iterator.hasNext()) {
					JSONObject jsonObject = (JSONObject) iterator.next();
					int goodsType = Utils.getIntValue(Utils.null2String(jsonObject.get("goodsType")));
					String startPlace = Utils.null2String(jsonObject.get("startPlace"));
					String endPlace = Utils.null2String(jsonObject.get("endPlace"));
					int invoiceFlg = Utils.getIntValue(Utils.null2String(jsonObject.get("invoiceFlg")),0);
					BigDecimal realCarry = Utils.toDecimal(Utils.null2String(jsonObject.get("realCarry")),0);
					TruckGoodsOrderDetailEntity truckGoodsOrderDetailEntity = new TruckGoodsOrderDetailEntity();
					truckGoodsOrderDetailEntity.setGoodsType(goodsType);
					truckGoodsOrderDetailEntity.setEndPlace(endPlace);
					truckGoodsOrderDetailEntity.setStartPlace(startPlace);
					truckGoodsOrderDetailEntity.setInvoiceFlg(invoiceFlg);
					truckGoodsOrderDetailEntity.setRealCarry(realCarry);
					truckGoodsOrderDetailEntity.setReportId(orderId);
					truckGoodsOrderDao.insertTruckGoodsOrderDetail(truckGoodsOrderDetailEntity);
				}
			}
		}else{
			retMap.put("ret",false);
			retMap.put("errorMsg","车辆信息为空！");
		}
		return retMap;
	}

	@Override
	public Map updateOrderTaker(Map params) {
		Map retMap = new HashMap();
		retMap.put("status",true);
		String orderTakerInfo = Utils.null2String(params.get("orderTakerInfo"));
		String mainInfo = Utils.null2String(params.get("mainInfo"));
		String deleteIds = Utils.null2String(params.get("deleteIds"));
		if(!mainInfo.equals("")){
			JSONObject orderTakerInfoJson = JSONObject.parseObject(mainInfo);
			int orderId =Utils.getIntValue(Utils.null2String(orderTakerInfoJson.get("id")));
			TruckGoodsOrderTakerEntity truckGoodsOrderTakerEntity = new TruckGoodsOrderTakerEntity();
			truckGoodsOrderTakerEntity.setClient(Utils.getIntValue(Utils.null2String(orderTakerInfoJson.get("client"))));
			truckGoodsOrderTakerEntity.setBeginDate(Utils.null2String(orderTakerInfoJson.get("beginDate")));
			truckGoodsOrderTakerEntity.setPackageFlg(Utils.getIntValue(Utils.null2String(orderTakerInfoJson.get("packageFlg"))));
			truckGoodsOrderTakerEntity.setId(orderId);
			truckGoodsOrderDao.updateTruckGoodsOrderTaker(truckGoodsOrderTakerEntity);
			if(!orderTakerInfo.equals("")){
				JSONArray orderTakerInfoArr = JSONArray.parseArray(orderTakerInfo);
				Iterator iterator = orderTakerInfoArr.iterator();
				while (iterator.hasNext()) {
					JSONObject jsonObject = (JSONObject) iterator.next();
					int goodsType = Utils.getIntValue(Utils.null2String(jsonObject.get("goodsType")));
					String startPlace = Utils.null2String(jsonObject.get("startPlace"));
					String endPlace = Utils.null2String(jsonObject.get("endPlace"));
					int invoiceFlg = Utils.getIntValue(Utils.null2String(jsonObject.get("invoiceFlg")),0);
					BigDecimal realCarry = Utils.toDecimal(Utils.null2String(jsonObject.get("realCarry")),0);
					int id =  Utils.getIntValue(Utils.null2String(jsonObject.get("id")),0);
					if(id>0){
						TruckGoodsOrderDetailEntity truckGoodsOrderDetailEntity = new TruckGoodsOrderDetailEntity();
						truckGoodsOrderDetailEntity.setGoodsType(goodsType);
						truckGoodsOrderDetailEntity.setEndPlace(endPlace);
						truckGoodsOrderDetailEntity.setStartPlace(startPlace);
						truckGoodsOrderDetailEntity.setInvoiceFlg(invoiceFlg);
						truckGoodsOrderDetailEntity.setRealCarry(realCarry);
						truckGoodsOrderDetailEntity.setReportId(orderId);
						truckGoodsOrderDetailEntity.setId(id);
						truckGoodsOrderDao.updateTruckGoodsOrderDetail(truckGoodsOrderDetailEntity);
					}else{
						TruckGoodsOrderDetailEntity truckGoodsOrderDetailEntity = new TruckGoodsOrderDetailEntity();
						truckGoodsOrderDetailEntity.setGoodsType(goodsType);
						truckGoodsOrderDetailEntity.setEndPlace(endPlace);
						truckGoodsOrderDetailEntity.setStartPlace(startPlace);
						truckGoodsOrderDetailEntity.setInvoiceFlg(invoiceFlg);
						truckGoodsOrderDetailEntity.setRealCarry(realCarry);
						truckGoodsOrderDetailEntity.setReportId(orderId);
						truckGoodsOrderDetailEntity.setId(id);
						truckGoodsOrderDao.insertTruckGoodsOrderDetail(truckGoodsOrderDetailEntity);
					}

				}
			}
			if(!deleteIds.equals("")){
				Arrays.asList(deleteIds.split(",")).stream().filter(item->!item.equals("")).forEach(item->{
					truckGoodsOrderDao.deleteTruckGoodsOrderDetail(item);
				});
			}
		}else{
			retMap.put("ret",false);
			retMap.put("errorMsg","车辆信息为空！");
		}
		return retMap;
	}

	@Override
	public Map deleteOrderTaker(Map params) {
		Map retMap = new HashMap();
		String delids = Utils.null2String(params.get("delIds"));
		if(!delids.equals("")){
			Arrays.asList(delids.split(",")).stream().filter(item->!item.equals("")).forEach(item->{
				truckGoodsOrderDao.deleteTruckGoodsOrderTaker(item);
			});
		}
		retMap.put("status",true);
		return retMap;
	}
}
