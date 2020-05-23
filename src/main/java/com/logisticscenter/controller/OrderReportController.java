package com.logisticscenter.controller;

import com.common.CommonTransMethod;
import com.common.ConvertService;
import com.general.BaseBean;
import com.idgenerate.DefaultIdGenerator;
import com.javabean.*;
import com.logisticscenter.service.FeeTypeService;
import com.logisticscenter.service.ImageFileService;
import com.logisticscenter.service.TruckGoodsOrderService;
import com.logisticscenter.service.TruckGoodsReportService;
import com.util.ConstantUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping(value = "/api/truckGoodsReport")
public class OrderReportController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OrderReportController(){
		
	}

	@Autowired
	private TruckGoodsReportService truckGoodsReportService;
	
	/**
	 * 出车预录信息service
	 */
	@Autowired
	private TruckGoodsOrderService truckGoodsOrderService;

	@Autowired
	private ImageFileService imageFileService;

	@Autowired
	private CommonTransMethod commonTransMethod;


}
