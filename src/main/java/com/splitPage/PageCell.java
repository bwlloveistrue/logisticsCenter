package com.splitPage;

/**
 * Created by Administrator on 2020/5/24.
 */
public class PageCell {

    public PageCell() {
    }

    public PageCell(String title, String dataIndex, String key, String width, boolean sorter, boolean needTotal) {
        this.title = title;
        this.dataIndex = dataIndex;
        this.key = key;
        this.sorter = sorter;
        this.width = width;
        this.needTotal = needTotal;
    }

    public String title;

    public String dataIndex;

    public String key;

    public boolean sorter;

    public String width;

    public boolean needTotal;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(String dataIndex) {
        this.dataIndex = dataIndex;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isSorter() {
        return sorter;
    }

    public void setSorter(boolean sorter) {
        this.sorter = sorter;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public boolean isNeedTotal() {
        return needTotal;
    }

    public void setNeedTotal(boolean needTotal) {
        this.needTotal = needTotal;
    }
}
