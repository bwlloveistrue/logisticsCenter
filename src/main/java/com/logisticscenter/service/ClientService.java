package com.logisticscenter.service;

import java.util.List;
import java.util.Map;

import com.javabean.ClientBean;
import com.javabean.FeeTypeBean;

public interface ClientService {

	
	public abstract Map insertClient(Map<String, Object> params);
	
	public abstract Map getClient(Map<String, Object> params);

	public abstract Map getAllClient();
	
	public abstract Map deleteClient(Map<String, Object> params);
	
	public abstract Map updateClient(Map<String, Object> params);
}
