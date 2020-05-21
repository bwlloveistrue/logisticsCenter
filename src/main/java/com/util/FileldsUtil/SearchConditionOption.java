package com.util.FileldsUtil;

import java.io.Serializable;

public class SearchConditionOption implements Serializable,Cloneable{

	private static final long serialVersionUID = 363769579655304111L;
	private String key;
	private String showname;
	private boolean selected= false;
	private boolean disabled = false;
	private boolean visible = true;//下拉框的值是否显示，默认显示
	
	private String [] childitemid;//在查询列表 搜索条件中存在子选项时使用,存在于父选项中 qc:376384
	
	public String [] getChilditemid() {
		return childitemid;
	}

	public void setChilditemid(String [] childitemid) {
		this.childitemid = childitemid;
	}

	public SearchConditionOption(){}
	
	public SearchConditionOption(String key, String showname){
		this.key = key;
		this.showname = showname;
	}
	
	public SearchConditionOption(String key, String showname, boolean selected){
		this.key = key;
		this.showname = showname;
		this.selected = selected;
	}

	public SearchConditionOption(String key, String showname, boolean selected, boolean visible){
		this.key = key;
		this.showname = showname;
		this.selected = selected;
		this.visible = visible;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getShowname() {
		return showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public SearchConditionOption clone() throws CloneNotSupportedException {
		return (SearchConditionOption)super.clone();
	}
	
}
