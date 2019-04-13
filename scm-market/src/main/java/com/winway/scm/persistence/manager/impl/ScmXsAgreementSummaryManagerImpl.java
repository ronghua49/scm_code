package com.winway.scm.persistence.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.model.ScmXsAgreementProductDetail;
import com.winway.scm.model.ScmXsAgreementSummary;
import com.winway.scm.model.ScmXsDealerClause;
import com.winway.scm.persistence.dao.ScmXsAgreementProductDetailDao;
import com.winway.scm.persistence.dao.ScmXsAgreementSummaryDao;
import com.winway.scm.persistence.dao.ScmXsDealerClauseDao;
import com.winway.scm.persistence.manager.ScmXsAgreementProductDetailManager;
import com.winway.scm.persistence.manager.ScmXsAgreementSummaryManager;
import com.winway.scm.persistence.manager.ScmXsDealerClauseManager;

/**
 * <pre>
 * 描述：省区协议汇总表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsAgreementSummaryManager")
public class ScmXsAgreementSummaryManagerImpl extends AbstractManagerImpl<String, ScmXsAgreementSummary> implements ScmXsAgreementSummaryManager {
    @Resource
    ScmXsAgreementSummaryDao scmXsAgreementSummaryDao;
    @Resource
    ScmXsAgreementProductDetailDao scmXsAgreementProductDetailDao;
    @Resource
    ScmXsAgreementProductDetailManager scmXsAgreementProductDetailManager;
    @Resource
    ScmXsDealerClauseManager scmXsDealerClauseManager;
    @Resource
    ScmXsDealerClauseDao scmXsDealerClauseDao;

    @Override
    protected MyBatisDao<String, ScmXsAgreementSummary> getDao() {
        return scmXsAgreementSummaryDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmXsAgreementSummary scmXsAgreementSummary) {
        super.create(scmXsAgreementSummary);
        String pk = scmXsAgreementSummary.getId();
        List<ScmXsAgreementProductDetail> scmXsAgreementProductDetailList = scmXsAgreementSummary.getScmXsAgreementProductDetailList();
        for (ScmXsAgreementProductDetail scmXsAgreementProductDetail : scmXsAgreementProductDetailList) {
            if ("1".equals(scmXsAgreementProductDetail.getIsSign())) {
                scmXsAgreementProductDetail.setAgreementSummaryId(pk);
                scmXsAgreementProductDetailManager.create(scmXsAgreementProductDetail);
            }
        }
    }

    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmXsAgreementProductDetailDao.delByMainId(entityId);
    }

    /**
     * 批量删除包含子表记录
     */
    public void removeByIds(String[] entityIds) {
        for (String id : entityIds) {
            this.remove(id);
        }
    }

    /**
     * 获取实体
     */
    public ScmXsAgreementSummary get(String entityId) {
        ScmXsAgreementSummary scmXsAgreementSummary = super.get(entityId);
        List<ScmXsAgreementProductDetail> scmXsAgreementProductDetailList = scmXsAgreementProductDetailDao.getByMainId(entityId);
        scmXsAgreementSummary.setScmXsAgreementProductDetailList(scmXsAgreementProductDetailList);

        //根据主键id查询协议合作明细数据
        List<ScmXsDealerClause> list = new ArrayList<ScmXsDealerClause>();
        List<ScmXsDealerClause> scmXsDealerClauseList = scmXsDealerClauseDao.getByMasterId(entityId);
        for (ScmXsDealerClause scmXsDealerClause : scmXsDealerClauseList) {
            ScmXsDealerClause scmXsDealerClause1 = scmXsDealerClauseManager.get(scmXsDealerClause.getId());
            list.add(scmXsDealerClause1);
        }
        scmXsAgreementSummary.setScmXsDealerClauseList(list);
        return scmXsAgreementSummary;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmXsAgreementSummary scmXsAgreementSummary) {
        super.update(scmXsAgreementSummary);
        String id = scmXsAgreementSummary.getId();
        scmXsAgreementProductDetailDao.delByMainId(id);
        List<ScmXsAgreementProductDetail> scmXsAgreementProductDetailList = scmXsAgreementSummary.getScmXsAgreementProductDetailList();
        for (ScmXsAgreementProductDetail scmXsAgreementProductDetail : scmXsAgreementProductDetailList) {
            scmXsAgreementProductDetail.setAgreementSummaryId(id);
            scmXsAgreementProductDetailManager.create(scmXsAgreementProductDetail);
        }
    }

    @Override
    public void sendApply(ScmXsAgreementSummary scmXsAgreementSummary) {
        // TODO Auto-generated method stub
        scmXsAgreementSummary.setApprovalState("1");
        //判断总表中是否存在该数据
        ScmXsAgreementSummary ScmXsAgreementSummaryById = scmXsAgreementSummaryDao.get(scmXsAgreementSummary.getId());
        if (ScmXsAgreementSummaryById == null) {
            //有数据先删除
            create(scmXsAgreementSummary);
        } else {
            //有数据,先判断是否已经有正在审核中的数据
            if ("1".equals(ScmXsAgreementSummaryById.getApprovalState())) {
                //数据在审批中,不可修改编辑
                throw new RuntimeException("当前数据已经在审批中,不可重复提交");
            } else {
                //修改主表，删除主表数据
                update(scmXsAgreementSummary);
            }
			/*ScmXsAgreementSummary scmXsAgreementSummary2 = scmXsAgreementSummaryDao.get(scmXsAgreementSummary.getId());
			//主表数据存在
			if(scmXsAgreementSummary2 == null) {
				throw new RuntimeException("主表数据异常");
			}
			//查询主表调整数据
			scmXsAgreementSummary2.setApprovalState("1");
			scmXsAgreementSummaryDao.update(scmXsAgreementSummary2);*/
            //保存第三步数据
            saveByAgreement(scmXsAgreementSummary);
        }
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        // TODO Auto-generated method stub
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        ScmXsAgreementSummary scmXsAgreementSummaryId = scmXsAgreementSummaryDao.getSupplierFirstByApprovalId(approvalId);
        if (scmXsAgreementSummaryId == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        if ("agree".equals(actionName)) {
            //审批状态调整为通过
            scmXsAgreementSummaryId.setApprovalState("2");
        } else if ("oppose".equals(actionName)) {
            scmXsAgreementSummaryId.setApprovalState("3");
        }
        scmXsAgreementSummaryDao.update(scmXsAgreementSummaryId);

    }

    @Override
    public void saveByDealerClause(ScmXsAgreementSummary scmXsAgreementSummary) {
        String pk = scmXsAgreementSummary.getId();
        ScmXsAgreementSummary scmXsAgreementSummary2 = scmXsAgreementSummaryDao.get(pk);
        //获取该表中已经保存的数据
        List<ScmXsDealerClause> sxdc = scmXsDealerClauseDao.getByMasterId(pk);
        Map<String, ScmXsDealerClause> map = new HashMap<String, ScmXsDealerClause>();
        for (ScmXsDealerClause scmXsDealerClause1 : sxdc) {
            map.put(scmXsDealerClause1.getCommerceCode(), scmXsDealerClause1);
        }
//    	scmXsDealerClauseDao.delByMainId(pk);
        //获取前端要保存的从表数据
        List<ScmXsDealerClause> scmXsDealerClauseList = scmXsAgreementSummary.getScmXsDealerClauseList();
        //遍历，使用前端传入的商业编码，获取已经保存到数据库中的商业数据，如果无法获取，说明没有保存，
        for (ScmXsDealerClause scmXsDealerClause : scmXsDealerClauseList) {
            ScmXsDealerClause scmXsDealerClause2 = map.get(scmXsDealerClause.getCommerceCode());
            //数据库和前端传过来的匹配成功
            if (scmXsDealerClause2 != null) {
                if (scmXsDealerClause.getId() == null || "".equals(scmXsDealerClause.getId())) {
                    //没值说明是新增的方法，这个商业已经添加过了，所以跳过本次循环
                    continue;
                } else {
                    //如果id有值，说明是下一步或者申请暂存，
                    scmXsDealerClauseDao.remove(scmXsDealerClause.getId());
                }
            }
            scmXsDealerClause.setAgreementSummaryId(pk);
            scmXsDealerClause.setClauseCode(scmXsAgreementSummary2.getAgreementSummaryCode());
            scmXsDealerClauseManager.create(scmXsDealerClause);
        }
    }

    @Override
    public void saveByAgreement(ScmXsAgreementSummary scmXsAgreementSummary) {
        String pk = scmXsAgreementSummary.getId();
        ScmXsAgreementSummary scmXsAgreementSummary2 = scmXsAgreementSummaryDao.get(pk);
        scmXsAgreementProductDetailDao.delByMainId(pk);
        List<ScmXsAgreementProductDetail> scmXsAgreementProductDetailList = scmXsAgreementSummary.getScmXsAgreementProductDetailList();
        for (ScmXsAgreementProductDetail scmXsAgreementProductDetail : scmXsAgreementProductDetailList) {
            if ("1".equals(scmXsAgreementProductDetail.getIsSign())) {
                scmXsAgreementProductDetail.setAgreementSummaryId(pk);
                scmXsAgreementProductDetailManager.create(scmXsAgreementProductDetail);
            }
        }
    }

    @Override
    public ScmXsAgreementSummary getDealerClauseAndProductDetail(String id) {
        // TODO Auto-generated method stub
        //根据审批id获取主表的Id
        ScmXsAgreementSummary scmXsAgreementSummaryByApprovalId = scmXsAgreementSummaryDao.getByApprovalId(id);
        if (scmXsAgreementSummaryByApprovalId == null) {
            throw new RuntimeException("获取数据异常");
        }
        String masterId = scmXsAgreementSummaryByApprovalId.getId();
        //根据主键id查询协议合作明细数据
        List<ScmXsDealerClause> list = new ArrayList<ScmXsDealerClause>();
        List<ScmXsDealerClause> scmXsDealerClauseList = scmXsDealerClauseDao.getByMasterId(masterId);
        for (ScmXsDealerClause scmXsDealerClause : scmXsDealerClauseList) {
            ScmXsDealerClause scmXsDealerClause1 = scmXsDealerClauseManager.get(scmXsDealerClause.getId());
            list.add(scmXsDealerClause1);
        }
        scmXsAgreementSummaryByApprovalId.setScmXsDealerClauseList(list);
        //根据主键Id查询品规明细数据
        List<ScmXsAgreementProductDetail> scmXsAgreementProductDetailList = scmXsAgreementProductDetailDao.getByMainId(masterId);
        scmXsAgreementSummaryByApprovalId.setScmXsAgreementProductDetailList(scmXsAgreementProductDetailList);
        return scmXsAgreementSummaryByApprovalId;
    }

}
