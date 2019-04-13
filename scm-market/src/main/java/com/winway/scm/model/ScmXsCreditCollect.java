package com.winway.scm.model;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 商业资信申请总表
 * <pre> 
 * 描述：商业资信申请总表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmXsCreditCollect",description = "商业资信申请总表") 
public class ScmXsCreditCollect extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="货主ID")
	protected String ownerId; 
	
	@ApiModelProperty(value="流程ID")
	protected String approvalId; 
	
	@ApiModelProperty(value="流程状态")
	protected String approvalState; 
	
	@ApiModelProperty(value="申请单号")
	protected String requestCode; 
	
	/**
	*商业资信申请表列表
	*/
	protected List<ScmXsCreditApply> scmXsCreditApplyList=new ArrayList<ScmXsCreditApply>(); 
	
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
	
	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}
	
	/**
	 * 返回 流程ID
	 * @return
	 */
	public String getApprovalId() {
		return this.approvalId;
	}
	
	public void setApprovalState(String approvalState) {
		this.approvalState = approvalState;
	}
	
	/**
	 * 返回 流程状态
	 * @return
	 */
	public String getApprovalState() {
		return this.approvalState;
	}
	
	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}
	
	/**
	 * 返回 requestCode
	 * @return
	 */
	public String getRequestCode() {
		return this.requestCode;
	}

	public void setScmXsCreditApplyList(List<ScmXsCreditApply> scmXsCreditApplyList) {
		this.scmXsCreditApplyList = scmXsCreditApplyList;
	}
	
	/**
	 * 返回 商业资信申请表列表
	 * @return
	 */
	public List<ScmXsCreditApply> getScmXsCreditApplyList() {
		return this.scmXsCreditApplyList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("ownerId", this.ownerId) 
		.append("approvalId", this.approvalId) 
		.append("approvalState", this.approvalState) 
		.append("requestCode", this.requestCode) 
		.toString();
	}
}