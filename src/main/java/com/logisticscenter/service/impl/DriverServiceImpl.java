package com.logisticscenter.service.impl;

import java.util.*;

import com.cache.Cache;
import com.cache.CacheManager;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.logisticscenter.mapper.DriverInfoDao;
import com.logisticscenter.model.DriverInfoEntity;
import com.logisticscenter.service.DriverService;
import com.splitPage.OrderTakerSplitPage;
import com.splitPage.pageInterface.SplitPageInterface;
import com.util.FileldsUtil.FieldUtil;
import com.util.FileldsUtil.SearchConditionOption;
import com.util.Utils;
import com.util.optionUtil.SelectOptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverInfoDao driverInfoDao;

    @Autowired
    SelectOptionUtils selectOptionUtils;


    @Override
    public Map getAllDriverInfo() {
        Map retResult = new HashMap();
        List<DriverInfoEntity> entityList = new ArrayList<DriverInfoEntity>();
        List<Cache> cacheList = CacheManager.getCacheListInfo("driverEntity_CACHE");
        if(cacheList!=null && cacheList.size()>0){
            for(int i =0;i<cacheList.size();i++){
                entityList.add((DriverInfoEntity)cacheList.get(i).getValue());
            }
        }else{
            entityList = driverInfoDao.getAllDriverInfo();
            Cache cache = null;
            Date date = new Date();
            List <Cache> beanCacheLst = new ArrayList<Cache>();
            for(int i = 0;i<entityList.size();i++){
                cache = new Cache();
                cache.setKey(entityList.get(i).getId()+"");
                cache.setTimeOut(date.getTime());
                cache.setValue(entityList.get(i));
                beanCacheLst.add(cache);
            }
            CacheManager.putCacheList("driverEntity_CACHE", beanCacheLst);
        }
        retResult.put("driverInfo",entityList);
        return retResult;
    }

    @Override
    public Map getCondition(Map<String, Object> params) {
        Map retMap = new HashMap();
        List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
        Map<String,Object> groupitem = new HashMap<String,Object>();
        List<SearchConditionOption> sexOptions = selectOptionUtils.getSexOptions();
        List<SearchConditionOption> educationOptions = selectOptionUtils.getEducationOptions();
        List<SearchConditionOption> driverJobOptions = selectOptionUtils.getDriverJobOptions();
        List itemlist = new ArrayList();
        itemlist.add(FieldUtil.getFormItemForInput("name", "姓名", "", 3));
        itemlist.add(FieldUtil.getFormItemForInput("mobile", "手机号码", "", 3));
        itemlist.add(FieldUtil.getFormItemForInput("idNumber", "身份证号码", "", 3));
        itemlist.add(FieldUtil.getFormItemForDate("startWorkDate", "入职时间", "", 3,false));
        itemlist.add(FieldUtil.getFormItemForInput("driverLicense", "驾驶证号", "", 3));

        groupitem.put("title", "基本信息");
        groupitem.put("defaultshow", true);
        groupitem.put("col", 6);
        groupitem.put("items", itemlist);
        grouplist.add(groupitem);
        retMap.put("data",grouplist);
        return retMap;
    }

    @Override
    public Map insertDriverInfo(Map<String, Object> params) {
        Map retResult = new HashMap();
        String name = Utils.null2String(params.get("name"));
        String sex = Utils.null2String(params.get("sex"));
        String nativePlace = Utils.null2String(params.get("nativePlace"));
        String education = Utils.null2String(params.get("education"));
        String birthday = Utils.null2String(params.get("birthday"));
        int age = Utils.getIntValue(Utils.null2String(params.get("age")) );
        String address = Utils.null2String(params.get("address"));
        String contactNmuber = Utils.null2String(params.get("contactNmuber"));
        String mobile = Utils.null2String(params.get("mobile"));
        String idNumber = Utils.null2String(params.get("idNumber"));
        String startWorkDate = Utils.null2String(params.get("startWorkDate"));
        String driverLicense = Utils.null2String(params.get("driverLicense"));
        String truckNumber = Utils.null2String(params.get("truckNumber"));
        String appraise = Utils.null2String(params.get("appraise"));
        String salary = Utils.null2String(params.get("salary"));
        String remark = Utils.null2String(params.get("remark"));
        String job = Utils.null2String(params.get("job"));
        DriverInfoEntity driverInfoEntity = new DriverInfoEntity();
        driverInfoEntity.setName(name);
        driverInfoEntity.setSex(sex);
        driverInfoEntity.setNativePlace(nativePlace);
        driverInfoEntity.setEducation(education);
        driverInfoEntity.setBirthday(birthday);
        driverInfoEntity.setAge(age);
        driverInfoEntity.setAddress(address);
        driverInfoEntity.setContactNmuber(contactNmuber);
        driverInfoEntity.setMobile(mobile);
        driverInfoEntity.setIdNumber(idNumber);
        driverInfoEntity.setStartWorkDate(startWorkDate);
        driverInfoEntity.setDriverLicense(driverLicense);
        driverInfoEntity.setTruckNumber(truckNumber);
        driverInfoEntity.setAppraise(appraise);
        driverInfoEntity.setSalary(salary);
        driverInfoEntity.setRemark(remark);
        driverInfoEntity.setJob(job);
        int maxId = driverInfoDao.insertDriverInfo(driverInfoEntity);
        CacheManager.clearOnly("driverEntity_CACHE");
        retResult.put("id",maxId);
        return null;
    }

    @Override
    public Map getTableInfoList(Map<String, Object> params) {
        Map retMap = new HashMap();
        List<DriverInfoEntity> entityList = new ArrayList<DriverInfoEntity>();
        int page = Utils.getIntValue(Utils.null2String(params.get("currentPage")) ,1) ;
        int pageSize = Utils.getIntValue(Utils.null2String(params.get("pageSize")) ,10) ;
        PageHelper.startPage(page,pageSize);
        DriverInfoEntity searchEntity = new DriverInfoEntity();

        String name = Utils.null2String(params.get("name"));
        String mobile = Utils.null2String(params.get("mobile"));
        String idNumber = Utils.null2String(params.get("idNumber"));
        String startWorkDate = Utils.null2String(params.get("startWorkDate"));
        String driverLicense = Utils.null2String(params.get("driverLicense"));
        searchEntity.setName(name);
        searchEntity.setMobile(mobile);
        searchEntity.setIdNumber(idNumber);
        searchEntity.setStartWorkDate(startWorkDate);
        searchEntity.setDriverLicense(driverLicense);
        entityList = driverInfoDao.getDriverInfo(searchEntity);
        PageInfo pageInfo = new PageInfo(entityList);
        SplitPageInterface splitPageInterface = new OrderTakerSplitPage();
        splitPageInterface.init(pageInfo);
        retMap.put("columns",splitPageInterface.splitPageBean.getColumns());
        retMap.put("data",splitPageInterface.splitPageBean.getData());
        return retMap;
    }

    @Override
    public Map updateDriverInfo(Map<String, Object> params) {
        Map retResult = new HashMap();
        int id = Utils.getIntValue( Utils.null2String(params.get("id")));
        if(id > 0){
            String name = Utils.null2String(params.get("name"));
            String sex = Utils.null2String(params.get("sex"));
            String nativePlace = Utils.null2String(params.get("nativePlace"));
            String education = Utils.null2String(params.get("education"));
            String birthday = Utils.null2String(params.get("birthday"));
            int age = Utils.getIntValue(Utils.null2String(params.get("age")) );
            String address = Utils.null2String(params.get("address"));
            String contactNmuber = Utils.null2String(params.get("contactNmuber"));
            String mobile = Utils.null2String(params.get("mobile"));
            String idNumber = Utils.null2String(params.get("idNumber"));
            String startWorkDate = Utils.null2String(params.get("startWorkDate"));
            String driverLicense = Utils.null2String(params.get("driverLicense"));
            String truckNumber = Utils.null2String(params.get("truckNumber"));
            String appraise = Utils.null2String(params.get("appraise"));
            String salary = Utils.null2String(params.get("salary"));
            String remark = Utils.null2String(params.get("remark"));
            String job = Utils.null2String(params.get("job"));
            DriverInfoEntity driverInfoEntity = new DriverInfoEntity();
            driverInfoEntity.setId(id);
            driverInfoEntity.setName(name);
            driverInfoEntity.setSex(sex);
            driverInfoEntity.setNativePlace(nativePlace);
            driverInfoEntity.setEducation(education);
            driverInfoEntity.setBirthday(birthday);
            driverInfoEntity.setAge(age);
            driverInfoEntity.setAddress(address);
            driverInfoEntity.setContactNmuber(contactNmuber);
            driverInfoEntity.setMobile(mobile);
            driverInfoEntity.setIdNumber(idNumber);
            driverInfoEntity.setStartWorkDate(startWorkDate);
            driverInfoEntity.setDriverLicense(driverLicense);
            driverInfoEntity.setTruckNumber(truckNumber);
            driverInfoEntity.setAppraise(appraise);
            driverInfoEntity.setSalary(salary);
            driverInfoEntity.setRemark(remark);
            driverInfoEntity.setJob(job);
            driverInfoDao.updateDriverInfo(driverInfoEntity);
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
    public Map deleteDriverInfo(Map<String, Object> params) {
        Map retMap = new HashMap();
        String delids = Utils.null2String(params.get("delIds"));
        if(!delids.equals("")){
            Arrays.asList(delids.split(",")).stream().filter(item->!item.equals("")).forEach(item->{
                driverInfoDao.deleteDriverInfo(item);
            });
        }
        retMap.put("status",true);
        return retMap;
    }





    @Override
    public Map getDriverInfoFields(Map<String, Object> params) {
        Map retMap = new HashMap();
        int id = Utils.getIntValue(Utils.null2String(params.get("id")),0) ;
        DriverInfoEntity driverValueEntity = new DriverInfoEntity();
        if(id > 0){
            DriverInfoEntity searchEntity = new DriverInfoEntity();
            searchEntity.setId(id);
            List<DriverInfoEntity> driverInfoList = driverInfoDao.getDriverInfo(searchEntity);
            if(driverInfoList.size() > 0){
                driverValueEntity = driverInfoList.get(0);
            }
        }
        String name = Utils.null2String(driverValueEntity.getName());
        String sex = Utils.null2String(driverValueEntity.getSex());
        String nativePlace = Utils.null2String(driverValueEntity.getNativePlace());
        String education = Utils.null2String(driverValueEntity.getEducation());
        String birthday = Utils.null2String(driverValueEntity.getBirthday());
        int age = Utils.getIntValue(Utils.null2String(driverValueEntity.getAge())) ;
        String address = Utils.null2String(driverValueEntity.getAddress());
        String contactNmuber = Utils.null2String(driverValueEntity.getContactNmuber());
        String mobile = Utils.null2String(driverValueEntity.getMobile());
        String idNumber = Utils.null2String(driverValueEntity.getIdNumber());
        String startWorkDate = Utils.null2String(driverValueEntity.getStartWorkDate());
        String driverLicense = Utils.null2String(driverValueEntity.getDriverLicense());
        String truckNumber = Utils.null2String(driverValueEntity.getTruckNumber());
        String appraise = Utils.null2String(driverValueEntity.getAppraise());
        String salary = Utils.null2String(driverValueEntity.getSalary());
        String remark = Utils.null2String(driverValueEntity.getRemark());
        String job = Utils.null2String(driverValueEntity.getJob());
        List<Map<String,Object>> grouplist = new ArrayList<Map<String,Object>>();
        Map<String,Object> groupitem = new HashMap<String,Object>();
        List<SearchConditionOption> sexOptions = selectOptionUtils.getSexOptions();
        List<SearchConditionOption> educationOptions = selectOptionUtils.getEducationOptions();
        List<SearchConditionOption> driverJobOptions = selectOptionUtils.getDriverJobOptions();
        List itemlist = new ArrayList();
        itemlist.add(FieldUtil.getFormItemForInput("name", "姓名", name, 3));
        itemlist.add(FieldUtil.getFormItemForSelect("sex", "性别", sex, 3,3,sexOptions));
        itemlist.add(FieldUtil.getFormItemForInput("nativePlace", "国籍", nativePlace, 3));
        itemlist.add(FieldUtil.getFormItemForSelect("education", "文化程度", education, 3,3,educationOptions));
        itemlist.add(FieldUtil.getFormItemForDate("birthday", "生日", birthday, 3,false));
        itemlist.add(FieldUtil.getFormItemForInputNumber("age", "年龄", age+"", 3));
        itemlist.add(FieldUtil.getFormItemForInput("address", "地址", address, 3));
        itemlist.add(FieldUtil.getFormItemForInput("contactNmuber", "联系电话", contactNmuber, 2));
        itemlist.add(FieldUtil.getFormItemForInput("mobile", "手机号码", mobile, 3));
        itemlist.add(FieldUtil.getFormItemForInput("idNumber", "身份证号码", idNumber, 3));
        itemlist.add(FieldUtil.getFormItemForDate("startWorkDate", "入职时间", startWorkDate, 3,false));
        itemlist.add(FieldUtil.getFormItemForInput("driverLicense", "驾驶证号", driverLicense, 3));
        itemlist.add(FieldUtil.getFormItemForInput("appraise", "评价", appraise, 3));
        itemlist.add(FieldUtil.getFormItemForInputNumber("salary", "工资标准", salary, 3));
        itemlist.add(FieldUtil.getFormItemForInput("remark", "备注", remark, 3));
        itemlist.add(FieldUtil.getFormItemForSelect("job", "职位", job, 3,3,driverJobOptions));

        groupitem.put("title", "基本信息");
        groupitem.put("defaultshow", true);
        groupitem.put("col", 6);
        groupitem.put("items", itemlist);
        grouplist.add(groupitem);
        retMap.put("data",grouplist);
        return retMap;
    }

}
