package com.winway.scm.print;

import com.winway.purchase.print.jasperreport.PrintBase;
import com.winway.purchase.print.jasperreport.PrintObject;
import com.winway.scm.persistence.dao.ScmXsAgreementSummaryDao;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("ScmXsClauseSupplyPrint")
public class ScmXsClauseSupplyPrint extends PrintBase {
    @Resource
    ScmXsAgreementSummaryDao scmXsAgreementSummaryDao;
    @Override
    public List<PrintObject> getPrintData(Map<String, Object> map) {
        BufferedImage img_ht = null;
        BufferedImage img_hc = null;
        ClassPathResource mbResource = new ClassPathResource("jaspertemplate" + File.separator + "imgs" + File.separator + "ht_img.png");
        ClassPathResource mbResource2 = new ClassPathResource("jaspertemplate" + File.separator + "imgs" + File.separator + "ht_img.png");
        try {
            InputStream is = mbResource.getInputStream();
            InputStream is2 = mbResource2.getInputStream();
            img_ht = ImageIO.read(is);
            img_hc = ImageIO.read(is2);
        } catch (IOException e) {
            throw new RuntimeException("公司章图片读取失败");
        }
        if (!map.containsKey("ID")) {
            throw new RuntimeException("协议ID参数错误");
        }
        if (!map.containsKey("commerceCode")) {
            throw new RuntimeException("商业编码参数不得为空");
        }
        String id = String.valueOf(map.get("ID"));
        String commerceCode = String.valueOf(map.get("commerceCode"));
        Map parameters1 = new HashMap(10);
        // 单个的对象全部放进一个map
        Map mapMain = scmXsAgreementSummaryDao.printAgreementPart1(id, commerceCode);
        List<PrintObject> exports = new ArrayList<PrintObject>();
        PrintObject printObject1 = new PrintObject();
        printObject1.setPrintMbname("scm_xs_clauseSupply");
        parameters1.putAll(mapMain);
        parameters1.put("img_ht", img_ht);
        parameters1.put("img_hc", img_hc);
        //调整前品规
        List<Map> mapList = scmXsAgreementSummaryDao.printAgreementPart2(id);
        parameters1.put("product_before",mapList);
        // 调整后品规

        List<Map> mapList2 = scmXsAgreementSummaryDao.printAgreementPart3(id);
        parameters1.put("product_after",mapList2);
        // 调整前付款方式

        List<Map> mapList3 = scmXsAgreementSummaryDao.printAgreementPart4(id, commerceCode);
        parameters1.put("payStyle_before",mapList3);

        // 调整后付款方式
        List<Map> mapList4 = scmXsAgreementSummaryDao.printAgreementPart5(id, commerceCode);
        parameters1.put("payStyle_after",mapList4);
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
