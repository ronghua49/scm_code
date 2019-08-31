package com.winway.scm.model;

import com.hotent.base.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * 招标项目性质
 * <pre>
 * 描述：scm_zd_tendertype 实体对象
 * 构建组：x7
 * 作者:FangGan
 * 邮箱:libayiv@163.com
 * 日期:2019-08-20 14:47:47
 * 版权：深圳万威科技有限公司
 * </pre>
 */
@ApiModel(value = "ScmZdTenderType", description = "招标项目性质")
public class ScmZdTenderType extends BaseModel<String> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务主键")
    protected String id;

    @ApiModelProperty(value = "招标项目类型编码")
    protected String code;

    @ApiModelProperty(value = "创建日期")
    protected java.util.Date createDate;

    @ApiModelProperty(value = "创建人")
    protected String createPersion;

    @ApiModelProperty(value = "备注")
    protected String memo;

    @ApiModelProperty(value = "招标项目类型名称")
    protected String type;


    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 返回 招标项目类型编码
     *
     * @return
     */
    public String getCode() {
        return this.code;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 返回 createDate
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
     * 返回 createPersion
     *
     * @return
     */
    public String getCreatePersion() {
        return this.createPersion;
    }

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

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 返回 memo
     *
     * @return
     */
    public String getMemo() {
        return this.memo;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 返回 招标项目类型名称
     *
     * @return
     */
    public String getType() {
        return this.type;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", this.code)
                .append("createDate", this.createDate)
                .append("createPersion", this.createPersion)
                .append("id", this.id)
                .append("memo", this.memo)
                .append("type", this.type)
                .toString();
    }
}