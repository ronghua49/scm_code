package com.winway.scm.persistence.vo;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


 /**
 * ma_product_achange
 * <pre> 
 * 描述：ma_product_achange 实体对象
 * 构建组：x7
 * 作者:郑勇彬
 * 邮箱:PRD-Yongbin.zheng@Winwayworld.com
 * 日期:2019-06-19 13:54:44
 * 版权：深圳万威科技有限公司
 * </pre>
 */
 @ApiModel(value = "ProductAchange",description = "ma_product_achange") 
public class ProductAchange extends BaseModel<String>{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="id")
	protected String id; 
	
	@ApiModelProperty(value="商品id")
	protected String productId;
	
	@ApiModelProperty(value="件装量")
	protected String achage; 
	
	@ApiModelProperty(value="产品编码")
	protected String productCode;
	
	@ApiModelProperty(value="编号")
	protected String code; 
	
	@ApiModelProperty(value="是否删除(0:删除,1:未删除)")
	protected String isDelete;
	
	@ApiModelProperty(value="物料编码")
	protected String productNum;
	
	
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
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	/**
	 * 返回 商品id
	 * @return
	 */
	public String getProductId() {
		return this.productId;
	}
	
	public void setAchage(String achage) {
		this.achage = achage;
	}
	
	/**
	 * 返回 件装量
	 * @return
	 */
	public String getAchage() {
		return this.achage;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * 返回 产品编码
	 * @return
	 */
	public String getProductCode() {
		return this.productCode;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 返回 编号
	 * @return
	 */
	public String getCode() {
		return this.code;
	}
	
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	/**
	 * 返回 是否删除(0:删除,1:未删除)
	 * @return
	 */
	public String getIsDelete() {
		return this.isDelete;
	}
	
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}
	
	/**
	 * 返回 物料编码
	 * @return
	 */
	public String getProductNum() {
		return this.productNum;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("productId", this.productId)
		.append("achage", this.achage) 
		.append("productCode", this.productCode)
		.append("code", this.code) 
		.append("isDelete", this.isDelete)
		.append("productNum", this.productNum)
		.toString();
	}
}