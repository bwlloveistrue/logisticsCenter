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
public class DriverSplitPage extends SplitPageInterface {


    @Override
    public List<PageCell> createColumn() {
        List<PageCell> pageCellList = new ArrayList<PageCell>();
        PageCell pageCell = new PageCell("","","","",false,false);
        pageCellList.add(pageCell);

//        pageCell = new PageCell("客户","client","client","10%",false,false);
//        pageCellList.add(pageCell);

        pageCell = new PageCell("司机姓名","name","name","15%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("司机性别","sex","sex","15%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("司机国籍","nativePlace","nativePlace","15%",false,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("手机号码","mobile","mobile","15%",false,false);
        pageCellList.add(pageCell);

//        pageCell = new PageCell("单价","price","price","5%",true,false);
//        pageCellList.add(pageCell);
//
//        pageCell = new PageCell("重量","realCarry","realCarry","5%",true,false);
//        pageCellList.add(pageCell);

        pageCell = new PageCell("身份证号码","idNumber","idNumber","10%",true,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("驾驶证号","driverLicense","driverLicense","15%",true,false);
        pageCellList.add(pageCell);

        pageCell = new PageCell("入职时间","startWorkDate","startWorkDate","10%",true,false);
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
