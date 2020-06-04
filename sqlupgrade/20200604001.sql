create table messageInfo(
	id  int identity (1,1),
	objType varchar(100),
	messageType varchar(100),
	mouldId varchar(100)
  )
  GO
  create table workflowType(
	id  int identity (1,1),
	flowType int,
	description varchar(100)
  )
  GO
  insert into workflowType(flowType,description) values (0,'预录')
  GO
  insert into workflowType(flowType,description) values (1,'分配')
  GO
  insert into workflowType(flowType,description) values (2,'回执完成')
  GO
  insert into workflowType(flowType,description) values (3,'到达装货地点')
  GO
  insert into workflowType(flowType,description) values (4,'装货完成')
  GO
  insert into workflowType(flowType,description) values (5,'送达')
  GO
  insert into workflowType(flowType,description) values (6,'完成')
  GO
  create table sendType(
	id  int identity (1,1),
	sendType varchar(100),
	description varchar(100)
  )
  GO
  insert into sendType (sendType,description) values (0,'管理员')
  GO
  insert into sendType (sendType,description) values (0,'司机')
  GO
  insert into sendType (sendType,description) values (0,'客户')
  GO