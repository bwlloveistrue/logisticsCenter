package com.logisticscenter.service;

import java.util.List;
import java.util.Map;

//import com.entity.DriverInfoEntity;
import com.javabean.DriverInfoBean;
import com.logisticscenter.model.DriverInfoEntity;

public interface DriverService {
	
	public abstract Map insertDriverInfo(DriverInfoEntity insertInfo);
	
	public abstract Map getDriverInfo(String id);

	public abstract Map getDriverInfo(Map params);
	
	public abstract Map getDriverInfo(DriverInfoEntity selectInfo, String selectStatus);
	
	public abstract Map getDriverInfoCount(DriverInfoEntity selectInfo, String selectStatus);
	
	public abstract Map getAllDriverInfo();
	
	public abstract Map deleteDriverInfo(String id);
	
	public abstract Map updateDriverInfo(DriverInfoEntity updateInfo);
	
	public abstract Map updateAllDriverInfo(DriverInfoEntity updateInfo);

}
