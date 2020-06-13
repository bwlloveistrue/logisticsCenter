package com.util.optionUtil;

import com.common.CommonTransMethod;
import com.logisticscenter.model.*;
import com.util.FileldsUtil.SearchConditionOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/5/23.
 */
@Component
public class SelectOptionUtils {

    @Autowired
    private CommonTransMethod commonTransMethod;

    /**
     * 获取分配状态选择框
     * @return
     */
    public List<SearchConditionOption> getOrderStatusOptions(){
        List<SearchConditionOption> orderStatusOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",false,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("0","未分配",true,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("1","已分配",false,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("2","已删除",false,true);
        orderStatusOptions.add(searchConditionOption);
        return orderStatusOptions;
    }

    /**
     * 获取分配状态选择框
     * @return
     */
    public List<SearchConditionOption> getReportStatusOptions(){
        List<SearchConditionOption> orderStatusOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("0","待回执",false,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("1","回执中",false,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("2","回执完成",false,true);
        orderStatusOptions.add(searchConditionOption);
        return orderStatusOptions;
    }

    /**
     * 获取性别状态选择框
     * @return
     */
    public List<SearchConditionOption> getSexOptions(){
        List<SearchConditionOption> orderStatusOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption();
        searchConditionOption = new SearchConditionOption("1","男",true,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("2","女",false,true);
        orderStatusOptions.add(searchConditionOption);
        return orderStatusOptions;
    }

    /**
     * 获取文化程度状态选择框
     * @return
     */
    public List<SearchConditionOption> getEducationOptions(){
        List<SearchConditionOption> orderStatusOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption();
        searchConditionOption = new SearchConditionOption("0","小学",true,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("1","初中",false,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("2","高中",false,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("3","大专",false,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("4","本科",false,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("5","研究生",false,true);
        orderStatusOptions.add(searchConditionOption);
        return orderStatusOptions;
    }

    /**
     * 获取司机职务状态选择框
     * @return
     */
    public List<SearchConditionOption> getDriverJobOptions(){
        List<SearchConditionOption> orderStatusOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption();
        searchConditionOption = new SearchConditionOption("0","司机",true,true);
        return orderStatusOptions;
    }

    /**
     * 获取客户选择框
     * @return
     */
    public List<SearchConditionOption> getClientOptions(){
        return getClientOptions(true);
    }

    /**
     * 获取客户选择框
     * @return
     */
    public List<SearchConditionOption> getClientOptions(boolean isSearch){
        List<ClientEntity> allClient =  commonTransMethod.getAllClient();
        List<SearchConditionOption> clientOptions = new ArrayList<SearchConditionOption>();
        if(isSearch){
            SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
            clientOptions.add(searchConditionOption);
        }

        allClient.stream().forEach((_v)->{
            SearchConditionOption childOption = new SearchConditionOption(_v.getId()+"",_v.getClientName(),false,true);
            clientOptions.add(childOption);
        });

        return clientOptions;
    }

    /**
     * 获取客户选择框
     * @return
     */
    public List<SearchConditionOption> getDriverOptions(){
        return getDriverOptions(true);
    }

    /**
     * 获取客户选择框
     * @return
     */
    public List<SearchConditionOption> getDriverOptions(boolean isSearch){
        List<DriverInfoEntity> allDriverMap =  commonTransMethod.getAllDriver();
        List<SearchConditionOption> driverOptions = new ArrayList<SearchConditionOption>();
        if(isSearch){
            SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
            driverOptions.add(searchConditionOption);
        }
        allDriverMap.forEach((_v)->{
            SearchConditionOption childOption = new SearchConditionOption(_v.getId()+"",_v.getName(),false,true);
            driverOptions.add(childOption);
        });
        return driverOptions;
    }

    /**
     * 获取客户选择框
     * @return
     */
    public List<SearchConditionOption> getFeeTypeOptions(){
        List<FeeTypeEntity> allDriverMap =  commonTransMethod.getAllFeeType();
        List<SearchConditionOption> clientOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
        clientOptions.add(searchConditionOption);
        allDriverMap.forEach((_v)->{
            SearchConditionOption childOption = new SearchConditionOption(_v.getId()+"",_v.getFeeName(),false,true);
            clientOptions.add(childOption);
        });
        return clientOptions;
    }

    /**
     * @return
     */
    public List<SearchConditionOption> getSubscribeOptions(){
        List<SubscribeEntity> allSubscribeMap =  commonTransMethod.getAllSubscribe();
        List<SearchConditionOption> subscribeOptions = new ArrayList<SearchConditionOption>();
        allSubscribeMap.forEach((_v)->{
            SearchConditionOption childOption = new SearchConditionOption(_v.getOpenId()+"",_v.getNickname(),false,true);
            subscribeOptions.add(childOption);
        });
        return subscribeOptions;
    }

    /**
     * 获取客户选择框
     * @return
     */
    public List<SearchConditionOption> getGoodsTypeOptions(){
        return getGoodsTypeOptions(true);
    }

    /**
     * 获取客户选择框
     * @return
     */
    public List<SearchConditionOption> getGoodsTypeOptions(boolean isSearch){
        return getGoodsTypeOptions(isSearch,"");
    }

    /**
     * 获取客户选择框
     * @return
     */
    public List<SearchConditionOption> getGoodsTypeOptions(boolean isSearch, String goods){
        List<GoodsTypeEntity> allGoodsTypeMap =  commonTransMethod.getAllGoodsType();
        List<SearchConditionOption> goodsOptions = new ArrayList<SearchConditionOption>();
        if(isSearch){
            SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
            goodsOptions.add(searchConditionOption);
        }

        allGoodsTypeMap.forEach((_v)->{
            if(goods!=null && !goods.equals("")){
                if((","+goods+",").indexOf(","+_v.getId()+",") > -1){
                    SearchConditionOption childOption = new SearchConditionOption(_v.getId()+"",_v.getGoodsName(),false,true);
                    goodsOptions.add(childOption);
                }

            }else{
                SearchConditionOption childOption = new SearchConditionOption(_v.getId()+"",_v.getGoodsName(),false,true);
                goodsOptions.add(childOption);
            }

        });
        return goodsOptions;
    }

    /**
     * 获取车辆选择框
     * @return
     */
    public List<SearchConditionOption> getTruckOptions(){
        return getTruckOptions(true);
    }

    /**
     * 获取车辆选择框
     * @return
     */
    public List<SearchConditionOption> getTruckOptions(boolean isSearch){
        List<TruckEntity> alltruckMap =  commonTransMethod.getAllTruck();
        List<SearchConditionOption> truckOptions = new ArrayList<SearchConditionOption>();
        if(isSearch){
            SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
            truckOptions.add(searchConditionOption);
        }
        alltruckMap.forEach((_v)->{
            SearchConditionOption childOption = new SearchConditionOption(_v.getId()+"",_v.getTruckNumber(),false,true);
            truckOptions.add(childOption);
        });
        return truckOptions;
    }

    /**
     * 获取车辆选择框
     * @return
     */
    public List<SearchConditionOption> getPartnerOptions(boolean isSearch){
        List<PartnerEntity> allpartnerMap =  commonTransMethod.getAllPartner();
        List<SearchConditionOption> partnerOptions = new ArrayList<SearchConditionOption>();
        if(isSearch){
            SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
            partnerOptions.add(searchConditionOption);
        }
        allpartnerMap.forEach((_v)->{
            SearchConditionOption childOption = new SearchConditionOption(_v.getId()+"",_v.getPartner(),false,true);
            partnerOptions.add(childOption);
        });
        return partnerOptions;
    }

    /**
     * 获取发送对象选择框
     * @return
     */
    public List<SearchConditionOption> getSendTypeOptions(){
        List<SendTypeEntity> allDriverMap =  commonTransMethod.getAllSendType();
        List<SearchConditionOption> sendTypeOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption();
        sendTypeOptions.add(searchConditionOption);
        allDriverMap.forEach((_v)->{
            SearchConditionOption childOption = new SearchConditionOption(_v.getId()+"",_v.getDescription(),false,true);
            sendTypeOptions.add(childOption);
        });
        return sendTypeOptions;
    }

    /**
     * 获取工作流选择框
     * @return
     */
    public List<SearchConditionOption> getWorkflowTypeOptions(){
        List<WorkflowTypeEntity> allWorkFLowTypeMap =  commonTransMethod.getAllWorkflowType();
        List<SearchConditionOption> sendTypeOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption();
        sendTypeOptions.add(searchConditionOption);
        allWorkFLowTypeMap.forEach((_v)->{
            SearchConditionOption childOption = new SearchConditionOption(_v.getId()+"",_v.getDescription(),false,true);
            sendTypeOptions.add(childOption);
        });
        return sendTypeOptions;
    }

    /**
     * 获取工作流选择框
     * @return
     */
    public List<SearchConditionOption> getTruckPartOptions(){
        List<SearchConditionOption> truckPartOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption("0","个人",true,true);
        truckPartOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("1","伙伴",true,true);
        truckPartOptions.add(searchConditionOption);
        return truckPartOptions;
    }

    /**
     * 获取工作流选择框
     * @return
     */
    public List<SearchConditionOption> getFeeShowTypeOptions(){
        List<SearchConditionOption> truckPartOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption("0","费用",true,true);
        truckPartOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("1","奖励",true,true);
        truckPartOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("2","显示",true,true);
        truckPartOptions.add(searchConditionOption);
        return truckPartOptions;
    }

}
