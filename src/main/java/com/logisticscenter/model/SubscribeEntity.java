package com.logisticscenter.model;

/**
 * 关注者entity
 */
public class SubscribeEntity {

	public SubscribeEntity() {

	}

	//标识ID
	private int id;
	//id
	private int key;
	// 是否关注
	private int subscribe;
	// 用户的标识，对当前公众号唯一
	private String openId;
	// 用户的昵称
	private String nickname;
	// 用户的性别，值为1时是男性，值为2时是女性
	private Integer sex;
	//用户的语言，简体中文为zh_CN
	private String language;
	//用户所在城市
	private String city;
	//用户所在省份
	private String province;
	//用户所在国家
	private String country;
	// 用户头像
	private String headImgUrl;
	// 关注时间
	private String subscribeTime;
	// 公众号运营者对粉丝的备注
	private String remark;
	// 用户所在的分组ID
	private Integer groupId;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		this.key = id;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

}
