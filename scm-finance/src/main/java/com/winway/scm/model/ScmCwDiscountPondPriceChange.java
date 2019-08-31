package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 票折池金额变动记录表
 * <pre> 
 * 描述：票折池金额变动记录表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-08-17 10:03:20
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmCwDiscountPondPriceChange",description = "票折池金额变动记录表") 
public class ScmCwDiscountPondPriceChange extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="票折池ID",required = true)
	protected String discountpondId; 
	
	@ApiModelProperty(value="变动前金额")
	protected Double changeFrontPrice; 
	
	@ApiModelProperty(value="变动金额",required = true)
	protected Double changePrice; 
	
	@ApiModelProperty(value="变动后金额")
	protected Double changeBackPrice; 
	
	@ApiModelProperty(value="变动时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date changeDate; 
	
	@ApiModelProperty(value="变动人")
	protected String changePersion; 
	
	@ApiModelProperty(value="变动原因")
	protected String changeCause;
	
	
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
	
	public void setDiscountpondId(String discountpondId) {
		this.discountpondId = discountpondId;
	}
	
	/**
	 * 返回 票折池ID
	 * @return
	 */
	public String getDiscountpondId() {
		return this.discountpondId;
	}
	
	public void setChangeFrontPrice(Double changeFrontPrice) {
		this.changeFrontPrice = changeFrontPrice;
	}
	
	/**
	 * 返回 变动前金额
	 * @return
	 */
	public Double getChangeFrontPrice() {
		return this.changeFrontPrice;
	}
	
	public void setChangePrice(Double changePrice) {
		this.changePrice = changePrice;
	}
	
	/**
	 * 返回 变动金额
	 * @return
	 */
	public Double getChangePrice() {
		return this.changePrice;
	}
	
	public void setChangeBackPrice(Double changeBackPrice) {
		this.changeBackPrice = changeBackPrice;
	}
	
	/**
	 * 返回 变动后金额
	 * @return
	 */
	public Double getChangeBackPrice() {
		return this.changeBackPrice;
	}
	
	public void setChangeDate(java.util.Date changeDate) {
		this.changeDate = changeDate;
	}
	
	/**
	 * 返回 变动时间
	 * @return
	 */
	public java.util.Date getChangeDate() {
		return this.changeDate;
	}
	
	public void setChangePersion(String changePersion) {
		this.changePersion = changePersion;
	}
	
	/**
	 * 返回 变动人
	 * @return
	 */
	public String getChangePersion() {
		return this.changePersion;
	}
	
	public void setChangeCause(String changeCause) {
		this.changeCause = changeCause;
	}
	
	/**
	 * 返回 变动原因
	 * @return
	 */
	public String getChangeCause() {
		return this.changeCause;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("discountpondId", this.discountpondId) 
		.append("changeFrontPrice", this.changeFrontPrice) 
		.append("changePrice", this.changePrice) 
		.append("changeBackPrice", this.changeBackPrice) 
		.append("changeDate", this.changeDate) 
		.append("changePersion", this.changePersion) 
		.append("changeCause", this.changeCause) 
		.toString();
	}
}