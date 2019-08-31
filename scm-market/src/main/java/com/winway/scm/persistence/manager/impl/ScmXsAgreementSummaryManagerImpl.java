package com.winway.scm.persistence.manager.impl;

import java.util.*;

import javax.annotation.Resource;

import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.UniqueIdUtil;
import com.winway.purchase.util.DateFormatter;
import com.winway.scm.model.*;
import com.winway.scm.persistence.dao.*;
import com.winway.scm.persistence.manager.*;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    ScmXsDealerPayStyleManager scmXsDealerPayStyleManager;
    @Resource
    ScmXsDealerPayStyleDao scmXsDealerPayStyleDao;

    @Resource
    ScmXsBigContractDao scmXsBigContractDao;
    @Resource
    ScmXsBigContractProductSumDao productSumDao;
    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;


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
            scmXsDealerClause1.setDealerclauseId(scmXsDealerClause1.getId());
            List<ScmXsDealerPayStyle> scmXsDealerPayStyleList = scmXsDealerPayStyleManager.getByMainId(scmXsDealerClause1.getId());
            scmXsDealerClause1.setScmXsDealerPayStyleList(scmXsDealerPayStyleList);
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
        List<ScmXsDealerClause> byMasterId = scmXsDealerClauseDao.getByMasterId(id);
        List<String> dealerClauseIds = new ArrayList<>();
        for (ScmXsDealerClause dealerClause : byMasterId) {
            dealerClauseIds.add(dealerClause.getId());
        }
        // 删除前保存上次品规数据到历史表
        saveAgreementLast(id);
        //获取原品规数据
        List<ScmXsAgreementProductDetail> byMainId = scmXsAgreementProductDetailDao.getByMainId(id);
        // 对品规数据进行修改
        List<ScmXsAgreementProductDetail> scmXsAgreementProductDetailList = scmXsAgreementSummary.getScmXsAgreementProductDetailList();
        for (ScmXsAgreementProductDetail scmXsAgreementProductDetail : scmXsAgreementProductDetailList) {
            String agreementSummaryId = scmXsAgreementProductDetail.getAgreementSummaryId();

            for (ScmXsAgreementProductDetail originalPro : byMainId) {
                // 原品规相同的 未修改的数据 直接跳过
                if (scmXsAgreementProductDetail.getCommerceCode().equals(originalPro.getCommerceCode())) {
                    if (scmXsAgreementProductDetail.getIsImplement().equals(originalPro.getIsImplement()) && "1".equals(scmXsAgreementProductDetail.getIsImplement())) {
                        break;
                    }
                    if (scmXsAgreementProductDetail.getBiddingPrice() != originalPro.getBiddingPrice() || scmXsAgreementProductDetail.getSupplyPrice() != originalPro.getSupplyPrice() ||
                            scmXsAgreementProductDetail.getAcceptGross() != originalPro.getAcceptGross() || scmXsAgreementProductDetail.getAllotGross() != originalPro.getAllotGross() ||
                            scmXsAgreementProductDetail.getPurePinGross() != originalPro.getPurePinGross() || scmXsAgreementProductDetail.getWireTransferPrice() != originalPro.getWireTransferPrice() ||
                            scmXsAgreementProductDetail.getAcceptPrice() != originalPro.getAcceptPrice() || scmXsAgreementProductDetail.getPurePinPrice() != originalPro.getPurePinPrice()) {

                        // 失效大合同商品
                        List<ScmXsBigContract> bigContracts = scmXsBigContractDao.getByAgreementSummaryId(scmXsAgreementSummary.getOwnerId(), agreementSummaryId);
                        for (ScmXsBigContract bigContract : bigContracts) {
                            List<ScmXsBigContractProduct> scmXsBigContractProductList = bigContract.getScmXsBigContractProductList();
                            // 如果当前协议下的大合同 商品是这些修改的品规 则失效大合同商品
                            for (ScmXsBigContractProduct product : scmXsBigContractProductList) {
                                if (product.getProductCode().equals(scmXsAgreementProductDetail.getCommerceCode())) {
                                    product.setInvalid("1");
                                }
                                // 大合同汇总失效变更协议下的  汇总商品
                                List<ScmXsBigContractProductSum> bigcontractproductsum = productSumDao.getByOwnerIdAndProCodeAndSummaryId(scmXsAgreementSummary.getOwnerId(), product.getProductCode(), dealerClauseIds);
                                for (ScmXsBigContractProductSum productSum : bigcontractproductsum) {
                                    productSum.setInvalid("1");
                                    productSumDao.update(productSum);
                                }
                            }
                        }
                    }
                }
            }
            if ("1".equals(scmXsAgreementProductDetail.getIsSign())) {
                if (agreementSummaryId != null) {
                    scmXsAgreementProductDetailManager.update(scmXsAgreementProductDetail);
                } else {
                    scmXsAgreementProductDetail.setAgreementSummaryId(id);
                    scmXsAgreementProductDetailManager.create(scmXsAgreementProductDetail);
                }
            }
        }
    }

    private void saveAgreementLast(String id) {
        // 只保留上一次数据
        List<ScmXsAgreementProductDetail> lastProductDetails = scmXsAgreementProductDetailDao.getLastByMainId(id);
        if (lastProductDetails != null && !lastProductDetails.isEmpty()) {
            scmXsAgreementProductDetailDao.delLastByMainId(id);
        }
        List<ScmXsAgreementProductDetail> byMainId = scmXsAgreementProductDetailDao.getByMainId(id);
        for (ScmXsAgreementProductDetail scmXsAgreementProductDetail : byMainId) {
            if ("1".equals(scmXsAgreementProductDetail.getIsSign())) {
                scmXsAgreementProductDetail.setAgreementSummaryId(id);
                scmXsAgreementProductDetail.setId(UniqueIdUtil.getSuid());
                scmXsAgreementProductDetailDao.createLast(scmXsAgreementProductDetail);
            }

        }
    }

    @Transactional
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
//            saveByAgreement(scmXsAgreementSummary);


        }
        StartFlowParam startFlowParam = new StartFlowParam("jxsxyhztksq", "SCM", "approvalId");
        startFlowParam.setFormType("frame");
        CustomStartResult customStartResult = null;
        try {
            System.out.println("发起经销商协议合作条款申请");
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            String approvalId = customStartResult.getInstId();
            scmXsAgreementSummary.setApprovalId(approvalId);
            super.update(scmXsAgreementSummary);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("启动工作流失败");
        }
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        ScmXsAgreementSummary scmXsAgreementSummaryId = scmXsAgreementSummaryDao.getSupplierFirstByApprovalId(approvalId);
        if (scmXsAgreementSummaryId == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
            //审批状态调整为通过
            scmXsAgreementSummaryId.setApprovalState("2");
            scmXsAgreementSummaryDao.update(scmXsAgreementSummaryId);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
            scmXsAgreementSummaryId.setApprovalState("3");
            scmXsAgreementSummaryDao.update(scmXsAgreementSummaryId);
        }
        //推送数据至WMS
    }

    @Override
    public void saveByDealerClause(ScmXsAgreementSummary scmXsAgreementSummary) {
        String pk = scmXsAgreementSummary.getId();
        ScmXsAgreementSummary scmXsAgreementSummary2 = scmXsAgreementSummaryDao.get(pk);
        // 删除原付款方式前保存
        savePayStyleLast(pk);
        List<ScmXsDealerClause> byMasterId = scmXsDealerClauseDao.getByMasterId(pk);
        for (ScmXsDealerClause dealerClause : byMasterId) {
            scmXsDealerPayStyleDao.delByMainId(dealerClause.getId());
        }
        scmXsDealerClauseDao.delByMainId(pk);
        //获取前端要保存的从表数据
        List<ScmXsDealerClause> scmXsDealerClauseList = scmXsAgreementSummary.getScmXsDealerClauseList();
        //遍历，使用前端传入的商业编码，获取已经保存到数据库中的商业数据，如果无法获取，说明没有保存，
        for (ScmXsDealerClause scmXsDealerClause : scmXsDealerClauseList) {
            // 获取协议的付款方式
            List<ScmXsDealerPayStyle> scmXsDealerPayStyleList = scmXsDealerClause.getScmXsDealerPayStyleList();
            scmXsDealerClause.setAgreementSummaryId(pk);
            scmXsDealerClause.setId(null);
            scmXsDealerClause.setClauseCode(scmXsAgreementSummary2.getAgreementSummaryCode());
            scmXsDealerClauseManager.create(scmXsDealerClause);
            for (ScmXsDealerPayStyle payStyle : scmXsDealerPayStyleList) {
                payStyle.setMasterId(scmXsDealerClause.getId());
                scmXsDealerPayStyleManager.create(payStyle);
            }
        }
    }

    private void savePayStyleLast(String pk) {
        List<ScmXsDealerClause> byMasterId = scmXsDealerClauseDao.getByMasterId(pk);
        for (ScmXsDealerClause dealerClause : byMasterId) {
            // 只保留上次协议变更付款方式
            scmXsDealerPayStyleDao.delLastByMainId(dealerClause.getId());
            List<ScmXsDealerPayStyle> scmXsDealerPayStyleList = dealerClause.getScmXsDealerPayStyleList();
            for (ScmXsDealerPayStyle payStyle : scmXsDealerPayStyleList) {
                payStyle.setId(UniqueIdUtil.getSuid());
                scmXsDealerPayStyleDao.createLast(payStyle);
            }
        }

    }

    @Override
    public void saveByAgreement(ScmXsAgreementSummary scmXsAgreementSummary) {
        String pk = scmXsAgreementSummary.getId();
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


    @Override
    public List<Map<String, Object>> aunualFee(String businessDivisionId, String provinceId, String year) {
        //获取本年度 商务分区和省区下 所有签订协议经销商的年费
        List<Map<String, Object>> mapList = new ArrayList<>();
//        scmXsAgreementSummaryDao.aunualFee(businessDivisionId,provinceId,year);
        return null;
    }

}
