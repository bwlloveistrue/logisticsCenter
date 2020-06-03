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
public class OrderTakerSplitPage extends SplitPageInterface {


    @Override
    public List<PageCell> createColumn() {
        List<PageCell> pageCellList = new ArrayList<PageCell>();
        PageCell pageCell = new PageCell("","","","",false,false);
//        pageCellList.add(pageCell);

//        pageCell = new PageCell("客户","client","client","10%",false,false);
//        pageCellList.add(pageCell);

        pageCell = new PageCell("客户","clientName","clientName","15%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("货物类型","goodsTypeName","goodsTypeShow","15%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("始发地","startPlace","startPlace","10%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("目的地","endPlace","endPlace","10%",false,false);
        pageCellList.add(pageCell);

//        pageCell = new PageCell("单价","price","price","5%",true,false);
//        pageCellList.add(pageCell);
//
//        pageCell = new PageCell("重量","realCarry","realCarry","5%",true,false);
//        pageCellList.add(pageCell);

        pageCell = new PageCell("出发日期","beginDate","beginDate","10%",true,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("状态","orderStatusName","orderStatusName","10%",true,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("是否包车","packageFlgShow","packageFlgShow","15%",true,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("创建日期","createDate","createDate","10%",true,false);
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
