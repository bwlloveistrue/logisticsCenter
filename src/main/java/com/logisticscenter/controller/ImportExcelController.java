package com.logisticscenter.controller;

import com.common.CommonTransMethod;
import com.common.ConvertService;
import com.javabean.FeeTypeBean;
import com.javabean.FeeTypeValueBean;
import com.javabean.TruckGoodsReportBean;
import com.javabean.TruckGoodsReportDetailBean;
import com.logisticscenter.model.FeeTypeEntity;
import com.logisticscenter.model.GoodsTypeEntity;
import com.logisticscenter.service.FeeTypeService;
import com.logisticscenter.service.TruckGoodsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *获得上传文件以及下载文件
 * @卜伟领 2017
 *
 */
@RestController
@RequestMapping(value = "/api/importExcel")
public class ImportExcelController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImportExcelController(){
		
	}

	@Autowired
	private TruckGoodsReportService truckGoodsReportService;

	//获得费用类型字段用
	@Autowired
	private FeeTypeService feeTypeService;

	@Autowired
	private CommonTransMethod commonTransMethod;

	@GetMapping
	public Map importExcel(HttpServletRequest request){
		Map beanMap= feeTypeService.getAllFeeType();
		List<FeeTypeEntity> feeTypeBean = (List<FeeTypeEntity>)beanMap.get("feeTypeInfo");
		String truckNumber = ConvertService.null2String(request.getParameter("truckNumber"));
		int reportStatus = ConvertService.getIntValue(request.getParameter("reportStatus"),-1);
		String startPlace = ConvertService.null2String(request.getParameter("startPlace"));
		String endPlace = ConvertService.null2String(request.getParameter("endPlace"));
		String beginDate = ConvertService.null2String(request.getParameter("beginDate"));
		String endDate = ConvertService.null2String(request.getParameter("endDate"));
		int driver = ConvertService.getIntValue(request.getParameter("driver"),-1);
		int client = ConvertService.getIntValue(request.getParameter("client"),-1);
		String goodsType = ConvertService.null2String(request.getParameter("goodsType"),"0");
		String carryNumber = ConvertService.null2String(request.getParameter("carryNumber"));
		
		TruckGoodsReportBean infoBean = new TruckGoodsReportBean( reportStatus,truckNumber, startPlace, endPlace, beginDate,endDate, driver, client, goodsType, carryNumber,"","");
		List<TruckGoodsReportBean> beanLst= truckGoodsReportService.getTruckGoodsReport(infoBean);
		String innerTable = "";
		innerTable +="<tr>"+
		"<th>日期</th>"+
		"<th>品名</th>"+
		"<th>装点</th>"+
		"<th>卸点</th>"+
		"<th>开票</th>"+
		"<th>费用</th>"+
		"<th>重量</th>"+
		"<th>单价</th>"+
		"<th>运费</th>"+
		"<th>货主</th>"+
		"<th>车号</th>"+
		"<th>发货</th>";
		for(int i = 0 ; i<feeTypeBean.size(); i++){
			innerTable +="<th>"+feeTypeBean.get(i).getFeeName()+"</th>";
			
		}
		innerTable +="</tr>";
		Map retResult = new HashMap();
		try {
			List result = new ArrayList();

			for(int i = 0 ; i<beanLst.size(); i++){
				innerTable +="<tr>"+
				"<td>"+beanLst.get(i).getBeginDate()+"</td>"+
				"<td>"+commonTransMethod.getGoodsTypeName(beanLst.get(i).getGoodsType()+"")+"</td>"+
				"<td>"+beanLst.get(i).getStartPlace()+"</td>"+
				"<td>"+beanLst.get(i).getEndDate()+"</td>"+
				"<td>"+beanLst.get(i).getBeginDate()+"</td>"+
				"<td>"+beanLst.get(i).getInvoiceFlg()+"</td>"+
				"<td>"+beanLst.get(i).getRealCarry()+"</td>"+
				"<td>"+beanLst.get(i).getPrice()+"</td>"+
				"<td>"+commonTransMethod.getClientName(beanLst.get(i).getClient()+"")+"</td>"+
				"<td>"+beanLst.get(i).getTruckNumber()+"</td>";
				innerTable +="</tr>";
			}
			retResult.put("truckGoodsReportBody",innerTable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//返回json对象
		
		return retResult;
	}
	
}
