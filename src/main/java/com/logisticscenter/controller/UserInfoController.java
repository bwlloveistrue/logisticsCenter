package com.logisticscenter.controller;

import com.cache.CacheManager;
import com.javabean.ClientBean;
import com.logisticscenter.service.ClientService;
import com.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(value = "/api/userInfo")
public class UserInfoController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserInfoController(){
		
	}

	@Autowired
	private ClientService clientService;


	@ResponseBody
	@PostMapping("/selectAllClient")
	public Map selectAllClient(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(clientService.getClient(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
//		HttpServletResponse response =   ServletActionContext.getResponse();
//		List<ClientBean> beanLst = null;
//		beanLst= clientService.getAllClient();
//		 //获取输出流，然后使用
//        PrintWriter out = null;
//		try {
//			Map result = new HashMap();
//			Map retResult = new HashMap();
//			Map beanMap = null;
//			for(int i = 0 ; i<beanLst.size(); i++){
//				beanMap = new HashMap();
//				beanMap.put("id",beanLst.get(i).getId());
//				beanMap.put("clientName",beanLst.get(i).getClientName());
//				beanMap.put("contant",beanLst.get(i).getContant());
//				beanMap.put("mobile",beanLst.get(i).getMobile());
//				beanMap.put("fax",beanLst.get(i).getFax());
//				beanMap.put("address",beanLst.get(i).getAddress());
//				beanMap.put("products",beanLst.get(i).getProducts());
//				beanMap.put("createDate",beanLst.get(i).getCreateDate());
//				beanMap.put("createTime",beanLst.get(i).getCreateTime());
//				result.put(beanLst.get(i).getId(), beanMap);
//			}
//			retResult.put("client",result);
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
	@PostMapping("/addClient")
	public Map addClient(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(clientService.insertClient(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;

//		ClientBean bean = new ClientBean(id,clientName,contant,mobile,fax,address,products);
//		int maxId = clientService.insertClient(bean);
//		id = maxId;
//		this.status = maxId > 0?true:false;
//		CacheManager.clearOnly("clientBean_CACHE");
//		return "success";
	}

	public Map addClient1(){
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
//		ClientBean bean = new ClientBean(id,clientName,contant,mobile,fax,address,products);
//		int maxId = clientService.insertClient(bean);
//		this.status = maxId > 0?true:false;
//		id = maxId;
//		CacheManager.clearOnly("clientBean_CACHE");
//		return "success";
	}

	@ResponseBody
	@PostMapping("/updateClient")
	public Map updateClient(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(clientService.updateClient(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
//		ClientBean bean = new ClientBean(id,clientName,contant,mobile,fax,address,products);
//		int count = clientService.updateClient(bean);
//		this.status = count > 0?true:false;
//		CacheManager.clearOnly("clientBean_CACHE");
//		return "success";
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectClientByName(){

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


//		selectClientName = request.getParameter("selectClientName");
//		checkStyle = request.getParameter("checkStyle");
//		ClientBean infoBean = new ClientBean(selectClientName,pageSize,currentPage);
//		List<ClientBean> beanLst= clientService.getClient(infoBean,checkStyle);
//		int pageCount = 0;
//		if("1".equals(currentPage)){
//			String count = clientService.getClientCount(infoBean,checkStyle);
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
//				beanMap.put("clientName",beanLst.get(i).getClientName());
//				beanMap.put("contant",beanLst.get(i).getContant());
//				beanMap.put("mobile",beanLst.get(i).getMobile());
//				beanMap.put("fax",beanLst.get(i).getFax());
//				beanMap.put("address",beanLst.get(i).getAddress());
//				beanMap.put("products",beanLst.get(i).getProducts());
//				beanMap.put("createDate",beanLst.get(i).getCreateDate());
//				beanMap.put("createTime",beanLst.get(i).getCreateTime());
//				result.put(beanLst.get(i).getId(), beanMap);
//			}
//			retResult.put("client",result);
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
	public Map selectClientById(){
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

//		String idStr = request.getParameter("id");
//		ClientBean clientBean = clientService.getClient(idStr);
//		//获取输出流，然后使用
//        PrintWriter out = null;
//		try {
//			Map retResult = new HashMap();
//			Map beanMap = null;
//			if(clientBean != null){
//				beanMap = new HashMap();
//				beanMap.put("id",clientBean.getId());
//				beanMap.put("clientName",clientBean.getClientName());
//				beanMap.put("contant",clientBean.getContant());
//				beanMap.put("mobile",clientBean.getMobile());
//				beanMap.put("fax",clientBean.getFax());
//				beanMap.put("address",clientBean.getAddress());
//				beanMap.put("products",clientBean.getProducts());
//				beanMap.put("createDate",clientBean.getCreateDate());
//				beanMap.put("createTime",clientBean.getCreateTime());
//			}
//			retResult.put("client",beanMap);
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
	@PostMapping("/deleteClient")
	public Map deleteClient(HttpServletRequest request){
		Map<String, Object> apidatas = new HashMap<String, Object>();
		try {
			apidatas.putAll(clientService.deleteClient(ParamUtil.request2Map(request)));
			apidatas.put("api_status", true);
		} catch (Exception e) {
			e.printStackTrace();
			apidatas.put("api_status", false);
			apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
		}
		return apidatas;
//		String ids = request.getParameter("deleteClients");
//		int count = clientService.deleteClient(ids);
//		//获取输出流，然后使用
//        PrintWriter out = null;
//		try {
//			Map retResult = new HashMap();
//			if(count>0){
//				retResult.put("result","1");
//				CacheManager.clearOnly("clientBean");
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
//		CacheManager.clearOnly("clientBean_CACHE");
//		return null;
	}
	

}
