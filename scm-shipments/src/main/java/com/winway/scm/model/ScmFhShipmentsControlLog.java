package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 发货控制日志记录
 * <pre> 
 * 描述：发货控制日志记录 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmFhShipmentsControlLog",description = "发货控制日志记录") 
public class ScmFhShipmentsControlLog extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="操作人")
	protected String operatorName; 
	
	@ApiModelProperty(value="操作日期")
	protected java.util.Date operatorDate; 
	
	@ApiModelProperty(value="操作内容")
	protected String operatormemo; 
	
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
	
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	/**
	 * 返回 操作人
	 * @return
	 */
	public String getOperatorName() {
		return this.operatorName;
	}
	
	public void setOperatorDate(java.util.Date operatorDate) {
		this.operatorDate = operatorDate;
	}
	
	/**
	 * 返回 操作日期
	 * @return
	 */
	public java.util.Date getOperatorDate() {
		return this.operatorDate;
	}
	
	public void setOperatormemo(String operatormemo) {
		this.operatormemo = operatormemo;
	}
	
	/**
	 * 返回 操作内容
	 * @return
	 */
	public String getOperatormemo() {
		return this.operatormemo;
	}
	
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("operatorName", this.operatorName) 
		.append("operatorDate", this.operatorDate) 
		.append("operatormemo", this.operatormemo) 
		.toString();
	}
}