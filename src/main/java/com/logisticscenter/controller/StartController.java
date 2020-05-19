package com.logisticscenter.controller;

import com.cache.CacheManager;
import com.javabean.TruckBean;
import com.logisticscenter.service.ChartsService;
import com.util.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @卜伟领 2017
 *
 */
@Controller
@RequestMapping
public class StartController implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public StartController(){
		
	}

//	@RequestMapping(value = "/index" , method = RequestMethod.GET)
//	public String index(){
//		return "index";
//	}

	@RequestMapping(value = "/error" , method = RequestMethod.GET)
	public String error(){
		return "index";
	}


	
}
