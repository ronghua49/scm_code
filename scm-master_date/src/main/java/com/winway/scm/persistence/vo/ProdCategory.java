package com.winway.scm.persistence.vo;
import com.hotent.base.model.BaseModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


 /**
 * 商品系列表
 * <pre> 
 * 描述：商品系列表 实体对象
 * 构建组：x7
 * 作者:贺军
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-04 10:08:24
 * 版权：深圳万威科技有限公司
 * </pre>
 */
 @ApiModel(value = "ProdCategory",description = "商品系列表") 
public class ProdCategory extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="主键")
	protected String id; 
	
	@ApiModelProperty(value="产品系列名称")
	protected String catName; 
	
	@ApiModelProperty(value="产品系列编码")
	protected String catCode; 
	
	@ApiModelProperty(value="系列简称")
	protected String catShortName; 
	
	@ApiModelProperty(value="产品系列通用名称")
	protected String catGenName; 
	
	@ApiModelProperty(value="产品系列英文名称")
	protected String catEnglishName; 
	
	@ApiModelProperty(value="剂型")
	protected String dosage; 
	
	@ApiModelProperty(value="类型")
	protected String type; 
	
	@ApiModelProperty(value="STATUS")
	protected Integer status; 
	
	@ApiModelProperty(value="IS_DELETE")
	protected Integer isDelete; 
	
	@ApiModelProperty(value="等级医院产品线")
	protected String levelHospital; 

	public String getLevelHospital() {
		return levelHospital;
	}

	public void setLevelHospital(String levelHospital) {
		this.levelHospital = levelHospital;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public void setCatName(String catName) {
		this.catName = catName;
	}
	
	/**
	 * 返回 产品系列名称
	 * @return
	 */
	public String getCatName() {
		return this.catName;
	}
	
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	
	/**
	 * 返回 产品系列编码
	 * @return
	 */
	public String getCatCode() {
		return this.catCode;
	}
	
	public void setCatShortName(String catShortName) {
		this.catShortName = catShortName;
	}
	
	/**
	 * 返回 系列简称
	 * @return
	 */
	public String getCatShortName() {
		return this.catShortName;
	}
	
	public void setCatGenName(String catGenName) {
		this.catGenName = catGenName;
	}
	
	/**
	 * 返回 产品系列通用名称
	 * @return
	 */
	public String getCatGenName() {
		return this.catGenName;
	}
	
	public void setCatEnglishName(String catEnglishName) {
		this.catEnglishName = catEnglishName;
	}
	
	/**
	 * 返回 产品系列英文名称
	 * @return
	 */
	public String getCatEnglishName() {
		return this.catEnglishName;
	}

}