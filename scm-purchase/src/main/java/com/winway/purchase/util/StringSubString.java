package com.winway.purchase.util;

public class StringSubString {
	
	public String wipeOffString(String oldStr){
		if (oldStr.contains("\"")) {			
			String newStr = oldStr.substring(1, oldStr.length()-1);			
			return newStr;
		}
		return oldStr;
	}

}
