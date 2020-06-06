package com.logisticscenter.controller;

import com.logisticscenter.service.MessageService;
import com.logisticscenter.service.PartnerService;
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
@RequestMapping(value = "/api/partnerInfo")
public class PartnerController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public PartnerController(){
		
	}

	@Autowired
	private PartnerService partnerService;

	@ResponseBody
	@PostMapping("/getCondition")
	public Map getCondition(HttpServletRequest request){

		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(partnerService.getCondition(ParamUtil.request2Map(request)));
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
			apidatas.putAll(partnerService.getTableInfoList(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	@ResponseBody
	@PostMapping("/getPartnerInfoFields")
	public Map getPartnerInfoFields(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(partnerService.getPartnerInfoFields(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	@ResponseBody
	@PostMapping("/addPartnerInfo")
	public Map addPartnerInfo(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(partnerService.insertPartner(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}


	@ResponseBody
	@PostMapping("/updatePartnerInfo")
	public Map updateMessage(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(partnerService.updatePartner(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}


	@ResponseBody
	@PostMapping("/deletePartnerInfo")
	public Map deleteMessage(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(partnerService.deletePartner(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}
	

}
