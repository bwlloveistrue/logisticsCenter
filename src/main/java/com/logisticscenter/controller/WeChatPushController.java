package com.logisticscenter.controller;

//import com.asprise.imaging.scan.ui.workbench.AspriseScanUI;

import com.util.wxPublic.WxMsgPush;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/weChatPush")
public class WeChatPushController {

	/**
	 * 微信消息推送
	 */
	private final WxMsgPush wxMsgPush;

	/**
	 * 构造注入
	 */
	protected WeChatPushController(WxMsgPush wxMsgPush) {
		this.wxMsgPush = wxMsgPush;
	}

	@ResponseBody
	@GetMapping("/pushMsg")
	public Map pushMsg(HttpServletRequest request){

		Map<String, Object> apidatas = new HashMap<String, Object>();
		wxMsgPush.SendWxMsg("oWYMNv6WJMfjWxqyV9NnAfdKHu4I");
		return apidatas;
	}

	@ResponseBody
	@GetMapping("/pushClient")
	public Map pushClient(HttpServletRequest request){

		Map<String, Object> apidatas = new HashMap<String, Object>();
		wxMsgPush.SendWxMsg("oWYMNv6WJMfjWxqyV9NnAfdKHu4I");
		return apidatas;
	}

	@ResponseBody
	@GetMapping("/pushDriver")
	public Map pushDriver(HttpServletRequest request){

		Map<String, Object> apidatas = new HashMap<String, Object>();
		wxMsgPush.SendWxMsg("oWYMNv6WJMfjWxqyV9NnAfdKHu4I");
		return apidatas;
	}

	@ResponseBody
	@GetMapping("/pushPartner")
	public Map pushPartner(HttpServletRequest request){

		Map<String, Object> apidatas = new HashMap<String, Object>();
		wxMsgPush.SendWxMsg("oWYMNv6WJMfjWxqyV9NnAfdKHu4I");
		return apidatas;
	}

	@ResponseBody
	@GetMapping("/getOpenIDs")
	public Map getOpenIDs(HttpServletRequest request){

		Map<String, Object> apidatas = new HashMap<String, Object>();
		List openIds = wxMsgPush.getOpenId();
		apidatas.put("openIds",openIds);
		return apidatas;
	}


	@ResponseBody
	@GetMapping("/getUserInfoByOpenID")
	public Map getUserInfoByOpenID(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		List userInfoList = wxMsgPush.getAllSubscribeUserInfo();
		apidatas.put("userInfo",userInfoList);
		return apidatas;
	}

}
