package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 勾兑记录表
 * <pre> 
 * 描述：勾兑记录表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmCwBlend",description = "勾兑记录表") 
public class ScmCwBlend extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="回款表ID")
	protected String returnMoneyId; 
	
	@ApiModelProperty(value="发票表ID")
	protected String invoiceId; 
	
	@ApiModelProperty(value="勾兑金额")
	protected Double blendprice; 
	
	@ApiModelProperty(value="勾兑人")
	protected String blendPersion; 
	
	@ApiModelProperty(value="勾兑时间")
	protected java.util.Date blendDate; 
	
	@ApiModelProperty(value="确认人")
	protected String affirmPersion; 
	
	@ApiModelProperty(value="确认时间")
	protected java.util.Date affirmDate; 
	
	@ApiModelProperty(value="勾兑状态(0:取消,1:同意,2操作中)")
	protected String blendType; 
	
	@ApiModelProperty(value="核销备注")
	protected String memo; 
	
	
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
	
	public void setReturnMoneyId(String returnMoneyId) {
		this.returnMoneyId = returnMoneyId;
	}
	
	/**
	 * 返回 回款表ID
	 * @return
	 */
	public String getReturnMoneyId() {
		return this.returnMoneyId;
	}
	
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	/**
	 * 返回 发票表ID
	 * @return
	 */
	public String getInvoiceId() {
		return this.invoiceId;
	}
	
	public void setBlendprice(Double blendprice) {
		this.blendprice = blendprice;
	}
	
	/**
	 * 返回 勾兑金额
	 * @return
	 */
	public Double getBlendprice() {
		return this.blendprice;
	}
	
	public void setBlendPersion(String blendPersion) {
		this.blendPersion = blendPersion;
	}
	
	/**
	 * 返回 勾兑人
	 * @return
	 */
	public String getBlendPersion() {
		return this.blendPersion;
	}
	
	public void setBlendDate(java.util.Date blendDate) {
		this.blendDate = blendDate;
	}
	
	/**
	 * 返回 勾兑时间
	 * @return
	 */
	public java.util.Date getBlendDate() {
		return this.blendDate;
	}
	
	public void setAffirmPersion(String affirmPersion) {
		this.affirmPersion = affirmPersion;
	}
	
	/**
	 * 返回 确认人
	 * @return
	 */
	public String getAffirmPersion() {
		return this.affirmPersion;
	}
	
	public void setAffirmDate(java.util.Date affirmDate) {
		this.affirmDate = affirmDate;
	}
	
	/**
	 * 返回 确认时间
	 * @return
	 */
	public java.util.Date getAffirmDate() {
		return this.affirmDate;
	}
	
	public void setBlendType(String blendType) {
		this.blendType = blendType;
	}
	
	/**
	 * 返回 勾兑状态(0:取消,1:同意,2操作中)
	 * @return
	 */
	public String getBlendType() {
		return this.blendType;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("returnMoneyId", this.returnMoneyId) 
		.append("invoiceId", this.invoiceId) 
		.append("blendprice", this.blendprice) 
		.append("blendPersion", this.blendPersion) 
		.append("blendDate", this.blendDate) 
		.append("affirmPersion", this.affirmPersion) 
		.append("affirmDate", this.affirmDate) 
		.append("blendType", this.blendType) 
		.toString();
	}
}