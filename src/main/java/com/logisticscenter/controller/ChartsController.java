package com.logisticscenter.controller;

import com.common.CommonTransMethod;
import com.javabean.ChartsBean;
import com.logisticscenter.service.ChartsService;
import com.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @卜伟领 2017
 *
 */
@RestController
@RequestMapping(value = "/api/charts")
public class ChartsController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChartsController(){
		
	}

	@Autowired
	private ChartsService chartsService;


	@ResponseBody
	@GetMapping("/getClientChartsByMonth")
	public Map getClientChartsByMonth(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(chartsService.getClientChartsByMonth(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;

	}

	@ResponseBody
	@PostMapping("/getDriverChartsByMonth")
	public Map getDriverChartsByMonth(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(chartsService.getClientChartsByMonth(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}


	@ResponseBody
	@PostMapping("/getDriverFeeChartsByMonth")
	public Map getDriverFeeChartsByMonth(HttpServletRequest request){

		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(chartsService.getClientChartsByMonth(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	@ResponseBody
	@PostMapping("/getClientFeeChartsByMonth")
	public Map getClientFeeChartsByMonth(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(chartsService.getClientChartsByMonth(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}
	
}
