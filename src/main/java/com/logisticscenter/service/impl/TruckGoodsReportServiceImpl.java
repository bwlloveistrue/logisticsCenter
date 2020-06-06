package com.logisticscenter.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.CommonTransMethod;
import com.common.ConvertService;
import com.javabean.FeeTypeValueBean;
import com.javabean.TruckGoodsReportBean;
import com.javabean.TruckGoodsReportDetailBean;
import com.javabean.TruckSetBean;
import com.logisticscenter.mapper.TruckGoodsReportDao;
import com.logisticscenter.model.FeeTypeValueEntity;
import com.logisticscenter.model.TruckGoodsReportDetailEntity;
import com.logisticscenter.model.TruckGoodsReportEntity;
import com.logisticscenter.service.TruckGoodsReportService;
import com.util.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TruckGoodsReportServiceImpl implements TruckGoodsReportService {

	@Autowired
	TruckGoodsReportDao truckGoodsReportDao;

	@Autowired
	private CommonTransMethod commonTransMethod;

	@Override
	public int deleteTruckGoodsReport(String id) {
		int count = 0;
		count =  truckGoodsReportDao.deleteTruckGoodsReport(id);
		return count;
	}
	
	@Override
	public TruckGoodsReportBean getTruckGoodsReport(String id) {
		return (TruckGoodsReportBean) ConvertService.convertEntityToBean(truckGoodsReportDao.getTruckGoodsReport(id), new TruckGoodsReportBean());
	}

	@Override
	public List<TruckGoodsReportBean> getTruckGoodsReport(TruckGoodsReportBean selectInfo) {
		TruckGoodsReportEntity truckGoodsReportE = (TruckGoodsReportEntity)ConvertService.convertBeanToEntity(selectInfo,new TruckGoodsReportEntity());
		List<TruckGoodsReportEntity> entityList = new ArrayList<TruckGoodsReportEntity>();
		List<TruckGoodsReportBean> beanList = new ArrayList<TruckGoodsReportBean>();
//		int pageSize =Integer.parseInt(truckGoodsReportE.getPageSize());
//		int currentPage =Integer.parseInt(truckGoodsReportE.getCurrentPage());
//		currentPage = (currentPage -1)*pageSize;
//		truckGoodsReportE.setCurrentPage(currentPage+"");
//		entityList = truckGoodsReportDao.getTruckGoodsReport(truckGoodsReportE);
//		for(int i=0;i<entityList.size(); i++){
//			TruckGoodsReportBean dirverBean = (TruckGoodsReportBean) ConvertService.convertEntityToBean(entityList.get(i), new TruckGoodsReportBean());
//			beanList.add(dirverBean);
//		}
		return beanList;
	}
	
	@Override
	public List<TruckGoodsReportBean> getTruckGoodsReportExcel(TruckGoodsReportBean selectInfo) {
		TruckGoodsReportEntity truckGoodsReportE = (TruckGoodsReportEntity)ConvertService.convertBeanToEntity(selectInfo,new TruckGoodsReportEntity());
		List<TruckGoodsReportEntity> entityList = new ArrayList<TruckGoodsReportEntity>();
		List<TruckGoodsReportBean> beanList = new ArrayList<TruckGoodsReportBean>();
		entityList = truckGoodsReportDao.getTruckGoodsReportExcel(truckGoodsReportE);
		for(int i=0;i<entityList.size(); i++){
			TruckGoodsReportBean dirverBean = (TruckGoodsReportBean) ConvertService.convertEntityToBean(entityList.get(i), new TruckGoodsReportBean());
			beanList.add(dirverBean);
		}
		return beanList;
	}
	
	@Override
	public String getTruckGoodsReportByCount(TruckGoodsReportBean selectInfo) {
		String count = "";
		TruckGoodsReportEntity truckGoodsReportE = (TruckGoodsReportEntity)ConvertService.convertBeanToEntity(selectInfo,new TruckGoodsReportEntity());
		List<TruckGoodsReportEntity> entityList = new ArrayList<TruckGoodsReportEntity>();
		List<TruckGoodsReportBean> beanList = new ArrayList<TruckGoodsReportBean>();
		count = truckGoodsReportDao.getTruckGoodsReportByCount(truckGoodsReportE);
		return count;
	}

	@Override
	public void updateTruckGoodsReport(TruckGoodsReportBean updateInfo) {
		TruckGoodsReportEntity truckGoodsReportE = (TruckGoodsReportEntity)ConvertService.convertBeanToEntity(updateInfo,new TruckGoodsReportEntity());
		truckGoodsReportDao.updateTruckGoodsReport(truckGoodsReportE);
		
	}
	
	@Override
	public void updateAllTruckGoodsReport(TruckGoodsReportBean updateInfo) {
		TruckGoodsReportEntity TruckGoodsReportE = new TruckGoodsReportEntity();
		truckGoodsReportDao.updateAllTruckGoodsReport(TruckGoodsReportE);
		
	}

	@Override
	public synchronized int insertTruckGoodsReport(TruckGoodsReportBean insertInfo) {
		
		TruckGoodsReportEntity TruckGoodsReportE = (TruckGoodsReportEntity) ConvertService.convertBeanToEntity(insertInfo, new TruckGoodsReportEntity());
		//设置订单编号
		Cache cache = CacheManager.getCacheInfo("truckSettingBean_CACHE");
		String reportNumber = "";
		TruckSetBean bean= (TruckSetBean)cache.getValue();
		//preRule^YYYY^MM^DD^mm^SS^preRule
		String orderRule = bean.getOrderRule();
		String preRule = bean.getPreRule();
		int lastRule = ConvertService.getIntValue(bean.getLastRule(),1);
		Calendar cal = Calendar.getInstance();
		String orderRuleArr[] = orderRule.split("\\^");
		for(int i = 0;i<orderRuleArr.length;i++){
			if("preRule".equals(orderRuleArr[i])){
				reportNumber += preRule;
				continue;
			}
			if("YYYY".equals(orderRuleArr[i])){
				reportNumber += ConvertService.add0(cal.get(Calendar.YEAR),4);
				continue;
			}
			if("MM".equals(orderRuleArr[i])){
				reportNumber += ConvertService.add0(cal.get(Calendar.MONTH) + 1,2);
				continue;
			}
			if("DD".equals(orderRuleArr[i])){
				reportNumber += ConvertService.add0(cal.get(Calendar.DAY_OF_MONTH),2);
				continue;
			}
			if("HH".equals(orderRuleArr[i])){
				reportNumber += ConvertService.add0(cal.get(Calendar.HOUR_OF_DAY),2);
				continue;
			}
			if("mm".equals(orderRuleArr[i])){
				reportNumber += ConvertService.add0(cal.get(Calendar.MINUTE),2);
				continue;
			}
			if("SS".equals(orderRuleArr[i])){
				reportNumber += ConvertService.add0(cal.get(Calendar.SECOND),2);
				continue;
			}
			if("lastRule".equals(orderRuleArr[i])){
				//设置开始日期是否和今天是一致的,如果不一致那就叫说明是从其他地方导入进来的和今天不一致的数据
				String turnDate = "";
				String beginDate = TruckGoodsReportE.getBeginDate();
				Pattern p =  Pattern.compile(ConstantUtils.dateRegex);//复杂匹配  
				Matcher m = p.matcher(beginDate);  
				if(m.matches()){
					turnDate = beginDate.substring(0,4)+beginDate.substring(5,7)+beginDate.substring(8,10);
				}else{
					turnDate = ConvertService.getDate(ConstantUtils.dateFormat1);
				}
				reportNumber += ConvertService.add0(commonTransMethod.getReportNumber(turnDate,lastRule),lastRule);
				continue;
			}
			
		}
		TruckGoodsReportE.setReportNumber(reportNumber);
		TruckGoodsReportE.setCreateDate(ConvertService.getDate());
		TruckGoodsReportE.setCreateTime(ConvertService.getTime());
		int statusFlg = truckGoodsReportDao.insertTruckGoodsReport(TruckGoodsReportE);
		// TODO Auto-generated method stub
		return statusFlg;
	}
	
	public List<TruckGoodsReportBean> getAllTruckGoodsReport(){
		List<TruckGoodsReportEntity> entityList = new ArrayList<TruckGoodsReportEntity>();
		List<TruckGoodsReportBean> beanList = new ArrayList<TruckGoodsReportBean>();
		entityList = truckGoodsReportDao.getAllTruckGoodsReport();
		for(int i=0;i<entityList.size(); i++){
			TruckGoodsReportBean dirverBean = (TruckGoodsReportBean) ConvertService.convertEntityToBean(entityList.get(i), new TruckGoodsReportBean());
			beanList.add(dirverBean);
		}
		return beanList;
	}
	
	public List<TruckGoodsReportBean> getAllTruckGoodsReport(String reportStatus){
		List<TruckGoodsReportEntity> entityList = new ArrayList<TruckGoodsReportEntity>();
		List<TruckGoodsReportBean> beanList = new ArrayList<TruckGoodsReportBean>();
		entityList = truckGoodsReportDao.getAllTruckGoodsReport(reportStatus);
		for(int i=0;i<entityList.size(); i++){
			TruckGoodsReportBean dirverBean = (TruckGoodsReportBean) ConvertService.convertEntityToBean(entityList.get(i), new TruckGoodsReportBean());
			beanList.add(dirverBean);
		}
		return beanList;
	}

	/**
	 * @param reportId
	 * @param columnName
	 */
	@Override
	public List<FeeTypeValueBean> getColumnValue(String reportId, String columnName) {
		List<FeeTypeValueEntity> entityList = new ArrayList<FeeTypeValueEntity>();
		List<FeeTypeValueBean> beanList = new ArrayList<FeeTypeValueBean>();
		entityList = truckGoodsReportDao.getColumnValue(reportId,columnName);
		for(int i=0;i<entityList.size(); i++){
			FeeTypeValueBean dirverBean = (FeeTypeValueBean) ConvertService.convertEntityToBean(entityList.get(i), new FeeTypeValueBean());
			beanList.add(dirverBean);
		}
		return beanList;
		
	}
	
	@Override
	public List<TruckGoodsReportBean> getTruckGoodsReportImport(TruckGoodsReportBean selectInfo) {
		TruckGoodsReportEntity truckGoodsReportE = (TruckGoodsReportEntity)ConvertService.convertBeanToEntity(selectInfo,new TruckGoodsReportEntity());
		List<TruckGoodsReportEntity> entityList = new ArrayList<TruckGoodsReportEntity>();
		List<TruckGoodsReportBean> beanList = new ArrayList<TruckGoodsReportBean>();
//		int pageSize =Integer.parseInt(truckGoodsReportE.getPageSize());
//		int currentPage =Integer.parseInt(truckGoodsReportE.getCurrentPage());
//		currentPage = (currentPage -1)*pageSize;
//		truckGoodsReportE.setCurrentPage(currentPage+"");
//		entityList = truckGoodsReportDao.getTruckGoodsReportImport(truckGoodsReportE);
//		for(int i=0;i<entityList.size(); i++){
//			TruckGoodsReportBean dirverBean = (TruckGoodsReportBean) ConvertService.convertEntityToBean(entityList.get(i), new TruckGoodsReportBean());
//			beanList.add(dirverBean);
//		}
		return beanList;
	}
	
	
	@Override
	public synchronized int insertTruckGoodsReportDetail(TruckGoodsReportDetailBean insertInfo) {
		
		TruckGoodsReportDetailEntity TruckGoodsReportDetailE = (TruckGoodsReportDetailEntity) ConvertService.convertBeanToEntity(insertInfo, new TruckGoodsReportDetailEntity());
		TruckGoodsReportDetailE.setCreateDate(ConvertService.getDate());
		TruckGoodsReportDetailE.setCreateTime(ConvertService.getTime());
		TruckGoodsReportDetailE.setEditDate(ConvertService.getDate());
		TruckGoodsReportDetailE.setEditTime(ConvertService.getTime());
		int statusFlg = truckGoodsReportDao.insertTruckGoodsReportDetail(TruckGoodsReportDetailE);
		// TODO Auto-generated method stub
		return statusFlg;
	}
	
	@Override
	public List<TruckGoodsReportDetailBean> getTruckGoodsReportDetail(TruckGoodsReportDetailBean selectInfo) {
		TruckGoodsReportDetailEntity TruckGoodsReportDetailE = (TruckGoodsReportDetailEntity) ConvertService.convertBeanToEntity(selectInfo, new TruckGoodsReportDetailEntity());
		List<TruckGoodsReportDetailEntity> entityList = new ArrayList<TruckGoodsReportDetailEntity>();
		List<TruckGoodsReportDetailBean> beanList = new ArrayList<TruckGoodsReportDetailBean>();
		entityList = truckGoodsReportDao.getTruckGoodsReportDetail(TruckGoodsReportDetailE);
		for(int i=0;i<entityList.size(); i++){
			TruckGoodsReportDetailBean dirverBean = (TruckGoodsReportDetailBean) ConvertService.convertEntityToBean(entityList.get(i), new TruckGoodsReportDetailBean());
			beanList.add(dirverBean);
		}
		return beanList;
	}
	
	@Override
	public int updateTruckGoodsReportDetail(TruckGoodsReportDetailBean updateInfo) {
		int count = 0;
		TruckGoodsReportDetailEntity TruckGoodsReportDetailE = (TruckGoodsReportDetailEntity) ConvertService.convertBeanToEntity(updateInfo, new TruckGoodsReportDetailEntity());
		count = truckGoodsReportDao.updateTruckGoodsReportDetail(TruckGoodsReportDetailE);
		return count;
		
	}
	
	@Override
	public String getMaxReportNumber(String date) {
		String maxReportNumber = "";
		maxReportNumber = truckGoodsReportDao.getMaxReportNumber(date);
		return maxReportNumber;
		
	}

	@Override
	public int updateColumn(int id, String updColumnSql) {
		// TODO Auto-generated method stub
		int count = 0;
		count = truckGoodsReportDao.updateColumn(id,updColumnSql);
		return count;
	}

	@Override
	public int updateTruckGoodsReportExpense(
			TruckGoodsReportBean updateInfo) {
		int count = 0;
		TruckGoodsReportEntity TruckGoodsReportE = new TruckGoodsReportEntity();
		TruckGoodsReportE.setId(updateInfo.getId());
		TruckGoodsReportE.setExpensens(updateInfo.getExpensens());
		TruckGoodsReportE.setCost(updateInfo.getCost());
		TruckGoodsReportE.setProfit(updateInfo.getProfit());
		count = truckGoodsReportDao.updateTruckGoodsReportExpense(TruckGoodsReportE);
		return count;
	}

	@Override
	public List<TruckGoodsReportDetailBean> getTruckGoodsReportDetail(String truckOrders) {
		List<TruckGoodsReportDetailEntity> entityList = new ArrayList<TruckGoodsReportDetailEntity>();
		List<TruckGoodsReportDetailBean> beanList = new ArrayList<TruckGoodsReportDetailBean>();
		entityList = truckGoodsReportDao.getTruckGoodsReportDetail(truckOrders);
		for(int i=0;i<entityList.size(); i++){
			TruckGoodsReportDetailBean dirverBean = (TruckGoodsReportDetailBean) ConvertService.convertEntityToBean(entityList.get(i), new TruckGoodsReportDetailBean());
			beanList.add(dirverBean);
		}
		return beanList;
	}

	
	/*计算费用/盈利/运费的合计
	 *  (non-Javadoc)
	 * @see com.service.TruckGoodsReportService#getTruckGoodsReportSum(com.javabean.TruckGoodsReportBean)
	 */
	@Override
	public TruckGoodsReportBean getTruckGoodsReportSum(
			TruckGoodsReportBean selectInfo) {
		TruckGoodsReportEntity truckGoodsReportE = (TruckGoodsReportEntity)ConvertService.convertBeanToEntity(selectInfo,new TruckGoodsReportEntity());
		return (TruckGoodsReportBean) ConvertService.convertEntityToBean(truckGoodsReportDao.getTruckGoodsReportSum(truckGoodsReportE), new TruckGoodsReportBean());
	}
	
}
