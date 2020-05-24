package com.logisticscenter.controller;

import com.javabean.SystemInfoBean;
import com.logisticscenter.service.LoginService;
import com.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/login")
public class LoginController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static List<Map<String,SystemInfoBean>> systemInfo = new ArrayList<Map<String,SystemInfoBean>>();

	public LoginController(){

	}


	@Autowired
	private LoginService loginService;


	/**
	 * 登陆请求
	 * @return
	 */
	@ResponseBody
	@PostMapping("/signIn")
	public Map signIn(HttpServletRequest request , HttpServletResponse response){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(loginService.getSystemInfo(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	/**
	 * @return
	 */
	@ResponseBody
	@PostMapping("/currentUser")
	public Map currentUser(HttpServletRequest request , HttpServletResponse response){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(loginService.getSystemInfo(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
			apidatas.put("status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", true);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	/**
	 * 登出请求
	 * @return
	 */
	@ResponseBody
	@PostMapping("/signOut")
	public Map signOut(HttpServletRequest request , HttpServletResponse response){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(loginService.getSystemInfo(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", true);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	/**
	 * 登出请求
	 * @return
	 */
	@ResponseBody
	@PostMapping("/account")
	public Map account(HttpServletRequest request , HttpServletResponse response){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(loginService.getSystemInfo(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", true);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}



}
