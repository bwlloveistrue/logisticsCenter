create table partnerInfo(
	id  int identity (1,1),
	partner varchar(100),
	address varchar(400),
	mobile varchar(100)
  )
  GO
alter table truckGoodsReportDetail add driver int
GO
alter table truckGoodsReportDetail add partnerCarry decimal(10,4)
GO
alter table truckGoodsReportDetail add partnerPrice decimal(10,4)
GO
alter table truckGoodsReportDetail add partner int
GO
alter table truckGoodsReportDetail add truckPart int
GO
alter table truckGoodsReportDetail add truckNumber int
GO
alter table truckGoodsReportDetail drop column price
GO
alter table truckGoodsReportDetail drop column realcarry
GO
alter table truckGoodsReportDetail drop column invoiceflg
GO
alter table truckGoodsReportDetail drop column liftingcost
GO
alter table truckGoodsReportDetail drop column startplace
GO
alter table truckGoodsReportDetail drop column endplace
GO
alter table truckGoodsReportDetail add reportId int
GO
alter table truckGoodsReport add profit decimal(10,4)
 GO
 alter table truckGoodsReport add cost decimal(10,4)
 GO
 alter table truckGoodsReport add customerOrder varchar(400)
 GO
 alter table truckGoodsReport add settlement int
 GO
CREATE TABLE truckGoodsReceiptDetail(
	id int IDENTITY(1,1) NOT NULL,
	reportId int NULL,
	goodsType int NULL,
	deleteFlg int NULL,
	price decimal(10, 4) NULL,
	realCarry decimal(10, 4) NULL,
	invoiceFlg int NULL,
	startPlace varchar(200) NULL,
	endPlace varchar(200) NULL,
	receiptId int,
	createDate varchar(10) NULL,
	createTime varchar(5) NULL,
	editDate varchar(10) NULL,
	editTime varchar(5) NULL
)