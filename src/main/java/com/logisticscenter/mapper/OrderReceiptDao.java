package com.logisticscenter.mapper;

import com.logisticscenter.model.TruckGoodsOrderDetailEntity;
import com.logisticscenter.model.TruckGoodsOrderTakerEntity;
import com.logisticscenter.model.TruckGoodsReportDetailEntity;
import com.logisticscenter.model.TruckGoodsReportEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderReceiptDao {

	/**
	 * @param insertInfo 分配信息
	 * @return
	 */
	public abstract int insertTruckGoodsReport(TruckGoodsReportEntity insertInfo);

	/**
	 * 删除分配信息
	 * @param delIds
	 * @return
	 */
	public abstract int deleteTruckGoodsReport(String delIds);

	/**
	 * 删除分配信息
	 * @param delIds
	 * @return
	 */
	public abstract int deleteReportByOrderId(String delIds);

	/**
	 * 获得分配车辆信息
	 * @param selectInfo
	 * @return
	 */
	public abstract List<TruckGoodsReportEntity> getTruckGoodsReport(TruckGoodsReportEntity selectInfo);


	/**
	 * 更新预录详细信息
	 * @param updateInfo
	 */
	public abstract void updateTruckGoodsReport(TruckGoodsReportEntity updateInfo);

	
}
