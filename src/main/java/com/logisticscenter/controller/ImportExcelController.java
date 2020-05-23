package com.logisticscenter.controller;

import com.common.CommonTransMethod;
import com.common.ConvertService;
import com.javabean.FeeTypeBean;
import com.javabean.FeeTypeValueBean;
import com.javabean.TruckGoodsReportBean;
import com.javabean.TruckGoodsReportDetailBean;
import com.logisticscenter.service.FeeTypeService;
import com.logisticscenter.service.TruckGoodsReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@SuppressWarnings("unchecked")
	public Map importExcel(HttpServletRequest request){
		List<FeeTypeBean> feeTypeBean= feeTypeService.getAllFeeType();

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
				"<td>"+getCount(beanLst.get(i).getRealCarry(),beanLst.get(i).getPrice())+"</td>"+
				"<td>"+commonTransMethod.getClientName(beanLst.get(i).getClient()+"")+"</td>"+
				"<td>"+beanLst.get(i).getTruckNumber()+"</td>";
				for(int j = 0 ; i<feeTypeBean.size(); j++){
					innerTable +="<td>"+getColumn(feeTypeBean.get(i).getId()+"",feeTypeBean.get(i).getFeeTypeColumn())+"</td>";
					
				}
				innerTable +="</tr>";
			}
			retResult.put("truckGoodsReportBody",innerTable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//返回json对象
		
		return retResult;
	}
	
	/**
	 * @param carry 载重
	 * @param price 单价
	 * @return 出车费用
	 */
	public String getCount(BigDecimal carry,BigDecimal price){
		
		String count="异常";
		try{
			count = carry.multiply(price).doubleValue() +"";
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
		
	}
	
	public Map getColumn(String reportId,String columnName){
		String columnValue = "";
		Map feeTypeValue = new HashMap();
		FeeTypeValueBean typeBean = new FeeTypeValueBean();
		List<FeeTypeValueBean> feeTypeValueList = new ArrayList<FeeTypeValueBean>();
		feeTypeValueList = truckGoodsReportService.getColumnValue(reportId,columnName);
		for(int i=0;i<feeTypeValueList.size();i++){
			typeBean = feeTypeValueList.get(i);
			feeTypeValue.put(typeBean.getFeeType(), typeBean.getFeeTypeValue());
		}
		return feeTypeValue;
		
	}
	
	public String getFeeTypeTitle(String id){
		//获得费用的title
		List<FeeTypeBean> beanLst= feeTypeService.getAllFeeType();
		String feeTypeTitle = "";
		String feeName = "";
		String columnName="";
		String br = "\n";
		if(beanLst!=null){
			String feeTypeColumns = "";
			for(int i = 0 ; i<beanLst.size(); i++){
				if(beanLst.get(i).getIsUse() == 0 || beanLst.get(i).getShowType() == 3) continue;
				feeTypeColumns+=","+beanLst.get(i).getFeeTypeColumn();
			}
			if(feeTypeColumns.length()>0){
				feeTypeColumns = feeTypeColumns.substring(1);
			}else{
				return "";
			}
			Map<String,String> feeTypeValue = getColumn(id,feeTypeColumns);
			for(int i = 0 ; i<beanLst.size(); i++){
				if(beanLst.get(i).getIsUse() == 0 || beanLst.get(i).getShowType() == 3) continue;
				feeName = beanLst.get(i).getFeeName();
				columnName = beanLst.get(i).getFeeTypeColumn();
				feeTypeColumns+=beanLst.get(i).getFeeTypeColumn();
				feeTypeTitle+=feeName+" : "+ConvertService.null2String((String)feeTypeValue.get(columnName),"0")+br;
			}
		}
		return feeTypeTitle;
	}
	
	public String getAllFee(String id){
		//获得费用的title
		List<FeeTypeBean> beanLst= feeTypeService.getAllFeeType();
		float allFee = 0F;
		if(beanLst!=null){
			String feeTypeColumns = "";
			for(int i = 0 ; i<beanLst.size(); i++){
				//不启用并且类型为显示的不计算
				if(beanLst.get(i).getIsUse() == 0 || beanLst.get(i).getShowType() == 3) continue;
				feeTypeColumns+=","+beanLst.get(i).getFeeTypeColumn();
			}
			if(feeTypeColumns.length()>0){
				feeTypeColumns = feeTypeColumns.substring(1);
			}else{
				return "";
			}
			Map<String,String> feeTypeValue = getColumn(id,feeTypeColumns);
			for(String key: feeTypeValue.keySet()){
				allFee+=ConvertService.getFloatValue(feeTypeValue.get(key),0f);
			}
			
		}
		return ConvertService.getPointValue(allFee+"");
	}
	
	/**
	 * @param id
	 * @return
	 */
	public String getAllCount(String id){
		float allCount = 0F;
		//设置订单中货物类型详细信息
		TruckGoodsReportDetailBean selectInfo = new TruckGoodsReportDetailBean();
		selectInfo.setTruckOrder(Integer.parseInt(id));
		//TruckGoodsOrderDetailBean中需要新建一个select用goodsType,selectGoodsTypes字段用于检索
		List<TruckGoodsReportDetailBean> detailLst = truckGoodsReportService.getTruckGoodsReportDetail(selectInfo);
		//查询出车辆详细信息
		for(int i = 0 ; i<detailLst.size(); i++){
			allCount += detailLst.get(i).getRealCarry().multiply(detailLst.get(i).getPrice()).doubleValue();
		}
		return ConvertService.getPointValue(allCount+"");
	}
	
}
