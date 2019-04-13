package com.winway.scm.model;

import java.util.List;

public class FastEntryVo {
	
	private List<FastEntryData> datas;
	private String account;
	
	public List<FastEntryData> getDatas() {
		return datas;
	}
	public void setDatas(List<FastEntryData> datas) {
		this.datas = datas;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
}
