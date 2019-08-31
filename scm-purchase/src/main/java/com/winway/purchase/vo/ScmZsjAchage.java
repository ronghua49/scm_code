package com.winway.purchase.vo;

import com.hotent.base.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 件装量表
 * <pre> 
 * 描述：件装量表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmZsjAchage",description = "件装量表") 
public class ScmZsjAchage extends BaseModel<String>{
	private static final long serialVersionUID = 1L;
	private String id;
	private String productId;
	private String productCode;
	private String productNum;
	private String achage;
	private String code;
	private String isDelete;


	@ApiModelProperty(value="长(厘米)")
	protected Double length;

	@ApiModelProperty(value="宽(厘米)")
	protected Double wide;

	@ApiModelProperty(value="高(厘米)")
	protected Double high;

	@ApiModelProperty(value="体积(立方米)")
	protected Double volume;

	@ApiModelProperty(value="毛重(KG)")
	protected Double roughWeight;

	@ApiModelProperty(value="外箱条码")
	protected String cartonBarcode;

	@ApiModelProperty(value="小盒条码")
	protected String smallBarcode;


	public void setLength(Double length) {
		this.length = length;
	}

	/**
	 * 返回 长(厘米)
	 * @return
	 */
	public Double getLength() {
		return this.length;
	}

	public void setWide(Double wide) {
		this.wide = wide;
	}

	/**
	 * 返回 宽(厘米)
	 * @return
	 */
	public Double getWide() {
		return this.wide;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	/**
	 * 返回 高(厘米)
	 * @return
	 */
	public Double getHigh() {
		return this.high;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	/**
	 * 返回 体积(立方米)
	 * @return
	 */
	public Double getVolume() {
		return this.volume;
	}

	public void setRoughWeight(Double roughWeight) {
		this.roughWeight = roughWeight;
	}

	/**
	 * 返回 毛重(KG)
	 * @return
	 */
	public Double getRoughWeight() {
		return this.roughWeight;
	}

	public void setCartonBarcode(String cartonBarcode) {
		this.cartonBarcode = cartonBarcode;
	}

	/**
	 * 返回 外箱条码
	 * @return
	 */
	public String getCartonBarcode() {
		return this.cartonBarcode;
	}

	public String getSmallBarcode() {
		return smallBarcode;
	}

	public void setSmallBarcode(String smallBarcode) {
		this.smallBarcode = smallBarcode;
	}



	public String getCode() {
		return code;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProductId() {
		return productId;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public void setProducrId(String productId) {
		this.productId = productId;
	}

	public String getAchage() {
		return achage;
	}

	public void setAchage(String achage) {
		this.achage = achage;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	

}
