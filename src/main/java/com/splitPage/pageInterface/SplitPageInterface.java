package com.splitPage.pageInterface;

import com.github.pagehelper.PageInfo;
import com.splitPage.PageCell;
import com.splitPage.SplitPageBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/5/24.
 */
public abstract class SplitPageInterface {

    public SplitPageBean splitPageBean = new SplitPageBean();

    /**
     * 生成分页columns
     * @return
     */
    public abstract List<PageCell> createColumn();

    /**
     * 生成分页datas
     * @param pageInfo
     * @return
     */
    public abstract Map createDatas(PageInfo pageInfo);

    public void init(PageInfo pageInfo){
        this.splitPageBean = new SplitPageBean();
        List<PageCell> pageCellList = createColumn();
        Map datas = createDatas(pageInfo);
        splitPageBean.setColumns(pageCellList);
        splitPageBean.setData(datas);
    };
}
