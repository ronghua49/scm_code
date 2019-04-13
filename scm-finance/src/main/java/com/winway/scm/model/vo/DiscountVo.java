package com.winway.scm.model.vo;

public class DiscountVo {

    //票折规则id
    private  String discountId;

    private String productId;

    //商品票折类型 1:禁止 2：正常状态 3.可票折
    private String type;

    //可票折数量
    private Integer discountNum;

    //票折比率
    private Double rate;

    //规则使用结束后是否正常票折（0：正常票折，1：禁止票折）
    private String state;

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDiscountNum() {
        return discountNum;
    }

    public void setDiscountNum(Integer discountNum) {
        this.discountNum = discountNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}
