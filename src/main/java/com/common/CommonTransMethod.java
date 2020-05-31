package com.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cache.Cache;
import com.cache.CacheManager;
import com.javabean.ClientBean;
import com.javabean.DriverInfoBean;
import com.javabean.FeeTypeBean;
import com.javabean.GoodsTypeBean;
import com.javabean.ImageFileBean;
import com.javabean.TruckBean;
import com.javabean.TruckGoodsOrderDetailBean;
import com.logisticscenter.model.*;
import com.logisticscenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component("commonTransMethod")
@ComponentScan
public class CommonTransMethod {
	//客户service注入
	@Autowired
	public  ClientService clientService;
	//司机service注入
	@Autowired
	public  DriverService driverService;
	//费用类型service注入
	@Autowired
	public  FeeTypeService feeTypeService;
	//货物类型service注入
	@Autowired
	public  GoodsTypeService goodsTypeService;
	//违章记录service注入
	@Autowired
	public  IllegalReportService illegalReportService;
	//admin管理者service注入
	@Autowired
	public  LoginService loginService;
	//维修类型service注入
	@Autowired
	public  RepairTypeService repairTypeService;
	//车辆费用service注入
	@Autowired
	public  TruckFeeInfoService truckFeeInfoService;
	//车辆记录service注入
	@Autowired
	public  TruckGoodsReportService truckGoodsReportService;
	//车辆order记录service注入
	@Autowired
	public  TruckGoodsOrderService truckGoodsOrderService;
	//车辆service注入
	@Autowired
	public  TruckService truckService;
	//报表注入
	@Autowired
	public  ChartsService chartsService;
	//图片文件service注入
	@Autowired
	public  ImageFileService imageFileService;
	@Autowired
	public  TruckSetService truckSetService;

	/**
	 * @return 客户名称
	 */
	public  List<ClientEntity> getAllClient(){
		List<ClientEntity> allClient = new ArrayList<ClientEntity>();
		Map beanMap = new HashMap();
		try{
			beanMap= clientService.getAllClient();
			allClient = (List<ClientEntity>)beanMap.get("clientInfo");
			return allClient;
		}catch(Exception e){
			allClient = new ArrayList<ClientEntity>();
			e.printStackTrace();
		}
		return allClient;
	}

	/**
	 * @return 司机姓名
	 */
	public  List<DriverInfoEntity> getAllDriver(){
		List<DriverInfoEntity> allDriver = new ArrayList<DriverInfoEntity>();
		Map beanMap = new HashMap();
		try{
			beanMap= driverService.getAllDriverInfo();
			allDriver = (List<DriverInfoEntity>)beanMap.get("driverInfo");
			return allDriver;
		}catch(Exception e){
			allDriver = new ArrayList<DriverInfoEntity>();
			e.printStackTrace();
		}
		return allDriver;
	}

	/**
	 * @return 费用
	 */
	public  List<FeeTypeEntity> getAllFeeType(){
		List<FeeTypeEntity> allFeeType = new ArrayList<FeeTypeEntity>();
		Map beanMap = new HashMap();
		try{
			beanMap= feeTypeService.getAllFeeType(new HashMap());
			allFeeType = (List<FeeTypeEntity>)beanMap.get("feeTypeInfo");
			return allFeeType;
		}catch(Exception e){
			allFeeType = new ArrayList<FeeTypeEntity>();
			e.printStackTrace();
		}
		return allFeeType;
	}

	/**
	 * @return 货物类型ID
	 */
	public  List<GoodsTypeEntity> getAllGoodsType(){
		List<GoodsTypeEntity> allGoodsType = new ArrayList<GoodsTypeEntity>();
		Map beanMap = new HashMap();
		try{
			beanMap= goodsTypeService.getAllGoodsType();
			allGoodsType = (List<GoodsTypeEntity>)beanMap.get("goodsTypeInfo");

		}catch(Exception e){
			e.printStackTrace();
		}
		return allGoodsType;
	}

	/**
	 * @return 货物类型ID
	 */
	public  List<TruckEntity> getAllTruck(){
		List<TruckEntity> allTruck = new ArrayList<TruckEntity>();
		Map beanMap = new HashMap();
		try{
			beanMap= truckService.getAllTruck();
			allTruck = (List<TruckEntity>)beanMap.get("goodsTypeInfo");

		}catch(Exception e){
			e.printStackTrace();
		}
		return allTruck;
	}

	/**
	 * @param ids 客户ID
	 * @return 客户名称
	 */
	public static String getClientName(String ids){
		List<Cache> cacheList = CacheManager.getCacheListInfo("clientEntity_CACHE");
		String clientNameTemp = "";
		for (int i = 0; i < cacheList.size(); i++) {
			String key = cacheList.get(i).getKey();
			if(key.equals(ids+"")){
				ClientEntity clientEntity = (ClientEntity) cacheList.get(i).getValue();
				clientNameTemp = clientEntity.getClientName();
			}
		}
		return clientNameTemp;
	}


	/**
	 * @param ids 司机ID
	 * @return 司机姓名
	 */
	public  String getDriverName(String ids){
		List<Cache> cacheList = CacheManager.getCacheListInfo("driverEntity_CACHE");
		String driverNameTemp = "";
		for (int i = 0; i < cacheList.size(); i++) {
			String key = cacheList.get(i).getKey();
			if(key.equals(ids+"")){
				DriverInfoEntity driverInfoEntity = (DriverInfoEntity) cacheList.get(i).getValue();
				driverNameTemp = driverInfoEntity.getName();
			}
		}
		return driverNameTemp;
	}

	/**
	 * @param ids 货物类型ID
	 * @return 货物名称
	 */
	public static String getGoodsTypeName(String ids){
		List<Cache> cacheList = CacheManager.getCacheListInfo("goodsTypeEntity_CACHE");
		String goodsTypeNameTemp = "";
		for (int i = 0; i < cacheList.size(); i++) {
			String key = cacheList.get(i).getKey();
			if(key.equals(ids+"")){
				GoodsTypeEntity goodsTypeEntity = (GoodsTypeEntity) cacheList.get(i).getValue();
				goodsTypeNameTemp = goodsTypeEntity.getGoodsName();
			}
		}
		return goodsTypeNameTemp;
	}

	/**
	 * @param str
	 * @return 是/否
	 */
	public static String getIsOrNotString(String str){
		String retStr = "";
		if(str.equals("1")){
			retStr = "是";
		}else{
			retStr = "否";
		}
		return retStr;
	}

	/**
	 * @param str
	 * @return 是/否
	 */
	public static String getOrderStatusName(String str){
		String retStr = "";
		if(str.equals("0")){
			retStr = "未分配";
		}else if(str.equals("1")){
			retStr = "已分配";
		}else{
			retStr = "已删除";
		}

		return retStr;
	}



	/**
	 * @param driverName 司机姓名
	 * @return 司机ID
	 */
	public  int getDriverId(String driverName){
		int retStr = 0;
		List<DriverInfoBean> beanLst = null;
		try{
//			beanLst= driverService.getAllDriverInfo();
			for(int i = 0;i<beanLst.size();i++){
				if(beanLst.get(i).getName().equals(driverName)){
					retStr = beanLst.get(i).getId();
					break;
				}
			}
			return retStr;
		}catch(Exception e){
			e.printStackTrace();
		}
		return retStr;

	}

	public  int createDriver(String driverName){
		int maxId = 0;
		DriverInfoBean bean = new DriverInfoBean(0,driverName, "1", "", "", "", 0, "", "", "", "", "", "", "", "", "", "", "");
//		maxId = driverService.insertDriverInfo(bean);
		CacheManager.clearOnly("driverEntity_CACHE");
		return maxId;
	}


	public  int createClient(String clientName){
		int maxId = 0;
		ClientBean bean = new ClientBean(0,clientName,"","","","","");
//		maxId = clientService.insertClient(bean);
		CacheManager.clearOnly("clientEntity_CACHE");
		return maxId;
	}

	/**
	 * @param clientName 客户名称
	 * @return 客户ID
	 */
	public  int getClientId(String clientName){
		if("".equals(clientName) || clientName == null){
			return 0;
		}
		int retStr = 0;
		List<ClientBean> beanLst = null;
		try{
//			beanLst= clientService.getAllClient();
			for(int i = 0;i<beanLst.size();i++){
				if(beanLst.get(i).getClientName().equals(clientName)){
					retStr = beanLst.get(i).getId();
					break;
				}
			}
			return retStr;
		}catch(Exception e){
			e.printStackTrace();
		}
		return retStr;
	}


	/**
	 * @param goodsTypeName 货物名称
	 * @return 货物类型ID
	 */
	public  int getGoodsTypeId(String goodsTypeName){
		if("".equals(goodsTypeName) || goodsTypeName == null){
			return 0;
		}
		int retStr = 0;
		try{
//			List<GoodsTypeBean> beanLst= goodsTypeService.getAllGoodsType();
//			for(int k = 0;k<beanLst.size();k++){
//				if(beanLst.get(k).getGoodsName().equals(goodsTypeName)){
//					retStr = beanLst.get(k).getId();
//					break;
//				}
//			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return retStr;
	}

	/**
	 * @param goodsTypeName 货物名称
	 * @return 货物类型ID
	 */
	public  String getGoodsTypeIds(String goodsTypeName){
		if("".equals(goodsTypeName) || goodsTypeName == null){
			return "";
		}
		String retStr = "";
		try{
			String goodsTypeArr[] = goodsTypeName.split(",");
//			for(int i=0;i<goodsTypeArr.length;i++){
//				if(!goodsTypeArr[i].equals("")){
//					List<GoodsTypeBean> beanLst= goodsTypeService.getAllGoodsType();
//					for(int k = 0;k<beanLst.size();k++){
//						if(beanLst.get(k).getGoodsName().equals(goodsTypeArr[i])){
//							retStr += ","+beanLst.get(k).getId();
//						}
//					}
//				}
//			}

		}catch(Exception e){
			e.printStackTrace();
		}
		if(!retStr.equals("")){
			retStr = retStr.substring(1);
		}
		return retStr;
	}

	public  int createGoodsType(String goodsName){
		int maxId = 0;
//		GoodsTypeEntity entity = new GoodsTypeEntity( goodsName, 1, 0);
//		maxId = goodsTypeService.insertGoodsType(entity);
		CacheManager.clearOnly("goodsTypeEntity_CACHE");
		return maxId;
	}

	/**
	 * @param id 车牌号
	 * @return 车辆名称
	 */
	public  String getTruckNumber(String id){
		String retStr = "";
		if("".equals(id) || id == null || id.equals("0")){
			return "";
		}
		Map truckInfoMap= truckService.getAllTruck();
		List<TruckEntity> entityList = (List<TruckEntity>)truckInfoMap.get("data");
		try{
			for(int i = 0;i<entityList.size();i++){
				if(entityList.get(i).getId()== Integer.parseInt(id)){
					retStr = entityList.get(i).getTruckNumber();
					break;
				}
			}
			return retStr;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @param truckNmuber 车辆名称
	 * @return 车牌号
	 */
	public  int getTruckId(String truckNmuber){
		int retStr = 0;
		if("".equals(truckNmuber) || truckNmuber == null ){
			return 0;
		}
		Map truckInfoMap= truckService.getAllTruck();
		List<TruckEntity> entityList = (List<TruckEntity>)truckInfoMap.get("data");
		try{
			for(int i = 0;i<entityList.size();i++){
				if(entityList.get(i).getTruckNumber().equals(truckNmuber)){
					retStr = entityList.get(i).getId();
					break;
				}
			}
			return retStr;
		}catch(Exception e){
			e.printStackTrace();
		}
		return retStr;
	}

	public  int createTruck(String truckNumber){
		int maxId = 0;
		Map truckValueMap = new HashMap();
		Map retMap = truckService.insertTruck(truckValueMap);
		CacheManager.clearOnly("truckEntity_CACHE");
		return maxId;
	}

	/**
	 * 是否包车
	 * @param flg
	 * @return 是否包车
	 */
	public  String convertPackage(int flg){
		String retStr = "否";
		if(flg == 1){
			retStr = "是";
		}else{
			retStr = "否";
		}
		return retStr;
	}

	/**
	 * 是否
	 * @param str flg
	 * @return 是否
	 */
	public  int convertFlg(String str){
		int retStr = 0;
		if(str.trim().equals("是")){
			retStr = 1;
		}else{
			retStr = 0;
		}
		return retStr;
	}

	/**
	 * 是否伙伴
	 * @param flg
	 * @return 个人/伙伴
	 */
	public  String convertTruckPart(int flg){
		String retStr = "个人";
		if(flg == 1){
			retStr = "伙伴";
		}else{
			retStr = "个人";
		}
		return retStr;
	}

	/**
	 * 是否包车
	 * @param flg
	 * @return 是否包车
	 */
	public  String convertShowType(int flg){
		String retStr = "";
		if(flg == 0){
			retStr = "费用";
		}else if(flg == 1){
			retStr = "奖励";
		}else{
			retStr = "显示";
		}
		return retStr;
	}

	/**
	 * 获得文件名称
	 * @param ids
	 * @return
	 */
	public  String getFileName(String ids){
		if(ids==null || ids.equals("") || ids.equals(",")){
			return "";
		}
		String fileNames = "";
		if(ids.startsWith(",")){
			ids = ids.substring(1);
		}
		if(ids.endsWith(",")){
			ids = ids.substring(0,ids.length()-1);
		}
		List<ImageFileBean> imageList = getImageBean(ids);
		for(ImageFileBean bean:imageList){
			fileNames += fileNames.equals("")?bean.getImageFileName():","+bean.getImageFileName();
		}
		return fileNames;

	}

	/**
	 * 获得文件路径
	 * @param ids
	 * @return
	 */
	public  String getFullPathName(String ids){
		if(ids==null || ids.equals("") || ids.equals(",")){
			return "";
		}
		String fileNames = "";
		if(ids.startsWith(",")){
			ids = ids.substring(1);
		}
		if(ids.endsWith(",")){
			ids = ids.substring(0,ids.length()-1);
		}
		List<ImageFileBean> imageList = getImageBean(ids);
		for(ImageFileBean bean:imageList){
			fileNames += fileNames.equals("")?bean.getFilerealpath()+"\\"+bean.getImageFileName():","+bean.getFilerealpath()+"\\"+bean.getImageFileName();
		}
		return fileNames;

	}

	/**
	 * 获得文件Bean
	 * @param ids
	 * @return
	 */
	public  List<ImageFileBean> getImageBean(String ids){
		List<ImageFileBean> imageList = new ArrayList<ImageFileBean>();
		String selectIds = "";
		String idsArr[] = ids.split(",");
		for(int i=0;i<idsArr.length;i++){
			if(!idsArr[i].equals("")){
				selectIds += selectIds.equals("")?idsArr[i]:","+idsArr[i];
			}
		}
		if(ids == null || ids.equals("")){
			return imageList;
		}
		try{
			imageList = imageFileService.getImageFileBy(ids);
		}catch(Exception e){
			e.printStackTrace();
		}
		return imageList;
	}

	/**
	 * 获得出车订单编号
	 * @param date 要查询的日期
	 * @param num 显示的个数
	 * @return
	 */
	public  synchronized int getReportNumber(String date,int num){
		String maxId = ConvertService.null2String( truckGoodsReportService.getMaxReportNumber(date),"000000000");
		//转换成后num位
		return ConvertService.getIntValue(maxId.substring(maxId.length()-num), 1)+1;
	}
	public  void main(String[] args) {
		String a = "BWL20170712351";
		int b = ConvertService.getIntValue(a.substring(a.length()-2), 1);
		System.out.println(b);
	}

}
