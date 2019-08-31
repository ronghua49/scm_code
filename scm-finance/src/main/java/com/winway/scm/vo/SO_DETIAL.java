package com.winway.scm.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class SO_DETIAL {
	@JSONField(name = "MATERIAL")
	@ApiModelProperty(value = "产品号")
	private String MATERIAL;
	
	@JSONField(name ="BATCH" )
	@ApiModelProperty(value = "批号")
	private String BATCH;
	
	@JSONField(name = "STORE_LOC")
	@ApiModelProperty(value = "仓库")
	private String STORE_LOC;
	
	@JSONField(name = "REQ_QTY")
	@ApiModelProperty(value = "数量")
	private int REQ_QTY;
	
	@JSONField(name ="PRICE" )
	@ApiModelProperty(value = "价格")
	private double PRICE;
	
	@JSONField(name = "AMT1")
	@ApiModelProperty(value = "促销票折")
	private double AMT1;
	
	@JSONField(name = "AMT2")
	@ApiModelProperty(value = "返利票折")
	private double AMT2;
	
	@JSONField(name = "AMT3")
	@ApiModelProperty(value = "补差票折")
	private double AMT3;
	
	@JSONField(name = "TXT1")
	@ApiModelProperty(value = "流程号")
	private String TXT1;
	
	@JSONField(name = "TXT2")
	@ApiModelProperty(value = "合同单号")
	private String TXT2;

	@JSONField(name = "PRODUCTNAME")
	@ApiModelProperty(value = "商品名称")
	private String PRODUCTNAME;
	
	@JSONField(name = "PRODUCTCODE")
	@ApiModelProperty(value = "商品子码")
	private String PRODUCTCODE;
	
	@JSONField(name = "PRODUCTNAME") 
	public String getPRODUCTNAME() {
		return PRODUCTNAME;
	}
	public void setPRODUCTNAME(String pRODUCTNAME) {
		PRODUCTNAME = pRODUCTNAME;
	}
	@JSONField(name = "PRODUCTCODE") 
	public String getPRODUCTCODE() {
		return PRODUCTCODE;
	}
	public void setPRODUCTCODE(String pRODUCTCODE) {
		PRODUCTCODE = pRODUCTCODE;
	}
	@JSONField(name = "MATERIAL") 
	public String getMATERIAL() {
		return MATERIAL;
	}
	@JSONField(name = "TXT2") 
	public String getTXT2() {
		return TXT2;
	}

	public void setTXT2(String tXT2) {
		TXT2 = tXT2;
	}


	public void setMATERIAL(String mATERIAL) {
		MATERIAL = mATERIAL;
	}
	@JSONField(name = "BATCH") 
	public String getBATCH() {
		return BATCH;
	}

	public void setBATCH(String bATCH) {
		BATCH = bATCH;
	}
	@JSONField(name = "STORE_LOC") 
	public String getSTORE_LOC() {
		return STORE_LOC;
	}

	public void setSTORE_LOC(String sTORE_LOC) {
		STORE_LOC = sTORE_LOC;
	}
	@JSONField(name = "REQ_QTY") 
	public int getREQ_QTY() {
		return REQ_QTY;
	}

	public void setREQ_QTY(int rEQ_QTY) {
		REQ_QTY = rEQ_QTY;
	}
	@JSONField(name = "PRICE") 
	public double getPRICE() {
		return PRICE;
	}

	public void setPRICE(double pRICE) {
		PRICE = pRICE;
	}
	@JSONField(name = "AMT1") 
	public double getAMT1() {
		return AMT1;
	}

	public void setAMT1(double aMT1) {
		AMT1 = aMT1;
	}
	@JSONField(name = "AMT2") 
	public double getAMT2() {
		return AMT2;
	}

	public void setAMT2(double aMT2) {
		AMT2 = aMT2;
	}
	@JSONField(name = "AMT3") 
	public double getAMT3() {
		return AMT3;
	}

	public void setAMT3(double aMT3) {
		AMT3 = aMT3;
	}
	@JSONField(name = "TXT1") 
	public String getTXT1() {
		return TXT1;
	}

	public void setTXT1(String tXT1) {
		TXT1 = tXT1;
	}
	
}
