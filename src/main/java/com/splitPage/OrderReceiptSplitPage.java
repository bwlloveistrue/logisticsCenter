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
public class OrderReceiptSplitPage extends SplitPageInterface {


    @Override
    public List<PageCell> createColumn() {
        List<PageCell> pageCellList = new ArrayList<PageCell>();

        PageCell pageCell = new PageCell("出车编号","reportNumber","reportNumber","10%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("包车","packageFlgShow","packageFlgShow","5%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("包车价格","packagePrice","packagePrice","5%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("车牌","truckNumberName","truckNumberName","7%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("驾驶员","driverName","driverName","7%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("客户","clientName","clientName","10%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("货物类型","goodsTypeName","goodsTypeName","15%",false,false);
        pageCellList.add(pageCell);


        pageCell = new PageCell("出发时间","beginDate","beginDate","10%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("到达时间","endDate","endDate","10%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("总费用","expensens","expensens","5%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("运费","cost","cost","5%",false,false);
        pageCellList.add(pageCell);
        
        pageCell = new PageCell("利润","profit","profit","5%",false,false);
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
