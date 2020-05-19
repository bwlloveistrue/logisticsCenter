package com.logisticscenter.controller;

import com.javabean.LabelSetBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Timestamp;

@RestController
@RequestMapping(value = "/api/labelSet")
public class labelSetController implements Serializable{
	
	private LabelSetBean lsb;
	
	private int count;
	
	private int id;
	
	private String type;

	private String lableName;
	
	private String URL;
	
	private int sort;
	
	private int ishow;
	
	private Timestamp createTime;
	
	private Timestamp editTime;
	
	private HttpServletRequest req; 
	
	public String labelSet(HttpServletRequest req){
	for(int i = 1;i<=count;i++){
		lsb = new LabelSetBean();
		lsb.setType(req.getParameter("type"+i).toString());
		lsb.setLableName(req.getParameter("labelName"+i).toString());
		lsb.setURL(req.getParameter("URL"+i).toString());
		lsb.setIshow(Integer.parseInt(req.getParameter("ishow"+i).toString()));
		lsb.setSort(Integer.parseInt(req.getParameter("sort"+i).toString()));
	}
	req.getAttribute("URL");


		return "success";
	}

	public LabelSetBean getLsb() {
		return lsb;
	}

	public void setLsb(LabelSetBean lsb) {
		this.lsb = lsb;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLableName() {
		return lableName;
	}

	public void setLableName(String lableName) {
		this.lableName = lableName;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getIshow() {
		return ishow;
	}

	public void setIshow(int ishow) {
		this.ishow = ishow;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getEditTime() {
		return editTime;
	}

	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
