package com.logisticscenter.controller;

import com.cache.CacheManager;
import com.javabean.DriverInfoBean;
import com.logisticscenter.service.DriverService;
import com.util.ParamUtil;
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

/**
 * @卜伟领 2017
 *
 */
@RestController
@RequestMapping(value = "/api/driverInfo")
public class DriverController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DriverController(){
		
	}
	
	private DriverService driverService;

	@ResponseBody
	@PostMapping("/selectDriver")
	public Map selectDriver(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(driverService.getDriverInfo(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;

//		HttpServletResponse response =   ServletActionContext.getResponse();
//		DriverInfoBean infoBean = new DriverInfoBean(id,selectDriverName,pageSize,currentPage);
//		List<DriverInfoBean> beanLst = null;
//		beanLst= driverService.getDriverInfo(infoBean);
//		//systemBean = loginService.getSystemInfo(loginid);
//		 //获取输出流，然后使用
//        PrintWriter out = null;
//		try {
//			Map result = new HashMap();
//			Map retResult = new HashMap();
//			Map beanMap = null;
//			for(int i = 0 ; i<beanLst.size(); i++){
//				beanMap = new HashMap();
//				beanMap.put("id",beanLst.get(i).getId());
//				beanMap.put("driverName",beanLst.get(i).getName());
//				beanMap.put("sex",beanLst.get(i).getSex());
//				beanMap.put("nativePlace",beanLst.get(i).getNativePlace());
//				beanMap.put("education",beanLst.get(i).getEducation());
//				beanMap.put("birthday",beanLst.get(i).getBirthday());
//				beanMap.put("age",beanLst.get(i).getAge());
//				beanMap.put("address",beanLst.get(i).getAddress());
//				beanMap.put("contactNmuber",beanLst.get(i).getContactNmuber());
//				beanMap.put("mobile",beanLst.get(i).getMobile());
//				beanMap.put("idNumber",beanLst.get(i).getIdNumber());
//				beanMap.put("startWorkDate",beanLst.get(i).getStartWorkDate());
//				beanMap.put("driverLicense",beanLst.get(i).getDriverLicense());
//				beanMap.put("truckNumber",beanLst.get(i).getTruckNumber());
//				beanMap.put("appraise",beanLst.get(i).getAppraise());
//				beanMap.put("salary",beanLst.get(i).getSalary());
//				beanMap.put("remark",beanLst.get(i).getRemark());
//				beanMap.put("job",beanLst.get(i).getJob());
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
	@PostMapping("/addDriver")
	public Map addDriver(HttpServletRequest request){
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

//		DriverInfoBean bean = new DriverInfoBean(id,name, sex, nativePlace, education, birthday, age, address, contactNmuber, mobile, idNumber, startWorkDate, driverLicense, truckNumber, appraise, salary, remark, job);
//		int maxId = driverService.insertDriverInfo(bean);
//		this.status = maxId > 0?true:false;
//		id = maxId;
//		CacheManager.clearOnly("driverBean_CACHE");
//		return "success";
	}

	@ResponseBody
	@PostMapping("/updateDriver")
	public Map updateDriver(HttpServletRequest request){
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

//		DriverInfoBean bean = new DriverInfoBean(id,name, sex, nativePlace, education, birthday, age, address, contactNmuber, mobile, idNumber, startWorkDate, driverLicense, truckNumber, appraise, salary, remark, job);
//		int maxId = driverService.updateDriverInfo(bean);
//		this.status = maxId > 0?true:false;
//		CacheManager.clearOnly("driverBean_CACHE");
//		return "success";
	}


	@ResponseBody
	@PostMapping("/deleteDriver")
	public Map deleteDriver(HttpServletRequest request){
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
//		String ids = request.getParameter("deleteDrivers");
//		int count = driverService.deleteDriverInfo(ids);
//		//获取输出流，然后使用
//        PrintWriter out = null;
//		try {
//			Map retResult = new HashMap();
//			if(count>0){
//				retResult.put("result","1");
//				CacheManager.clearOnly("driverBean");
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
//		CacheManager.clearOnly("driverBean_CACHE");
//		return null;
	}
	

}
