package com.logisticscenter.mapper;

import com.logisticscenter.model.TruckGoodsOrderDetailEntity;
import com.logisticscenter.model.TruckGoodsOrderTakerEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderReceiptDao {
	
	/**
	 * @param insertInfo 预录车辆信息
	 * @return
	 */
	public abstract int insertTruckGoodsOrderTaker(TruckGoodsOrderTakerEntity insertInfo);
	
	/**
	 * 预录车辆详细信息
	 * @param insertInfo
	 * @return
	 */
	public abstract int insertTruckGoodsOrderDetail(TruckGoodsOrderDetailEntity insertInfo);
	
	/**
	 * 获得预录车辆信息
	 * @param selectInfo
	 * @return
	 */
	public abstract List<TruckGoodsOrderTakerEntity> getTruckGoodsOrderTaker(TruckGoodsOrderTakerEntity selectInfo);
	
	
	/**
	 * 获得预录车辆详细信息
	 * @param selectInfo
	 * @return
	 */
	public abstract List<TruckGoodsOrderDetailEntity> getTruckGoodsOrderDetail(TruckGoodsOrderDetailEntity selectInfo);

	 
	/**
	 * 根据ID删除预录信息
	 * @param id
	 * @return
	 */
	public abstract int deleteTruckGoodsOrderTaker(String id);
	
	/**
	 * 根据ID删除预录信息
	 * @param reportId
	 * @return
	 */
	public abstract int deleteTruckGoodsOrderDetail(String reportId);

	/**
	 * 根据ID删除预录信息
	 * @param delEntity
	 * @return
	 */
	public abstract int deleteTruckGoodsOrderDetail(TruckGoodsOrderDetailEntity delEntity);
	
	/**
	 * 更新预录信息(现在不用)
	 * @param updateInfo
	 */
	public abstract int updateTruckGoodsOrderTaker(TruckGoodsOrderTakerEntity updateInfo);
	
	/**
	 * 更新预录详细信息
	 * @param updateInfo
	 */
	public abstract void updateTruckGoodsOrderDetail(TruckGoodsOrderDetailEntity updateInfo);

	
}
