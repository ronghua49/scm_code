package com.winway.scm.persistence.manager.impl;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.query.*;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.UniqueIdUtil;
import com.mysql.fabric.xmlrpc.base.Data;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.ContrasUtil;
import com.winway.scm.model.*;
import com.winway.scm.persistence.dao.*;
import com.winway.scm.persistence.manager.*;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <pre>
 *
 * 描述：供应商首营记录表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjSupplierFirstManager")
public class ScmZsjSupplierFirstManagerImpl extends AbstractManagerImpl<String, ScmZsjSupplierFirst>
		implements ScmZsjSupplierFirstManager {
	@Resource
	ScmZsjSupplierFirstDao scmZsjSupplierFirstDao;
	@Resource
	ScmZsjSupplierDao scmZsjSupplierDao;
	@Resource
	ScmZsjSupplierWarehouseDao scmZsjSupplierWarehouseDao;
	@Resource
	ScmZsjSupplierLicenceDao scmZsjSupplierLicenceDao;
	@Resource
	ScmZsjSupplierBusinessDao scmZsjSupplierBusinessDao;
	@Resource
	ScmZsjGMPDao scmZsjGMPDao;
    @Resource
    ScmZsjAccessoryDao scmZsjAccessoryDao;
	@Resource
	ScmZsjSupplierEntruseBookDao scmZsjSupplierEntruseBookDao;
	@Resource
	ScmZsjSupplierEntruseBookManager scmZsjSupplierEntruseBookManager;
	@Resource
	ScmZsjSupplierShipAddressDao scmZsjSupplierShipAddressDao;
	@Resource
	ScmZsjSupplierPromiseBookDao scmZsjSupplierPromiseBookDao;
	@Resource
	ScmZsjSupplierAccreditDao scmZsjSupplierAccreditDao;
	@Resource
	ScmZdPromiseBookDao scmZdPromiseBookDao;
	@Resource
	ScmZsjSupplierManager scmZsjSupplierManager;
	@Resource
	ScmZsjSupplierWarehouseManager scmZsjSupplierWarehouseManager;
	@Resource
	ScmZsjSupplierLicenceManager scmZsjSupplierLicenceManager;
	@Resource
	ScmZsjSupplierBusinessManager scmZsjSupplierBusinessManager;
	@Resource
	ScmZsjGMPManager scmZsjGMPManager;
	@Resource
	ScmZsjAccessoryManagerImpl scmZsjAccessoryManagerImpl;
	@Resource
	ScmZsjSupplierAccreditManager scmZsjSupplierAccreditManager;
	@Resource
	ScmZsjSupplierShipAddressManager scmZsjSupplierShipAddressManager;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	@Resource
	BpmRuntimeFeignService bpmRuntimeFeignService;

	@Override
	protected MyBatisDao<String, ScmZsjSupplierFirst> getDao() {
		return scmZsjSupplierFirstDao;
	}

	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmZsjSupplierFirst scmZsjSupplierFirst) {
		scmZsjSupplierManager.isExist(scmZsjSupplierFirst.getScmZsjSupplier());
		super.create(scmZsjSupplierFirst);
		String pk = scmZsjSupplierFirst.getId();
		if(scmZsjSupplierFirst.getQualityValidity()!=null && scmZsjSupplierFirst.getQualityValidity().getTime()<=new Date().getTime()) {
			throw new RuntimeException("药品质量保证协议有效期不可小于今天日期");
		}
		// 供应商生产/仓库地址
		List<ScmZsjSupplierWarehouse> scmZsjSupplierWarehouseList = scmZsjSupplierFirst
				.getScmZsjSupplierWarehouseList();

		for (ScmZsjSupplierWarehouse scmZsjSupplierWarehouse : scmZsjSupplierWarehouseList) {
			scmZsjSupplierWarehouse.setSupplierFirstId(pk);
			scmZsjSupplierWarehouseManager.create(scmZsjSupplierWarehouse);
		}
		// 供应商许可证信息
		List<ScmZsjSupplierLicence> scmZsjSupplierLicenceList = scmZsjSupplierFirst.getScmZsjSupplierLicenceList();

		for (ScmZsjSupplierLicence scmZsjSupplierLicence : scmZsjSupplierLicenceList) {
			if (scmZsjSupplierLicence.getValidity()!=null && scmZsjSupplierLicence.getValidity().getTime() <= new Date().getTime()) {
				throw new RuntimeException("许可证有效期不可小于今天日期");
			}
			scmZsjSupplierLicence.setSupplierFirstId(pk);
			scmZsjSupplierLicenceManager.create(scmZsjSupplierLicence);
		}
		// 供应商营业执照信息
		List<ScmZsjSupplierBusiness> scmZsjSupplierBusinessList = scmZsjSupplierFirst.getScmZsjSupplierBusinessList();

		for (ScmZsjSupplierBusiness scmZsjSupplierBusiness : scmZsjSupplierBusinessList) {
			if (scmZsjSupplierBusiness.getLicenseValidity()!=null && scmZsjSupplierBusiness.getLicenseValidity().getTime() <= new Date().getTime()) {
				throw new RuntimeException("营业执照有效期不可小于今天日期");
			}
			if (scmZsjSupplierBusiness.getYearReportValidity()!=null && scmZsjSupplierBusiness.getYearReportValidity().getTime() <= new Date().getTime()) {
				throw new RuntimeException("营业执照年度有效期不可小于今天日期");
			}
			scmZsjSupplierBusiness.setSupplierFirstId(pk);

			scmZsjSupplierBusinessManager.create(scmZsjSupplierBusiness);
		}
		// 供应商GMP证书信息
		List<ScmZsjGMP> scmZsjGMPList = scmZsjSupplierFirst.getScmZsjGMPList();

		for (ScmZsjGMP scmZsjGMP : scmZsjGMPList) {
			if (scmZsjGMP.getValidity()!=null && scmZsjGMP.getValidity().getTime() <= new Date().getTime()) {
				throw new RuntimeException("GMP证书有效期不可小于今天日期");
			}
			scmZsjGMP.setSupplierFirstId(pk);
			scmZsjGMPManager.create(scmZsjGMP);
		}
		// 供应商委托书
		List<ScmZsjSupplierEntruseBook> scmZsjSupplierEntruseBookList = scmZsjSupplierFirst
				.getScmZsjSupplierEntruseBookList();

		for (ScmZsjSupplierEntruseBook scmZsjSupplierEntruseBook : scmZsjSupplierEntruseBookList) {
			if (scmZsjSupplierEntruseBook.getEntrustValidity()!=null && scmZsjSupplierEntruseBook.getEntrustValidity().getTime() <= new Date().getTime()) {
				throw new RuntimeException("委托书有效期不可小于今天日期");
			}
			if (scmZsjSupplierEntruseBook.getIdValidity()!=null && scmZsjSupplierEntruseBook.getIdValidity().getTime() <= new Date().getTime()) {
				throw new RuntimeException("委托书身份证有效期不可小于今天日期");
			}
			scmZsjSupplierEntruseBook.setSupplierFirstId(pk);
			scmZsjSupplierEntruseBookManager.create(scmZsjSupplierEntruseBook);
			String entruseId = scmZsjSupplierEntruseBook.getId();
			// 保存供应商授权销售品种
			List<ScmZsjSupplierAccredit> scmZsjSupplierAccreditList = scmZsjSupplierEntruseBook
					.getScmZsjSupplierAccreditList();
			for (ScmZsjSupplierAccredit scmZsjSupplierAccredit : scmZsjSupplierAccreditList) {
				scmZsjSupplierAccredit.setEntrustId(entruseId);
				scmZsjSupplierAccreditManager.create(scmZsjSupplierAccredit);
			}
		}
		// 收货地址
		List<ScmZsjSupplierShipAddress> scmZsjSupplierShipAddressList = scmZsjSupplierFirst
				.getScmZsjSupplierShipAddressList();

		for (ScmZsjSupplierShipAddress scmZsjSupplierShipAddress : scmZsjSupplierShipAddressList) {
			scmZsjSupplierShipAddress.setSupplierFirstId(pk);
			scmZsjSupplierShipAddressManager.create(scmZsjSupplierShipAddress);
		}
		
        //附件
        List<ScmZsjAccessory> scmZsjAccessoryList = scmZsjSupplierFirst.getScmZsjAccessoryList();
        for (ScmZsjAccessory scmZsjAccessory : scmZsjAccessoryList) {
        	scmZsjAccessory.setType("0");
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
		scmZsjSupplierWarehouseDao.delByMainId(entityId);
		scmZsjSupplierLicenceDao.delByMainId(entityId);
		scmZsjSupplierBusinessDao.delByMainId(entityId);
		scmZsjGMPDao.delByMainId(entityId);
		scmZsjSupplierEntruseBookDao.delByMainId(entityId);
		scmZsjSupplierShipAddressDao.delByMainId(entityId);
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
	public ScmZsjSupplierFirst get(String entityId) {
		ScmZsjSupplierFirst scmZsjSupplierFirst = super.get(entityId);
		if (scmZsjSupplierFirst == null) {
			throw new RuntimeException("首营数据不存在或已经被删除");
		}
		ScmZsjSupplier scmZsjSupplier = scmZsjSupplierManager.get(scmZsjSupplierFirst.getSupplierId());
		scmZsjSupplierFirst.setScmZsjSupplier(scmZsjSupplier);
		// 供应商生产/仓库地址
		List<ScmZsjSupplierWarehouse> scmZsjSupplierWarehouseList = scmZsjSupplierWarehouseDao.getByMainId(entityId);
		scmZsjSupplierFirst.setScmZsjSupplierWarehouseList(scmZsjSupplierWarehouseList);
		// 供应商许可证信息
		List<ScmZsjSupplierLicence> scmZsjSupplierLicenceList = scmZsjSupplierLicenceDao.getByMainId(entityId);
		scmZsjSupplierFirst.setScmZsjSupplierLicenceList(scmZsjSupplierLicenceList);
		// 供应商营业执照信息
		List<ScmZsjSupplierBusiness> scmZsjSupplierBusinessList = scmZsjSupplierBusinessDao.getByMainId(entityId);
		scmZsjSupplierFirst.setScmZsjSupplierBusinessList(scmZsjSupplierBusinessList);
		// 供应商GMP证书信息
		List<ScmZsjGMP> scmZsjGMPList = scmZsjGMPDao.getByMainId(entityId);
		scmZsjSupplierFirst.setScmZsjGMPList(scmZsjGMPList);
		// 供应商委托书
		List<ScmZsjSupplierEntruseBook> scmZsjSupplierEntruseBookList = scmZsjSupplierEntruseBookDao
				.getByMainId(entityId);
		List<ScmZsjSupplierEntruseBook> entruseList = new ArrayList<ScmZsjSupplierEntruseBook>();
		// 授权销售品种
		for (ScmZsjSupplierEntruseBook scmZsjSupplierEntruseBook : scmZsjSupplierEntruseBookList) {
			List<ScmZsjSupplierAccredit> scmZsjSupplierAccreditList = scmZsjSupplierAccreditDao
					.getByMainId(scmZsjSupplierEntruseBook.getId());
			scmZsjSupplierEntruseBook.setScmZsjSupplierAccreditList(scmZsjSupplierAccreditList);
            entruseList.add(scmZsjSupplierEntruseBook);
		}
		scmZsjSupplierFirst.setScmZsjSupplierEntruseBookList(entruseList);
		// 收货地址
		List<ScmZsjSupplierShipAddress> scmZsjSupplierShipAddressList = scmZsjSupplierShipAddressDao
				.getByMainId(entityId);
		scmZsjSupplierFirst.setScmZsjSupplierShipAddressList(scmZsjSupplierShipAddressList);
        List<ScmZsjAccessory> byTypeAndDataId = scmZsjAccessoryDao.getByTypeAndDataId("0", entityId);
        scmZsjSupplierFirst.setScmZsjAccessoryList(byTypeAndDataId);
		return scmZsjSupplierFirst;
	}

	/**
	 * 更新实体同时更新子表记录
	 */
	public void update(ScmZsjSupplierFirst scmZsjSupplierFirst) {
		super.update(scmZsjSupplierFirst);
		String id = scmZsjSupplierFirst.getId();
		if(scmZsjSupplierFirst.getQualityValidity()!=null && scmZsjSupplierFirst.getQualityValidity().getTime()<=new Date().getTime()) {
			throw new RuntimeException("药品质量保证协议有效期不可小于今天日期");
		}
		// 供应商生产/仓库地址
		List<ScmZsjSupplierWarehouse> list = scmZsjSupplierWarehouseDao.getByMainId(id);
		scmZsjSupplierWarehouseDao.delByMainId(id);
		List<ScmZsjSupplierWarehouse> scmZsjSupplierWarehouseList = scmZsjSupplierFirst
				.getScmZsjSupplierWarehouseList();
		// 供应商许可证信息
		List<ScmZsjSupplierLicence> byMainId = scmZsjSupplierLicenceDao.getByMainId(id);
		scmZsjSupplierLicenceDao.delByMainId(id);
		List<ScmZsjSupplierLicence> scmZsjSupplierLicenceList = scmZsjSupplierFirst.getScmZsjSupplierLicenceList();
		// 供应商营业执照信息
		List<ScmZsjSupplierBusiness> byMainId2 = scmZsjSupplierBusinessDao.getByMainId(id);
		scmZsjSupplierBusinessDao.delByMainId(id);
		List<ScmZsjSupplierBusiness> scmZsjSupplierBusinessList = scmZsjSupplierFirst.getScmZsjSupplierBusinessList();
		// 供应商生产/仓库地址
		try {
			if (scmZsjSupplierWarehouseList.size() > 0) {
				ScmZsjSupplierWarehouse warehouse = scmZsjSupplierWarehouseList.get(0);
				String contrast = ContrasUtil.Contrast(list.get(0), warehouse, id, "gysxg");
				if (!StringUtils.isEmpty(contrast)) {
					scmMasterDateFeignService.inserScmLogRecord(contrast);
				}
			}
			if (scmZsjSupplierLicenceList.size() > 0) {
				ScmZsjSupplierLicence licence = scmZsjSupplierLicenceList.get(0);
				String contrast = ContrasUtil.Contrast(byMainId.get(0), licence, id, "gysxg");
				if (!StringUtils.isEmpty(contrast)) {
					scmMasterDateFeignService.inserScmLogRecord(contrast);
				}
			}
			if (scmZsjSupplierBusinessList.size() > 0) {
				ScmZsjSupplierBusiness business = scmZsjSupplierBusinessList.get(0);
				String contrast = ContrasUtil.Contrast(byMainId2.get(0), business, id, "gysxg");
				if (!StringUtils.isEmpty(contrast)) {
					scmMasterDateFeignService.inserScmLogRecord(contrast);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (ScmZsjSupplierWarehouse scmZsjSupplierWarehouse : scmZsjSupplierWarehouseList) {
			scmZsjSupplierWarehouse.setSupplierFirstId(id);
			scmZsjSupplierWarehouse.setId(UniqueIdUtil.getSuid());
			scmZsjSupplierWarehouseManager.create(scmZsjSupplierWarehouse);
		}
		// 供应商许可证信息
		for (ScmZsjSupplierLicence scmZsjSupplierLicence : scmZsjSupplierLicenceList) {
			if (scmZsjSupplierLicence.getValidity()!=null && scmZsjSupplierLicence.getValidity().getTime() <= new Date().getTime()) {
				throw new RuntimeException("许可证有效期不可小于今天日期");
			}
			scmZsjSupplierLicence.setSupplierFirstId(id);
			scmZsjSupplierLicence.setId(UniqueIdUtil.getSuid());
			scmZsjSupplierLicenceManager.create(scmZsjSupplierLicence);
		}
		// 供应商营业执照信息
		for (ScmZsjSupplierBusiness scmZsjSupplierBusiness : scmZsjSupplierBusinessList) {
			if (scmZsjSupplierBusiness.getLicenseValidity()!=null && scmZsjSupplierBusiness.getLicenseValidity().getTime() <= new Date().getTime()) {
				throw new RuntimeException("营业执照有效期不可小于今天日期");
			}
			if (scmZsjSupplierBusiness.getYearReportValidity()!=null && scmZsjSupplierBusiness.getYearReportValidity().getTime() <= new Date().getTime()) {
				throw new RuntimeException("营业执照年度有效期不可小于今天日期");
			}
			scmZsjSupplierBusiness.setSupplierFirstId(id);
			scmZsjSupplierBusiness.setId(UniqueIdUtil.getSuid());
			scmZsjSupplierBusinessManager.create(scmZsjSupplierBusiness);
		}
		// 供应商GMP证书信息
		scmZsjGMPDao.delByMainId(id);
		List<ScmZsjGMP> scmZsjGMPList = scmZsjSupplierFirst.getScmZsjGMPList();
		for (ScmZsjGMP scmZsjGMP : scmZsjGMPList) {
			if (scmZsjGMP.getValidity()!=null && scmZsjGMP.getValidity().getTime() <= new Date().getTime()) {
				throw new RuntimeException("GMP证书有效期不可小于今天日期");
			}
			scmZsjGMP.setSupplierFirstId(id);
			scmZsjGMP.setId(UniqueIdUtil.getSuid());
			scmZsjGMPManager.create(scmZsjGMP);
		}
		// 供应商委托书,先删除商业授权销售品种再删
		List<ScmZsjSupplierEntruseBook> scmZsjSupplierEntruseBookList = scmZsjSupplierEntruseBookDao.getByMainId(id);
		for (ScmZsjSupplierEntruseBook scmZsjSupplierEntruseBook : scmZsjSupplierEntruseBookList) {
			String entrustId = scmZsjSupplierEntruseBook.getId();
			scmZsjSupplierAccreditDao.delByMainId(entrustId);
		}
		scmZsjSupplierEntruseBookDao.delByMainId(id);
		List<ScmZsjSupplierEntruseBook> entruseList = scmZsjSupplierFirst.getScmZsjSupplierEntruseBookList();
		for (ScmZsjSupplierEntruseBook scmZsjSupplierEntruseBook : entruseList) {
			if (scmZsjSupplierEntruseBook.getEntrustValidity()!=null && scmZsjSupplierEntruseBook.getEntrustValidity().getTime() <= new Date().getTime()) {
				throw new RuntimeException("委托书有效期不可小于今天日期");
			}
			if (scmZsjSupplierEntruseBook.getIdValidity()!=null && scmZsjSupplierEntruseBook.getIdValidity().getTime() <= new Date().getTime()) {
				throw new RuntimeException("委托书身份证有效期不可小于今天日期");
			}
			scmZsjSupplierEntruseBook.setSupplierFirstId(id);
			scmZsjSupplierEntruseBook.setId(UniqueIdUtil.getSuid());
			scmZsjSupplierEntruseBookManager.create(scmZsjSupplierEntruseBook);
		}
		// 收货地址
		scmZsjSupplierShipAddressDao.delByMainId(id);
		List<ScmZsjSupplierShipAddress> scmZsjSupplierShipAddressList = scmZsjSupplierFirst.getScmZsjSupplierShipAddressList();
		for (ScmZsjSupplierShipAddress scmZsjSupplierShipAddress : scmZsjSupplierShipAddressList) {
			scmZsjSupplierShipAddress.setSupplierFirstId(id);
			scmZsjSupplierShipAddress.setId(UniqueIdUtil.getSuid());
			scmZsjSupplierShipAddressManager.create(scmZsjSupplierShipAddress);
		}
        //附件
        List<ScmZsjAccessory> scmZsjAccessoryList = scmZsjSupplierFirst.getScmZsjAccessoryList();
        scmZsjAccessoryDao.delByMainId(id);
        for (ScmZsjAccessory scmZsjAccessory : scmZsjAccessoryList) {
        	scmZsjAccessory.setType("0");
        	scmZsjAccessory.setUpdateDate(new Date());
        	scmZsjAccessory.setDateId(id);
        	scmZsjAccessory.setId(UniqueIdUtil.getSuid());
        	scmZsjAccessoryManagerImpl.create(scmZsjAccessory);
		}
	}

	@Transactional
	@Override
	public void sendApply(ScmZsjSupplierFirst scmZsjSupplierFirst, String flowKey) {
		// 验证供应商是否存在
		scmZsjSupplierManager.isExist(scmZsjSupplierFirst.getScmZsjSupplier());
		scmZsjSupplierFirst.setSupplierId(scmZsjSupplierFirst.getScmZsjSupplier().getId());
		// 判断当前供应商是否已经首营
		List<ScmZsjSupplierFirst> getSupplierFirstByOwnerIdAndCommerceId = scmZsjSupplierFirstDao
				.getSupplierFirstByOwnerIdAndCommerceId(scmZsjSupplierFirst);
		if (getSupplierFirstByOwnerIdAndCommerceId.size() != 0) {
			throw new RuntimeException("当前供应商已经发起审批或审批已经通过,请勿重复提交");
		}
		// 调整审批状态,保存审批申请
		StartFlowParam startFlowParam = new StartFlowParam(flowKey, "SCM", "approvalId");
		startFlowParam.setFormType("frame");
		CustomStartResult customStartResult = null;
		create(scmZsjSupplierFirst);
		try {
			System.out.println("供应商首营申请");
			customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
			String approvalId = customStartResult.getInstId();
			scmZsjSupplierFirst.setApprovalId(approvalId);
			scmZsjSupplierFirst.setApprovalState("1");
			super.update(scmZsjSupplierFirst);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("启动流程失败");
		}

	}

	@Override
	public void isSave(ScmZsjSupplierFirst scmZsjSupplierFirst) {
		List<ScmZsjSupplierFirst> scmZsjSupplierFirsts = scmZsjSupplierFirstDao.isSave(scmZsjSupplierFirst);
		if (scmZsjSupplierFirsts.size() > 0) {
			throw new RuntimeException("当前商业首营信息已暂存或已经发起申请,请勿重复提交");
		}
	}

	@Override
	public void endApply(JsonNode jsonNode) {
		String approvalId = jsonNode.get("instId").asText();
		String actionName = jsonNode.get("actionName").asText();
		ScmZsjSupplierFirst supplierFirstByApprovalId = scmZsjSupplierFirstDao.getSupplierFirstByApprovalId(approvalId);
		if (supplierFirstByApprovalId == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		String endEvent = jsonNode.get("eventType").asText();
		if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
			// 审批状态调整为通过
			supplierFirstByApprovalId.setApprovalState("2");
			supplierFirstByApprovalId.setIsPassed("1");
			scmZsjSupplierFirstDao.update(supplierFirstByApprovalId);
		} else if ("agree".equals(actionName)) {
		} else if ("reject".equals(actionName)) {
		} else if ("backToStart".equals(actionName)) {
		} else if ("opposeTrans".equals(actionName)) {
		} else if ("endProcess".equals(actionName)) {
			supplierFirstByApprovalId.setApprovalState("3");
			scmZsjSupplierFirstDao.update(supplierFirstByApprovalId);
		}
	}

	@Override
	public ScmZsjSupplierFirst getSupplierFirstByApprovalId(String approvalId) {
		ScmZsjSupplierFirst scmZsjSupplierFirst = scmZsjSupplierFirstDao.getSupplierFirstByApprovalId(approvalId);
		if (scmZsjSupplierFirst == null) {
			throw new RuntimeException("查询数据不存在或已经被删除");
		}
		ScmZsjSupplierFirst scmZsjSupplierFirst2 = get(scmZsjSupplierFirst.getId());
		return scmZsjSupplierFirst2;
	}

	@Transactional
	@Override
	public void updateSendApply(ScmZsjSupplierFirst scmZsjSupplierFirst, String flowKey) {
		ScmZsjSupplierFirst scmZsjSupplierFirst2 = scmZsjSupplierFirstDao.get(scmZsjSupplierFirst.getId());
		if ("1".equals(scmZsjSupplierFirst2.getApprovalState())) {
			// 数据在审批中,不可修改编辑
			throw new RuntimeException("当前数据已经在审批中,不可重复提交");
		} else {
			// 修改其他关联性数据
			StartFlowParam startFlowParam = new StartFlowParam(flowKey, "SCM", "approvalId");
			startFlowParam.setFormType("frame");
			CustomStartResult customStartResult = null;
			update(scmZsjSupplierFirst);
			try {
				System.out.println("供应商首营申请");
				customStartResult = bpmRuntimeFeignService.customStart(startFlowParam);
				String approvalId = customStartResult.getInstId();
				scmZsjSupplierFirst.setApprovalId(approvalId);
				scmZsjSupplierFirst.setApprovalState("1");
				super.update(scmZsjSupplierFirst);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("启动流程失败");
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
		queryFilter.addFilter("type", "2", QueryOP.EQUAL, FieldRelation.AND);
		List<ScmZsjCommerce> query = scmZsjSupplierFirstDao.licenseWaring(queryFilter.getParams());
		return new PageList(query);
	}

	@Override
	public PageList<ScmZsjSupplier> gmplist(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
		if (BeanUtils.isEmpty(pageBean)) {
			PageHelper.startPage(1, 2147483647, false);
		} else {
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
		}
		List<ScmZsjSupplier> query = scmZsjSupplierFirstDao.gmplist(queryFilter.getParams());
		return new PageList(query);
	}

	@Override
	public PageList<ScmZsjSupplier> creditlist(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
		if (BeanUtils.isEmpty(pageBean)) {
			PageHelper.startPage(1, 2147483647, false);
		} else {
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
		}
		List<ScmZsjSupplier> query = scmZsjSupplierFirstDao.creditlist(queryFilter.getParams());
		return new PageList(query);
	}

	@Override
	public PageList<ScmZsjSupplier> qlist(QueryFilter queryFilter) {
		PageBean pageBean = queryFilter.getPageBean();
		if (BeanUtils.isEmpty(pageBean)) {
			PageHelper.startPage(1, 2147483647, false);
		} else {
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
		}
		List<ScmZsjSupplier> query = scmZsjSupplierFirstDao.qlist(queryFilter.getParams());
		return new PageList(query);
	}

	@Override
	public String startOrStop(String id) {
		ScmZsjSupplierFirst scmZsjSupplierFirst = scmZsjSupplierFirstDao.get(id);
		if (scmZsjSupplierFirst == null) {
			throw new RuntimeException("首营数据获取异常");
		}
		scmZsjSupplierFirst.setApprovalState("0");
		scmZsjSupplierFirst.setIsPassed("0");
		scmZsjSupplierFirstDao.update(scmZsjSupplierFirst);
		return "0";
	}

	@Override
	public boolean isInForce(String supplierId) {
		ScmZsjSupplier scmZsjSupplier = scmZsjSupplierDao.get(supplierId);
		ScmZsjSupplierFirst bySupplierId = scmZsjSupplierFirstDao.getBySupplierId(supplierId);
		if (scmZsjSupplier == null || bySupplierId == null) {
			throw new RuntimeException("数据错误，没有查询到供应商信息");
		}
		Date now = new Date();
		Date qualityValidity = bySupplierId.getQualityValidity();
		if (qualityValidity.before(now)) {
			return false;
		}
		List<ScmZsjSupplierLicence> byMainId = scmZsjSupplierLicenceDao.getByMainId(bySupplierId.getId());
		if (byMainId == null || byMainId.isEmpty()) {
			throw new RuntimeException("没有查询到许可证信息");
		}
		if (byMainId.get(0).getValidity().before(now)) {
			return false;
		}
		List<ScmZsjSupplierBusiness> byMainId1 = scmZsjSupplierBusinessDao.getByMainId(bySupplierId.getId());
		if (byMainId1 == null || byMainId1.isEmpty()) {
			throw new RuntimeException("没有查询到营业执照信息");
		}
		if (byMainId1.get(0).getLicenseValidity().before(now) || byMainId1.get(0).getYearReportValidity().before(now)) {
			return false;
		}
		List<ScmZsjGMP> byMainId2 = scmZsjGMPDao.getByMainId(bySupplierId.getId());
		if (byMainId2 == null || byMainId2.isEmpty()) {
			throw new RuntimeException("没有查询到GMP证书信息");
		}
		if (byMainId2.get(0).getValidity().before(now)) {
			return false;
		}
		List<ScmZsjSupplierEntruseBook> byMainId3 = scmZsjSupplierEntruseBookDao.getByMainId(bySupplierId.getId());
		if (byMainId3 == null || byMainId3.isEmpty()) {
			throw new RuntimeException("没有查询到委托书信息");
		}
		for (ScmZsjSupplierEntruseBook book : byMainId3) {
			if (book.getEntrustValidity().before(now) || book.getIdValidity().before(now)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Set<String> getAccreditProCodes(String supplierId) {
		ScmZsjSupplierFirst bySupplierId = scmZsjSupplierFirstDao.getBySupplierId(supplierId);
		if (bySupplierId == null) {
			throw new RuntimeException("数据错误，暂无该供应商的首营数据");
		}
		List<ScmZsjSupplierEntruseBook> byMainId = scmZsjSupplierEntruseBookManager
				.getBySupplierId(bySupplierId.getId());
		Set<String> codes = new HashSet<>();
		for (ScmZsjSupplierEntruseBook book : byMainId) {
			List<ScmZsjSupplierAccredit> scmZsjSupplierAccreditList = book.getScmZsjSupplierAccreditList();
			for (ScmZsjSupplierAccredit accredit : scmZsjSupplierAccreditList) {
				codes.add(accredit.getProductCode());
			}
		}
		return codes;
	}

}
