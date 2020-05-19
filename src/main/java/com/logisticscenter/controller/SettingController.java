package com.logisticscenter.controller;

import com.cache.Cache;
import com.cache.CacheManager;
import com.javabean.TruckSetBean;
import com.logisticscenter.service.ChartsService;
import com.logisticscenter.service.TruckSetService;
import com.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @卜伟领 2017
 *
 */
@RestController
@RequestMapping(value = "/api/setting")
public class SettingController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SettingController(){

	}

	@Autowired
	private TruckSetService truckSetService;


	@ResponseBody
	@PostMapping("/selectSetting")
	public Map selectSetting(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(truckSetService.getTruckSet(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;

	}

	@ResponseBody
	@PostMapping("/updateSetting")
	public Map updateSetting(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(truckSetService.updateTruckSet(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;

	}

}
