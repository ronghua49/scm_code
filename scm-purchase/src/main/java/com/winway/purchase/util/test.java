package com.winway.purchase.util;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class test {

    static passparameter pp = null;
    static String setdata = "";

    public static void main(String[] args) {
        pp = new passparameter();
        pp.setUserName("EIPUSER");
        pp.setPassword("EIPUSER");
        try {

	            login();
//
//	            yycustomer("0000000221");

//            dowtest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
	public static void login() throws UnsupportedEncodingException {
        setdata = "&UserName=" + URLEncoder.encode(pp.getUserName(), "UTF-8") + "&Password=" + URLEncoder.encode(pp.getPassword(), "UTF-8");
        String getdata = Help.callSAP(pp, "http://203.85.28.127:8000/sap/zsaplogin?sap-client=200", setdata);
        System.out.println("getdata:" + getdata);
    }

    public static void yycustomer(String kunnr) throws UnsupportedEncodingException {
        setdata = "&kunnr=" + URLEncoder.encode(kunnr, "UTF-8");
        String getdata = Help.callSAP(pp, "http://203.85.28.127:8000/sap/yycustomer?sap-client=200", setdata);
        System.out.println("yycustomer:" + getdata);
    }
    
}
