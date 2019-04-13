package com.winway.purchase;

import com.thoughtworks.xstream.XStream;

public class Test {

    @org.junit.Test
    public void test(){
        String ss="<proData>\n" +
                "  <proId>1</proId>\n" +
                "  <proName>华为</proName>\n" +
                "  <commonaryList>\n" +
                "    <commont>\n" +
                "      <nikiName>aa</nikiName>\n" +
                "      <comNum>22</comNum>\n" +
                "      <content>sdjkfhvidsbvfsbs</content>\n" +
                "    </commont>\n" +
                "    <commont>\n" +
                "      <nikiName>bb</nikiName>\n" +
                "      <comNum>33</comNum>\n" +
                "      <content>esajvfsukbvfdsb</content>\n" +
                "    </commont>\n" +
                "  </commonaryList>\n" +
                "</proData>";
        System.out.println("-------------------------------------------");
        //xml 转换bean
        XStream xStream = new XStream();
        Object o = xStream.fromXML(ss);
        System.out.println(o);


    }



}
