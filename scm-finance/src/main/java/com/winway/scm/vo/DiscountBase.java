package com.winway.scm.vo;

public class DiscountBase {

    private String commerceId;

    private String commerceCode;

    private String commerceLevel;

    private String commerceName;

    private Double rb;

    public String getCommerceId() {
        return commerceId;
    }

    public void setCommerceId(String commerceId) {
        this.commerceId = commerceId;
    }

    public String getCommerceCode() {
        return commerceCode;
    }

    public void setCommerceCode(String commerceCode) {
        this.commerceCode = commerceCode;
    }

    public String getCommerceLevel() {
        return commerceLevel;
    }

    public void setCommerceLevel(String commerceLevel) {
        this.commerceLevel = commerceLevel;
    }

    public Double getRb() {
        return rb;
    }

    public void setRb(Double rb) {
        this.rb = rb;
    }

    public String getCommerceName() {
        return commerceName;
    }

    public void setCommerceName(String commerceName) {
        this.commerceName = commerceName;
    }
}
