package com.splitPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/5/24.
 */
public class PaginationBean {
    int pageSize = 10;
    boolean showSizeChanger = true;
    boolean showQuickJumper = true;
    List<String> pageSizeOptions = new ArrayList<String>(){{
        this.add("10");
        this.add("20");
        this.add("50");
        this.add("100");
    }};
    Long total;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isShowSizeChanger() {
        return showSizeChanger;
    }

    public void setShowSizeChanger(boolean showSizeChanger) {
        this.showSizeChanger = showSizeChanger;
    }

    public boolean isShowQuickJumper() {
        return showQuickJumper;
    }

    public void setShowQuickJumper(boolean showQuickJumper) {
        this.showQuickJumper = showQuickJumper;
    }

    public List<String> getPageSizeOptions() {
        return pageSizeOptions;
    }

    public void setPageSizeOptions(List<String> pageSizeOptions) {
        this.pageSizeOptions = pageSizeOptions;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
