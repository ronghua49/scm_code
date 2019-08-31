package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.hotent.base.exception.SystemException;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjCommerceAcceptState;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.persistence.dao.ScmZsjCommerceAcceptStateDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceFirstDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Strings;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.model.ScmZsjCommerceMerge;
import com.winway.scm.persistence.dao.ScmZsjCommerceMergeDao;
import com.winway.scm.persistence.manager.ScmZsjCommerceMergeManager;

import java.util.List;

/**
 * <pre>
 * 描述：商业合并表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceMergeManager")
public class ScmZsjCommerceMergeManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceMerge> implements ScmZsjCommerceMergeManager {
    @Resource
    ScmZsjCommerceMergeDao scmZsjCommerceMergeDao;
    @Resource
    ScmZsjCommerceFirstDao zsjCommerceFirstDao;
    @Resource
    ScmZsjCommerceDao zsjCommerceDao;
    @Resource
    ScmZsjCommerceAcceptStateDao scmZsjCommerceAcceptStateDao;


    @Override
    protected MyBatisDao<String, ScmZsjCommerceMerge> getDao() {
        return scmZsjCommerceMergeDao;
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        List<ScmZsjCommerceMerge> commerceFirstByApprovalId = scmZsjCommerceMergeDao.getCommerceFirstByApprovalId(approvalId);
        if (commerceFirstByApprovalId == null || commerceFirstByApprovalId.isEmpty()) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	for (ScmZsjCommerceMerge merge : commerceFirstByApprovalId) {
                merge.setApprovalState("2");
                //更新被合并商业的状态
                changeMergeState(merge.getViceCommerceId(),"2");
                scmZsjCommerceMergeDao.update(merge);
            }
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	for (ScmZsjCommerceMerge merge : commerceFirstByApprovalId) {
                //更新被合并商业状态
                changeMergeState(merge.getViceCommerceId(),"0");
                merge.setApprovalState("3");
                scmZsjCommerceMergeDao.update(merge);
            }
        }
    }

    @Override
    public PageList<ScmZsjCommerce> listCommerceMerge(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 2147483647, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjCommerce> query = scmZsjCommerceMergeDao.listCommerceMerge(queryFilter.getParams());
        return new PageList(query);
    }

    @Override
    public void changeMergeState(String viceCommerceId,String mergeState) {
        //更新该商业的合并状态
        ScmZsjCommerceFirst first = zsjCommerceFirstDao.getByCommerceId(viceCommerceId);
        if (first == null) {
            throw new RuntimeException("没有查到商业信息");
        }
        first.setMergeState(mergeState);
        zsjCommerceFirstDao.update(first);
    }
    @Override
    public PageList<ScmZsjCommerceMerge> query(QueryFilter queryFilter) throws SystemException {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 2147483647, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjCommerceMerge> query1 = scmZsjCommerceMergeDao.query(queryFilter.getParams());
        //添加商业级别和认可状态
        for(ScmZsjCommerceMerge merge: query1){
            ScmZsjCommerce scmZsjCommerce = zsjCommerceDao.get(merge.getHostCommerceId());
            merge.setHcommerceLevcel(scmZsjCommerce.getCommerceLevel());
            ScmZsjCommerceAcceptState lastByCommerceId = scmZsjCommerceAcceptStateDao.getLastByCommerceId(merge.getHostCommerceId());
            merge.setHacceptState(lastByCommerceId==null ? "2" :lastByCommerceId.getAcceptState());
            ScmZsjCommerce scmZsjCommerce1 = zsjCommerceDao.get(merge.getViceCommerceId());
            merge.setVcommerceLevel(scmZsjCommerce1.getCommerceLevel());
            ScmZsjCommerceAcceptState lastByCommerceId1 = scmZsjCommerceAcceptStateDao.getLastByCommerceId(merge.getViceCommerceId());
            merge.setVacceptState(lastByCommerceId1==null? "2" :lastByCommerceId1.getAcceptState());
        }
        return new PageList(query1);
    }
}
