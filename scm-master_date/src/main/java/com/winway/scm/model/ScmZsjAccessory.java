package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotent.base.model.BaseModel;


 /**
 * 附件表
 * <pre> 
 * 描述：附件表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjAccessory",description = "附件表") 
public class ScmZsjAccessory extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="类型(0:供应商首营,1:商业首营,...)")
	protected String type; 
	
	@ApiModelProperty(value="文件URL")
	protected String fileUrl; 
	
	@ApiModelProperty(value="上传时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	protected java.util.Date updateDate; 
	
	@ApiModelProperty(value="文件ID")
	protected String fileId; 
	
	@ApiModelProperty(value="数据ID")
	protected String dateId; 
	
	@ApiModelProperty(value="文件名称")
	protected String fileName; 
	
	
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 返回 类型(0:供应商首营,1:商业首营,...)
	 * @return
	 */
	public String getType() {
		return this.type;
	}
	
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	/**
	 * 返回 文件URL
	 * @return
	 */
	public String getFileUrl() {
		return this.fileUrl;
	}
	
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}
	
	/**
	 * 返回 上传时间
	 * @return
	 */
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}
	
	public void setDateId(String dateId) {
		this.dateId = dateId;
	}
	
	/**
	 * 返回 数据ID
	 * @return
	 */
	public String getDateId() {
		return this.dateId;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * 返回 文件名称
	 * @return
	 */
	public String getFileName() {
		return this.fileName;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("type", this.type) 
		.append("fileUrl", this.fileUrl) 
		.append("updateDate", this.updateDate) 
		.append("dateId", this.dateId) 
		.append("fileName", this.fileName) 
		.toString();
	}
}