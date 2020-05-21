package com.logisticscenter.controller;

import com.javabean.SystemInfoBean;
import com.util.wxPublic.WXPublicUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/wxpublic")
public class wechatVerifyController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static List<Map<String,SystemInfoBean>> systemInfo = new ArrayList<Map<String,SystemInfoBean>>();

	public wechatVerifyController(){

	}

	/**
	 * 微信验证
	 * @return
	 */
	@ResponseBody
	@GetMapping("/verify_wx_token")
	public String verify_wx_token(HttpServletRequest request , HttpServletResponse response){
		String msgSignature = request.getParameter("signature");
		String msgTimestamp = request.getParameter("timestamp");
		String msgNonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		try{
			if (WXPublicUtils.verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
				return echostr;
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}



}
