package com.logisticscenter.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.logisticscenter.mapper.FeeTypeDao;
import com.logisticscenter.mapper.OrderReceiptDao;
import com.logisticscenter.mapper.TruckGoodsOrderDao;
import com.logisticscenter.model.*;
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
	FeeTypeDao feeTypeDao;

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
		truckGoodsReportEntity.setDeleteFlg(0);
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
			itemlist.add(FieldUtil.getFormItemForSelect("partner", "伙伴", partner, readOnly, 2, truckPartnerOptions));
			itemlist.add(FieldUtil.getFormItemForInputNumber("partnerCarry", "重量", partnerCarry, readOnly));
			itemlist.add(FieldUtil.getFormItemForInputNumber("partnerPrice", "单价", partnerPrice, readOnly));
		}else{
			itemlist.add(FieldUtil.getFormItemForSelect("truckNumber", "车牌号", truckNumber, readOnly, 2, truckNumberOptions));
			itemlist.add(FieldUtil.getFormItemForSelect("driver", "司机", driver, readOnly, 2, driverOptions));
		}
		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 3);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);


		// 获取分配后的货物详细信息
		TruckGoodsReceiptDetailEntity searchReceiptDetailInfo = new TruckGoodsReceiptDetailEntity();
		searchReceiptDetailInfo.setReceiptId(id);
		List<TruckGoodsReceiptDetailEntity> truckGoodsReceiptDetailValues = orderReceiptDao.getTruckGoodsReceiptDetail(searchReceiptDetailInfo);
		List newItemlist = new ArrayList();
		List receiptDetailIds = new ArrayList();
		truckGoodsReceiptDetailValues.stream().forEach(_item->{
			int receiptDetailid = _item.getId();
			receiptDetailIds.add(receiptDetailid);
			newItemlist.add(FieldUtil.getFormItemForSelect("goodsType_"+receiptDetailid, "货物类型", _item.getGoodsType(), readOnly, 2, goodsTypeOptions,true));
			newItemlist.add(FieldUtil.getFormItemForInput("startPlace_"+receiptDetailid, "始发地", _item.getStartPlace(), readOnly));
			newItemlist.add(FieldUtil.getFormItemForInput("endPlace_"+receiptDetailid, "目的地", _item.getEndPlace(), readOnly));
			newItemlist.add(FieldUtil.getFormItemForCheckbox("invoiceFlg_"+receiptDetailid, "开票", _item.getInvoiceFlg()+"",  readOnly));
			newItemlist.add(FieldUtil.getFormItemForInputNumber("realCarry_"+receiptDetailid, "重量", _item.getRealCarry()+"", 3));
			newItemlist.add(FieldUtil.getFormItemForInputNumber("price_"+receiptDetailid, "单价", _item.getPrice()+"", 3));
		});
		groupitem = new HashMap<String,Object>();
		groupitem.put("title", "货物详细");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 6);
		groupitem.put("items", newItemlist);
		grouplist.add(groupitem);

		groupitem = new HashMap<String,Object>();
		itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForDate("endDate", "到达时间", endDate, mustInput,false));
		itemlist.add(FieldUtil.getFormItemForInput("customerOrder", "客户订单编号", customerOrder+"",  mustInput));
		itemlist.add(FieldUtil.getFormItemForCheckbox("settlement", "结算运费", settlement+"",  mustInput));
		groupitem.put("title", "订单回执信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 4);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);

		groupitem = new HashMap<String,Object>();
		itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForInputNumber("expensens", "运费", expensens+"", 1));
		itemlist.add(FieldUtil.getFormItemForInputNumber("cost", "费用", cost+"", 1));
		itemlist.add(FieldUtil.getFormItemForInputNumber("profit", "盈利", profit+"", 1));
		groupitem.put("title", "费用汇总");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 3);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);

		groupitem = new HashMap<String,Object>();
		itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForInputNumber("lifitingCost", "吊费", lifitingCost+"", mustInput));
		itemlist.add(FieldUtil.getFormItemForInputNumber("processingFee", "加工费", processingFee+"", mustInput));
		itemlist.add(FieldUtil.getFormItemForCheckbox("loadIsSettle", "是否结清", loadIsSettle+"", mustInput));
		groupitem.put("title", "装车费用");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 3);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);

		groupitem = new HashMap<String,Object>();
		itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForInputNumber("oilFee", "油费", oilFee+"", mustInput));
		itemlist.add(FieldUtil.getFormItemForInputNumber("tollFee", "过路费", tollFee+"", mustInput));
		itemlist.add(FieldUtil.getFormItemForInputNumber("repairFee", "修车费", repairFee+"", mustInput));
		itemlist.add(FieldUtil.getFormItemForInputNumber("tyreFee", "轮胎费", tyreFee+"", mustInput));
		itemlist.add(FieldUtil.getFormItemForInputNumber("guideWayFee", "带路费", guideWayFee+"", mustInput));
		itemlist.add(FieldUtil.getFormItemForInputNumber("otherFee", "其他", otherFee+"", mustInput));
		itemlist.add(FieldUtil.getFormItemForInputNumber("allowance", "补助", allowance+"", mustInput));
		itemlist.add(FieldUtil.getFormItemForInputNumber("parts", "配件", parts+"", mustInput));
		itemlist.add(FieldUtil.getFormItemForInputNumber("breakRulesFee", "处理违章", breakRulesFee+"", mustInput));
		itemlist.add(FieldUtil.getFormItemForInputNumber("forfeit", "罚款", forfeit+"", mustInput));
		itemlist.add(FieldUtil.getFormItemForInput("remark", "备注", remark+"", 2));
		groupitem.put("title", "费用详细");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 6);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);

		groupitem = new HashMap<String,Object>();
		itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForUpload("payAccessory", "缴费单", "image/*", "picture-card",2,"",true));
		groupitem.put("title", "缴费单");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 1);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);

		groupitem = new HashMap<String,Object>();
		itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForUpload("signAccessory", "签收单", "image/*", "picture-card",2,"",true));
		groupitem.put("title", "签收单");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 1);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);

		groupitem = new HashMap<String,Object>();
		itemlist = new ArrayList();
		itemlist.add(FieldUtil.getFormItemForUpload("checkOutAccessory", "出库单", "image/*", "picture-card",2,"",true));
		groupitem.put("title", "出库单");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 1);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);

		retMap.put("data",grouplist);
		retMap.put("receiptDetailIds",receiptDetailIds);
		List<FeeTypeEntity> feeTypeEntities = feeTypeDao.getAllFeeType();
		List<String> feeTypeColumns = new ArrayList<>();
		feeTypeEntities.stream().forEach(_entity->{
			feeTypeColumns.add(_entity.getFeeTypeColumn());
		});
		retMap.put("feeTypes",feeTypeColumns);

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
		String receiptDetailIds = Utils.null2String(params.get("receiptDetailIds"));
		String mainInfo = Utils.null2String(params.get("mainInfo"));
		int receiptId =Utils.getIntValue(Utils.null2String(params.get("id")));
		if(!mainInfo.equals("")){
			JSONObject jsonObject = (JSONObject.parseObject(mainInfo));
			String truckNumber = Utils.null2String(jsonObject.get("truckNumber")) ;
			String beginDate = Utils.null2String(jsonObject.get("beginDate"));
			String expectedDate = Utils.null2String(jsonObject.get("expectedDate"));
			String endDate = Utils.null2String(jsonObject.get("endDate"));
			String driver = Utils.null2String(jsonObject.get("driver"));
			String client = Utils.null2String(jsonObject.get("client"));
			String goodsType = Utils.null2String(jsonObject.get("goodsType"));
			String packageFlg = Utils.null2String(jsonObject.get("packageFlg"));
			BigDecimal packagePrice = Utils.toDecimal(Utils.null2String(jsonObject.get("packagePrice")),2) ;
			int truckPart = Utils.getIntValue(Utils.null2String(jsonObject.get("truckPart")));
			String partner = Utils.null2String(jsonObject.get("partner"));
			String partnerPrice = Utils.null2String(jsonObject.get("partnerPrice"));
			String partnerCarry = Utils.null2String(jsonObject.get("partnerCarry"));
			String reportStatus = Utils.null2String(jsonObject.get("reportStatus"));
			int isLater = Utils.getIntValue(Utils.null2String(jsonObject.get("isLater")));
			String laterReason = Utils.null2String(jsonObject.get("laterReason"));
			BigDecimal expensens = Utils.toDecimal(Utils.null2String(jsonObject.get("expensens")),2) ;
			BigDecimal profit = Utils.toDecimal(Utils.null2String(jsonObject.get("profit")),2) ;
			BigDecimal cost = Utils.toDecimal(Utils.null2String(jsonObject.get("cost")),2) ;
			int settlement = Utils.getIntValue(Utils.null2String(jsonObject.get("settlement")));
			String payAccessory = Utils.null2String(jsonObject.get("payAccessory"));
			String signAccessory = Utils.null2String(jsonObject.get("signAccessory"));
			String checkOutAccessory = Utils.null2String(jsonObject.get("checkOutAccessory"));
			String workPlace = Utils.null2String(jsonObject.get("workPlace"));
			String remark = Utils.null2String(jsonObject.get("remark"));
			String carryNumber = Utils.null2String(jsonObject.get("carryNumber"));
			String customerOrder = Utils.null2String(jsonObject.get("customerOrder"));
			int prepaidFlg = Utils.getIntValue(Utils.null2String(jsonObject.get("prepaidFlg")));
			BigDecimal prepaidExpress = Utils.toDecimal(Utils.null2String(jsonObject.get("prepaidExpress")),2) ;
			String prepaidDesc = Utils.null2String(jsonObject.get("prepaidDesc"));
			BigDecimal processingFee = Utils.toDecimal(Utils.null2String(jsonObject.get("processingFee")),2) ;
			int loadIsSettle = Utils.getIntValue(Utils.null2String(jsonObject.get("loadIsSettle")));
			BigDecimal lifitingCost = Utils.toDecimal(Utils.null2String(jsonObject.get("lifitingCost")),2) ;
			BigDecimal oilFee = Utils.toDecimal(Utils.null2String(jsonObject.get("oilFee")),2) ;
			BigDecimal tollFee = Utils.toDecimal(Utils.null2String(jsonObject.get("tollFee")),2) ;;
			BigDecimal repairFee = Utils.toDecimal(Utils.null2String(jsonObject.get("repairFee")),2) ;
			BigDecimal tyreFee = Utils.toDecimal(Utils.null2String(jsonObject.get("tyreFee")),2) ;
			BigDecimal guideWayFee = Utils.toDecimal(Utils.null2String(jsonObject.get("guideWayFee")),2) ;
			BigDecimal otherFee = Utils.toDecimal(Utils.null2String(jsonObject.get("otherFee")),2) ;
			BigDecimal allowance = Utils.toDecimal(Utils.null2String(jsonObject.get("allowance")),2) ;
			BigDecimal forfeit = Utils.toDecimal(Utils.null2String(jsonObject.get("forfeit")),2) ;
			BigDecimal parts = Utils.toDecimal(Utils.null2String(jsonObject.get("parts")),2) ;
			BigDecimal breakRulesFee = Utils.toDecimal(Utils.null2String(jsonObject.get("breakRulesFee")),2) ;
			TruckGoodsReportEntity truckGoodsReportValueEntity = new TruckGoodsReportEntity();
			truckGoodsReportValueEntity.setTruckNumber(truckNumber);
			truckGoodsReportValueEntity.setBeginDate(beginDate);
			truckGoodsReportValueEntity.setExpectedDate(expectedDate);
			truckGoodsReportValueEntity.setEndDate(endDate);
			truckGoodsReportValueEntity.setDriver(driver);
			truckGoodsReportValueEntity.setClient(client);
			truckGoodsReportValueEntity.setGoodsType(goodsType);
			truckGoodsReportValueEntity.setPackageFlg(packageFlg);
			truckGoodsReportValueEntity.setPackagePrice(packagePrice);
			truckGoodsReportValueEntity.setTruckPart(truckPart);
			truckGoodsReportValueEntity.setPartner(partner);
			truckGoodsReportValueEntity.setPartnerPrice(partnerPrice);
			truckGoodsReportValueEntity.setPartnerCarry(partnerCarry);
			truckGoodsReportValueEntity.setReportStatus(0);
			truckGoodsReportValueEntity.setIsLater(isLater);
			truckGoodsReportValueEntity.setLaterReason(laterReason);
			truckGoodsReportValueEntity.setExpensens(expensens);
			truckGoodsReportValueEntity.setProfit(profit);
			truckGoodsReportValueEntity.setCost(cost);
			truckGoodsReportValueEntity.setSettlement(settlement);
			truckGoodsReportValueEntity.setPayAccessory(payAccessory);
			truckGoodsReportValueEntity.setSignAccessory(signAccessory);
			truckGoodsReportValueEntity.setCheckOutAccessory(checkOutAccessory);
			truckGoodsReportValueEntity.setWorkPlace(workPlace);
			truckGoodsReportValueEntity.setRemark(remark);
			truckGoodsReportValueEntity.setCarryNumber(carryNumber);
			truckGoodsReportValueEntity.setCustomerOrder(customerOrder);
			truckGoodsReportValueEntity.setPrepaidFlg(prepaidFlg);
			truckGoodsReportValueEntity.setPrepaidExpress(prepaidExpress);
			truckGoodsReportValueEntity.setPrepaidDesc(prepaidDesc);
			truckGoodsReportValueEntity.setProcessingFee(processingFee);
			truckGoodsReportValueEntity.setLoadIsSettle(loadIsSettle);
			truckGoodsReportValueEntity.setDeleteFlg(0);
			truckGoodsReportValueEntity.setLifitingCost(lifitingCost);
			truckGoodsReportValueEntity.setOilFee(oilFee);
			truckGoodsReportValueEntity.setTollFee(tollFee);
			truckGoodsReportValueEntity.setRepairFee(repairFee);
			truckGoodsReportValueEntity.setTyreFee(tyreFee);
			truckGoodsReportValueEntity.setGuideWayFee(guideWayFee);
			truckGoodsReportValueEntity.setOtherFee(otherFee);
			truckGoodsReportValueEntity.setAllowance(allowance);
			truckGoodsReportValueEntity.setForfeit(forfeit);
			truckGoodsReportValueEntity.setParts(parts);
			truckGoodsReportValueEntity.setBreakRulesFee(breakRulesFee);
			truckGoodsReportValueEntity.setId(receiptId);
			orderReceiptDao.updateTruckGoodsReport(truckGoodsReportValueEntity);

			// 更新明细
			Arrays.asList(receiptDetailIds.split(",")).stream().forEach(_detailId->{

				int id = Utils.getIntValue(_detailId);
				if(id>0){
					BigDecimal realCarry = Utils.toDecimal(Utils.null2String(jsonObject.get("realCarry_"+_detailId)),2);
					BigDecimal price = Utils.toDecimal(Utils.null2String(jsonObject.get("price_"+_detailId)),2);

					TruckGoodsReceiptDetailEntity truckGoodsReceiptDetailEntity = new TruckGoodsReceiptDetailEntity();
					truckGoodsReceiptDetailEntity.setId(id);
					truckGoodsReceiptDetailEntity.setRealCarry(realCarry);
					truckGoodsReceiptDetailEntity.setPrice(price);
					orderReceiptDao.updateTruckGoodsReceiptDetail(truckGoodsReceiptDetailEntity);
				}

			});
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
				orderReceiptDao.deleteTruckGoodsReport(item);
			});
		}
		retMap.put("status",true);
		return retMap;
	}
}
