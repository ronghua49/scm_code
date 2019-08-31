package com.winway.scm.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.hotent.base.model.BaseModel;


/**
 * 不合格产品报损主表
 * <pre>
 * 描述：不合格产品报损主表 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmKcBreakageMaster", description = "不合格产品报损主表")
public class ScmKcBreakageMaster extends BaseModel<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    protected String id;

    @ApiModelProperty(value = "报损单号", required = true)
    protected String breakageCode;

    @ApiModelProperty(value = "创建时间")
    protected java.util.Date createDate;

    @ApiModelProperty(value = "报损时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    protected java.util.Date breakageDate;

    @ApiModelProperty(value = "创建人")
    protected String createPersion;

    @ApiModelProperty(value = "货主ID", required = true)
    protected String ownerId;

    @ApiModelProperty(value = "品种数", required = true)
    protected Integer varietySum;

    @ApiModelProperty(value = "商品总数", required = true)
    protected Integer productSum;

    @ApiModelProperty(value = "总金额", required = true)
    protected Double priceSum;

    @ApiModelProperty(value = "审批ID")
    protected String approvalId;

    @ApiModelProperty(value = "审批状态")
    protected String approvalState;

    @ApiModelProperty(value="是否推送至WMS 0未推送；1已推送")
    protected String isToWms;
    @ApiModelProperty(value="WMS是否确认订单已经结束(0:未确认,1:已完成)")
    protected String isAffirmWms;

    public String getIsToWms() {
		return isToWms;
	}

	public void setIsToWms(String isToWms) {
		this.isToWms = isToWms;
	}

	public String getIsAffirmWms() {
		return isAffirmWms;
	}

	public void setIsAffirmWms(String isAffirmWms) {
		this.isAffirmWms = isAffirmWms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getBreakageDate() {
        return breakageDate;
    }

    public void setBreakageDate(Date breakageDate) {
        this.breakageDate = breakageDate;
    }

    /**
     * 不合格产品报损商品表列表
     */
    protected List<ScmKcBreakage> scmKcBreakageList = new ArrayList<ScmKcBreakage>();

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 返回 id
     *
     * @return
     */
    public String getId() {
        return this.id;
    }

    public void setBreakageCode(String breakageCode) {
        this.breakageCode = breakageCode;
    }

    /**
     * 返回 报损单号
     *
     * @return
     */
    public String getBreakageCode() {
        return this.breakageCode;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 返回 创建时间
     *
     * @return
     */
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    public void setCreatePersion(String createPersion) {
        this.createPersion = createPersion;
    }

    /**
     * 返回 创建人
     *
     * @return
     */
    public String getCreatePersion() {
        return this.createPersion;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 返回 货主ID
     *
     * @return
     */
    public String getOwnerId() {
        return this.ownerId;
    }

    public void setVarietySum(Integer varietySum) {
        this.varietySum = varietySum;
    }

    /**
     * 返回 品种数
     *
     * @return
     */
    public Integer getVarietySum() {
        return this.varietySum;
    }

    public void setProductSum(Integer productSum) {
        this.productSum = productSum;
    }

    /**
     * 返回 商品总数
     *
     * @return
     */
    public Integer getProductSum() {
        return this.productSum;
    }

    public void setPriceSum(Double priceSum) {
        this.priceSum = priceSum;
    }

    /**
     * 返回 总金额
     *
     * @return
     */
    public Double getPriceSum() {
        return this.priceSum;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    /**
     * 返回 审批ID
     *
     * @return
     */
    public String getApprovalId() {
        return this.approvalId;
    }

    public void setApprovalState(String approvalState) {
        this.approvalState = approvalState;
    }

    /**
     * 返回 审批状态
     *
     * @return
     */
    public String getApprovalState() {
        return this.approvalState;
    }

    public void setScmKcBreakageList(List<ScmKcBreakage> scmKcBreakageList) {
        this.scmKcBreakageList = scmKcBreakageList;
    }

    /**
     * 返回 不合格产品报损商品表列表
     *
     * @return
     */
    public List<ScmKcBreakage> getScmKcBreakageList() {
        return this.scmKcBreakageList;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("breakageCode", this.breakageCode)
                .append("createDate", this.createDate)
                .append("createPersion", this.createPersion)
                .append("ownerId", this.ownerId)
                .append("varietySum", this.varietySum)
                .append("productSum", this.productSum)
                .append("priceSum", this.priceSum)
                .append("approvalId", this.approvalId)
                .append("approvalState", this.approvalState)
                .toString();
    }
}