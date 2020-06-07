package com.logisticscenter.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.CommonTransMethod;
import com.common.consatnt.SendTypeConstant;
import com.common.consatnt.WorkflowTypeConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.logisticscenter.controller.WeChatPushController;
import com.logisticscenter.mapper.OrderApportionDao;
import com.logisticscenter.mapper.OrderReceiptDao;
import com.logisticscenter.mapper.TruckGoodsOrderDao;
import com.logisticscenter.model.*;
import com.logisticscenter.service.TruckGoodsOrderService;
import com.logisticscenter.service.TruckOrderApportionService;
import com.splitPage.EditTableBean;
import com.splitPage.OrderTakerSplitPage;
import com.splitPage.pageInterface.SplitPageInterface;
import com.util.FileldsUtil.FieldUtil;
import com.util.FileldsUtil.SearchConditionOption;
import com.util.Utils;
import com.util.optionUtil.SelectOptionUtils;
import com.util.wxPublic.Template;
import com.util.wxPublic.TemplateParam;
import com.util.wxPublic.WxMsgPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TruckOrderApportionServiceImpl implements TruckOrderApportionService {

	@Autowired
	TruckGoodsOrderDao truckGoodsOrderDao;

	@Autowired
	OrderApportionDao orderApportionDao;

	@Autowired
	OrderReceiptDao orderReceiptDao;


	@Autowired
	SelectOptionUtils selectOptionUtils;

	@Autowired
	WeChatPushController weChatPushController;

	@Autowired
	CommonTransMethod commonTransMethod;

	@Autowired
	WxMsgPush wxMsgPush;


	/**
	 * @param params
	 * @return
	 */
	@Override
	public Map getCondition(Map params) {
		Map retMap = new HashMap();
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		List itemlist = new ArrayList();
		List<SearchConditionOption> orderStatusOptions = selectOptionUtils.getOrderStatusOptions();
		itemlist.add(FieldUtil.getFormItemForSelect("orderStatus", "状态", "0", 2, 2, orderStatusOptions));
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

	/**
	 * @param params
	 * @return
	 */
	@Override
	public Map getTableInfoList(Map params) {
		Map retMap = new HashMap();
		int page = Utils.getIntValue(Utils.null2String(params.get("currentPage")) ,1) ;
		int pageSize = Utils.getIntValue(Utils.null2String(params.get("pageSize")) ,10) ;
		PageHelper.startPage(page,pageSize);

		TruckGoodsOrderTakerEntity truckGoodsOrderTakerEntity = new TruckGoodsOrderTakerEntity();
		String client = Utils.null2String(params.get("client"));
		int orderStatus = Utils.getIntValue(Utils.null2String(params.get("orderStatus")),-1) ;
		truckGoodsOrderTakerEntity.setClient(client);
		truckGoodsOrderTakerEntity.setOrderStatus(orderStatus);
		List<TruckGoodsOrderTakerEntity> truckGoodsOrderTakerEntityList = truckGoodsOrderDao.getTruckGoodsOrderTaker(truckGoodsOrderTakerEntity);
		PageInfo pageInfo = new PageInfo(truckGoodsOrderTakerEntityList);
		SplitPageInterface splitPageInterface = new OrderTakerSplitPage();
		splitPageInterface.init(pageInfo);
		retMap.put("columns",splitPageInterface.splitPageBean.getColumns());

		retMap.put("data",splitPageInterface.splitPageBean.getData());
		return retMap;
	}

	/**
	 * @param params
	 * @return
	 */
	@Override
	public Map getOrderApportionFields(Map params) {
		Map retMap = new HashMap();
		List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
		Map<String,Object> groupitem = new HashMap<String,Object>();
		int id = Utils.getIntValue(Utils.null2String(params.get("id")),0) ;
		TruckGoodsOrderTakerEntity truckGoodsOrderTakerValueEntity = new TruckGoodsOrderTakerEntity();
		if(id > 0){
			TruckGoodsOrderTakerEntity truckGoodsOrderTakerEntity = new TruckGoodsOrderTakerEntity();
			truckGoodsOrderTakerEntity.setId(id);
			List<TruckGoodsOrderTakerEntity> truckGoodsOrderTakerEntityList = truckGoodsOrderDao.getTruckGoodsOrderTaker(truckGoodsOrderTakerEntity);
			if(truckGoodsOrderTakerEntityList.size() > 0){
				truckGoodsOrderTakerValueEntity = truckGoodsOrderTakerEntityList.get(0);

			}
		}
		String client = Utils.null2String(truckGoodsOrderTakerValueEntity.getClient() );
		String beginDate = Utils.null2String(truckGoodsOrderTakerValueEntity.getBeginDate());
		String packageFlg = Utils.null2String(truckGoodsOrderTakerValueEntity.getPackageFlg());
		String packagePrice = Utils.null2String(truckGoodsOrderTakerValueEntity.getPackagePrice());
		String truckGoods = truckGoodsOrderTakerValueEntity.getGoodsType();
		// 获取基本信息
		List itemlist = new ArrayList();
		List<SearchConditionOption> clientOptions = selectOptionUtils.getClientOptions(false);
		List<SearchConditionOption> goodsTypeOptions = selectOptionUtils.getGoodsTypeOptions(false);
		itemlist.add(FieldUtil.getFormItemForSelect("client", "客户", client, 1, 2, clientOptions));
		itemlist.add(FieldUtil.getFormItemForSelect("goodsType", "货物类型", truckGoods, 1, 2, goodsTypeOptions));
		itemlist.add(FieldUtil.getFormItemForDate("beginDate", "出发时间", beginDate, 1,false));
		itemlist.add(FieldUtil.getFormItemForCheckbox("packageFlg", "是否包车", packageFlg, 1));
		itemlist.add(FieldUtil.getFormItemForInputNumber("packagePrice", "包车价格", packagePrice, 1));
		groupitem.put("title", "基本信息");
		groupitem.put("defaultshow", true);
		groupitem.put("col", 3);
		groupitem.put("items", itemlist);
		grouplist.add(groupitem);
		retMap.put("data",grouplist);
		// 获取货物信息
		List<EditTableBean> editTableBeanList = new ArrayList<EditTableBean>();
		//货物类型
		EditTableBean editTableBean = new EditTableBean("货物类型","goodsTypeName","20%",false);
		Map goodsTypeMap = FieldUtil.getFormItemForInput("goodsTypeName", "货物类型", "", 1);
		editTableBean.setCell(goodsTypeMap);
		editTableBeanList.add(editTableBean);

		editTableBean = new EditTableBean("始发地","startPlace","20%",false);
		Map startPlaceMap = FieldUtil.getFormItemForInput("startPlace", "始发地", "", 1);
		editTableBean.setCell(startPlaceMap);
		editTableBeanList.add(editTableBean);

		editTableBean = new EditTableBean("目的地","endPlace","20%",false);
		Map endPlaceMap = FieldUtil.getFormItemForInput("endPlace", "目的地", "", 1);
		editTableBean.setCell(endPlaceMap);
		editTableBeanList.add(editTableBean);

		editTableBean = new EditTableBean("票据","invoiceFlg","20%",false);
		Map invoiceFlgMap = FieldUtil.getFormItemForCheckbox("invoiceFlg", "票据", "", 1);
		editTableBean.setCell(invoiceFlgMap);
		editTableBeanList.add(editTableBean);

		editTableBean = new EditTableBean("单价","price","15%",false);
		Map priceMap = FieldUtil.getFormItemForInputNumber("price", "单价", "", 0,999,1);
		editTableBean.setCell(priceMap);
		editTableBeanList.add(editTableBean);

		editTableBean = new EditTableBean("重量","realCarry","15%",false);
		Map realCarryMap = FieldUtil.getFormItemForInputNumber("realCarry", "重量", "", 0, 999, 1);
		editTableBean.setCell(realCarryMap);
		editTableBeanList.add(editTableBean);

		retMap.put("editcolumns",editTableBeanList);

		List<TruckGoodsOrderDetailEntity> truckGoodsOrderDetailEntities = new ArrayList<TruckGoodsOrderDetailEntity>();
		if(id > 0){
			TruckGoodsOrderDetailEntity searchForOrderDetail = new TruckGoodsOrderDetailEntity();
			searchForOrderDetail.setReportId(id);
			truckGoodsOrderDetailEntities = truckGoodsOrderDao.getTruckGoodsOrderDetail(searchForOrderDetail);
		}
		retMap.put("editdatas",truckGoodsOrderDetailEntities);

		// 获取分配信息
		List<SearchConditionOption> goodsTypeSelectOptions = selectOptionUtils.getGoodsTypeOptions(false,truckGoods);
		List<SearchConditionOption> driverOptions = selectOptionUtils.getDriverOptions(false);
		List<SearchConditionOption> truckPartOptions = selectOptionUtils.getTruckPartOptions();
		List<SearchConditionOption> truckNumberOptions = selectOptionUtils.getTruckOptions(false);
		List<SearchConditionOption> truckPartnerOptions = selectOptionUtils.getPartnerOptions(false);
		Map initFormFields = new HashMap();
		initFormFields.put("goodsType",FieldUtil.getFormItemForSelect("goodsType", "货物类型", "", 3, 2, goodsTypeSelectOptions,true));
		initFormFields.put("truckPart",FieldUtil.getFormItemForRadioGroup("truckPart", "分配状态", "", 3, 2, truckPartOptions));
		initFormFields.put("truckNumber",FieldUtil.getFormItemForSelect("truckNumber", "车牌号", "", 3, 2, truckNumberOptions));
		initFormFields.put("driver",FieldUtil.getFormItemForSelect("driver", "司机", "", 3, 2, driverOptions));
		initFormFields.put("partner",FieldUtil.getFormItemForSelect("partner", "伙伴", "", 3, 2, truckPartnerOptions));
		initFormFields.put("partnerCarry",FieldUtil.getFormItemForInputNumber("partnerCarry", "重量", "", 3));
		initFormFields.put("partnerPrice",FieldUtil.getFormItemForInputNumber("partnerPrice", "单价", "", 3));


		List<TruckGoodsReportDetailEntity> initDatas = new ArrayList<TruckGoodsReportDetailEntity>();
		TruckGoodsReportDetailEntity forSearchEntity = new TruckGoodsReportDetailEntity();
		forSearchEntity.setTruckOrder(id);
		initDatas = orderApportionDao.getTruckGoodsReportDetail(forSearchEntity);
		if(initDatas.size() == 0){
			initDatas.add(new TruckGoodsReportDetailEntity());
		}
		retMap.put("initFormFields",initFormFields);
		retMap.put("initDatas",initDatas);

		return retMap;
	}

	/**
	 * @param params
	 * @return
	 */
	@Override
	public Map saveOrderApportion(Map params) {
		Map retMap = new HashMap();
		retMap.put("status",true);
		String orderApportionInfo = Utils.null2String(params.get("orderApportionInfo"));
		int orderTakerId = Utils.getIntValue(Utils.null2String(params.get("orderTakerId"))) ;
		if(!orderApportionInfo.equals("") && orderTakerId > 0){
			//首先删除所有信息
			orderApportionDao.deleteReportDetailByOrderId(orderTakerId+"");
			// 再新增
			JSONArray orderTakerInfoArr = JSONArray.parseArray(orderApportionInfo);
			Iterator iterator = orderTakerInfoArr.iterator();
			while (iterator.hasNext()) {
				JSONObject jsonObject = (JSONObject) iterator.next();
				String goodsType = Utils.null2String(jsonObject.get("goodsType"));
				if(!goodsType.equals("")){
					String truckNumber = Utils.null2String(jsonObject.get("truckNumber"));
					String driver = Utils.null2String(jsonObject.get("driver"));
					BigDecimal partnerCarry = Utils.toDecimal(Utils.null2String(jsonObject.get("partnerCarry")),0);
					BigDecimal partnerPrice = Utils.toDecimal(Utils.null2String(jsonObject.get("partnerPrice")),0);
					int partner = Utils.getIntValue(Utils.null2String(jsonObject.get("partner")));
					int truckPart = Utils.getIntValue(Utils.null2String(jsonObject.get("truckPart")));
					TruckGoodsReportDetailEntity truckGoodsReportDetailEntity = new TruckGoodsReportDetailEntity();
					truckGoodsReportDetailEntity.setGoodsType(goodsType);
					truckGoodsReportDetailEntity.setTruckNumber(truckNumber);
					truckGoodsReportDetailEntity.setDriver(driver);
					truckGoodsReportDetailEntity.setPartnerCarry(partnerCarry);
					truckGoodsReportDetailEntity.setPartnerPrice(partnerPrice);
					truckGoodsReportDetailEntity.setPartner(partner);
					truckGoodsReportDetailEntity.setTruckOrder(orderTakerId);
					truckGoodsReportDetailEntity.setTruckPart(truckPart);
					orderApportionDao.insertTruckGoodsReportDetail(truckGoodsReportDetailEntity);
				}

			}

		}else{
			retMap.put("ret",false);
			retMap.put("errorMsg","车辆信息为空！");
		}
		return retMap;
	}

	/**
	 * @param params
	 * @return
	 */
	@Override
	public Map dispatchOrderApportion(Map params) {
		Map retMap = new HashMap();
		int orderTakerId = Utils.getIntValue(Utils.null2String(params.get("orderTakerId"))) ;
		String orderApportionInfo = Utils.null2String(params.get("orderApportionInfo"));
		if(!orderApportionInfo.equals("") && orderTakerId > 0){
			// 获取货物详细信息
			TruckGoodsOrderTakerEntity truckGoodsOrderTakerValueEntity = new TruckGoodsOrderTakerEntity();
			TruckGoodsOrderTakerEntity truckGoodsOrderTakerEntity = new TruckGoodsOrderTakerEntity();
			truckGoodsOrderTakerEntity.setId(orderTakerId);
			List<TruckGoodsOrderTakerEntity> truckGoodsOrderTakerEntityList = truckGoodsOrderDao.getTruckGoodsOrderTaker(truckGoodsOrderTakerEntity);
			if(truckGoodsOrderTakerEntityList.size() > 0){
				truckGoodsOrderTakerValueEntity = truckGoodsOrderTakerEntityList.get(0);
			}

			// 获取分配货物明细信息
			List<TruckGoodsOrderDetailEntity> truckGoodsOrderDetailEntities = new ArrayList<TruckGoodsOrderDetailEntity>();
			TruckGoodsOrderDetailEntity searchForOrderDetail = new TruckGoodsOrderDetailEntity();
			searchForOrderDetail.setReportId(orderTakerId);
			truckGoodsOrderDetailEntities = truckGoodsOrderDao.getTruckGoodsOrderDetail(searchForOrderDetail);

			//首先删除所有信息
			orderApportionDao.deleteReportDetailByOrderId(orderTakerId+"");
			// 再新增
			JSONArray orderTakerInfoArr = JSONArray.parseArray(orderApportionInfo);
			Iterator iterator = orderTakerInfoArr.iterator();
			List<String> driverModuleIds = commonTransMethod.getModuleId(SendTypeConstant.DRIVER, WorkflowTypeConstant.APPORTION);
			Calendar today = Calendar.getInstance();
			String currentdate = Utils.add0(today.get(Calendar.YEAR), 4) + "-" + Utils.add0(today.get(Calendar.MONTH) + 1, 2) + "-" + Utils.add0(today.get(Calendar.DAY_OF_MONTH), 2);

			String currenttime = Utils.add0(today.get(Calendar.HOUR_OF_DAY), 2) + ":" + Utils.add0(today.get(Calendar.MINUTE), 2) ;
			while (iterator.hasNext()) {
				JSONObject jsonObject = (JSONObject) iterator.next();
				String goodsType = Utils.null2String(jsonObject.get("goodsType"));
				if(!goodsType.equals("")){
					int reportId = insertReportInfo(orderTakerId,jsonObject,truckGoodsOrderDetailEntities,truckGoodsOrderTakerValueEntity);
					String truckNumber = Utils.null2String(jsonObject.get("truckNumber"));
					String driver = Utils.null2String(jsonObject.get("driver"));
					BigDecimal partnerCarry = Utils.toDecimal(Utils.null2String(jsonObject.get("partnerCarry")),0);
					BigDecimal partnerPrice = Utils.toDecimal(Utils.null2String(jsonObject.get("partnerPrice")),0);
					int partner = Utils.getIntValue(Utils.null2String(jsonObject.get("partner")));
					int truckPart = Utils.getIntValue(Utils.null2String(jsonObject.get("truckPart")));
					TruckGoodsReportDetailEntity truckGoodsReportDetailEntity = new TruckGoodsReportDetailEntity();
					truckGoodsReportDetailEntity.setGoodsType(goodsType);
					truckGoodsReportDetailEntity.setTruckNumber(truckNumber);
					truckGoodsReportDetailEntity.setDriver(driver);
					truckGoodsReportDetailEntity.setPartnerCarry(partnerCarry);
					truckGoodsReportDetailEntity.setPartnerPrice(partnerPrice);
					truckGoodsReportDetailEntity.setPartner(partner);
					truckGoodsReportDetailEntity.setTruckOrder(orderTakerId);
					truckGoodsReportDetailEntity.setReportId(reportId);
					truckGoodsReportDetailEntity.setTruckPart(truckPart);
					orderApportionDao.insertTruckGoodsReportDetail(truckGoodsReportDetailEntity);
					//推送给司机
					DriverInfoEntity driverInfoEntity = commonTransMethod.getDriverInfo(driver);
					String openId = driverInfoEntity.getOpenId();
					if(!openId.equals("") && !driver.equals("")){
						driverModuleIds.stream().forEach(_moduleId->{
							Template template = new Template();
							template.setTemplateId(_moduleId);
							template.setToUser(openId);
							template.setUrl("");
							List<TemplateParam> templateParams = new ArrayList<>();
							TemplateParam templateParam = new TemplateParam("first","您好，您有新的订单！","");
							templateParams.add(templateParam);
							templateParam = new TemplateParam("keyword1",driverInfoEntity.getName(),"");
							templateParams.add(templateParam);
							templateParam = new TemplateParam("keyword2",commonTransMethod.getGoodsTypeName(goodsType),"");
							templateParams.add(templateParam);
							templateParam = new TemplateParam("keyword3",currentdate+" "+currenttime,"");
							templateParams.add(templateParam);
							templateParam = new TemplateParam("remark","11111111","");
							templateParams.add(templateParam);
							template.setTemplateParamList(templateParams);
							wxMsgPush.SendWxMsg(template);
						});
					}

				}
				// 设置已分配
				truckGoodsOrderDao.setOrderTakerStatusForReceipt(orderTakerId+"");
				// 推送给客户
				String clinetId = truckGoodsOrderTakerValueEntity.getClient();
				ClientEntity clientEntity = commonTransMethod.getClientInfo(clinetId);
				String openId = clientEntity.getOpenId();
				if(!clinetId.equals("") && !openId.equals("")){
					List<String> moduleIds = commonTransMethod.getModuleId(SendTypeConstant.CLIENT, WorkflowTypeConstant.APPORTION);
					moduleIds.stream().forEach(_moduleId->{
						Template template = new Template();
						template.setTemplateId(_moduleId);
						template.setToUser(openId);
						template.setUrl("");
						List<TemplateParam> templateParams = new ArrayList<>();
						TemplateParam templateParam = new TemplateParam("first","您好，您有新的订单！","");
						templateParams.add(templateParam);
						templateParam = new TemplateParam("keyword1","xxxxxxx","");
						templateParams.add(templateParam);
						templateParam = new TemplateParam("keyword2","xxxxxx","");
						templateParams.add(templateParam);
						templateParam = new TemplateParam("keyword3",clientEntity.getClientName(),"");
						templateParams.add(templateParam);
						templateParam = new TemplateParam("keyword4","","");
						templateParams.add(templateParam);
						templateParam = new TemplateParam("keyword5","","");
						templateParams.add(templateParam);
						templateParam = new TemplateParam("remark","11111111","");
						templateParams.add(templateParam);
						template.setTemplateParamList(templateParams);
						wxMsgPush.SendWxMsg(template);
					});
				}

			}

		}else{
			retMap.put("ret",false);
			retMap.put("errorMsg","车辆信息为空！");
		}
		retMap.put("status",true);
		return retMap;
	}


	/**
	 * @param params
	 * @return
	 */
	@Override
	public Map deleteOrderApportion(Map params) {
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

	/**
	 * @param orderTakerId
	 * @param jsonObject
	 * @param truckGoodsOrderDetailEntities
	 * @param mainInfo
	 * @return
	 */
	public int insertReportInfo(int orderTakerId, JSONObject jsonObject,List<TruckGoodsOrderDetailEntity> truckGoodsOrderDetailEntities,TruckGoodsOrderTakerEntity mainInfo){
		int receiptId = 0;
		String goodsType = Utils.null2String(jsonObject.get("goodsType"));
		String truckNumber = Utils.null2String(jsonObject.get("truckNumber"));
		String driver = Utils.null2String(jsonObject.get("driver"));
		BigDecimal partnerCarry = Utils.toDecimal(Utils.null2String(jsonObject.get("partnerCarry")),0);
		BigDecimal partnerPrice = Utils.toDecimal(Utils.null2String(jsonObject.get("partnerPrice")),0);
		int partner = Utils.getIntValue(Utils.null2String(jsonObject.get("partner")));
		int truckPart = Utils.getIntValue(Utils.null2String(jsonObject.get("truckPart")));
		TruckGoodsReportEntity truckGoodsReportEntity = new TruckGoodsReportEntity();
		truckGoodsReportEntity.setTruckNumber(truckNumber);
		truckGoodsReportEntity.setReportNumber("");
		truckGoodsReportEntity.setReportId(orderTakerId);
		truckGoodsReportEntity.setBeginDate(mainInfo.getBeginDate());
		truckGoodsReportEntity.setExpectedDate("");
		truckGoodsReportEntity.setEndDate("");
		truckGoodsReportEntity.setDriver(driver);
		truckGoodsReportEntity.setClient(mainInfo.getClient());
		truckGoodsReportEntity.setGoodsType(goodsType);
		truckGoodsReportEntity.setPackageFlg( Utils.null2String(mainInfo.getPackageFlg()) );
		truckGoodsReportEntity.setPackagePrice(mainInfo.getPackagePrice());
		truckGoodsReportEntity.setTruckPart(truckPart);
		truckGoodsReportEntity.setPartner(Utils.null2String(partner));
		truckGoodsReportEntity.setPartnerPrice(Utils.null2String(partnerPrice));
		truckGoodsReportEntity.setPartnerCarry(Utils.null2String(partnerCarry));
		truckGoodsReportEntity.setReportStatus(0);
		truckGoodsReportEntity.setIsLater(0);
		truckGoodsReportEntity.setLaterReason("");
		truckGoodsReportEntity.setPrepaidFlg(0);
		truckGoodsReportEntity.setPrepaidExpress(new BigDecimal(0));
		truckGoodsReportEntity.setPrepaidDesc("");
		truckGoodsReportEntity.setProfit(new BigDecimal(0));
		truckGoodsReportEntity.setCost(new BigDecimal(0));
		truckGoodsReportEntity.setCustomerOrder("");
		truckGoodsReportEntity.setRemark("");
		truckGoodsReportEntity.setSettlement(0);
		orderReceiptDao.insertTruckGoodsReport(truckGoodsReportEntity);
		receiptId = truckGoodsReportEntity.getId();

		List<TruckGoodsReceiptDetailEntity> insertInfo = new ArrayList<>();
		for (TruckGoodsOrderDetailEntity _truckGoodsOrderDetail:truckGoodsOrderDetailEntities) {
			String detailGoodsType = _truckGoodsOrderDetail.getGoodsType();
			if((","+goodsType+",").indexOf(","+detailGoodsType+",")>-1){
				TruckGoodsReceiptDetailEntity truckGoodsReceiptDetailEntity = new TruckGoodsReceiptDetailEntity();
				truckGoodsReceiptDetailEntity.setReceiptId(receiptId);
				truckGoodsReceiptDetailEntity.setReportId(_truckGoodsOrderDetail.getReportId());
				truckGoodsReceiptDetailEntity.setGoodsType(_truckGoodsOrderDetail.getGoodsType());
				truckGoodsReceiptDetailEntity.setDeleteFlg(_truckGoodsOrderDetail.getDeleteFlg());
				truckGoodsReceiptDetailEntity.setPrice(_truckGoodsOrderDetail.getPrice());
				truckGoodsReceiptDetailEntity.setRealCarry(_truckGoodsOrderDetail.getRealCarry());
				truckGoodsReceiptDetailEntity.setInvoiceFlg(_truckGoodsOrderDetail.getInvoiceFlg());
				truckGoodsReceiptDetailEntity.setStartPlace(_truckGoodsOrderDetail.getStartPlace());
				truckGoodsReceiptDetailEntity.setEndPlace(_truckGoodsOrderDetail.getEndPlace());
				truckGoodsReceiptDetailEntity.setCreateDate(_truckGoodsOrderDetail.getCreateDate());
				truckGoodsReceiptDetailEntity.setCreateTime(_truckGoodsOrderDetail.getCreateTime());
				truckGoodsReceiptDetailEntity.setEditDate(_truckGoodsOrderDetail.getEditDate());
				truckGoodsReceiptDetailEntity.setEditTime(_truckGoodsOrderDetail.getEditTime());
				insertInfo.add(truckGoodsReceiptDetailEntity);
			}
		}
		orderReceiptDao.insertTruckGoodsReceiptDetail(insertInfo);
		wxMsgPush.SendWxMsg("");

		return receiptId;
	}
}
