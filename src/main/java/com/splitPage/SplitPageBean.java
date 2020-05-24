package com.splitPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/5/24.
 */
public class SplitPageBean {
    public List<PageCell> columns;

    public Map data;

    public List<PageCell> getColumns() {
        return columns;
    }

    public void setColumns(List<PageCell> columns) {
        this.columns = columns;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public SplitPageBean() {
    }
}
