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
public class PartnerSplitPage extends SplitPageInterface {


    @Override
    public List<PageCell> createColumn() {
        List<PageCell> pageCellList = new ArrayList<PageCell>();
        PageCell pageCell = new PageCell("伙伴","partner","partner","25%",false,false);
        pageCellList.add(pageCell);
        pageCell = new PageCell("地址","address","address","25%",false,false);
        pageCellList.add(pageCell);
        pageCell = new PageCell("联系方式","mobile","mobile","25%",false,false);
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
