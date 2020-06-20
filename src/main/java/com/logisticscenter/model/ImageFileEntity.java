package com.logisticscenter.model;

import com.util.Utils;

import java.util.Calendar;

public class ImageFileEntity {

	public ImageFileEntity(){
		Calendar today = Calendar.getInstance();
		String currentdate = Utils.add0(today.get(Calendar.YEAR), 4) + "-" + Utils.add0(today.get(Calendar.MONTH) + 1, 2) + "-" + Utils.add0(today.get(Calendar.DAY_OF_MONTH), 2);

		String currenttime = Utils.add0(today.get(Calendar.HOUR_OF_DAY), 2) + ":" + Utils.add0(today.get(Calendar.MINUTE), 2) ;
		this.createDate = currentdate;
		this.createTime = currenttime;
	}
	/**
	 * 文件id
	 */
	private int id;
	
	/**
	 * 文件名称
	 */
	private String imageFileName;
	
	/**
	 * 文件MIME类型
	 */
	private String imagefiletype;
	
	/**
	 * 文件使用次数
	 */
	private int imagefileused;
	
	/**
	 * 文件存放目录
	 */
	private String filerealpath;
	
	/**
	 * 是否压缩
	 */
	private String iszip;
	
	/**
	 * 下载次数
	 */
	private int downloads;
	
	//创建日期
	private String createDate;
	//创建时间
	private String createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImagefiletype() {
		return imagefiletype;
	}

	public void setImagefiletype(String imagefiletype) {
		this.imagefiletype = imagefiletype;
	}

	public int getImagefileused() {
		return imagefileused;
	}

	public void setImagefileused(int imagefileused) {
		this.imagefileused = imagefileused;
	}

	public String getFilerealpath() {
		return filerealpath;
	}

	public void setFilerealpath(String filerealpath) {
		this.filerealpath = filerealpath;
	}

	public String getIszip() {
		return iszip;
	}

	public void setIszip(String iszip) {
		this.iszip = iszip;
	}

	public int getDownloads() {
		return downloads;
	}

	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
