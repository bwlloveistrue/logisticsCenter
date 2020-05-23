package com.logisticscenter.controller;

import com.asprise.imaging.core.Request;
import com.asprise.imaging.core.Result;

//import com.asprise.imaging.scan.ui.workbench.AspriseScanUI;
import com.common.CommonTransMethod;
import com.common.ConvertService;
import com.general.BaseBean;
import com.javabean.*;
import com.logisticscenter.service.FeeTypeService;
import com.logisticscenter.service.ImageFileService;
import com.logisticscenter.service.TruckGoodsReportService;
import com.util.ConstantUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 *获得上传文件以及下载文件
 * @卜伟领 2017
 *
 */
@RestController
@RequestMapping(value = "/api/imageFile")
public class ImageFileController implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageFileController(){
		
	}

	@Autowired
	private ImageFileService imageFileService;

	@Autowired
	private TruckGoodsReportService truckGoodsReportService;
	
	//获得费用类型字段用
	@Autowired
	private FeeTypeService feeTypeService;

	@Autowired
	private CommonTransMethod commonTransMethod;

	@ResponseBody
	@PostMapping("/getFiles")
	public Map getFiles(HttpServletRequest request){
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
//		String selectFileIds = request.getParameter("selectFileIds");
//		List<ImageFileBean> imageFileList = new ArrayList<ImageFileBean>();
//		imageFileList = imageFileService.getImageFileBy(selectFileIds);
//		PrintWriter out = null;
//		Map result = new HashMap();
//		Map retResult = new HashMap();
//		String retDetail = "";
//		try{
//			for(int i=0;i<imageFileList.size();i++){
//
//			}
//			retResult.put("FileDetail",result);
//			response.setContentType("text/html; charset=utf-8");
//			out = response.getWriter();
////			/* 设置格式为text/json	*/
////			response.setContentType("text/json");
////			/*设置字符集为'UTF-8'*/
////			response.setCharacterEncoding("UTF-8");
//			JSONObject obj = JSONObject.parseObject(retResult.toString());
//			out.print(obj.toString());
//			out.flush();
//		}catch(Exception e){
//
//		}
//		return null;
	}

	@ResponseBody
	@PostMapping("/downloads")
	public String downloads(HttpServletRequest request, HttpServletResponse response){

		String pathId = request.getParameter("id");
		String pathUrl = commonTransMethod.getFullPathName(pathId);
		try {
				File file = new File(pathUrl); 
				String filename = file.getName();
				InputStream fis = new BufferedInputStream(new FileInputStream(file));
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				response.reset();
				// 设置response的Header
				response.addHeader("Content-Length", "" + file.length());
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("image/jpeg");
				toClient.write(buffer);
				toClient.flush();
				toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@ResponseBody
	@PostMapping("/exportExcel")
	public String exportExcel(HttpServletRequest request ,HttpServletResponse response ){
		//获得费用类型的columns
		String[] feeNames = ConstantUtils.FEE_TYPE_NAMES.split(",");
		String[] feeIds = ConstantUtils.FEE_TYPE_COLUMNS.split(",");
		//创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("出车信息");
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1=sheet.createRow(0);
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell=row1.createCell(0);
		//设置单元格内容
		cell.setCellValue("出车信息一览表");
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,21+feeNames.length));
		//在sheet里创建第二行
		HSSFRow row2=sheet.createRow(1);
		List<String> columnNameList = new ArrayList<String>();
		List<String> columnIdList = new ArrayList<String>();
		columnNameList.add("出发日期");
		columnIdList.add("beginDate");
		columnNameList.add("到达日期");
		columnIdList.add("endDate");
		columnNameList.add("出车编号");
		columnIdList.add("reportNumber");
		columnNameList.add("车号");
		columnIdList.add("truckNumber");
		columnNameList.add("客户");
		columnIdList.add("client");
		columnNameList.add("司机");
		columnIdList.add("driver");
		columnNameList.add("包车");
		columnIdList.add("packageFlgShow");
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
		columnNameList.add("运费");
		columnIdList.add("expensens");
		//从静态变量中获取费用名称
		for(int i=0;i<feeNames.length;i++){
			if(!feeNames.equals("")){
				columnNameList.add(feeNames[i]);
				columnIdList.add(feeIds[i]);
			}
		}
		columnNameList.add("仓库费用");
		columnIdList.add("liftingCost");
		columnNameList.add("损耗");
		columnIdList.add("cost");
		columnNameList.add("盈利");
		columnIdList.add("profit");
		columnNameList.add("备注");
		columnIdList.add("remark");
		for(int i=0;i<columnNameList.size();i++){
			row2.createCell(i).setCellValue((columnNameList.get(i)+""));
		}
		//创建详细的行
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
		List<TruckGoodsReportBean> beanLst= truckGoodsReportService.getTruckGoodsReportExcel(infoBean);
		//获取输出流，然后使用  
        PrintWriter out = null;
		try {
			Map beanMap = null;
			for(int i = 0 ; i<beanLst.size(); i++){
				beanMap = new HashMap();
				int packageFlgExchange = Integer.parseInt(beanLst.get(i).getPackageFlg());
				int truckPartExchange = beanLst.get(i).getTruckPart();
				beanMap.put("id",beanLst.get(i).getId());
				beanMap.put("truckNumber",commonTransMethod.getTruckNumber(beanLst.get(i).getTruckNumber()));
				beanMap.put("packageFlg",packageFlgExchange);
				beanMap.put("packageFlgShow",commonTransMethod.convertPackage(packageFlgExchange));
				beanMap.put("packagePrice",ConvertService.getPointValue(beanLst.get(i).getPackagePrice()+"",2));
				beanMap.put("reportNumber",ConvertService.null2String(beanLst.get(i).getReportNumber()));
				beanMap.put("truckPart",commonTransMethod.convertTruckPart(beanLst.get(i).getTruckPart()));
				beanMap.put("partner",beanLst.get(i).getPartner());
				beanMap.put("partnerPrice",ConvertService.getPointValue(beanLst.get(i).getPartnerPrice(),2));
				beanMap.put("partnerCarry",ConvertService.getPointValue(beanLst.get(i).getPartnerCarry(),2));
//				beanMap.put("startPlace",beanLst.get(i).getStartPlace());
//				beanMap.put("endPlace",beanLst.get(i).getEndPlace());
				beanMap.put("beginDate",beanLst.get(i).getBeginDate());
				beanMap.put("expectedDate",beanLst.get(i).getExpectedDate());
				beanMap.put("endDate",beanLst.get(i).getEndDate());
				beanMap.put("driver",commonTransMethod.getDriverName(beanLst.get(i).getDriver()+""));
				beanMap.put("client",commonTransMethod.getClientName(beanLst.get(i).getClient()+""));
//				beanMap.put("goodsType",commonTransMethod.getGoodsTypeName(beanLst.get(i).getGoodsType()+""));
				beanMap.put("prepaidFlg",beanLst.get(i).getPrepaidFlg());
				beanMap.put("prepaidExpress",beanLst.get(i).getPrepaidExpress());
				beanMap.put("prepaidDesc",beanLst.get(i).getPrepaidDesc());
				beanMap.put("reportStatus",beanLst.get(i).getReportStatus());
				beanMap.put("isLater",beanLst.get(i).getIsLater());
				beanMap.put("laterReason",beanLst.get(i).getLaterReason());
//				beanMap.put("realCarry",beanLst.get(i).getRealCarry());
//				beanMap.put("price",beanLst.get(i).getPrice());
				//beanMap.put("expensens",getAllFee(beanLst.get(i).getId()+""));
				beanMap.put("carryNumber",beanLst.get(i).getCarryNumber());
				beanMap.put("workPlace",beanLst.get(i).getWorkPlace());
				beanMap.put("createDate",beanLst.get(i).getCreateDate());
				beanMap.put("createTime",beanLst.get(i).getCreateTime());
				beanMap.put("editDate",beanLst.get(i).getEditDate());
				beanMap.put("editTime",beanLst.get(i).getEditTime());
				beanMap.put("remark",beanLst.get(i).getRemark());
				beanMap.put("expensens",ConvertService.getPointValue(beanLst.get(i).getExpensens()+"",2));
				beanMap.put("cost",ConvertService.getPointValue(beanLst.get(i).getCost()+"",2));
				beanMap.put("profit",ConvertService.getPointValue(beanLst.get(i).getProfit()+"",2));
				
				
				
				
				TruckGoodsReportDetailBean selectInfo = new TruckGoodsReportDetailBean();
				selectInfo.setTruckOrder(beanLst.get(i).getId());
				//TruckGoodsOrderDetailBean中需要新建一个select用goodsType,selectGoodsTypes字段用于检索
				List<TruckGoodsReportDetailBean> detailLst = truckGoodsReportService.getTruckGoodsReportDetail(selectInfo);
				if(detailLst.size()>0){
					//查询出车辆详细信息
					String startPlaceShow = "";
					String endPlaceShow = "";
					String goodsTypeShow = "";
					String invoiceFlgShow = "";
					String priceShow = "";
					String realCarryShow = "";
					String liftingCostShow = "";
					
					for(int j = 0 ; j<detailLst.size(); j++){
						goodsTypeShow +=goodsTypeShow.equals("")?detailLst.get(j).getGoodsType():","+detailLst.get(j).getGoodsType();
						priceShow +=priceShow.equals("")?detailLst.get(j).getPrice():","+detailLst.get(j).getPrice();
						realCarryShow +=realCarryShow.equals("")?detailLst.get(j).getRealCarry()+"":","+detailLst.get(j).getRealCarry();
						invoiceFlgShow +=invoiceFlgShow.equals("")?(detailLst.get(j).getInvoiceFlg()==1?"开票":"否"):","+(detailLst.get(j).getInvoiceFlg()==1?"开票":"否");
						startPlaceShow +=startPlaceShow.equals("")?detailLst.get(j).getStartPlace()+"":","+detailLst.get(j).getStartPlace();
						endPlaceShow +=endPlaceShow.equals("")?detailLst.get(j).getEndPlace()+"":detailLst.get(j).getEndPlace()+"";
						liftingCostShow +=liftingCostShow.equals("")?detailLst.get(j).getLiftingCost()+"":","+detailLst.get(j).getLiftingCost();
					}
					beanMap.put("goodsType", commonTransMethod.getGoodsTypeName(goodsTypeShow));
					beanMap.put("price", priceShow);
					beanMap.put("realCarry", realCarryShow);
					beanMap.put("startPlace", startPlaceShow);
					beanMap.put("endPlace", endPlaceShow);
					beanMap.put("invoiceFlg", invoiceFlgShow);
					beanMap.put("liftingCost", liftingCostShow);
				}
				
				//beanMap.put("feeTypeTitle",getFeeTypeTitle(beanLst.get(i).getId()+""));
				getFeeTypeColumnMap(beanLst.get(i).getId()+"",beanMap);
				HSSFRow row3=sheet.createRow(2+i);
				for(int j=0;j<columnIdList.size();j++){
					row3.createCell(j).setCellValue((beanMap.get(columnIdList.get(j))+""));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	//输出Excel文件
		OutputStream output;
		try {
			output = response.getOutputStream();
			 response.reset();
				response.setHeader("Content-disposition", "attachment; filename=details.xls");
				response.setContentType("application/msexcel");		
				wb.write(output);
				output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 */
	@ResponseBody
	@PostMapping("/mouldExcel")
	public String mouldExcel(HttpServletRequest request ,HttpServletResponse response ){
		//获得费用类型的columns
		String[] feeNames = ConstantUtils.FEE_TYPE_NAMES.split(",");
		String[] feeIds = ConstantUtils.FEE_TYPE_COLUMNS.split(",");
		//创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		//建立新的sheet对象（excel的表单）
		HSSFSheet sheet=wb.createSheet("导入模板");
		//在sheet里创建第二行
		HSSFRow row2=sheet.createRow(1);
		List<String> columnNameList = new ArrayList<String>();
		List<String> columnIdList = new ArrayList<String>();
		columnNameList.add("出发日期");
		columnIdList.add("beginDate");
		columnNameList.add("货物类型");
		columnIdList.add("goodsType");
		columnNameList.add("始发地");
		columnIdList.add("startPlace");
		columnNameList.add("目的地");
		columnIdList.add("endPlace");
		columnNameList.add("开票");
		columnIdList.add("invoiceFlg");
		columnNameList.add("重量");
		columnIdList.add("realCarry");
		columnNameList.add("单价");
		columnIdList.add("price");
		columnNameList.add("客户");
		columnIdList.add("client");
		columnNameList.add("司机");
		columnIdList.add("driver");
		columnNameList.add("车号");
		columnIdList.add("truckNumber");
		columnNameList.add("包车");
		columnIdList.add("packageFlgShow");
		columnNameList.add("包车价格");
		columnIdList.add("packagePrice");
		columnNameList.add("发车状态");
		columnIdList.add("truckPart");
		columnNameList.add("伙伴");
		columnIdList.add("partner");
		//从静态变量中获取费用名称
		for(int i=0;i<feeNames.length;i++){
			if(!feeNames.equals("")){
				columnNameList.add(feeNames[i]);
				columnIdList.add(feeIds[i]);
			}
		}
		columnNameList.add("仓库费用");
		columnIdList.add("liftingCost");
		columnNameList.add("备注");
		columnIdList.add("remark");
		for(int i=0;i<columnNameList.size();i++){
			row2.createCell(i).setCellValue((columnNameList.get(i)+""));
		}

	//输出Excel文件
		OutputStream output;
		try {
			output = response.getOutputStream();
			 response.reset();
				response.setHeader("Content-disposition", "attachment; filename=mould.xls");
				response.setContentType("application/msexcel");		
				wb.write(output);
				output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * mvn install:install-file -Dfile=asprise_scan.jar -DgroupId=com.asprise -DartifactId=asprise -Dversion=1.0.1 -Dpackaging=jar
	 * 调用扫描仪上传图片
	 */
	@ResponseBody
	@PostMapping("/scan")
	public Map scan(HttpServletResponse response){
		BaseBean bs = new BaseBean();
		bs.writeLog("扫描图片开始");
		bs.writeLog("1111111111");
	//输出Excel文件
		Map retResult = new HashMap();
//		try {

//			//参照http://asprise.com/scan2/docs/html/scan-dsl-spec.html#dsl-i18n
//			Result result = new AspriseScanUI().setRequest(
//					  Request.fromJson("{" +
//					    "  \"twain_cap_setting\" : {" +
//					    "    \"ICAP_PIXEXELTYPE\" : \"TWPT_RGB\"," +
//					    "    \"ICAP_SUPPORPORTEDSIZES\" : \"TWSS_USLESLETTER\"" +
//					    "  }," +
//					    "  \"i18n\" : {" +
//					    "    \"lang\" : \"zh\"" +
//					    "  }," +
//					    "  \"output_settings\" : [ {" +
//					    "    \"type\" : \"save\"," +
//					    "    \"format\" : \"jpg\"," +
//					    "    \"save_path\" : \".\\\\${TMS}-thumb${EXT}\"" +
//					    "  }, {"+
//					    "    \"type\" : \"save-thumbnail\"," +
//					    "    \"format\" : \"jpg\"," +
//					    "    \"save_path\" : \".\\\\${TMS}-thumb${EXT}\"" +
//					    "  } ]" +
//					    "}")
//					  ).showDialog(null, "启动扫描仪", true, null); // owner can be null
//
//			List<File> files = result.getImageFiles(); // Gets files
//			File file = null;
//			//设置目录
//			String dateTemp=ConvertService.getDate();;
//			String root = "D:\\temp\\"+dateTemp;
//			File fileRoot = new File(root);
//			if(!fileRoot.isDirectory()){
//				fileRoot.mkdirs();
//			}
//			String realName = "";
//			String accessorys = "";
//			String fileNames = "";
//			for(int i = 0;i<files.size();i++){
//				//获得唯一序列号
//				InputStream is = new FileInputStream(files.get(i));
//				realName = files.get(i).getName();
//				//String fileArr[] = fileFileName[i].split("\\.");
//				//String fileName = fileArr[0];
//				//String suffix = fileArr[1];
//				String contentType = "image/jpeg";
//				//String realName = idGenerator.next();
//				OutputStream os = new FileOutputStream(new File(root, realName));
//
//
//				//因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
//				System.out.println("file: " + files.get(i).getName());
//				System.out.println("file: " + files.get(i).getPath());
//
//				byte[] buffer = new byte[500];
//				int length = 0;
//
//				while(-1 != (length = is.read(buffer, 0, buffer.length)))
//				{
//					os.write(buffer);
//				}
//
//				os.close();
//				is.close();
//				ImageFileBean insertInfo = new ImageFileBean();
//				insertInfo.setImageFileName(realName);
//				insertInfo.setFilerealpath(root);
//				insertInfo.setImagefiletype("");
//				insertInfo.setDownloads(0);
//				insertInfo.setImagefileused(0);
//				insertInfo.setIszip("");
//				accessorys += ","+imageFileService.insertImageFile(insertInfo);
//				fileNames += ","+realName;
//			}
//			if(!accessorys.equals("")){
//				accessorys = accessorys.substring(1);
//			}
//			if(!fileNames.equals("")){
//				fileNames = fileNames.substring(1);
//			}
//			retResult.put("fileNames", fileNames);
//			retResult.put("accessorys", accessorys);
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return retResult;
	}
	
	
	/**
	 * 调用Js扫描仪上传图片
	 */
	@ResponseBody
	@PostMapping("/scanJs")
	public Map scanJs(HttpServletRequest request,HttpServletResponse response ){
		request.getAttribute("files");
 		BaseBean bs = new BaseBean();
		Map retResult = new HashMap();
		try {

			//设置目录
			String dateTemp=ConvertService.getDate();;
			String root = "D:\\temp\\"+dateTemp;
			String realName = "";
			String accessorys = "";
			String fileNames = "";
			if (!new File("D:\\tempScan\\"+dateTemp).isDirectory())

				new File("D:\\tempScan\\"+dateTemp).mkdirs();
			File tmpDir = new File("D:\\tempScan\\"+dateTemp);
			if (ServletFileUpload.isMultipartContent(request)) {

				
				DiskFileItemFactory dff = new DiskFileItemFactory();

				dff.setRepository(tmpDir);

				dff.setSizeThreshold(10240000);

				ServletFileUpload sfu = new ServletFileUpload(dff);
				List<FileItem> list = sfu.parseRequest(request);
				List<String> imgs = new ArrayList<String>();
				for(FileItem file :list){
					String fileName = file.getName();
				}

				sfu.setFileSizeMax(20 * 1024 * 1024);

				sfu.setSizeMax(500 * 1024 * 1024);

				FileItemIterator fii = sfu.getItemIterator(request);
				
				
				String fileName = "";
				
				while (fii.hasNext()) {

					FileItemStream fis = fii.next();

					if (!fis.isFormField() && fis.getName().length() > 0) {

						fileName = fis.getName();
						
						int index = fileName.lastIndexOf("\\");
						
						fileName = fileName.substring(index+1,fileName.length());

						String uploadPath = root;

						System.out.println("uploadPath=="+ uploadPath);
						
						if (!new File(uploadPath).isDirectory())

							new File(uploadPath).mkdirs();

						System.out.println("uploadPath=" + uploadPath);


						Date time = new Date();

						String dirTime = String.valueOf(time.getTime());

						//

						BufferedInputStream in = new BufferedInputStream(
								fis.openStream());

						// BufferedOutputStream out = new
						// BufferedOutputStream(new FileOutputStream(new
						// File(saveDir+"\\"+dirTime+fileName)));

						BufferedOutputStream out1 = new BufferedOutputStream(
								new FileOutputStream(new File(uploadPath + "\\"
										+ dirTime + fileName)));

						
						Streams.copy(in, out1, true);
						
						ImageFileBean insertInfo = new ImageFileBean();
						insertInfo.setImageFileName(realName);
						insertInfo.setFilerealpath(root);
						insertInfo.setImagefiletype("");
						insertInfo.setDownloads(0);
						insertInfo.setImagefileused(0);
						insertInfo.setIszip("");
						accessorys += ","+imageFileService.insertImageFile(insertInfo);
						fileNames += ","+realName;
					}
					if(!accessorys.equals("")){
						accessorys = accessorys.substring(1);
					}
					if(!fileNames.equals("")){
						fileNames = fileNames.substring(1);
					}
					retResult.put("fileNames", fileNames);
					retResult.put("accessorys", accessorys);
					}
				}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	/**
	 * @param id
	 * @param partnerPrice
	 * @return
	 */
	public String getTruckPartnerCount(String id,String partnerPrice){
		float allCount = 0F;
		//设置订单中货物类型详细信息
		TruckGoodsReportDetailBean selectInfo = new TruckGoodsReportDetailBean();
		selectInfo.setTruckOrder(Integer.parseInt(id));
		//TruckGoodsOrderDetailBean中需要新建一个select用goodsType,selectGoodsTypes字段用于检索
		List<TruckGoodsReportDetailBean> detailLst = truckGoodsReportService.getTruckGoodsReportDetail(selectInfo);
		//首先计算接货价格和给伙伴的价格差
		BigDecimal b1 = new BigDecimal(partnerPrice);
		//查询出车辆详细信息
		for(int i = 0 ; i<detailLst.size(); i++){
			BigDecimal b2 = detailLst.get(i).getPrice().subtract(b1);
			allCount += detailLst.get(i).getRealCarry().multiply(b2).doubleValue();
		}
		return ConvertService.getPointValue(allCount+"");
	}
	
	public Map<String,String> getColumn(String reportId,String columnName){
		Map<String,String> feeTypeValue = new HashMap<String,String>();
		FeeTypeValueBean typeBean = new FeeTypeValueBean();
		List<FeeTypeValueBean> feeTypeValueList = new ArrayList<FeeTypeValueBean>();
		feeTypeValueList = truckGoodsReportService.getColumnValue(reportId,columnName);
		for(int i=0;i<feeTypeValueList.size();i++){
			typeBean = feeTypeValueList.get(i);
			feeTypeValue.put(typeBean.getFeeType(), typeBean.getFeeTypeValue());
		}
		return feeTypeValue;
		
	}
	
	public void getFeeTypeColumnMap(String id,Map beanMap){
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
				;
			}
			Map<String,String> feeTypeValue = getColumn(id,feeTypeColumns);
			for(int i = 0 ; i<beanLst.size(); i++){
				if(beanLst.get(i).getIsUse() == 0 || beanLst.get(i).getShowType() == 3) continue;
				feeName = beanLst.get(i).getFeeName();
				columnName = beanLst.get(i).getFeeTypeColumn();
				feeTypeColumns+=beanLst.get(i).getFeeTypeColumn();
				feeTypeTitle+=feeName+" : "+ConvertService.null2String((String)feeTypeValue.get(columnName),"0")+br;
				beanMap.put(columnName, ConvertService.null2String((String)feeTypeValue.get(columnName),"0"));
			}
		}
	}

	@ResponseBody
	@PostMapping("/getAllFee")
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
	@ResponseBody
	@PostMapping("/getAllCount")
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
	
	
	public double getProfit(String count,String fee){
		BigDecimal profit = new BigDecimal("0.00");
		double countD = Double.parseDouble(count);
		double feeD = Double.parseDouble(fee);
		BigDecimal b1 = new BigDecimal(countD);
        BigDecimal b2 = new BigDecimal(feeD);
		return b1.subtract(b2).doubleValue();
	}
}
