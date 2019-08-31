package com.winway.scm.model;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 公司收回仓库
 * <pre> 
 * 描述：公司收回仓库 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-29 11:18:31
 * 版权：美达开发小组
 * </pre>
 */
 @ApiModel(value = "ScmZsjTxCompanyWarehouse",description = "公司收回仓库") 
public class ScmZsjTxCompanyWarehouse extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="货主ID" ,required = true )
	protected String ownerId; 
	
	@ApiModelProperty(value="仓库地址" ,required = true)
	protected String address; 
	
	@ApiModelProperty(value="仓库名称" ,required = true)
	protected String warehouseName;

	 @ApiModelProperty(value="仓库编号" ,required = true)
	 protected String warehouseCode;

	 @ApiModelProperty(value="联系人" ,required = true)
	protected String contactPersion; 
	
	@ApiModelProperty(value="联系电话" ,required = true)
	protected String contactPhone;


	 public String getWarehouseCode() {
		 return warehouseCode;
	 }

	 public void setWarehouseCode(String warehouseCode) {
		 this.warehouseCode = warehouseCode;
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
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 返回 仓库地址
	 * @return
	 */
	public String getAddress() {
		return this.address;
	}
	
	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getContactPersion() {
		return contactPersion;
	}

	public void setContactPersion(String contactPersion) {
		this.contactPersion = contactPersion;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("ownerId", this.ownerId) 
		.append("address", this.address) 
		.toString();
	}
}