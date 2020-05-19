package com.logisticscenter.controller;

import com.javabean.IllegalReportBean;
import com.logisticscenter.service.IllegalReportService;
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
@RequestMapping(value = "/api/illegalReport")
public class IllegalReportController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalReportController(){
		
	}
	
	private IllegalReportService illegalReportService;


	@ResponseBody
	@PostMapping("/getIllegalReport")
	public Map getIllegalReport(HttpServletRequest request){
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

//		HttpServletResponse response =   ServletActionContext.getResponse();
//		List<IllegalReportBean> beanLst= illegalReportService.getAllIllegalReport();
//		//systemBean = loginService.getSystemInfo(loginid);
//		 //获取输出流，然后使用
//        PrintWriter out = null;
//		try {
//			Map result = new HashMap();
//			Map retResult = new HashMap();
//			Map beanMap = null;
//			for(int i = 0 ; i<beanLst.size(); i++){
//				beanMap = new HashMap();
//				beanMap.put("name",beanLst.get(i).getIllegalNumber());
//
//				result.put(beanLst.get(i).getId(), beanMap);
//			}
//			retResult.put("driver",result);
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
	@PostMapping("/insertIllegalReport")
	public Map insertIllegalReport(HttpServletRequest request){
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

//		IllegalReportBean bean = new IllegalReportBean(truckNumber,illegalNumber,illegalType,illegalCost,driver,illegalDate,illegalReason,insuranceCost,result,illegalRemind,remark);
//		int maxId = illegalReportService.insertIllegalReport(bean);
//		this.status = maxId > 0?true:false;
//		return "success";
	}
	

}
