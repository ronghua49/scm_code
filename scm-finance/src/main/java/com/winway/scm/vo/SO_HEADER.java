package com.winway.scm.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class SO_HEADER {
	@JSONField(name = "DOC_TYPE")
	@ApiModelProperty(value = "单据类型 销售ZORS, 退货：ZRE")
	private String DOC_TYPE = "ZORS";
	
	@JSONField(name = "SALES_ORG")
	@ApiModelProperty(value = "销售组织 默认:6888")
	private String SALES_ORG = "6888";
	
	@JSONField(name = "PURCH_NO_C")
	@ApiModelProperty(value = "合同号")
	private String PURCH_NO_C;
	
	@JSONField(name = "PARTN_NUMB")
	@ApiModelProperty(value = "客户号")
	private String PARTN_NUMB;
	
	@JSONField(name = "ORD_REASON")
	@ApiModelProperty(value = "订单原因  例：Z3")
	private String ORD_REASON;
	
	@JSONField(name = "HEAD_TXT")
	@ApiModelProperty(value = "票折原因 ")
	private String HEAD_TXT;
	
	@JSONField(name = "PDATE")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "单据日期")
	private Date PDATE;
	
	@JSONField(name = "SO_DETIAL")
	@ApiModelProperty(value = "商品信息")
	List<SO_DETIAL> SO_DETIAL = new ArrayList<SO_DETIAL>();

	@JSONField(name = "MATERIAL") 
	public String getDOC_TYPE() {
		return DOC_TYPE;
	}

	public void setDOC_TYPE(String dOC_TYPE) {
		DOC_TYPE = dOC_TYPE;
	}
	@JSONField(name = "SALES_ORG") 
	public String getSALES_ORG() {
		return SALES_ORG;
	}

	public void setSALES_ORG(String sALES_ORG) {
		SALES_ORG = sALES_ORG;
	}
	@JSONField(name = "PURCH_NO_C") 
	public String getPURCH_NO_C() {
		return PURCH_NO_C;
	}

	public void setPURCH_NO_C(String pURCH_NO_C) {
		PURCH_NO_C = pURCH_NO_C;
	}
	@JSONField(name = "PARTN_NUMB") 
	public String getPARTN_NUMB() {
		return PARTN_NUMB;
	}

	public void setPARTN_NUMB(String pARTN_NUMB) {
		PARTN_NUMB = pARTN_NUMB;
	}
	@JSONField(name = "ORD_REASON") 
	public String getORD_REASON() {
		return ORD_REASON;
	}

	public void setORD_REASON(String oRD_REASON) {
		ORD_REASON = oRD_REASON;
	}
	@JSONField(name = "HEAD_TXT") 
	public String getHEAD_TXT() {
		return HEAD_TXT;
	}

	public void setHEAD_TXT(String hEAD_TXT) {
		HEAD_TXT = hEAD_TXT;
	}
	@JSONField(name = "PDATE") 
	public Date getPDATE() {
		return PDATE;
	}

	public void setPDATE(Date pDATE) {
		PDATE = pDATE;
	}
	@JSONField(name = "SO_DETIAL") 
	public List<SO_DETIAL> getSO_DETIAL() {
		return SO_DETIAL;
	}

	public void setSO_DETIAL(List<SO_DETIAL> sO_DETIAL) {
		SO_DETIAL = sO_DETIAL;
	}

}
