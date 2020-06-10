package com.logisticscenter.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.logisticscenter.mapper.OrderReceiptDao;
import com.logisticscenter.mapper.TruckGoodsOrderDao;
import com.logisticscenter.model.TruckGoodsOrderDetailEntity;
import com.logisticscenter.model.TruckGoodsOrderTakerEntity;
import com.logisticscenter.model.TruckGoodsReceiptDetailEntity;
import com.logisticscenter.model.TruckGoodsReportEntity;
import com.logisticscenter.service.TruckGoodsOrderService;
import com.logisticscenter.service.TruckOrderReceiptService;
import com.splitPage.EditTableBean;
import com.splitPage.OrderReceiptSplitPage;
import com.splitPage.OrderTakerSplitPage;
import com.splitPage.pageInterface.SplitPageInterface;
import com.util.FileldsUtil.FieldUtil;
import com.util.FileldsUtil.SearchConditionOption;
import com.util.Utils;
import com.util.optionUtil.SelectOptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TruckOrderReceiptServiceImpl implements TruckOrderReceiptService {

	@Autowired
	TruckGoodsOrderDao truckGoodsOrderDao;

	@Autowired
	OrderReceiptDao orderReceiptDao;

	@Autowired
	SelectOptionUtils selectOptionUtils;


	@Override
	public Map getCondition(Map params) {
		Map retMap = new HashMap();
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		List<SearchConditionOption> reportStatusOptions = selectOptionUtils.getReportStatusOptions();
		itemlist.add(FieldUtil.getFormItemForSelect("reportStatus", "回执状态", "0", 2, 2, reportStatusOptions));
		List<SearchConditionOption> clientOptions = selectOptionUtils.getClientOptions();
		itemlist.add(FieldUtil.getFormItemForSelect("client", "客户", "", 2, 2, clientOptions));
		List<SearchConditionOption> driverOptions = selectOptionUtils.getDriverOptions();
		itemlist.add(FieldUtil.getFormItemForSelect("driver", "司机", "", 2, 2, driverOptions));
		List<SearchConditionOption> goodsTypeOptions = selectOptionUtils.getGoodsTypeOptions(false);
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

		TruckGoodsReportEntity truckGoodsReportEntity = new TruckGoodsReportEntity();
		String client = Utils.null2String(params.get("client"));
		String driver = Utils.null2String(params.get("driver"));
		String goodsType = Utils.null2String(params.get("goodsType"));

		truckGoodsReportEntity.setClient(client);
		truckGoodsReportEntity.setDriver(driver);
		truckGoodsReportEntity.setGoodsType(goodsType);
		List<TruckGoodsReportEntity> truckGoodsOrderTakerEntityList = orderReceiptDao.getTruckGoodsReport(truckGoodsReportEntity);
		PageInfo pageInfo = new PageInfo(truckGoodsOrderTakerEntityList);
		SplitPageInterface splitPageInterface = new OrderReceiptSplitPage();
		splitPageInterface.init(pageInfo);
		retMap.put("columns",splitPageInterface.splitPageBean.getColumns());
		retMap.put("data",splitPageInterface.splitPageBean.getData());

		return retMap;
	}

	@Override
	public Map getOrderReceiptInfoFields(Map params) {
		Map retMap = new HashMap();
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		int id = Utils.getIntValue(Utils.null2String(params.get("id")),0) ;
		TruckGoodsReportEntity truckGoodsReportValueEntity = new TruckGoodsReportEntity();
		if(id > 0){
			TruckGoodsReportEntity truckGoodsOrderTakerEntity = new TruckGoodsReportEntity();
			truckGoodsOrderTakerEntity.setId(id);
			List<TruckGoodsReportEntity> truckGoodsOrderTakerEntityList = orderReceiptDao.getTruckGoodsReport(truckGoodsOrderTakerEntity);
			if(truckGoodsOrderTakerEntityList.size() > 0){
				truckGoodsReportValueEntity = truckGoodsOrderTakerEntityList.get(0);
			}
		}
		String truckNumber = Utils.null2String(truckGoodsReportValueEntity.getTruckNumber());
		String reportNumber = Utils.null2String(truckGoodsReportValueEntity.getReportNumber());
		String reportId = Utils.null2String(truckGoodsReportValueEntity.getReportId());
		String beginDate = Utils.null2String(truckGoodsReportValueEntity.getBeginDate());
		String expectedDate = Utils.null2String(truckGoodsReportValueEntity.getExpectedDate());
		String endDate = Utils.null2String(truckGoodsReportValueEntity.getEndDate());
		String driver = Utils.null2String(truckGoodsReportValueEntity.getDriver());
		String client = Utils.null2String(truckGoodsReportValueEntity.getClient());
		String goodsType = Utils.null2String(truckGoodsReportValueEntity.getGoodsType());
		String packageFlg = Utils.null2String(truckGoodsReportValueEntity.getPackageFlg());
		String packagePrice = Utils.null2String(truckGoodsReportValueEntity.getPackagePrice());
		String truckPart = Utils.null2String(truckGoodsReportValueEntity.getTruckPart());
		String partner = Utils.null2String(truckGoodsReportValueEntity.getPartner());
		String partnerPrice = Utils.null2String(truckGoodsReportValueEntity.getPartnerPrice());
		String partnerCarry = Utils.null2String(truckGoodsReportValueEntity.getPartnerCarry());
		String reportStatus = Utils.null2String(truckGoodsReportValueEntity.getReportStatus());
		String isLater = Utils.null2String(truckGoodsReportValueEntity.getIsLater());
		String laterReason = Utils.null2String(truckGoodsReportValueEntity.getLaterReason());
		String expensens = Utils.null2String(truckGoodsReportValueEntity.getExpensens());
		String profit = Utils.null2String(truckGoodsReportValueEntity.getProfit());
		String cost = Utils.null2String(truckGoodsReportValueEntity.getCost());
		String settlement = Utils.null2String(truckGoodsReportValueEntity.getSettlement());
		String payAccessory = Utils.null2String(truckGoodsReportValueEntity.getPayAccessory());
		String signAccessory = Utils.null2String(truckGoodsReportValueEntity.getSignAccessory());
		String checkOutAccessory = Utils.null2String(truckGoodsReportValueEntity.getCheckOutAccessory());
		String workPlace = Utils.null2String(truckGoodsReportValueEntity.getWorkPlace());
		String remark = Utils.null2String(truckGoodsReportValueEntity.getRemark());
		String carryNumber = Utils.null2String(truckGoodsReportValueEntity.getCarryNumber());
		String customerOrder = Utils.null2String(truckGoodsReportValueEntity.getCustomerOrder());
		String prepaidFlg = Utils.null2String(truckGoodsReportValueEntity.getPrepaidFlg());
		String prepaidExpress = Utils.null2String(truckGoodsReportValueEntity.getPrepaidExpress());
		String prepaidDesc = Utils.null2String(truckGoodsReportValueEntity.getPrepaidDesc());
		String processingFee = Utils.null2String(truckGoodsReportValueEntity.getProcessingFee());
		String loadIsSettle = Utils.null2String(truckGoodsReportValueEntity.getLoadIsSettle());
		String deleteFlg = Utils.null2String(truckGoodsReportValueEntity.getDeleteFlg());
		String lifitingCost = Utils.null2String(truckGoodsReportValueEntity.getLifitingCost());
		String oilFee = Utils.null2String(truckGoodsReportValueEntity.getOilFee());
		String tollFee = Utils.null2String(truckGoodsReportValueEntity.getTollFee());
		String repairFee = Utils.null2String(truckGoodsReportValueEntity.getRepairFee());
		String tyreFee = Utils.null2String(truckGoodsReportValueEntity.getTyreFee());
		String guideWayFee = Utils.null2String(truckGoodsReportValueEntity.getGuideWayFee());
		String otherFee = Utils.null2String(truckGoodsReportValueEntity.getOtherFee());
		String allowance = Utils.null2String(truckGoodsReportValueEntity.getAllowance());
		String forfeit = Utils.null2String(truckGoodsReportValueEntity.getForfeit());
		String parts = Utils.null2String(truckGoodsReportValueEntity.getParts());
		String breakRulesFee = Utils.null2String(truckGoodsReportValueEntity.getBreakRulesFee());
		String createDate = Utils.null2String(truckGoodsReportValueEntity.getCreateDate());
		String createTime = Utils.null2String(truckGoodsReportValueEntity.getCreateTime());
		String editDate = Utils.null2String(truckGoodsReportValueEntity.getEditDate());
		String editTime = Utils.null2String(truckGoodsReportValueEntity.getEditTime());

		List itemlist = new ArrayList();
		List<SearchConditionOption> clientOptions = selectOptionUtils.getClientOptions(false);
		List<SearchConditionOption> goodsTypeOptions = selectOptionUtils.getGoodsTypeOptions(false);
		List<SearchConditionOption> driverOptions = selectOptionUtils.getDriverOptions();
		List<SearchConditionOption> truckPartOptions = selectOptionUtils.getTruckPartOptions();
		List<SearchConditionOption> truckNumberOptions = selectOptionUtils.getTruckOptions(false);
		List<SearchConditionOption> truckPartnerOptions = selectOptionUtils.getPartnerOptions(false);
		int readOnly = 1;
		int mustInput = 3;
		itemlist.add(FieldUtil.getFormItemForSelect("client", "客户", client, readOnly, 2, clientOptions));
		itemlist.add(FieldUtil.getFormItemForSelect("goodsType", "货物类型", goodsType, readOnly, 2, goodsTypeOptions));
		itemlist.add(FieldUtil.getFormItemForDate("beginDate", "出发时间", beginDate, readOnly,false));
		itemlist.add(FieldUtil.getFormItemForCheckbox("packageFlg", "是否包车", packageFlg, readOnly));
		if(packageFlg.equals("1")){
			itemlist.add(FieldUtil.getFormItemForInputNumber("packagePrice", "包车价格", packagePrice, readOnly));
		}
		FieldUtil.getFormItemForRadioGroup("truckPart", "分配状态", truckPart, readOnly, 2, truckPartOptions);
		if(truckPart.equals("1")){//0：个人 1：同伴
			itemlist.add(FieldUtil.getFormItemForSelect("partner", "伙伴", truckPart, readOnly, 2, truckPartnerOptions));
			itemlist.add(FieldUtil.getFormItemForInputNumber("partnerCarry", "重量", partnerCarry, readOnly));
			itemlist.add(FieldUtil.getFormItemForInputNumber("partnerPrice", "单价", partnerPrice, readOnly));
		}else{
			itemlist.add(FieldUtil.getFormItemForSelect("truckNumber", "车牌号", truckNumber, 3, 2, truckNumberOptions));
			itemlist.add(FieldUtil.getFormItemForSelect("driver", "司机", driver, 3, 2, driverOptions));
		}
		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 3);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);


		// 获取分配后的货物详细信息
		TruckGoodsReceiptDetailEntity searchReceiptDetailInfo = new TruckGoodsReceiptDetailEntity();
		searchReceiptDetailInfo.setReportId(id);
		List<TruckGoodsReceiptDetailEntity> truckGoodsReceiptDetailValues = orderReceiptDao.getTruckGoodsReceiptDetail(searchReceiptDetailInfo);
		List newItemlist = new ArrayList();
		truckGoodsReceiptDetailValues.stream().forEach(_item->{
			int detailRow = 1;
			newItemlist.add(FieldUtil.getFormItemForSelect("goodsType_"+detailRow, "货物类型", _item.getGoodsType(), 3, 2, goodsTypeOptions,true));
			newItemlist.add(FieldUtil.getFormItemForInput("startPlace_"+detailRow, "始发地", _item.getStartPlace(), 3));
			newItemlist.add(FieldUtil.getFormItemForInput("endPlace_"+detailRow, "目的地", _item.getEndPlace(), 3));
			newItemlist.add(FieldUtil.getFormItemForCheckbox("invoiceFlg_"+detailRow, "开票", _item.getInvoiceFlg()+"",  3));
			newItemlist.add(FieldUtil.getFormItemForInputNumber("partnerCarry_"+detailRow, "重量", _item.getRealCarry()+"", 3));
			newItemlist.add(FieldUtil.getFormItemForInputNumber("partnerPrice_"+detailRow, "单价", _item.getPrice()+"", 3));
			detailRow++;
		});
		groupitem = new HashMap<String,Object>();
		groupitem.put("title", "货物详细");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 6);
		groupitem.put("items", newItemlist);
		grouplist.add(groupitem);

		groupitem = new HashMap<String,Object>();
		itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForDate("endDate", "到达时间", endDate, 3,false));
		newItemlist.add(FieldUtil.getFormItemForCheckbox("settlement", "结算运费", settlement+"",  3));
		newItemlist.add(FieldUtil.getFormItemForInput("settlement", "开票", settlement+"",  3));
		groupitem.put("title", "订单回执信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 6);
		groupitem.put("items", newItemlist);
		grouplist.add(groupitem);

		groupitem = new HashMap<String,Object>();
		itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForInputNumber("expensens", "运费", expensens+"", 3));
		itemlist.add(FieldUtil.getFormItemForInputNumber("cost", "费用", cost+"", 3));
		itemlist.add(FieldUtil.getFormItemForInputNumber("profit", "盈利", profit+"", 3));
		groupitem.put("title", "费用汇总");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 6);
		groupitem.put("items", newItemlist);
		grouplist.add(groupitem);

		groupitem = new HashMap<String,Object>();
		itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForInputNumber("oilFee", "油费", expensens+"", 3));
		itemlist.add(FieldUtil.getFormItemForInputNumber("tollFee", "过路费", cost+"", 3));
		itemlist.add(FieldUtil.getFormItemForInputNumber("repairFee", "修车费", profit+"", 3));
		groupitem.put("title", "费用详细");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 6);
		groupitem.put("items", newItemlist);
		grouplist.add(groupitem);


		retMap.put("data",grouplist);
		retMap.put("detailRow",truckGoodsReceiptDetailValues.size());

		return retMap;
	}

	@Override
	public Map addOrderReceipt(Map params) {
		Map retMap = new HashMap();
		retMap.put("status",true);

		return retMap;
	}

	@Override
	public Map updateOrderReceipt(Map params) {
		Map retMap = new HashMap();
		retMap.put("status",true);
		String orderTakerInfo = Utils.null2String(params.get("orderTakerInfo"));
		String mainInfo = Utils.null2String(params.get("mainInfo"));
		int orderId =Utils.getIntValue(Utils.null2String(params.get("id")));
		if(!mainInfo.equals("")){
			JSONObject orderTakerInfoJson = JSONObject.parseObject(mainInfo);

			TruckGoodsOrderTakerEntity truckGoodsOrderTakerEntity = new TruckGoodsOrderTakerEntity();
			truckGoodsOrderTakerEntity.setClient(Utils.null2String(orderTakerInfoJson.get("client")));
			truckGoodsOrderTakerEntity.setBeginDate(Utils.null2String(orderTakerInfoJson.get("beginDate")));
			truckGoodsOrderTakerEntity.setPackageFlg(Utils.getIntValue(Utils.null2String(orderTakerInfoJson.get("packageFlg"))));
			truckGoodsOrderTakerEntity.setPackagePrice(Utils.toDecimal(Utils.null2String(orderTakerInfoJson.get("packagePrice")),2));
			truckGoodsOrderTakerEntity.setId(orderId);
			truckGoodsOrderDao.updateTruckGoodsOrderTaker(truckGoodsOrderTakerEntity);
			TruckGoodsOrderDetailEntity forDel = new TruckGoodsOrderDetailEntity();
			forDel.setReportId(orderId);
			truckGoodsOrderDao.deleteTruckGoodsOrderDetail(forDel);
			if(!orderTakerInfo.equals("")){
				JSONArray orderTakerInfoArr = JSONArray.parseArray(orderTakerInfo);
				Iterator iterator = orderTakerInfoArr.iterator();
				while (iterator.hasNext()) {
					JSONObject jsonObject = (JSONObject) iterator.next();
					int goodsType = Utils.getIntValue(Utils.null2String(jsonObject.get("goodsType")));
					String startPlace = Utils.null2String(jsonObject.get("startPlace"));
					String endPlace = Utils.null2String(jsonObject.get("endPlace"));
					int invoiceFlg = Utils.getIntValue(Utils.null2String(jsonObject.get("invoiceFlg")),0);
					BigDecimal realCarry = Utils.toDecimal(Utils.null2String(jsonObject.get("realCarry")),2);
					BigDecimal price = Utils.toDecimal(Utils.null2String(jsonObject.get("price")),2);
					TruckGoodsOrderDetailEntity truckGoodsOrderDetailEntity = new TruckGoodsOrderDetailEntity();
					truckGoodsOrderDetailEntity.setGoodsType(goodsType+"");
					truckGoodsOrderDetailEntity.setEndPlace(endPlace);
					truckGoodsOrderDetailEntity.setStartPlace(startPlace);
					truckGoodsOrderDetailEntity.setInvoiceFlg(invoiceFlg);
					truckGoodsOrderDetailEntity.setRealCarry(realCarry);
					truckGoodsOrderDetailEntity.setPrice(price);
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
	public Map deleteOrderReceipt(Map params) {
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
