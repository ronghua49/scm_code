package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * scm_cw_discountrulerecord
 * <pre> 
 * 描述：scm_cw_discountrulerecord 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-27 14:18:12
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwDiscountRuleRecord",description = "scm_cw_discountrulerecord") 
public class ScmCwDiscountRuleRecord extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商业id")
	protected String commerceId; 
	
	@ApiModelProperty(value="商品id")
	protected String productId; 
	
	@ApiModelProperty(value="实际折扣数量")
	protected Integer discountNum; 
	
	@ApiModelProperty(value="票折规则id")
	protected String discountid; 
	
	
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
	
	public void setCommerceId(String commerceId) {
		this.commerceId = commerceId;
	}
	
	/**
	 * 返回 商业id
	 * @return
	 */
	public String getCommerceId() {
		return this.commerceId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	/**
	 * 返回 商品id
	 * @return
	 */
	public String getProductId() {
		return this.productId;
	}
	
	public void setDiscountNum(Integer discountNum) {
		this.discountNum = discountNum;
	}
	
	/**
	 * 返回 实际折扣数量
	 * @return
	 */
	public Integer getDiscountNum() {
		return this.discountNum;
	}
	
	public void setDiscountid(String discountid) {
		this.discountid = discountid;
	}
	
	/**
	 * 返回 票折规则id
	 * @return
	 */
	public String getDiscountid() {
		return this.discountid;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("commerceId", this.commerceId) 
		.append("productId", this.productId) 
		.append("discountNum", this.discountNum) 
		.append("discountid", this.discountid) 
		.toString();
	}
}