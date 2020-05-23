package com.logisticscenter.controller;

import com.logisticscenter.service.NoticesInfoService;
import com.logisticscenter.service.UserInfoService;
import com.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @卜伟领 2017
 *
 */
@RestController
@RequestMapping(value = "/api/notices")
public class NoticesInfoController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NoticesInfoController(){
		
	}

	@Autowired
	private NoticesInfoService noticesInfoService;


	@ResponseBody
	@PostMapping("/getNotices")
	public Map selectAllClient(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(noticesInfoService.getNotices(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	@ResponseBody
	@PostMapping("/clearNotices")
	public Map clearNotices(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(noticesInfoService.clearNotices(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;

	}
	

}
