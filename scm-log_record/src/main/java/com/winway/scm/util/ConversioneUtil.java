package com.winway.scm.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.feign.UCFeignService;
import com.winway.purchase.util.ContrasUtil;
import com.winway.scm.model.ScmLogModifyLog;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 解析字符串
 * 保存为对象集合
 * @param 修改字段
 * @return
 */
public class ConversioneUtil {

    public static List<ScmLogModifyLog> getObject(String str, String userName) throws Exception {
        List<ScmLogModifyLog> scmLogModifyLogs = new ArrayList<ScmLogModifyLog>();
        ScmLogModifyLog log = null;
        String[] arr = str.split(";");
        String[] strArr = null;
        for (int i = 0; i < arr.length; i++) {
            strArr = arr[i].split("-");
            for (int j = 0; j < strArr.length; j++) {
                log = new ScmLogModifyLog();
                log.setFiledName(strArr[0] + "-" + strArr[1]);
                log.setCategoryId(strArr[2]);
                log.setCategory(strArr[3]);
                log.setModifier(userName);
                log.setModifiedTime(new Date());
            }
            scmLogModifyLogs.add(log);
        }
        return scmLogModifyLogs;
    }
}
