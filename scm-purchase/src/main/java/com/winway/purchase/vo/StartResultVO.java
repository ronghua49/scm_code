package com.winway.purchase.vo;

import com.hotent.base.model.CommonResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 启动流程的结果
 */
@ApiModel(value = "启动流程的结果", parent = CommonResult.class)
public class StartResultVO extends CommonResult<Object> {
	@ApiModelProperty(name = "message", notes = "流程实例ID", example = "10000000000001", required = true)
	private String instId;

	public StartResultVO() {
	}

	public StartResultVO(String instId) {
		this.instId = instId;
	}

	public StartResultVO(String message, String instId) {
		super(message);
		this.instId = instId;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}
}
