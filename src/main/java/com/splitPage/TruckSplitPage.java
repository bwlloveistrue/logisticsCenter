package com.splitPage;

import com.github.pagehelper.PageInfo;
import com.splitPage.pageInterface.SplitPageInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/5/24.
 */
public class TruckSplitPage extends SplitPageInterface {


    @Override
    public List<PageCell> createColumn() {
        List<PageCell> pageCellList = new ArrayList<PageCell>();
        PageCell pageCell = new PageCell("","","","",false,false);
        pageCellList.add(pageCell);

//        pageCell = new PageCell("客户","client","client","10%",false,false);
//        pageCellList.add(pageCell);

        pageCell = new PageCell("车牌号码","truckNumber","truckNumber","15%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("户主","truckOwner","truckOwner","15%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("型号","truckBrand","truckBrand","15%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("名称","truckName","truckName","15%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("发动机号","engineNumber","engineNumber","10%",true,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("生产日期","madeDate","madeDate","15%",true,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("买进日期","buyDate","buyDate","10%",true,false);
        pageCellList.add(pageCell);
        return pageCellList;
    }

    @Override
    public Map createDatas(PageInfo pageInfo) {
        Map<String,Object> dataMap = new HashMap<String,Object>();
        PaginationBean paginationBean = new PaginationBean();
        paginationBean.setTotal(pageInfo.getTotal());
        paginationBean.setPageSize(pageInfo.getPageSize());
        dataMap.put("pagination",paginationBean);
        dataMap.put("list",pageInfo.getList());
        return dataMap;
    }


}
