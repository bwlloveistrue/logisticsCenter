package com.logisticscenter.mapper;

import com.logisticscenter.model.TruckGoodsOrderDetailEntity;
import com.logisticscenter.model.TruckGoodsOrderTakerEntity;
import com.logisticscenter.model.TruckGoodsReportDetailEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderApportionDao {
	
	/**
	 * @param insertInfo 分配信息
	 * @return
	 */
	public abstract int insertTruckGoodsReportDetail(TruckGoodsReportDetailEntity insertInfo);
	
	/**
	 * 删除分配信息
	 * @param delIds
	 * @return
	 */
	public abstract int deleteTruckGoodsReportDetail(String delIds);

	/**
	 * 删除分配信息
	 * @param delIds
	 * @return
	 */
	public abstract int deleteReportDetailByOrderId(String delIds);
	
	/**
	 * 获得分配车辆信息
	 * @param selectInfo
	 * @return
	 */
	public abstract List<TruckGoodsReportDetailEntity> getTruckGoodsReportDetail(TruckGoodsReportDetailEntity selectInfo);

	
	/**
	 * 更新预录详细信息
	 * @param updateInfo
	 */
	public abstract void updateTruckGoodsReportDetail(TruckGoodsOrderDetailEntity updateInfo);

	
}
