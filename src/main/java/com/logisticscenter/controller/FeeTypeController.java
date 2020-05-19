package com.logisticscenter.controller;

import com.cache.CacheManager;
import com.common.CommonTransMethod;
import com.common.ConvertService;
import com.javabean.FeeTypeBean;
import com.logisticscenter.service.FeeTypeService;
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
//			apidatas.putAll(clientService.getClient(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
//		selectFeeName = ConvertService.null2String(request.getParameter("selectFeeName"));
//		//如果查询的是按照使用情况来查看,默认已1:使用中来查看
//		isUse = ConvertService.getIntValue(request.getParameter("isUse"),-1);
//		HttpServletResponse response =   ServletActionContext.getResponse();
//		FeeTypeBean bean = new FeeTypeBean( id,selectFeeName,showType,isUse,isDelete,pageSize,currentPage);
//		List<FeeTypeBean> beanLst= feeTypeService.getFeeType(bean);
//		int pageCount = 0;
//		if("1".equals(currentPage)){
//			String count = feeTypeService.getFeeTypeCount(bean);
//			pageCount = Integer.parseInt(count)%Integer.parseInt(pageSize) == 0?(Integer.parseInt(count)/Integer.parseInt(pageSize)):(Integer.parseInt(count)/Integer.parseInt(pageSize) + 1);
//		}
//		//systemBean = loginService.getSystemInfo(loginid);
//		//获取输出流，然后使用
//		PrintWriter out = null;
//		try {
//			Map result = new HashMap();
//			Map retResult = new HashMap();
//			Map beanMap = null;
//			for(int i = 0 ; i<beanLst.size(); i++){
//				beanMap = new HashMap();
//				beanMap.put("feeName",beanLst.get(i).getFeeName());
//				beanMap.put("showTypeShow",CommonTransMethod.convertShowType(beanLst.get(i).getShowType()));
//				beanMap.put("showType",beanLst.get(i).getShowType());
//				beanMap.put("isUse",beanLst.get(i).getIsUse());
//				beanMap.put("isDelete",beanLst.get(i).getIsDelete());
//				beanMap.put("id",beanLst.get(i).getId());
//
//				result.put(beanLst.get(i).getId(), beanMap);
//			}
//			retResult.put("typeInfo",result);
//			retResult.put("pageCount",pageCount);
//			retResult.put("currentPage",currentPage);
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
