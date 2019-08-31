package com.winway.purchase.tool;

import com.winway.purchase.util.passparameter;

public class ScmWmsConfiguration {
	
	//主数据中心正式环境
//	public static String MASTER_DATE_URL = "https://masterservice.hkbf.com.cn";
	//UAT环境
	public static String MASTER_DATE_URL = "http://172.18.1.140:8091";
	//主数据中心测试环境
//	public static String MASTER_DATE_URL = "http://172.18.1.213:8091";
	public static String MASTER_TOKEN = "Basic YnBtYWRtaW46YnBtQWRtaW5AMjAxOQ==";
	//WMS正式环境
//	public static String url = "http://172.17.16.90:7080";
	//WMS测试环境
	public static String url = "http://172.17.16.11:7080";
	public static String username = "73101348649789";
	public static String password = "9014b0e04c9ecc6064eb01f5e369cb02";
	public static String token = "Basic NzMxMDEzNDg2NDk3ODk6OTAxNGIwZTA0YzllY2M2MDY0ZWIwMWY1ZTM2OWNiMDI=";
	//SAP测试环境
	public static String SAP_URL = "http://sapqas01.brightfuture.com.hk:8000";
	public static passparameter passparameter = new passparameter("EIPUSER","EIPUSER");
}
