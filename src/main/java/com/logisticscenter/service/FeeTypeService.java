package com.logisticscenter.service;

import java.util.List;
import java.util.Map;

import com.javabean.FeeTypeBean;

public interface FeeTypeService {
	
	public abstract Map insertFeeType(Map params);
	
	public abstract Map getFeeType(Map params);
	
	public abstract List<FeeTypeBean> getFeeType(FeeTypeBean selectInfo);
	
	public abstract Map getFeeTypeCount(FeeTypeBean selectInfo);
	
	public abstract Map deleteFeeType(Map params);
	
	public abstract Map updateFeeType(FeeTypeBean updateInfo);

	public abstract Map getAllFeeType(Map params);

}
