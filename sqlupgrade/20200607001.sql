
create table wechatUserInfo(
	id  int identity (1,1),
	subscribe int,
	openId varchar(100),
	nickname varchar(100),
	sex int,
	city varchar(100),
	country varchar(100),
	province varchar(100),
	language varchar(100),
	headImgUrl varchar(100),
	subscribeTime varchar(19),
	remark varchar(200),
	groupId int,
)
GO
alter table partnerInfo add openId varchar(200)
GO