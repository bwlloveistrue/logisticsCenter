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
  insert into workflowType(flowType,description) values (0,'Ԥ¼')
  GO
  insert into workflowType(flowType,description) values (1,'����')
  GO
  insert into workflowType(flowType,description) values (2,'��ִ���')
  GO
  insert into workflowType(flowType,description) values (3,'����װ���ص�')
  GO
  insert into workflowType(flowType,description) values (4,'װ�����')
  GO
  insert into workflowType(flowType,description) values (5,'�ʹ�')
  GO
  insert into workflowType(flowType,description) values (6,'���')
  GO
  create table sendType(
	id  int identity (1,1),
	sendType varchar(100),
	description varchar(100)
  )
  GO
  insert into sendType (sendType,description) values (0,'����Ա')
  GO
  insert into sendType (sendType,description) values (0,'˾��')
  GO
  insert into sendType (sendType,description) values (0,'�ͻ�')
  GO