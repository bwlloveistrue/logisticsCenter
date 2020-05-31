package com.logisticscenter.controller;

import com.common.CommonTransMethod;
import com.logisticscenter.model.LoginInfoEntity;
import com.logisticscenter.service.TruckGoodsOrderService;
import com.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Controller
@RestController
@RequestMapping(value = "/api/orderReceipt")
public class OrderReceiptController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public OrderReceiptController(){
		
	}

	/**
	 * 出车预录信息service
	 */
	@Autowired
	private TruckGoodsOrderService truckGoodsOrderService;

	private CommonTransMethod commonTransMethod;


	@ResponseBody
	@PostMapping("/getCondition")
	public Map getCondition(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(truckGoodsOrderService.getCondition(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	@ResponseBody
	@PostMapping("/getTableInfoList")
	public Map getTableInfoList(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(truckGoodsOrderService.getTableInfoList(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	@ResponseBody
	@PostMapping("/getOrderReceiptFields")
	public Map getOrderTakersInfoFields(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(truckGoodsOrderService.getOrderTakersInfoFields(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	@ResponseBody
	@PostMapping("/addOrderReceipt")
	public Map addOrderTaker(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(truckGoodsOrderService.addOrderTaker(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	@ResponseBody
	@PostMapping("/updateOrderReceipt")
	public Map updateOrderTaker(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(truckGoodsOrderService.updateOrderTaker(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	@ResponseBody
	@PostMapping("/deleteOrderReceipt")
	public Map deleteOrderTaker(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(truckGoodsOrderService.deleteOrderTaker(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}
}
