package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 物流协议表
 * <pre> 
 * 描述：物流协议表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmFhLogisticsAgreement",description = "物流协议表") 
public class ScmFhLogisticsAgreement extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="发运地")
	protected String shippedTo; 
	
	@ApiModelProperty(value="目的地")
	protected String destination; 
	
	@ApiModelProperty(value="最长到达日期")
	protected Integer maxArrivalTime; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	@ApiModelProperty(value="创建人")
	protected String createPersion; 
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate; 
	
	
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
	
	public void setShippedTo(String shippedTo) {
		this.shippedTo = shippedTo;
	}
	
	/**
	 * 返回 发运地
	 * @return
	 */
	public String getShippedTo() {
		return this.shippedTo;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	/**
	 * 返回 目的地
	 * @return
	 */
	public String getDestination() {
		return this.destination;
	}
	
	public void setMaxArrivalTime(Integer maxArrivalTime) {
		this.maxArrivalTime = maxArrivalTime;
	}
	
	/**
	 * 返回 最长到达日期
	 * @return
	 */
	public Integer getMaxArrivalTime() {
		return this.maxArrivalTime;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("shippedTo", this.shippedTo) 
		.append("destination", this.destination) 
		.append("maxArrivalTime", this.maxArrivalTime) 
		.append("memo", this.memo) 
		.append("createPersion", this.createPersion) 
		.append("createDate", this.createDate) 
		.toString();
	}
}