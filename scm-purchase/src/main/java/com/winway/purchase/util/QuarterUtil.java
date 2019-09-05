package com.winway.purchase.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class QuarterUtil {
    /**
     * 根据季度 返回月份字符串
     *
     * @param quarter季度
     * @return
     */
    public static String quarterSlew(String quarter) {
        String mouth = null;
        if (!(quarter == null || "".equals(quarter))) {
            if ("1".equals(quarter)) {
                mouth = "1,2,3";
            } else if ("2".equals(quarter)) {
                mouth = "4,5,6";
            } else if ("3".equals(quarter)) {
                mouth = "7,8,9";
            } else if ("4".equals(quarter)) {
                mouth = "10,11,12";
            }
        } else {
            throw new RuntimeException("季度为空");
        }
        return mouth;
    }

    /**
     * 根据月份返回当前季度
     *
     * @param month月份
     * @return
     */
    @SuppressWarnings("unused")
    public static String getQuarter(String month) {
        String quarter = null;

        if (!(month == null || "".equals(month))) {
            if ("1".equals(month) || "2".equals(month) || "3".equals(month)) {
                quarter = "1";
            } else if ("4".equals(month) || "5".equals(month) || "6".equals(month)) {
                quarter = "2";
            } else if ("7".equals(month) || "8".equals(month) || "9".equals(month)) {
                quarter = "3";
            } else {
                quarter = "4";
            }
        } else {
            throw new RuntimeException("月份为空");
        }
        return quarter;
    }


    /**
     * 传入单号类型生成唯一单号
     *
     * @param type单号类型:档案 DA  采购合同  CGHT   采购订单  CGDD  采购补单  CGBD...
     * @return
     */
    public static String getCode(String type) {
        if (type == null) {
            throw new RuntimeException("订单类型异常,请传入正确订单类型");
        }
        type = type.toUpperCase();
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyMMdd");
        String time = format.format(date);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        String string = new StringBuffer().append(type).append(time).append(String.format("%09d", hashCodeV)).toString();
        if (string.length() > 18) {
            string = string.substring(0, 18);
        }

        return string;

    }

    public static void main(String[] args) {
        for (int a = 0; a < 10000; a++) {
            String code = getCode("CGDD");
            System.out.println(code.length());
        }

    }

    //判断指定时间是否在两个时间区间内
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }


    public static String numToChinese(String num) {
        Map<String, String> toChinese = new HashMap<>();
        toChinese.put("1", "一");
        toChinese.put("2", "二");
        toChinese.put("3", "三");
        toChinese.put("4", "四");
        toChinese.put("5", "五");
        toChinese.put("6", "六");
        toChinese.put("7", "七");
        toChinese.put("8", "八");
        toChinese.put("9", "九");
        toChinese.put("10", "十");
        return toChinese.get(num);
    }

}
