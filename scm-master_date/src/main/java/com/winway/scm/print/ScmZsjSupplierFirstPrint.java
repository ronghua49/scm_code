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

@Component("ScmZsjSupplierFirstPrint")
public class ScmZsjSupplierFirstPrint extends PrintBase {
    @Resource
    ScmZsjSupplierFirstDao supplierFirstDao;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    @Override
    public List<PrintObject> getPrintData(Map<String, Object> map) {
        String id = String.valueOf(map.get("ID"));
        ScmZsjSupplierFirst supplierFirst = supplierFirstDao.get(id);
        if (supplierFirst == null) {
            throw new RuntimeException("传输id数值错误，未查询到首营数据");
        }
        BufferedImage logo = null;
        ClassPathResource mbResource = new ClassPathResource("jaspertemplate" + File.separator + "imgs" + File.separator + "logo.png");
        try {
            InputStream is = mbResource.getInputStream();
            logo = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("公司章图片读取失败");
        }
        String instId = supplierFirst.getApprovalId();
        Map parameters1 = new HashMap(25);
        parameters1.put("logo", logo);
        List<String> addresss = supplierFirstDao.getAddressByMainId(id);
        if (addresss != null && !addresss.isEmpty()) {
            parameters1.put("addressStr", addresss.get(0));
        } else {
            parameters1.put("addressStr", "");
        }
        Map mapMain = supplierFirstDao.printByMainId(id);
        List<PrintObject> exports = new ArrayList<PrintObject>();
        PrintObject printObject1 = new PrintObject();
        printObject1.setPrintMbname("scm_zsj_supplierFirst");
        parameters1.putAll(mapMain);
        List<Map> entrustBookList = supplierFirstDao.printEntrustBookByMainId(id);
        printObject1.setDatasource(entrustBookList);
        try {
            List<DefaultFmsBpmCheckTaskOpinion> instanceFlowHistoryList = bpmRuntimeFeignService.instanceFlowHistoryList(Arrays.asList(instId));
            for (DefaultFmsBpmCheckTaskOpinion o : instanceFlowHistoryList) {
                String auditorName = o.getAuditorName();
                LocalDateTime completeTime = o.getCompleteTime();
                String string = "";
                if (completeTime != null) {
                    string = completeTime.toString().replaceAll("T", " ");
                }
                String taskName = o.getTaskName();
                String opinion = o.getOpinion();
                switch (taskName) {
                    case "发起节点":
                        parameters1.put("auditorName", auditorName == null ? "" : auditorName);
                        parameters1.put("completeTime", completeTime == null ? "" : string);
                        parameters1.put("opinion", opinion == null ? "" : opinion);
                        break;

                    case "供应管理部":
                        parameters1.put("auditorName1", auditorName == null ? "" : auditorName);
                        parameters1.put("completeTime1", completeTime == null ? "" : string);
                        parameters1.put("opinion1", opinion == null ? "" : opinion);
                        break;
                    case "质量管理部":
                        parameters1.put("auditorName2", auditorName == null ? "" : auditorName);
                        parameters1.put("completeTime2", completeTime == null ? "" : string);
                        parameters1.put("opinion2", opinion == null ? "" : opinion);
                        break;
                    case "财务部":
                        parameters1.put("auditorName3", auditorName == null ? "" : auditorName);
                        parameters1.put("completeTime3", completeTime == null ? "" : string);
                        parameters1.put("opinion3", opinion == null ? "" : opinion);
                        break;
                    case "质量负责人":
                        parameters1.put("auditorName4", auditorName == null ? "" : auditorName);
                        parameters1.put("completeTime4", completeTime == null ? "" : string);
                        parameters1.put("opinion4", opinion == null ? "" : opinion);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取审批意见异常");
        }
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
