package com.logisticscenter.controller;

import com.common.CommonTransMethod;
import com.common.ConvertService;
import com.javabean.TruckGoodsOrderDetailBean;
import com.javabean.TruckGoodsOrderTakerBean;
import com.javabean.TruckGoodsReportBean;
import com.javabean.TruckGoodsReportDetailBean;
import com.logisticscenter.model.LoginInfoEntity;
import com.logisticscenter.service.TruckGoodsOrderService;
import com.logisticscenter.service.TruckGoodsReportService;
import com.util.ConstantUtils;
import com.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping(value = "/api/orderTaker")
public class OrderTakerController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderTakerController(){
		
	}

	LoginInfoEntity loginInfoEntity = new LoginInfoEntity();

	public LoginInfoEntity getLoginInfoEntity() {
		return loginInfoEntity;
	}

	public void setLoginInfoEntity(LoginInfoEntity loginInfoEntity) {
		this.loginInfoEntity = loginInfoEntity;
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
}
