package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 经销商协议特殊商品表
 * <pre> 
 * 描述：经销商协议特殊商品表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmXsAgreementSpecialProduct",description = "经销商协议特殊商品表") 
public class ScmXsAgreementSpecialProduct extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="协议ID")
	protected String AgreementId; 
	
	@ApiModelProperty(value="商品首营ID")
	protected String ProductFirstId; 
	
	@ApiModelProperty(value="商品名")
	protected String productName; 
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 id
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setAgreementId(String AgreementId) {
		this.AgreementId = AgreementId;
	}
	
	/**
	 * 返回 协议ID
	 * @return
	 */
	public String getAgreementId() {
		return this.AgreementId;
	}
	
	public void setProductFirstId(String ProductFirstId) {
		this.ProductFirstId = ProductFirstId;
	}
	
	/**
	 * 返回 商品首营ID
	 * @return
	 */
	public String getProductFirstId() {
		return this.ProductFirstId;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 返回 商品名
	 * @return
	 */
	public String getProductName() {
		return this.productName;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("AgreementId", this.AgreementId) 
		.append("ProductFirstId", this.ProductFirstId) 
		.append("productName", this.productName) 
		.toString();
	}
}