package com.winway.purchase.util;

public class passparameter {
	private String userName;
	private String password;

	private int reocrds;
	
	public String getPassword() {
		return password;
	}

	public passparameter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public passparameter(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getReocrds() {
		return reocrds;
	}

	public void setreocrds(int reocrds) {
		this.reocrds = reocrds;
	}
}
