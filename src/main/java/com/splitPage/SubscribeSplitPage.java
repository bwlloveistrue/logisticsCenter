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
public class SubscribeSplitPage extends SplitPageInterface {


    @Override
    public List<PageCell> createColumn() {
        List<PageCell> pageCellList = new ArrayList<PageCell>();
        PageCell pageCell = null;

        pageCell = new PageCell("昵称","nickname","nickname","15%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("性别","sexName","sexName","5%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("国家","country","country","10%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("省份","province","province","10%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("城市","city","city","10%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("关注时间","subscribeTime","subscribeTime","15%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("备注","remark","remark","20%",false,false);
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
