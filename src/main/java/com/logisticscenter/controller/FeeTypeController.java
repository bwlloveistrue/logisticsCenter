package com.logisticscenter.controller;

import com.cache.CacheManager;
import com.common.CommonTransMethod;
import com.common.ConvertService;
import com.javabean.FeeTypeBean;
import com.logisticscenter.service.FeeTypeService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/feeType")
public class FeeTypeController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FeeTypeController(){
		
	}

	@Autowired
	private FeeTypeService feeTypeService;


	@ResponseBody
	@PostMapping("/selectDriver")
	public Map selectFeeType(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(feeTypeService.getFeeType(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	@ResponseBody
	@PostMapping("/addFeeType")
	public Map addFeeType(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
//			apidatas.putAll(clientService.getClient(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;

//		int maxId = 0;
//		FeeTypeBean bean = new FeeTypeBean( id,feeName,showType,isUse,isDelete,pageSize,currentPage);
//		if("add".equals(method)){
//			maxId = feeTypeService.insertFeeType(bean);
//			this.status = maxId > 0?true:false;
//		}else if("edit".equals(method)){
//			feeTypeService.updateFeeType(bean);
//		}
//		CacheManager.clearOnly("feeTypeBean_CACHE");
//		return "success";
	}


	@ResponseBody
	@PostMapping("/deleteFeeType")
	public Map deleteFeeType(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
//			apidatas.putAll(clientService.getClient(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;

//		String ids = request.getParameter("deleteFeeTypes");
//		int count = feeTypeService.deleteFeeType(ids);
//		//获取输出流，然后使用
//        PrintWriter out = null;
//		try {
//			Map retResult = new HashMap();
//			if(count>0){
//				retResult.put("result","1");
//			}else{
//				retResult.put("result","0");
//			}
//			response.setContentType("text/html; charset=utf-8");
//			out = response.getWriter();
////			/* 设置格式为text/json    */
////            response.setContentType("text/json");
////            /*设置字符集为'UTF-8'*/
////            response.setCharacterEncoding("UTF-8");
//			JSONObject obj = JSONObject.parseObject(retResult.toString());
//			out.print(obj.toString());
//			out.flush();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}//返回json对象
//		return null;
	}
	

}
