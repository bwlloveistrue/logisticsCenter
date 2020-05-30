package com.logisticscenter.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.ConvertService;
import com.common.consatnt.CacheConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javabean.TruckBean;
import com.logisticscenter.mapper.DriverInfoDao;
import com.logisticscenter.mapper.TruckInfoDao;
import com.logisticscenter.model.DriverInfoEntity;
import com.logisticscenter.model.TruckEntity;
import com.logisticscenter.service.TruckService;
import com.splitPage.OrderTakerSplitPage;
import com.splitPage.TruckSplitPage;
import com.splitPage.pageInterface.SplitPageInterface;
import com.util.FileldsUtil.FieldUtil;
import com.util.FileldsUtil.SearchConditionOption;
import com.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TruckServiceImpl implements TruckService {

    @Autowired
    TruckInfoDao truckDao;

    @Override
    public Map getAllTruck() {
        Map retResult = new HashMap();
        List<TruckEntity> entityList = new ArrayList<TruckEntity>();
        List<Cache> cacheList = CacheManager.getCacheListInfo("truckEntity_CACHE");
        if (cacheList != null && cacheList.size() > 0) {
            for (int i = 0; i < cacheList.size(); i++) {
                entityList.add((TruckEntity) cacheList.get(i).getValue());
            }
        } else {
            entityList = truckDao.getAllTruck();
            Cache cache = null;
            Date date = new Date();
            List<Cache> beanCacheLst = new ArrayList<Cache>();
            for (int i = 0; i < entityList.size(); i++) {
                cache = new Cache();
                cache.setKey(entityList.get(i).getId() + "");
                cache.setTimeOut(date.getTime());
                cache.setValue(entityList.get(i));
                beanCacheLst.add(cache);
            }
            CacheManager.putCacheList("truckEntity_CACHE", beanCacheLst);
        }
        retResult.put("driverInfo", entityList);
        return retResult;
    }

    @Override
    public Map getCondition(Map<String, Object> params) {
        Map retResult = new HashMap();
        List<Map<String, Object>> grouplist = new ArrayList<Map<String, Object>>();
        Map<String, Object> groupitem = new HashMap<String, Object>();
        List itemlist = new ArrayList();
        itemlist.add(FieldUtil.getFormItemForInput("truckNumber", "车牌号码", "", 3));
        itemlist.add(FieldUtil.getFormItemForInput("truckOwner", "户主", "", 3));
        itemlist.add(FieldUtil.getFormItemForInput("truckBrand", "车牌型号", "", 3));
        itemlist.add(FieldUtil.getFormItemForInput("engineNumber", "发动机号", "", 3));

        groupitem.put("title", "基本信息");
        groupitem.put("defaultshow", true);
        groupitem.put("col", 3);
        groupitem.put("items", itemlist);
        grouplist.add(groupitem);
        retResult.put("data", grouplist);
        retResult.put("status",true);
        retResult.put("ret",true);
        return retResult;
    }

    @Override
    public Map getTableInfoList(Map<String, Object> params) {
        Map retResult = new HashMap();
        List<TruckEntity> entityList = new ArrayList<TruckEntity>();
        int page = Utils.getIntValue(Utils.null2String(params.get("currentPage")), 1);
        int pageSize = Utils.getIntValue(Utils.null2String(params.get("pageSize")), 10);
        PageHelper.startPage(page, pageSize);
        TruckEntity searchEntity = new TruckEntity();

        String truckNumber = Utils.null2String(params.get("truckNumber"));
        String truckOwner = Utils.null2String(params.get("truckOwner"));
        String truckBrand = Utils.null2String(params.get("truckBrand"));
        String engineNumber = Utils.null2String(params.get("engineNumber"));
        searchEntity.setTruckName(truckNumber);
        searchEntity.setTruckOwner(truckOwner);
        searchEntity.setTruckBrand(truckBrand);
        searchEntity.setEngineNumber(engineNumber);
        entityList = truckDao.getTruckInfo(searchEntity);
        PageInfo pageInfo = new PageInfo(entityList);
        TruckSplitPage splitPageInterface = new TruckSplitPage();
        splitPageInterface.init(pageInfo);
        retResult.put("columns", splitPageInterface.splitPageBean.getColumns());
        retResult.put("data", splitPageInterface.splitPageBean.getData());
        retResult.put("status",true);
        retResult.put("ret",true);
        return retResult;
    }

    @Override
    public Map getTruckFields(Map<String, Object> params) {
        Map retResult = new HashMap();
        int id = Utils.getIntValue(Utils.null2String(params.get("id")),0) ;
        TruckEntity truckValueEntity = new TruckEntity();
        if(id > 0){
            TruckEntity searchEntity = new TruckEntity();
            searchEntity.setId(id);
            List<TruckEntity> driverInfoList = truckDao.getTruckInfo(searchEntity);
            if(driverInfoList.size() > 0){
                truckValueEntity = driverInfoList.get(0);
            }
        }
        //车牌号码
        String truckNumber = Utils.null2String(truckValueEntity.getTruckNumber());

//户主
        String truckOwner = Utils.null2String(truckValueEntity.getTruckOwner());

//车牌型号
        String truckBrand = Utils.null2String(truckValueEntity.getTruckBrand());

//车辆名称
        String truckName = Utils.null2String(truckValueEntity.getTruckName());

//司机联系方式
        String contactNumber = Utils.null2String(truckValueEntity.getContactNumber());

//车辆类型
        int truckType = Utils.getIntValue(Utils.null2String(truckValueEntity.getTruckType()));

//司机
        int driver = Utils.getIntValue(Utils.null2String(truckValueEntity.getDriver()));

//车辆颜色
        String truckColor = Utils.null2String(truckValueEntity.getTruckColor());

//车辆长度
        BigDecimal truckLength = Utils.toDecimal(Utils.null2String(truckValueEntity.getTruckLength()), 0);

//车辆宽度
        BigDecimal truckWidth = Utils.toDecimal(Utils.null2String(truckValueEntity.getTruckWidth()), 0);

//车辆高度
        BigDecimal truckHeight = Utils.toDecimal(Utils.null2String(truckValueEntity.getTruckHeight()), 0);

//标准载重
        BigDecimal standardWeight = Utils.toDecimal(Utils.null2String(truckValueEntity.getStandardWeight()), 0);

//驾驶证号
        String driverLicense = Utils.null2String(truckValueEntity.getDriverLicense());

//发动机号
        String engineNumber = Utils.null2String(truckValueEntity.getEngineNumber());

//生产日期
        String madeDate = Utils.null2String(truckValueEntity.getMadeDate());

//买进日期
        String buyDate = Utils.null2String(truckValueEntity.getBuyDate());

//原值
        BigDecimal worth = Utils.toDecimal(Utils.null2String(truckValueEntity.getWorth()), 0);

//买进价格
        BigDecimal buyCost = Utils.toDecimal(Utils.null2String(truckValueEntity.getBuyCost()), 0);

//备注
        String remark = Utils.null2String(truckValueEntity.getRemark());

        List<Map<String, Object>> grouplist = new ArrayList<Map<String, Object>>();
        Map<String, Object> groupitem = new HashMap<String, Object>();
        List itemlist = new ArrayList();
        itemlist.add(FieldUtil.getFormItemForInput("truckNumber", "车牌号码", truckNumber, 3));
        itemlist.add(FieldUtil.getFormItemForInput("truckOwner", "户主", truckOwner, 3));
        itemlist.add(FieldUtil.getFormItemForInput("truckBrand", "车牌型号", truckBrand, 3));
        itemlist.add(FieldUtil.getFormItemForInput("truckName", "车辆名称", truckName, 3));
//        itemlist.add(FieldUtil.getFormItemForInput("contactNumber", "司机联系方式", contactNumber, 3));
//        itemlist.add(FieldUtil.getFormItemForInput("truckType", "车辆类型", "", 3));
//        itemlist.add(FieldUtil.getFormItemForInput("driver", "司机", "", 3));
        itemlist.add(FieldUtil.getFormItemForInput("truckColor", "车辆颜色", truckColor, 3));
        itemlist.add(FieldUtil.getFormItemForInputNumber("truckLength", "车辆长度", truckLength+"", 3));
        itemlist.add(FieldUtil.getFormItemForInputNumber("truckWidth", "车辆宽度", truckWidth+"", 3));
        itemlist.add(FieldUtil.getFormItemForInputNumber("truckHeight", "车辆高度", truckHeight+"", 3));
        itemlist.add(FieldUtil.getFormItemForInputNumber("standardWeight", "标准载重", standardWeight+"", 3));
//        itemlist.add(FieldUtil.getFormItemForInput("driverLicense", "驾驶证号", "", 3));
        itemlist.add(FieldUtil.getFormItemForInput("engineNumber", "发动机号", engineNumber, 3));
        itemlist.add(FieldUtil.getFormItemForDate("madeDate", "生产日期", madeDate, 3,false));
        itemlist.add(FieldUtil.getFormItemForDate("buyDate", "买进日期", buyDate, 3,false));
        itemlist.add(FieldUtil.getFormItemForInputNumber("worth", "原值", worth+"", 3));
        itemlist.add(FieldUtil.getFormItemForInputNumber("buyCost", "买进价格", buyCost+"", 3));
        itemlist.add(FieldUtil.getFormItemForInput("remark", "备注", remark, 2));

        groupitem.put("title", "基本信息");
        groupitem.put("defaultshow", true);
        groupitem.put("col", 4);
        groupitem.put("items", itemlist);
        grouplist.add(groupitem);
        retResult.put("data", grouplist);
        retResult.put("status",true);
        retResult.put("ret",true);
        return retResult;
    }

    @Override
    public Map deleteTruck(Map<String, Object> params) {
        Map retResult = new HashMap();
        String delids = Utils.null2String(params.get("delIds"));
        if (!delids.equals("")) {
            Arrays.asList(delids.split(",")).stream().filter(item -> !item.equals("")).forEach(item -> {
                truckDao.deleteTruck(item);
            });
        }
        CacheManager.clearOnly("truckEntity_CACHE");
        retResult.put("status",true);
        retResult.put("ret",true);
        return retResult;
    }

    @Override
    public Map updateTruck(Map<String, Object> params) {
        Map retResult = new HashMap();
        int id = Utils.getIntValue( Utils.null2String(params.get("id")));
        if(id > 0){
            //车牌号码
            String truckNumber = Utils.null2String(params.get("truckNumber"));

            //户主
            String truckOwner = Utils.null2String(params.get("truckOwner"));

            //车牌型号
            String truckBrand = Utils.null2String(params.get("truckBrand"));

            //车辆名称
            String truckName = Utils.null2String(params.get("truckName"));

            //司机联系方式
            String contactNumber = Utils.null2String(params.get("contactNumber"));

            //车辆类型
            int truckType = Utils.getIntValue(Utils.null2String(params.get("truckType")));

            //司机
            int driver = Utils.getIntValue(Utils.null2String(params.get("driver")));

            //车辆颜色
            String truckColor = Utils.null2String(params.get("truckColor"));

            //车辆长度
            BigDecimal truckLength = Utils.toDecimal(Utils.null2String(params.get("truckLength")), 0);

            //车辆宽度
            BigDecimal truckWidth = Utils.toDecimal(Utils.null2String(params.get("truckWidth")), 0);

            //车辆高度
            BigDecimal truckHeight = Utils.toDecimal(Utils.null2String(params.get("truckHeight")), 0);

            //标准载重
            BigDecimal standardWeight = Utils.toDecimal(Utils.null2String(params.get("standardWeight")), 0);

            //驾驶证号
            String driverLicense = Utils.null2String(params.get("driverLicense"));

            //发动机号
            String engineNumber = Utils.null2String(params.get("engineNumber"));

            //生产日期
            String madeDate = Utils.null2String(params.get("madeDate"));

            //买进日期
            String buyDate = Utils.null2String(params.get("buyDate"));

            //原值
            BigDecimal worth = Utils.toDecimal(Utils.null2String(params.get("worth")), 0);

            //买进价格
            BigDecimal buyCost = Utils.toDecimal(Utils.null2String(params.get("buyCost")), 0);

            //备注
            String remark = Utils.null2String(params.get("remark"));

            TruckEntity truckEntity = new TruckEntity();
            truckEntity.setId(id);
            truckEntity.setTruckNumber(truckNumber);
            truckEntity.setTruckOwner(truckOwner);
            truckEntity.setTruckBrand(truckBrand);
            truckEntity.setTruckName(truckName);
            truckEntity.setContactNumber(contactNumber);
            truckEntity.setTruckType(truckType);
            truckEntity.setDriver(driver);
            truckEntity.setTruckColor(truckColor);
            truckEntity.setTruckLength(truckLength);
            truckEntity.setTruckWidth(truckWidth);
            truckEntity.setTruckHeight(truckHeight);
            truckEntity.setStandardWeight(standardWeight);
            truckEntity.setDriverLicense(driverLicense);
            truckEntity.setEngineNumber(engineNumber);
            truckEntity.setMadeDate(madeDate);
            truckEntity.setBuyDate(buyDate);
            truckEntity.setWorth(worth);
            truckEntity.setBuyCost(buyCost);
            truckEntity.setRemark(remark);
            int maxId = truckDao.updateTruck(truckEntity);
            CacheManager.clearOnly("driverEntity_CACHE");
            retResult.put("id",maxId);
            retResult.put("status",true);
            retResult.put("ret",true);
        }else{
            retResult.put("status",true);
            retResult.put("ret",false);
            retResult.put("errorMsg","数据无效！");
        }


        return retResult;
    }

    @Override
    public Map insertTruck(Map<String, Object> params) {
        Map retResult = new HashMap();
        //车牌号码
        String truckNumber = Utils.null2String(params.get("truckNumber"));

        //户主
        String truckOwner = Utils.null2String(params.get("truckOwner"));

        //车牌型号
        String truckBrand = Utils.null2String(params.get("truckBrand"));

        //车辆名称
        String truckName = Utils.null2String(params.get("truckName"));

        //司机联系方式
        String contactNumber = Utils.null2String(params.get("contactNumber"));

        //车辆类型
        int truckType = Utils.getIntValue(Utils.null2String(params.get("truckType")));

        //司机
        int driver = Utils.getIntValue(Utils.null2String(params.get("driver")));

        //车辆颜色
        String truckColor = Utils.null2String(params.get("truckColor"));

        //车辆长度
        BigDecimal truckLength = Utils.toDecimal(Utils.null2String(params.get("truckLength")), 0);

        //车辆宽度
        BigDecimal truckWidth = Utils.toDecimal(Utils.null2String(params.get("truckWidth")), 0);

        //车辆高度
        BigDecimal truckHeight = Utils.toDecimal(Utils.null2String(params.get("truckHeight")), 0);

        //标准载重
        BigDecimal standardWeight = Utils.toDecimal(Utils.null2String(params.get("standardWeight")), 0);

        //驾驶证号
        String driverLicense = Utils.null2String(params.get("driverLicense"));

        //发动机号
        String engineNumber = Utils.null2String(params.get("engineNumber"));

        //生产日期
        String madeDate = Utils.null2String(params.get("madeDate"));

        //买进日期
        String buyDate = Utils.null2String(params.get("buyDate"));

        //原值
        BigDecimal worth = Utils.toDecimal(Utils.null2String(params.get("worth")), 0);

        //买进价格
        BigDecimal buyCost = Utils.toDecimal(Utils.null2String(params.get("buyCost")), 0);

        //备注
        String remark = Utils.null2String(params.get("truckNumbe"));

        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setTruckNumber(truckNumber);
        truckEntity.setTruckOwner(truckOwner);
        truckEntity.setTruckBrand(truckBrand);
        truckEntity.setTruckName(truckName);
        truckEntity.setContactNumber(contactNumber);
        truckEntity.setTruckType(truckType);
        truckEntity.setDriver(driver);
        truckEntity.setTruckColor(truckColor);
        truckEntity.setTruckLength(truckLength);
        truckEntity.setTruckWidth(truckWidth);
        truckEntity.setTruckHeight(truckHeight);
        truckEntity.setStandardWeight(standardWeight);
        truckEntity.setDriverLicense(driverLicense);
        truckEntity.setEngineNumber(engineNumber);
        truckEntity.setMadeDate(madeDate);
        truckEntity.setBuyDate(buyDate);
        truckEntity.setWorth(worth);
        truckEntity.setBuyCost(buyCost);
        truckEntity.setRemark(remark);
        int maxId = truckDao.insertTruck(truckEntity);
        CacheManager.clearOnly("driverEntity_CACHE");
        retResult.put("id",maxId);
        retResult.put("status",true);
        retResult.put("ret",true);
        return retResult;
    }
}
