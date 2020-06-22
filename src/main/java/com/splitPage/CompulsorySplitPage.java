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
public class CompulsorySplitPage extends SplitPageInterface {


    @Override
    public List<PageCell> createColumn() {
        List<PageCell> pageCellList = new ArrayList<PageCell>();
        PageCell pageCell = new PageCell("","","","",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("车牌号","truckNumberName","truckNumberName","25%",false,false);
        pageCellList.add(pageCell);


        pageCell = new PageCell("有效开始日期","startDate","startDate","25%",true,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("有效结束日期","endDate","endDate","25%",true,false);
        pageCellList.add(pageCell);


        pageCell = new PageCell("启用","isNewShow","isNewShow","25%",false,false);
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
