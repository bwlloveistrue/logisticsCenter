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
        SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("0","未分配",false,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("1","已分配",false,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("2","已删除",false,true);
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
        searchConditionOption = new SearchConditionOption("0","男",true,true);
        orderStatusOptions.add(searchConditionOption);
        searchConditionOption = new SearchConditionOption("1","女",false,true);
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
        List<ClientEntity> allClient =  commonTransMethod.getAllClient();
        List<SearchConditionOption> clientOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
        clientOptions.add(searchConditionOption);
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
        List<DriverInfoEntity> allDriverMap =  commonTransMethod.getAllDriver();
        List<SearchConditionOption> clientOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
        clientOptions.add(searchConditionOption);
        allDriverMap.forEach((_v)->{
            SearchConditionOption childOption = new SearchConditionOption(_v.getId()+"",_v.getName(),false,true);
            clientOptions.add(childOption);
        });
        return clientOptions;
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
     * 获取客户选择框
     * @return
     */
    public List<SearchConditionOption> getGoodsTypeOptions(){
        List<GoodsTypeEntity> allDriverMap =  commonTransMethod.getAllGoodsType();
        List<SearchConditionOption> clientOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
        clientOptions.add(searchConditionOption);
        allDriverMap.forEach((_v)->{
            SearchConditionOption childOption = new SearchConditionOption(_v.getId()+"",_v.getGoodsName(),false,true);
            clientOptions.add(childOption);
        });
        return clientOptions;
    }

    /**
     * 获取车辆选择框
     * @return
     */
    public List<SearchConditionOption> getTruckOptions(){
        List<TruckEntity> allDriverMap =  commonTransMethod.getAllTruck();
        List<SearchConditionOption> truckOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
        truckOptions.add(searchConditionOption);
        allDriverMap.forEach((_v)->{
            SearchConditionOption childOption = new SearchConditionOption(_v.getId()+"",_v.getTruckName(),false,true);
            truckOptions.add(childOption);
        });
        return truckOptions;
    }

}
