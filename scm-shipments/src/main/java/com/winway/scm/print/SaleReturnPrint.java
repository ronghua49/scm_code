package com.winway.scm.print;

import com.winway.purchase.print.jasperreport.PrintBase;
import com.winway.purchase.print.jasperreport.PrintObject;
import com.winway.scm.persistence.dao.ScmFhMarketSelesReturnMasterDao;
import com.winway.scm.persistence.dao.ScmFhShipmentsDatailMasterDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("SaleReturnPrint")
public class SaleReturnPrint extends PrintBase {

    @Resource
    ScmFhShipmentsDatailMasterDao scmFhShipmentsDatailMasterDao;

    @Override
    public List<PrintObject> getPrintData(Map<String, Object> map) {
        String medicineType = (String) map.get("medicineType");
        String createString = (String) map.get("shipmentsAffirmDate");
        if (medicineType == null || "".equals(medicineType)) {
            throw new RuntimeException("药品类型参数为空");
        }
        if ("0".equals(medicineType)) {
            medicineType = "普通药品";
        } else {
            medicineType = "特殊药品";
        }
        if (createString!=null&&!createString.isEmpty()) {
            String[] split = createString.split(",");
            map.put("startDate", split[0]);
            map.put("endDate", split[1]);
        }
        List<PrintObject> exports = new ArrayList<PrintObject>();
        PrintObject printObject1 = new PrintObject();
        printObject1.setPrintMbname("saleRecode");
        Map parameters1 = new HashMap(10);
        parameters1.put("medicineType", medicineType);
        List<Map> mapList = scmFhShipmentsDatailMasterDao.salesRecordMap(map);
        if(mapList.isEmpty()){
            mapList = null;
        }
        printObject1.setDatasource(mapList);
        printObject1.setParameters(parameters1);
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
