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
public class OrderTakerDetailSplitPage extends SplitPageInterface {


    @Override
    public List<PageCell> createColumn() {
        List<PageCell> pageCellList = new ArrayList<PageCell>();

        PageCell pageCell = new PageCell("货物类型","goodsTypeName","goodsTypeShow","15%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("始发地","startPlace","startPlace","10%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("目的地","endPlace","endPlace","10%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("单价","price","price","10%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("重量","realCarry","realCarry","10%",false,false);
        pageCellList.add(pageCell);


        return pageCellList;
    }

    @Override
    public Map createDatas(PageInfo pageInfo) {
        Map<String,Object> dataMap = new HashMap<String,Object>();

        return dataMap;
    }


}
