package com.logisticscenter.controller;

import com.asprise.imaging.core.Request;
import com.asprise.imaging.core.Result;
//import com.asprise.imaging.scan.ui.workbench.AspriseScanUI;
import com.common.ConvertService;
import com.javabean.ImageFileBean;
import com.logisticscenter.service.ImageFileService;
import com.util.ParamUtil;
import com.util.wxPublic.WxMsgPush;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/test")
public class TestController {

	/**
	 * 微信消息推送
	 */
	private final WxMsgPush wxMsgPush;

	/**
	 * 构造注入
	 */
	protected TestController(WxMsgPush wxMsgPush) {
		this.wxMsgPush = wxMsgPush;
	}

	@ResponseBody
	@GetMapping("/pushMsg")
	public Map selectAllClient(HttpServletRequest request){

		Map<String, Object> apidatas = new HashMap<String, Object>();
		wxMsgPush.SendWxMsg("oWYMNv6WJMfjWxqyV9NnAfdKHu4I");
		return apidatas;
	}
	
	public static void setMap( Map oldMap){
		oldMap.put("e", "5");
		oldMap.put("f", "6");
	}

}
