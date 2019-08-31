package com.winway.purchase.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * scm_sys_log
 * <pre> 
 * 描述：scm_sys_log 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-24 17:36:05
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmSysLog",description = "scm_sys_log") 
public class ScmSysLog extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键")
	protected String id; 
	
	@ApiModelProperty(value="操作用户")
	protected String loginUser; 
	
	@ApiModelProperty(value="异常消息")
	protected String exceptionMes; 
	
	@ApiModelProperty(value="操作描述")
	protected String operation; 
	
	@ApiModelProperty(value="请求方法")
	protected String method; 
	
	@ApiModelProperty(value="请求参数")
	protected String params; 
	
	@ApiModelProperty(value="请求ip")
	protected String ip; 
	
	@ApiModelProperty(value="请求时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date createDate;
	
	@ApiModelProperty(value="请求时长")
	protected Long totalTime; 
	
	@ApiModelProperty(value="请求状态")
	protected String responseState; 
	
	@ApiModelProperty(value="请求结果")
	protected String result; 
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 返回 主键
	 * @return
	 */
	public String getId() {
		return this.id;
	}
	
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	
	/**
	 * 返回 操作用户
	 * @return
	 */
	public String getLoginUser() {
		return this.loginUser;
	}
	
	public void setExceptionMes(String exceptionMes) {
		this.exceptionMes = exceptionMes;
	}
	
	/**
	 * 返回 异常消息
	 * @return
	 */
	public String getExceptionMes() {
		return this.exceptionMes;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	/**
	 * 返回 日志类型
	 * @return
	 */
	public String getOperation() {
		return this.operation;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	/**
	 * 返回 请求方法
	 * @return
	 */
	public String getMethod() {
		return this.method;
	}
	
	public void setParams(String params) {
		this.params = params;
	}
	
	/**
	 * 返回 请求参数
	 * @return
	 */
	public String getParams() {
		return this.params;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	 * 返回 请求ip
	 * @return
	 */
	public String getIp() {
		return this.ip;
	}
	
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 返回 请求时间
	 * @return
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}
	
	/**
	 * 返回 请求时长
	 * @return
	 */
	public Long getTotalTime() {
		return this.totalTime;
	}
	
	public void setResponseState(String responseState) {
		this.responseState = responseState;
	}
	
	/**
	 * 返回 请求状态
	 * @return
	 */
	public String getResponseState() {
		return this.responseState;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	/**
	 * 返回 请求结果
	 * @return
	 */
	public String getResult() {
		return this.result;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("loginUser", this.loginUser) 
		.append("exceptionMes", this.exceptionMes) 
		.append("operation", this.operation) 
		.append("method", this.method) 
		.append("params", this.params) 
		.append("ip", this.ip) 
		.append("createDate", this.createDate) 
		.append("totalTime", this.totalTime) 
		.append("responseState", this.responseState) 
		.append("result", this.result) 
		.toString();
	}
}