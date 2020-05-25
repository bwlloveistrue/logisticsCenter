package com.splitPage;

import java.util.Map;

public class EditTableBean {

    public EditTableBean() {
    }

    public EditTableBean(String title, String dataIndex, String width, boolean editable) {
        this.title = title;
        this.dataIndex = dataIndex;
        this.width = width;
        this.editable = editable;
    }

    public EditTableBean(String title, String dataIndex, String width, boolean editable, Map cell) {
        this.title = title;
        this.dataIndex = dataIndex;
        this.width = width;
        this.editable = editable;
        this.cell = cell;
    }

    public String title;

    public String dataIndex;

    public String width;

    public boolean editable = true;

    public Map cell;

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

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public Map getCell() {
        return cell;
    }

    public void setCell(Map cells) {
        this.cell = cells;
    }
}
