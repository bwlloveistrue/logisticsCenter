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
public class MessageSplitPage extends SplitPageInterface {


    @Override
    public List<PageCell> createColumn() {
        List<PageCell> pageCellList = new ArrayList<PageCell>();
        PageCell pageCell = new PageCell("","","","",false,false);
        pageCellList.add(pageCell);

//        pageCell = new PageCell("客户","client","client","10%",false,false);
//        pageCellList.add(pageCell);

        pageCell = new PageCell("发送对象","sendTypeName","sendTypeName","25%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("发送类型","workflowTypeName","workflowTypeName","25%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("模板消息ID","mouldId","mouldId","25%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("发送实现类","clazzName","clazzName","20%",false,false);
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
