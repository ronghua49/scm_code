package com.winway.purchase.vo;

public class ReturnWmsVo {
	private String stute;
	private String data;
	private String page;
	private String pagesize;
	private String total;
	private String msg;
	
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPagesize() {
		return pagesize;
	}
	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	public ReturnWmsVo(String stute,String msg) {
		super();
		this.stute = stute;
		this.msg = msg;
	}
	public ReturnWmsVo(String stute, String data, String msg) {
		super();
		this.stute = stute;
		this.data = data;
		this.msg = msg;
	}
	public ReturnWmsVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStute() {
		return stute;
	}
	public void setStute(String stute) {
		this.stute = stute;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
