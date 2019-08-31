package com.winway.scm.persistence.vo;
import java.time.LocalDate;

import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * 商业表
 * <pre> 
 * 描述：商业表 实体对象
 * 构建组：x7
 * 作者:贺军
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-17 19:52:59
 * 版权：深圳万威科技有限公司
 * </pre>
 */
 @ApiModel(value = "Commerce",description = "商业表") 
public class Commerce extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商业名称")
	protected String commerceName; 
	
	@ApiModelProperty(value="档案编号")
	protected String fileCode; 
	
	@ApiModelProperty(value="企业类别(0药品经营企业,1医疗机构)")
	protected String commerceState; 
	
	@ApiModelProperty(value="客户编号")
	protected String commerceCode; 
	
	@ApiModelProperty(value="企业负责人")
	protected String companyPersion; 
	
	@ApiModelProperty(value="联系人")
	protected String contactPersion; 
	
	@ApiModelProperty(value="联系方式")
	protected String phone; 
	
	@ApiModelProperty(value="开户行")
	protected String openBank; 
	
	@ApiModelProperty(value="银行账号")
	protected String bankCode; 
	
	@ApiModelProperty(value="税号")
	protected String dutyParagraph; 
	
	@ApiModelProperty(value="商务分区ID")
	protected String businessDivisionid; 
	
	@ApiModelProperty(value="商务分区")
	protected String businessDivision; 
	
	@ApiModelProperty(value="省区ID")
	protected String provinceId; 
	
	@ApiModelProperty(value="省区")
	protected String province; 
	
	@ApiModelProperty(value="药品质量保证协议有效期")
	protected LocalDate qualityValidity; 
	
	@ApiModelProperty(value="注册地址")
	protected String registerAddress; 
	
	@ApiModelProperty(value="印章样式")
	protected String sealStyle; 
	
	@ApiModelProperty(value="随货单据样式")
	protected String billsStyle; 
	
	@ApiModelProperty(value="录入人")
	protected String enterPersion; 
	
	@ApiModelProperty(value="录入日期")
	protected java.util.Date enterDate; 
	
	@ApiModelProperty(value="商业级别")
	protected String commerceLevel; 
	
	@ApiModelProperty(value="职能类型")
	protected String functionType; 
	
	@ApiModelProperty(value="商业级别名称")
	protected String commerceLevelName;
	
	@ApiModelProperty(value="省区编码")
	private String provinceCode;
	
	@ApiModelProperty(value="市区编码")
    private String cityCode;
	
	@ApiModelProperty(value="市")
    private String cityName;
	
	@ApiModelProperty(value="县区编码")
    private String areaCode;
    
	@ApiModelProperty(value="县区名称")
	private String areaName;
	
    @ApiModelProperty(value="是否删除")
    private String isDelete;
    
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCommerceLevelName() {
        return commerceLevelName;
    }

    public void setCommerceLevelName(String commerceLevelName) {
        this.commerceLevelName = commerceLevelName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCommerceName(String commerceName) {
		this.commerceName = commerceName;
	}
	
	/**
	 * 返回 商业名称
	 * @return
	 */
	public String getCommerceName() {
		return this.commerceName;
	}
	
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}
	
	/**
	 * 返回 档案编号
	 * @return
	 */
	public String getFileCode() {
		return this.fileCode;
	}
	
	public void setCommerceState(String commerceState) {
		this.commerceState = commerceState;
	}
	
	/**
	 * 返回 企业类别(0药品经营企业,1医疗机构)
	 * @return
	 */
	public String getCommerceState() {
		return this.commerceState;
	}
	
	public void setCommerceCode(String commerceCode) {
		this.commerceCode = commerceCode;
	}
	
	/**
	 * 返回 客户编号
	 * @return
	 */
	public String getCommerceCode() {
		return this.commerceCode;
	}
	
	public void setCompanyPersion(String companyPersion) {
		this.companyPersion = companyPersion;
	}
	
	/**
	 * 返回 企业负责人
	 * @return
	 */
	public String getCompanyPersion() {
		return this.companyPersion;
	}
	
	public void setContactPersion(String contactPersion) {
		this.contactPersion = contactPersion;
	}
	
	/**
	 * 返回 联系人
	 * @return
	 */
	public String getContactPersion() {
		return this.contactPersion;
	}
	
	
	
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	
	/**
	 * 返回 开户行
	 * @return
	 */
	public String getOpenBank() {
		return this.openBank;
	}
	
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	/**
	 * 返回 银行账号
	 * @return
	 */
	public String getBankCode() {
		return this.bankCode;
	}
	
	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
	}
	
	/**
	 * 返回 税号
	 * @return
	 */
	public String getDutyParagraph() {
		return this.dutyParagraph;
	}
	
	public void setBusinessDivisionid(String businessDivisionid) {
		this.businessDivisionid = businessDivisionid;
	}
	
	/**
	 * 返回 商务分区ID
	 * @return
	 */
	public String getBusinessDivisionid() {
		return this.businessDivisionid;
	}
	
	public void setBusinessDivision(String businessDivision) {
		this.businessDivision = businessDivision;
	}
	
	/**
	 * 返回 商务分区
	 * @return
	 */
	public String getBusinessDivision() {
		return this.businessDivision;
	}
	
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	/**
	 * 返回 省区ID
	 * @return
	 */
	public String getProvinceId() {
		return this.provinceId;
	}
	
	
	
	/*public void setQualityValidity(java.util.Date qualityValidity) {
		this.qualityValidity = qualityValidity;
	}
	
	*//**
	 * 返回 药品质量保证协议有效期
	 * @return
	 *//*
	public java.util.Date getQualityValidity() {
		return this.qualityValidity;
	}*/
	
	
	
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
	
	public LocalDate getQualityValidity() {
        return qualityValidity;
    }

    public void setQualityValidity(LocalDate qualityValidity) {
        this.qualityValidity = qualityValidity;
    }

    /**
	 * 返回 注册地址
	 * @return
	 */
	public String getRegisterAddress() {
		return this.registerAddress;
	}
	
	public void setSealStyle(String sealStyle) {
		this.sealStyle = sealStyle;
	}
	
	/**
	 * 返回 印章样式
	 * @return
	 */
	public String getSealStyle() {
		return this.sealStyle;
	}
	
	public void setBillsStyle(String billsStyle) {
		this.billsStyle = billsStyle;
	}
	
	/**
	 * 返回 随货单据样式
	 * @return
	 */
	public String getBillsStyle() {
		return this.billsStyle;
	}
	
	public void setEnterPersion(String enterPersion) {
		this.enterPersion = enterPersion;
	}
	
	/**
	 * 返回 录入人
	 * @return
	 */
	public String getEnterPersion() {
		return this.enterPersion;
	}
	
	public void setEnterDate(java.util.Date enterDate) {
		this.enterDate = enterDate;
	}
	
	/**
	 * 返回 录入日期
	 * @return
	 */
	public java.util.Date getEnterDate() {
		return this.enterDate;
	}
	
	public void setCommerceLevel(String commerceLevel) {
		this.commerceLevel = commerceLevel;
	}
	
	/**
	 * 返回 商业级别
	 * @return
	 */
	public String getCommerceLevel() {
		return this.commerceLevel;
	}
	
	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}
	
	/**
	 * 返回 职能类型
	 * @return
	 */
	public String getFunctionType() {
		return this.functionType;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("commerceName", this.commerceName) 
		.append("fileCode", this.fileCode) 
		.append("commerceState", this.commerceState) 
		.append("commerceCode", this.commerceCode) 
		.append("companyPersion", this.companyPersion) 
		.append("contactPersion", this.contactPersion) 
		.append("PHONE", this.phone) 
		.append("openBank", this.openBank) 
		.append("bankCode", this.bankCode) 
		.append("dutyParagraph", this.dutyParagraph) 
		.append("businessDivisionid", this.businessDivisionid) 
		.append("businessDivision", this.businessDivision) 
		.append("provinceId", this.provinceId) 
		.append("PROVINCE", this.province) 
		.append("qualityValidity", this.qualityValidity) 
		.append("registerAddress", this.registerAddress) 
		.append("sealStyle", this.sealStyle) 
		.append("billsStyle", this.billsStyle) 
		.append("enterPersion", this.enterPersion) 
		.append("enterDate", this.enterDate) 
		.append("commerceLevel", this.commerceLevel) 
		.append("functionType", this.functionType) 
		.toString();
	}
}