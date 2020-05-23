package com.util.optionUtil;

import com.common.CommonTransMethod;
import com.logisticscenter.model.ClientEntity;
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
     * 获取客户选择框
     * @return
     */
    public List<SearchConditionOption> getClientOptions(){
        Map<String,Map<String,String>> allClientMap =  commonTransMethod.getAllClient();
        List<SearchConditionOption> clientOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
        clientOptions.add(searchConditionOption);
        allClientMap.forEach((_key,_v)->{
            SearchConditionOption childOption = new SearchConditionOption(_key,_v.get("clientName"),false,true);
            clientOptions.add(childOption);
        });

        return clientOptions;
    }

    /**
     * 获取客户选择框
     * @return
     */
    public List<SearchConditionOption> getDriverOptions(){
        Map<String,Map<String,String>> allClientMap =  commonTransMethod.getAllClient();
        List<SearchConditionOption> clientOptions = new ArrayList<SearchConditionOption>();
        SearchConditionOption searchConditionOption = new SearchConditionOption("","全部",true,true);
        clientOptions.add(searchConditionOption);
        allClientMap.forEach((_key,_v)->{
            SearchConditionOption childOption = new SearchConditionOption(_key,_v.get("clientName"),false,true);
            clientOptions.add(childOption);
        });

        return clientOptions;
    }

}
