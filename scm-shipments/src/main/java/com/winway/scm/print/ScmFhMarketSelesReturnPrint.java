package com.winway.scm.print;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.modelBpmVo.DefaultFmsBpmCheckTaskOpinion;
import com.winway.purchase.print.jasperreport.PrintBase;
import com.winway.purchase.print.jasperreport.PrintObject;
import com.winway.purchase.util.MoneyStringUtil;
import com.winway.scm.model.ScmFhMarketSelesReturnMaster;
import com.winway.scm.persistence.dao.ScmFhMarketSelesReturnDao;
import com.winway.scm.persistence.dao.ScmFhMarketSelesReturnMasterDao;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component("ScmFhMarketSelesReturnPrint")
public class ScmFhMarketSelesReturnPrint extends PrintBase {

    @Resource
    ScmFhMarketSelesReturnMasterDao scmFhMarketSelesReturnMasterDao;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;
    @Override
    public List<PrintObject> getPrintData(Map<String, Object> map) {
        if (!map.containsKey("ID")) {
            throw new RuntimeException("商业退货ID参数错误");
        }
        String id = String.valueOf(map.get("ID"));
        String ownerId = String.valueOf(map.get("ownerId"));
//        String userName = String.valueOf(map.get("userName"));
        if (id == null) {
            throw new RuntimeException("退货id参数不存在");
        }
        if (ownerId == null) {
            throw new RuntimeException("货主id参数不存在");
        }
        Map parameters1 = new HashMap(10);
//        parameters1.put("login_user","");
        ScmFhMarketSelesReturnMaster master = scmFhMarketSelesReturnMasterDao.get(id);
        if (master == null) {
            throw new RuntimeException("id参数错误，没有查询到退货信息");
        }
        String instId = master.getApprovalId();


        Map mapMain = scmFhMarketSelesReturnMasterDao.printPart1(id, ownerId);
        List<PrintObject> exports = new ArrayList<PrintObject>();
        PrintObject printObject1 = new PrintObject();
        printObject1.setPrintMbname("scm_fh_marketSelectReturn");
        parameters1.putAll(mapMain);

        List<Map> detail = scmFhMarketSelesReturnMasterDao.printPart2(id);
        if(detail.isEmpty()){
        	detail = null;
        }
        printObject1.setDatasource(detail);
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            List<DefaultFmsBpmCheckTaskOpinion> instanceFlowHistoryList = bpmRuntimeFeignService.instanceFlowHistoryList(Arrays.asList(instId));
            for (DefaultFmsBpmCheckTaskOpinion o : instanceFlowHistoryList) {
                String auditorName = o.getAuditorName();
                LocalDateTime completeTime = o.getCompleteTime();
                String string = "";
                if(completeTime!=null){
                	string= df.format(completeTime);
                }
                String opinion = o.getOpinion();
                String taskName = o.getTaskName();
                switch (taskName) {
                    case "区域商务经理":
                        parameters1.put("opinion", opinion == null ? "" : opinion);
                        parameters1.put("auditorName", auditorName == null ? "" : auditorName);
                        parameters1.put("completeTime", completeTime == null ? "" : string);
                        break;
                    case "BU总监":
                        parameters1.put("opinion1", opinion == null ? "" : opinion);
                        parameters1.put("auditorName1", auditorName == null ? "" : auditorName);
                        parameters1.put("completeTime1", completeTime == null ? "" : string);
                        break;
                    case "商务总监":
                        parameters1.put("opinion2", opinion == null ? "" : opinion);
                        parameters1.put("auditorName2", auditorName == null ? "" : auditorName);
                        parameters1.put("completeTime2", completeTime == null ? "" : string);
                        break;
                    case "质量管理部负责人":
                        parameters1.put("opinion3", opinion == null ? "" : opinion);
                        parameters1.put("auditorName3", auditorName == null ? "" : auditorName);
                        parameters1.put("completeTime3", completeTime == null ? "" : string);
                        break;
                    case "配送主管":
                        parameters1.put("opinion4", opinion == null ? "" : opinion);
                        parameters1.put("auditorName4", auditorName == null ? "" : auditorName);
                        parameters1.put("completeTime4", completeTime == null ? "" : string);
                        break;
                    case "储运部经理":
                        parameters1.put("opinion5", opinion == null ? "" : opinion);
                        parameters1.put("auditorName5", auditorName == null ? "" : auditorName);
                        parameters1.put("completeTime5", completeTime == null ? "" : string);
                        break;
                    case "财务经理":
                        parameters1.put("opinion6", opinion == null ? "" : opinion);
                        parameters1.put("auditorName6", auditorName == null ? "" : auditorName);
                        parameters1.put("completeTime6", completeTime == null ? "" : string);
                        break;
                    case "总经理":
                        parameters1.put("opinion7", opinion == null ? "" : opinion);
                        parameters1.put("auditorName7", auditorName == null ? "" : auditorName);
                        parameters1.put("completeTime7", completeTime == null ? "" : string);
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
