package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 动态票折商品可票折类型
 * <pre> 
 * 描述：动态票折商品可票折类型 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-16 17:06:30
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwDynamicDiscountType",description = "动态票折商品可票折类型") 
public class ScmCwDynamicDiscountType extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="动态票折ID")
	protected String dynamicDiscountId; 
	
	@ApiModelProperty(value="票折类型ID")
	protected String discountTypeId; 
	
	@ApiModelProperty(value="票折类型")
	protected String discountType; 
	
	@ApiModelProperty(value="票折类型编码")
	protected String code; 
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
	
	public void setDynamicDiscountId(String dynamicDiscountId) {
		this.dynamicDiscountId = dynamicDiscountId;
	}
	
	/**
	 * 返回 动态票折ID
	 * @return
	 */
	public String getDynamicDiscountId() {
		return this.dynamicDiscountId;
	}
	
	public void setDiscountTypeId(String discountTypeId) {
		this.discountTypeId = discountTypeId;
	}
	
	/**
	 * 返回 票折类型ID
	 * @return
	 */
	public String getDiscountTypeId() {
		return this.discountTypeId;
	}
	
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	
	/**
	 * 返回 票折类型
	 * @return
	 */
	public String getDiscountType() {
		return this.discountType;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("dynamicDiscountId", this.dynamicDiscountId) 
		.append("discountTypeId", this.discountTypeId) 
		.append("discountType", this.discountType) 
		.toString();
	}
}