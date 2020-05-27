package com.logisticscenter.model;

import com.util.Utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DriverInfoEntity {

	public DriverInfoEntity() {
		Calendar today = Calendar.getInstance();
		String currentdate = Utils.add0(today.get(Calendar.YEAR), 4) + "-" + Utils.add0(today.get(Calendar.MONTH) + 1, 2) + "-" + Utils.add0(today.get(Calendar.DAY_OF_MONTH), 2);
		String currenttime = Utils.add0(today.get(Calendar.HOUR_OF_DAY), 2) + ":" + Utils.add0(today.get(Calendar.MINUTE), 2) + ":" + Utils.add0(today.get(Calendar.SECOND), 2);
		this.editDate = currentdate;
		this.editTime = currenttime;
		this.createDate = currentdate;
		this.createTime = currenttime;
	}

	//标识ID
	private int id;
	//司机姓名
	private String name;
	//司机性别
	private String sex;
	//司机国籍
	private String nativePlace;
	//司机文化程度
	private String education;
	//司机生日日期
	private String birthday;
	//年龄
	private int age;
	//住址
	private String address;
	//联系电话
	private String contactNmuber;
	//手机号码
	private String mobile;
	//身份证号码
	private String idNumber;
	//入职时间
	private String startWorkDate;
	//驾驶证号
	private String driverLicense;
	//驾驶车辆号码
	private String truckNumber;
	//评价
	private String appraise;
	//工资标准
	private String salary;
	//备注
	private String remark;
	//职位
	private String job;
	//创建日期
	private String createDate;
	//创建时间
	private String createTime;
	
	//编辑日期
	private String editDate;
	//编辑时间
	private String editTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNmuber() {
		return contactNmuber;
	}
	public void setContactNmuber(String contactNmuber) {
		this.contactNmuber = contactNmuber;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getStartWorkDate() {
		return startWorkDate;
	}
	public void setStartWorkDate(String startWorkDate) {
		this.startWorkDate = startWorkDate;
	}
	public String getDriverLicense() {
		return driverLicense;
	}
	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}
	public String getTruckNumber() {
		return truckNumber;
	}
	public void setTruckNumber(String truckNumber) {
		this.truckNumber = truckNumber;
	}
	public String getAppraise() {
		return appraise;
	}
	public void setAppraise(String appraise) {
		this.appraise = appraise;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
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
	public String getEditDate() {
		return editDate;
	}
	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	
	public Map<String,String> toMap(){
		Map driverInfoMap = new HashMap();
		driverInfoMap.put("id",this.getId());
		driverInfoMap.put("name",this.getName());
		driverInfoMap.put("sex",this.getSex());
		driverInfoMap.put("nativePlace",this.getNativePlace());
		driverInfoMap.put("education",this.getEducation());
		driverInfoMap.put("birthday",this.getBirthday());
		driverInfoMap.put("age",this.getAge());
		driverInfoMap.put("address",this.getAddress());
		driverInfoMap.put("contactNmuber",this.getContactNmuber());
		driverInfoMap.put("mobile",this.getMobile());
		driverInfoMap.put("idNumber",this.getIdNumber());
		driverInfoMap.put("startWorkDate",this.getStartWorkDate());
		driverInfoMap.put("driverLicense",this.getDriverLicense());
		driverInfoMap.put("truckNumber",this.getTruckNumber());
		driverInfoMap.put("appraise",this.getAppraise());
		driverInfoMap.put("salary",this.getSalary());
		driverInfoMap.put("remark",this.getRemark());
		driverInfoMap.put("job",this.getJob());
		driverInfoMap.put("createDate",this.getCreateDate());
		driverInfoMap.put("createTime",this.getCreateTime());
		driverInfoMap.put("editDate",this.getEditDate());
		driverInfoMap.put("editTime",this.getEditTime());
		return driverInfoMap;
	}
	
}
