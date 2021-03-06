package com.logisticscenter.controller;

import com.common.CommonTransMethod;
import com.common.ConvertService;
import com.configBean.FileProperties;
import com.javabean.*;
import com.logisticscenter.model.ImageFileEntity;
import com.logisticscenter.model.UploadFileResponse;
import com.logisticscenter.service.FeeTypeService;
import com.logisticscenter.service.ImageFileService;
import com.logisticscenter.service.TruckGoodsReportService;
import com.util.ConstantUtils;
import com.util.ParamUtil;
import com.util.TimeUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.core.io.Resource;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 获得上传文件以及下载文件
 *
 * @卜伟领 2017
 */
@Controller
@RequestMapping(value = "/api/imageFile")
public class ImageFileController implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ImageFileController() {

    }

    @Autowired
    private ImageFileService imageFileService;

    @Autowired
    private TruckGoodsReportService truckGoodsReportService;

    @Autowired
    private FileProperties fileProperties;

    //获得费用类型字段用
    @Autowired
    private FeeTypeService feeTypeService;

    @Autowired
    private CommonTransMethod commonTransMethod;

    @ResponseBody
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        ImageFileEntity fileEntity = imageFileService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/imagePreview/")
                .path(fileEntity.getId()+"")
                .toUriString();

        UploadFileResponse uploadFileResponse = new UploadFileResponse();
        uploadFileResponse.setSize(file.getSize());
        uploadFileResponse.setStatus("done");
        uploadFileResponse.setThumbUrl(fileEntity.getFilerealpath());
        uploadFileResponse.setUrl(fileEntity.getFilerealpath());
        Map responseMap = new HashMap();
        responseMap.put("status","success");
        uploadFileResponse.setResponse(responseMap);
        uploadFileResponse.setUid(fileEntity.getId()+"");
        return uploadFileResponse;
    }


    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }


    @ResponseBody
    @PostMapping("/getFiles")
    public Map getFiles(HttpServletRequest request) {
        Map<String, Object> apidatas = new HashMap<String, Object>();
        try {
            apidatas.putAll(imageFileService.getImageFileBy(ParamUtil.request2Map(request)));
            apidatas.put("api_status", true);
        } catch (Exception e) {
            e.printStackTrace();
            apidatas.put("api_status", false);
            apidatas.put("api_errormsg", "catch exception : " + e.getMessage());
        }
        return apidatas;
    }

    @GetMapping(value = "/imageDownloads")
    public ResponseEntity<Resource> downloads(HttpServletRequest request, HttpServletResponse response) {

        String pathId = request.getParameter("pathId");
        Resource resource = imageFileService.loadFileAsResource(pathId);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping(value = "/imagePreview")
    public ResponseEntity<byte[]> imagePreview(HttpServletRequest request, HttpServletResponse response) {

        String pathId = request.getParameter("pathId");
        String realPath = imageFileService.getFilePath(pathId);
        String contentType = null;
        try {
            Resource resource = imageFileService.loadFileAsResource(pathId);
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }
        byte[] imageContent = new byte[0];
        try {
            imageContent = fileToByte(new File(realPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    public static byte[] fileToByte(File img) throws Exception {
        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "png", baos);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baos.close();
        }
        return bytes;
    }

    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        //获得费用类型的columns
        String[] feeNames = ConstantUtils.FEE_TYPE_NAMES.split(",");
        String[] feeIds = ConstantUtils.FEE_TYPE_COLUMNS.split(",");
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("出车信息");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell = row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("出车信息一览表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 21 + feeNames.length));
        //在sheet里创建第二行
        HSSFRow row2 = sheet.createRow(1);
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
        for (int i = 0; i < feeNames.length; i++) {
            if (!feeNames.equals("")) {
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
        for (int i = 0; i < columnNameList.size(); i++) {
            row2.createCell(i).setCellValue((columnNameList.get(i) + ""));
        }
        //创建详细的行
        String truckNumber = ConvertService.null2String(request.getParameter("truckNumber"));
        int reportStatus = ConvertService.getIntValue(request.getParameter("reportStatus"), -1);
        String startPlace = ConvertService.null2String(request.getParameter("startPlace"));
        String endPlace = ConvertService.null2String(request.getParameter("endPlace"));
        String beginDate = ConvertService.null2String(request.getParameter("beginDate"));
        String endDate = ConvertService.null2String(request.getParameter("endDate"));
        int driver = ConvertService.getIntValue(request.getParameter("driver"), -1);
        int client = ConvertService.getIntValue(request.getParameter("client"), -1);
        String goodsType = ConvertService.null2String(request.getParameter("goodsType"), "0");
        String carryNumber = ConvertService.null2String(request.getParameter("carryNumber"));

        TruckGoodsReportBean infoBean = new TruckGoodsReportBean(reportStatus, truckNumber, startPlace, endPlace, beginDate, endDate, driver, client, goodsType, carryNumber, "", "");
        List<TruckGoodsReportBean> beanLst = truckGoodsReportService.getTruckGoodsReportExcel(infoBean);
        //获取输出流，然后使用
        PrintWriter out = null;
        try {
            Map beanMap = null;
            for (int i = 0; i < beanLst.size(); i++) {
                beanMap = new HashMap();
                int packageFlgExchange = Integer.parseInt(beanLst.get(i).getPackageFlg());
                int truckPartExchange = beanLst.get(i).getTruckPart();
                beanMap.put("id", beanLst.get(i).getId());
                beanMap.put("truckNumber", commonTransMethod.getTruckNumberName(beanLst.get(i).getTruckNumber()));
                beanMap.put("packageFlg", packageFlgExchange);
                beanMap.put("packageFlgShow", commonTransMethod.convertPackage(packageFlgExchange));
                beanMap.put("packagePrice", ConvertService.getPointValue(beanLst.get(i).getPackagePrice() + "", 2));
                beanMap.put("reportNumber", ConvertService.null2String(beanLst.get(i).getReportNumber()));
                beanMap.put("truckPart", commonTransMethod.convertTruckPart(beanLst.get(i).getTruckPart()));
                beanMap.put("partner", beanLst.get(i).getPartner());
                beanMap.put("partnerPrice", ConvertService.getPointValue(beanLst.get(i).getPartnerPrice(), 2));
                beanMap.put("partnerCarry", ConvertService.getPointValue(beanLst.get(i).getPartnerCarry(), 2));
//				beanMap.put("startPlace",beanLst.get(i).getStartPlace());
//				beanMap.put("endPlace",beanLst.get(i).getEndPlace());
                beanMap.put("beginDate", beanLst.get(i).getBeginDate());
                beanMap.put("expectedDate", beanLst.get(i).getExpectedDate());
                beanMap.put("endDate", beanLst.get(i).getEndDate());
                beanMap.put("driver", commonTransMethod.getDriverName(beanLst.get(i).getDriver() + ""));
                beanMap.put("client", commonTransMethod.getClientName(beanLst.get(i).getClient() + ""));
//				beanMap.put("goodsType",commonTransMethod.getGoodsTypeName(beanLst.get(i).getGoodsType()+""));
                beanMap.put("prepaidFlg", beanLst.get(i).getPrepaidFlg());
                beanMap.put("prepaidExpress", beanLst.get(i).getPrepaidExpress());
                beanMap.put("prepaidDesc", beanLst.get(i).getPrepaidDesc());
                beanMap.put("reportStatus", beanLst.get(i).getReportStatus());
                beanMap.put("isLater", beanLst.get(i).getIsLater());
                beanMap.put("laterReason", beanLst.get(i).getLaterReason());
//				beanMap.put("realCarry",beanLst.get(i).getRealCarry());
//				beanMap.put("price",beanLst.get(i).getPrice());
                //beanMap.put("expensens",getAllFee(beanLst.get(i).getId()+""));
                beanMap.put("carryNumber", beanLst.get(i).getCarryNumber());
                beanMap.put("workPlace", beanLst.get(i).getWorkPlace());
                beanMap.put("createDate", beanLst.get(i).getCreateDate());
                beanMap.put("createTime", beanLst.get(i).getCreateTime());
                beanMap.put("editDate", beanLst.get(i).getEditDate());
                beanMap.put("editTime", beanLst.get(i).getEditTime());
                beanMap.put("remark", beanLst.get(i).getRemark());
                beanMap.put("expensens", ConvertService.getPointValue(beanLst.get(i).getExpensens() + "", 2));
                beanMap.put("cost", ConvertService.getPointValue(beanLst.get(i).getCost() + "", 2));
                beanMap.put("profit", ConvertService.getPointValue(beanLst.get(i).getProfit() + "", 2));


                TruckGoodsReportDetailBean selectInfo = new TruckGoodsReportDetailBean();
                selectInfo.setTruckOrder(beanLst.get(i).getId());
                //TruckGoodsOrderDetailBean中需要新建一个select用goodsType,selectGoodsTypes字段用于检索
                List<TruckGoodsReportDetailBean> detailLst = truckGoodsReportService.getTruckGoodsReportDetail(selectInfo);
                if (detailLst.size() > 0) {
                    //查询出车辆详细信息
                    String startPlaceShow = "";
                    String endPlaceShow = "";
                    String goodsTypeShow = "";
                    String invoiceFlgShow = "";
                    String priceShow = "";
                    String realCarryShow = "";
                    String liftingCostShow = "";

                    for (int j = 0; j < detailLst.size(); j++) {
                        goodsTypeShow += goodsTypeShow.equals("") ? detailLst.get(j).getGoodsType() : "," + detailLst.get(j).getGoodsType();
                        priceShow += priceShow.equals("") ? detailLst.get(j).getPrice() : "," + detailLst.get(j).getPrice();
                        realCarryShow += realCarryShow.equals("") ? detailLst.get(j).getRealCarry() + "" : "," + detailLst.get(j).getRealCarry();
                        invoiceFlgShow += invoiceFlgShow.equals("") ? (detailLst.get(j).getInvoiceFlg() == 1 ? "开票" : "否") : "," + (detailLst.get(j).getInvoiceFlg() == 1 ? "开票" : "否");
                        startPlaceShow += startPlaceShow.equals("") ? detailLst.get(j).getStartPlace() + "" : "," + detailLst.get(j).getStartPlace();
                        endPlaceShow += endPlaceShow.equals("") ? detailLst.get(j).getEndPlace() + "" : detailLst.get(j).getEndPlace() + "";
                        liftingCostShow += liftingCostShow.equals("") ? detailLst.get(j).getLiftingCost() + "" : "," + detailLst.get(j).getLiftingCost();
                    }
                    beanMap.put("goodsType", commonTransMethod.getGoodsTypeName(goodsTypeShow));
                    beanMap.put("price", priceShow);
                    beanMap.put("realCarry", realCarryShow);
                    beanMap.put("startPlace", startPlaceShow);
                    beanMap.put("endPlace", endPlaceShow);
                    beanMap.put("invoiceFlg", invoiceFlgShow);
                    beanMap.put("liftingCost", liftingCostShow);
                }
                HSSFRow row3 = sheet.createRow(2 + i);
                for (int j = 0; j < columnIdList.size(); j++) {
                    row3.createCell(j).setCellValue((beanMap.get(columnIdList.get(j)) + ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //输出Excel文件
        OutputStream output;
        try {
            output = response.getOutputStream();
            response.setCharacterEncoding("gb2312");
            response.setHeader("Content-disposition", "attachment; filename=details.xls");
            response.setContentType("application/msexcel");
            response.setHeader("Content-disposition", "attachment;filename=" + TimeUtil.getFormartString(Calendar.getInstance(), "yyyyMMddHH") + ".xls");
            response.setContentType("application/vnd.ms-excel");
            wb.write(output);
            output.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @PostMapping("/mouldExcel")
    public void mouldExcel(HttpServletRequest request, HttpServletResponse response) {
        //获得费用类型的columns
        String[] feeNames = ConstantUtils.FEE_TYPE_NAMES.split(",");
        String[] feeIds = ConstantUtils.FEE_TYPE_COLUMNS.split(",");
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("导入模板");
        //在sheet里创建第二行
        HSSFRow row2 = sheet.createRow(1);
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
        for (int i = 0; i < feeNames.length; i++) {
            if (!feeNames.equals("")) {
                columnNameList.add(feeNames[i]);
                columnIdList.add(feeIds[i]);
            }
        }
        columnNameList.add("仓库费用");
        columnIdList.add("liftingCost");
        columnNameList.add("备注");
        columnIdList.add("remark");
        for (int i = 0; i < columnNameList.size(); i++) {
            row2.createCell(i).setCellValue((columnNameList.get(i) + ""));
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
    }
}
