package com.logisticscenter.controller;

import com.common.CommonTransMethod;
import com.common.ConvertService;
import com.javabean.CommercialBean;
import com.javabean.TruckBean;
import com.logisticscenter.service.CommercialService;
import com.logisticscenter.service.TruckService;
import com.util.ParamUtil;
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
 *商业险ACTION
 */
@RestController
@RequestMapping(value = "/api/commercial")
public class CommercialController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommercialController(){
		
	}

	@Autowired
	private CommercialService commercialService;

	//车辆服务类
	@Autowired
	private TruckService truckService;

	@ResponseBody
	@PostMapping("/addCommercial")
	public Map addCommercial(HttpServletRequest request){
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
//		CommercialBean bean = new CommercialBean(truckNumber,startDate,endDate,pageSize,currentPage);
//		int maxId = commercialService.insertCommercial(bean);
//		this.status = maxId > 0?true:false;
//		return "success";
	}

	@ResponseBody
	@PostMapping("/selectCommercial")
	public Map selectCommercial(HttpServletRequest request){
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
//
//		}
//		CommercialBean infoBean = new CommercialBean(id,truckNumber,startDate,endDate,pageSize,currentPage);
//		List<CommercialBean> beanLst= commercialService.getCommercial(infoBean);
//		int pageCount = 0;
//		if("1".equals(currentPage)){
//			String count = commercialService.getCommercialCount(infoBean);
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
//			retResult.put("commercial",result);
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
	@PostMapping("/deleteCommercial")
	public Map deleteCommercial(HttpServletRequest request){
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
//		String ids = request.getParameter("deleteCommercials");
//		int count = commercialService.deleteCommercial(ids);
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
	@PostMapping("/updateCommercial")
	public Map updateCommercial(HttpServletRequest request){
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
//		CommercialBean infoBean = new CommercialBean(truckNumber,startDate,endDate,pageSize,currentPage);
//		infoBean.setId(id);
//		commercialService.updateCommercial(infoBean);
//
//		return "success";
	}

	@ResponseBody
	@PostMapping("/getWarnCommercial")
	public Map getWarnCommercial(HttpServletRequest request){

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

//		int days = ConvertService.getIntValue(request.getParameter("commercialDate"),0);
//		String selectDays = ConvertService.getDate(days);
//		List<CommercialBean> beanLst = commercialService.getWarnCommercial(selectDays);
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
//					msg +="车牌号:"+beanTruckLst.get(i).getTruckNumber()+"在"+selectDays+"没有提交商业保险,请尽快提交"+"<br>";
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
