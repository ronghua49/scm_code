package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmFhShipmentsTask;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：发货任务表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhShipmentsTaskDao extends MyBatisDao<String, ScmFhShipmentsTask> {
    List<Map<String,String>> commerceList(Map<String, Object> params);

    List<String> proList(String ownerId);

    List<String> codeList(String ownerId);
}
