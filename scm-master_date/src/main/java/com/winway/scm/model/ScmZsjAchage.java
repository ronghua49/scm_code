package com.winway.scm.model;

import com.hotent.base.model.BaseModel;

import groovy.transform.ToString;
import io.swagger.annotations.ApiModel;
/**
 * 件装量表
 * <pre> 
 * 描述：件装量表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmZsjAchage",description = "件装量表") 
public class ScmZsjAchage extends BaseModel<String>{
	private static final long serialVersionUID = 1L;
	private String id;
	private String productId;
	private String productCode;
	private String achage;
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProductId() {
		return productId;
	}

	public void setProducrId(String productId) {
		this.productId = productId;
	}

	public String getAchage() {
		return achage;
	}

	public void setAchage(String achage) {
		this.achage = achage;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	

}
