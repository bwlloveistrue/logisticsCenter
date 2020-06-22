package com.logisticscenter.mapper;

import com.logisticscenter.model.CompulsoryEntity;
import com.logisticscenter.model.CompulsoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 卜
 *
 */
@Mapper
public interface CompulsoryDao {

	public abstract int insertCompulsoryInfo(CompulsoryEntity insertInfo);

	public abstract List<CompulsoryEntity> getCompulsoryInfo(CompulsoryEntity selectInfo);

	public abstract List<CompulsoryEntity> getAllCompulsory();

	public abstract int deleteCompulsoryInfo(String id);

	public abstract void updateCompulsoryInfo(CompulsoryEntity selectInfo);
}
