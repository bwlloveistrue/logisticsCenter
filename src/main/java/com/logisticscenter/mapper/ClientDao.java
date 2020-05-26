package com.logisticscenter.mapper;

import com.logisticscenter.model.ClientEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientDao {
	
	public abstract int insertClient(ClientEntity insertInfo);

	public abstract List<ClientEntity> getClient(ClientEntity selectInfo);

	public abstract List<ClientEntity> getAllClient();
	
	public abstract int deleteClient(String id);
	
	public abstract int updateClient(ClientEntity selectInfo);

}
