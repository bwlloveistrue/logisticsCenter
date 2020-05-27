package com.logisticscenter.service.impl;

import java.util.*;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.ConvertService;
import com.common.consatnt.CacheConstant;
import com.javabean.TruckBean;
import com.logisticscenter.mapper.DriverInfoDao;
import com.logisticscenter.mapper.TruckInfoDao;
import com.logisticscenter.model.DriverInfoEntity;
import com.logisticscenter.model.TruckEntity;
import com.logisticscenter.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TruckServiceImpl implements TruckService {

	@Autowired
	TruckInfoDao truckDao;

	@Autowired
	DriverInfoDao driverDao;


	@Override
	public Map getCondition(Map<String, Object> params) {
		return null;
	}

	@Override
	public Map insertTruck(Map<String, Object> params) {
		return null;
	}

	@Override
	public Map getTableInfoList(Map<String, Object> params) {
		return null;
	}

	@Override
	public Map getTruckFields(Map<String, Object> params) {
		return null;
	}

	@Override
	public Map getAllTruck() {
		return null;
	}

	@Override
	public Map deleteTruck(Map<String, Object> params) {
		return null;
	}

	@Override
	public Map updateTruck(Map<String, Object> params) {
		return null;
	}
}
