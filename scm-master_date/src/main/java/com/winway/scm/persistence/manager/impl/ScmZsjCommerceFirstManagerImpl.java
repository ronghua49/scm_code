package com.winway.scm.persistence.manager.impl;

import java.util.*;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.query.*;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.UniqueIdUtil;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.ContrasUtil;
import com.winway.scm.model.ScmZdPromiseBook;
import com.winway.scm.model.ScmZsjAccessory;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjCommerceAccredit;
import com.winway.scm.model.ScmZsjCommerceBusiness;
import com.winway.scm.model.ScmZsjCommerceEntruseBook;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.model.ScmZsjCommerceLicence;
import com.winway.scm.model.ScmZsjCommercePromiseBook;
import com.winway.scm.model.ScmZsjCommerceShipAddress;
import com.winway.scm.model.ScmZsjCommerceWarehouse;
import com.winway.scm.model.ScmZsjGSP;
import com.winway.scm.model.ScmZsjTreatLicence;
import com.winway.scm.persistence.dao.ScmZdPromiseBookDao;
import com.winway.scm.persistence.dao.ScmZsjAccessoryDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceAccreditDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceBusinessDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceEntruseBookDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceFirstDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceLicenceDao;
import com.winway.scm.persistence.dao.ScmZsjCommercePromiseBookDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceShipAddressDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceWarehouseDao;
import com.winway.scm.persistence.dao.ScmZsjGSPDao;
import com.winway.scm.persistence.dao.ScmZsjTreatLicenceDao;
import com.winway.scm.persistence.manager.ScmZsjCommerceAccreditManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceBusinessManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceEntruseBookManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceFirstManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceLicenceManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceManager;
import com.winway.scm.persistence.manager.ScmZsjCommercePromiseBookManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceShipAddressManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceWarehouseManager;
import com.winway.scm.persistence.manager.ScmZsjGSPManager;
import com.winway.scm.persistence.manager.ScmZsjTreatLicenceManager;

/**
 * <pre>
 * 描述：商业首营记录表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceFirstManager")
public class ScmZsjCommerceFirstManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceFirst> implements ScmZsjCommerceFirstManager {
    @Resource
    ScmZsjCommerceFirstDao scmZsjCommerceFirstDao;
    @Resource
    ScmZsjCommerceWarehouseDao scmZsjCommerceWarehouseDao;
    @Resource
    ScmZsjCommerceLicenceDao scmZsjCommerceLicenceDao;
    @Resource
    ScmZsjCommerceBusinessDao scmZsjCommerceBusinessDao;
    @Resource
    ScmZsjTreatLicenceDao scmZsjTreatLicenceDao;
    @Resource
    ScmZsjGSPDao scmZsjGSPDao;
    @Resource
    ScmZsjCommercePromiseBookDao scmZsjCommercePromiseBookDao;
    @Resource
    ScmZsjCommerceShipAddressDao scmZsjCommerceShipAddressDao;
    @Resource
    ScmZdPromiseBookDao scmZdPromiseBookDao;
    @Resource
    ScmZsjCommerceEntruseBookDao scmZsjCommerceEntruseBookDao;
    @Resource
    ScmZsjCommerceAccreditDao scmZsjCommerceAccreditDao;
    @Resource
    ScmZsjCommerceManager scmZsjCommerceManager;
    @Resource
    ScmZsjCommerceLicenceManager scmZsjCommerceLicenceManager;
    @Resource
    ScmZsjAccessoryManagerImpl scmZsjAccessoryManagerImpl;
    @Resource
    ScmZsjCommerceBusinessManager scmZsjCommerceBusinessManager;
    @Resource
    ScmZsjAccessoryDao scmZsjAccessoryDao;
    @Resource
    ScmZsjTreatLicenceManager scmZsjTreatLicenceManager;

    @Resource
    ScmZsjGSPManager scmZsjGSPManager;
    @Resource
    ScmZsjCommercePromiseBookManager scmZsjCommercePromiseBookManager;

    @Resource
    ScmZsjCommerceAccreditManager scmZsjCommerceAccreditManager;

    @Resource
    ScmZsjCommerceWarehouseManager scmZsjCommerceWarehouseManager;

    @Resource
    ScmZsjCommerceShipAddressManager scmZsjCommerceShipAddressManager;

    @Resource
    ScmZsjCommerceEntruseBookManager scmZsjCommerceEntruseBookManager;


    @Resource
    ScmMasterDateFeignService scmMasterDateFeignService;

    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    @Override
    protected MyBatisDao<String, ScmZsjCommerceFirst> getDao() {
        return scmZsjCommerceFirstDao;
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmZsjCommerceFirst scmZsjCommerceFirst) {
        super.create(scmZsjCommerceFirst);
        String pk = scmZsjCommerceFirst.getId();
//    	//商业生产/仓库地址
        List<ScmZsjCommerceWarehouse> scmZsjCommerceWarehouseList = scmZsjCommerceFirst.getScmZsjCommerceWarehouseList();
        for (ScmZsjCommerceWarehouse scmZsjCommerceWarehouse : scmZsjCommerceWarehouseList) {
            scmZsjCommerceWarehouse.setCommerceFirstId(pk);
            scmZsjCommerceWarehouseManager.create(scmZsjCommerceWarehouse);
        }
        //商业许可证信息
        List<ScmZsjCommerceLicence> scmZsjCommerceLicenceList = scmZsjCommerceFirst.getScmZsjCommerceLicenceList();
        for (ScmZsjCommerceLicence scmZsjCommerceLicence : scmZsjCommerceLicenceList) {
            scmZsjCommerceLicence.setCommerceFirstId(pk);
            scmZsjCommerceLicenceManager.create(scmZsjCommerceLicence);
        }

        //商业营业执照信息
        List<ScmZsjCommerceBusiness> scmZsjCommerceBusinessList = scmZsjCommerceFirst.getScmZsjCommerceBusinessList();
        for (ScmZsjCommerceBusiness scmZsjCommerceBusiness : scmZsjCommerceBusinessList) {
            scmZsjCommerceBusiness.setCommerceFirstId(pk);
            scmZsjCommerceBusinessManager.create(scmZsjCommerceBusiness);
        }

        //商业医疗许可证
        List<ScmZsjTreatLicence> scmZsjTreatLicenceList = scmZsjCommerceFirst.getScmZsjTreatLicenceList();
        for (ScmZsjTreatLicence scmZsjTreatLicence : scmZsjTreatLicenceList) {
            scmZsjTreatLicence.setCommerceFirstId(pk);
            scmZsjTreatLicenceManager.create(scmZsjTreatLicence);
        }

        //商业GSP证书信息
        List<ScmZsjGSP> scmZsjGSPList = scmZsjCommerceFirst.getScmZsjGSPList();
        for (ScmZsjGSP scmZsjGSP : scmZsjGSPList) {
            scmZsjGSP.setCommerceFirstId(pk);
            scmZsjGSPManager.create(scmZsjGSP);
        }

        //商业承诺书
        List<ScmZsjCommercePromiseBook> scmZsjCommercePromiseBookList = scmZsjCommerceFirst.getScmZsjCommercePromiseBookList();
        for (ScmZsjCommercePromiseBook scmZsjCommercePromiseBook : scmZsjCommercePromiseBookList) {
            scmZsjCommercePromiseBook.setCommerceFirstId(pk);
            scmZsjCommercePromiseBookManager.create(scmZsjCommercePromiseBook);
        }

        //商业收货地址
        List<ScmZsjCommerceShipAddress> scmZsjCommerceShipAddressList = scmZsjCommerceFirst.getScmZsjCommerceShipAddressList();
        for (ScmZsjCommerceShipAddress scmZsjCommerceShipAddress : scmZsjCommerceShipAddressList) {
            scmZsjCommerceShipAddress.setCommerceFirstId(pk);
            scmZsjCommerceShipAddressManager.create(scmZsjCommerceShipAddress);
        }

        //商业委托书
        List<ScmZsjCommerceEntruseBook> scmZsjCommerceEntruseBookList = scmZsjCommerceFirst.getScmZsjCommerceEntruseBookList();
        for (ScmZsjCommerceEntruseBook scmZsjCommerceEntruseBook : scmZsjCommerceEntruseBookList) {
            scmZsjCommerceEntruseBook.setCommerceFirstId(pk);
            scmZsjCommerceEntruseBookManager.create(scmZsjCommerceEntruseBook);
        }
        //附件
        List<ScmZsjAccessory> scmZsjAccessoryList = scmZsjCommerceFirst.getScmZsjAccessoryList();
        for (ScmZsjAccessory scmZsjAccessory : scmZsjAccessoryList) {
        	scmZsjAccessory.setType("1");
        	scmZsjAccessory.setUpdateDate(new Date());
        	scmZsjAccessory.setDateId(pk);
        	scmZsjAccessoryManagerImpl.create(scmZsjAccessory);
		}
    }

    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmZsjCommerceWarehouseDao.delByMainId(entityId);
        scmZsjCommerceLicenceDao.delByMainId(entityId);
        scmZsjCommerceBusinessDao.delByMainId(entityId);
        scmZsjTreatLicenceDao.delByMainId(entityId);
        scmZsjGSPDao.delByMainId(entityId);
        scmZsjCommercePromiseBookDao.delByMainId(entityId);
        scmZsjCommerceShipAddressDao.delByMainId(entityId);
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
    public ScmZsjCommerceFirst get(String entityId) {
        ScmZsjCommerceFirst scmZsjCommerceFirst = super.get(entityId);
        if (scmZsjCommerceFirst == null) {
            throw new RuntimeException("首营数据不存在或已经被删除");
        }
        ScmZsjCommerce scmZsjCommerce = scmZsjCommerceManager.get(scmZsjCommerceFirst.getCommerceId());
        scmZsjCommerceFirst.setScmZsjCommerce(scmZsjCommerce);
        //商业生产/仓库地址
        List<ScmZsjCommerceWarehouse> scmZsjCommerceWarehouseList = scmZsjCommerceWarehouseDao.getByMainId(entityId);
        scmZsjCommerceFirst.setScmZsjCommerceWarehouseList(scmZsjCommerceWarehouseList);
        //商业许可证信息
        List<ScmZsjCommerceLicence> scmZsjCommerceLicenceList = scmZsjCommerceLicenceDao.getByMainId(entityId);
        scmZsjCommerceFirst.setScmZsjCommerceLicenceList(scmZsjCommerceLicenceList);
        //商业营业执照信息
        List<ScmZsjCommerceBusiness> scmZsjCommerceBusinessList = scmZsjCommerceBusinessDao.getByMainId(entityId);
        scmZsjCommerceFirst.setScmZsjCommerceBusinessList(scmZsjCommerceBusinessList);
        //商业医疗许可证
        List<ScmZsjTreatLicence> scmZsjTreatLicenceList = scmZsjTreatLicenceDao.getByMainId(entityId);
        scmZsjCommerceFirst.setScmZsjTreatLicenceList(scmZsjTreatLicenceList);
        //商业GSP证书信息
        List<ScmZsjGSP> scmZsjGSPList = scmZsjGSPDao.getByMainId(entityId);
        scmZsjCommerceFirst.setScmZsjGSPList(scmZsjGSPList);
        //商业承诺书
        List<ScmZsjCommercePromiseBook> scmZsjCommercePromiseBookList = scmZsjCommercePromiseBookDao.getByMainId(entityId);
        //获取商业承诺书详情
        List<ScmZsjCommercePromiseBook> list = new ArrayList<ScmZsjCommercePromiseBook>();
        for (ScmZsjCommercePromiseBook scmZsjCommercePromiseBook : scmZsjCommercePromiseBookList) {
            String promiseId = scmZsjCommercePromiseBook.getPromiseBookId();
            ScmZdPromiseBook scmZdPromiseBook = scmZdPromiseBookDao.get(promiseId);
            scmZsjCommercePromiseBook.setScmZdPromiseBook(scmZdPromiseBook);
            list.add(scmZsjCommercePromiseBook);
        }
        scmZsjCommerceFirst.setScmZsjCommercePromiseBookList(list);
        //商业收货地址
        List<ScmZsjCommerceShipAddress> scmZsjCommerceShipAddressList = scmZsjCommerceShipAddressDao.getByMainId(entityId);
        scmZsjCommerceFirst.setScmZsjCommerceShipAddressList(scmZsjCommerceShipAddressList);
        //商业委托书
        List<ScmZsjCommerceEntruseBook> scmZsjCommerceEntruseBookList = scmZsjCommerceEntruseBookDao.getByMainId(entityId);
        List<ScmZsjCommerceEntruseBook> entruseList = new ArrayList<ScmZsjCommerceEntruseBook>();
        for (ScmZsjCommerceEntruseBook scmZsjCommerceEntruseBook : scmZsjCommerceEntruseBookList) {
            List<ScmZsjCommerceAccredit> scmZsjCommerceAccreditList = scmZsjCommerceAccreditDao.getScmZsjCommerceAccreditList(scmZsjCommerceEntruseBook.getId());
            scmZsjCommerceEntruseBook.setScmZsjCommerceAccreditList(scmZsjCommerceAccreditList);
            entruseList.add(scmZsjCommerceEntruseBook);
        }
        scmZsjCommerceFirst.setScmZsjCommerceEntruseBookList(entruseList);
        
        List<ScmZsjAccessory> byTypeAndDataId = scmZsjAccessoryDao.getByTypeAndDataId("1", entityId);
        scmZsjCommerceFirst.setScmZsjAccessoryList(byTypeAndDataId);
        return scmZsjCommerceFirst;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmZsjCommerceFirst scmZsjCommerceFirst) {
        ScmZsjCommerceFirst scmZsjCommerceFirst2 = scmZsjCommerceFirstDao.get(scmZsjCommerceFirst.getId());
        if ("1".equals(scmZsjCommerceFirst2.getApprovalState())) {
            throw new RuntimeException("审批中数据不可修改");
        }
        scmZsjCommerceFirst.setApprovalState("0");
        super.update(scmZsjCommerceFirst);
        String id = scmZsjCommerceFirst.getId();
        List<ScmZsjCommerceBusiness> byMainId = scmZsjCommerceBusinessDao.getByMainId(id);
        List<ScmZsjCommerceLicence> byMainId2 = scmZsjCommerceLicenceDao.getByMainId(id);
        List<ScmZsjTreatLicence> byMainId3 = scmZsjTreatLicenceDao.getByMainId(id);
        List<ScmZsjCommerceLicence> scmZsjCommerceLicenceList = scmZsjCommerceFirst.getScmZsjCommerceLicenceList();
        List<ScmZsjCommerceBusiness> scmZsjCommerceBusinessList = scmZsjCommerceFirst.getScmZsjCommerceBusinessList();
        List<ScmZsjTreatLicence> scmZsjTreatLicenceList = scmZsjCommerceFirst.getScmZsjTreatLicenceList();
        List<ScmZsjGSP> scmZsjGSPList = scmZsjCommerceFirst.getScmZsjGSPList();
        //对比更新修改记录
        try {
            if (scmZsjCommerceLicenceList.size() > 0) {
                //商业许可证信息
                ScmZsjCommerceLicence scmZsjCommerceLicence2 = scmZsjCommerceLicenceList.get(0);
                String contrast = ContrasUtil.Contrast(byMainId2.get(0), scmZsjCommerceLicence2, id, "syxg");
                if (!StringUtils.isEmpty(contrast)) {
                    scmMasterDateFeignService.inserScmLogRecord(contrast);
                }
            }
            if (scmZsjCommerceBusinessList.size() > 0) {
                //商业营业执照信息
                ScmZsjCommerceBusiness scmZsjCommerceBusiness2 = scmZsjCommerceBusinessList.get(0);
                String contrast2 = ContrasUtil.Contrast(byMainId.get(0), scmZsjCommerceBusiness2, id, "syxg");
                if (!StringUtils.isEmpty(contrast2)) {
                    scmMasterDateFeignService.inserScmLogRecord(contrast2);
                }
            }
            if (scmZsjTreatLicenceList.size() > 0) {
                //商业医疗许可证
                ScmZsjTreatLicence scmZsjTreatLicence2 = scmZsjTreatLicenceList.get(0);
                String contrast3 = ContrasUtil.Contrast(byMainId3.get(0), scmZsjTreatLicence2, id, "syxg");
                if (!StringUtils.isEmpty(contrast3)) {
                    scmMasterDateFeignService.inserScmLogRecord(contrast3);
                }
            }
           
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        //商业生产/仓库地址
        scmZsjCommerceWarehouseDao.delByMainId(id);
        List<ScmZsjCommerceWarehouse> scmZsjCommerceWarehouseList = scmZsjCommerceFirst.getScmZsjCommerceWarehouseList();
        for (ScmZsjCommerceWarehouse scmZsjCommerceWarehouse : scmZsjCommerceWarehouseList) {
            scmZsjCommerceWarehouse.setCommerceFirstId(id);
            scmZsjCommerceWarehouse.setId(UniqueIdUtil.getSuid());
            scmZsjCommerceWarehouseManager.create(scmZsjCommerceWarehouse);
        }
        //商业许可证信息
        scmZsjCommerceLicenceDao.delByMainId(id);
        for (ScmZsjCommerceLicence scmZsjCommerceLicence : scmZsjCommerceLicenceList) {
            scmZsjCommerceLicence.setCommerceFirstId(id);
            scmZsjCommerceLicence.setId(UniqueIdUtil.getSuid());
            scmZsjCommerceLicenceManager.create(scmZsjCommerceLicence);
        }
        //商业营业执照信息
        scmZsjCommerceBusinessDao.delByMainId(id);
        for (ScmZsjCommerceBusiness scmZsjCommerceBusiness : scmZsjCommerceBusinessList) {
            scmZsjCommerceBusiness.setCommerceFirstId(id);
            scmZsjCommerceBusiness.setId(UniqueIdUtil.getSuid());
            scmZsjCommerceBusinessManager.create(scmZsjCommerceBusiness);
        }
        
        //商业医疗许可证
        scmZsjTreatLicenceDao.delByMainId(id);
        for (ScmZsjTreatLicence scmZsjTreatLicence : scmZsjTreatLicenceList) {
            scmZsjTreatLicence.setCommerceFirstId(id);
            scmZsjTreatLicence.setId(UniqueIdUtil.getSuid());
            scmZsjTreatLicenceManager.create(scmZsjTreatLicence);
        }
        
        //商业GSP证书信息
        scmZsjGSPDao.delByMainId(id);

        for (ScmZsjGSP scmZsjGSP : scmZsjGSPList) {
            scmZsjGSP.setCommerceFirstId(id);
            scmZsjGSP.setId(UniqueIdUtil.getSuid());
            scmZsjGSPManager.create(scmZsjGSP);
        }
        
        //商业承诺书
        scmZsjCommercePromiseBookDao.delByMainId(id);
        List<ScmZsjCommercePromiseBook> scmZsjCommercePromiseBookList = scmZsjCommerceFirst.getScmZsjCommercePromiseBookList();
        for (ScmZsjCommercePromiseBook scmZsjCommercePromiseBook : scmZsjCommercePromiseBookList) {
            scmZsjCommercePromiseBook.setCommerceFirstId(id);
            scmZsjCommercePromiseBook.setId(UniqueIdUtil.getSuid());
            scmZsjCommercePromiseBookManager.create(scmZsjCommercePromiseBook);
        }
        
        //商业收货地址
        scmZsjCommerceShipAddressDao.delByMainId(id);
        List<ScmZsjCommerceShipAddress> scmZsjCommerceShipAddressList = scmZsjCommerceFirst.getScmZsjCommerceShipAddressList();
        for (ScmZsjCommerceShipAddress scmZsjCommerceShipAddress : scmZsjCommerceShipAddressList) {
            scmZsjCommerceShipAddress.setCommerceFirstId(id);
            scmZsjCommerceShipAddress.setId(UniqueIdUtil.getSuid());
            scmZsjCommerceShipAddressManager.create(scmZsjCommerceShipAddress);
        }
        
        //商业委托书,先删除商业授权销售品种再删
        List<ScmZsjCommerceEntruseBook> scmZsjCommerceEntruseBookList = scmZsjCommerceEntruseBookDao.getByMainId(id);
        for (ScmZsjCommerceEntruseBook scmZsjCommerceEntruseBook : scmZsjCommerceEntruseBookList) {
            String entrustId = scmZsjCommerceEntruseBook.getId();
            scmZsjCommerceAccreditDao.delByMainId(entrustId);
        }
        scmZsjCommerceEntruseBookDao.delByMainId(id);
        //保存新数据
        List<ScmZsjCommerceEntruseBook> entruseList = scmZsjCommerceFirst.getScmZsjCommerceEntruseBookList();
        for (ScmZsjCommerceEntruseBook scmZsjCommerceEntruseBook : entruseList) {
            scmZsjCommerceEntruseBook.setCommerceFirstId(id);
            scmZsjCommerceEntruseBook.setId(UniqueIdUtil.getSuid());
            scmZsjCommerceEntruseBookManager.create(scmZsjCommerceEntruseBook);
        }
        
        //附件
        List<ScmZsjAccessory> scmZsjAccessoryList = scmZsjCommerceFirst.getScmZsjAccessoryList();
        scmZsjAccessoryDao.delByMainId(id);
        for (ScmZsjAccessory scmZsjAccessory : scmZsjAccessoryList) {
        	scmZsjAccessory.setType("1");
        	scmZsjAccessory.setUpdateDate(new Date());
        	scmZsjAccessory.setDateId(id);
        	scmZsjAccessory.setId(UniqueIdUtil.getSuid());
        	scmZsjAccessoryManagerImpl.create(scmZsjAccessory);
		}
    }

    @Override
    public void sendApply(ScmZsjCommerceFirst scmZsjCommerceFirst) {
        //审批状态    0:待提交,1:待审批,2:已通过
        //判断提交的商业信息是否存在,如果不存在,在基础数据保存一份
        scmZsjCommerceManager.isExist(scmZsjCommerceFirst.getScmZsjCommerce());

        scmZsjCommerceFirst.setCommerceId(scmZsjCommerceFirst.getScmZsjCommerce().getId());
        //判断当前货主下是否已经首营过
        List<ScmZsjCommerceFirst> commerceFirstByOwnerIdAndCommerceId = scmZsjCommerceFirstDao.getCommerceFirstByOwnerIdAndCommerceId(scmZsjCommerceFirst);
        if (commerceFirstByOwnerIdAndCommerceId.size() != 0) {
            throw new RuntimeException("当前商业已经发起审批或审批已经通过,请勿重复提交");
        }
        //调整审批状态
        scmZsjCommerceFirst.setApprovalState("0");
        create(scmZsjCommerceFirst);
        StartFlowParam startFlowParam = new StartFlowParam("sysy", "SCM", "approvalId");
        startFlowParam.setFormType("frame");
        CustomStartResult customStartResult = null;
        try {
            System.out.println("发起商业首营申请");
            customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
            String approvalId = customStartResult.getInstId();
            scmZsjCommerceFirst.setApprovalId(approvalId);
            scmZsjCommerceFirst.setApprovalState("1");
            super.create(scmZsjCommerceFirst);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("发起流程失败");
        }
    }

    @Override
    public void isSave(ScmZsjCommerceFirst scmZsjCommerceFirst) {
        List<ScmZsjCommerceFirst> scmZsjCommerceFirsts = scmZsjCommerceFirstDao.isSave(scmZsjCommerceFirst);
        if (scmZsjCommerceFirsts.size() > 0) {
            throw new RuntimeException("当前商业首营信息已暂存或已经发起申请,请勿重复提交");
        }
    }

    @Override
    public ScmZsjCommerceFirst getCommerceFirstByApprovalId(String approvalId) {
        ScmZsjCommerceFirst scmZsjCommerceFirst = scmZsjCommerceFirstDao.getCommerceFirstByApprovalId(approvalId);
        if (scmZsjCommerceFirst == null) {
            throw new RuntimeException("查询数据不存在或已经被删除");
        }
        ScmZsjCommerceFirst scmZsjCommerceFirst2 = get(scmZsjCommerceFirst.getId());
        return scmZsjCommerceFirst2;
    }

    @Override
    public void endApply(JsonNode jsonNode) {
        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        ScmZsjCommerceFirst productFirstByApprovalId = scmZsjCommerceFirstDao.getCommerceFirstByApprovalId(approvalId);
        if (productFirstByApprovalId == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
        	//审批状态调整为通过
            productFirstByApprovalId.setApprovalState("2");
            productFirstByApprovalId.setIsPassed("1");
            scmZsjCommerceFirstDao.update(productFirstByApprovalId);
        } else if ("agree".equals(actionName)) {
        } else if ("reject".equals(actionName)) {
        } else if ("backToStart".equals(actionName)) {
        } else if ("opposeTrans".equals(actionName)) {
        } else if ("endProcess".equals(actionName)) {
        	productFirstByApprovalId.setApprovalState("3");
        	scmZsjCommerceFirstDao.update(productFirstByApprovalId);
        } 
    }

    @Override
    public void updateSendApply(ScmZsjCommerceFirst scmZsjCommerceFirst) {
        ScmZsjCommerceFirst scmZsjCommerceFirst2 = scmZsjCommerceFirstDao.get(scmZsjCommerceFirst.getId());
        if ("1".equals(scmZsjCommerceFirst2.getApprovalState())) {
            //数据在审批中,不可修改编辑
            throw new RuntimeException("当前数据已经在审批中,不可重复提交");
        } else {
            //修改其他关联性数据
            scmZsjCommerceFirst.setApprovalState("0");
            update(scmZsjCommerceFirst);
            StartFlowParam startFlowParam = new StartFlowParam("sysy", "SCM", "approvalId");
            startFlowParam.setFormType("frame");
            CustomStartResult customStartResult = null;
            try {
                System.out.println("发起商业首营修改申请");
                customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
                String approvalId = customStartResult.getInstId();
                scmZsjCommerceFirst.setApprovalId(approvalId);
                scmZsjCommerceFirst.setApprovalState("1");
                scmZsjCommerceFirstDao.update(scmZsjCommerceFirst);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("发起流程失败");
            }

        }
    }

    @Override
    public PageList<ScmZsjCommerce> licenseWaring(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 2147483647, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        queryFilter.addFilter("type", "1", QueryOP.EQUAL, FieldRelation.AND);
        List<ScmZsjCommerce> query = scmZsjCommerceFirstDao.licenseWaring(queryFilter.getParams());
        return new PageList(query);
    }

    @Override
    public PageList<ScmZsjCommerce> gsplist(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 2147483647, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjCommerce> query = scmZsjCommerceFirstDao.gsplist(queryFilter.getParams());
        return new PageList(query);

    }

    @Override
    public PageList<ScmZsjCommerce> creditlist(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 2147483647, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjCommerce> query = scmZsjCommerceFirstDao.creditlist(queryFilter.getParams());
        return new PageList(query);
    }

    @Override
    public PageList<ScmZsjCommerce> qlist(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 2147483647, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmZsjCommerce> query = scmZsjCommerceFirstDao.qlist(queryFilter.getParams());
        return new PageList(query);
    }

    @Override
    public String startOrStop(String id) {
        ScmZsjCommerceFirst scmZsjCommerceFirst = scmZsjCommerceFirstDao.get(id);
        if (scmZsjCommerceFirst == null) {
            throw new RuntimeException("首营数据获取异常");
        }
        scmZsjCommerceFirst.setApprovalState("0");
        scmZsjCommerceFirst.setIsPassed("0");
        scmZsjCommerceFirstDao.update(scmZsjCommerceFirst);
        return "0";
    }

    @Override
    public boolean getCommerceStateByFirstId(String commerceFirstId) {
        //取到当前时间戳
        long time = new Date().getTime();
        ScmZsjCommerceFirst scmZsjCommerceFirst = get(commerceFirstId);
        if (scmZsjCommerceFirst == null) {
            return false;
        }

        ScmZsjCommerce scmZsjCommerce = scmZsjCommerceFirst.getScmZsjCommerce();
        if (scmZsjCommerce == null) {
            return false;
        }
        List<ScmZsjCommerceBusiness> scmZsjCommerceBusinessList = scmZsjCommerceFirst.getScmZsjCommerceBusinessList();
        if (scmZsjCommerceBusinessList.size() == 0) {
            return false;
        }
        for (ScmZsjCommerceBusiness scmZsjCommerceBusiness : scmZsjCommerceBusinessList) {
            Date date = scmZsjCommerceBusiness.getLicenseValidity();
            if (date == null) {
                return false;
            } else {
                if (date.getTime() < time) {
                    return false;
                }
            }
        }
        List<ScmZsjCommerceEntruseBook> scmZsjCommerceEntruseBookList = scmZsjCommerceFirst.getScmZsjCommerceEntruseBookList();
        if (scmZsjCommerceBusinessList.size() == 0) {
            return false;
        }
        for (ScmZsjCommerceEntruseBook scmZsjCommerceEntruseBook : scmZsjCommerceEntruseBookList) {
            Date date2 = scmZsjCommerceEntruseBook.getIdValidity();
            Date date = scmZsjCommerceEntruseBook.getEntrustValidity();
            if (date == null) {
                return false;
            } else {
                if (date.getTime() < time) {
                    return false;
                }
            }
            if (date2 == null) {
                return false;
            } else {
                if (date2.getTime() < time) {
                    return false;
                }
            }
        }
        List<ScmZsjCommerceLicence> scmZsjCommerceLicenceList = scmZsjCommerceFirst.getScmZsjCommerceLicenceList();
        if (scmZsjCommerceBusinessList.size() == 0) {
            return false;
        }
        for (ScmZsjCommerceLicence scmZsjCommerceLicence : scmZsjCommerceLicenceList) {
            Date date = scmZsjCommerceLicence.getValidity();
            if (date == null) {
                return false;
            } else {
                if (date.getTime() < time) {
                    return false;
                }
            }
        }
        List<ScmZsjCommercePromiseBook> scmZsjCommercePromiseBookList = scmZsjCommerceFirst.getScmZsjCommercePromiseBookList();
        if (scmZsjCommerceBusinessList.size() == 0) {
            return false;
        }
        for (ScmZsjCommercePromiseBook scmZsjCommercePromiseBook : scmZsjCommercePromiseBookList) {
            Date date = scmZsjCommercePromiseBook.getValidity();
            if (date == null) {
                return false;
            } else {
                if (date.getTime() < time) {
                    return false;
                }
            }
        }
        if ("0".equals(scmZsjCommerce.getCommerceState())) {
            //GSP
            List<ScmZsjGSP> scmZsjGSPList = scmZsjCommerceFirst.getScmZsjGSPList();
            if (scmZsjCommerceBusinessList.size() == 0) {
                return false;
            }
            for (ScmZsjGSP scmZsjGSP : scmZsjGSPList) {
                Date date = scmZsjGSP.getValidity();
                if (date == null) {
                    return false;
                } else {
                    if (date.getTime() < time) {
                        return false;
                    }
                }
            }
        } else {
            //医疗许可证
            List<ScmZsjTreatLicence> scmZsjTreatLicenceList = scmZsjCommerceFirst.getScmZsjTreatLicenceList();
            if (scmZsjCommerceBusinessList.size() == 0) {
                return false;
            }
            for (ScmZsjTreatLicence scmZsjTreatLicence : scmZsjTreatLicenceList) {
                Date date = scmZsjTreatLicence.getValidity();
                if (date == null) {
                    return false;
                } else {
                    if (date.getTime() < time) {
                        return false;
                    }
                }
            }
        }


        Date qualityValidity = scmZsjCommerceFirst.getQualityValidity();
        if (qualityValidity != null) {
            return qualityValidity.getTime() >= time;

        } else {
            return false;
        }
    }

    @Override
    public Set<String> getAccreditProCodes(String commerceFirstId) {
        ScmZsjCommerceFirst bySupplierId = scmZsjCommerceFirstDao.get(commerceFirstId);
        if (bySupplierId == null) {
            throw new RuntimeException("数据错误，没有该商业的首营数据");
        }
        List<ScmZsjCommerceEntruseBook> byMainId = scmZsjCommerceEntruseBookManager.getByCommerceFirstId(bySupplierId.getId());
        Set<String> codes = new HashSet<>();
        for (ScmZsjCommerceEntruseBook book : byMainId) {
            List<ScmZsjCommerceAccredit> scmZsjCommerceAccreditList = book.getScmZsjCommerceAccreditList();
            for (ScmZsjCommerceAccredit accredit : scmZsjCommerceAccreditList) {
                codes.add(accredit.getProductCode());
            }
        }
        return codes;
    }

}
