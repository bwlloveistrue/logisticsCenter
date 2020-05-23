package com.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.logisticscenter.service.TruckGoodsReportService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.common.CommonTransMethod;
import com.common.ConvertService;
import com.javabean.TruckGoodsReportBean;
import com.javabean.TruckGoodsReportDetailBean;
import com.util.ConstantUtils;

public class ReadExcel {
	@Autowired
	private TruckGoodsReportService truckGoodsReportService;

	private CommonTransMethod commonTransMethod;
	
	public void init(){
		ApplicationContext context1 = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext_iBatis.xml" });
		truckGoodsReportService = (TruckGoodsReportService) context1.getBean("truckGoodsReportService");
	}
	@SuppressWarnings("deprecation")
	public String readExcel(String pathname, PrintWriter out) {
		String retStr = "";
		try {
			init();
			// 获得费用类型的columns
			String[] feeNames = ConstantUtils.FEE_TYPE_NAMES.split(",");
			String[] feeIds = ConstantUtils.FEE_TYPE_COLUMNS.split(",");
 			List<String> columnNameList = new ArrayList<String>();
			List<String> columnIdList = new ArrayList<String>();
			Map<String,String> feeColumnMap = new HashMap<String,String>();
			columnNameList.add("出发日期");
			columnIdList.add("beginDate");
			columnNameList.add("车号");
			columnIdList.add("truckNumber");
			columnNameList.add("客户");
			columnIdList.add("client");
			columnNameList.add("司机");
			columnIdList.add("driver");
			columnNameList.add("包车");
			columnIdList.add("packageFlg");
			columnNameList.add("包车价格");
			columnIdList.add("packagePrice");
			columnNameList.add("发车状态");
			columnIdList.add("truckPart");
			columnNameList.add("伙伴");
			columnIdList.add("partner");
			columnNameList.add("伙伴费用");
			columnIdList.add("partnerPrice");
			columnNameList.add("货物类型");
			columnIdList.add("goodsType");
			columnNameList.add("始发地");
			columnIdList.add("startPlace");
			columnNameList.add("目的地");
			columnIdList.add("endPlace");
			columnNameList.add("开票");
			columnIdList.add("invoiceFlg");
			columnNameList.add("单价");
			columnIdList.add("price");
			columnNameList.add("重量");
			columnIdList.add("realCarry");
			// 从静态变量中获取费用名称
			for (int i = 0; i < feeNames.length; i++) {
				if (!feeNames.equals("")) {
					columnNameList.add(feeNames[i]);
					columnIdList.add(feeIds[i]);
					feeColumnMap.put(feeIds[i], feeNames[i]);
				}
			}
			columnNameList.add("仓库费用");
			columnIdList.add("liftingCost");
			columnNameList.add("备注");
			columnIdList.add("remark");
			// 创建对Excel工作簿文件的引用?
			HSSFWorkbook wookbook = new HSSFWorkbook(new POIFSFileSystem(
					new FileInputStream(pathname)));
			// 在Excel文档中，第一张工作表的缺省索引是0
			// 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);?
			HSSFSheet sheet = wookbook.getSheetAt(0);
			// 获取到Excel文件中的所有行数?
			int rows = sheet.getPhysicalNumberOfRows();
			Map<Integer, String> relationColumn = new HashMap<Integer, String>();
			for (int i = 0; i < rows; i++) {
				// 读取左上端单元格
				HSSFRow row = sheet.getRow(i);
				Map<String, Object> relationColumnValue = new HashMap<String, Object>();
				if (i == 0) {
					int index = 0;
					for (short j = 0; j < row.getLastCellNum(); j++) {
						HSSFCell cell = row.getCell(j);
						if (columnNameList.contains(cell.getStringCellValue())) {
							index = columnNameList.indexOf(cell
									.getStringCellValue());
							relationColumn.put(j + 0, columnIdList.get(index));
						}
					}
				} else {
					if (row != null) {
						for (short j = 0; j < row.getLastCellNum(); j++) {
							HSSFCell cell = row.getCell(j);
							getTypeValue(cell, j + 0, relationColumn,
									relationColumnValue);
						}
					}
					TruckGoodsReportBean reprotBean = new TruckGoodsReportBean();
					setReportValue(reprotBean,relationColumnValue,feeColumnMap);
					try{
						int maxId = truckGoodsReportService.insertTruckGoodsReport(reprotBean);
						truckGoodsReportService.updateColumn(maxId,reprotBean.getFeeTypecolumnSqlUpd());
						TruckGoodsReportDetailBean reprotDetailBean = new TruckGoodsReportDetailBean();
						reprotDetailBean.setTruckOrder(maxId);
						setReportDetailValue(reprotDetailBean,relationColumnValue,feeColumnMap);
						truckGoodsReportService.insertTruckGoodsReportDetail(reprotDetailBean);
					}catch(Exception e){
						e.printStackTrace();
						retStr += "第"+i+"行数据插入错误,请将第"+i+"行数据重新导入,成功数据请删除,避免重复导入!<br>";
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retStr;
	}

	public void getTypeValue(HSSFCell cell, int index,
			Map<Integer, String> relationColumn, Map<String, Object> objMap) {
		if(relationColumn.containsKey(index)){
			String column = relationColumn.get(index);
			if (cell != null) {
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) { // 判断单元格的值是否为字符串类型
					objMap.put(column, cell.getStringCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) { // 判断单元格的值是否为数字类型
																				// (日期类型也在其中)
					// 判断是否为日期类型
					if (DateUtil.isCellDateFormatted(cell)) {
						// 用于转化为日期格式
						Date d = cell.getDateCellValue();
						DateFormat formater = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm");
						objMap.put(column, formater.format(d));
					} else {
						// 用于格式化数字，精确到小数点后两位数
						DecimalFormat df = new DecimalFormat("########.##");
						objMap.put(column, df.format(cell.getNumericCellValue()));
					}
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) { // 判断单元格的值是否为布尔类型
					objMap.put(column, cell.getStringCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
					;
				}
			}
		}
	}
	
	/**
	 * @param reprotBean
	 * @param map1 对应关系集合
	 * @param map2 费用键值对集合
	 */
	public void setReportValue(TruckGoodsReportBean reprotBean,Map<String, Object> map1,Map<String,String> map2) {
		reprotBean.setBeginDate(ConvertService.null2String(map1.get("beginDate")+""));
		//判断是否存在该客户类型,没有则新建一个
		String clientName = ConvertService.null2String((String)map1.get("client"));
		int clinetId = commonTransMethod.getClientId(clientName);
		if(clinetId <= 0){
			clinetId = commonTransMethod.createClient(clientName);
		}
		reprotBean.setClient(clinetId);
		//判断是否存在该司机,没有则新建一个
		String driverName = ConvertService.null2String((String)map1.get("driver"));
		int driverId = commonTransMethod.getDriverId(driverName);
		if(driverId <= 0){
			driverId = commonTransMethod.createDriver(driverName);
		}
		reprotBean.setDriver(driverId);
		//
		String goodsTypeIds="";
		String tempGoodsType = "";
		String goodsTypes = ConvertService.null2String((String)map1.get("goodsType"));
		String arr1[] = goodsTypes.split(",");
		for(int i=0;i<arr1.length;i++){
			tempGoodsType = commonTransMethod.getGoodsTypeIds(arr1[i]);
			if(tempGoodsType.equals("")){
				goodsTypeIds +=","+commonTransMethod.createGoodsType(arr1[i]);
			}else{
				goodsTypeIds +=","+tempGoodsType;
			}
		}
		if(!goodsTypeIds.equals("")){
			goodsTypeIds = goodsTypeIds.substring(1);
		}
		reprotBean.setGoodsType(goodsTypeIds);
		reprotBean.setTruckPart(reprotBean.getDriver()>0?0:1);
		//
		String truckNumber = ConvertService.null2String((String)map1.get("truckNumber"));
		int truckId = commonTransMethod.getTruckId(truckNumber);
		if(truckId <= 0){
			truckId = commonTransMethod.createTruck(truckNumber);
		}
		reprotBean.setTruckNumber(truckId+"");
		reprotBean.setReportStatus(2);
		reprotBean.setRealCarry(ConvertService.getDecimalValue(map1.get("realCarry")+"",ConstantUtils.defaultDecimal));
		reprotBean.setPrice(ConvertService.getDecimalValue(map1.get("price")+"",ConstantUtils.defaultDecimal));
		reprotBean.setRemark(ConvertService.null2String(map1.get("remark")+""));
		reprotBean.setInvoiceFlg(commonTransMethod.convertFlg(ConvertService.null2String(map1.get("invoiceFlg")+""))+"");
		reprotBean.setPackagePrice(ConvertService.getDecimalValue(map1.get("packagePrice")+"",ConstantUtils.defaultDecimal));
		reprotBean.setPackageFlg(reprotBean.getPackagePrice().compareTo(ConstantUtils.defaultDecimal)==0?"0":"1");
		reprotBean.setPartner("");
		reprotBean.setPartnerCarry("");
		reprotBean.setPartnerPrice("");
		//拼接更新费用的sql
		String columnId = "";
		String columnName = "";
		String feeTypecolumnSqlUpd = "";
		StringBuffer updateSqlSb = new StringBuffer(); 
		for(String key : map2.keySet()){
			columnId = key;
			columnName = ConvertService.null2String(ConvertService.null2String(map1.get(key)+""));
			if(columnName.equals("")){
				columnName = "''";
			}
			updateSqlSb.append(columnId).append("=").append(columnName).append(",");
		}
		feeTypecolumnSqlUpd = updateSqlSb.toString();
		if(feeTypecolumnSqlUpd.endsWith(",")){
			feeTypecolumnSqlUpd = feeTypecolumnSqlUpd.substring(0,feeTypecolumnSqlUpd.length()-1);
		}
		reprotBean.setFeeTypecolumnSqlUpd(feeTypecolumnSqlUpd);
	}
	
	/**
	 * @param reprotBean
	 * @param map1 对应关系集合
	 * @param map2 费用键值对集合
	 */
	public void setReportDetailValue(TruckGoodsReportDetailBean reprotDetailBean,Map<String, Object> map1,Map<String,String> map2) {
		reprotDetailBean.setGoodsType(commonTransMethod.getGoodsTypeIds(ConvertService.null2String(map1.get("goodsType")+""))+"");
		reprotDetailBean.setRealCarry(ConvertService.getDecimalValue(map1.get("realCarry")+"",ConstantUtils.defaultDecimal));
		reprotDetailBean.setPrice(ConvertService.getDecimalValue(map1.get("price")+"",ConstantUtils.defaultDecimal));
		reprotDetailBean.setLiftingCost(ConvertService.getDecimalValue(map1.get("liftingCost")+"",ConstantUtils.defaultDecimal));
		reprotDetailBean.setStartPlace(ConvertService.null2String(map1.get("startPlace")+""));
		reprotDetailBean.setEndPlace(ConvertService.null2String(map1.get("endPlace")+""));
		reprotDetailBean.setInvoiceFlg(commonTransMethod.convertFlg(ConvertService.null2String(map1.get("invoiceFlg")+"")));
	}

}