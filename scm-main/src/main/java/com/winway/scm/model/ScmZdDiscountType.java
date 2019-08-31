package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 票折类型表
 * <pre> 
 * 描述：票折类型表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-27 11:25:26
 * 版权：美达项目组
 * </pre>
 */
 @ApiModel(value = "ScmZdDiscountType",description = "票折类型表") 
public class ScmZdDiscountType extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="票折类型")
	protected String discountType; 
	
	@ApiModelProperty(value="创建人")
	protected String createPersion; 
	
	@ApiModelProperty(value="票折类型编码")
	protected String code; 
	
	@ApiModelProperty(value="创建时间")
	protected java.util.Date createDate; 
	
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
	
	public void setCreatePersion(String createPersion) {
		this.createPersion = createPersion;
	}
	
	/**
	 * 返回 创建人
	 * @return
	 */
	public String getCreatePersion() {
		return this.createPersion;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("discountType", this.discountType) 
		.append("createPersion", this.createPersion) 
		.append("createDate", this.createDate) 
		.toString();
	}
}