package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 预警设置
 * <pre> 
 * 描述：预警设置 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-18 15:11:34
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmGspEarlyWarningSet",description = "预警设置") 
public class ScmGspEarlyWarningSet extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="许可证天数")
	protected Integer licenceDay; 
	
	@ApiModelProperty(value="营业执照天数")
	protected Integer businessDay; 
	
	@ApiModelProperty(value="GSP天数")
	protected Integer gspDay; 
	
	@ApiModelProperty(value="GMP天数")
	protected Integer gmpDay; 
	
	@ApiModelProperty(value="商业委托书天数")
	protected Integer entrustBookDay; 
	
	@ApiModelProperty(value="药品资料保证协议天数")
	protected Integer dataGuaranteeDay; 
	
	@ApiModelProperty(value="商业库存情况天数")
	protected Integer commerceStockDay; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	
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
	
	public void setLicenceDay(Integer licenceDay) {
		this.licenceDay = licenceDay;
	}
	
	/**
	 * 返回 许可证天数
	 * @return
	 */
	public Integer getLicenceDay() {
		return this.licenceDay;
	}
	
	public void setBusinessDay(Integer businessDay) {
		this.businessDay = businessDay;
	}
	
	/**
	 * 返回 营业执照天数
	 * @return
	 */
	public Integer getBusinessDay() {
		return this.businessDay;
	}
	
	public void setGspDay(Integer gspDay) {
		this.gspDay = gspDay;
	}
	
	/**
	 * 返回 GSP天数
	 * @return
	 */
	public Integer getGspDay() {
		return this.gspDay;
	}
	
	public void setGmpDay(Integer gmpDay) {
		this.gmpDay = gmpDay;
	}
	
	/**
	 * 返回 GMP天数
	 * @return
	 */
	public Integer getGmpDay() {
		return this.gmpDay;
	}
	
	public void setEntrustBookDay(Integer entrustBookDay) {
		this.entrustBookDay = entrustBookDay;
	}
	
	/**
	 * 返回 商业委托书天数
	 * @return
	 */
	public Integer getEntrustBookDay() {
		return this.entrustBookDay;
	}
	
	public void setDataGuaranteeDay(Integer dataGuaranteeDay) {
		this.dataGuaranteeDay = dataGuaranteeDay;
	}
	
	/**
	 * 返回 药品资料保证协议天数
	 * @return
	 */
	public Integer getDataGuaranteeDay() {
		return this.dataGuaranteeDay;
	}
	
	public void setCommerceStockDay(Integer commerceStockDay) {
		this.commerceStockDay = commerceStockDay;
	}
	
	/**
	 * 返回 商业库存情况天数
	 * @return
	 */
	public Integer getCommerceStockDay() {
		return this.commerceStockDay;
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("licenceDay", this.licenceDay) 
		.append("businessDay", this.businessDay) 
		.append("gspDay", this.gspDay) 
		.append("gmpDay", this.gmpDay) 
		.append("entrustBookDay", this.entrustBookDay) 
		.append("dataGuaranteeDay", this.dataGuaranteeDay) 
		.append("commerceStockDay", this.commerceStockDay) 
		.append("ownerId", this.ownerId) 
		.toString();
	}
}