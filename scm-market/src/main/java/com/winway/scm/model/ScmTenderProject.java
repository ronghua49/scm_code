package com.winway.scm.model;

import com.hotent.base.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * 招标项目管理
 * <pre>
 * 描述：招标项目管理 实体对象
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-25 11:09:05
 * 版权：美达开发小组
 * </pre>
 */
@ApiModel(value = "ScmTenderProject", description = "招标项目管理")
public class ScmTenderProject extends BaseModel<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    protected String id;

    @ApiModelProperty(value = "招标项目编码")
    protected String tenderProjectCode;

    @ApiModelProperty(value = "项目名称")
    protected String tenderProjectName;

    @ApiModelProperty(value = "招标性质")
    protected String tenderProperty;

    @ApiModelProperty(value = "省区")
    protected String provinceCode;

    @ApiModelProperty(value = "省区名称")
    protected String provinceName;

    @ApiModelProperty(value = "地市")
    protected String cityCode;

    @ApiModelProperty(value = "地市名称")
    protected String cityName;

    @ApiModelProperty(value = "县区")
    protected String areaCode;

    @ApiModelProperty(value = "县区名称")
    protected String areaName;

    @ApiModelProperty(value = "项目开始时间")
    protected LocalDate startDate;

    @ApiModelProperty(value = "计划项目失效时间")
    protected LocalDate endDate;

    @ApiModelProperty(value = "项目年份")
    protected Integer year;

    @ApiModelProperty(value = "备注")
    protected String remarks;

    @ApiModelProperty(value = "文件地址")
    protected String fileUrl;

    @ApiModelProperty(value = "状态", notes = "-1 失效 0 未生效 1 正常")
    protected Integer status;

    @ApiModelProperty(value = "工作流审批状态 ", allowableValues = "-2, -1, 0, 1, 2", notes = " -2 废除 -1 已拒绝 0 未提交 1 已通过 2 审批中")
    protected Integer flowStatus;

    @ApiModelProperty(value = "工作流Id")
    protected String flowId;

    @ApiModelProperty(value = "标志删除（0-正常/1-删除）")
    protected Integer isDelete;

    /**
     * 中标价管理列表
     */
    protected List<ScmTenderPrice> scmTenderPriceList = new ArrayList<>();

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

    public void setTenderProperty(String tenderProperty) {
        this.tenderProperty = tenderProperty;
    }

    /**
     * 返回 招标性质
     *
     * @return
     */
    public String getTenderProperty() {
        return this.tenderProperty;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * 返回 省区
     *
     * @return
     */
    public String getProvinceCode() {
        return this.provinceCode;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * 返回 省区名称
     *
     * @return
     */
    public String getProvinceName() {
        return this.provinceName;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 返回 地市
     *
     * @return
     */
    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 返回 city_name
     *
     * @return
     */
    public String getCityName() {
        return this.cityName;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 返回 县区
     *
     * @return
     */
    public String getAreaCode() {
        return this.areaCode;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * 返回 县区名称
     *
     * @return
     */
    public String getAreaName() {
        return this.areaName;
    }

    public void setTenderProjectCode(String tenderProjectCode) {
        this.tenderProjectCode = tenderProjectCode;
    }

    /**
     * 返回 招标项目编码
     *
     * @return
     */
    public String getTenderProjectCode() {
        return this.tenderProjectCode;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * 返回 项目开始时间
     *
     * @return
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * 返回 计划项目失效时间
     *
     * @return
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setTenderProjectName(String tenderProjectName) {
        this.tenderProjectName = tenderProjectName;
    }

    /**
     * 返回 tender_project_name
     *
     * @return
     */
    public String getTenderProjectName() {
        return this.tenderProjectName;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 返回 项目年份
     *
     * @return
     */
    public Integer getYear() {
        return this.year;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 返回 备注
     *
     * @return
     */
    public String getRemarks() {
        return this.remarks;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * 返回 文件地址
     *
     * @return
     */
    public String getFileUrl() {
        return this.fileUrl;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 返回 状态
     *
     * @return
     */
    public Integer getStatus() {
        return status;
    }

    public void setFlowStatus(Integer flowStatus) {
        this.flowStatus = flowStatus;
    }

    /**
     * 返回 工作流审批状态
     *
     * @return
     */
    public Integer getFlowStatus() {
        return flowStatus;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    /**
     * 返回 工作流ID
     *
     * @return
     */
    public String getFlowId() {
        return flowId;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 返回 删除标志
     *
     * @return
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setScmTenderPriceList(List<ScmTenderPrice> scmTenderPriceList) {
        this.scmTenderPriceList = scmTenderPriceList;
    }

    /**
     * 返回 中标价管理列表
     *
     * @return
     */
    public List<ScmTenderPrice> getScmTenderPriceList() {
        return this.scmTenderPriceList;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("tenderProperty", this.tenderProperty)
                .append("provinceCode", this.provinceCode)
                .append("provinceName", this.provinceName)
                .append("cityCode", this.cityCode)
                .append("cityName", this.cityName)
                .append("areaCode", this.areaCode)
                .append("areaName", this.areaName)
                .append("tenderProjectCode", this.tenderProjectCode)
                .append("startDate", this.startDate)
                .append("endDate", this.endDate)
                .append("tenderProjectName", this.tenderProjectName)
                .append("year", this.year)
                .append("remarks", this.remarks)
                .append("fileUrl", this.fileUrl)
                .append("status", this.status)
                .append("flowId", this.flowId)
                .append("flowStatus", this.flowStatus)
                .append("isDelete", this.isDelete)
                .toString();
    }
}