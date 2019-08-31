package com.winway.scm.print;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.winway.purchase.print.jasperreport.PrintBase;
import com.winway.purchase.print.jasperreport.PrintObject;
import com.winway.purchase.util.ScmPuthWmsUtil;
import com.winway.purchase.vo.ScmWmsReturnData;
import com.winway.scm.vo.ScmGspDrupBreakageVo;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("DrugBreakRecord")
public class DrugBreakRecord extends PrintBase {
    @Override
    public List<PrintObject> getPrintData(Map<String, Object> map) {
        String s = new JSONObject(map).toJSONString();
        ScmGspDrupBreakageVo scmGspDrupBreakageVo = JSON.parseObject(s, ScmGspDrupBreakageVo.class);
        ScmWmsReturnData datPputh = ScmPuthWmsUtil.getDatPputh(JSONObject.toJSONString(scmGspDrupBreakageVo), "/prolog_aomei_interface/interface/itemLoss/unqualified/scmItemUnqualifiedLossQuery.json");
        if (datPputh == null) {
            throw new RuntimeException("WMS返回数据异常");
        }
        List<Map> listMap = new ArrayList<>();
        long i=1;
        for (String text : datPputh.getData()) {
            ScmGspDrupBreakageVo scmGspDrupBreakage = JSON.parseObject(text, ScmGspDrupBreakageVo.class);
            scmGspDrupBreakage.setI(i);
            BeanMap beanMap2 = BeanMap.create(scmGspDrupBreakage);
            HashMap<Object, Object> map2 = new HashMap<>();
            for (Object key : beanMap2.keySet()) {
                map2.put(key, beanMap2.get(key));
            }
            listMap.add(map2);
            i++;
        }
        if (listMap.isEmpty()) {
            listMap = null;
        }
        Map parameters1 = new HashMap(10);
        List<PrintObject> exports = new ArrayList<PrintObject>();
        PrintObject printObject1 = new PrintObject();
        parameters1.put("itemtype", scmGspDrupBreakageVo.getItemtype());
        printObject1.setPrintMbname("drugBreakRecord");
        printObject1.setParameters(parameters1);
        printObject1.setDatasource(listMap);
        exports.add(printObject1);
        return exports;
    }

    @Override
    public boolean beforePrint(Map<String, Object> map) {
        return false;
    }

    @Override
    public void afterPrint(Map<String, Object> map) {

    }

}
