package com.logisticscenter.mapper;

import com.logisticscenter.model.ClientEntity;
import com.logisticscenter.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
	
	public abstract UserEntity getCurrentUser(Map params);
	
	public abstract List<UserEntity> getUsers(Map params);

}
