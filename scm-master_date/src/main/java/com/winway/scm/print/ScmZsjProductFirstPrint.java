package com.winway.scm.print;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelBpmVo.DefaultFmsBpmCheckTaskOpinion;
import com.winway.purchase.print.jasperreport.PrintBase;
import com.winway.purchase.print.jasperreport.PrintObject;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmZsjProductFirst;
import com.winway.scm.persistence.dao.ScmZsjProductFirstDao;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Component("ScmZsjProductFirstPrint")
public class ScmZsjProductFirstPrint extends PrintBase {
    @Resource
    ScmZsjProductFirstDao scmZsjProductFirstDao;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    @Override
    public List<PrintObject> getPrintData(Map<String, Object> map) {
        String id = String.valueOf(map.get("ID"));
        ScmZsjProductFirst scmZsjProductFirst = scmZsjProductFirstDao.get(id);
        if (scmZsjProductFirst == null) {
            throw new RuntimeException("传输id数值错误，未查询到首营数据");
        }
        BufferedImage logo = null;
        ClassPathResource mbResource = new ClassPathResource("jaspertemplate" + File.separator + "imgs" + File.separator + "logo.png");
        try {
            InputStream is = mbResource.getInputStream();
            logo = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("logo读取失败");
        }
        String instId = scmZsjProductFirst.getApprovalId();
        Map parameters1 = new HashMap(25);
        parameters1.put("logo", logo);
        Map mapMain = scmZsjProductFirstDao.printByMainId(id);
        List<PrintObject> exports = new ArrayList<PrintObject>();
        PrintObject printObject1 = new PrintObject();
        printObject1.setPrintMbname("scm_zsj_productFirst");
        parameters1.putAll(mapMain);
        List<Map> mapList = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            List<DefaultFmsBpmCheckTaskOpinion> instanceFlowHistoryList = bpmRuntimeFeignService.instanceFlowHistoryList(Arrays.asList(instId));
            for (int i =0;i<instanceFlowHistoryList.size();i++) {
                Map map1 = new HashMap();
                DefaultFmsBpmCheckTaskOpinion taskOpinion = instanceFlowHistoryList.get(i);
                String auditorName = taskOpinion.getAuditorName();
                LocalDateTime completeTime = taskOpinion.getCompleteTime();
                String string = "";
                if(completeTime!=null){
                    string= dateTimeFormatter.format(completeTime);
                }
                String taskName = taskOpinion.getTaskName();
                String opinion = taskOpinion.getOpinion();
                String approvalNum = "第"+ QuarterUtil.numToChinese(String.valueOf(i))+"审";
                if(i==0){
                    approvalNum = "申请信息";
                }
                map1.put("approvalNum",approvalNum);
                map1.put("approvalName",taskName);
                map1.put("auditorName",auditorName == null ? "" : auditorName);
                map1.put("completeTime",completeTime == null ? "" : string);
                map1.put("opinion",opinion == null ? "" : opinion);
                mapList.add(map1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取审批意见异常");
        }
        parameters1.put("product_approval",mapList);
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
