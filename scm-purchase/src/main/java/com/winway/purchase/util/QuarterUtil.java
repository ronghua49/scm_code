package com.winway.purchase.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class QuarterUtil {
	/**
	 * 根据季度 返回月份字符串
	 * @param quarter季度
	 * @return
	 */
	public static String quarterSlew(String quarter){
		String mouth = null;
		if (!(quarter == null || "".equals(quarter))) {		
			if ("1".equals(quarter)) {
				mouth = "1,2,3"; 
			}else if ("2".equals(quarter)) {
				mouth = "4,5,6";
			}else if ("3".equals(quarter)) {
				mouth = "7,8,9";
			}else if ("4".equals(quarter)) {
				mouth = "10,11,12";
			}
		}else{
			throw new RuntimeException("季度为空");
		}
		return mouth;
	}
	/**
	 * 根据月份返回当前季度
	 * @param month月份
	 * @return
	 */
	@SuppressWarnings("unused")
	public static String getQuarter(String month){
		String quarter = null;
		
		if (!(quarter == null || "".equals(quarter))) {		
			if ("1".equals(quarter) || "2".equals(quarter) || "3".equals(quarter)) {
				quarter = "1"; 
			}else if ("4".equals(quarter)||"5".equals(quarter)||"6".equals(quarter)) {
				quarter = "2";
			}else if ("7".equals(quarter)||"8".equals(quarter)||"9".equals(quarter)) {
				quarter = "3";
			}else {
				quarter = "4";
			}
		}else{
			throw new RuntimeException("月份为空");
		}
		return quarter;
	}


	/**
	 * 传入单号类型生成唯一单号
	 * @param type单号类型:档案  DA  采购合同  CGHT   采购订单  CGDD  采购补单  CGBD...
	 * @return
	 */
	public static String getCode(String type){
		if(type == null) {
			throw new RuntimeException("订单类型异常,请传入正确订单类型");
		}
		type = type.toUpperCase();
		Date date=new Date();
		DateFormat format = new SimpleDateFormat("yyMMdd");
		String time = format.format(date);
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {//有可能是负数
			hashCodeV = -hashCodeV;
		}
		return new StringBuffer().append(type).append(time).append(String.format("%010d", hashCodeV)).toString();
	}
	

}
