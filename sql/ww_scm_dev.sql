/*
SQLyog Ultimate v8.32 
MySQL - 5.7.24-log : Database - ww_scm_dev
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ww_scm_dev` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ww_scm_dev`;

/*Table structure for table `scm_cg_contractproduct` */

DROP TABLE IF EXISTS `scm_cg_contractproduct`;

CREATE TABLE `scm_cg_contractproduct` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `contractCode` varchar(64) DEFAULT NULL COMMENT '合同编号',
  `productId` varchar(64) DEFAULT NULL COMMENT '产品ID',
  `ProductName` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `ProductCode` varchar(255) DEFAULT NULL COMMENT '产品编码',
  `productCname` varchar(255) DEFAULT NULL COMMENT '产品通用名称',
  `productGauge` varchar(255) DEFAULT NULL COMMENT '品规',
  `medicineTypeId` varchar(64) DEFAULT NULL COMMENT '药品类型ID',
  `medicineType` varchar(255) DEFAULT NULL COMMENT '药品类型',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `unitPrice` double(10,2) DEFAULT NULL COMMENT '单价',
  `productSum` int(10) DEFAULT NULL COMMENT '数量',
  `sumPrice` double(10,2) DEFAULT NULL COMMENT '总金额',
  `currency` varchar(255) DEFAULT NULL COMMENT '币种',
  `approvalNumber` varchar(255) DEFAULT NULL COMMENT '批准文号/启用批号',
  `contractId` varchar(64) DEFAULT NULL COMMENT '采购合同ID',
  `achange` int(10) DEFAULT NULL COMMENT '件装量',
  `surplusAmount` int(10) DEFAULT NULL COMMENT '剩余数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购合同商品表';

/*Data for the table `scm_cg_contractproduct` */

insert  into `scm_cg_contractproduct`(`id`,`contractCode`,`productId`,`ProductName`,`ProductCode`,`productCname`,`productGauge`,`medicineTypeId`,`medicineType`,`unit`,`unitPrice`,`productSum`,`sumPrice`,`currency`,`approvalNumber`,`contractId`,`achange`,`surplusAmount`) values ('9621024','CGHT1902281642128135','6651015','奥泰灵0.75g(10粒/盒)','BB-93A','盐酸氨基葡萄糖胶囊','0.75g*10粒/盒','6001042','普通药品','盒',22.00,133,2926.00,'CNY','测试','9621023',600,100),('9621025','CGHT1902281642128135','6651016','奥泰灵0.75g(60粒/盒)','BB-93D','盐酸氨基葡萄糖胶囊','0.75g*60粒/盒','6001042','普通药品','盒',888.00,97,86136.00,'CNY','测试','9621023',150,9),('9621026','CGHT1902281642128135','6651018','澳米沙(30ml/瓶)','BO-15','克林霉素磷酸酯外用溶液','1％*30ml/瓶','6001042','普通药品','瓶',88.00,89,7832.00,'CNY','6766','9621023',160,67),('9691009','CGHT1902281844540917','6651015','奥泰灵0.75g(10粒/盒)','BB-93A','盐酸氨基葡萄糖胶囊','0.75g*10粒/盒','6001042','普通药品','盒',2.00,1,2.00,'CNY','测试','9691008',600,1),('9691012','CGHT1902281446839283','6651015','奥泰灵0.75g(10粒/盒)','BB-93A','盐酸氨基葡萄糖胶囊','0.75g*10粒/盒','6001042','普通药品','盒',5.00,1,5.00,'CNY','测试','9691011',600,1),('9691020','CGHT1902281911824949','6651015','奥泰灵0.75g(10粒/盒)','BB-93A','盐酸氨基葡萄糖胶囊','0.75g*10粒/盒','6001042','普通药品','盒',12.00,111,1332.00,'CNY','测试','9691019',600,111),('9691025','CGHT1902281091463144','6651015','奥泰灵0.75g(10粒/盒)','BB-93A','盐酸氨基葡萄糖胶囊','0.75g*10粒/盒','6001042','普通药品','盒',33.00,111,3663.00,'CNY','测试','9691024',600,111),('9691032','CGHT1902280931377557','6651015','奥泰灵0.75g(10粒/盒)','BB-93A','盐酸氨基葡萄糖胶囊','0.75g*10粒/盒','6001042','普通药品','盒',11.00,111,1221.00,'CNY','测试','9691031',600,111),('9971002','CGHT1903010617388713','6651015','奥泰灵0.75g(10粒/盒)','BB-93A','盐酸氨基葡萄糖胶囊','0.75g*10粒/盒','6001042','普通药品','盒',10.00,111,1110.00,'CNY','测试','9971001',600,111);

/*Table structure for table `scm_cg_procurementcontract` */

DROP TABLE IF EXISTS `scm_cg_procurementcontract`;

CREATE TABLE `scm_cg_procurementcontract` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `supplierId` varchar(64) DEFAULT NULL COMMENT '供应商id',
  `supplier` varchar(255) DEFAULT NULL COMMENT '供应商',
  `speciesSum` int(11) DEFAULT NULL COMMENT '供货产品种类数',
  `productSum` int(11) DEFAULT NULL COMMENT '产品总数量',
  `sumPrice` double(11,2) DEFAULT NULL COMMENT '产品总金额',
  `currency` varchar(64) DEFAULT NULL COMMENT '币种',
  `PayType` varchar(255) DEFAULT NULL COMMENT '付款方式',
  `filingDate` datetime DEFAULT NULL COMMENT '申请日期',
  `successDate` datetime DEFAULT NULL COMMENT '通过日期',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批表ID',
  `approvalState` char(1) DEFAULT '0' COMMENT '审批状态',
  `operatorId` varchar(64) DEFAULT NULL COMMENT '操作人ID',
  `operatorName` varchar(255) DEFAULT NULL COMMENT '操作人',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `contractCode` varchar(255) DEFAULT NULL COMMENT '合同编号',
  PRIMARY KEY (`id`),
  KEY `INDEX_SUPPLIERID_UNQ` (`supplierId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `scm_cg_procurementcontract` */

insert  into `scm_cg_procurementcontract`(`id`,`supplierId`,`supplier`,`speciesSum`,`productSum`,`sumPrice`,`currency`,`PayType`,`filingDate`,`successDate`,`approvalId`,`approvalState`,`operatorId`,`operatorName`,`ownerId`,`contractCode`) values ('9621023','7281002','澳美制药（海南）有限公司',3,319,96894.00,'CNY',NULL,'2019-02-28 00:45:17',NULL,'5004722','2','7621007','原浩','7111011','CGHT1902281642128135'),('9681010','7281002','澳美制药（海南）有限公司',0,0,0.00,'CNY',NULL,NULL,NULL,NULL,'0','7621007','原浩','7111011','CGHT1902280803239736'),('9691001','7281002','澳美制药（海南）有限公司',0,0,0.00,'CNY',NULL,NULL,NULL,NULL,'0','7621007','原浩','7111011','CGHT1902280272323938'),('9691002','7281002','澳美制药（海南）有限公司',0,0,0.00,'CNY',NULL,NULL,NULL,NULL,'0','7621007','原浩','7111011','CGHT1902281686153199'),('9691004','7281002','澳美制药（海南）有限公司',0,0,0.00,'CNY',NULL,NULL,NULL,NULL,'0','7621007','原浩','7111011','CGHT1902281404414996'),('9691008','7281002','澳美制药（海南）有限公司',1,1,2.00,'CNY',NULL,NULL,NULL,NULL,'0','7621007','原浩','7111011','CGHT1902281844540917'),('9691011','7281002','澳美制药（海南）有限公司',1,1,5.00,'CNY',NULL,NULL,NULL,NULL,'0','7621007','原浩','7111011','CGHT1902281446839283'),('9691013','7281002','澳美制药（海南）有限公司',0,0,0.00,'CNY',NULL,NULL,NULL,NULL,'0','7621007','原浩','7111011','CGHT1902281550438388'),('9691019','7281002','澳美制药（海南）有限公司',1,111,1332.00,'CNY',NULL,'2019-02-28 09:58:55',NULL,'5005312','2','7621007','原浩','7111011','CGHT1902281911824949'),('9691024','7281002','澳美制药（海南）有限公司',1,111,3663.00,'CNY',NULL,'2019-02-28 10:06:39',NULL,'5005342','2','7621007','原浩','7111011','CGHT1902281091463144'),('9691031','7281002','澳美制药（海南）有限公司',1,111,1221.00,'CNY',NULL,'2019-02-28 10:11:45',NULL,'5005424','1','7621007','原浩','7111011','CGHT1902280931377557'),('9971001','7281002','澳美制药（海南）有限公司',1,111,1110.00,'CNY',NULL,NULL,NULL,NULL,'0','7621007','原浩','7111011','CGHT1903010617388713');

/*Table structure for table `scm_cg_procurementorder` */

DROP TABLE IF EXISTS `scm_cg_procurementorder`;

CREATE TABLE `scm_cg_procurementorder` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `procurementCode` varchar(255) DEFAULT NULL COMMENT '采购订单号',
  `supplierId` varchar(64) DEFAULT NULL COMMENT '供应商id',
  `supplier` varchar(255) DEFAULT NULL COMMENT '供应商名称',
  `contractCode` varchar(255) DEFAULT NULL COMMENT '合同编号',
  `speciesSum` int(10) DEFAULT NULL COMMENT '品种数',
  `productSum` int(10) DEFAULT NULL COMMENT '数量',
  `sumPrice` double(10,2) DEFAULT NULL COMMENT '金额',
  `currency` varchar(255) DEFAULT NULL COMMENT '币种',
  `orderDate` datetime DEFAULT NULL COMMENT '下单日期',
  `isToWMS` char(1) DEFAULT '0' COMMENT '是否推送至WMS',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` char(1) DEFAULT NULL COMMENT '审批状态',
  `getTicket` varchar(255) DEFAULT NULL COMMENT '收票方',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `operatorId` varchar(64) DEFAULT NULL COMMENT '操作人ID',
  `operatorName` varchar(255) DEFAULT NULL COMMENT '操作人',
  `contractId` varchar(64) DEFAULT NULL COMMENT '采购合同ID',
  `deliveryDate` datetime DEFAULT NULL COMMENT '到货日期',
  `getMoneyWarehouse` varchar(255) DEFAULT NULL COMMENT '收货仓库',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购订单表';

/*Data for the table `scm_cg_procurementorder` */

insert  into `scm_cg_procurementorder`(`id`,`procurementCode`,`supplierId`,`supplier`,`contractCode`,`speciesSum`,`productSum`,`sumPrice`,`currency`,`orderDate`,`isToWMS`,`approvalId`,`approvalState`,`getTicket`,`ownerId`,`operatorId`,`operatorName`,`contractId`,`deliveryDate`,`getMoneyWarehouse`) values ('9621027','CGDD1902280734580992','7281002','澳美制药（海南）有限公司','CGHT1902281642128135',3,143,80806.00,'CNY',NULL,'0','5004752','2','博成','7111011','7621007','原浩','9621023','2019-02-28 00:00:00',NULL);

/*Table structure for table `scm_cg_procurementplan` */

DROP TABLE IF EXISTS `scm_cg_procurementplan`;

CREATE TABLE `scm_cg_procurementplan` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `productCode` varchar(255) DEFAULT NULL COMMENT '商品编码',
  `supplier` varchar(255) DEFAULT NULL COMMENT '供应商',
  `productCname` varchar(255) DEFAULT NULL COMMENT '产品品规',
  `inventoryCode` varchar(255) DEFAULT NULL COMMENT '存货代码',
  `month` varchar(64) DEFAULT NULL COMMENT '月份',
  `unitPrice` int(10) DEFAULT NULL COMMENT '采购数量',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `createPersion` varchar(255) DEFAULT NULL COMMENT '创建人',
  `memo` varchar(600) DEFAULT NULL COMMENT '备注',
  `isAffirm` char(1) DEFAULT '0' COMMENT '是否确认(1:已确认)',
  `isSupplierMate` char(1) DEFAULT '0' COMMENT '供应商是否匹配成功',
  `supplierId` varchar(64) DEFAULT NULL COMMENT '供应商ID',
  `isProductMate` char(1) DEFAULT '0' COMMENT '商品是否匹配成功',
  `ProcurementContractId` varchar(64) DEFAULT NULL COMMENT '商品ID',
  `approvalNumber` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `operatorId` varchar(64) DEFAULT NULL COMMENT '操作人ID',
  `operatorName` varchar(255) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购计划表';

/*Data for the table `scm_cg_procurementplan` */

/*Table structure for table `scm_cg_procurementreturn` */

DROP TABLE IF EXISTS `scm_cg_procurementreturn`;

CREATE TABLE `scm_cg_procurementreturn` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `returnCode` varchar(255) DEFAULT NULL COMMENT '退货单号',
  `supplierId` varchar(64) DEFAULT NULL COMMENT '供应商id',
  `supplier` varchar(255) DEFAULT NULL COMMENT '供应商',
  `getMoneyWarehouseId` varchar(64) DEFAULT NULL COMMENT '仓库ID',
  `getMoneyWarehouse` varchar(255) DEFAULT NULL COMMENT '仓库',
  `speciesSum` int(10) DEFAULT NULL COMMENT '品种数',
  `sumPrice` double(10,2) DEFAULT NULL COMMENT '退货总金额',
  `returnDate` datetime DEFAULT NULL COMMENT '退货日期',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` char(1) DEFAULT NULL COMMENT '审批状态',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `operatorId` varchar(64) DEFAULT NULL COMMENT '操作人ID',
  `operatorName` varchar(255) DEFAULT NULL COMMENT '操作人',
  `procurementId` varchar(64) DEFAULT NULL COMMENT '采购订单ID',
  `procurementCode` varchar(255) DEFAULT NULL COMMENT '采购订单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购退货表';

/*Data for the table `scm_cg_procurementreturn` */

/*Table structure for table `scm_cg_proorderproduct` */

DROP TABLE IF EXISTS `scm_cg_proorderproduct`;

CREATE TABLE `scm_cg_proorderproduct` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `proOrderId` varchar(64) DEFAULT NULL COMMENT '订单ID',
  `productId` varchar(64) DEFAULT NULL COMMENT '商品ID',
  `productName` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `productCode` varchar(255) DEFAULT NULL COMMENT '产品编码',
  `productCname` varchar(255) DEFAULT NULL COMMENT '商品通用名',
  `productGauge` varchar(255) DEFAULT NULL COMMENT '品规',
  `MedicineTypeId` varchar(64) DEFAULT NULL COMMENT '药品类型ID',
  `MedicineType` varchar(255) DEFAULT NULL COMMENT '药品类型',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `unitPrice` double(10,2) DEFAULT NULL COMMENT '单价',
  `sumPrice` double(10,2) DEFAULT NULL COMMENT '总金额',
  `currency` varchar(255) DEFAULT NULL COMMENT '币种',
  `Achange` varchar(255) DEFAULT NULL COMMENT '件装量',
  `productSum` int(10) DEFAULT NULL COMMENT '采购数量',
  `procurementCode` varchar(255) DEFAULT NULL COMMENT '采购订单号',
  `contractProductId` varchar(64) DEFAULT NULL COMMENT '采购合同商品ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购订单商品表';

/*Data for the table `scm_cg_proorderproduct` */

insert  into `scm_cg_proorderproduct`(`id`,`proOrderId`,`productId`,`productName`,`productCode`,`productCname`,`productGauge`,`MedicineTypeId`,`MedicineType`,`unit`,`unitPrice`,`sumPrice`,`currency`,`Achange`,`productSum`,`procurementCode`,`contractProductId`) values ('9621028','9621027','6651016','奥泰灵0.75g(60粒/盒)','BB-93D','盐酸氨基葡萄糖胶囊','0.75g*60粒/盒','6001042','普通药品','盒',888.00,78144.00,'CNY','150',88,NULL,'9621025'),('9621029','9621027','6651015','奥泰灵0.75g(10粒/盒)','BB-93A','盐酸氨基葡萄糖胶囊','0.75g*10粒/盒','6001042','普通药品','盒',22.00,726.00,'CNY','600',33,NULL,'9621024'),('9621030','9621027','6651018','澳米沙(30ml/瓶)','BO-15','克林霉素磷酸酯外用溶液','1％*30ml/瓶','6001042','普通药品','瓶',88.00,1936.00,'CNY','160',22,NULL,'9621026');

/*Table structure for table `scm_cg_proreplacementorder` */

DROP TABLE IF EXISTS `scm_cg_proreplacementorder`;

CREATE TABLE `scm_cg_proreplacementorder` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `contractId` varchar(64) DEFAULT NULL COMMENT '合同ID',
  `orderId` varchar(64) DEFAULT NULL COMMENT '采购ID',
  `ReplacementOrderCode` varchar(255) DEFAULT NULL COMMENT '采购补单号',
  `speciesSum` int(10) DEFAULT NULL COMMENT '品种数',
  `productSum` int(10) DEFAULT NULL COMMENT '数量',
  `sumPrice` double(10,2) DEFAULT NULL COMMENT '金额',
  `currency` varchar(255) DEFAULT NULL COMMENT '币种',
  `orderDate` datetime DEFAULT NULL COMMENT '补单日期',
  `isToWMS` char(1) DEFAULT '0' COMMENT '是否推送至WMS',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` char(1) DEFAULT NULL COMMENT '审批状态',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `operatorId` varchar(64) DEFAULT NULL COMMENT '操作人ID',
  `operatorName` varchar(255) DEFAULT NULL COMMENT '操作人',
  `contractCode` varchar(255) DEFAULT NULL COMMENT '合同编号',
  `procurementId` varchar(64) DEFAULT NULL COMMENT '采购订单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购订单补单表';

/*Data for the table `scm_cg_proreplacementorder` */

/*Table structure for table `scm_cg_prorepproduct` */

DROP TABLE IF EXISTS `scm_cg_prorepproduct`;

CREATE TABLE `scm_cg_prorepproduct` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `ReplacementOrderId` varchar(64) DEFAULT NULL COMMENT '补单ID',
  `productId` varchar(64) DEFAULT NULL COMMENT '商品ID',
  `productName` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `productCode` varchar(255) DEFAULT NULL COMMENT '产品编码',
  `productCname` varchar(255) DEFAULT NULL COMMENT '商品通用名',
  `productGauge` varchar(255) DEFAULT NULL COMMENT '品规',
  `medicineTypeId` varchar(64) DEFAULT NULL COMMENT '药品类型ID',
  `medicineType` varchar(255) DEFAULT NULL COMMENT '药品类型',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `unitPrice` double(10,2) DEFAULT NULL COMMENT '单价',
  `sumPrice` double(10,2) DEFAULT NULL COMMENT '总金额',
  `currency` varchar(255) DEFAULT NULL COMMENT '币种',
  `achange` varchar(255) DEFAULT NULL COMMENT '件装量',
  `productSum` int(10) DEFAULT NULL COMMENT '采购数量',
  `contractProductId` varchar(64) DEFAULT NULL COMMENT '采购合同商品ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购补单商品表';

/*Data for the table `scm_cg_prorepproduct` */

/*Table structure for table `scm_cg_returnproduct` */

DROP TABLE IF EXISTS `scm_cg_returnproduct`;

CREATE TABLE `scm_cg_returnproduct` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `batchNum` varchar(255) DEFAULT NULL COMMENT '批号',
  `productId` varchar(64) DEFAULT NULL COMMENT '商品ID',
  `productName` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `medicineTypeId` varchar(64) DEFAULT NULL COMMENT '药品类型ID',
  `medicineType` varchar(255) DEFAULT NULL COMMENT '药品类型',
  `achange` varchar(255) DEFAULT NULL COMMENT '件装量',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `manufacturer` varchar(255) DEFAULT NULL COMMENT '生产厂家',
  `producedDate` datetime DEFAULT NULL COMMENT '生产日期',
  `validity` datetime DEFAULT NULL COMMENT '有效期',
  `getMoneyWarehouseId` varchar(64) DEFAULT NULL COMMENT '仓库ID',
  `getMoneyWarehouse` varchar(255) DEFAULT NULL COMMENT '仓库',
  `inboundDate` datetime DEFAULT NULL COMMENT '入库日期',
  `returnNumber` int(10) DEFAULT NULL COMMENT '退货数量',
  `returnDate` datetime DEFAULT NULL COMMENT '退货日期',
  `unitPrice` double(10,2) DEFAULT NULL COMMENT '单价',
  `sumPrice` double(10,2) DEFAULT NULL COMMENT '退货总金额',
  `returnRequestCauseId` varchar(64) DEFAULT NULL COMMENT '退货原因ID',
  `returnRequestCause` varchar(255) DEFAULT NULL COMMENT '退货原因',
  `memo` varchar(600) DEFAULT NULL COMMENT '备注',
  `proReturnId` varchar(64) DEFAULT NULL COMMENT '退货表ID',
  `returnId` varchar(64) DEFAULT NULL COMMENT '退货单Id',
  `returnCode` varchar(255) DEFAULT NULL COMMENT '退货单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购退货商品表';

/*Data for the table `scm_cg_returnproduct` */

/*Table structure for table `scm_fh_commoncarrier` */

DROP TABLE IF EXISTS `scm_fh_commoncarrier`;

CREATE TABLE `scm_fh_commoncarrier` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commonCarrierName` varchar(255) DEFAULT NULL COMMENT '承运商名称',
  `commonCarrierCode` varchar(255) DEFAULT NULL COMMENT '承运商编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='承运商表';

/*Data for the table `scm_fh_commoncarrier` */

insert  into `scm_fh_commoncarrier`(`id`,`commonCarrierName`,`commonCarrierCode`,`memo`,`createPersion`,`createDate`,`ownerId`) values ('8341003','e','ni yo','d',NULL,NULL,NULL),('8341004','e','ni yo','yy',NULL,NULL,NULL);

/*Table structure for table `scm_fh_focusshipmentsset` */

DROP TABLE IF EXISTS `scm_fh_focusshipmentsset`;

CREATE TABLE `scm_fh_focusshipmentsset` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `setCode` varchar(255) DEFAULT NULL COMMENT '设置编号',
  `businessDivision` varchar(255) DEFAULT NULL COMMENT '商务大区',
  `businessDivisionId` varchar(64) DEFAULT NULL COMMENT '商务大区id',
  `province` varchar(255) DEFAULT NULL COMMENT '省区',
  `provinceId` varchar(64) DEFAULT NULL COMMENT '省区id',
  `commerceName` varchar(255) DEFAULT NULL COMMENT '商业名称',
  `commerceId` varchar(64) DEFAULT NULL COMMENT '商业id',
  `ProductLineId` varchar(64) DEFAULT NULL COMMENT '产品系列ID',
  `ProductLineName` varchar(255) DEFAULT NULL COMMENT '产品系列',
  `isStartUsing` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `startUsingDate` datetime DEFAULT NULL COMMENT '启用日期',
  `createPersion` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='集中发货设置表';

/*Data for the table `scm_fh_focusshipmentsset` */

/*Table structure for table `scm_fh_logisticsagreement` */

DROP TABLE IF EXISTS `scm_fh_logisticsagreement`;

CREATE TABLE `scm_fh_logisticsagreement` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `shippedTo` varchar(255) DEFAULT NULL COMMENT '发运地',
  `destination` varchar(255) DEFAULT NULL COMMENT '目的地',
  `maxArrivalTime` int(10) DEFAULT NULL COMMENT '最长到达日期',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流协议表';

/*Data for the table `scm_fh_logisticsagreement` */

/*Table structure for table `scm_fh_shipmentscontrol` */

DROP TABLE IF EXISTS `scm_fh_shipmentscontrol`;

CREATE TABLE `scm_fh_shipmentscontrol` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `province` varchar(255) DEFAULT NULL COMMENT '省区',
  `provinceId` varchar(64) DEFAULT NULL COMMENT '省区id',
  `businessDivision` varchar(255) DEFAULT NULL COMMENT '商务大区',
  `businessDivisionId` varchar(64) DEFAULT NULL COMMENT '商务大区id',
  `commerce` varchar(255) DEFAULT NULL COMMENT '经销商',
  `commerceId` varchar(64) DEFAULT NULL COMMENT '经销商ID',
  `ProductLineId` varchar(64) DEFAULT NULL COMMENT '产品系列ID',
  `ProductLineName` varchar(255) DEFAULT NULL COMMENT '产品系列',
  `productNum` varchar(255) DEFAULT NULL COMMENT '药品编号',
  `productState` varchar(255) DEFAULT NULL COMMENT '药品品规',
  `productName` varchar(255) DEFAULT NULL COMMENT '禁止发货药品名称',
  `createDate` datetime DEFAULT NULL COMMENT '添加时间',
  `createPersion` varchar(255) DEFAULT NULL COMMENT '添加人',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发货控制';

/*Data for the table `scm_fh_shipmentscontrol` */

/*Table structure for table `scm_fh_shipmentscontrollog` */

DROP TABLE IF EXISTS `scm_fh_shipmentscontrollog`;

CREATE TABLE `scm_fh_shipmentscontrollog` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `operatorName` varchar(255) DEFAULT NULL COMMENT '操作人',
  `operatorDate` datetime DEFAULT NULL COMMENT '操作日期',
  `operatormemo` varchar(255) DEFAULT NULL COMMENT '操作内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发货控制日志记录';

/*Data for the table `scm_fh_shipmentscontrollog` */

/*Table structure for table `scm_fh_shipmentsdatail` */

DROP TABLE IF EXISTS `scm_fh_shipmentsdatail`;

CREATE TABLE `scm_fh_shipmentsdatail` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `shipmentsTaskId` varchar(64) DEFAULT NULL COMMENT '发货任务ID',
  `type` varchar(1) DEFAULT NULL COMMENT '类型(0:发货任务,1:剩余任务)',
  `taskType` varchar(1) DEFAULT NULL COMMENT '任务类型(0:集中发货,1:分配,2日常合同)',
  `dateCode` varchar(255) DEFAULT NULL COMMENT '数据编号',
  `shipmentsAffirmDate` datetime DEFAULT NULL COMMENT '发货确认时间',
  `paymentType` varchar(1) DEFAULT NULL COMMENT '付款类型(0:预付款,1赊销)',
  `focusShipmentsMonth` varchar(2) DEFAULT NULL COMMENT '集中发货分配月',
  `isSms` varchar(1) DEFAULT NULL COMMENT '是否SMS',
  `commerceName` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `commerceId` varchar(64) DEFAULT NULL COMMENT '商业id',
  `productName` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `productNum` varchar(255) DEFAULT NULL COMMENT '药品编号',
  `MedicineType` varchar(255) DEFAULT NULL COMMENT '药品类型',
  `productState` varchar(255) DEFAULT NULL COMMENT '规格型号',
  `aChange` varchar(10) DEFAULT NULL COMMENT '件装量',
  `commercetype` varchar(10) DEFAULT NULL COMMENT '客户类型',
  `marketType` varchar(1) DEFAULT NULL COMMENT '销售类型',
  `batchNumber` varchar(255) DEFAULT NULL COMMENT '批号',
  `price` double(10,2) DEFAULT NULL COMMENT '单价',
  `memo` varchar(300) DEFAULT NULL COMMENT '发货备注',
  `planShipmentsSum` int(10) DEFAULT NULL COMMENT '计划量',
  `deliveringAmount` int(10) DEFAULT NULL COMMENT '实际量',
  `warehouse` varchar(255) DEFAULT NULL COMMENT '仓库',
  `stock` int(10) DEFAULT NULL COMMENT '当前库存量',
  `contactPersion` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `shippingAddress` varchar(300) DEFAULT NULL COMMENT '收货地址',
  `dateOfManufacture` datetime DEFAULT NULL COMMENT '生产日期',
  `masterId` varchar(64) DEFAULT NULL COMMENT '主表ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发货明细表';

/*Data for the table `scm_fh_shipmentsdatail` */

/*Table structure for table `scm_fh_shipmentsdatailmaster` */

DROP TABLE IF EXISTS `scm_fh_shipmentsdatailmaster`;

CREATE TABLE `scm_fh_shipmentsdatailmaster` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '审批状态',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `businessDivision` varchar(255) DEFAULT NULL COMMENT '商务大区',
  `businessDivisionId` varchar(64) DEFAULT NULL COMMENT '商务大区id',
  `province` varchar(255) DEFAULT NULL COMMENT '省区',
  `provinceId` varchar(64) DEFAULT NULL COMMENT '省区id',
  `commerceName` varchar(255) DEFAULT NULL COMMENT '商业名称',
  `commerceId` varchar(64) DEFAULT NULL COMMENT '商业id',
  `shipmentsCode` varchar(255) DEFAULT NULL COMMENT '发货编号',
  `successDate` datetime DEFAULT NULL COMMENT '通过时间',
  `createPersion` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发货明细总表';

/*Data for the table `scm_fh_shipmentsdatailmaster` */

/*Table structure for table `scm_fh_shipmentsmessage` */

DROP TABLE IF EXISTS `scm_fh_shipmentsmessage`;

CREATE TABLE `scm_fh_shipmentsmessage` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `shipmentsDatailId` varchar(64) DEFAULT NULL COMMENT '发货明细表ID',
  `logisticsName` varchar(255) DEFAULT NULL COMMENT '物流承运商',
  `logisticsCode` varchar(255) DEFAULT NULL COMMENT '物流单号',
  `actualDeliveryDate` datetime DEFAULT NULL COMMENT '仓库实际发货日期',
  `expectedArrivalDate` datetime DEFAULT NULL COMMENT '预计到货日期',
  `actualDateOfArrival` datetime DEFAULT NULL COMMENT '实际到货日期',
  `confirmArrivalDate` datetime DEFAULT NULL COMMENT '配送确认到货日期',
  `confirmMemo` varchar(300) DEFAULT NULL COMMENT '配送确认备注',
  `officialReceiptsSum` int(10) DEFAULT NULL COMMENT '实收件数',
  `exceptionCase` varchar(255) DEFAULT NULL COMMENT '异常情况',
  `explain` varchar(300) DEFAULT NULL COMMENT '说明',
  `agreementDays` int(10) DEFAULT NULL COMMENT '协议到货天数',
  `dalayDays` int(10) DEFAULT NULL COMMENT '延误天数',
  `transportationMethod` varchar(255) DEFAULT NULL COMMENT '运输方式',
  `entrustPersion` varchar(255) DEFAULT NULL COMMENT '委托经办人',
  `driver` varchar(255) DEFAULT NULL COMMENT '驾驶员',
  `licencePlate` varchar(64) DEFAULT NULL COMMENT '车牌号',
  `isToWms` varchar(1) DEFAULT NULL COMMENT '是否传入WMS',
  `toWmsDate` datetime DEFAULT NULL COMMENT '传入WMS时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流发运信息表';

/*Data for the table `scm_fh_shipmentsmessage` */

/*Table structure for table `scm_fh_shipmentstask` */

DROP TABLE IF EXISTS `scm_fh_shipmentstask`;

CREATE TABLE `scm_fh_shipmentstask` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `type` varchar(1) DEFAULT NULL COMMENT '类型(0:发货任务,1:剩余任务)',
  `taskType` varchar(1) DEFAULT NULL COMMENT '任务类型(0:集中发货,1:分配,2日常合同)',
  `dateCode` varchar(64) DEFAULT NULL COMMENT '数据编号',
  `taskCreateDate` datetime DEFAULT NULL COMMENT '任务生成时间',
  `paymentType` varchar(1) DEFAULT NULL COMMENT '付款类型(0:预付款,1赊销)',
  `focusShipmentsMonth` varchar(2) DEFAULT NULL COMMENT '集中发货分配月',
  `isSms` varchar(1) DEFAULT NULL COMMENT '是否SMS',
  `commerceName` varchar(255) DEFAULT NULL COMMENT '商业名称',
  `commerceId` varchar(64) DEFAULT NULL COMMENT '商业id',
  `productName` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `productNum` varchar(255) DEFAULT NULL COMMENT '药品编号',
  `MedicineType` varchar(255) DEFAULT NULL COMMENT '药品类型',
  `productState` varchar(255) DEFAULT NULL COMMENT '规格型号',
  `aChange` varchar(255) DEFAULT NULL COMMENT '件装量',
  `commercetype` varchar(255) DEFAULT NULL COMMENT '客户类型',
  `marketType` varchar(255) DEFAULT NULL COMMENT '销售类型',
  `price` double(10,2) DEFAULT NULL COMMENT '单价',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `planShipmentsSum` int(10) DEFAULT NULL COMMENT '计划发货量',
  `contactPersion` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发货任务表';

/*Data for the table `scm_fh_shipmentstask` */

/*Table structure for table `scm_log_modifylog` */

DROP TABLE IF EXISTS `scm_log_modifylog`;

CREATE TABLE `scm_log_modifylog` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `category` varchar(64) DEFAULT NULL COMMENT '修改数据类别',
  `modifier` varchar(255) DEFAULT NULL COMMENT '修改人',
  `categoryId` varchar(64) DEFAULT NULL COMMENT '修改数据ID',
  `filedName` varchar(600) DEFAULT NULL COMMENT '修改字段内容',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改日期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `scm_log_modifylog` */

insert  into `scm_log_modifylog`(`id`,`category`,`modifier`,`categoryId`,`filedName`,`modifiedTime`) values ('9011001','TEST','原浩','123','审批ID：123->321','2019-02-23 18:42:53');

/*Table structure for table `scm_xs_agreementlist` */

DROP TABLE IF EXISTS `scm_xs_agreementlist`;

CREATE TABLE `scm_xs_agreementlist` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceFirstId` varchar(64) DEFAULT NULL COMMENT '商业首营ID',
  `year` varchar(4) DEFAULT NULL COMMENT '年度',
  `commerceCode` varchar(255) DEFAULT NULL COMMENT '商业编码',
  `commerceClassify` varchar(255) DEFAULT NULL COMMENT '商业分类',
  `commerceLevel` varchar(255) DEFAULT NULL COMMENT '商业级别',
  `memo` varchar(600) DEFAULT NULL COMMENT '申请备注',
  `isSign` varchar(1) DEFAULT NULL COMMENT '是否签约(0:未签约,1已签约)',
  `masterId` varchar(64) DEFAULT NULL COMMENT '协议名单主表ID',
  `commerceName` varchar(255) DEFAULT NULL COMMENT '商业名称',
  `type` varchar(1) DEFAULT NULL COMMENT '选择类型(0:经销商,1两票制经销商,终止认可,转分销商)',
  `lastYear` varchar(1) DEFAULT NULL COMMENT '上年度是否签约(0:未签约,1已签约)',
  `shipments` varchar(64) DEFAULT NULL COMMENT '发货量',
  `allocating` varchar(64) DEFAULT NULL COMMENT '调拨量',
  `sales` varchar(64) DEFAULT NULL COMMENT '纯销量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商协议名单申请表';

/*Data for the table `scm_xs_agreementlist` */

insert  into `scm_xs_agreementlist`(`id`,`commerceFirstId`,`year`,`commerceCode`,`commerceClassify`,`commerceLevel`,`memo`,`isSign`,`masterId`,`commerceName`,`type`,`lastYear`,`shipments`,`allocating`,`sales`) values ('10061006','9621001','2019','1843',NULL,'经销商','测试5','1','10061001','国药控股沈阳有限公司','0','0','0','0','0'),('10061007','9621038','2019','10322',NULL,'经销商','测试5','1','10061001','华润辽宁医药有限公司','1','0','0','0','0'),('10071006','9621001','2019','1843',NULL,'经销商','测试6','1','10071001','国药控股沈阳有限公司','0','0','0','0','0'),('10071007','9621038','2019','10322',NULL,'经销商','测试6','1','10071001','华润辽宁医药有限公司','0','0','0','0','0'),('10071011','9621038','2019','10322',NULL,'经销商','测试7','1','10071008','华润辽宁医药有限公司','0','0','0','0','0'),('10071012','9621001','2019','1843',NULL,'经销商','测试7','1','10071008','国药控股沈阳有限公司','1','0','0','0','0'),('10151011','9621001','2019','1843',NULL,'经销商','测试2019-03-02','1','10151010','国药控股沈阳有限公司','0','0','0','0','0'),('10151012','9621001','2019','1843',NULL,'经销商','测试2019-03-02','1','10151010','国药控股沈阳有限公司','0','0','0','0','0'),('10151013','9621038','2019','10322',NULL,'经销商','测试2019-03-02','1','10151010','华润辽宁医药有限公司','0','0','0','0','0'),('10151014','9621038','2019','10322',NULL,'经销商','测试2019-03-02','1','10151010','华润辽宁医药有限公司','0','0','0','0','0'),('9871012','9621001','2019','1843',NULL,'经销商','123','1','9871011','国药控股沈阳有限公司','0','0','0','0','0'),('9871013','9621038','2019','10322',NULL,'经销商','123','1','9871011','华润辽宁医药有限公司','0','0','0','0','0'),('9991002','9621001','2019','1843',NULL,'经销商','','0','9991001','国药控股沈阳有限公司','0','0','0','0','0'),('9991003','9621038','2019','10322',NULL,'经销商','','0','9991001','华润辽宁医药有限公司','0','0','0','0','0'),('9991005','9621001','2019','1843',NULL,'经销商','','1','9991004','国药控股沈阳有限公司','0','0','0','0','0'),('9991006','9621038','2019','10322',NULL,'经销商','','1','9991004','华润辽宁医药有限公司','0','0','0','0','0');

/*Table structure for table `scm_xs_agreementlistmaster` */

DROP TABLE IF EXISTS `scm_xs_agreementlistmaster`;

CREATE TABLE `scm_xs_agreementlistmaster` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `businessDivision` varchar(255) DEFAULT NULL COMMENT '商务分区',
  `businessDivisionId` varchar(64) DEFAULT NULL COMMENT '商务分区ID',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '审批状态',
  `MasterCode` varchar(255) DEFAULT NULL COMMENT '申请单号',
  `isEffect` varchar(1) DEFAULT NULL COMMENT '是否生效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业协议合作名单主表';

/*Data for the table `scm_xs_agreementlistmaster` */

insert  into `scm_xs_agreementlistmaster`(`id`,`businessDivision`,`businessDivisionId`,`ownerId`,`approvalId`,`approvalState`,`MasterCode`,`isEffect`) values ('10061001','华北区','7111078','7111011','5007139','1','JXMD1903011752050407','0'),('10071001','华北区','7111078','7111011','5007158','1','JXMD1903011883006732','0'),('10071008','华北区','7111078','7111011',NULL,'0','JXMD1903010316794907','0'),('10151010','华北区','7111078','7111011','5007261','1','JXMD1903021261459316','0'),('9871011','华北区','7111078','7111011','5006333','2','JXMD1903011896793034','1'),('9991001','华北区','7111078','7111011','5006968','1','JXMD1903010459426452','0'),('9991004','华北区','7111078','7111011','5006987','1','JXMD1903010749312140','0');

/*Table structure for table `scm_xs_agreementproductdetail` */

DROP TABLE IF EXISTS `scm_xs_agreementproductdetail`;

CREATE TABLE `scm_xs_agreementproductdetail` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `agreementSummaryId` varchar(64) DEFAULT NULL COMMENT '协议汇总ID',
  `commerceGauge` varchar(255) DEFAULT NULL COMMENT '商品品规',
  `isSign` varchar(1) DEFAULT NULL COMMENT '是否签约',
  `effectiveDate` datetime DEFAULT NULL COMMENT '生效日期',
  `gaugeType` varchar(1) DEFAULT NULL COMMENT '品规类型(0:非OTC,,1甲类,2乙类)',
  `isBidding` varchar(1) DEFAULT NULL COMMENT '是否中标',
  `isImplement` varchar(1) DEFAULT NULL COMMENT '是否实施',
  `biddingPrice` double(10,2) DEFAULT NULL COMMENT '实际中标价',
  `supplyPrice` double(10,2) DEFAULT NULL COMMENT '商业供货单价',
  `allotGross` double(10,2) DEFAULT NULL COMMENT '调拨毛利',
  `acceptGross` double(10,2) DEFAULT NULL COMMENT '承兑毛利',
  `purePinGross` double(10,2) DEFAULT NULL COMMENT '纯销毛利',
  `wireTransferPrice` double(10,2) DEFAULT NULL COMMENT '电汇调拨供货价',
  `acceptPrice` double(10,2) DEFAULT NULL COMMENT '承兑调拨供货价',
  `purePinPrice` double(10,2) DEFAULT NULL COMMENT '纯销供货价',
  `memo` varchar(600) DEFAULT NULL COMMENT '备注',
  `commerceCode` varchar(255) DEFAULT NULL COMMENT '药品编码',
  `delivery` double(10,2) DEFAULT NULL COMMENT '发货额',
  `lastIsSign` varchar(1) DEFAULT NULL COMMENT '上次是否签约',
  `lastSupplyPrice` double(10,2) DEFAULT NULL COMMENT '上次商业供货单价',
  `lastAllotGross` double(10,2) DEFAULT NULL COMMENT '上次调拨毛利',
  `lastAcceptGross` double(10,2) DEFAULT NULL COMMENT '上次承兑毛利',
  `lastPurePinGross` double(10,2) DEFAULT NULL COMMENT '上次纯销毛利',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='协议品规明细';

/*Data for the table `scm_xs_agreementproductdetail` */

insert  into `scm_xs_agreementproductdetail`(`id`,`agreementSummaryId`,`commerceGauge`,`isSign`,`effectiveDate`,`gaugeType`,`isBidding`,`isImplement`,`biddingPrice`,`supplyPrice`,`allotGross`,`acceptGross`,`purePinGross`,`wireTransferPrice`,`acceptPrice`,`purePinPrice`,`memo`,`commerceCode`,`delivery`,`lastIsSign`,`lastSupplyPrice`,`lastAllotGross`,`lastAcceptGross`,`lastPurePinGross`) values ('10161013','10161005','奥泰灵0.75g(10粒/盒)','1','2019-01-01 00:00:00',NULL,'1','1',30.00,0.00,1.00,1.00,1.00,2.00,4.00,1.00,'备注0001','BB-93A',0.00,'0',0.00,0.00,0.00,0.00),('10161014','10161005','奥泰灵0.75g(60粒/盒)','1','2019-01-01 00:00:00',NULL,'1','1',30.00,0.00,1.00,1.00,1.00,2.00,4.00,1.00,'备注','BB-93D',0.00,'0',0.00,0.00,0.00,0.00),('10261004','10191001','奥泰灵0.75g(10粒/盒)',NULL,'2019-01-01 00:00:00',NULL,'1','1',30.00,0.00,23.00,3.00,3.00,48.00,192.00,2.00,NULL,'BB-93A',0.00,'0',0.00,0.00,0.00,0.00),('10261005','10191001','奥泰灵0.75g(60粒/盒)',NULL,'2019-01-01 00:00:00',NULL,'1','1',30.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL,'BB-93D',0.00,'0',0.00,0.00,0.00,0.00);

/*Table structure for table `scm_xs_agreementspecialproduct` */

DROP TABLE IF EXISTS `scm_xs_agreementspecialproduct`;

CREATE TABLE `scm_xs_agreementspecialproduct` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `AgreementId` varchar(64) DEFAULT NULL COMMENT '协议ID',
  `ProductFirstId` varchar(64) DEFAULT NULL COMMENT '商品首营ID',
  `productName` varchar(255) DEFAULT NULL COMMENT '商品名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商协议特殊商品表';

/*Data for the table `scm_xs_agreementspecialproduct` */

insert  into `scm_xs_agreementspecialproduct`(`id`,`AgreementId`,`ProductFirstId`,`productName`) values ('10161008','10161007','6651015','奥泰灵0.75g(10粒/盒)'),('10161010','10161006','6651015','奥泰灵0.75g(10粒/盒)');

/*Table structure for table `scm_xs_agreementsubsidiary` */

DROP TABLE IF EXISTS `scm_xs_agreementsubsidiary`;

CREATE TABLE `scm_xs_agreementsubsidiary` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `subsidiaryId` varchar(64) DEFAULT NULL COMMENT '子公司ID',
  `AgreementId` varchar(64) DEFAULT NULL COMMENT '协议ID',
  `subsidiaryName` varchar(255) DEFAULT NULL COMMENT '子公司名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业协议分子公司表';

/*Data for the table `scm_xs_agreementsubsidiary` */

insert  into `scm_xs_agreementsubsidiary`(`id`,`subsidiaryId`,`AgreementId`,`subsidiaryName`) values ('10161009','6481005','10161007','上药康德乐（辽宁）医药有限公司'),('10161011','6481005','10161006','上药康德乐（辽宁）医药有限公司'),('10161012','6481004','10161006','辽宁九州通医药有限公司'),('10261006','6481005','10191003','上药康德乐（辽宁）医药有限公司'),('10261007','6481004','10191003','辽宁九州通医药有限公司');

/*Table structure for table `scm_xs_agreementsummary` */

DROP TABLE IF EXISTS `scm_xs_agreementsummary`;

CREATE TABLE `scm_xs_agreementsummary` (
  `id` varchar(64) NOT NULL,
  `businessDivision` varchar(255) DEFAULT NULL COMMENT '商务分区',
  `Province` varchar(255) DEFAULT NULL COMMENT '省区',
  `businessDivisionId` varchar(64) DEFAULT NULL COMMENT '商务分区ID',
  `provinceId` varchar(64) DEFAULT NULL COMMENT '省区ID',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '流程ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '流程状态',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `agreementSummaryCode` varchar(64) DEFAULT NULL COMMENT '协议单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='省区协议汇总表';

/*Data for the table `scm_xs_agreementsummary` */

insert  into `scm_xs_agreementsummary`(`id`,`businessDivision`,`Province`,`businessDivisionId`,`provinceId`,`approvalId`,`approvalState`,`ownerId`,`agreementSummaryCode`) values ('10161004','华中区','湖南+江西','7111080','7111083',NULL,'0','7111011','JXTK1903021907761708'),('10161005','华北区','','7111078','','5007318','1','7111011','JXTK1903020094190567'),('10191001','华北区','','7111078','',NULL,'0','7111011','JXTK1903021732374271'),('10191004','华北区','','7111078','',NULL,'0','7111011','JXTK1903020370993164'),('10261032','华北区','','7111078','',NULL,'0','7111011','JXTK1903041827245785'),('10261071','华北区','','7111078','',NULL,'0','7111011','JXTK1903050903179853'),('10421001','华北区','','7111078','',NULL,'0','7111011','JXTK1903052041016139'),('10421002','华东区','','7111079','',NULL,'0','7111011','JXTK1903050727516222'),('10421003','华中区','','7111080','',NULL,'0','7111011','JXTK1903051120915973'),('10421004','西南区','','7111081','',NULL,'0','7111011','JXTK1903050541268613'),('10421005','开拓团队','','7111082','',NULL,'0','7111011','JXTK1903051377371456');

/*Table structure for table `scm_xs_bigcontract` */

DROP TABLE IF EXISTS `scm_xs_bigcontract`;

CREATE TABLE `scm_xs_bigcontract` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `ownerName` varchar(255) DEFAULT NULL COMMENT '供货商',
  `dealer` varchar(255) DEFAULT NULL COMMENT '经销商',
  `commerceFirstId` varchar(64) DEFAULT NULL COMMENT '经销商首营ID',
  `contractCode` varchar(255) DEFAULT NULL COMMENT '合同编号',
  `sendProductDate` datetime DEFAULT NULL COMMENT '计划发货时间',
  `consignee` varchar(255) DEFAULT NULL COMMENT '收货人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `marketingWay` varchar(1) DEFAULT NULL COMMENT '购销方式(0预付款,1赊销)',
  `shipAddress` varchar(255) DEFAULT NULL COMMENT '到货地址',
  `payType` varchar(1) DEFAULT NULL COMMENT '付款方式',
  `signTheAddress` varchar(255) DEFAULT NULL COMMENT '签订地址',
  `specialOpinion` varchar(255) DEFAULT NULL COMMENT '特求意见',
  `YUnit` varchar(255) DEFAULT NULL COMMENT '乙方单位',
  `YAddress` varchar(255) DEFAULT NULL COMMENT '乙方地址',
  `YOpenBank` varchar(255) DEFAULT NULL COMMENT '乙方开户行',
  `YDutyCode` varchar(255) DEFAULT NULL COMMENT '乙方税号',
  `YBankCode` varchar(255) DEFAULT NULL COMMENT '乙方账号',
  `YPhone` varchar(255) DEFAULT NULL COMMENT '乙方电话',
  `YPostcode` varchar(6) DEFAULT NULL COMMENT '乙方邮编',
  `JUnit` varchar(255) DEFAULT NULL COMMENT '甲方单位',
  `JAddress` varchar(255) DEFAULT NULL COMMENT '甲方地址',
  `JOpenBank` varchar(255) DEFAULT NULL COMMENT '甲方开户行',
  `JBankCode` varchar(255) DEFAULT NULL COMMENT '甲方账户',
  `JDutyCode` varchar(255) DEFAULT NULL COMMENT '甲方税号',
  `JPhone` varchar(255) DEFAULT NULL COMMENT '甲方电话',
  `JPostcode` varchar(6) DEFAULT NULL COMMENT '甲方邮编',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `DealerClauseId` varchar(64) DEFAULT NULL COMMENT '协议ID',
  `reduceContractTime` int(10) DEFAULT NULL COMMENT '减免合同次数',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '审批状态',
  `dateOfSigning` datetime DEFAULT NULL COMMENT '签订时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大合同';

/*Data for the table `scm_xs_bigcontract` */

insert  into `scm_xs_bigcontract`(`id`,`ownerName`,`dealer`,`commerceFirstId`,`contractCode`,`sendProductDate`,`consignee`,`phone`,`marketingWay`,`shipAddress`,`payType`,`signTheAddress`,`specialOpinion`,`YUnit`,`YAddress`,`YOpenBank`,`YDutyCode`,`YBankCode`,`YPhone`,`YPostcode`,`JUnit`,`JAddress`,`JOpenBank`,`JBankCode`,`JDutyCode`,`JPhone`,`JPostcode`,`ownerId`,`DealerClauseId`,`reduceContractTime`,`approvalId`,`approvalState`,`dateOfSigning`) values ('10291006','','国药控股沈阳有限公司','10261075','',NULL,'','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('10291007','','','','',NULL,'','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('10291009','','','','',NULL,'','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('10421008','','','','',NULL,'','','','','','','','','','','','','','','','','','','','','',NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `scm_xs_bigcontractallot` */

DROP TABLE IF EXISTS `scm_xs_bigcontractallot`;

CREATE TABLE `scm_xs_bigcontractallot` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `specialOpinion` varchar(255) DEFAULT NULL COMMENT '特求意见',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `BigContractId` varchar(64) DEFAULT NULL COMMENT '大合同ID',
  `reduceContractTime` varchar(255) DEFAULT NULL COMMENT '减免合同次数理由',
  `allocationCode` varchar(255) DEFAULT NULL COMMENT '分配编号',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '审批状态',
  `dateOfSigning` timestamp NULL DEFAULT NULL COMMENT '签订日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大合同分配表';

/*Data for the table `scm_xs_bigcontractallot` */

/*Table structure for table `scm_xs_bigcontractallotproduct` */

DROP TABLE IF EXISTS `scm_xs_bigcontractallotproduct`;

CREATE TABLE `scm_xs_bigcontractallotproduct` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `BigContractAllotId` varchar(64) DEFAULT NULL COMMENT '大合同分配ID',
  `theNumber` int(10) DEFAULT NULL COMMENT '数量',
  `packageNum` int(10) DEFAULT NULL COMMENT '件数',
  `unitPrice` double(10,2) DEFAULT NULL COMMENT '含税单价',
  `sumPrice` double(10,2) DEFAULT NULL COMMENT '总价',
  `BigContractProductId` varchar(64) DEFAULT NULL COMMENT '大合同商品ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大合同分配商品表';

/*Data for the table `scm_xs_bigcontractallotproduct` */

/*Table structure for table `scm_xs_bigcontractproduct` */

DROP TABLE IF EXISTS `scm_xs_bigcontractproduct`;

CREATE TABLE `scm_xs_bigcontractproduct` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `bigContractId` varchar(64) DEFAULT NULL COMMENT '大合同ID',
  `productGauge` varchar(255) DEFAULT NULL COMMENT '商品品规',
  `MedicineType` varchar(255) DEFAULT NULL COMMENT '药品类型',
  `commonName` varchar(255) DEFAULT NULL COMMENT '商品常用名称',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `Achange` int(10) DEFAULT NULL COMMENT '标准包装数',
  `suppyType` varchar(1) DEFAULT NULL COMMENT '药品供应状态',
  `suggestApplyNum` int(10) DEFAULT NULL COMMENT '建议本次申请量',
  `lastShipmentDate` datetime DEFAULT NULL COMMENT '上次发货时间',
  `SMSCurrentMonthSales` int(10) DEFAULT NULL COMMENT '省区本月SMS销量',
  `productCode` varchar(64) DEFAULT NULL COMMENT '药品编号',
  `isSMS` varchar(1) DEFAULT NULL COMMENT '是否SMS',
  `theNumber` int(10) DEFAULT NULL COMMENT '数量',
  `packageNum` int(10) DEFAULT NULL COMMENT '件数',
  `unitPrice` double(10,2) DEFAULT NULL COMMENT '含税单价',
  `sumPrice` double(10,2) DEFAULT NULL COMMENT '总价',
  `surplusSUm` int(10) DEFAULT NULL COMMENT '剩余数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='大合同商品表';

/*Data for the table `scm_xs_bigcontractproduct` */

insert  into `scm_xs_bigcontractproduct`(`id`,`bigContractId`,`productGauge`,`MedicineType`,`commonName`,`unit`,`Achange`,`suppyType`,`suggestApplyNum`,`lastShipmentDate`,`SMSCurrentMonthSales`,`productCode`,`isSMS`,`theNumber`,`packageNum`,`unitPrice`,`sumPrice`,`surplusSUm`) values ('10161013','10291006',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('10161014','10291006',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('10261004','10291006',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('10261005','10291006',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `scm_xs_creditapply` */

DROP TABLE IF EXISTS `scm_xs_creditapply`;

CREATE TABLE `scm_xs_creditapply` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `businessDivision` varchar(255) DEFAULT NULL COMMENT '商务分区',
  `Province` varchar(255) DEFAULT NULL COMMENT '省区',
  `businessDivisionId` varchar(64) DEFAULT NULL COMMENT '商务分区ID',
  `provinceId` varchar(64) DEFAULT NULL COMMENT '省区ID',
  `commerceCode` varchar(255) DEFAULT NULL COMMENT '商业编码',
  `commerceName` varchar(255) DEFAULT NULL COMMENT '商业名称',
  `marketWay` varchar(1) DEFAULT NULL COMMENT '购销方式(0:预付款,1赊销)',
  `beforeYearPrice` int(10) DEFAULT NULL COMMENT '前年月均销售额(万)',
  `lastYearPrice` int(10) DEFAULT NULL COMMENT '去年月均销售额(万)',
  `lastTimePrice` int(10) DEFAULT NULL COMMENT '上次批准资信额(万)',
  `sysPrice` int(10) DEFAULT NULL COMMENT '系统预测资信额(万)',
  `price` int(10) DEFAULT NULL COMMENT '资信额(万)',
  `memo` varchar(600) DEFAULT NULL COMMENT '备注',
  `CreditCollectId` varchar(64) DEFAULT NULL COMMENT '汇总ID',
  `year` varchar(64) DEFAULT NULL COMMENT '年度',
  `quarter` varchar(64) DEFAULT NULL COMMENT '季度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业资信申请表';

/*Data for the table `scm_xs_creditapply` */

insert  into `scm_xs_creditapply`(`id`,`businessDivision`,`Province`,`businessDivisionId`,`provinceId`,`commerceCode`,`commerceName`,`marketWay`,`beforeYearPrice`,`lastYearPrice`,`lastTimePrice`,`sysPrice`,`price`,`memo`,`CreditCollectId`,`year`,`quarter`) values ('10181002','华北区','','7111078','','1843','国药控股沈阳有限公司','',0,0,0,0,0,'','10181001','2011','1'),('9631004','华北区','','7111078','','1843','国药控股沈阳有限公司','0',0,0,0,0,0,'','9631003','2019','4'),('9631005','华北区','','7111078','','10322','华润辽宁医药有限公司','1',0,0,0,0,800,'','9631003','2019','4'),('9641002','华北区','','7111078','','10322','华润辽宁医药有限公司','1',0,0,0,0,66,'','9641001','2019','2'),('9641003','华北区','','7111078','','1843','国药控股沈阳有限公司','1',0,0,0,0,66,'','9641001','2019','2'),('9891009','华北区','','7111078','','1843','国药控股沈阳有限公司','1',0,0,0,0,800,'','9891008','2019','4'),('9891010','华北区','','7111078','','10322','华润辽宁医药有限公司','0',0,0,0,0,0,'','9891008','2019','4'),('9911018','华北区','','7111078','','1843','国药控股沈阳有限公司','1',0,0,0,0,100,'','9911017','2019','4'),('9911019','华北区','','7111078','','10322','华润辽宁医药有限公司','0',0,0,0,0,0,'','9911017','2019','4');

/*Table structure for table `scm_xs_creditcollect` */

DROP TABLE IF EXISTS `scm_xs_creditcollect`;

CREATE TABLE `scm_xs_creditcollect` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '流程ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '流程状态',
  `quarter` varchar(1) DEFAULT NULL COMMENT '季度',
  `requestCode` varchar(255) DEFAULT NULL COMMENT '申请单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业资信申请总表';

/*Data for the table `scm_xs_creditcollect` */

insert  into `scm_xs_creditcollect`(`id`,`ownerId`,`approvalId`,`approvalState`,`quarter`,`requestCode`) values ('10181001','7111011',NULL,'0',NULL,'1903020699068993'),('9631003','7111011','5004939','1',NULL,NULL),('9641001','7111011','5004977','2',NULL,NULL),('9891008','7111011','5006409','1',NULL,'1903011781125952'),('9911017','7111011','5006580','1',NULL,'1903010011547494');

/*Table structure for table `scm_xs_dealerclause` */

DROP TABLE IF EXISTS `scm_xs_dealerclause`;

CREATE TABLE `scm_xs_dealerclause` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `AgreementSummaryId` varchar(64) DEFAULT NULL COMMENT '协议汇总ID',
  `clauseCode` varchar(255) DEFAULT NULL COMMENT '协议号',
  `commerceName` varchar(255) DEFAULT NULL COMMENT '商业名称',
  `functionType` varchar(255) DEFAULT NULL COMMENT '职能类型',
  `AgreementType` varchar(255) DEFAULT NULL COMMENT '选择分类',
  `AgreementStartDate` datetime DEFAULT NULL COMMENT '协议生效日期',
  `AgreementChangeDate` datetime DEFAULT NULL COMMENT '协议最新变更日期',
  `AgreementEndDate` datetime DEFAULT NULL COMMENT '协议生效日期止',
  `paymentType` varchar(1) DEFAULT NULL COMMENT '付款类型(0:预付款,1赊销)',
  `returnMoneyDay` int(10) DEFAULT NULL COMMENT '回款天数',
  `promiseProvince` varchar(255) DEFAULT NULL COMMENT '承诺省份',
  `messageStandard` int(10) DEFAULT NULL COMMENT '信息直连标准',
  `flowStandard` int(10) DEFAULT NULL COMMENT '流向折扣标准',
  `transportationDiscount` int(10) DEFAULT NULL COMMENT '储运管理折扣率',
  `unloadPayer` varchar(1) DEFAULT NULL COMMENT '卸载费支付方(0:甲方,1乙方)',
  `subsidiarySum` int(10) DEFAULT NULL COMMENT '分子公司数量',
  `memo` varchar(600) DEFAULT NULL COMMENT '申请备注',
  `isOverfulfil` varchar(1) DEFAULT NULL COMMENT '是否可以超额发货',
  `StardDay` int(10) DEFAULT NULL COMMENT '起始天数',
  `endDay` int(10) DEFAULT NULL COMMENT '截止天数',
  `theApplication` int(10) DEFAULT NULL COMMENT '本次申请折扣',
  `isSpecial` varchar(1) DEFAULT NULL COMMENT '是否特殊(:0:没有,1:有)',
  `lastPaymentType` varchar(1) DEFAULT NULL COMMENT '上次付款类型',
  `lastReturnMoneyDay` int(10) DEFAULT NULL COMMENT '上次回款天数',
  `quarterPaymentPercent` double(10,2) DEFAULT NULL COMMENT '季度预付款占比',
  `commerceCode` varchar(255) DEFAULT NULL COMMENT '商业编号',
  `lastTheApplication` int(10) DEFAULT NULL COMMENT '上次申请折扣',
  `actualLevel` varchar(255) DEFAULT NULL COMMENT '实际级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经销商协议合作条款表';

/*Data for the table `scm_xs_dealerclause` */

insert  into `scm_xs_dealerclause`(`id`,`AgreementSummaryId`,`clauseCode`,`commerceName`,`functionType`,`AgreementType`,`AgreementStartDate`,`AgreementChangeDate`,`AgreementEndDate`,`paymentType`,`returnMoneyDay`,`promiseProvince`,`messageStandard`,`flowStandard`,`transportationDiscount`,`unloadPayer`,`subsidiarySum`,`memo`,`isOverfulfil`,`StardDay`,`endDay`,`theApplication`,`isSpecial`,`lastPaymentType`,`lastReturnMoneyDay`,`quarterPaymentPercent`,`commerceCode`,`lastTheApplication`,`actualLevel`) values ('10161006','10161005','JXTK1903020094190567','华润辽宁医药有限公司','纯销型','0','2019-03-03 00:00:00','2019-03-06 00:00:00','2019-03-08 00:00:00','1',0,NULL,0,0,0,'1',0,'备注1','1',0,60,23,'1','-',0,0.25,'10322',0,'经销商'),('10161007','10161005','JXTK1903020094190567','国药控股沈阳有限公司','纯销型','0','2019-03-05 00:00:00','2019-03-07 00:00:00','2019-03-09 00:00:00','0',0,NULL,0,0,0,'0',0,'备注','1',0,0,12,'1','-',0,0.25,'1843',0,'经销商'),('10261055','10191004','JXTK1903020370993164','国药控股沈阳有限公司','纯销型','0',NULL,NULL,NULL,NULL,0,NULL,0,0,0,NULL,0,NULL,NULL,0,0,0,NULL,'-',0,0.25,'1843',0,'经销商'),('10261060','10191001','JXTK1903021732374271','华润辽宁医药有限公司','纯销型','0',NULL,NULL,NULL,NULL,0,NULL,0,0,0,NULL,0,NULL,NULL,0,0,0,NULL,'-',0,0.25,'10322',0,'经销商'),('10261061','10191001','JXTK1903021732374271','华润辽宁医药有限公司','纯销型','0',NULL,NULL,NULL,NULL,0,NULL,0,0,0,NULL,0,NULL,NULL,0,0,0,NULL,'-',0,0.25,'10322',0,'经销商'),('10261075','10261071','JXTK1903050903179853','国药控股沈阳有限公司','纯销型','0',NULL,NULL,NULL,NULL,0,NULL,0,0,0,NULL,0,NULL,NULL,0,0,0,NULL,'-',0,0.25,'1843',0,'经销商');

/*Table structure for table `scm_xs_distributoragreementsummary` */

DROP TABLE IF EXISTS `scm_xs_distributoragreementsummary`;

CREATE TABLE `scm_xs_distributoragreementsummary` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `businessDivision` varchar(255) DEFAULT NULL COMMENT '商务分区',
  `Province` varchar(255) DEFAULT NULL COMMENT '省区',
  `businessDivisionId` varchar(64) DEFAULT NULL COMMENT '商务分区ID',
  `provinceId` varchar(64) DEFAULT NULL COMMENT '省区ID',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '流程ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '流程状态',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `agreementSummaryCode` varchar(64) DEFAULT NULL COMMENT '协议单号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='省区协议汇总表';

/*Data for the table `scm_xs_distributoragreementsummary` */

insert  into `scm_xs_distributoragreementsummary`(`id`,`businessDivision`,`Province`,`businessDivisionId`,`provinceId`,`approvalId`,`approvalState`,`ownerId`,`agreementSummaryCode`) values ('9621055','华中区','广东省','7111080','7111084','5004855','1','7111011',NULL),('9621057','华中区','广东省','7111080','7111084','5004874','1','7111011',NULL),('9631001','华中区','广东省','7111080','7111084','5004893','2','7111011',NULL),('9891006','华中区','广东省','7111080','7111084','5006390','1','7111011','FXXY1903010900923276'),('9911015','华中区','广东省','7111080','7111084','5006561','1','7111011','FXXY1903011325083311');

/*Table structure for table `scm_xs_distributorclause` */

DROP TABLE IF EXISTS `scm_xs_distributorclause`;

CREATE TABLE `scm_xs_distributorclause` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceId` varchar(64) DEFAULT NULL COMMENT '商业ID',
  `clauseCode` varchar(255) DEFAULT NULL COMMENT '协议号',
  `commerceName` varchar(255) DEFAULT NULL COMMENT '商业名称',
  `commerceLevel` varchar(255) DEFAULT NULL COMMENT '级别',
  `functionType` varchar(255) DEFAULT NULL COMMENT '职能类型',
  `isSubsidiary` varchar(600) DEFAULT NULL COMMENT '是否分子公司',
  `starLevel` varchar(255) DEFAULT NULL COMMENT '星级',
  `messageCost` double(10,2) DEFAULT NULL COMMENT '信息管理费',
  `isPromotional` varchar(1) DEFAULT NULL COMMENT '是否促销折让',
  `startDate` datetime DEFAULT NULL COMMENT '协议起始日期',
  `endDate` datetime DEFAULT NULL COMMENT '协议截止日期',
  `provincePlatformId` varchar(64) DEFAULT NULL COMMENT '省级平台',
  `upCommerceId` varchar(64) DEFAULT NULL COMMENT '上游商业',
  `memo` varchar(600) DEFAULT NULL COMMENT '备注',
  `DistributorAgreementId` varchar(64) DEFAULT NULL COMMENT '汇总表ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分销商协议合作条款表';

/*Data for the table `scm_xs_distributorclause` */

insert  into `scm_xs_distributorclause`(`id`,`commerceId`,`clauseCode`,`commerceName`,`commerceLevel`,`functionType`,`isSubsidiary`,`starLevel`,`messageCost`,`isPromotional`,`startDate`,`endDate`,`provincePlatformId`,`upCommerceId`,`memo`,`DistributorAgreementId`) values ('9621056','8071020','FXXY1902280632224145','测试123','分销商','高端专供','1','4',1000.00,'1','2019-02-28 00:00:00','2019-02-28 00:00:00','','','123','9621055'),('9621058','8071020','FXXY1902280410707284','测试123','分销商','高端专供','1','4',555.00,'1','2019-02-28 00:00:00','2019-03-08 00:00:00','','','123','9621057'),('9631002','8071020','FXXY1902280087048336','测试123','分销商','高端专供','1','3',777.00,'1','2019-02-28 00:00:00','2019-02-28 00:00:00','','','','9631001'),('9891007','8071020','FXXY1903010900923276','测试123','分销商','高端专供','0','4',999999.00,'0','2019-03-31 00:00:00','2019-03-31 00:00:00','','','','9891006'),('9911016','8071020','FXXY1903011325083311','测试123','分销商','高端专供','0','2',1900.00,'1','2019-03-31 00:00:00','2019-03-31 00:00:00','','','','9911015');

/*Table structure for table `scm_xs_everydaycontract` */

DROP TABLE IF EXISTS `scm_xs_everydaycontract`;

CREATE TABLE `scm_xs_everydaycontract` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `ownerName` varchar(255) DEFAULT NULL COMMENT '供货商',
  `dealer` varchar(255) DEFAULT NULL COMMENT '经销商',
  `commerceFirstId` varchar(64) DEFAULT NULL COMMENT '经销商首营ID',
  `contractCode` varchar(255) DEFAULT NULL COMMENT '合同编号',
  `consignee` varchar(255) DEFAULT NULL COMMENT '收货人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `marketingWay` varchar(1) DEFAULT NULL COMMENT '购销方式(0预付款,1赊销)',
  `shipAddress` varchar(255) DEFAULT NULL COMMENT '到货地址',
  `payType` varchar(1) DEFAULT NULL COMMENT '付款方式',
  `signTheAddress` varchar(255) DEFAULT NULL COMMENT '签订地址',
  `specialOpinion` varchar(255) DEFAULT NULL COMMENT '特求意见',
  `YUnit` varchar(255) DEFAULT NULL COMMENT '乙方单位',
  `YAddress` varchar(255) DEFAULT NULL COMMENT '乙方地址',
  `YOpenBank` varchar(255) DEFAULT NULL COMMENT '乙方开户行',
  `YDutyCode` varchar(255) DEFAULT NULL COMMENT '乙方税号',
  `YBankCode` varchar(255) DEFAULT NULL COMMENT '乙方账号',
  `YPhone` varchar(255) DEFAULT NULL COMMENT '乙方电话',
  `YPostcode` varchar(6) DEFAULT NULL COMMENT '乙方邮编',
  `JUnit` varchar(255) DEFAULT NULL COMMENT '甲方单位',
  `JAddress` varchar(255) DEFAULT NULL COMMENT '甲方地址',
  `JOpenBank` varchar(255) DEFAULT NULL COMMENT '甲方开户行',
  `JBankCode` varchar(255) DEFAULT NULL COMMENT '甲方账户',
  `JDutyCode` varchar(255) DEFAULT NULL COMMENT '甲方税号',
  `JPhone` varchar(255) DEFAULT NULL COMMENT '甲方电话',
  `JPostcode` varchar(6) DEFAULT NULL COMMENT '甲方邮编',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `DealerClauseId` varchar(64) DEFAULT NULL COMMENT '协议ID',
  `reduceContractTime` int(10) DEFAULT NULL COMMENT '减免合同理由',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '审批状态',
  `dateOfSigning` timestamp NULL DEFAULT NULL COMMENT '签订日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日常合同';

/*Data for the table `scm_xs_everydaycontract` */

/*Table structure for table `scm_xs_everydaycontractproduct` */

DROP TABLE IF EXISTS `scm_xs_everydaycontractproduct`;

CREATE TABLE `scm_xs_everydaycontractproduct` (
  `id` varchar(64) NOT NULL,
  `EveryDayContractId` varchar(64) DEFAULT NULL COMMENT '日常合同ID',
  `productGauge` varchar(255) DEFAULT NULL COMMENT '商品品规',
  `MedicineType` varchar(255) DEFAULT NULL COMMENT '药品类型',
  `commonName` varchar(255) DEFAULT NULL COMMENT '商品常用名称',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `Achange` int(10) DEFAULT NULL COMMENT '标准包装数',
  `suppyType` varchar(1) DEFAULT NULL COMMENT '药品供应状态',
  `suggestApplyNum` int(10) DEFAULT NULL COMMENT '建议本次申请量',
  `lastShipmentDate` datetime DEFAULT NULL COMMENT '上次发货时间',
  `SMSCurrentMonthSales` int(10) DEFAULT NULL COMMENT '省区本月SMS销量',
  `productCode` varchar(64) DEFAULT NULL COMMENT '药品编号',
  `isSMS` varchar(1) DEFAULT NULL COMMENT '是否SMS',
  `theNumber` int(10) DEFAULT NULL COMMENT '数量',
  `packageNum` int(10) DEFAULT NULL COMMENT '件数',
  `unitPrice` double(10,2) DEFAULT NULL COMMENT '含税单价',
  `sumPrice` double(10,2) DEFAULT NULL COMMENT '总价',
  `surplusSUm` int(10) DEFAULT NULL COMMENT '剩余数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日常合同商品表';

/*Data for the table `scm_xs_everydaycontractproduct` */

/*Table structure for table `scm_xs_focussend` */

DROP TABLE IF EXISTS `scm_xs_focussend`;

CREATE TABLE `scm_xs_focussend` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `FocusSendCode` varchar(255) DEFAULT NULL COMMENT '集中发货编号',
  `specialOpinion` varchar(255) DEFAULT NULL COMMENT '特求意见',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `BigContractId` varchar(64) DEFAULT NULL COMMENT '合同ID',
  `reduceContractTime` varchar(255) DEFAULT NULL COMMENT '减免合同次数理由',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '审批状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='集中发货合同';

/*Data for the table `scm_xs_focussend` */

insert  into `scm_xs_focussend`(`id`,`FocusSendCode`,`specialOpinion`,`ownerId`,`BigContractId`,`reduceContractTime`,`approvalId`,`approvalState`) values ('10291008',NULL,'',NULL,NULL,NULL,NULL,NULL),('10341001',NULL,'','7111011',NULL,NULL,NULL,NULL);

/*Table structure for table `scm_xs_focussendproduct` */

DROP TABLE IF EXISTS `scm_xs_focussendproduct`;

CREATE TABLE `scm_xs_focussendproduct` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `theNumber` int(10) DEFAULT NULL COMMENT '数量',
  `packageNum` int(10) DEFAULT NULL COMMENT '件数',
  `unitPrice` double(10,2) DEFAULT NULL COMMENT '含税单价',
  `sumPrice` double(10,2) DEFAULT NULL COMMENT '总价',
  `FocusSendId` varchar(64) DEFAULT NULL COMMENT '集中发货ID',
  `BigContractProductId` varchar(64) DEFAULT NULL COMMENT '大合同商品ID',
  `yearAndMonth` datetime DEFAULT NULL COMMENT '年月',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='集中发货商品表';

/*Data for the table `scm_xs_focussendproduct` */

/*Table structure for table `scm_zd_agenttype` */

DROP TABLE IF EXISTS `scm_zd_agenttype`;

CREATE TABLE `scm_zd_agenttype` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `agentType` varchar(255) NOT NULL COMMENT '剂型',
  `code` varchar(255) NOT NULL COMMENT '编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_CODE_UNQ` (`code`) USING BTREE,
  UNIQUE KEY `agentType` (`agentType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='剂型';

/*Data for the table `scm_zd_agenttype` */

insert  into `scm_zd_agenttype`(`id`,`agentType`,`code`,`memo`,`createPersion`,`createDate`) values ('6001005','滴剂','1','','超级管理员','2019-01-14 09:02:18'),('6001007','干混悬剂','2','','超级管理员','2019-01-14 09:02:42'),('6001008','胶囊剂','3','','超级管理员','2019-01-14 09:02:50'),('6001009','口服混悬剂','4','','超级管理员','2019-01-14 09:02:57'),('6001010','口服溶液剂','5','','超级管理员','2019-01-14 09:03:05'),('6001011','凝胶剂','6','','超级管理员','2019-01-14 09:03:14'),('6001012','片剂','7','','超级管理员','2019-01-14 09:03:23'),('6001013','乳膏剂','8','','超级管理员','2019-01-14 09:03:31'),('6001014','软膏剂','9','','超级管理员','2019-01-14 09:03:40'),('6001016','外用溶液剂','10','','超级管理员','2019-01-14 09:03:56'),('6001017','丸剂','11','','超级管理员','2019-01-14 09:04:05'),('6001018','原料药','12','','超级管理员','2019-01-14 09:04:13'),('6251004','无','13','','超级管理员','2019-01-15 17:47:26'),('6251007','胶囊','14','','超级管理员','2019-01-15 17:50:34'),('6251016','溶液','15','','超级管理员','2019-01-15 17:56:23');

/*Table structure for table `scm_zd_businessdivision` */

DROP TABLE IF EXISTS `scm_zd_businessdivision`;

CREATE TABLE `scm_zd_businessdivision` (
  `id` varchar(64) NOT NULL COMMENT '商务分区id',
  `businessDivision` varchar(255) NOT NULL COMMENT '商务分区',
  `code` varchar(255) NOT NULL COMMENT '编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_CODE_UNQ` (`code`) USING BTREE,
  UNIQUE KEY `businessDivision` (`businessDivision`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商务分区';

/*Data for the table `scm_zd_businessdivision` */

insert  into `scm_zd_businessdivision`(`id`,`businessDivision`,`code`,`memo`,`createPersion`,`createDate`) values ('6001001','华北区','310','','超级管理员','2019-01-14 10:39:27'),('6001002','华东区','330','','超级管理员','2019-01-14 08:59:39'),('6001003','西南区','350','','超级管理员','2019-01-14 08:59:58'),('6001004','华中区','360','','超级管理员','2019-01-14 09:00:23');

/*Table structure for table `scm_zd_commercelevel` */

DROP TABLE IF EXISTS `scm_zd_commercelevel`;

CREATE TABLE `scm_zd_commercelevel` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceLevel` varchar(255) DEFAULT NULL COMMENT '商业级别',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `scm_zd_commercelevel` */

insert  into `scm_zd_commercelevel`(`id`,`commerceLevel`,`memo`,`createPersion`,`createDate`,`code`) values ('6271001','经销商','1','原浩','2019-02-16 11:44:13','001'),('6271002','分销商','','原浩','2019-02-16 11:46:44','002'),('6271003','连锁总部','','原浩','2019-02-16 11:47:24','003'),('6271004','一级电商','','原浩','2019-02-16 11:47:31','004'),('6271005','二级电商','','原浩','2019-02-16 11:47:45','005'),('9491013','二级电商2131','dfsf','原浩','2019-02-26 16:32:09','21321321'),('9551027','一级','备注','原浩','2019-02-27 08:55:22','32142143');

/*Table structure for table `scm_zd_dutyaffirm` */

DROP TABLE IF EXISTS `scm_zd_dutyaffirm`;

CREATE TABLE `scm_zd_dutyaffirm` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `dutyAffirm` varchar(255) NOT NULL COMMENT '责任认定',
  `code` varchar(255) NOT NULL COMMENT '编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `ownerId` varchar(64) NOT NULL COMMENT '货主ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='责任认定';

/*Data for the table `scm_zd_dutyaffirm` */

insert  into `scm_zd_dutyaffirm`(`id`,`dutyAffirm`,`code`,`memo`,`createPersion`,`createDate`,`ownerId`) values ('6071006','测试0125','CS0125','测试','超级管理员','2019-01-14 18:47:26','1012'),('6081001','测试0127','CS0127','测试','超级管理员','2019-01-14 18:49:58','1012'),('6091001','测试0122','CS0123','测试','超级管理员','2019-01-14 18:45:49','1012'),('6221001','测试0126','CS0126','测试','超级管理员','2019-01-15 15:15:41','1001');

/*Table structure for table `scm_zd_dutyoffice` */

DROP TABLE IF EXISTS `scm_zd_dutyoffice`;

CREATE TABLE `scm_zd_dutyoffice` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `dutyOffice` varchar(255) NOT NULL COMMENT '责任办事处',
  `code` varchar(255) NOT NULL COMMENT '编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `ownerId` varchar(64) NOT NULL COMMENT '货主ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_CODE_UNQ` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='责任办事处';

/*Data for the table `scm_zd_dutyoffice` */

insert  into `scm_zd_dutyoffice`(`id`,`dutyOffice`,`code`,`memo`,`createPersion`,`createDate`,`ownerId`) values ('','123','123','123','123','2018-12-25 15:03:58','');

/*Table structure for table `scm_zd_functiontype` */

DROP TABLE IF EXISTS `scm_zd_functiontype`;

CREATE TABLE `scm_zd_functiontype` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceLevelId` varchar(64) DEFAULT NULL COMMENT '商业级别id',
  `functionType` varchar(255) DEFAULT NULL COMMENT '职能类型',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `scm_zd_functiontype` */

insert  into `scm_zd_functiontype`(`id`,`commerceLevelId`,`functionType`,`memo`,`createPersion`,`createDate`,`code`) values ('6271006',NULL,'纯销型','',NULL,NULL,'001'),('6271007',NULL,'纯销型','',NULL,NULL,'001'),('6271008',NULL,'纯销型','',NULL,NULL,'001'),('6271014','6271002','深度专供','',NULL,NULL,'002'),('6271015','6271002','基药专供','',NULL,NULL,'003'),('6271016','6271002','军供站','',NULL,NULL,'004'),('6271017','6271002','深度相关混合','',NULL,NULL,'005'),('6271018','6271002','非深度相关混合','',NULL,NULL,'006'),('8071121','6271001','测试2','测试',NULL,NULL,'1'),('8071122','6271001','测试3','测试33',NULL,NULL,'2'),('8071123','6271001','测试4','测试4',NULL,NULL,'3'),('8071124','6271004','测试职能','测试',NULL,NULL,'001'),('8121005',NULL,'测试5','',NULL,NULL,'123456');

/*Table structure for table `scm_zd_getmoneywarehouse` */

DROP TABLE IF EXISTS `scm_zd_getmoneywarehouse`;

CREATE TABLE `scm_zd_getmoneywarehouse` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `getMoneyWarehouse` varchar(255) NOT NULL COMMENT '收货仓库',
  `code` varchar(255) NOT NULL COMMENT '编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_CODE_UNQ` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='收货仓库';

/*Data for the table `scm_zd_getmoneywarehouse` */

insert  into `scm_zd_getmoneywarehouse`(`id`,`getMoneyWarehouse`,`code`,`memo`,`createPersion`,`createDate`,`ownerId`) values ('6411003','01仓库','001','','超级管理员','2019-02-12 14:05:50','81068');

/*Table structure for table `scm_zd_getticket` */

DROP TABLE IF EXISTS `scm_zd_getticket`;

CREATE TABLE `scm_zd_getticket` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `getTicket` varchar(255) NOT NULL COMMENT '收票方',
  `code` varchar(255) NOT NULL COMMENT '编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `ownerId` varchar(64) NOT NULL COMMENT '货主ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_CODE_UNQ` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='收票方';

/*Data for the table `scm_zd_getticket` */

insert  into `scm_zd_getticket`(`id`,`getTicket`,`code`,`memo`,`createPersion`,`createDate`,`ownerId`) values ('7241001','澳美','1','','原浩','2019-01-24 17:55:35','7111011'),('7241002','博成','2','','原浩','2019-01-24 17:55:45','7111011');

/*Table structure for table `scm_zd_gmpcertiflcationscope` */

DROP TABLE IF EXISTS `scm_zd_gmpcertiflcationscope`;

CREATE TABLE `scm_zd_gmpcertiflcationscope` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `GMPCertiflcationScope` varchar(255) NOT NULL COMMENT 'GMP证书认证范围',
  `code` varchar(255) NOT NULL COMMENT '编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_CODE_UNQ` (`code`) USING BTREE,
  UNIQUE KEY `GMPCertiflcationScope` (`GMPCertiflcationScope`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='GMP证书认证范围';

/*Data for the table `scm_zd_gmpcertiflcationscope` */

/*Table structure for table `scm_zd_keepingcondition` */

DROP TABLE IF EXISTS `scm_zd_keepingcondition`;

CREATE TABLE `scm_zd_keepingcondition` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `KeepingCondition` varchar(255) NOT NULL COMMENT '贮存条件',
  `suggestWarehouse` varchar(255) NOT NULL COMMENT '建议库区',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `ownerId` varchar(64) NOT NULL COMMENT '货主ID',
  `code` varchar(255) DEFAULT NULL COMMENT '编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='贮存条件';

/*Data for the table `scm_zd_keepingcondition` */

insert  into `scm_zd_keepingcondition`(`id`,`KeepingCondition`,`suggestWarehouse`,`memo`,`createPersion`,`createDate`,`ownerId`,`code`) values ('6651008','10℃~25℃','阴凉库,25度常温库','','原浩','2019-01-21 09:58:14','7111011','1'),('6651009','<20℃','阴凉库','','原浩','2019-01-21 09:58:40','7111011','2'),('6651010','10℃~30℃','25度常温库,30度常温库','','原浩','2019-01-21 09:59:02','7111011','3'),('6651011','常温，密闭，遮光保存','无','','原浩','2019-01-21 10:00:13','7111011','4'),('6651012','25℃以下干燥密封保存','无','','原浩','2019-01-21 10:00:35','7111011','5');

/*Table structure for table `scm_zd_managementscope` */

DROP TABLE IF EXISTS `scm_zd_managementscope`;

CREATE TABLE `scm_zd_managementscope` (
  `id` varchar(64) NOT NULL COMMENT '生产经营范围ID',
  `managementScope` varchar(255) NOT NULL COMMENT '生产经营范围',
  `code` varchar(255) NOT NULL COMMENT '编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_CODE_UNQ` (`code`) USING BTREE,
  UNIQUE KEY `managementScope` (`managementScope`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='生产经营范围';

/*Data for the table `scm_zd_managementscope` */

insert  into `scm_zd_managementscope`(`id`,`managementScope`,`code`,`memo`,`createPersion`,`createDate`) values ('5971005','中成药','1','','超级管理员','2019-01-14 09:45:14'),('6001020','化学原料药','2','','超级管理员','2019-01-14 09:45:21'),('6001021','化学药制剂','3','','超级管理员','2019-01-14 09:45:29'),('6001022','抗生素原料药','4','','超级管理员','2019-01-14 09:45:35'),('6001023','抗生素制剂','5','','超级管理员','2019-01-14 09:45:44'),('6001024','第二类精神药品原料药','6','','超级管理员','2019-01-14 09:45:53'),('6001025','生化药品','7','','超级管理员','2019-01-14 09:46:02'),('6001026','第二类精神药品制剂','8','','超级管理员','2019-01-14 09:46:15'),('6001027','胶囊剂','9','','超级管理员','2019-01-14 09:49:07'),('6001028','乳膏剂','10','','超级管理员','2019-01-14 09:49:17'),('6001029','口服混悬剂','11','','超级管理员','2019-01-14 09:49:26'),('6001030','凝胶剂','12','','原浩','2019-02-25 18:31:48'),('6001031','干混悬剂','13','','超级管理员','2019-01-14 09:49:53'),('6001032','口服溶液剂','14','','超级管理员','2019-01-14 09:50:02'),('6001033','滴剂','15','','超级管理员','2019-01-14 09:50:10'),('6001034','片剂','16','','超级管理员','2019-01-14 09:50:25'),('6001035','丸剂','17','','超级管理员','2019-01-30 18:39:32'),('6001036','外用溶液剂','18','','超级管理员','2019-01-14 09:50:41'),('6001037','全部剂型','19','','超级管理员','2019-01-14 09:50:48'),('6001038','软膏剂','20','','超级管理员','2019-01-14 09:51:03'),('6001039','原料药','21','','超级管理员','2019-01-14 10:47:15');

/*Table structure for table `scm_zd_manufacturer` */

DROP TABLE IF EXISTS `scm_zd_manufacturer`;

CREATE TABLE `scm_zd_manufacturer` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `manufacturer` varchar(255) NOT NULL COMMENT '生产厂家',
  `code` varchar(255) NOT NULL COMMENT '编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_CODE_UNQ` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='生产厂家';

/*Data for the table `scm_zd_manufacturer` */

insert  into `scm_zd_manufacturer`(`id`,`manufacturer`,`code`,`memo`,`createPersion`,`createDate`,`ownerId`) values ('6651004','澳美制药厂','1','','原浩','2019-01-21 09:44:53','7111011'),('6651005','海南澳美华制药有限公司','2','','原浩','2019-01-21 09:45:00','7111011'),('6651006','澳美制药厂-海南澳美华制药有限公司分包装','3','','原浩','2019-01-21 09:45:36','7111011');

/*Table structure for table `scm_zd_medicineclassify` */

DROP TABLE IF EXISTS `scm_zd_medicineclassify`;

CREATE TABLE `scm_zd_medicineclassify` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `medicineClassify` varchar(255) NOT NULL COMMENT '药品分类',
  `code` varchar(255) NOT NULL COMMENT '编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_CODE_UNQ` (`code`) USING BTREE,
  UNIQUE KEY `medicineClassify` (`medicineClassify`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='药品分类';

/*Data for the table `scm_zd_medicineclassify` */

insert  into `scm_zd_medicineclassify`(`id`,`medicineClassify`,`code`,`memo`,`createPersion`,`createDate`) values ('5991007','皮肤药','1','','超级管理员','2019-01-14 09:53:20'),('6001044','儿科','2','','超级管理员','2019-01-14 09:53:30'),('6021001','呼抗','3','','超级管理员','2019-01-14 14:53:38'),('6251002','皮科','4','','超级管理员','2019-01-15 17:44:06'),('6251003','无','5','','超级管理员','2019-01-15 17:46:46'),('6251025','AMH','6','','超级管理员','2019-01-15 18:09:55'),('6251026','疼痛','7','','超级管理员','2019-01-15 18:10:46');

/*Table structure for table `scm_zd_medicinetype` */

DROP TABLE IF EXISTS `scm_zd_medicinetype`;

CREATE TABLE `scm_zd_medicinetype` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `medicineType` varchar(255) NOT NULL COMMENT '药品类型',
  `code` varchar(255) NOT NULL COMMENT '编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_CODE_UNQ` (`code`) USING BTREE,
  UNIQUE KEY `medicineType` (`medicineType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='药品类型';

/*Data for the table `scm_zd_medicinetype` */

insert  into `scm_zd_medicinetype`(`id`,`medicineType`,`code`,`memo`,`createPersion`,`createDate`) values ('6001042','普通药品','1','','超级管理员','2019-01-14 09:52:52'),('6001043','特殊管理药品','2','','原浩','2019-01-31 09:37:59');

/*Table structure for table `scm_zd_promisebook` */

DROP TABLE IF EXISTS `scm_zd_promisebook`;

CREATE TABLE `scm_zd_promisebook` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `promiseBook` varchar(255) NOT NULL COMMENT '承诺书',
  `code` varchar(255) NOT NULL COMMENT '编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `ownerId` varchar(64) NOT NULL COMMENT '货主ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_CODE_UNQ` (`code`) USING BTREE,
  UNIQUE KEY `promiseBook` (`promiseBook`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='承诺书';

/*Data for the table `scm_zd_promisebook` */

insert  into `scm_zd_promisebook`(`id`,`promiseBook`,`code`,`memo`,`createPersion`,`createDate`,`ownerId`) values ('6481001','承诺书01','01','12','原浩','2019-02-12 13:58:44','7111011'),('6711003','承诺书02','02','','原浩','2019-01-24 17:56:29','7111011');

/*Table structure for table `scm_zd_province` */

DROP TABLE IF EXISTS `scm_zd_province`;

CREATE TABLE `scm_zd_province` (
  `id` varchar(64) NOT NULL COMMENT '省区ID',
  `businessDivisionId` varchar(64) NOT NULL COMMENT '商务分区ID',
  `province` varchar(255) NOT NULL COMMENT '省区',
  `code` varchar(64) NOT NULL COMMENT '省区编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_CODE_UNQ` (`code`) USING BTREE,
  UNIQUE KEY `INDEX_BDID_UNQ` (`businessDivisionId`) USING BTREE,
  UNIQUE KEY `province` (`province`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='省区';

/*Data for the table `scm_zd_province` */

insert  into `scm_zd_province`(`id`,`businessDivisionId`,`province`,`code`,`memo`,`createPersion`,`createDate`) values ('6251048','6001003','123','123','123','超级管理员','2019-01-15 22:21:14');

/*Table structure for table `scm_zd_returnrequestcause` */

DROP TABLE IF EXISTS `scm_zd_returnrequestcause`;

CREATE TABLE `scm_zd_returnrequestcause` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `returnRequestCause` varchar(255) NOT NULL COMMENT '退货申请原因',
  `code` varchar(255) NOT NULL COMMENT '编码',
  `memo` varchar(300) DEFAULT NULL COMMENT '备注',
  `createPersion` varchar(255) NOT NULL COMMENT '创建人',
  `createDate` datetime NOT NULL COMMENT '创建时间',
  `ownerId` varchar(64) NOT NULL COMMENT '货主ID',
  `returnType` varchar(1) DEFAULT NULL COMMENT '退货类别',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `INDEX_CODE_UNQ` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='退货申请原因';

/*Data for the table `scm_zd_returnrequestcause` */

insert  into `scm_zd_returnrequestcause`(`id`,`returnRequestCause`,`code`,`memo`,`createPersion`,`createDate`,`ownerId`,`returnType`) values ('6431001','不要了','001','','原浩','2019-01-17 19:10:59','7111011','1');

/*Table structure for table `scm_zsj_accessory` */

DROP TABLE IF EXISTS `scm_zsj_accessory`;

CREATE TABLE `scm_zsj_accessory` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `type` varchar(1) DEFAULT NULL COMMENT '类型(0:供应商首营,1:商业首营,...)',
  `fileUrl` varchar(255) DEFAULT NULL COMMENT '文件URL',
  `updateDate` datetime DEFAULT NULL COMMENT '上传时间',
  `dateId` varchar(64) DEFAULT NULL COMMENT '数据ID',
  `fileName` varchar(255) DEFAULT NULL COMMENT '文件名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='附件表';

/*Data for the table `scm_zsj_accessory` */

/*Table structure for table `scm_zsj_achange` */

DROP TABLE IF EXISTS `scm_zsj_achange`;

CREATE TABLE `scm_zsj_achange` (
  `id` varchar(64) DEFAULT NULL COMMENT 'id',
  `productId` varchar(64) DEFAULT NULL COMMENT '商品id',
  `achage` varchar(64) DEFAULT NULL COMMENT '件装量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `scm_zsj_achange` */

insert  into `scm_zsj_achange`(`id`,`productId`,`achage`) values ('10331002','9311108','15'),('10331003','9311108','20'),('10331004','8061027','16'),('10331005','9311108','35'),('10341002','6651023','20'),('10421006','9311108','40');

/*Table structure for table `scm_zsj_commerce` */

DROP TABLE IF EXISTS `scm_zsj_commerce`;

CREATE TABLE `scm_zsj_commerce` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceName` varchar(255) DEFAULT NULL COMMENT '商业名称',
  `fileCode` varchar(255) DEFAULT NULL COMMENT '档案编号',
  `commerceState` varchar(1) DEFAULT NULL COMMENT '企业类别(0药品经营企业,1医疗机构)',
  `commerceCode` varchar(255) DEFAULT NULL COMMENT '客户编号',
  `conpanyPersion` varchar(255) DEFAULT NULL COMMENT '企业负责人',
  `contactPersion` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系方式',
  `openBank` varchar(255) DEFAULT NULL COMMENT '开户行',
  `bankCode` varchar(30) DEFAULT NULL COMMENT '银行账号',
  `dutyParagraph` varchar(255) DEFAULT NULL COMMENT '税号',
  `businessDivisionId` varchar(64) DEFAULT NULL COMMENT '商务分区ID',
  `businessDivision` varchar(255) DEFAULT NULL COMMENT '商务分区',
  `provinceId` varchar(64) DEFAULT NULL COMMENT '省区ID',
  `province` varchar(255) DEFAULT NULL COMMENT '省区',
  `qualityValidity` datetime DEFAULT NULL COMMENT '药品质量保证协议有效期',
  `registerAddress` varchar(255) DEFAULT NULL COMMENT '注册地址',
  `sealStyle` varchar(1) DEFAULT NULL COMMENT '印章样式',
  `billsStyle` varchar(1) DEFAULT NULL COMMENT '随货单据样式',
  `enterPersion` varchar(255) DEFAULT NULL COMMENT '录入人',
  `enterDate` datetime DEFAULT NULL COMMENT '录入日期',
  `commerceLevel` varchar(255) DEFAULT NULL COMMENT '商业级别',
  `functionType` varchar(255) DEFAULT NULL COMMENT '职能类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业表';

/*Data for the table `scm_zsj_commerce` */

insert  into `scm_zsj_commerce`(`id`,`commerceName`,`fileCode`,`commerceState`,`commerceCode`,`conpanyPersion`,`contactPersion`,`phone`,`openBank`,`bankCode`,`dutyParagraph`,`businessDivisionId`,`businessDivision`,`provinceId`,`province`,`qualityValidity`,`registerAddress`,`sealStyle`,`billsStyle`,`enterPersion`,`enterDate`,`commerceLevel`,`functionType`) values ('6481002','国药控股沈阳有限公司','DA1901180710043909','0','1843','孙晓丹','孙晓丹','17737317099','招商银行沈阳分行营业部','240182454810001','91210000755766207T','7111078','华北区','','','2019-01-17 00:00:00','辽宁省沈阳市苏家屯区雪莲街158-1号','1','1','原浩','2019-01-18 15:47:49','经销商','纯销型'),('6481003','华润辽宁医药有限公司','DA1901180890379742','0','10322','陈悦','陈悦','17737317099','兴业银行沈阳分行营业部','422010100100409172','210102567599237','7111078','华北区','','','2019-01-17 00:00:00','辽宁省沈阳市和平区南六马路33号（1-5轴）、沈阳市和平区西安街70号（5-8轴 A-F轴）、沈阳市和平区南京南街197号汇锦金融中心19层1901至1907、1909至1911室','1','1','原浩','2019-01-18 15:49:38','经销商','纯销型'),('6481004','辽宁九州通医药有限公司','DA1901180748603255','0','55937','刘强','刘强','17737317099','中国建设银行股份有限公司沈阳虎石台支行','21001493601052503552','210113784567696','7111078','华北区','','','2019-01-16 00:00:00','沈阳市虎石台开发区建设路81号','1','1','原浩',NULL,'经销商','纯销型'),('6481005','上药康德乐（辽宁）医药有限公司','DA1901181026318007','0','33315','贾正军','贾正军','17737317099','沈阳市工商银行中山广场支行','3301003409249028593','91210100701791677X','7111078','华北区','','','2019-01-14 00:00:00','沈阳市和平区中山路83号海悦国际大厦B座12层 1220-1226室','1','1','原浩',NULL,'经销商','纯销型'),('8041026','测试','DA1901300985825171','0','10086','测试','测试','17737317099','测试','1564548945416','4654984518954','7111080','华中区','','','2019-01-30 00:00:00','测试','0','0','原浩',NULL,'经销商','纯销型'),('8041084','测试1','DA1901300400187505','0','测试1','测试1','测试1','测试1','测试1','测试1','测试1','7111078','华北区','','','2019-01-31 00:00:00','测试1','0','0','原浩',NULL,'经销商','纯销型'),('8071020','测试123','DA1901312121072930','0','测试123','测试123','测试1231','1773731709','测试123','5645645646123','65456132152222q','7111080','华中区','7111084','广东省','2019-01-31 00:00:00','测试123','0','0','原浩',NULL,'分销商','高端专供'),('9031003','药品经营企业','DA1902250287626186','0','DA2132131231321','cx','cx','19012312312','icbc','ewdfwe','2312432412','7111078','华北区','','','2019-02-12 00:00:00','z','1','1','原浩','2019-02-25 09:13:43','一级电商',''),('9311033','测试003','DA1902250293916311','0','cs003','原浩','原浩','17737317099','中国银行','15635596322125654','541654165165','7111080','华中区','7111083','湖南+江西','2019-02-28 00:00:00','山西长治','0','0','原浩','2019-02-25 18:34:43','分销商','高端专供');

/*Table structure for table `scm_zsj_commerceacceptstate` */

DROP TABLE IF EXISTS `scm_zsj_commerceacceptstate`;

CREATE TABLE `scm_zsj_commerceacceptstate` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `agtherId` varchar(64) DEFAULT NULL COMMENT '总表ID',
  `commerceId` varchar(64) DEFAULT NULL COMMENT '商业ID',
  `commerceCode` varchar(255) DEFAULT NULL COMMENT '商业编码',
  `acceptState` varchar(1) DEFAULT NULL COMMENT '认可状态(0:认可,1:考察中,2:终止)',
  `startDate` datetime DEFAULT NULL COMMENT '开始日期',
  `memo` varchar(600) DEFAULT NULL COMMENT '备注',
  `endDate` datetime DEFAULT NULL COMMENT '截止日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业认可状态表';

/*Data for the table `scm_zsj_commerceacceptstate` */

insert  into `scm_zsj_commerceacceptstate`(`id`,`agtherId`,`commerceId`,`commerceCode`,`acceptState`,`startDate`,`memo`,`endDate`) values ('10271021','10271015',NULL,'10322','0','2018-01-01 00:00:00','123','2018-03-31 00:00:00'),('10271023','10271022','6481003','10322','0','2019-10-01 00:00:00','321','2019-12-31 00:00:00'),('10271025','10271024','6481003','10322','0','2019-10-01 00:00:00','321','2019-12-31 00:00:00'),('10271029','10271026','6481002','1843','0','2018-07-01 00:00:00','ddd','2018-09-30 00:00:00'),('10271031','10271030','6481002','1843','0','2019-07-01 00:00:00','rerr','2019-09-30 00:00:00'),('10271035','10271032','6481003','10322','0','2019-10-01 00:00:00','kkk','2019-12-31 00:00:00'),('10271038','10271036','6481002','1843','0','2016-01-01 00:00:00','ooo','2016-03-31 00:00:00'),('10271041','10271039','6481003','10322','0','2011-07-01 00:00:00','sdsd','2011-09-30 00:00:00');

/*Table structure for table `scm_zsj_commerceacceptstateagther` */

DROP TABLE IF EXISTS `scm_zsj_commerceacceptstateagther`;

CREATE TABLE `scm_zsj_commerceacceptstateagther` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `approvalCode` varchar(255) DEFAULT NULL COMMENT '申请单号',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '审批状态',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `operatorName` varchar(255) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业认可状态总表';

/*Data for the table `scm_zsj_commerceacceptstateagther` */

insert  into `scm_zsj_commerceacceptstateagther`(`id`,`approvalCode`,`approvalId`,`approvalState`,`ownerId`,`operatorName`) values ('10271015',NULL,NULL,'0','7111011','原浩'),('10271022','SYRK1903052021041659','5007593','2','7111011','原浩'),('10271024','SYRK1903051215475584','5007612','2','7111011','原浩'),('10271026',NULL,'5007631','1','7111011','原浩'),('10271030','SYRK1903051298597317','5007650','1','7111011','原浩'),('10271032',NULL,'5007669','1','7111011','原浩'),('10271036','SYRK1903050891779144','5007688','1','7111011','原浩'),('10271039',NULL,NULL,'0','7111011','原浩');

/*Table structure for table `scm_zsj_commerceacceptstatets` */

DROP TABLE IF EXISTS `scm_zsj_commerceacceptstatets`;

CREATE TABLE `scm_zsj_commerceacceptstatets` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceId` varchar(64) DEFAULT NULL COMMENT '主商业ID',
  `commerceCode` varchar(255) DEFAULT NULL COMMENT '主商业编码',
  `acceptState` varchar(1) DEFAULT NULL COMMENT '认可状态(0:认可,1:考察中,2:终止)',
  `effectiveDate` datetime DEFAULT NULL COMMENT '生效日期',
  `memo` varchar(600) DEFAULT NULL COMMENT '备注',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业认可状态暂存表';

/*Data for the table `scm_zsj_commerceacceptstatets` */

/*Table structure for table `scm_zsj_commerceaccredit` */

DROP TABLE IF EXISTS `scm_zsj_commerceaccredit`;

CREATE TABLE `scm_zsj_commerceaccredit` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `entrustId` varchar(64) DEFAULT NULL COMMENT '委托书ID',
  `productCode` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `productName` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `commonName` varchar(255) DEFAULT NULL COMMENT '通用名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业授权销售品种';

/*Data for the table `scm_zsj_commerceaccredit` */

/*Table structure for table `scm_zsj_commerceblacklist` */

DROP TABLE IF EXISTS `scm_zsj_commerceblacklist`;

CREATE TABLE `scm_zsj_commerceblacklist` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `approvalCode` varchar(255) DEFAULT NULL COMMENT '申请单号',
  `businessDivisionId` varchar(64) DEFAULT NULL COMMENT '商务分区ID',
  `businessDivision` varchar(255) DEFAULT NULL COMMENT '商务分区',
  `provinceId` varchar(64) DEFAULT NULL COMMENT '省区ID',
  `province` varchar(255) DEFAULT NULL COMMENT '省区',
  `commerceCode` varchar(255) DEFAULT NULL COMMENT '商业编号',
  `commerceName` varchar(255) DEFAULT NULL COMMENT '商业名称',
  `classify` varchar(1) DEFAULT NULL COMMENT '分类(0:窜货,1:虚假流向)',
  `penaltyReason` varchar(600) DEFAULT NULL COMMENT '处罚原因',
  `bannedBreed` varchar(600) DEFAULT NULL COMMENT '禁销品种',
  `approvalMemo` varchar(600) DEFAULT NULL COMMENT '申请原因',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '审批状态',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `approvalDate` datetime DEFAULT NULL COMMENT '申请日期',
  `operatorName` varchar(255) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业黑名单表';

/*Data for the table `scm_zsj_commerceblacklist` */

insert  into `scm_zsj_commerceblacklist`(`id`,`approvalCode`,`businessDivisionId`,`businessDivision`,`provinceId`,`province`,`commerceCode`,`commerceName`,`classify`,`penaltyReason`,`bannedBreed`,`approvalMemo`,`approvalId`,`approvalState`,`ownerId`,`approvalDate`,`operatorName`) values ('10001019','HMD1903011237120168','7111078','华北区','','','55937','辽宁九州通医药有限公司','0','原因1','禁销1','备注1','5007025','1','7111011','2019-03-01 11:27:11','原浩'),('10081001','HMD1903011101722864','7111078','华北区','','','1843','国药控股沈阳有限公司','1','原因1111111111111','1111111111111111111','111111111111111111','5007177','1','7111011','2019-03-01 17:19:33','原浩'),('10081002','HMD1903020175134985','7111078','华北区','','','55937','','0','','','',NULL,'0','7111011','2019-03-02 08:36:54',NULL),('10201001','HMD1903041180546902','','','','','','','1','','','',NULL,'0','7111011','2019-03-04 09:10:46',NULL),('10201002','HMD1903040201103794','','','','','','','0','','','',NULL,'0','7111011','2019-03-04 09:19:11',NULL),('10281001','HMD1903051355116877','','','','','','','','','','',NULL,'0','7111011','2019-03-05 13:50:28','原浩');

/*Table structure for table `scm_zsj_commercebusiness` */

DROP TABLE IF EXISTS `scm_zsj_commercebusiness`;

CREATE TABLE `scm_zsj_commercebusiness` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `register` varchar(255) DEFAULT NULL COMMENT '注册号',
  `licenseValidity` datetime DEFAULT NULL COMMENT '营业执照有效期',
  `legalPersion` varchar(255) DEFAULT NULL COMMENT '法定代表人',
  `registerPrice` varchar(20) DEFAULT NULL COMMENT '注册资本(万元)',
  `commerceType` varchar(255) DEFAULT NULL COMMENT '企业类型',
  `yearReportValidity` datetime DEFAULT NULL COMMENT '年度报表有效期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业营业执照信息';

/*Data for the table `scm_zsj_commercebusiness` */

insert  into `scm_zsj_commercebusiness`(`id`,`commerceFirstId`,`register`,`licenseValidity`,`legalPersion`,`registerPrice`,`commerceType`,`yearReportValidity`) values ('9621004','9621001','测试','2019-02-28 00:00:00','测试','799','测试','2019-02-28 00:00:00'),('9621041','9621038','3332342','2019-02-28 00:00:00','测试','33333','测试','2019-02-28 00:00:00'),('9681004','9681001','312321','2019-02-28 00:00:00','测试','8000','测试','2019-02-28 00:00:00');

/*Table structure for table `scm_zsj_commercedutyofficer` */

DROP TABLE IF EXISTS `scm_zsj_commercedutyofficer`;

CREATE TABLE `scm_zsj_commercedutyofficer` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceId` varchar(64) DEFAULT NULL COMMENT '商业ID',
  `commerceCode` varchar(255) DEFAULT NULL COMMENT '商业编码',
  `dutyOfficerId` varchar(64) DEFAULT NULL COMMENT '责任人ID',
  `dutyOfficer` varchar(255) DEFAULT NULL COMMENT '责任人',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '审批状态',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `operatorName` varchar(255) DEFAULT NULL COMMENT '操作人',
  `operatorId` varchar(64) DEFAULT NULL COMMENT '操作人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业责任人表';

/*Data for the table `scm_zsj_commercedutyofficer` */

/*Table structure for table `scm_zsj_commerceentrusebook` */

DROP TABLE IF EXISTS `scm_zsj_commerceentrusebook`;

CREATE TABLE `scm_zsj_commerceentrusebook` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `entrustCode` varchar(255) DEFAULT NULL COMMENT '委托书编号',
  `entrustName` varchar(255) DEFAULT NULL COMMENT '委托书姓名',
  `entrustValidity` datetime DEFAULT NULL COMMENT '委托书有效期至',
  `IDNumber` varchar(64) DEFAULT NULL COMMENT '身份证号',
  `IDValidity` datetime DEFAULT NULL COMMENT '身份证有效期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业委托书';

/*Data for the table `scm_zsj_commerceentrusebook` */

insert  into `scm_zsj_commerceentrusebook`(`id`,`commerceFirstId`,`entrustCode`,`entrustName`,`entrustValidity`,`IDNumber`,`IDValidity`) values ('9621009','9621001','测试','测试','2019-02-28 00:00:00','测试','2019-02-28 00:00:00'),('9621046','9621038','测试','测试','2019-02-28 00:00:00','1213213423123123','2019-02-28 00:00:00'),('9681009','9681001','测试','测试','2019-02-28 00:00:00','测试','2019-02-27 00:00:00');

/*Table structure for table `scm_zsj_commercefirst` */

DROP TABLE IF EXISTS `scm_zsj_commercefirst`;

CREATE TABLE `scm_zsj_commercefirst` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceId` varchar(64) DEFAULT NULL COMMENT '商业ID',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '审批状态',
  `operatorName` varchar(255) DEFAULT NULL COMMENT '申请人',
  `operatorDate` datetime DEFAULT NULL COMMENT '申请时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业首营记录表';

/*Data for the table `scm_zsj_commercefirst` */

insert  into `scm_zsj_commercefirst`(`id`,`commerceId`,`ownerId`,`approvalId`,`approvalState`,`operatorName`,`operatorDate`) values ('9621001','6481002','7111011','5004492','2','原浩','2019-02-28 00:33:06'),('9621038','6481003','7111011','5004790','2','原浩','2019-02-28 00:55:38'),('9681001','6481004','7111011','5005205','1','原浩','2019-02-28 09:20:41');

/*Table structure for table `scm_zsj_commercelicence` */

DROP TABLE IF EXISTS `scm_zsj_commercelicence`;

CREATE TABLE `scm_zsj_commercelicence` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `licenseKey` varchar(255) DEFAULT NULL COMMENT '许可证号',
  `validity` datetime DEFAULT NULL COMMENT '有效期',
  `managementScope` varchar(255) DEFAULT NULL COMMENT '生产/经营范围',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业许可证信息';

/*Data for the table `scm_zsj_commercelicence` */

insert  into `scm_zsj_commercelicence`(`id`,`commerceFirstId`,`licenseKey`,`validity`,`managementScope`) values ('9621003','9621001','测试','2019-02-20 00:00:00','滴剂/外用溶液剂/全部剂型'),('9621040','9621038','3333232','2019-02-28 00:00:00','滴剂/片剂/外用溶液剂'),('9681003','9681001','1232321','2019-02-28 00:00:00','片剂/丸剂/外用溶液剂/全部剂型');

/*Table structure for table `scm_zsj_commercemerge` */

DROP TABLE IF EXISTS `scm_zsj_commercemerge`;

CREATE TABLE `scm_zsj_commercemerge` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `hostCommerceId` varchar(64) DEFAULT NULL COMMENT '主商业ID',
  `hostCommerceCode` varchar(255) DEFAULT NULL COMMENT '主商业编码',
  `hostCommerceName` varchar(255) DEFAULT NULL COMMENT '主商业名称',
  `viceCommerceId` varchar(64) DEFAULT NULL COMMENT '副商业ID',
  `viceCommerceCode` varchar(255) DEFAULT NULL COMMENT '副商业编码',
  `viceCommerceName` varchar(255) DEFAULT NULL COMMENT '商业名称',
  `approvalMemo` varchar(600) DEFAULT NULL COMMENT '申请原因',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '审批状态',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `operatorName` varchar(255) DEFAULT NULL COMMENT '操作人',
  `operatorId` varchar(64) DEFAULT NULL COMMENT '操作人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业合并表';

/*Data for the table `scm_zsj_commercemerge` */

/*Table structure for table `scm_zsj_commercepromisebook` */

DROP TABLE IF EXISTS `scm_zsj_commercepromisebook`;

CREATE TABLE `scm_zsj_commercepromisebook` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `PromiseBookId` varchar(64) DEFAULT NULL COMMENT '承诺书ID',
  `isExist` varchar(1) DEFAULT NULL COMMENT '是否存在',
  `validity` datetime DEFAULT NULL COMMENT '有效期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业承诺书';

/*Data for the table `scm_zsj_commercepromisebook` */

insert  into `scm_zsj_commercepromisebook`(`id`,`commerceFirstId`,`PromiseBookId`,`isExist`,`validity`) values ('9621012','9621001','6711003','1','2019-02-28 00:00:00'),('9621043','9621038','6711003','1','2019-02-28 00:00:00'),('9621044','9621038','6481001','1','2019-02-28 00:00:00'),('9681006','9681001','6711003','1','2019-02-28 00:00:00'),('9681007','9681001','6481001','1','2019-02-28 00:00:00');

/*Table structure for table `scm_zsj_commerceshipaddress` */

DROP TABLE IF EXISTS `scm_zsj_commerceshipaddress`;

CREATE TABLE `scm_zsj_commerceshipaddress` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `isDufault` varchar(1) DEFAULT NULL COMMENT '是否默认(0:否,1:是)',
  `contactPersion` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `headPhone` varchar(11) DEFAULT NULL COMMENT '手机',
  `province` varchar(64) DEFAULT NULL COMMENT '省',
  `city` varchar(64) DEFAULT NULL COMMENT '市',
  `district` varchar(64) DEFAULT NULL COMMENT '区',
  `street` varchar(255) DEFAULT NULL COMMENT '街道',
  `postcode` varchar(6) DEFAULT NULL COMMENT '邮编',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业收货地址';

/*Data for the table `scm_zsj_commerceshipaddress` */

insert  into `scm_zsj_commerceshipaddress`(`id`,`commerceFirstId`,`isDufault`,`contactPersion`,`phone`,`headPhone`,`province`,`city`,`district`,`street`,`postcode`,`address`) values ('9621008','9621001','0','测试','测试','测试','测试','测试','测试','测试','测试','测试'),('9621045','9621038','0','测试','测试','测试','测试','测试','测试','测试','测试','测试'),('9681008','9681001','0','测试','测试','测试','测试','测试','测试','测试','测试','测试');

/*Table structure for table `scm_zsj_commercewarehouse` */

DROP TABLE IF EXISTS `scm_zsj_commercewarehouse`;

CREATE TABLE `scm_zsj_commercewarehouse` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `address` varchar(600) DEFAULT NULL COMMENT '地址',
  `isDefault` varchar(1) DEFAULT NULL COMMENT '是否默认',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业生产/仓库地址';

/*Data for the table `scm_zsj_commercewarehouse` */

insert  into `scm_zsj_commercewarehouse`(`id`,`commerceFirstId`,`address`,`isDefault`) values ('9621002','9621001','测试','0'),('9621039','9621038','测试','0'),('9681002','9681001','测试','0');

/*Table structure for table `scm_zsj_gmp` */

DROP TABLE IF EXISTS `scm_zsj_gmp`;

CREATE TABLE `scm_zsj_gmp` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `supplierFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `GMPCertificate` varchar(255) DEFAULT NULL COMMENT 'GMP证书',
  `validity` datetime DEFAULT NULL COMMENT 'GMP证书有效期',
  `certificationScope` varchar(255) DEFAULT NULL COMMENT 'GMP证书认证范围',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商GMP证书信息';

/*Data for the table `scm_zsj_gmp` */

insert  into `scm_zsj_gmp`(`id`,`supplierFirstId`,`GMPCertificate`,`validity`,`certificationScope`) values ('9621017','9621013','测试','2019-02-28 00:00:00','测试'),('9721009','9721005','测试','2019-02-22 00:00:00','测试');

/*Table structure for table `scm_zsj_gsp` */

DROP TABLE IF EXISTS `scm_zsj_gsp`;

CREATE TABLE `scm_zsj_gsp` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `GSP` varchar(255) DEFAULT NULL COMMENT 'GSP证书',
  `validity` datetime DEFAULT NULL COMMENT 'GSP证书有效期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业GSP证书信息';

/*Data for the table `scm_zsj_gsp` */

insert  into `scm_zsj_gsp`(`id`,`commerceFirstId`,`GSP`,`validity`) values ('9621005','9621001','测试','2019-02-28 00:00:00'),('9621042','9621038','测试','2019-02-28 00:00:00'),('9681005','9681001','测试','2019-02-28 00:00:00');

/*Table structure for table `scm_zsj_product` */

DROP TABLE IF EXISTS `scm_zsj_product`;

CREATE TABLE `scm_zsj_product` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `productName` varchar(255) DEFAULT NULL COMMENT '产品名',
  `ProductLineId` varchar(64) DEFAULT NULL COMMENT '产品系列ID',
  `ProductLineName` varchar(255) DEFAULT NULL COMMENT '产品系列',
  `productCode` varchar(255) DEFAULT NULL COMMENT '物料编码',
  `commonName` varchar(255) DEFAULT NULL COMMENT '通用名称',
  `productState` varchar(255) DEFAULT NULL COMMENT '规格',
  `AgentTypeId` varchar(64) DEFAULT NULL COMMENT '剂型ID',
  `AgentType` varchar(255) DEFAULT NULL COMMENT '剂型',
  `unit` varchar(255) DEFAULT NULL COMMENT '单位',
  `Achange` varchar(10) DEFAULT NULL COMMENT '件装量',
  `OTC` varchar(255) DEFAULT NULL COMMENT 'OTC',
  `traceCode` varchar(255) DEFAULT NULL COMMENT '追溯码',
  `MedicineTypeId` varchar(64) DEFAULT NULL COMMENT '药品类型ID',
  `MedicineType` varchar(255) DEFAULT NULL COMMENT '药品类型',
  `medicineClassifyId` varchar(64) DEFAULT NULL COMMENT '药品分类ID',
  `medicineClassify` varchar(255) DEFAULT NULL COMMENT '药品分类',
  `KeepingCondition` varchar(255) DEFAULT NULL COMMENT '贮存条件',
  `suggestWarehouse` varchar(255) DEFAULT NULL COMMENT '建议库区',
  `SupplierId` varchar(64) DEFAULT NULL COMMENT '供应商Id',
  `supplierName` varchar(255) DEFAULT NULL COMMENT '供应商名称',
  `manufacturer` varchar(255) DEFAULT NULL COMMENT '生产厂商',
  `InventoryCode` varchar(255) DEFAULT NULL COMMENT '存货代码',
  `operatorName` varchar(255) DEFAULT NULL COMMENT '录入人',
  `operatorDate` datetime DEFAULT NULL COMMENT '录入时间',
  `productNum` varchar(255) DEFAULT NULL COMMENT '产品编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

/*Data for the table `scm_zsj_product` */

insert  into `scm_zsj_product`(`id`,`productName`,`ProductLineId`,`ProductLineName`,`productCode`,`commonName`,`productState`,`AgentTypeId`,`AgentType`,`unit`,`Achange`,`OTC`,`traceCode`,`MedicineTypeId`,`MedicineType`,`medicineClassifyId`,`medicineClassify`,`KeepingCondition`,`suggestWarehouse`,`SupplierId`,`supplierName`,`manufacturer`,`InventoryCode`,`operatorName`,`operatorDate`,`productNum`) values ('6651015','奥泰灵0.75g(10粒/盒)','6251027','奥泰灵-系列','BB-93A','盐酸氨基葡萄糖胶囊','0.75g*10粒/盒','6001008','胶囊剂','盒','600','无','0','6001042','普通药品','6251003','','10℃~30℃','阴凉库,25度常温库','','','澳美制药厂','10101080101','原浩',NULL,'100'),('6651016','奥泰灵0.75g(60粒/盒)','6251027','奥泰灵-系列','BB-93D','盐酸氨基葡萄糖胶囊','0.75g*60粒/盒','6001008','胶囊剂','盒','150','甲类','0','6001042','普通药品','6251003','','10℃~30℃','阴凉库,25度常温库','','','澳美制药厂','80106','原浩',NULL,'105'),('6651017','奥泰灵0.75g(20粒/盒)','6251027','奥泰灵-系列','BB-93B','盐酸氨基葡萄糖胶囊','0.75g*20粒/盒','6001008','胶囊剂','盒','400','甲类','0','6001042','普通药品','6251003','','10℃~30℃','阴凉库,25度常温库','','','澳美制药厂','1.01020101040801E+22','原浩',NULL,'101'),('6651018','澳米沙(30ml/瓶)','6061001','澳米沙-系列','BO-15','克林霉素磷酸酯外用溶液','1％*30ml/瓶','6001016','外用溶液剂','瓶','160','甲类','0','6001042','普通药品','6251003','','10℃~30℃','阴凉库,25度常温库','','','澳美制药厂','20601','原浩',NULL,'142'),('6651020','奥勃抒(10ml*30袋)','6251022','奥勃抒-系列','BA-44A','盐酸氨溴索口服溶液','10ml:30mg*30袋/盒','6001010','口服溶液剂','盒','40','甲类','0','6001042','普通药品','6251003','','10℃~30℃','25度常温库,30度常温库','','','澳美制药厂','10302','原浩',NULL,'74'),('6651021','奥勃抒(10ml*15袋)','6251022','奥勃抒-系列','BA-44C','盐酸氨溴索口服溶液','10ml:30mg*14袋/盒','6001010','口服溶液剂','盒','60','甲类','0','6001042','普通药品','6251026','疼痛','25℃以下干燥密封保存','无','','','澳美制药厂','1030310304','原浩',NULL,'80'),('6651022','奥青(10g/支)','6251021','奥青-系列','BT-10B','复方克霉唑乳膏（II）','10g/支','6001013','乳膏剂','管','400','乙类','0','6001042','普通药品','6251026','','常温，密闭，遮光保存','无','8041060','测试123','澳美制药厂','2030420305','原浩',NULL,'92'),('6651023','澳琪(5g/支)','6251034','澳琪-系列','BA-37','莫匹罗星软膏','2％*5g/支','6001014','软膏剂','支','400','甲类','0','6001042','普通药品','6251026','疼痛','25℃以下干燥密封保存','无','','','澳美制药厂','20501','原浩',NULL,'96'),('8061027','测试','6251041','宁之助-系列','测试','测试','测试','6251016','溶液','测试','80','乙类','0','6001043','特殊管理药品','6251026','疼痛','25℃以下干燥密封保存','无',NULL,NULL,'澳美制药厂-海南澳美华制药有限公司分包装','测试','原浩',NULL,'测试'),('8071051','测试321','6251041','宁之助-系列','测试321','测试321','测试321','6251016','溶液','测试321','800','甲类','0','6001043','特殊管理药品','6251026','疼痛','25℃以下干燥密封保存','无',NULL,NULL,'澳美制药厂-海南澳美华制药有限公司分包装','测试321','原浩',NULL,'测试321'),('9311108','测试010','6251041','宁之助-系列','CS010','CS010','CS010','6251007','胶囊','盒','800','甲类','0','6001042','普通药品','6251026','疼痛','10℃~30℃','25度常温库,30度常温库','8041060','测试123','澳美制药厂-海南澳美华制药有限公司分包装','CS010','原浩',NULL,'CS010');

/*Table structure for table `scm_zsj_productfirst` */

DROP TABLE IF EXISTS `scm_zsj_productfirst`;

CREATE TABLE `scm_zsj_productfirst` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `productId` varchar(64) DEFAULT NULL COMMENT '商品ID',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '审批状态',
  `supplierId` varchar(64) DEFAULT NULL COMMENT '供应商ID',
  `supplier` varchar(255) DEFAULT NULL COMMENT '供应商',
  `approvalCode` varchar(255) DEFAULT NULL COMMENT '批准文号/注册证号',
  `approvalValidity` datetime DEFAULT NULL COMMENT '批准文号/注册证号有效期',
  `businessScope` varchar(255) DEFAULT NULL COMMENT '经营范围',
  `gmp` varchar(255) DEFAULT NULL COMMENT 'GMP证书',
  `gmpValidity` datetime DEFAULT NULL COMMENT 'GMP证书有效期',
  `startBatch` varchar(255) DEFAULT NULL COMMENT '启用批号',
  `qualityStandard` varchar(255) DEFAULT NULL COMMENT '质量标准',
  `length` double(10,2) DEFAULT NULL COMMENT '长(厘米)',
  `wide` double(10,2) DEFAULT NULL COMMENT '宽(厘米)',
  `high` double(10,2) DEFAULT NULL COMMENT '高(厘米)',
  `volume` double(10,2) DEFAULT NULL COMMENT '体积(立方米)',
  `roughWeight` double(10,2) DEFAULT NULL COMMENT '毛重(KG)',
  `cartonBarcode` varchar(255) DEFAULT NULL COMMENT '外箱条码',
  `smallBarcode` varchar(255) DEFAULT NULL COMMENT '小盒条码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品首营记录表';

/*Data for the table `scm_zsj_productfirst` */

insert  into `scm_zsj_productfirst`(`id`,`productId`,`ownerId`,`approvalId`,`approvalState`,`supplierId`,`supplier`,`approvalCode`,`approvalValidity`,`businessScope`,`gmp`,`gmpValidity`,`startBatch`,`qualityStandard`,`length`,`wide`,`high`,`volume`,`roughWeight`,`cartonBarcode`,`smallBarcode`) values ('9621020','6651015','7111011','5004676','2','7281002','澳美制药（海南）有限公司','测试','2019-02-28 00:00:00','全部剂型','测试','2019-02-15 00:00:00','666','6666',44.00,44.00,44.00,44.00,4.00,'44','4444'),('9621021','6651016','7111011','5004584','2','7281002','澳美制药（海南）有限公司','测试','2019-02-28 00:00:00','口服溶液剂','测试','2019-02-28 00:00:00','777','666',66.00,66.00,66.00,66.00,66.00,'666','666'),('9621022','6651018','7111011',NULL,'0','7281002','澳美制药（海南）有限公司','6766','2019-02-28 00:00:00','软膏剂','测试','2019-02-21 00:00:00','555','555',55.00,55.00,55.00,55.00,55.00,'5555','55555'),('9691003','6651017','7111011','5005251','1','7281002','澳美制药（海南）有限公司','8080','2019-02-28 00:00:00','滴剂','231','2019-02-20 00:00:00','312312','12312',80.00,80.00,80.00,80.00,80.00,'80','80');

/*Table structure for table `scm_zsj_productline` */

DROP TABLE IF EXISTS `scm_zsj_productline`;

CREATE TABLE `scm_zsj_productline` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `ProductLineName` varchar(255) DEFAULT NULL COMMENT '产品系列名称',
  `ProductLineAcronym` varchar(255) DEFAULT NULL COMMENT '产品系列缩写',
  `ProductLineCode` varchar(255) DEFAULT NULL COMMENT '编码',
  `ProductLineCommon` varchar(255) DEFAULT NULL COMMENT '系列通用名称',
  `ProductLineEname` varchar(255) DEFAULT NULL COMMENT '系列英文名',
  `AgentTypeId` varchar(64) DEFAULT NULL COMMENT '剂型ID',
  `AgentType` varchar(255) DEFAULT NULL COMMENT '剂型',
  `MedicineTypeId` varchar(64) DEFAULT NULL COMMENT '类型ID',
  `MedicineType` varchar(255) DEFAULT NULL COMMENT '类型',
  `ProductLineState` varchar(64) DEFAULT NULL COMMENT '商品系列状态(0停用,1启用)',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `createPersion` varchar(255) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品系列';

/*Data for the table `scm_zsj_productline` */

insert  into `scm_zsj_productline`(`id`,`ProductLineName`,`ProductLineAcronym`,`ProductLineCode`,`ProductLineCommon`,`ProductLineEname`,`AgentTypeId`,`AgentType`,`MedicineTypeId`,`MedicineType`,`ProductLineState`,`createDate`,`createPersion`) values ('6061001','澳米沙-系列','AMS','1020','克林霉素磷酸酯','','6251007','胶囊','6251002','皮科','1','2019-01-15 17:50:47','超级管理员'),('6071004','澳达先-系列','ADX','1019','氨酚双氢可待因','','6001012','片剂','6021001','呼抗','1','2019-01-15 17:45:10','超级管理员'),('6251001','澳可修-系列','AKX','1018','硝酸异康唑乳膏','','6001013','乳膏剂','6251002','皮科','1','2019-01-15 17:44:24','超级管理员'),('6251005','奥亭片-系列','ATT','1021','愈创罂粟待因片','','6001012','片剂','6021001','呼抗','1','2019-01-15 17:49:41','超级管理员'),('6251006','澳特欣-系列','ATX','1022','头孢羟氨苄','','6001018','原料药','6021001','呼抗','1','2019-01-15 17:50:19','超级管理员'),('6251008','澳博灵-系列','ABL','1023','氨苄西林氯唑西林','','6251007','胶囊','6021001','呼抗','0','2019-02-16 09:50:37','超级管理员'),('6251009','奥来舒-系列','ALS','1024','盐酸特比萘酚','','6001013','乳膏剂','6251002','皮科','1','2019-01-15 17:51:54','超级管理员'),('6251010','奥先片-系列','AXT','1025','阿莫西林克拉维酸片','Amoxicillin and Clavulanate','6001012','片剂','6021001','呼抗','1','2019-01-15 17:52:25','超级管理员'),('6251011','奥松-系列','ASW','1026','肚疼泻丸','Red Bamboo Pills','6001017','丸剂','6021001','呼抗','1','2019-01-15 17:52:49','超级管理员'),('6251012','澳夫清-系列','AFQ','1027','卡泊三醇','Calcipotriol','6001014','软膏剂','6251002','皮科','1','2019-01-15 17:53:13','超级管理员'),('6251013','奥肯能-系列','AKN','1028','复方氨酚溴敏胶囊','Compound Paracetamol,Bromhexine Hydrochloride and Brompheniramine Maleate Capsules','6251007','胶囊','6021001','呼抗','1','2019-01-15 17:53:48','超级管理员'),('6251014','奥亭-系列','AT','1029','复方磷酸可待因','Compound Codeine Phosphate','6001010','口服溶液剂','6251002','皮科','1','2019-01-15 17:55:08','超级管理员'),('6251015','澳能-系列','AN','1030','卤米松','Halometasone','6001013','乳膏剂','6251002','皮科','1','2019-01-15 17:58:01','超级管理员'),('6251017','澳特斯-系列','ATS','1031','复方福尔可定','Compound Pholcodine','6001010','口服溶液剂','6001044','儿科','1','2019-01-15 18:24:35','超级管理员'),('6251018','奥络-系列','AL','1032','夫西地酸','Fusidic Acid','6001007','干混悬剂','6251002','皮科','1','2019-01-15 18:24:25','超级管理员'),('6251019','澳广-系列','AG','1033','阿莫西林双氯西林','Amoxicillin and Dicloxocillin','6251007','胶囊','6021001','呼抗','1','2019-01-15 18:01:17','超级管理员'),('6251020','奥先干混-系列','AXS','1034','阿莫西林克拉维酸钾干混悬剂(7:1)','Amoxicillin and Clavulanate','','','6001044','儿科','1','2019-01-15 18:07:38','超级管理员'),('6251021','奥青-系列','AQ','1035','克霉唑','Clotrimazole','6001013','乳膏剂','6251002','皮科','1','2019-01-15 18:08:09','超级管理员'),('6251022','奥勃抒-系列','ABS','1036','氨溴索','Ambroxol','','','6021001','呼抗','1','2019-01-15 18:08:33','超级管理员'),('6251023','澳博达-系列','ABD','1037','西替利嗪','Cetirizine','6001005','滴剂','6001044','儿科','1','2019-01-15 18:24:06','超级管理员'),('6251024','澳美华-感冒灵','AMH-GML','1038','复方感冒灵颗粒(胶囊、片)','','6001012','片剂','6251025','AMH','1','2019-01-15 18:23:45','超级管理员'),('6251027','奥泰灵-系列','ATL','1039','氨基葡萄糖','Glucosamine','6251007','胶囊','6251026','疼痛','1','2019-01-15 18:11:16','超级管理员'),('6251028','澳托芬-系列','ATF','1040','依托芬那脂凝胶','Etofenamate Gel','6001014','软膏剂','6251026','疼痛','1','2019-01-15 18:11:44','超级管理员'),('6251029','奥络(袋)-系列','ALs','1041','夫西地酸干混悬剂','Fusidic Acid Dry Suspension','6001007','干混悬剂','6251003','无','1','2019-01-15 18:23:32','超级管理员'),('6251030','奥贝爽-系列','ABSC','1042','丙酸氟替卡松乳膏','','6001013','乳膏剂','6251002','皮科','1','2019-01-15 18:12:44','超级管理员'),('6251031','澳美华-怡方','AMH-YF','1043','缬沙坦胶囊','','6251007','胶囊','6251025','AMH','1','2019-01-15 18:13:20','超级管理员'),('6251032','澳美华-则立','AMH-ZL','1044','美洛昔康片','','6001012','片剂','6251025','AMH','1','2019-01-15 18:13:41','超级管理员'),('6251033','澳石林-系列','ASL','1045','氨酚右敏口服溶液','Paracetamol and Dexchlorpheniramine Maleate Oral Solution','','','6001044','儿科','1','2019-01-15 18:14:09','超级管理员'),('6251034','澳琪-系列','AQI','1046','莫匹罗星软膏','Mupirocin Ointment','','','6251002','皮科','1','2019-01-15 18:14:49','超级管理员'),('6251035','阿莫灵-系列','AML','1047','阿莫西林干混悬剂','Amoxicillin Dry Suspension','6001007','干混悬剂','6251003','无','1','2019-01-15 18:23:08','超级管理员'),('6251036','澳锐-系列','AR','1048','无','','6251004','无','6251003','无','1','2019-01-15 18:22:49','超级管理员'),('6251037','澳美华-澳抒欣','AMH-ASX','1049','环酯红霉素胶囊','','6251007','胶囊','6251025','AMH','1','2019-01-15 18:16:15','超级管理员'),('6251038','尿通-系列','NT','1050','无','','6251004','无','6251026','疼痛','1','2019-01-15 18:16:41','超级管理员'),('6251039','澳美华-澳抒达','AMH-ASD','1051','环酯红霉素干混悬剂','','6001007','干混悬剂','6251025','AMH','1','2019-01-29 19:25:37','超级管理员'),('6251040','澳美华-双山胶囊','AMH-SS','1052','双山胶囊','','6251007','胶囊','6251025','AMH','1','2019-01-29 19:25:37','超级管理员'),('6251041','宁之助-系列','NZZ','1053','曲安耐德口腔软膏','','6001014','软膏剂','6251003','无','1','2019-02-13 15:07:36','原浩');

/*Table structure for table `scm_zsj_supplier` */

DROP TABLE IF EXISTS `scm_zsj_supplier`;

CREATE TABLE `scm_zsj_supplier` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `supplierName` varchar(255) DEFAULT NULL COMMENT '供应商名称',
  `fileCode` varchar(255) DEFAULT NULL COMMENT '档案编号',
  `supplierCode` varchar(255) DEFAULT NULL COMMENT '供应商编号',
  `conpanyPersion` varchar(255) DEFAULT NULL COMMENT '企业负责人',
  `contactPersion` varchar(255) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系方式',
  `openBank` varchar(255) DEFAULT NULL COMMENT '开户行',
  `bankCode` varchar(64) DEFAULT NULL COMMENT '银行账号',
  `dutyParagraph` varchar(255) DEFAULT NULL COMMENT '税号',
  `qualityValidity` datetime DEFAULT NULL COMMENT '药品质量保证协议有效期',
  `registerAddress` varchar(255) DEFAULT NULL COMMENT '注册地址',
  `sealStyle` varchar(1) DEFAULT NULL COMMENT '印章样式',
  `billsStyle` varchar(1) DEFAULT NULL COMMENT '随货单据样式',
  `enterPersion` varchar(255) DEFAULT NULL COMMENT '录入人',
  `enterDate` datetime DEFAULT NULL COMMENT '录入日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商表';

/*Data for the table `scm_zsj_supplier` */

insert  into `scm_zsj_supplier`(`id`,`supplierName`,`fileCode`,`supplierCode`,`conpanyPersion`,`contactPersion`,`phone`,`openBank`,`bankCode`,`dutyParagraph`,`qualityValidity`,`registerAddress`,`sealStyle`,`billsStyle`,`enterPersion`,`enterDate`) values ('7281002','澳美制药（海南）有限公司','GYS1901251037435316','67875','马海峰','马海峰','17737317099','中国银行','17737317099','17737317099','2019-10-31 00:00:00','海口国家高新技术产业开发区药谷工业园药谷一路2号','0','0','原浩',NULL),('7281003','澳美制药厂','GYS1901251037435317','867','无','无','17737317099','中国银行','17737317099','17737317099','2020-10-08 00:00:00','香港新界元朗工业村宏富路8号','0','0','原浩',NULL),('8041043','测试','GYS1901301258793506','测试','测试','测试','1889632563','测试','8498416541964','5465165','2019-01-31 00:00:00','测试','0','0','原浩',NULL),('8041060','测试123','GYS1901301007909862','测试123','测试123','测试123','测试123','测试123','测试123','测试123','2019-01-31 00:00:00','测试123','0','0','原浩',NULL),('8041093','测试122','GYS1901301908002187','测试122','测试122','测试122','测试122','测试122','测试122','测试122','2019-01-31 00:00:00','测试122','0','0','原浩',NULL),('8061003','测试312','GYS1901300752467232','测试312','测试312','测试312','测试312','测试312','测试312','测试312','2019-01-31 00:00:00','测试312','0','0','原浩',NULL),('8071004','lzy测试供应商1','GYS1901301257344180','lzy测试供应商1','司马懿','司马懿','1390000000','中国银行','622220837377373778','868686586588','2020-02-29 00:00:00','深圳市','0','0','原浩',NULL),('8071043','测试321','GYS1901311761229496','测试321','测试321','测试321','17731370955','测试321','测试321','测试321','2019-01-31 00:00:00','测试321','0','0','原浩',NULL),('8071060','lzy2','GYS1901312130161551','lzy2','曹操','曹操','13900000000','中国银行','343535353534325','343535235325325','2020-01-31 00:00:00','深圳市','0','0','原浩',NULL),('9311068','测试001','GYS1902252037659103','cs001','原浩','原浩','17737317099','中国银行','5165465489465','4651653156416','2019-02-28 00:00:00','山西长治','1','1','原浩',NULL);

/*Table structure for table `scm_zsj_supplieraccredit` */

DROP TABLE IF EXISTS `scm_zsj_supplieraccredit`;

CREATE TABLE `scm_zsj_supplieraccredit` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `entrustId` varchar(64) DEFAULT NULL COMMENT '委托书ID',
  `productCode` varchar(255) DEFAULT NULL COMMENT '产品编号',
  `productName` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `commonName` varchar(255) DEFAULT NULL COMMENT '通用名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='授权销售品种';

/*Data for the table `scm_zsj_supplieraccredit` */

/*Table structure for table `scm_zsj_supplierbusiness` */

DROP TABLE IF EXISTS `scm_zsj_supplierbusiness`;

CREATE TABLE `scm_zsj_supplierbusiness` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `supplierFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `registerCode` varchar(255) DEFAULT NULL COMMENT '注册号',
  `licenseValidity` datetime DEFAULT NULL COMMENT '营业执照有效期',
  `legalPersion` varchar(255) DEFAULT NULL COMMENT '法定代表人',
  `registerPrice` varchar(20) DEFAULT NULL COMMENT '注册资本(万元)',
  `commerceType` varchar(255) DEFAULT NULL COMMENT '企业类型',
  `yearReportValidity` datetime DEFAULT NULL COMMENT '年度报表有效期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商营业执照信息';

/*Data for the table `scm_zsj_supplierbusiness` */

insert  into `scm_zsj_supplierbusiness`(`id`,`supplierFirstId`,`registerCode`,`licenseValidity`,`legalPersion`,`registerPrice`,`commerceType`,`yearReportValidity`) values ('9621016','9621013','测试','2019-02-28 00:00:00','测试','688','测试','2019-02-28 00:00:00'),('9721008','9721005','123123123','2019-02-28 00:00:00','123123','33333','测试','2019-02-28 00:00:00');

/*Table structure for table `scm_zsj_supplierentrusebook` */

DROP TABLE IF EXISTS `scm_zsj_supplierentrusebook`;

CREATE TABLE `scm_zsj_supplierentrusebook` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `supplierFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `entrustCode` varchar(255) DEFAULT NULL COMMENT '委托书编号',
  `entrustName` varchar(255) DEFAULT NULL COMMENT '委托书姓名',
  `entrustValidity` datetime DEFAULT NULL COMMENT '委托书有效期至',
  `IDNumber` varchar(255) DEFAULT NULL COMMENT '身份证号',
  `IDValidity` datetime DEFAULT NULL COMMENT '身份证有效期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商委托书';

/*Data for the table `scm_zsj_supplierentrusebook` */

insert  into `scm_zsj_supplierentrusebook`(`id`,`supplierFirstId`,`entrustCode`,`entrustName`,`entrustValidity`,`IDNumber`,`IDValidity`) values ('9621018','9621013','测试','测试','2019-02-28 00:00:00','1231231231231231231','2019-02-28 00:00:00'),('9721010','9721005','测试','测试','2019-02-19 00:00:00','测试','2019-02-21 00:00:00');

/*Table structure for table `scm_zsj_supplierfirst` */

DROP TABLE IF EXISTS `scm_zsj_supplierfirst`;

CREATE TABLE `scm_zsj_supplierfirst` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `supplierId` varchar(64) DEFAULT NULL COMMENT '供应商ID',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  `approvalId` varchar(64) DEFAULT NULL COMMENT '审批ID',
  `approvalState` varchar(1) DEFAULT NULL COMMENT '审批状态',
  `operatorName` varchar(255) DEFAULT NULL COMMENT '申请人',
  `operatorDate` datetime DEFAULT NULL COMMENT '申请时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商首营记录表';

/*Data for the table `scm_zsj_supplierfirst` */

insert  into `scm_zsj_supplierfirst`(`id`,`supplierId`,`ownerId`,`approvalId`,`approvalState`,`operatorName`,`operatorDate`) values ('9621013','7281002','7111011','5006023','2','原浩','2019-02-28 17:31:25'),('9721005','8041093','7111011','5006004','2','原浩','2019-02-28 17:31:44');

/*Table structure for table `scm_zsj_supplierlicence` */

DROP TABLE IF EXISTS `scm_zsj_supplierlicence`;

CREATE TABLE `scm_zsj_supplierlicence` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `supplierFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `licenseKey` varchar(255) DEFAULT NULL COMMENT '许可证号',
  `validity` datetime DEFAULT NULL COMMENT '有效期',
  `managementScope` varchar(255) DEFAULT NULL COMMENT '生产/经营范围',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商许可证信息';

/*Data for the table `scm_zsj_supplierlicence` */

insert  into `scm_zsj_supplierlicence`(`id`,`supplierFirstId`,`licenseKey`,`validity`,`managementScope`) values ('9621015','9621013','测试','2019-02-28 00:00:00','外用溶液剂/丸剂/原料药/无'),('9721007','9721005','123123','2019-02-28 00:00:00','无/丸剂/外用溶液剂');

/*Table structure for table `scm_zsj_supplierpromisebook` */

DROP TABLE IF EXISTS `scm_zsj_supplierpromisebook`;

CREATE TABLE `scm_zsj_supplierpromisebook` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `supplierFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `PromiseBookId` varchar(64) DEFAULT NULL COMMENT '承诺书ID',
  `isExist` varchar(1) DEFAULT NULL COMMENT '是否存在',
  `validity` datetime DEFAULT NULL COMMENT '有效期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商承诺书';

/*Data for the table `scm_zsj_supplierpromisebook` */

/*Table structure for table `scm_zsj_suppliershipaddress` */

DROP TABLE IF EXISTS `scm_zsj_suppliershipaddress`;

CREATE TABLE `scm_zsj_suppliershipaddress` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `supplierFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `isDufault` varchar(1) DEFAULT NULL COMMENT '是否默认(0:否,1:是)',
  `contactPersion` varchar(64) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `headPhone` varchar(11) DEFAULT NULL COMMENT '手机',
  `province` varchar(64) DEFAULT NULL COMMENT '省',
  `city` varchar(64) DEFAULT NULL COMMENT '市',
  `district` varchar(64) DEFAULT NULL COMMENT '区',
  `street` varchar(255) DEFAULT NULL COMMENT '街道',
  `postcode` varchar(6) DEFAULT NULL COMMENT '邮编',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址';

/*Data for the table `scm_zsj_suppliershipaddress` */

insert  into `scm_zsj_suppliershipaddress`(`id`,`supplierFirstId`,`isDufault`,`contactPersion`,`phone`,`headPhone`,`province`,`city`,`district`,`street`,`postcode`,`address`) values ('9621019','9621013','0','测试','测试','测试','测试','测试','测试','测试','测试','测试'),('9721011','9721005','0','测试','测试','测试','测试','测试','测试','测试','测试','测试');

/*Table structure for table `scm_zsj_supplierwarehouse` */

DROP TABLE IF EXISTS `scm_zsj_supplierwarehouse`;

CREATE TABLE `scm_zsj_supplierwarehouse` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `supplierFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `isDefault` varchar(1) DEFAULT NULL COMMENT '是否默认',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商生产/仓库地址';

/*Data for the table `scm_zsj_supplierwarehouse` */

insert  into `scm_zsj_supplierwarehouse`(`id`,`supplierFirstId`,`address`,`isDefault`) values ('9621014','9621013','测试','0'),('9821005','9721005','测试','0');

/*Table structure for table `scm_zsj_treatlicence` */

DROP TABLE IF EXISTS `scm_zsj_treatlicence`;

CREATE TABLE `scm_zsj_treatlicence` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `commerceFirstId` varchar(64) DEFAULT NULL COMMENT '首营ID',
  `TreatLicence` varchar(255) DEFAULT NULL COMMENT '医疗许可证',
  `validity` datetime DEFAULT NULL COMMENT '医疗许可证有效期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商业医疗许可证';

/*Data for the table `scm_zsj_treatlicence` */

/*Table structure for table `scm_zsj_txcompany` */

DROP TABLE IF EXISTS `scm_zsj_txcompany`;

CREATE TABLE `scm_zsj_txcompany` (
  `name` varchar(255) DEFAULT NULL COMMENT '单位名',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `openBank` varchar(255) DEFAULT NULL COMMENT '开户行',
  `bankCode` varchar(30) DEFAULT NULL COMMENT '账号',
  `dutyParagraph` varchar(255) DEFAULT NULL COMMENT '税号',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `postcode` varchar(6) DEFAULT NULL COMMENT '邮政编码',
  `id` varchar(64) NOT NULL COMMENT 'id',
  `ownerId` varchar(64) DEFAULT NULL COMMENT '货主ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='天祥信息';

/*Data for the table `scm_zsj_txcompany` */

insert  into `scm_zsj_txcompany`(`name`,`address`,`openBank`,`bankCode`,`dutyParagraph`,`phone`,`postcode`,`id`,`ownerId`) values ('海南天祥药业有限公司','洋浦经济开发区远洋路西段北侧物业商场三楼301室','中国建设银行股份有限公司海口海港支行','46001004036059111999','460040713849063','0898-68581551','000000','6941001','7111011');

/*Table structure for table `xb_db_id` */

DROP TABLE IF EXISTS `xb_db_id`;

CREATE TABLE `xb_db_id` (
  `ID_` bigint(20) NOT NULL COMMENT '前缀',
  `START_` int(11) NOT NULL COMMENT '开始ID值',
  `MAX_` int(11) NOT NULL COMMENT '当前允许最大的ID值',
  `MAC_NAME_` varchar(255) NOT NULL COMMENT '机器名称',
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='主键生成表';

/*Data for the table `xb_db_id` */

insert  into `xb_db_id`(`ID_`,`START_`,`MAX_`,`MAC_NAME_`) values (1,10420001,10430001,'eden');

/*Table structure for table `v_addagreementlist` */

DROP TABLE IF EXISTS `v_addagreementlist`;

/*!50001 DROP VIEW IF EXISTS `v_addagreementlist` */;
/*!50001 DROP TABLE IF EXISTS `v_addagreementlist` */;

/*!50001 CREATE TABLE  `v_addagreementlist`(
 `commerceId` varchar(64) ,
 `commerceFirstId` varchar(64) ,
 `commerceName` varchar(255) ,
 `fileCode` varchar(255) ,
 `commerceState` varchar(1) ,
 `commerceCode` varchar(255) ,
 `conpanyPersion` varchar(255) ,
 `contactPersion` varchar(255) ,
 `phone` varchar(11) ,
 `openBank` varchar(255) ,
 `bankCode` varchar(30) ,
 `dutyParagraph` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `businessDivision` varchar(255) ,
 `provinceId` varchar(64) ,
 `province` varchar(255) ,
 `qualityValidity` datetime ,
 `registerAddress` varchar(255) ,
 `sealStyle` varchar(1) ,
 `billsStyle` varchar(1) ,
 `enterPersion` varchar(255) ,
 `enterDate` datetime ,
 `commerceLevel` varchar(255) ,
 `functionType` varchar(255) ,
 `acceptState` varchar(3) ,
 `shipments` int(1) ,
 `allocating` int(1) ,
 `Sales` int(1) ,
 `lastYear` varchar(1) ,
 `firstId` varchar(64) ,
 `ownerId` varchar(64) 
)*/;

/*Table structure for table `v_agreementdetails` */

DROP TABLE IF EXISTS `v_agreementdetails`;

/*!50001 DROP VIEW IF EXISTS `v_agreementdetails` */;
/*!50001 DROP TABLE IF EXISTS `v_agreementdetails` */;

/*!50001 CREATE TABLE  `v_agreementdetails`(
 `id` varchar(64) ,
 `commerceFirstId` varchar(64) ,
 `year` varchar(4) ,
 `commerceCode` varchar(255) ,
 `commerceClassify` varchar(255) ,
 `commerceLevel` varchar(255) ,
 `memo` varchar(600) ,
 `isSign` varchar(1) ,
 `masterId` varchar(64) ,
 `commerceName` varchar(255) ,
 `type` varchar(1) ,
 `lastYear` varchar(1) ,
 `shipments` varchar(64) ,
 `allocating` varchar(64) ,
 `sales` varchar(64) ,
 `acceptState` varchar(3) 
)*/;

/*Table structure for table `v_agreementlist` */

DROP TABLE IF EXISTS `v_agreementlist`;

/*!50001 DROP VIEW IF EXISTS `v_agreementlist` */;
/*!50001 DROP TABLE IF EXISTS `v_agreementlist` */;

/*!50001 CREATE TABLE  `v_agreementlist`(
 `commerceFirstId` varchar(64) ,
 `commerceName` varchar(255) ,
 `fileCode` varchar(255) ,
 `commerceState` varchar(1) ,
 `commerceCode` varchar(255) ,
 `conpanyPersion` varchar(255) ,
 `contactPersion` varchar(255) ,
 `phone` varchar(11) ,
 `openBank` varchar(255) ,
 `bankCode` varchar(30) ,
 `dutyParagraph` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `businessDivision` varchar(255) ,
 `provinceId` varchar(64) ,
 `province` varchar(255) ,
 `qualityValidity` datetime ,
 `registerAddress` varchar(255) ,
 `sealStyle` varchar(1) ,
 `billsStyle` varchar(1) ,
 `enterPersion` varchar(255) ,
 `enterDate` datetime ,
 `commerceLevel` varchar(255) ,
 `functionType` varchar(255) ,
 `ownerId` varchar(64) ,
 `acceptState` varchar(3) ,
 `lastYear` varchar(1) ,
 `issign` varchar(1) ,
 `shipments` varchar(64) ,
 `allocating` varchar(64) ,
 `sales` varchar(64) ,
 `id` varchar(64) ,
 `masterId` varchar(64) ,
 `commerceClassify` varchar(255) ,
 `memo` varchar(600) 
)*/;

/*Table structure for table `v_agreementlistmaster` */

DROP TABLE IF EXISTS `v_agreementlistmaster`;

/*!50001 DROP VIEW IF EXISTS `v_agreementlistmaster` */;
/*!50001 DROP TABLE IF EXISTS `v_agreementlistmaster` */;

/*!50001 CREATE TABLE  `v_agreementlistmaster`(
 `approvalState` varchar(1) ,
 `businessDivision` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `ownerId` varchar(64) ,
 `approvalId` varchar(64) ,
 `MasterCode` varchar(255) ,
 `isEffect` varchar(1) ,
 `id` varchar(64) ,
 `isSign` varchar(1) ,
 `commerceFirstId` varchar(64) ,
 `year` varchar(4) ,
 `commerceCode` varchar(255) ,
 `commerceName` varchar(255) ,
 `masterId` varchar(64) ,
 `type` varchar(1) ,
 `lastYear` varchar(1) ,
 `shipments` varchar(64) ,
 `allocating` varchar(64) ,
 `sales` varchar(64) ,
 `memo` varchar(600) ,
 `acceptState` varchar(3) 
)*/;

/*Table structure for table `v_agreementsendapplysuccess` */

DROP TABLE IF EXISTS `v_agreementsendapplysuccess`;

/*!50001 DROP VIEW IF EXISTS `v_agreementsendapplysuccess` */;
/*!50001 DROP TABLE IF EXISTS `v_agreementsendapplysuccess` */;

/*!50001 CREATE TABLE  `v_agreementsendapplysuccess`(
 `id` varchar(64) ,
 `functionType` varchar(255) ,
 `approvalStateStr` varchar(4) ,
 `acceptStateStr` varchar(3) ,
 `acceptState` varchar(2) ,
 `commerceCode` varchar(255) ,
 `fileCode` varchar(255) ,
 `commerceStateStr` varchar(6) ,
 `commerceState` varchar(2) ,
 `commerceName` varchar(255) ,
 `businessDivision` varchar(255) ,
 `province` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `provinceId` varchar(64) ,
 `legalPersion` varchar(255) ,
 `licenseKey` varchar(255) ,
 `validity` datetime ,
 `register` varchar(255) ,
 `licenseValidity` datetime ,
 `conpanyPersion` varchar(255) ,
 `registerPrice` varchar(20) ,
 `managementScope` varchar(255) ,
 `enterPersion` varchar(255) ,
 `enterDate` datetime ,
 `operatorName` varchar(255) ,
 `operatorDate` datetime ,
 `commerceFirstId` varchar(64) ,
 `approvalState` varchar(1) ,
 `ownerId` varchar(64) ,
 `type` varchar(1) ,
 `actualLevel` varchar(255) ,
 `isEffect` varchar(1) 
)*/;

/*Table structure for table `v_bigcontractgetydetails` */

DROP TABLE IF EXISTS `v_bigcontractgetydetails`;

/*!50001 DROP VIEW IF EXISTS `v_bigcontractgetydetails` */;
/*!50001 DROP TABLE IF EXISTS `v_bigcontractgetydetails` */;

/*!50001 CREATE TABLE  `v_bigcontractgetydetails`(
 `AgreementSummaryId` varchar(64) ,
 `DealerClauseId` varchar(64) ,
 `dealer` varchar(255) ,
 `payType` varchar(3) ,
 `YPhone` varchar(11) ,
 `YAddress` varchar(255) ,
 `YOpenBank` varchar(255) ,
 `YBankCode` varchar(30) ,
 `YDutyCode` varchar(255) ,
 `commerceFirstId` varchar(64) ,
 `consignee` varchar(255) ,
 `phone` varchar(20) ,
 `YPostcode` varchar(6) ,
 `shipAddress` text 
)*/;

/*Table structure for table `v_bigscmxsbigcontractsendapply` */

DROP TABLE IF EXISTS `v_bigscmxsbigcontractsendapply`;

/*!50001 DROP VIEW IF EXISTS `v_bigscmxsbigcontractsendapply` */;
/*!50001 DROP TABLE IF EXISTS `v_bigscmxsbigcontractsendapply` */;

/*!50001 CREATE TABLE  `v_bigscmxsbigcontractsendapply`(
 `productId` varchar(64) ,
 `productName` varchar(255) ,
 `MedicineType` varchar(255) ,
 `commonName` varchar(255) ,
 `productState` varchar(255) ,
 `unit` varchar(255) ,
 `agreementproductdetailId` varchar(64) ,
 `agreementSummaryId` varchar(64) ,
 `productGauge` varchar(255) ,
 `productCode` varchar(255) ,
 `unitPrice` double(10,2) ,
 `suppyType` varchar(4) 
)*/;

/*Table structure for table `v_commerceacceptapproval` */

DROP TABLE IF EXISTS `v_commerceacceptapproval`;

/*!50001 DROP VIEW IF EXISTS `v_commerceacceptapproval` */;
/*!50001 DROP TABLE IF EXISTS `v_commerceacceptapproval` */;

/*!50001 CREATE TABLE  `v_commerceacceptapproval`(
 `id` varchar(64) ,
 `commerceName` varchar(255) ,
 `fileCode` varchar(255) ,
 `commerceState` varchar(1) ,
 `commerceCode` varchar(255) ,
 `conpanyPersion` varchar(255) ,
 `contactPersion` varchar(255) ,
 `phone` varchar(11) ,
 `openBank` varchar(255) ,
 `bankCode` varchar(30) ,
 `dutyParagraph` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `businessDivision` varchar(255) ,
 `provinceId` varchar(64) ,
 `province` varchar(255) ,
 `qualityValidity` datetime ,
 `registerAddress` varchar(255) ,
 `sealStyle` varchar(1) ,
 `billsStyle` varchar(1) ,
 `enterPersion` varchar(255) ,
 `enterDate` datetime ,
 `commerceLevel` varchar(255) ,
 `functionType` varchar(255) ,
 `acceptStateId` varchar(64) ,
 `acceptState` varchar(1) ,
 `effectiveDate` datetime ,
 `memo` varchar(600) ,
 `acceptId` varchar(64) ,
 `approvalId` varchar(64) ,
 `approvalState` varchar(1) ,
 `ownerId` varchar(64) ,
 `operatorName` varchar(255) 
)*/;

/*Table structure for table `v_commerceacceptstate` */

DROP TABLE IF EXISTS `v_commerceacceptstate`;

/*!50001 DROP VIEW IF EXISTS `v_commerceacceptstate` */;
/*!50001 DROP TABLE IF EXISTS `v_commerceacceptstate` */;

/*!50001 CREATE TABLE  `v_commerceacceptstate`(
 `id` varchar(64) ,
 `commerceName` varchar(255) ,
 `fileCode` varchar(255) ,
 `commerceState` varchar(1) ,
 `commerceCode` varchar(255) ,
 `conpanyPersion` varchar(255) ,
 `contactPersion` varchar(255) ,
 `phone` varchar(11) ,
 `openBank` varchar(255) ,
 `bankCode` varchar(30) ,
 `dutyParagraph` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `businessDivision` varchar(255) ,
 `provinceId` varchar(64) ,
 `province` varchar(255) ,
 `qualityValidity` datetime ,
 `registerAddress` varchar(255) ,
 `sealStyle` varchar(1) ,
 `billsStyle` varchar(1) ,
 `enterPersion` varchar(255) ,
 `enterDate` datetime ,
 `commerceLevel` varchar(255) ,
 `functionType` varchar(255) ,
 `dutyOfficer` varchar(255) ,
 `scm_zsj_commerce` varchar(3) 
)*/;

/*Table structure for table `v_commerceacceptstatedetails` */

DROP TABLE IF EXISTS `v_commerceacceptstatedetails`;

/*!50001 DROP VIEW IF EXISTS `v_commerceacceptstatedetails` */;
/*!50001 DROP TABLE IF EXISTS `v_commerceacceptstatedetails` */;

/*!50001 CREATE TABLE  `v_commerceacceptstatedetails`(
 `agtherId` varchar(64) ,
 `commerceId` varchar(64) ,
 `commerceCode` varchar(255) ,
 `acceptState` varchar(1) ,
 `startDate` datetime ,
 `endDate` datetime ,
 `memo` varchar(600) ,
 `commerceName` varchar(255) ,
 `conpanyPersion` varchar(255) ,
 `businessDivision` varchar(255) ,
 `province` varchar(255) ,
 `enterDate` datetime 
)*/;

/*Table structure for table `v_commerceacceptstatefirstlist` */

DROP TABLE IF EXISTS `v_commerceacceptstatefirstlist`;

/*!50001 DROP VIEW IF EXISTS `v_commerceacceptstatefirstlist` */;
/*!50001 DROP TABLE IF EXISTS `v_commerceacceptstatefirstlist` */;

/*!50001 CREATE TABLE  `v_commerceacceptstatefirstlist`(
 `id` varchar(64) ,
 `agtherId` varchar(64) ,
 `approvalCode` varchar(255) ,
 `ownerId` varchar(64) ,
 `businessDivisionId` varchar(64) ,
 `businessDivision` varchar(255) ,
 `approvalId` varchar(64) ,
 `approvalState` varchar(1) ,
 `approvalStateStr` varchar(3) ,
 `provinceId` varchar(64) ,
 `province` varchar(255) ,
 `commerceName` varchar(255) ,
 `commerceCode` varchar(255) ,
 `dutyOfficer` varchar(255) ,
 `enterDate` datetime ,
 `acceptState` varchar(1) ,
 `startDate` datetime ,
 `endDate` datetime ,
 `memo` varchar(600) ,
 `commerceId` varchar(64) ,
 `acceptStateStr` varchar(3) 
)*/;

/*Table structure for table `v_commerceacceptts` */

DROP TABLE IF EXISTS `v_commerceacceptts`;

/*!50001 DROP VIEW IF EXISTS `v_commerceacceptts` */;
/*!50001 DROP TABLE IF EXISTS `v_commerceacceptts` */;

/*!50001 CREATE TABLE  `v_commerceacceptts`(
 `id` varchar(64) ,
 `commerceName` varchar(255) ,
 `fileCode` varchar(255) ,
 `commerceState` varchar(1) ,
 `commerceCode` varchar(255) ,
 `conpanyPersion` varchar(255) ,
 `contactPersion` varchar(255) ,
 `phone` varchar(11) ,
 `openBank` varchar(255) ,
 `bankCode` varchar(30) ,
 `dutyParagraph` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `businessDivision` varchar(255) ,
 `provinceId` varchar(64) ,
 `province` varchar(255) ,
 `qualityValidity` datetime ,
 `registerAddress` varchar(255) ,
 `sealStyle` varchar(1) ,
 `billsStyle` varchar(1) ,
 `enterPersion` varchar(255) ,
 `enterDate` datetime ,
 `commerceLevel` varchar(255) ,
 `functionType` varchar(255) ,
 `acceptStateId` varchar(64) ,
 `acceptState` varchar(1) ,
 `effectiveDate` datetime ,
 `memo` varchar(600) ,
 `ownerId` varchar(64) 
)*/;

/*Table structure for table `v_commercefirstlist` */

DROP TABLE IF EXISTS `v_commercefirstlist`;

/*!50001 DROP VIEW IF EXISTS `v_commercefirstlist` */;
/*!50001 DROP TABLE IF EXISTS `v_commercefirstlist` */;

/*!50001 CREATE TABLE  `v_commercefirstlist`(
 `id` varchar(64) ,
 `commerceId` varchar(64) ,
 `approvalStateStr` varchar(4) ,
 `acceptStateStr` varchar(3) ,
 `acceptState` varchar(2) ,
 `commerceCode` varchar(255) ,
 `fileCode` varchar(255) ,
 `commerceStateStr` varchar(6) ,
 `commerceState` varchar(2) ,
 `commerceName` varchar(255) ,
 `businessDivision` varchar(255) ,
 `province` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `provinceId` varchar(64) ,
 `legalPersion` varchar(255) ,
 `licenseKey` varchar(255) ,
 `validity` datetime ,
 `register` varchar(255) ,
 `licenseValidity` datetime ,
 `conpanyPersion` varchar(255) ,
 `registerPrice` varchar(20) ,
 `managementScope` varchar(255) ,
 `enterPersion` varchar(255) ,
 `enterDate` datetime ,
 `operatorName` varchar(255) ,
 `operatorDate` datetime ,
 `commerceFirstId` varchar(64) ,
 `approvalState` varchar(1) ,
 `ownerId` varchar(64) ,
 `functionType` varchar(255) 
)*/;

/*Table structure for table `v_creditapply` */

DROP TABLE IF EXISTS `v_creditapply`;

/*!50001 DROP VIEW IF EXISTS `v_creditapply` */;
/*!50001 DROP TABLE IF EXISTS `v_creditapply` */;

/*!50001 CREATE TABLE  `v_creditapply`(
 `id` varchar(64) ,
 `businessDivision` varchar(255) ,
 `Province` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `provinceId` varchar(64) ,
 `commerceCode` varchar(255) ,
 `commerceName` varchar(255) ,
 `marketWay` varchar(1) ,
 `beforeYearPrice` int(10) ,
 `lastYearPrice` int(10) ,
 `lastTimePrice` int(10) ,
 `sysPrice` int(10) ,
 `price` int(10) ,
 `memo` varchar(600) ,
 `CreditCollectId` varchar(64) ,
 `year` varchar(64) ,
 `quarter` varchar(64) ,
 `masterId` varchar(64) ,
 `approvalId` varchar(64) ,
 `approvalState` varchar(1) ,
 `ownerId` varchar(64) 
)*/;

/*Table structure for table `v_creditapplyfirstlist` */

DROP TABLE IF EXISTS `v_creditapplyfirstlist`;

/*!50001 DROP VIEW IF EXISTS `v_creditapplyfirstlist` */;
/*!50001 DROP TABLE IF EXISTS `v_creditapplyfirstlist` */;

/*!50001 CREATE TABLE  `v_creditapplyfirstlist`(
 `masterId` varchar(64) ,
 `approvalId` varchar(64) ,
 `approvalState` varchar(1) ,
 `approvalStateStr` varchar(4) ,
 `id` varchar(64) ,
 `year` varchar(64) ,
 `quarter` varchar(64) ,
 `businessDivision` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `Province` varchar(255) ,
 `provinceId` varchar(64) ,
 `commerceCode` varchar(255) ,
 `commerceName` varchar(255) ,
 `marketWay` varchar(1) ,
 `price` int(10) ,
 `ownerId` varchar(64) 
)*/;

/*Table structure for table `v_dealerclause` */

DROP TABLE IF EXISTS `v_dealerclause`;

/*!50001 DROP VIEW IF EXISTS `v_dealerclause` */;
/*!50001 DROP TABLE IF EXISTS `v_dealerclause` */;

/*!50001 CREATE TABLE  `v_dealerclause`(
 `id` varchar(64) ,
 `approvalStateStr` varchar(3) ,
 `AgreementSummaryId` varchar(64) ,
 `clauseCode` varchar(255) ,
 `commerceName` varchar(255) ,
 `functionType` varchar(255) ,
 `AgreementType` varchar(255) ,
 `AgreementStartDate` datetime ,
 `AgreementChangeDate` datetime ,
 `AgreementEndDate` datetime ,
 `paymentType` varchar(1) ,
 `returnMoneyDay` int(10) ,
 `promiseProvince` varchar(255) ,
 `messageStandard` int(10) ,
 `flowStandard` int(10) ,
 `transportationDiscount` int(10) ,
 `unloadPayer` varchar(1) ,
 `subsidiarySum` int(10) ,
 `memo` varchar(600) ,
 `isOverfulfil` varchar(1) ,
 `StardDay` int(10) ,
 `endDay` int(10) ,
 `theApplication` int(10) ,
 `isSpecial` varchar(1) ,
 `lastPaymentType` varchar(1) ,
 `lastReturnMoneyDay` int(10) ,
 `quarterPaymentPercent` double(10,2) ,
 `commerceCode` varchar(255) ,
 `actualLevel` varchar(255) ,
 `lasttheApplication` bigint(11) ,
 `masterId` varchar(64) ,
 `businessDivision` varchar(255) ,
 `Province` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `provinceId` varchar(64) ,
 `approvalId` varchar(64) ,
 `approvalState` varchar(1) ,
 `ownerId` varchar(64) ,
 `agreementSummaryCode` varchar(64) ,
 `discount` int(1) 
)*/;

/*Table structure for table `v_distributoracceptstate` */

DROP TABLE IF EXISTS `v_distributoracceptstate`;

/*!50001 DROP VIEW IF EXISTS `v_distributoracceptstate` */;
/*!50001 DROP TABLE IF EXISTS `v_distributoracceptstate` */;

/*!50001 CREATE TABLE  `v_distributoracceptstate`(
 `commerceId` varchar(64) ,
 `commerceName` varchar(255) ,
 `fileCode` varchar(255) ,
 `commerceState` varchar(1) ,
 `commerceCode` varchar(255) ,
 `conpanyPersion` varchar(255) ,
 `contactPersion` varchar(255) ,
 `phone` varchar(11) ,
 `openBank` varchar(255) ,
 `bankCode` varchar(30) ,
 `dutyParagraph` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `businessDivision` varchar(255) ,
 `provinceId` varchar(64) ,
 `province` varchar(255) ,
 `qualityValidity` datetime ,
 `registerAddress` varchar(255) ,
 `sealStyle` varchar(1) ,
 `billsStyle` varchar(1) ,
 `enterPersion` varchar(255) ,
 `enterDate` datetime ,
 `commerceLevel` varchar(255) ,
 `functionType` varchar(255) ,
 `integral` decimal(23,0) ,
 `ownerId` varchar(64) ,
 `scm_zsj_commerceState` varchar(3) 
)*/;

/*Table structure for table `v_distributorfirstlist` */

DROP TABLE IF EXISTS `v_distributorfirstlist`;

/*!50001 DROP VIEW IF EXISTS `v_distributorfirstlist` */;
/*!50001 DROP TABLE IF EXISTS `v_distributorfirstlist` */;

/*!50001 CREATE TABLE  `v_distributorfirstlist`(
 `id` varchar(64) ,
 `commerceId` varchar(64) ,
 `clauseCode` varchar(255) ,
 `commerceName` varchar(255) ,
 `commerceLevel` varchar(255) ,
 `functionType` varchar(255) ,
 `isSubsidiary` varchar(600) ,
 `starLevel` varchar(255) ,
 `messageCost` double(10,2) ,
 `isPromotional` varchar(1) ,
 `startDate` datetime ,
 `endDate` datetime ,
 `provincePlatformId` varchar(64) ,
 `upCommerceId` varchar(64) ,
 `memo` varchar(600) ,
 `DistributorAgreementId` varchar(64) ,
 `year` varchar(4) ,
 `businessDivision` varchar(255) ,
 `Province` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `provinceId` varchar(64) ,
 `approvalId` varchar(64) ,
 `approvalState` varchar(1) ,
 `ownerId` varchar(64) ,
 `agreementSummaryCode` varchar(64) ,
 `approvalStateStr` varchar(3) 
)*/;

/*Table structure for table `v_productfirstlist` */

DROP TABLE IF EXISTS `v_productfirstlist`;

/*!50001 DROP VIEW IF EXISTS `v_productfirstlist` */;
/*!50001 DROP TABLE IF EXISTS `v_productfirstlist` */;

/*!50001 CREATE TABLE  `v_productfirstlist`(
 `id` varchar(64) ,
 `productNum` varchar(255) ,
 `MedicineType` varchar(255) ,
 `productName` varchar(255) ,
 `commonName` varchar(255) ,
 `unit` varchar(255) ,
 `productState` varchar(255) ,
 `AgentType` varchar(255) ,
 `ProductLineId` varchar(64) ,
 `ProductLineName` varchar(255) ,
 `approvalCode` varchar(255) ,
 `gmp` varchar(255) ,
 `GMPValidity` datetime ,
 `businessScope` varchar(255) ,
 `approvalState` varchar(1) ,
 `productFirstId` varchar(64) ,
 `ownerId` varchar(64) ,
 `approvalId` varchar(64) ,
 `supplier` varchar(255) ,
 `approvalStateStr` varchar(3) 
)*/;

/*Table structure for table `v_supplierfirstlist` */

DROP TABLE IF EXISTS `v_supplierfirstlist`;

/*!50001 DROP VIEW IF EXISTS `v_supplierfirstlist` */;
/*!50001 DROP TABLE IF EXISTS `v_supplierfirstlist` */;

/*!50001 CREATE TABLE  `v_supplierfirstlist`(
 `approvalStateStr` varchar(4) ,
 `supplierCode` varchar(255) ,
 `fileCode` varchar(255) ,
 `supplierName` varchar(255) ,
 `id` varchar(64) ,
 `legalPersion` varchar(255) ,
 `licenseKey` varchar(255) ,
 `validity` datetime ,
 `registerCode` varchar(255) ,
 `licenseValidity` datetime ,
 `conpanyPersion` varchar(255) ,
 `registerPrice` varchar(20) ,
 `managementScope` varchar(255) ,
 `enterPersion` varchar(255) ,
 `enterDate` datetime ,
 `operatorName` varchar(255) ,
 `operatorDate` datetime ,
 `ownerId` varchar(64) ,
 `approvalState` varchar(1) ,
 `approvalId` varchar(64) ,
 `supplierFirstId` varchar(64) 
)*/;

/*Table structure for table `v_updateagreementlist` */

DROP TABLE IF EXISTS `v_updateagreementlist`;

/*!50001 DROP VIEW IF EXISTS `v_updateagreementlist` */;
/*!50001 DROP TABLE IF EXISTS `v_updateagreementlist` */;

/*!50001 CREATE TABLE  `v_updateagreementlist`(
 `id` varchar(64) ,
 `commerceName` varchar(255) ,
 `fileCode` varchar(255) ,
 `commerceState` varchar(1) ,
 `commerceCode` varchar(255) ,
 `conpanyPersion` varchar(255) ,
 `contactPersion` varchar(255) ,
 `phone` varchar(11) ,
 `openBank` varchar(255) ,
 `bankCode` varchar(30) ,
 `dutyParagraph` varchar(255) ,
 `businessDivisionId` varchar(64) ,
 `businessDivision` varchar(255) ,
 `provinceId` varchar(64) ,
 `province` varchar(255) ,
 `qualityValidity` datetime ,
 `registerAddress` varchar(255) ,
 `sealStyle` varchar(1) ,
 `billsStyle` varchar(1) ,
 `enterPersion` varchar(255) ,
 `enterDate` datetime ,
 `commerceLevel` varchar(255) ,
 `functionType` varchar(255) ,
 `ownerId` varchar(64) ,
 `acceptState` varchar(3) ,
 `lastYear` varchar(1) ,
 `shipments` varchar(64) ,
 `allocating` varchar(64) ,
 `sales` varchar(64) 
)*/;

/*View structure for view v_addagreementlist */

/*!50001 DROP TABLE IF EXISTS `v_addagreementlist` */;
/*!50001 DROP VIEW IF EXISTS `v_addagreementlist` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_addagreementlist` AS select `e`.`id` AS `commerceId`,`e`.`commerceFirstId` AS `commerceFirstId`,`e`.`commerceName` AS `commerceName`,`e`.`fileCode` AS `fileCode`,`e`.`commerceState` AS `commerceState`,`e`.`commerceCode` AS `commerceCode`,`e`.`conpanyPersion` AS `conpanyPersion`,`e`.`contactPersion` AS `contactPersion`,`e`.`phone` AS `phone`,`e`.`openBank` AS `openBank`,`e`.`bankCode` AS `bankCode`,`e`.`dutyParagraph` AS `dutyParagraph`,`e`.`businessDivisionId` AS `businessDivisionId`,`e`.`businessDivision` AS `businessDivision`,`e`.`provinceId` AS `provinceId`,`e`.`province` AS `province`,`e`.`qualityValidity` AS `qualityValidity`,`e`.`registerAddress` AS `registerAddress`,`e`.`sealStyle` AS `sealStyle`,`e`.`billsStyle` AS `billsStyle`,`e`.`enterPersion` AS `enterPersion`,`e`.`enterDate` AS `enterDate`,`e`.`commerceLevel` AS `commerceLevel`,`e`.`functionType` AS `functionType`,`e`.`acceptState` AS `acceptState`,`e`.`shipments` AS `shipments`,`e`.`allocating` AS `allocating`,`e`.`Sales` AS `Sales`,`e`.`lastYear` AS `lastYear`,`e`.`firstId` AS `firstId`,`e`.`ownerId` AS `ownerId` from (select `a`.`id` AS `id`,`a`.`commerceName` AS `commerceName`,`a`.`fileCode` AS `fileCode`,`a`.`commerceState` AS `commerceState`,`a`.`commerceCode` AS `commerceCode`,`a`.`conpanyPersion` AS `conpanyPersion`,`a`.`contactPersion` AS `contactPersion`,`a`.`phone` AS `phone`,`a`.`openBank` AS `openBank`,`a`.`bankCode` AS `bankCode`,`a`.`dutyParagraph` AS `dutyParagraph`,`a`.`businessDivisionId` AS `businessDivisionId`,`a`.`businessDivision` AS `businessDivision`,`a`.`provinceId` AS `provinceId`,`a`.`province` AS `province`,`a`.`qualityValidity` AS `qualityValidity`,`a`.`registerAddress` AS `registerAddress`,`a`.`sealStyle` AS `sealStyle`,`a`.`billsStyle` AS `billsStyle`,`a`.`enterPersion` AS `enterPersion`,`a`.`enterDate` AS `enterDate`,`a`.`commerceLevel` AS `commerceLevel`,`a`.`functionType` AS `functionType`,`b`.`id` AS `commerceFirstId`,(case `c`.`acceptState` when '0' then '认可' when '1' then '考察中' when '2' then '终止' else '未认可' end) AS `acceptState`,0 AS `shipments`,0 AS `allocating`,0 AS `Sales`,ifnull(`d`.`isSign`,0) AS `lastYear`,`b`.`id` AS `firstId`,`b`.`ownerId` AS `ownerId` from (((`ww_scm_dev`.`scm_zsj_commerce` `a` join `ww_scm_dev`.`scm_zsj_commercefirst` `b` on(((`a`.`id` = `b`.`commerceId`) and (`b`.`approvalState` = 2)))) left join `ww_scm_dev`.`scm_zsj_commerceacceptstate` `c` on((`a`.`id` = `c`.`commerceId`))) left join (select `c`.`commerceCode` AS `commerceCode`,`c`.`isSign` AS `isSign` from `ww_scm_dev`.`scm_xs_agreementlist` `c` where (`c`.`year` = (date_format(now(),'%Y') - 1)) group by `c`.`commerceCode`) `d` on((`a`.`commerceCode` = `d`.`commerceCode`)))) `e` */;

/*View structure for view v_agreementdetails */

/*!50001 DROP TABLE IF EXISTS `v_agreementdetails` */;
/*!50001 DROP VIEW IF EXISTS `v_agreementdetails` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_agreementdetails` AS select `a`.`id` AS `id`,`a`.`commerceFirstId` AS `commerceFirstId`,`a`.`year` AS `year`,`a`.`commerceCode` AS `commerceCode`,`a`.`commerceClassify` AS `commerceClassify`,`a`.`commerceLevel` AS `commerceLevel`,`a`.`memo` AS `memo`,`a`.`isSign` AS `isSign`,`a`.`masterId` AS `masterId`,`a`.`commerceName` AS `commerceName`,`a`.`type` AS `type`,`a`.`lastYear` AS `lastYear`,`a`.`shipments` AS `shipments`,`a`.`allocating` AS `allocating`,`a`.`sales` AS `sales`,(case `b`.`acceptState` when '0' then '认可' when '1' then '考察中' when '2' then '终止' else '未处理' end) AS `acceptState` from (((select `ww_scm_dev`.`scm_xs_agreementlist`.`id` AS `id`,`ww_scm_dev`.`scm_xs_agreementlist`.`commerceFirstId` AS `commerceFirstId`,`ww_scm_dev`.`scm_xs_agreementlist`.`year` AS `year`,`ww_scm_dev`.`scm_xs_agreementlist`.`commerceCode` AS `commerceCode`,`ww_scm_dev`.`scm_xs_agreementlist`.`commerceClassify` AS `commerceClassify`,`ww_scm_dev`.`scm_xs_agreementlist`.`commerceLevel` AS `commerceLevel`,`ww_scm_dev`.`scm_xs_agreementlist`.`memo` AS `memo`,`ww_scm_dev`.`scm_xs_agreementlist`.`isSign` AS `isSign`,`ww_scm_dev`.`scm_xs_agreementlist`.`masterId` AS `masterId`,`ww_scm_dev`.`scm_xs_agreementlist`.`commerceName` AS `commerceName`,`ww_scm_dev`.`scm_xs_agreementlist`.`type` AS `type`,`ww_scm_dev`.`scm_xs_agreementlist`.`lastYear` AS `lastYear`,`ww_scm_dev`.`scm_xs_agreementlist`.`shipments` AS `shipments`,`ww_scm_dev`.`scm_xs_agreementlist`.`allocating` AS `allocating`,`ww_scm_dev`.`scm_xs_agreementlist`.`sales` AS `sales` from `ww_scm_dev`.`scm_xs_agreementlist` where (`ww_scm_dev`.`scm_xs_agreementlist`.`masterId` = '9491003'))) `a` left join `ww_scm_dev`.`scm_zsj_commerceacceptstate` `b` on((`a`.`commerceCode` = `b`.`commerceCode`))) */;

/*View structure for view v_agreementlist */

/*!50001 DROP TABLE IF EXISTS `v_agreementlist` */;
/*!50001 DROP VIEW IF EXISTS `v_agreementlist` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_agreementlist` AS select `a`.`commerceFirstId` AS `commerceFirstId`,`a`.`commerceName` AS `commerceName`,`a`.`fileCode` AS `fileCode`,`a`.`commerceState` AS `commerceState`,`a`.`commerceCode` AS `commerceCode`,`a`.`conpanyPersion` AS `conpanyPersion`,`a`.`contactPersion` AS `contactPersion`,`a`.`phone` AS `phone`,`a`.`openBank` AS `openBank`,`a`.`bankCode` AS `bankCode`,`a`.`dutyParagraph` AS `dutyParagraph`,`a`.`businessDivisionId` AS `businessDivisionId`,`a`.`businessDivision` AS `businessDivision`,`a`.`provinceId` AS `provinceId`,`a`.`province` AS `province`,`a`.`qualityValidity` AS `qualityValidity`,`a`.`registerAddress` AS `registerAddress`,`a`.`sealStyle` AS `sealStyle`,`a`.`billsStyle` AS `billsStyle`,`a`.`enterPersion` AS `enterPersion`,`a`.`enterDate` AS `enterDate`,`a`.`commerceLevel` AS `commerceLevel`,`a`.`functionType` AS `functionType`,`a`.`ownerId` AS `ownerId`,`a`.`acceptState` AS `acceptState`,ifnull(`b`.`lastYear`,'0') AS `lastYear`,ifnull(`b`.`isSign`,'0') AS `issign`,ifnull(`b`.`shipments`,'0') AS `shipments`,ifnull(`b`.`allocating`,'0') AS `allocating`,ifnull(`b`.`sales`,'0') AS `sales`,`b`.`id` AS `id`,`b`.`masterId` AS `masterId`,`b`.`commerceClassify` AS `commerceClassify`,`b`.`memo` AS `memo` from (((select `a`.`id` AS `commerceFirstId`,`a`.`commerceName` AS `commerceName`,`a`.`fileCode` AS `fileCode`,`a`.`commerceState` AS `commerceState`,`a`.`commerceCode` AS `commerceCode`,`a`.`conpanyPersion` AS `conpanyPersion`,`a`.`contactPersion` AS `contactPersion`,`a`.`phone` AS `phone`,`a`.`openBank` AS `openBank`,`a`.`bankCode` AS `bankCode`,`a`.`dutyParagraph` AS `dutyParagraph`,`a`.`businessDivisionId` AS `businessDivisionId`,`a`.`businessDivision` AS `businessDivision`,`a`.`provinceId` AS `provinceId`,`a`.`province` AS `province`,`a`.`qualityValidity` AS `qualityValidity`,`a`.`registerAddress` AS `registerAddress`,`a`.`sealStyle` AS `sealStyle`,`a`.`billsStyle` AS `billsStyle`,`a`.`enterPersion` AS `enterPersion`,`a`.`enterDate` AS `enterDate`,`a`.`commerceLevel` AS `commerceLevel`,`a`.`functionType` AS `functionType`,`d`.`ownerId` AS `ownerId`,(case `b`.`acceptState` when '0' then '认可' when '1' then '考察中' when '2' then '终止' else '未处理' end) AS `acceptState` from (((`ww_scm_dev`.`scm_zsj_commerce` `a` left join `ww_scm_dev`.`scm_zsj_commerceacceptstate` `b` on((`a`.`id` = `b`.`commerceId`))) left join `ww_scm_dev`.`scm_zsj_commerceacceptstateagther` `c` on((`b`.`agtherId` = `c`.`id`))) left join `ww_scm_dev`.`scm_zsj_commercefirst` `d` on((`a`.`id` = `d`.`commerceId`))) where ((`d`.`approvalState` = '2') and (`a`.`commerceLevel` = '经销商')))) `a` left join `ww_scm_dev`.`scm_xs_agreementlist` `b` on((`a`.`commerceCode` = `b`.`commerceCode`))) */;

/*View structure for view v_agreementlistmaster */

/*!50001 DROP TABLE IF EXISTS `v_agreementlistmaster` */;
/*!50001 DROP VIEW IF EXISTS `v_agreementlistmaster` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_agreementlistmaster` AS select `a`.`approvalState` AS `approvalState`,`a`.`businessDivision` AS `businessDivision`,`a`.`businessDivisionId` AS `businessDivisionId`,`a`.`ownerId` AS `ownerId`,`a`.`approvalId` AS `approvalId`,`a`.`MasterCode` AS `MasterCode`,`a`.`isEffect` AS `isEffect`,`b`.`id` AS `id`,`b`.`isSign` AS `isSign`,`b`.`commerceFirstId` AS `commerceFirstId`,`b`.`year` AS `year`,`b`.`commerceCode` AS `commerceCode`,`b`.`commerceName` AS `commerceName`,`b`.`masterId` AS `masterId`,`b`.`type` AS `type`,`b`.`lastYear` AS `lastYear`,`b`.`shipments` AS `shipments`,`b`.`allocating` AS `allocating`,`b`.`sales` AS `sales`,`b`.`memo` AS `memo`,(case `c`.`acceptState` when '0' then '认可' when '1' then '考察中' when '2' then '终止' else '未处理' end) AS `acceptState` from ((`scm_xs_agreementlistmaster` `a` left join `scm_xs_agreementlist` `b` on((`a`.`id` = `b`.`masterId`))) left join `scm_zsj_commerceacceptstatets` `c` on((`b`.`commerceCode` = `c`.`commerceCode`))) */;

/*View structure for view v_agreementsendapplysuccess */

/*!50001 DROP TABLE IF EXISTS `v_agreementsendapplysuccess` */;
/*!50001 DROP VIEW IF EXISTS `v_agreementsendapplysuccess` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_agreementsendapplysuccess` AS select `a`.`id` AS `id`,`a`.`functionType` AS `functionType`,`a`.`approvalStateStr` AS `approvalStateStr`,`a`.`acceptStateStr` AS `acceptStateStr`,`a`.`acceptState` AS `acceptState`,`a`.`commerceCode` AS `commerceCode`,`a`.`fileCode` AS `fileCode`,`a`.`commerceStateStr` AS `commerceStateStr`,`a`.`commerceState` AS `commerceState`,`a`.`commerceName` AS `commerceName`,`a`.`businessDivision` AS `businessDivision`,`a`.`province` AS `province`,`a`.`businessDivisionId` AS `businessDivisionId`,`a`.`provinceId` AS `provinceId`,`a`.`legalPersion` AS `legalPersion`,`a`.`licenseKey` AS `licenseKey`,`a`.`validity` AS `validity`,`a`.`register` AS `register`,`a`.`licenseValidity` AS `licenseValidity`,`a`.`conpanyPersion` AS `conpanyPersion`,`a`.`registerPrice` AS `registerPrice`,`a`.`managementScope` AS `managementScope`,`a`.`enterPersion` AS `enterPersion`,`a`.`enterDate` AS `enterDate`,`a`.`operatorName` AS `operatorName`,`a`.`operatorDate` AS `operatorDate`,`a`.`commerceFirstId` AS `commerceFirstId`,`a`.`approvalState` AS `approvalState`,`a`.`ownerId` AS `ownerId`,`a`.`type` AS `type`,`a`.`actualLevel` AS `actualLevel`,`a`.`isEffect` AS `isEffect` from (select `a`.`id` AS `id`,`a`.`functionType` AS `functionType`,`a`.`approvalStateStr` AS `approvalStateStr`,`a`.`acceptStateStr` AS `acceptStateStr`,`a`.`acceptState` AS `acceptState`,`a`.`commerceCode` AS `commerceCode`,`a`.`fileCode` AS `fileCode`,`a`.`commerceStateStr` AS `commerceStateStr`,`a`.`commerceState` AS `commerceState`,`a`.`commerceName` AS `commerceName`,`a`.`businessDivision` AS `businessDivision`,`a`.`province` AS `province`,`a`.`businessDivisionId` AS `businessDivisionId`,`a`.`provinceId` AS `provinceId`,`a`.`legalPersion` AS `legalPersion`,`a`.`licenseKey` AS `licenseKey`,`a`.`validity` AS `validity`,`a`.`register` AS `register`,`a`.`licenseValidity` AS `licenseValidity`,`a`.`conpanyPersion` AS `conpanyPersion`,`a`.`registerPrice` AS `registerPrice`,`a`.`managementScope` AS `managementScope`,`a`.`enterPersion` AS `enterPersion`,`a`.`enterDate` AS `enterDate`,`a`.`operatorName` AS `operatorName`,`a`.`operatorDate` AS `operatorDate`,`a`.`commerceFirstId` AS `commerceFirstId`,`a`.`approvalState` AS `approvalState`,`a`.`ownerId` AS `ownerId`,`b`.`type` AS `type`,`b`.`commerceLevel` AS `actualLevel`,`b`.`isEffect` AS `isEffect` from (((select `v_commercefirstlist`.`id` AS `id`,`v_commercefirstlist`.`approvalStateStr` AS `approvalStateStr`,`v_commercefirstlist`.`acceptStateStr` AS `acceptStateStr`,`v_commercefirstlist`.`acceptState` AS `acceptState`,`v_commercefirstlist`.`commerceCode` AS `commerceCode`,`v_commercefirstlist`.`fileCode` AS `fileCode`,`v_commercefirstlist`.`commerceStateStr` AS `commerceStateStr`,`v_commercefirstlist`.`commerceState` AS `commerceState`,`v_commercefirstlist`.`commerceName` AS `commerceName`,`v_commercefirstlist`.`businessDivision` AS `businessDivision`,`v_commercefirstlist`.`province` AS `province`,`v_commercefirstlist`.`businessDivisionId` AS `businessDivisionId`,`v_commercefirstlist`.`provinceId` AS `provinceId`,`v_commercefirstlist`.`legalPersion` AS `legalPersion`,`v_commercefirstlist`.`licenseKey` AS `licenseKey`,`v_commercefirstlist`.`validity` AS `validity`,`v_commercefirstlist`.`register` AS `register`,`v_commercefirstlist`.`licenseValidity` AS `licenseValidity`,`v_commercefirstlist`.`conpanyPersion` AS `conpanyPersion`,`v_commercefirstlist`.`registerPrice` AS `registerPrice`,`v_commercefirstlist`.`managementScope` AS `managementScope`,`v_commercefirstlist`.`enterPersion` AS `enterPersion`,`v_commercefirstlist`.`enterDate` AS `enterDate`,`v_commercefirstlist`.`functionType` AS `functionType`,`v_commercefirstlist`.`operatorName` AS `operatorName`,`v_commercefirstlist`.`operatorDate` AS `operatorDate`,`v_commercefirstlist`.`commerceFirstId` AS `commerceFirstId`,`v_commercefirstlist`.`approvalState` AS `approvalState`,`v_commercefirstlist`.`ownerId` AS `ownerId` from `ww_scm_dev`.`v_commercefirstlist` where ((`v_commercefirstlist`.`approvalState` = 2) and (`v_commercefirstlist`.`acceptState` = 0)))) `a` left join (select `a`.`id` AS `id`,`a`.`commerceFirstId` AS `commerceFirstId`,`a`.`year` AS `year`,`a`.`commerceCode` AS `commerceCode`,`a`.`commerceClassify` AS `commerceClassify`,`a`.`commerceLevel` AS `commerceLevel`,`a`.`memo` AS `memo`,`a`.`isSign` AS `isSign`,`a`.`masterId` AS `masterId`,`a`.`commerceName` AS `commerceName`,`a`.`type` AS `type`,`a`.`lastYear` AS `lastYear`,`a`.`shipments` AS `shipments`,`a`.`allocating` AS `allocating`,`a`.`sales` AS `sales`,`b`.`businessDivisionId` AS `businessDivisionId`,`b`.`ownerId` AS `ownerId`,`b`.`approvalId` AS `approvalId`,`b`.`isEffect` AS `isEffect` from (`ww_scm_dev`.`scm_xs_agreementlist` `a` left join `ww_scm_dev`.`scm_xs_agreementlistmaster` `b` on((`a`.`masterId` = `b`.`id`)))) `b` on((`a`.`commerceCode` = `b`.`commerceCode`)))) `a` where ((`a`.`isEffect` = '1') and ((`a`.`type` = '0') or (`a`.`type` = '1'))) */;

/*View structure for view v_bigcontractgetydetails */

/*!50001 DROP TABLE IF EXISTS `v_bigcontractgetydetails` */;
/*!50001 DROP VIEW IF EXISTS `v_bigcontractgetydetails` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_bigcontractgetydetails` AS select `a`.`AgreementSummaryId` AS `AgreementSummaryId`,`a`.`id` AS `DealerClauseId`,`a`.`commerceName` AS `dealer`,`a`.`paymentType` AS `payType`,`b`.`YPhone` AS `YPhone`,`b`.`YAddress` AS `YAddress`,`b`.`YOpenBank` AS `YOpenBank`,`b`.`YBankCode` AS `YBankCode`,`b`.`YDutyCode` AS `YDutyCode`,`b`.`commerceFirstId` AS `commerceFirstId`,`b`.`consignee` AS `consignee`,`b`.`phone` AS `phone`,`b`.`YPostcode` AS `YPostcode`,`b`.`shipAddress` AS `shipAddress` from (((select `a`.`AgreementSummaryId` AS `AgreementSummaryId`,`a`.`id` AS `id`,`a`.`commerceCode` AS `commerceCode`,`a`.`commerceName` AS `commerceName`,(case `a`.`paymentType` when '0' then '预付款' when '1' then '赊销' else '未选择' end) AS `paymentType` from (`ww_scm_dev`.`scm_xs_dealerclause` `a` left join `ww_scm_dev`.`scm_xs_agreementsummary` `b` on((`a`.`AgreementSummaryId` = `b`.`id`))) where (`b`.`approvalState` = '2'))) `a` left join (select `a`.`phone` AS `YPhone`,`a`.`registerAddress` AS `YAddress`,`a`.`openBank` AS `YOpenBank`,`a`.`bankCode` AS `YBankCode`,`a`.`dutyParagraph` AS `YDutyCode`,`a`.`commerceFirstId` AS `commerceFirstId`,`c`.`contactPersion` AS `consignee`,`c`.`phone` AS `phone`,`c`.`postcode` AS `YPostcode`,concat(`c`.`province`,`c`.`city`,`c`.`district`,`c`.`street`,`c`.`address`) AS `shipAddress`,`a`.`commerceCode` AS `commerceCode` from (((select `a`.`contactPersion` AS `contactPersion`,`a`.`phone` AS `phone`,`a`.`registerAddress` AS `registerAddress`,`a`.`openBank` AS `openBank`,`a`.`bankCode` AS `bankCode`,`a`.`dutyParagraph` AS `dutyParagraph`,`b`.`id` AS `commerceFirstId`,`a`.`commerceCode` AS `commerceCode` from (`ww_scm_dev`.`scm_zsj_commerce` `a` left join `ww_scm_dev`.`scm_zsj_commercefirst` `b` on((`a`.`id` = `b`.`commerceId`))) where (`b`.`approvalState` = '2'))) `a` left join `ww_scm_dev`.`scm_zsj_commerceshipaddress` `c` on((`a`.`commerceFirstId` = `c`.`commerceFirstId`)))) `b` on((`a`.`commerceCode` = `b`.`commerceCode`))) */;

/*View structure for view v_bigscmxsbigcontractsendapply */

/*!50001 DROP TABLE IF EXISTS `v_bigscmxsbigcontractsendapply` */;
/*!50001 DROP VIEW IF EXISTS `v_bigscmxsbigcontractsendapply` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_bigscmxsbigcontractsendapply` AS select `b`.`id` AS `productId`,`b`.`productName` AS `productName`,`b`.`MedicineType` AS `MedicineType`,`b`.`commonName` AS `commonName`,`b`.`productState` AS `productState`,`b`.`unit` AS `unit`,`a`.`id` AS `agreementproductdetailId`,`a`.`agreementSummaryId` AS `agreementSummaryId`,`a`.`commerceGauge` AS `productGauge`,`a`.`commerceCode` AS `productCode`,`a`.`unitPrice` AS `unitPrice`,(case `c`.`ProductLineState` when '0' then '暂停供应' else '正常供应' end) AS `suppyType` from ((((select `a`.`commerceGauge` AS `commerceGauge`,`a`.`id` AS `id`,`a`.`agreementSummaryId` AS `agreementSummaryId`,`a`.`commerceCode` AS `commerceCode`,`a`.`supplyPrice` AS `unitPrice`,`a`.`isSign` AS `isSign`,`b`.`approvalState` AS `approvalState` from (`ww_scm_dev`.`scm_xs_agreementproductdetail` `a` left join `ww_scm_dev`.`scm_xs_agreementsummary` `b` on((`a`.`agreementSummaryId` = `b`.`id`))) where ((`b`.`approvalState` = '2') and (`a`.`isSign` = '1')))) `a` left join `ww_scm_dev`.`scm_zsj_product` `b` on((`a`.`commerceCode` = `b`.`productCode`))) left join `ww_scm_dev`.`scm_zsj_productline` `c` on((`b`.`ProductLineId` = `c`.`id`))) */;

/*View structure for view v_commerceacceptapproval */

/*!50001 DROP TABLE IF EXISTS `v_commerceacceptapproval` */;
/*!50001 DROP VIEW IF EXISTS `v_commerceacceptapproval` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_commerceacceptapproval` AS select `a`.`id` AS `id`,`a`.`commerceName` AS `commerceName`,`a`.`fileCode` AS `fileCode`,`a`.`commerceState` AS `commerceState`,`a`.`commerceCode` AS `commerceCode`,`a`.`conpanyPersion` AS `conpanyPersion`,`a`.`contactPersion` AS `contactPersion`,`a`.`phone` AS `phone`,`a`.`openBank` AS `openBank`,`a`.`bankCode` AS `bankCode`,`a`.`dutyParagraph` AS `dutyParagraph`,`a`.`businessDivisionId` AS `businessDivisionId`,`a`.`businessDivision` AS `businessDivision`,`a`.`provinceId` AS `provinceId`,`a`.`province` AS `province`,`a`.`qualityValidity` AS `qualityValidity`,`a`.`registerAddress` AS `registerAddress`,`a`.`sealStyle` AS `sealStyle`,`a`.`billsStyle` AS `billsStyle`,`a`.`enterPersion` AS `enterPersion`,`a`.`enterDate` AS `enterDate`,`a`.`commerceLevel` AS `commerceLevel`,`a`.`functionType` AS `functionType`,`b`.`id` AS `acceptStateId`,`b`.`acceptState` AS `acceptState`,`b`.`startDate` AS `effectiveDate`,`b`.`memo` AS `memo`,`c`.`id` AS `acceptId`,`c`.`approvalId` AS `approvalId`,`c`.`approvalState` AS `approvalState`,`c`.`ownerId` AS `ownerId`,`c`.`operatorName` AS `operatorName` from ((`scm_zsj_commerce` `a` left join `scm_zsj_commerceacceptstate` `b` on((`a`.`id` = `b`.`commerceId`))) left join `scm_zsj_commerceacceptstateagther` `c` on((`b`.`agtherId` = `c`.`id`))) where (`b`.`id` is not null) */;

/*View structure for view v_commerceacceptstate */

/*!50001 DROP TABLE IF EXISTS `v_commerceacceptstate` */;
/*!50001 DROP VIEW IF EXISTS `v_commerceacceptstate` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_commerceacceptstate` AS select `a`.`id` AS `id`,`a`.`commerceName` AS `commerceName`,`a`.`fileCode` AS `fileCode`,`a`.`commerceState` AS `commerceState`,`a`.`commerceCode` AS `commerceCode`,`a`.`conpanyPersion` AS `conpanyPersion`,`a`.`contactPersion` AS `contactPersion`,`a`.`phone` AS `phone`,`a`.`openBank` AS `openBank`,`a`.`bankCode` AS `bankCode`,`a`.`dutyParagraph` AS `dutyParagraph`,`a`.`businessDivisionId` AS `businessDivisionId`,`a`.`businessDivision` AS `businessDivision`,`a`.`provinceId` AS `provinceId`,`a`.`province` AS `province`,`a`.`qualityValidity` AS `qualityValidity`,`a`.`registerAddress` AS `registerAddress`,`a`.`sealStyle` AS `sealStyle`,`a`.`billsStyle` AS `billsStyle`,`a`.`enterPersion` AS `enterPersion`,`a`.`enterDate` AS `enterDate`,`a`.`commerceLevel` AS `commerceLevel`,`a`.`functionType` AS `functionType`,(case when isnull(`c`.`dutyOfficer`) then '无' else `c`.`dutyOfficer` end) AS `dutyOfficer`,(case `b`.`acceptState` when '0' then '认可' when '1' then '考察中' when '2' then '终止' else '终止' end) AS `scm_zsj_commerce` from (((`ww_scm_dev`.`scm_zsj_commerce` `a` left join (select `a`.`id` AS `id`,`a`.`agtherId` AS `agtherId`,`a`.`commerceId` AS `commerceId`,`a`.`commerceCode` AS `commerceCode`,`a`.`acceptState` AS `acceptState`,`a`.`memo` AS `memo` from (`ww_scm_dev`.`scm_zsj_commerceacceptstate` `a` join (select `b`.`commerceId` AS `commerceId`,max(`b`.`id`) AS `id` from `ww_scm_dev`.`scm_zsj_commerceacceptstate` `b`) `c` on((`a`.`id` = `c`.`id`)))) `b` on((`a`.`id` = `b`.`commerceId`))) left join `ww_scm_dev`.`scm_zsj_commercedutyofficer` `c` on((`a`.`id` = `c`.`commerceId`))) left join `ww_scm_dev`.`scm_zsj_commercemerge` `d` on((`a`.`id` = `d`.`viceCommerceId`))) where isnull(`d`.`id`) */;

/*View structure for view v_commerceacceptstatedetails */

/*!50001 DROP TABLE IF EXISTS `v_commerceacceptstatedetails` */;
/*!50001 DROP VIEW IF EXISTS `v_commerceacceptstatedetails` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_commerceacceptstatedetails` AS select `a`.`masterId` AS `agtherId`,`a`.`commerceId` AS `commerceId`,`a`.`commerceCode` AS `commerceCode`,`a`.`acceptState` AS `acceptState`,`a`.`startDate` AS `startDate`,`a`.`endDate` AS `endDate`,`a`.`memo` AS `memo`,`b`.`commerceName` AS `commerceName`,`b`.`conpanyPersion` AS `conpanyPersion`,`b`.`businessDivision` AS `businessDivision`,`b`.`province` AS `province`,`b`.`enterDate` AS `enterDate` from (((select `a`.`id` AS `masterId`,`b`.`commerceId` AS `commerceId`,`b`.`commerceCode` AS `commerceCode`,`b`.`acceptState` AS `acceptState`,`b`.`startDate` AS `startDate`,`b`.`endDate` AS `endDate`,`b`.`memo` AS `memo` from (`ww_scm_dev`.`scm_zsj_commerceacceptstateagther` `a` left join `ww_scm_dev`.`scm_zsj_commerceacceptstate` `b` on((`a`.`id` = `b`.`agtherId`))))) `a` left join `ww_scm_dev`.`scm_zsj_commerce` `b` on((`a`.`commerceId` = `b`.`id`))) */;

/*View structure for view v_commerceacceptstatefirstlist */

/*!50001 DROP TABLE IF EXISTS `v_commerceacceptstatefirstlist` */;
/*!50001 DROP VIEW IF EXISTS `v_commerceacceptstatefirstlist` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_commerceacceptstatefirstlist` AS select `a`.`id` AS `id`,`a`.`agtherId` AS `agtherId`,`b`.`approvalCode` AS `approvalCode`,`b`.`ownerId` AS `ownerId`,`c`.`businessDivisionId` AS `businessDivisionId`,`c`.`businessDivision` AS `businessDivision`,`b`.`approvalId` AS `approvalId`,`b`.`approvalState` AS `approvalState`,(case `b`.`approvalState` when '0' then '待提交' when '1' then '审批中' when '2' then '已通过' else '未提交' end) AS `approvalStateStr`,`c`.`provinceId` AS `provinceId`,`c`.`province` AS `province`,`c`.`commerceName` AS `commerceName`,`c`.`commerceCode` AS `commerceCode`,ifnull(`d`.`dutyOfficer`,'无') AS `dutyOfficer`,`c`.`enterDate` AS `enterDate`,`a`.`acceptState` AS `acceptState`,`a`.`startDate` AS `startDate`,`a`.`endDate` AS `endDate`,`a`.`memo` AS `memo`,`a`.`commerceId` AS `commerceId`,(case `a`.`acceptState` when '0' then '认可' when '1' then '考察中' when '2' then '终止' else '未选择' end) AS `acceptStateStr` from (((`scm_zsj_commerceacceptstate` `a` left join `scm_zsj_commerceacceptstateagther` `b` on((`a`.`agtherId` = `b`.`id`))) left join `scm_zsj_commerce` `c` on((`a`.`commerceId` = `c`.`id`))) left join `scm_zsj_commercedutyofficer` `d` on(((`a`.`commerceId` = `d`.`commerceId`) and (`d`.`approvalState` = 2) and (`b`.`ownerId` = `d`.`ownerId`)))) */;

/*View structure for view v_commerceacceptts */

/*!50001 DROP TABLE IF EXISTS `v_commerceacceptts` */;
/*!50001 DROP VIEW IF EXISTS `v_commerceacceptts` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_commerceacceptts` AS select `a`.`id` AS `id`,`a`.`commerceName` AS `commerceName`,`a`.`fileCode` AS `fileCode`,`a`.`commerceState` AS `commerceState`,`a`.`commerceCode` AS `commerceCode`,`a`.`conpanyPersion` AS `conpanyPersion`,`a`.`contactPersion` AS `contactPersion`,`a`.`phone` AS `phone`,`a`.`openBank` AS `openBank`,`a`.`bankCode` AS `bankCode`,`a`.`dutyParagraph` AS `dutyParagraph`,`a`.`businessDivisionId` AS `businessDivisionId`,`a`.`businessDivision` AS `businessDivision`,`a`.`provinceId` AS `provinceId`,`a`.`province` AS `province`,`a`.`qualityValidity` AS `qualityValidity`,`a`.`registerAddress` AS `registerAddress`,`a`.`sealStyle` AS `sealStyle`,`a`.`billsStyle` AS `billsStyle`,`a`.`enterPersion` AS `enterPersion`,`a`.`enterDate` AS `enterDate`,`a`.`commerceLevel` AS `commerceLevel`,`a`.`functionType` AS `functionType`,`b`.`id` AS `acceptStateId`,`b`.`acceptState` AS `acceptState`,`b`.`effectiveDate` AS `effectiveDate`,`b`.`memo` AS `memo`,`b`.`ownerId` AS `ownerId` from (`scm_zsj_commerce` `a` left join `scm_zsj_commerceacceptstatets` `b` on((`a`.`id` = `b`.`commerceId`))) where (`b`.`id` is not null) */;

/*View structure for view v_commercefirstlist */

/*!50001 DROP TABLE IF EXISTS `v_commercefirstlist` */;
/*!50001 DROP VIEW IF EXISTS `v_commercefirstlist` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_commercefirstlist` AS select `a`.`id` AS `id`,`b`.`commerceId` AS `commerceId`,(case `b`.`approvalState` when '0' then '待提交' when '1' then '审批中' when '2' then '已经通过' else '未提交' end) AS `approvalStateStr`,(case `e`.`acceptState` when '0' then '认可' when '1' then '考察中' when '2' then '终止' else '未认可' end) AS `acceptStateStr`,ifnull(`e`.`acceptState`,'-1') AS `acceptState`,ifnull(`a`.`commerceCode`,'-') AS `commerceCode`,ifnull(`a`.`fileCode`,'-') AS `fileCode`,(case `a`.`commerceState` when '0' then '药品经营企业' when '1' then '医疗机构' else '无' end) AS `commerceStateStr`,ifnull(`a`.`commerceState`,'-1') AS `commerceState`,ifnull(`a`.`commerceName`,'-') AS `commerceName`,ifnull(`a`.`businessDivision`,'-') AS `businessDivision`,ifnull(`a`.`province`,'-') AS `province`,`a`.`businessDivisionId` AS `businessDivisionId`,`a`.`provinceId` AS `provinceId`,`d`.`legalPersion` AS `legalPersion`,`c`.`licenseKey` AS `licenseKey`,`c`.`validity` AS `validity`,`d`.`register` AS `register`,`d`.`licenseValidity` AS `licenseValidity`,`a`.`conpanyPersion` AS `conpanyPersion`,`d`.`registerPrice` AS `registerPrice`,`c`.`managementScope` AS `managementScope`,`a`.`enterPersion` AS `enterPersion`,`a`.`enterDate` AS `enterDate`,`b`.`operatorName` AS `operatorName`,`b`.`operatorDate` AS `operatorDate`,`b`.`id` AS `commerceFirstId`,`b`.`approvalState` AS `approvalState`,`b`.`ownerId` AS `ownerId`,`a`.`functionType` AS `functionType` from ((((`ww_scm_dev`.`scm_zsj_commerce` `a` join `ww_scm_dev`.`scm_zsj_commercefirst` `b` on((`a`.`id` = `b`.`commerceId`))) left join `ww_scm_dev`.`scm_zsj_commercelicence` `c` on((`b`.`id` = `c`.`commerceFirstId`))) left join `ww_scm_dev`.`scm_zsj_commercebusiness` `d` on((`b`.`id` = `d`.`commerceFirstId`))) left join (select `a`.`id` AS `id`,`a`.`agtherId` AS `agtherId`,`a`.`commerceId` AS `commerceId`,`a`.`commerceCode` AS `commerceCode`,`a`.`acceptState` AS `acceptState`,`a`.`startDate` AS `startDate`,`a`.`memo` AS `memo`,`a`.`endDate` AS `endDate` from (((select `a`.`id` AS `id`,`a`.`agtherId` AS `agtherId`,`a`.`commerceId` AS `commerceId`,`a`.`commerceCode` AS `commerceCode`,`a`.`acceptState` AS `acceptState`,`a`.`startDate` AS `startDate`,`a`.`memo` AS `memo`,`a`.`endDate` AS `endDate` from (`ww_scm_dev`.`scm_zsj_commerceacceptstate` `a` join (select max(`a`.`id`) AS `id`,`a`.`commerceId` AS `commerceId` from `ww_scm_dev`.`scm_zsj_commerceacceptstate` `a` group by `a`.`commerceId`) `b` on((`a`.`id` = `b`.`id`))))) `a` join `ww_scm_dev`.`scm_zsj_commerceacceptstateagther` `b` on(((`a`.`agtherId` = `b`.`id`) and (`b`.`approvalState` = 2))))) `e` on((`b`.`commerceId` = `e`.`commerceId`))) */;

/*View structure for view v_creditapply */

/*!50001 DROP TABLE IF EXISTS `v_creditapply` */;
/*!50001 DROP VIEW IF EXISTS `v_creditapply` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_creditapply` AS select `b`.`id` AS `id`,`b`.`businessDivision` AS `businessDivision`,`b`.`Province` AS `Province`,`b`.`businessDivisionId` AS `businessDivisionId`,`b`.`provinceId` AS `provinceId`,`b`.`commerceCode` AS `commerceCode`,`b`.`commerceName` AS `commerceName`,`b`.`marketWay` AS `marketWay`,`b`.`beforeYearPrice` AS `beforeYearPrice`,`b`.`lastYearPrice` AS `lastYearPrice`,`b`.`lastTimePrice` AS `lastTimePrice`,`b`.`sysPrice` AS `sysPrice`,`b`.`price` AS `price`,`b`.`memo` AS `memo`,`b`.`CreditCollectId` AS `CreditCollectId`,`b`.`year` AS `year`,`b`.`quarter` AS `quarter`,`a`.`id` AS `masterId`,`a`.`approvalId` AS `approvalId`,`a`.`approvalState` AS `approvalState`,`a`.`ownerId` AS `ownerId` from (`scm_xs_creditcollect` `a` left join `scm_xs_creditapply` `b` on((`a`.`id` = `b`.`CreditCollectId`))) */;

/*View structure for view v_creditapplyfirstlist */

/*!50001 DROP TABLE IF EXISTS `v_creditapplyfirstlist` */;
/*!50001 DROP VIEW IF EXISTS `v_creditapplyfirstlist` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_creditapplyfirstlist` AS select `a`.`id` AS `masterId`,`a`.`approvalId` AS `approvalId`,`a`.`approvalState` AS `approvalState`,(case `a`.`approvalState` when '0' then '待提交' when '1' then '审批中' when '2' then '已经通过' else '未提交' end) AS `approvalStateStr`,`b`.`id` AS `id`,`b`.`year` AS `year`,`b`.`quarter` AS `quarter`,`b`.`businessDivision` AS `businessDivision`,`b`.`businessDivisionId` AS `businessDivisionId`,`b`.`Province` AS `Province`,`b`.`provinceId` AS `provinceId`,`b`.`commerceCode` AS `commerceCode`,`b`.`commerceName` AS `commerceName`,`b`.`marketWay` AS `marketWay`,`b`.`price` AS `price`,`a`.`ownerId` AS `ownerId` from (`scm_xs_creditcollect` `a` left join `scm_xs_creditapply` `b` on((`a`.`id` = `b`.`CreditCollectId`))) */;

/*View structure for view v_dealerclause */

/*!50001 DROP TABLE IF EXISTS `v_dealerclause` */;
/*!50001 DROP VIEW IF EXISTS `v_dealerclause` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_dealerclause` AS select `a`.`id` AS `id`,(case `b`.`approvalState` when '0' then '待提交' when '1' then '审批中' when '2' then '已通过' else '未提交' end) AS `approvalStateStr`,`a`.`AgreementSummaryId` AS `AgreementSummaryId`,`a`.`clauseCode` AS `clauseCode`,`a`.`commerceName` AS `commerceName`,`a`.`functionType` AS `functionType`,`a`.`AgreementType` AS `AgreementType`,`a`.`AgreementStartDate` AS `AgreementStartDate`,`a`.`AgreementChangeDate` AS `AgreementChangeDate`,`a`.`AgreementEndDate` AS `AgreementEndDate`,`a`.`paymentType` AS `paymentType`,`a`.`returnMoneyDay` AS `returnMoneyDay`,`a`.`promiseProvince` AS `promiseProvince`,`a`.`messageStandard` AS `messageStandard`,`a`.`flowStandard` AS `flowStandard`,`a`.`transportationDiscount` AS `transportationDiscount`,`a`.`unloadPayer` AS `unloadPayer`,`a`.`subsidiarySum` AS `subsidiarySum`,`a`.`memo` AS `memo`,`a`.`isOverfulfil` AS `isOverfulfil`,`a`.`StardDay` AS `StardDay`,`a`.`endDay` AS `endDay`,`a`.`theApplication` AS `theApplication`,`a`.`isSpecial` AS `isSpecial`,`a`.`lastPaymentType` AS `lastPaymentType`,`a`.`lastReturnMoneyDay` AS `lastReturnMoneyDay`,`a`.`quarterPaymentPercent` AS `quarterPaymentPercent`,`a`.`commerceCode` AS `commerceCode`,`a`.`actualLevel` AS `actualLevel`,ifnull(`a`.`lastTheApplication`,0) AS `lasttheApplication`,`b`.`id` AS `masterId`,`b`.`businessDivision` AS `businessDivision`,`b`.`Province` AS `Province`,`b`.`businessDivisionId` AS `businessDivisionId`,`b`.`provinceId` AS `provinceId`,`b`.`approvalId` AS `approvalId`,`b`.`approvalState` AS `approvalState`,`b`.`ownerId` AS `ownerId`,`b`.`agreementSummaryCode` AS `agreementSummaryCode`,3 AS `discount` from (`scm_xs_dealerclause` `a` left join `scm_xs_agreementsummary` `b` on((`a`.`AgreementSummaryId` = `b`.`id`))) */;

/*View structure for view v_distributoracceptstate */

/*!50001 DROP TABLE IF EXISTS `v_distributoracceptstate` */;
/*!50001 DROP VIEW IF EXISTS `v_distributoracceptstate` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_distributoracceptstate` AS select `a`.`id` AS `commerceId`,`a`.`commerceName` AS `commerceName`,`a`.`fileCode` AS `fileCode`,`a`.`commerceState` AS `commerceState`,`a`.`commerceCode` AS `commerceCode`,`a`.`conpanyPersion` AS `conpanyPersion`,`a`.`contactPersion` AS `contactPersion`,`a`.`phone` AS `phone`,`a`.`openBank` AS `openBank`,`a`.`bankCode` AS `bankCode`,`a`.`dutyParagraph` AS `dutyParagraph`,`a`.`businessDivisionId` AS `businessDivisionId`,`a`.`businessDivision` AS `businessDivision`,`a`.`provinceId` AS `provinceId`,`a`.`province` AS `province`,`a`.`qualityValidity` AS `qualityValidity`,`a`.`registerAddress` AS `registerAddress`,`a`.`sealStyle` AS `sealStyle`,`a`.`billsStyle` AS `billsStyle`,`a`.`enterPersion` AS `enterPersion`,`a`.`enterDate` AS `enterDate`,`a`.`commerceLevel` AS `commerceLevel`,`a`.`functionType` AS `functionType`,sum(0) AS `integral`,`c`.`ownerId` AS `ownerId`,(case `b`.`acceptState` when '0' then '认可' when '1' then '考察中' when '2' then '终止' else '未处理' end) AS `scm_zsj_commerceState` from ((`scm_zsj_commerce` `a` left join `scm_zsj_commerceacceptstate` `b` on((`a`.`id` = `b`.`commerceId`))) left join `scm_zsj_commerceacceptstateagther` `c` on((`b`.`agtherId` = `c`.`id`))) where (`a`.`commerceLevel` = '分销商') */;

/*View structure for view v_distributorfirstlist */

/*!50001 DROP TABLE IF EXISTS `v_distributorfirstlist` */;
/*!50001 DROP VIEW IF EXISTS `v_distributorfirstlist` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_distributorfirstlist` AS select `a`.`id` AS `id`,`a`.`commerceId` AS `commerceId`,`a`.`clauseCode` AS `clauseCode`,`a`.`commerceName` AS `commerceName`,`a`.`commerceLevel` AS `commerceLevel`,`a`.`functionType` AS `functionType`,`a`.`isSubsidiary` AS `isSubsidiary`,`a`.`starLevel` AS `starLevel`,`a`.`messageCost` AS `messageCost`,`a`.`isPromotional` AS `isPromotional`,`a`.`startDate` AS `startDate`,`a`.`endDate` AS `endDate`,`a`.`provincePlatformId` AS `provincePlatformId`,`a`.`upCommerceId` AS `upCommerceId`,`a`.`memo` AS `memo`,`a`.`DistributorAgreementId` AS `DistributorAgreementId`,date_format(`a`.`startDate`,'%Y') AS `year`,`b`.`businessDivision` AS `businessDivision`,`b`.`Province` AS `Province`,`b`.`businessDivisionId` AS `businessDivisionId`,`b`.`provinceId` AS `provinceId`,`b`.`approvalId` AS `approvalId`,`b`.`approvalState` AS `approvalState`,`b`.`ownerId` AS `ownerId`,`b`.`agreementSummaryCode` AS `agreementSummaryCode`,(case `b`.`approvalState` when '0' then '待提交' when '1' then '审批中' when '2' then '已通过' else '未提交' end) AS `approvalStateStr` from (`scm_xs_distributorclause` `a` join `scm_xs_distributoragreementsummary` `b` on((`a`.`DistributorAgreementId` = `b`.`id`))) */;

/*View structure for view v_productfirstlist */

/*!50001 DROP TABLE IF EXISTS `v_productfirstlist` */;
/*!50001 DROP VIEW IF EXISTS `v_productfirstlist` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_productfirstlist` AS select `a`.`id` AS `id`,`a`.`productNum` AS `productNum`,`a`.`MedicineType` AS `MedicineType`,`a`.`productName` AS `productName`,`a`.`commonName` AS `commonName`,`a`.`unit` AS `unit`,`a`.`productState` AS `productState`,`a`.`AgentType` AS `AgentType`,`a`.`ProductLineId` AS `ProductLineId`,`a`.`ProductLineName` AS `ProductLineName`,`b`.`approvalCode` AS `approvalCode`,`b`.`gmp` AS `gmp`,`b`.`gmpValidity` AS `GMPValidity`,`b`.`businessScope` AS `businessScope`,`b`.`approvalState` AS `approvalState`,`b`.`id` AS `productFirstId`,`b`.`ownerId` AS `ownerId`,`b`.`approvalId` AS `approvalId`,`b`.`supplier` AS `supplier`,(case `b`.`approvalState` when '0' then '待提交' when '1' then '审批中' when '2' then '已通过' else '未提交' end) AS `approvalStateStr` from (`scm_zsj_product` `a` join `scm_zsj_productfirst` `b` on((`a`.`id` = `b`.`productId`))) */;

/*View structure for view v_supplierfirstlist */

/*!50001 DROP TABLE IF EXISTS `v_supplierfirstlist` */;
/*!50001 DROP VIEW IF EXISTS `v_supplierfirstlist` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_supplierfirstlist` AS select (case `b`.`approvalState` when '0' then '待提交' when '1' then '审批中' when '2' then '已经通过' else '待提交' end) AS `approvalStateStr`,ifnull(`a`.`supplierCode`,'-') AS `supplierCode`,ifnull(`a`.`fileCode`,'-') AS `fileCode`,ifnull(`a`.`supplierName`,'-') AS `supplierName`,`a`.`id` AS `id`,`d`.`legalPersion` AS `legalPersion`,`c`.`licenseKey` AS `licenseKey`,`c`.`validity` AS `validity`,`d`.`registerCode` AS `registerCode`,`d`.`licenseValidity` AS `licenseValidity`,`a`.`conpanyPersion` AS `conpanyPersion`,`d`.`registerPrice` AS `registerPrice`,`c`.`managementScope` AS `managementScope`,`a`.`enterPersion` AS `enterPersion`,`a`.`enterDate` AS `enterDate`,`b`.`operatorName` AS `operatorName`,`b`.`operatorDate` AS `operatorDate`,`b`.`ownerId` AS `ownerId`,`b`.`approvalState` AS `approvalState`,`b`.`approvalId` AS `approvalId`,`b`.`id` AS `supplierFirstId` from (((`scm_zsj_supplier` `a` join `scm_zsj_supplierfirst` `b` on((`a`.`id` = `b`.`supplierId`))) left join `scm_zsj_supplierlicence` `c` on((`b`.`id` = `c`.`supplierFirstId`))) left join `scm_zsj_supplierbusiness` `d` on((`b`.`id` = `d`.`supplierFirstId`))) */;

/*View structure for view v_updateagreementlist */

/*!50001 DROP TABLE IF EXISTS `v_updateagreementlist` */;
/*!50001 DROP VIEW IF EXISTS `v_updateagreementlist` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%` SQL SECURITY DEFINER VIEW `v_updateagreementlist` AS select `a`.`id` AS `id`,`a`.`commerceName` AS `commerceName`,`a`.`fileCode` AS `fileCode`,`a`.`commerceState` AS `commerceState`,`a`.`commerceCode` AS `commerceCode`,`a`.`conpanyPersion` AS `conpanyPersion`,`a`.`contactPersion` AS `contactPersion`,`a`.`phone` AS `phone`,`a`.`openBank` AS `openBank`,`a`.`bankCode` AS `bankCode`,`a`.`dutyParagraph` AS `dutyParagraph`,`a`.`businessDivisionId` AS `businessDivisionId`,`a`.`businessDivision` AS `businessDivision`,`a`.`provinceId` AS `provinceId`,`a`.`province` AS `province`,`a`.`qualityValidity` AS `qualityValidity`,`a`.`registerAddress` AS `registerAddress`,`a`.`sealStyle` AS `sealStyle`,`a`.`billsStyle` AS `billsStyle`,`a`.`enterPersion` AS `enterPersion`,`a`.`enterDate` AS `enterDate`,`a`.`commerceLevel` AS `commerceLevel`,`a`.`functionType` AS `functionType`,`a`.`ownerId` AS `ownerId`,`a`.`acceptState` AS `acceptState`,ifnull(`a`.`isSign`,'0') AS `lastYear`,ifnull(`a`.`shipments`,'0') AS `shipments`,ifnull(`a`.`allocating`,'0') AS `allocating`,ifnull(`a`.`sales`,'0') AS `sales` from (select `a`.`id` AS `id`,`a`.`commerceName` AS `commerceName`,`a`.`fileCode` AS `fileCode`,`a`.`commerceState` AS `commerceState`,`a`.`commerceCode` AS `commerceCode`,`a`.`conpanyPersion` AS `conpanyPersion`,`a`.`contactPersion` AS `contactPersion`,`a`.`phone` AS `phone`,`a`.`openBank` AS `openBank`,`a`.`bankCode` AS `bankCode`,`a`.`dutyParagraph` AS `dutyParagraph`,`a`.`businessDivisionId` AS `businessDivisionId`,`a`.`businessDivision` AS `businessDivision`,`a`.`provinceId` AS `provinceId`,`a`.`province` AS `province`,`a`.`qualityValidity` AS `qualityValidity`,`a`.`registerAddress` AS `registerAddress`,`a`.`sealStyle` AS `sealStyle`,`a`.`billsStyle` AS `billsStyle`,`a`.`enterPersion` AS `enterPersion`,`a`.`enterDate` AS `enterDate`,`a`.`commerceLevel` AS `commerceLevel`,`a`.`functionType` AS `functionType`,`d`.`ownerId` AS `ownerId`,(case `b`.`acceptState` when '0' then '认可' when '1' then '考察中' when '2' then '终止' else '未处理' end) AS `acceptState`,(select `ww_scm_dev`.`scm_xs_agreementlist`.`isSign` from `ww_scm_dev`.`scm_xs_agreementlist` where ((`ww_scm_dev`.`scm_xs_agreementlist`.`masterId` = (select `ww_scm_dev`.`scm_xs_agreementlistmaster`.`id` from `ww_scm_dev`.`scm_xs_agreementlistmaster` where (`ww_scm_dev`.`scm_xs_agreementlistmaster`.`isEffect` = '1'))) and (`ww_scm_dev`.`scm_xs_agreementlist`.`commerceCode` = `a`.`commerceCode`))) AS `isSign`,(select `ww_scm_dev`.`scm_xs_agreementlist`.`shipments` from `ww_scm_dev`.`scm_xs_agreementlist` where ((`ww_scm_dev`.`scm_xs_agreementlist`.`masterId` = (select `ww_scm_dev`.`scm_xs_agreementlistmaster`.`id` from `ww_scm_dev`.`scm_xs_agreementlistmaster` where (`ww_scm_dev`.`scm_xs_agreementlistmaster`.`isEffect` = '1'))) and (`ww_scm_dev`.`scm_xs_agreementlist`.`commerceCode` = `a`.`commerceCode`))) AS `shipments`,(select `ww_scm_dev`.`scm_xs_agreementlist`.`allocating` from `ww_scm_dev`.`scm_xs_agreementlist` where ((`ww_scm_dev`.`scm_xs_agreementlist`.`masterId` = (select `ww_scm_dev`.`scm_xs_agreementlistmaster`.`id` from `ww_scm_dev`.`scm_xs_agreementlistmaster` where (`ww_scm_dev`.`scm_xs_agreementlistmaster`.`isEffect` = '1'))) and (`ww_scm_dev`.`scm_xs_agreementlist`.`commerceCode` = `a`.`commerceCode`))) AS `allocating`,(select `ww_scm_dev`.`scm_xs_agreementlist`.`sales` from `ww_scm_dev`.`scm_xs_agreementlist` where ((`ww_scm_dev`.`scm_xs_agreementlist`.`masterId` = (select `ww_scm_dev`.`scm_xs_agreementlistmaster`.`id` from `ww_scm_dev`.`scm_xs_agreementlistmaster` where (`ww_scm_dev`.`scm_xs_agreementlistmaster`.`isEffect` = '1'))) and (`ww_scm_dev`.`scm_xs_agreementlist`.`commerceCode` = `a`.`commerceCode`))) AS `sales` from (((`ww_scm_dev`.`scm_zsj_commerce` `a` left join `ww_scm_dev`.`scm_zsj_commerceacceptstate` `b` on((`a`.`id` = `b`.`commerceId`))) left join `ww_scm_dev`.`scm_zsj_commerceacceptstateagther` `c` on((`b`.`agtherId` = `c`.`id`))) left join `ww_scm_dev`.`scm_zsj_commercefirst` `d` on((`a`.`id` = `d`.`commerceId`))) where ((`d`.`approvalState` = '2') and (`a`.`commerceLevel` = '经销商'))) `a` */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
