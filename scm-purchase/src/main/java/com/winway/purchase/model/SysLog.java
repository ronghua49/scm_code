package com.winway.purchase.model;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable {

    private Long id;  //id

    private String loginUser;  // 操作用户

    private String exceptionMes;  // 异常消息

    private String operation;  // 日志类型

    private String method;  // 请求方法

    private String params;  // 请求参数

    private String ip;  // 请求IP

    private Date createDate;// 请求时间

    private Long totalTime; //总耗时长（毫秒

    private String responseState;// 请求状态

    private String result; // 请求结果


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getExceptionMes() {
        return exceptionMes;
    }

    public void setExceptionMes(String exceptionMes) {
        this.exceptionMes = exceptionMes;
    }

    public String getResponseState() {
        return responseState;
    }

    public void setResponseState(String responseState) {
        this.responseState = responseState;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }
}