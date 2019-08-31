package com.winway.purchase.vo;

import java.util.List;

public class ScmWmsReturnData {
	private String msg;
	private String stute;
	private Integer page;
	private Integer pagesize;
	private Integer total;
	private List<String> data;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStute() {
		return stute;
	}
	public void setStute(String stute) {
		this.stute = stute;
	}
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	
	
}
