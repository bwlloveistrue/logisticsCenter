package com.logisticscenter.service.impl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.CommonTransMethod;
import com.logisticscenter.mapper.ChartsDao;
import com.logisticscenter.model.ChartsEntity;
import com.logisticscenter.service.ChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChartsServiceImpl implements ChartsService {

	@Autowired
	ChartsDao chartsDao;

	@Autowired
	private CommonTransMethod commonTransMethod;

	public Map<String, Object> getClientChartsByMonth(Map<String, Object> params){
		String selectYear = (String)params.get("selectYear");
		List<ChartsEntity> ChartsEntityList = new ArrayList<ChartsEntity>();
		List clientNameList = new ArrayList();
		List yDetailList = new ArrayList();
		String yDetail = "";
		Map retResult = new HashMap();
		try{
			ChartsEntityList = chartsDao.getDriverChartsByMonth(selectYear);
			for(int i=0;i<ChartsEntityList.size();i++){
				if(!yDetail.equals("")){
					yDetail = "";
				}
				clientNameList.add(commonTransMethod.getClientName(ChartsEntityList.get(i).getClient()));
				yDetail += commonTransMethod.getClientName(ChartsEntityList.get(i).getClient()) + ",";
				yDetail += ChartsEntityList.get(i).getJan()
						+","+ChartsEntityList.get(i).getFeb()
						+","+ChartsEntityList.get(i).getMar()
						+","+ChartsEntityList.get(i).getApr()
						+","+ChartsEntityList.get(i).getMay()
						+","+ChartsEntityList.get(i).getJun()
						+","+ChartsEntityList.get(i).getJul()
						+","+ChartsEntityList.get(i).getAug()
						+","+ChartsEntityList.get(i).getSept()
						+","+ChartsEntityList.get(i).getOct()
						+","+ChartsEntityList.get(i).getNov()
						+","+ChartsEntityList.get(i).getDec();
				yDetailList.add(yDetail);
			}
			retResult.put("retDetail",yDetailList);
			return retResult;

		}catch(Exception e){
			e.printStackTrace();
		}
		return retResult;
	}
	
	public Map<String, Object> getDriverChartsByMonth(Map<String, Object> params){
		String selectYear = (String)params.get("selectYear");
		List<ChartsEntity> ChartsEntityList = new ArrayList<ChartsEntity>();
		List clientNameList = new ArrayList();
		List yDetailList = new ArrayList();
		String yDetail = "";
		Map retResult = new HashMap();
		try{
			ChartsEntityList = chartsDao.getDriverChartsByMonth(selectYear);
			for(int i=0;i<ChartsEntityList.size();i++){
				if(!yDetail.equals("")){
					yDetail = "";
				}
				clientNameList.add(commonTransMethod.getDriverName(ChartsEntityList.get(i).getDriver()));
				yDetail += commonTransMethod.getDriverName(ChartsEntityList.get(i).getDriver()) + ",";
				yDetail += ChartsEntityList.get(i).getJan()
						+","+ChartsEntityList.get(i).getFeb()
						+","+ChartsEntityList.get(i).getMar()
						+","+ChartsEntityList.get(i).getApr()
						+","+ChartsEntityList.get(i).getMay()
						+","+ChartsEntityList.get(i).getJun()
						+","+ChartsEntityList.get(i).getJul()
						+","+ChartsEntityList.get(i).getAug()
						+","+ChartsEntityList.get(i).getSept()
						+","+ChartsEntityList.get(i).getOct()
						+","+ChartsEntityList.get(i).getNov()
						+","+ChartsEntityList.get(i).getDec();
				yDetailList.add(yDetail);
			}
			retResult.put("retDetail",yDetailList);
			return retResult;
		}catch(Exception e){
			e.printStackTrace();
		}
		return retResult;
	}
	
	public Map<String, Object> getClientFeeChartsByMonth(Map<String, Object> params){
		String selectYear = (String)params.get("selectYear");
		String columnNames = commonTransMethod.getAllFeeTypeColumn();
		List<ChartsEntity> ChartsEntityList = new ArrayList<ChartsEntity>();
		List clientNameList = new ArrayList();
		List yDetailList = new ArrayList();
		String yDetail = "";
		Map retResult = new HashMap();
		try{
			ChartsEntityList = chartsDao.getClientFeeChartsByMonth(selectYear, columnNames);
			for(int i=0;i<ChartsEntityList.size();i++){
				if(!yDetail.equals("")){
					yDetail = "";
				}
				clientNameList.add(commonTransMethod.getDriverName(ChartsEntityList.get(i).getDriver()));
				yDetail += commonTransMethod.getDriverName(ChartsEntityList.get(i).getDriver()) + ",";
				yDetail += ChartsEntityList.get(i).getJan()
						+","+ChartsEntityList.get(i).getFeb()
						+","+ChartsEntityList.get(i).getMar()
						+","+ChartsEntityList.get(i).getApr()
						+","+ChartsEntityList.get(i).getMay()
						+","+ChartsEntityList.get(i).getJun()
						+","+ChartsEntityList.get(i).getJul()
						+","+ChartsEntityList.get(i).getAug()
						+","+ChartsEntityList.get(i).getSept()
						+","+ChartsEntityList.get(i).getOct()
						+","+ChartsEntityList.get(i).getNov()
						+","+ChartsEntityList.get(i).getDec();
				yDetailList.add(yDetail);
			}
			retResult.put("retDetail",yDetailList);
			return retResult;
		}catch(Exception e){
			e.printStackTrace();
		}
		return retResult;
	}
	
	public Map<String, Object> getDriverFeeChartsByMonth(Map<String, Object> params){
		String selectYear = (String)params.get("selectYear");
		List<ChartsEntity> ChartsEntityList = new ArrayList<ChartsEntity>();
		//获得费用字段
		String columnNames = commonTransMethod.getAllFeeTypeColumn();
		List clientNameList = new ArrayList();
		List yDetailList = new ArrayList();
		String yDetail = "";
		Map retResult = new HashMap();
		try{
			clientNameList = chartsDao.getDriverFeeChartsByMonth(selectYear,columnNames);
			for(int i=0;i<ChartsEntityList.size();i++){
				if(!yDetail.equals("")){
					yDetail = "";
				}
				clientNameList.add(commonTransMethod.getClientName(ChartsEntityList.get(i).getClient()));
				yDetail += commonTransMethod.getClientName(ChartsEntityList.get(i).getClient()) + ",";
				yDetail += ChartsEntityList.get(i).getJan()
						+","+ChartsEntityList.get(i).getFeb()
						+","+ChartsEntityList.get(i).getMar()
						+","+ChartsEntityList.get(i).getApr()
						+","+ChartsEntityList.get(i).getMay()
						+","+ChartsEntityList.get(i).getJun()
						+","+ChartsEntityList.get(i).getJul()
						+","+ChartsEntityList.get(i).getAug()
						+","+ChartsEntityList.get(i).getSept()
						+","+ChartsEntityList.get(i).getOct()
						+","+ChartsEntityList.get(i).getNov()
						+","+ChartsEntityList.get(i).getDec();
				yDetailList.add(yDetail);
			}
			retResult.put("retDetail",yDetailList);
			return retResult;
		}catch(Exception e) {

		}
		return retResult;

	}

}
