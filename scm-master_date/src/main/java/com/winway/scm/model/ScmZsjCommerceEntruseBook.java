package com.winway.scm.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 商业委托书
 * <pre> 
 * 描述：商业委托书 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjCommerceEntruseBook",description = "商业委托书") 
public class ScmZsjCommerceEntruseBook extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="首营ID")
	protected String commerceFirstId; 
	
	@ApiModelProperty(value="委托书编号")
	protected String entrustCode; 
	
	@ApiModelProperty(value="委托书姓名")
	protected String entrustName; 
	
	@ApiModelProperty(value="委托书有效期至")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected Date entrustValidity;
	
	@ApiModelProperty(value="身份证号")
	protected String idNumber;

	 @ApiModelProperty(value="身份证有效期")
	 @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	 protected Date idValidity;

	 /**
	*商业授权销售品种列表
	*/
	protected List<ScmZsjCommerceAccredit> scmZsjCommerceAccreditList=new ArrayList<ScmZsjCommerceAccredit>(); 
	
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
	
	public void setCommerceFirstId(String commerceFirstId) {
		this.commerceFirstId = commerceFirstId;
	}
	
	/**
	 * 返回 首营ID
	 * @return
	 */
	public String getCommerceFirstId() {
		return this.commerceFirstId;
	}
	
	public void setEntrustCode(String entrustCode) {
		this.entrustCode = entrustCode;
	}
	
	/**
	 * 返回 委托书编号
	 * @return
	 */
	public String getEntrustCode() {
		return this.entrustCode;
	}
	
	public void setEntrustName(String entrustName) {
		this.entrustName = entrustName;
	}
	
	/**
	 * 返回 委托书姓名
	 * @return
	 */
	public String getEntrustName() {
		return this.entrustName;
	}
	
	public void setEntrustValidity(java.util.Date entrustValidity) {
		this.entrustValidity = entrustValidity;
	}
	
	/**
	 * 返回 委托书有效期至
	 * @return
	 */
	public java.util.Date getEntrustValidity() {
		return this.entrustValidity;
	}

	 public String getIdNumber() {
		 return idNumber;
	 }

	 public void setIdNumber(String idNumber) {
		 this.idNumber = idNumber;
	 }

	 public Date getIdValidity() {
		 return idValidity;
	 }

	 public void setIdValidity(Date idValidity) {
		 this.idValidity = idValidity;
	 }

	 public void setScmZsjCommerceAccreditList(List<ScmZsjCommerceAccredit> scmZsjCommerceAccreditList) {
		this.scmZsjCommerceAccreditList = scmZsjCommerceAccreditList;
	}
	
	/**
	 * 返回 商业授权销售品种列表
	 * @return
	 */
	public List<ScmZsjCommerceAccredit> getScmZsjCommerceAccreditList() {
		return this.scmZsjCommerceAccreditList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("commerceFirstId", this.commerceFirstId) 
		.append("entrustCode", this.entrustCode) 
		.append("entrustName", this.entrustName) 
		.append("entrustValidity", this.entrustValidity) 
		.append("idNumber", this.idNumber) 
		.append("idValidity", this.idValidity)
		.toString();
	}
}