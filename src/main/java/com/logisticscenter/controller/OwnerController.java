package com.logisticscenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/owner")
public class OwnerController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OwnerController(){
		
	}
	
	@SuppressWarnings("unchecked")
	public Map selectTruckOwner(HttpServletResponse response){
		Map result = new HashMap();
		try {

			Map ownerMap = new HashMap();
			ownerMap.put("1","程1同");
			ownerMap.put("2","程2同");
			ownerMap.put("3","程3同");
			ownerMap.put("4","程4同");
			ownerMap.put("5","程5同");
			ownerMap.put("6","程6同");
			result.put("owner", ownerMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//返回json对象
		return result;
	}

}
