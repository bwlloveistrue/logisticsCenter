package com.logisticscenter.controller;

import com.common.CommonTransMethod;
import com.common.ConvertService;
import com.javabean.CompulsoryBean;
import com.javabean.TruckBean;

import com.logisticscenter.service.CompulsoryService;
import com.logisticscenter.service.TruckService;
import com.util.TimeUtil;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @卜伟领 2017
 *车辆强制险ACTION
 */
@RestController
@RequestMapping(value = "/api/compulsory")
public class CompulsoryController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompulsoryController(){
		
	}

	@Autowired
	private CompulsoryService compulsoryService;
	//车辆服务类
	@Autowired
	private TruckService truckService;

	@ResponseBody
	@PostMapping("/addCompulsory")
	public Map addCompulsory(HttpServletRequest request){
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

//		CompulsoryBean bean = new CompulsoryBean(truckNumber,startDate,endDate,pageSize,currentPage);
//		int maxId = compulsoryService.insertCompulsory(bean);
//		this.status = maxId > 0?true:false;
//		return "success";
	}

	@ResponseBody
	@PostMapping("/selectCompulsory")
	public Map selectCompulsory(HttpServletRequest request){
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
//		HttpServletResponse response = ServletActionContext.getResponse();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		truckNumber = request.getParameter("truckNumber");
//		timeSag = ConvertService.getIntValue((request.getParameter("timeSag")),0);
//		truckNumber = request.getParameter("truckNumber");
//		if(timeSag > 0&&timeSag<6){
//			startDate = TimeUtil.getDateByOption(""+timeSag,"0");
//			endDate = TimeUtil.getDateByOption(""+timeSag,"1");
//
//		}else{
//			if(timeSag==6){//指定时间
//				startDate = request.getParameter("startDate");
//				endDate = request.getParameter("endDate");
//			}
//		}
//		CompulsoryBean infoBean = new CompulsoryBean(id,truckNumber,startDate,endDate,pageSize,currentPage);
//		List<CompulsoryBean> beanLst= compulsoryService.getCompulsory(infoBean);
//		int pageCount = 0;
//		if("1".equals(currentPage)){
//			String count = compulsoryService.getCompulsoryCount(infoBean);
//			pageCount = Integer.parseInt(count)%Integer.parseInt(pageSize) == 0?(Integer.parseInt(count)/Integer.parseInt(pageSize)):(Integer.parseInt(count)/Integer.parseInt(pageSize) + 1);
//		}
//		//获取输出流，然后使用
//        PrintWriter out = null;
//		try {
//			Map result = new HashMap();
//			Map retResult = new HashMap();
//			Map beanMap = null;
//			for(int i = 0 ; i<beanLst.size(); i++){
//				beanMap = new HashMap();
//				beanMap.put("id",beanLst.get(i).getId());
//				beanMap.put("truckNumber",beanLst.get(i).getTruckNumber());
//				beanMap.put("truckNumberShow",CommonTransMethod.getTruckNumber(beanLst.get(i).getTruckNumber()));
//				beanMap.put("startDate",beanLst.get(i).getStartDate());
//				beanMap.put("endDate",beanLst.get(i).getEndDate());
//				beanMap.put("createDate",beanLst.get(i).getCreateDate());
//				beanMap.put("createTime",beanLst.get(i).getCreateTime());
//				result.put(beanLst.get(i).getId(), beanMap);
//			}
//			retResult.put("compulsory",result);
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
	@PostMapping("/deleteCompulsory")
	public Map deleteCompulsory(HttpServletRequest request){
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

//		String ids = request.getParameter("deleteCompulsorys");
//		int count = compulsoryService.deleteCompulsory(ids);
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

	@ResponseBody
	@PostMapping("/updateCompulsory")
	public Map updateCompulsory(HttpServletRequest request){
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

//		truckNumber = request.getParameter("truckNumber");
//		startDate = request.getParameter("startDate");
//		endDate = request.getParameter("endDate");
//		CompulsoryBean infoBean = new CompulsoryBean(truckNumber,startDate,endDate,pageSize,currentPage);
//		infoBean.setId(id);
//		compulsoryService.updateCompulsory(infoBean);
//
//		return "success";
	}

	@ResponseBody
	@PostMapping("/getWarnCompulsory")
	public Map getWarnCompulsory(HttpServletRequest request){
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
//		int days = ConvertService.getIntValue(request.getParameter("compulsoryDate"),0);
//		String selectDays = ConvertService.getDate(days);
//		List<CompulsoryBean> beanLst = compulsoryService.getWarnCompulsory(selectDays);
//		List<TruckBean> beanTruckLst= truckService.getAllTruck();
//		//获取输出流，然后使用
//        PrintWriter out = null;
//		try {
//			Map retResult = new HashMap();
//			List truckRusultMap = new ArrayList();
//			String status = "0";
//			String msg = "";
//			for(int j=0;j<beanLst.size();j++){
//				truckRusultMap.add(beanLst.get(j).getTruckNumber());
//			}
//			for(int i=0;i<beanTruckLst.size();i++){
//				int truckId = beanTruckLst.get(i).getId();
//				if(truckRusultMap.contains(truckId+"")){
//					continue;
//				}else{
//					msg +="车牌号:"+beanTruckLst.get(i).getTruckNumber()+"在"+selectDays+"没有提交强制保险,请尽快提交"+"<br>";
//					status="1";
//				}
//
//			}
//			retResult.put("status", status);
//			retResult.put("msg", msg);
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
