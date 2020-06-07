package com.logisticscenter.controller;

//import com.asprise.imaging.scan.ui.workbench.AspriseScanUI;

import com.logisticscenter.service.MessageService;
import com.logisticscenter.service.SubscribeService;
import com.util.ParamUtil;
import com.util.wxPublic.WxMsgPush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/subscribeInfo")
public class WeChatSubscribeController {

	/**
	 * 微信消息推送
	 */
	private final WxMsgPush wxMsgPush;

	/**
	 * 构造注入
	 */
	protected WeChatSubscribeController(WxMsgPush wxMsgPush) {
		this.wxMsgPush = wxMsgPush;
	}

	@Autowired
	private SubscribeService subscribeService;

	@ResponseBody
	@PostMapping("/getCondition")
	public Map getCondition(HttpServletRequest request){

		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(subscribeService.getCondition(ParamUtil.request2Map(request)));
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
			apidatas.putAll(subscribeService.getTableInfoList(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

}
