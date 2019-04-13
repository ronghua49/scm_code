package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 贮存条件
 * <pre> 
 * 描述：贮存条件 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
 @ApiModel(value = "ScmZdKeepingCondition",description = "贮存条件") 
public class ScmZdKeepingCondition extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="ID")
	protected String id; 
	
	@ApiModelProperty(value="贮存条件")
	protected String KeepingCondition; 
	
	@ApiModelProperty(value="建议库区")
	protected String suggestWarehouse; 
	
	@ApiModelProperty(value="编号")
	protected String code; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="创建人")
	protected String createPersion; 
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 ID
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setKeepingCondition(String KeepingCondition) {
		this.KeepingCondition = KeepingCondition;
	}
	
	/**
	 * 返回 贮存条件
	 * @return
	 */
	public String getKeepingCondition() {
		return this.KeepingCondition;
	}
	
	public void setSuggestWarehouse(String suggestWarehouse) {
		this.suggestWarehouse = suggestWarehouse;
	}
	
	/**
	 * 返回 建议库区
	 * @return
	 */
	public String getSuggestWarehouse() {
		return this.suggestWarehouse;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * 返回 备注
	 * @return
	 */
	public String getMemo() {
		return this.memo;
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
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 返回 货主ID
	 * @return
	 */
	public String getOwnerId() {
		return this.ownerId;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("KeepingCondition", this.KeepingCondition) 
		.append("suggestWarehouse", this.suggestWarehouse) 
		.append("memo", this.memo) 
		.append("createPersion", this.createPersion) 
		.append("createDate", this.createDate) 
		.append("ownerId", this.ownerId) 
		.toString();
	}
}