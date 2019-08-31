package com.winway.scm.print;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelBpmVo.DefaultFmsBpmCheckTaskOpinion;
import com.winway.purchase.print.jasperreport.PrintBase;
import com.winway.purchase.print.jasperreport.PrintObject;
import com.winway.scm.model.ScmZsjSupplierFirst;
import com.winway.scm.persistence.dao.ScmZsjSupplierFirstDao;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

@Component("testPrint")
public class testPrint extends PrintBase {
    @Override
    public List<PrintObject> getPrintData(Map<String, Object> map) {
        List<PrintObject> exports = new ArrayList<PrintObject>();
        PrintObject printObject1 = new PrintObject();
        printObject1.setPrintMbname("testPrint");
        Map parameters1 = new HashMap(25);
        List<Map> entrustBookList = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("r","x希特勒");
        map1.put("a","尼采");
        HashMap<String, Object> map2 = new HashMap<>();
        map1.put("r","毛主席");
        map1.put("a","马克思");
        entrustBookList.add(map1);
        entrustBookList.add(map2);
        printObject1.setDatasource(entrustBookList);
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
