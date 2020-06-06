package com.util.FileldsUtil;

import java.io.Serializable;

public class SearchLabelConditionOption implements Serializable,Cloneable{

	private static final long serialVersionUID = 363769579655304111L;
	private String key;
	private String label;
	private String value;
	private boolean disabled = false;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		this.setKey(value);
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	@Override
	public SearchLabelConditionOption clone() throws CloneNotSupportedException {
		return (SearchLabelConditionOption)super.clone();
	}
	
}
