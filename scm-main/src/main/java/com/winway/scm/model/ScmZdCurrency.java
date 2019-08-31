package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 币种
 * <pre> 
 * 描述：币种 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-16 22:35:42
 * 版权：美达项目组
 * </pre>
 */
 @ApiModel(value = "ScmZdCurrency",description = "币种") 
public class ScmZdCurrency extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="币种")
	protected String currency; 
	
	@ApiModelProperty(value="英文简写")
	protected String currencyCode; 
	
	@ApiModelProperty(value="创建时间")
	protected java.util.Date createDate; 
	
	@ApiModelProperty(value="创建人")
	protected String createPersion; 
	
	@ApiModelProperty(value="备注")
	protected String memo; 
	
	
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
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	/**
	 * 返回 币种
	 * @return
	 */
	public String getCurrency() {
		return this.currency;
	}
	
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	/**
	 * 返回 英文简写
	 * @return
	 */
	public String getCurrencyCode() {
		return this.currencyCode;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("currency", this.currency) 
		.append("currencyCode", this.currencyCode) 
		.append("createDate", this.createDate) 
		.append("createPersion", this.createPersion) 
		.append("memo", this.memo) 
		.toString();
	}
}