package com.logisticscenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.cache.CacheManager;
import com.common.CommonTransMethod;
import com.common.ConvertService;
import com.javabean.TruckBean;
import com.logisticscenter.service.TruckService;
import com.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/truckInfo")
public class TruckController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TruckService truckService;
	
	private CommonTransMethod commonTransMethod;

	public TruckController(){
		
	}

	/**
	 * 登陆请求
	 * @return
	 */
	@ResponseBody
	@PostMapping("/getCondition")
	public String addTruck(HttpServletRequest request , HttpServletResponse response){
		Map<String, Object> apidatas = new HashMap<String, Object>();

		return JSONObject.toJSONString(apidatas);
	}
	
	public Map addTruck(){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
//			apidatas.putAll(truckService.insertTruck(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;

//		TruckBean truckBean = new TruckBean(id,truckNumber,truckOwner,truckBrand,truckName,contactNumber,truckType,driver,truckColor,trucklength,truckWidth,truckHeight,standardWeight,driverLicense,engineNumber,madeDate,buyDate,worth,buyCost,remark);
//		int maxId = truckService.insertTruck(truckBean);
//		id = maxId;
//		this.status = maxId>0?true:false;
//		CacheManager.clearOnly("truckBean_CACHE");
//		return "success";
	}

	@ResponseBody
	@PostMapping("/selectTruck")
	public Map selectTruck(HttpServletRequest request , HttpServletResponse response){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(truckService.getTruck(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
	}

	@ResponseBody
	@PostMapping("/getAdvancedForm")
	public Map getAdvancedForm(HttpServletRequest request , HttpServletResponse response){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(truckService.getAdvancedForm(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;

//		HttpServletResponse response =   ServletActionContext.getResponse();
//		List<TruckBean> beanLst= truckService.getAllTruck();
//		 //获取输出流，然后使用
//        PrintWriter out = null;
//		try {
//			Map result = new HashMap();
//			Map retResult = new HashMap();
//			Map beanMap = null;
//			for(int i = 0 ; i<beanLst.size(); i++){
//				beanMap = new HashMap();
//				beanMap.put("id",beanLst.get(i).getId());
//				beanMap.put("truckNumber",beanLst.get(i).getTruckNumber());
//				beanMap.put("truckOwner",beanLst.get(i).getTruckOwner());
//				beanMap.put("truckOwnerName",beanLst.get(i).getTruckOwner());
//				beanMap.put("truckBrand",beanLst.get(i).getTruckBrand());
//				beanMap.put("truckName",beanLst.get(i).getTruckName());
//				beanMap.put("contactNumber",beanLst.get(i).getContactNumber());
//				beanMap.put("truckType",beanLst.get(i).getTruckType());
//				beanMap.put("driver",commonTransMethod.getDriverName(beanLst.get(i).getDriver()+ "") );
//				beanMap.put("truckColor",beanLst.get(i).getTruckColor());
//				beanMap.put("truckLength",beanLst.get(i).getTruckLength());
//				beanMap.put("truckWidth",beanLst.get(i).getTruckWidth());
//				beanMap.put("truckHeight",beanLst.get(i).getTruckHeight());
//				beanMap.put("standardWeight",beanLst.get(i).getStandardWeight());
//				beanMap.put("driverLicense",beanLst.get(i).getDriverLicense());
//				beanMap.put("engineNumber",beanLst.get(i).getEngineNumber());
//				beanMap.put("madeDate",beanLst.get(i).getMadeDate());
//				beanMap.put("buyDate",beanLst.get(i).getBuyDate());
//				beanMap.put("worth",beanLst.get(i).getWorth());
//				beanMap.put("buyCost",beanLst.get(i).getBuyCost());
//				beanMap.put("remark",beanLst.get(i).getRemark());
//				beanMap.put("createDate",beanLst.get(i).getCreateTime());
//				beanMap.put("createTime",beanLst.get(i).getCreateTime());
//				beanMap.put("commercialDate",ConvertService.getWarn(beanLst.get(i).getCommercialDate(),"未提交"));
//				beanMap.put("compulsoryDate",ConvertService.getWarn(beanLst.get(i).getCompulsoryDate(),"未提交"));
//				result.put(beanLst.get(i).getId(), beanMap);
//			}
//			retResult.put("truck",result);
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
	
	@SuppressWarnings("unchecked")
	public Map getTruckBy(){
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

//		HttpServletRequest request =   ServletActionContext.getRequest();
//		truckNumber = Util.null2String(request.getParameter("truckNumber"));
//		truckBrand = Util.null2String(request.getParameter("truckBrand"));
//		driver = ConvertService.getIntValue((request.getParameter("driver")), 0);
//		engineNumber = Util.null2String(request.getParameter("engineNumber"));
//		buyStartDate = Util.null2String(request.getParameter("buyStartDate"));
//		buyEndDate = Util.null2String(request.getParameter("buyEndDate"));
//		TruckBean truckBean = new TruckBean(truckNumber,truckBrand,driver,engineNumber,buyStartDate,buyEndDate,pageSize,currentPage);
//		HttpServletResponse response =   ServletActionContext.getResponse();
//		List<TruckBean> beanLst= truckService.getTruckInfo(truckBean);
//		int pageCount = 0;
//		if("1".equals(currentPage)){
//			String count = truckService.getTruckInfoCount(truckBean);
//			pageCount = Integer.parseInt(count)%Integer.parseInt(pageSize) == 0?(Integer.parseInt(count)/Integer.parseInt(pageSize)):(Integer.parseInt(count)/Integer.parseInt(pageSize) + 1);
//		}
//		 //获取输出流，然后使用
//        PrintWriter out = null;
//		try {
//			Map result = new HashMap();
//			Map retResult = new HashMap();
//			Map beanMap = null;
//			for(int i = 0 ; i<beanLst.size(); i++){
//				beanMap = new HashMap();
//				beanMap.put("id",beanLst.get(i).getId());
//				beanMap.put("truckNumber",beanLst.get(i).getTruckNumber());
//				beanMap.put("truckOwner",beanLst.get(i).getTruckOwner());
//				beanMap.put("truckOwnerName",beanLst.get(i).getTruckOwner());
//				beanMap.put("truckBrand",beanLst.get(i).getTruckBrand());
//				beanMap.put("truckName",beanLst.get(i).getTruckName());
//				beanMap.put("contactNumber",beanLst.get(i).getContactNumber());
//				beanMap.put("truckType",beanLst.get(i).getTruckType());
//				beanMap.put("driver",commonTransMethod.getDriverName(beanLst.get(i).getDriver()+ "") );
//				beanMap.put("truckColor",beanLst.get(i).getTruckColor());
//				beanMap.put("truckLength",beanLst.get(i).getTruckLength());
//				beanMap.put("truckWidth",beanLst.get(i).getTruckWidth());
//				beanMap.put("truckHeight",beanLst.get(i).getTruckHeight());
//				beanMap.put("standardWeight",beanLst.get(i).getStandardWeight());
//				beanMap.put("driverLicense",beanLst.get(i).getDriverLicense());
//				beanMap.put("engineNumber",beanLst.get(i).getEngineNumber());
//				beanMap.put("madeDate",beanLst.get(i).getMadeDate());
//				beanMap.put("buyDate",beanLst.get(i).getBuyDate());
//				beanMap.put("worth",beanLst.get(i).getWorth());
//				beanMap.put("buyCost",beanLst.get(i).getBuyCost());
//				beanMap.put("remark",beanLst.get(i).getRemark());
//				beanMap.put("createDate",beanLst.get(i).getCreateDate());
//				beanMap.put("createTime",beanLst.get(i).getCreateTime());
//				beanMap.put("commercialDate",ConvertService.getWarn(beanLst.get(i).getCommercialDate(),"未提交"));
//				beanMap.put("compulsoryDate",ConvertService.getWarn(beanLst.get(i).getCompulsoryDate(),"未提交"));
//				result.put(beanLst.get(i).getId(), beanMap);
//			}
//			retResult.put("truck",result);
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
	
	@SuppressWarnings("unchecked")
	public Map getTruckById(){
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
//		HttpServletRequest request =   ServletActionContext.getRequest();
//		id = Util.getIntValue(request.getParameter("id"),0);
//		HttpServletResponse response =   ServletActionContext.getResponse();
//		TruckBean truckBean= truckService.getTruckInfo(id+"");
//		 //获取输出流，然后使用
//        PrintWriter out = null;
//		try {
//			Map result = new HashMap();
//			Map retResult = new HashMap();
//			Map beanMap = null;
//			if(truckBean != null){
//				beanMap = new HashMap();
//				beanMap.put("id",truckBean.getId());
//				beanMap.put("truckNumber",truckBean.getTruckNumber());
//				beanMap.put("truckOwner",truckBean.getTruckOwner());
//				beanMap.put("truckBrand",truckBean.getTruckBrand());
//				beanMap.put("truckName",truckBean.getTruckName());
//				beanMap.put("contactNumber",truckBean.getContactNumber());
//				beanMap.put("truckType",truckBean.getTruckType());
//				beanMap.put("driver",truckBean.getDriver()+ "" );
//				beanMap.put("truckColor",truckBean.getTruckColor());
//				beanMap.put("truckLength",truckBean.getTruckLength());
//				beanMap.put("truckWidth",truckBean.getTruckWidth());
//				beanMap.put("truckHeight",truckBean.getTruckHeight());
//				beanMap.put("standardWeight",truckBean.getStandardWeight());
//				beanMap.put("driverLicense",truckBean.getDriverLicense());
//				beanMap.put("engineNumber",truckBean.getEngineNumber());
//				beanMap.put("madeDate",truckBean.getMadeDate());
//				beanMap.put("buyDate",truckBean.getBuyDate());
//				beanMap.put("worth",truckBean.getWorth());
//				beanMap.put("buyCost",truckBean.getBuyCost());
//				beanMap.put("remark",truckBean.getRemark());
//				beanMap.put("createDate",truckBean.getCreateTime());
//				beanMap.put("createTime",truckBean.getCreateTime());
//			}
//			retResult.put("truck",beanMap);
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


	public Map updateTruck(){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
//			apidatas.putAll(clientService.getClient(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
			CacheManager.clearOnly("driverEntity_CACHE");
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;

//		TruckBean truckBean = new TruckBean(id,truckNumber,truckOwner,truckBrand,truckName,contactNumber,truckType,driver,truckColor,trucklength,truckWidth,truckHeight,standardWeight,driverLicense,engineNumber,madeDate,buyDate,worth,buyCost,remark);
//		int maxId = truckService.updateTruck(truckBean);
//		this.status = maxId > 0?true:false;
//		CacheManager.clearOnly("driverBean_CACHE");
//		return "success";
	}
	
	public Map deleteTruck(){
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
//		String ids = request.getParameter("deleteTrucks");
//		int count = truckService.deleteTruck(ids);
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
//		CacheManager.clearOnly("truckBean_CACHE");
//		return null;
	}

}

