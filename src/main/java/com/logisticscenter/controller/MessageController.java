package com.logisticscenter.controller;

import com.logisticscenter.service.MessageService;
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
@RequestMapping(value = "/api/messageTypeInfo")
public class MessageController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public MessageController(){
		
	}

	@Autowired
	private MessageService messageService;

	@ResponseBody
	@PostMapping("/getCondition")
	public Map getCondition(HttpServletRequest request){

		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(messageService.getCondition(ParamUtil.request2Map(request)));
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
			apidatas.putAll(messageService.getTableInfoList(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	@ResponseBody
	@PostMapping("/getMessageTypeInfoFields")
	public Map getMessageInfoFields(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(messageService.getMessageInfoFields(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	@ResponseBody
	@PostMapping("/addMessageTypeInfo")
	public Map addMessage(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(messageService.insertMessage(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}


	@ResponseBody
	@PostMapping("/updateMessageTypeInfo")
	public Map updateMessage(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(messageService.updateMessage(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}


	@ResponseBody
	@PostMapping("/deleteMessageTypeInfo")
	public Map deleteMessage(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(messageService.deleteMessage(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}
	

}
