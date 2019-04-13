package com.winway.scm.persistence.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.ContrasUtil;
import com.winway.scm.model.ScmZsjGMP;
import com.winway.scm.model.ScmZsjSupplier;
import com.winway.scm.model.ScmZsjSupplierAccredit;
import com.winway.scm.model.ScmZsjSupplierBusiness;
import com.winway.scm.model.ScmZsjSupplierEntruseBook;
import com.winway.scm.model.ScmZsjSupplierFirst;
import com.winway.scm.model.ScmZsjSupplierLicence;
import com.winway.scm.model.ScmZsjSupplierShipAddress;
import com.winway.scm.model.ScmZsjSupplierWarehouse;
import com.winway.scm.persistence.dao.ScmZdPromiseBookDao;
import com.winway.scm.persistence.dao.ScmZsjGMPDao;
import com.winway.scm.persistence.dao.ScmZsjSupplierAccreditDao;
import com.winway.scm.persistence.dao.ScmZsjSupplierBusinessDao;
import com.winway.scm.persistence.dao.ScmZsjSupplierEntruseBookDao;
import com.winway.scm.persistence.dao.ScmZsjSupplierFirstDao;
import com.winway.scm.persistence.dao.ScmZsjSupplierLicenceDao;
import com.winway.scm.persistence.dao.ScmZsjSupplierPromiseBookDao;
import com.winway.scm.persistence.dao.ScmZsjSupplierShipAddressDao;
import com.winway.scm.persistence.dao.ScmZsjSupplierWarehouseDao;
import com.winway.scm.persistence.manager.ScmZsjGMPManager;
import com.winway.scm.persistence.manager.ScmZsjSupplierAccreditManager;
import com.winway.scm.persistence.manager.ScmZsjSupplierBusinessManager;
import com.winway.scm.persistence.manager.ScmZsjSupplierEntruseBookManager;
import com.winway.scm.persistence.manager.ScmZsjSupplierFirstManager;
import com.winway.scm.persistence.manager.ScmZsjSupplierLicenceManager;
import com.winway.scm.persistence.manager.ScmZsjSupplierManager;
import com.winway.scm.persistence.manager.ScmZsjSupplierShipAddressManager;
import com.winway.scm.persistence.manager.ScmZsjSupplierWarehouseManager;

/**
 * 
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
	ScmZsjSupplierWarehouseDao scmZsjSupplierWarehouseDao;
	@Resource
	ScmZsjSupplierLicenceDao scmZsjSupplierLicenceDao;
	@Resource
	ScmZsjSupplierBusinessDao scmZsjSupplierBusinessDao;
	@Resource
	ScmZsjGMPDao scmZsjGMPDao;
	@Resource
	ScmZsjSupplierEntruseBookDao scmZsjSupplierEntruseBookDao;
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
	ScmZsjSupplierEntruseBookManager scmZsjSupplierEntruseBookManager;
	@Resource
	ScmZsjSupplierAccreditManager scmZsjSupplierAccreditManager;
	@Resource
	ScmZsjSupplierShipAddressManager scmZsjSupplierShipAddressManager;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;

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
			scmZsjSupplierLicence.setSupplierFirstId(pk);
			scmZsjSupplierLicenceManager.create(scmZsjSupplierLicence);
		}
		// 供应商营业执照信息
		List<ScmZsjSupplierBusiness> scmZsjSupplierBusinessList = scmZsjSupplierFirst.getScmZsjSupplierBusinessList();

		for (ScmZsjSupplierBusiness scmZsjSupplierBusiness : scmZsjSupplierBusinessList) {
			scmZsjSupplierBusiness.setSupplierFirstId(pk);
			scmZsjSupplierBusinessManager.create(scmZsjSupplierBusiness);
		}
		// 供应商GMP证书信息
		List<ScmZsjGMP> scmZsjGMPList = scmZsjSupplierFirst.getScmZsjGMPList();

		for (ScmZsjGMP scmZsjGMP : scmZsjGMPList) {
			scmZsjGMP.setSupplierFirstId(pk);
			scmZsjGMPManager.create(scmZsjGMP);
		}
		// 供应商委托书
		List<ScmZsjSupplierEntruseBook> scmZsjSupplierEntruseBookList = scmZsjSupplierFirst
				.getScmZsjSupplierEntruseBookList();

		for (ScmZsjSupplierEntruseBook scmZsjSupplierEntruseBook : scmZsjSupplierEntruseBookList) {
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
			;
			entruseList.add(scmZsjSupplierEntruseBook);
		}
		scmZsjSupplierFirst.setScmZsjSupplierEntruseBookList(entruseList);
		// 收货地址
		List<ScmZsjSupplierShipAddress> scmZsjSupplierShipAddressList = scmZsjSupplierShipAddressDao
				.getByMainId(entityId);
		scmZsjSupplierFirst.setScmZsjSupplierShipAddressList(scmZsjSupplierShipAddressList);
		return scmZsjSupplierFirst;
	}

	/**
	 * 更新实体同时更新子表记录
	 */
	public void update(ScmZsjSupplierFirst scmZsjSupplierFirst) {
		super.update(scmZsjSupplierFirst);
		String id = scmZsjSupplierFirst.getId();
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
			scmZsjSupplierWarehouseManager.create(scmZsjSupplierWarehouse);
		}
		// 供应商许可证信息
		for (ScmZsjSupplierLicence scmZsjSupplierLicence : scmZsjSupplierLicenceList) {
			scmZsjSupplierLicence.setSupplierFirstId(id);
			scmZsjSupplierLicenceManager.create(scmZsjSupplierLicence);
		}
		// 供应商营业执照信息
		for (ScmZsjSupplierBusiness scmZsjSupplierBusiness : scmZsjSupplierBusinessList) {
			scmZsjSupplierBusiness.setSupplierFirstId(id);
			scmZsjSupplierBusinessManager.create(scmZsjSupplierBusiness);
		}
		// 供应商GMP证书信息
		scmZsjGMPDao.delByMainId(id);
		List<ScmZsjGMP> scmZsjGMPList = scmZsjSupplierFirst.getScmZsjGMPList();
		for (ScmZsjGMP scmZsjGMP : scmZsjGMPList) {
			scmZsjGMP.setSupplierFirstId(id);
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
			String entruseId = scmZsjSupplierEntruseBook.getId();

			List<ScmZsjSupplierAccredit> scmZsjSupplierAccreditList = scmZsjSupplierEntruseBook
					.getScmZsjSupplierAccreditList();

			for (ScmZsjSupplierAccredit scmZsjSupplierAccredit : scmZsjSupplierAccreditList) {
				scmZsjSupplierAccredit.setEntrustId(entruseId);
				scmZsjSupplierAccreditManager.create(scmZsjSupplierAccredit);
			}
			scmZsjSupplierEntruseBook.setSupplierFirstId(id);
			scmZsjSupplierEntruseBookManager.create(scmZsjSupplierEntruseBook);
		}
		// 收货地址
		scmZsjSupplierShipAddressDao.delByMainId(id);
		List<ScmZsjSupplierShipAddress> scmZsjSupplierShipAddressList = scmZsjSupplierFirst
				.getScmZsjSupplierShipAddressList();
		for (ScmZsjSupplierShipAddress scmZsjSupplierShipAddress : scmZsjSupplierShipAddressList) {
			scmZsjSupplierShipAddress.setSupplierFirstId(id);
			scmZsjSupplierShipAddressManager.create(scmZsjSupplierShipAddress);
		}
	}

	@Override
	public void sendApply(ScmZsjSupplierFirst scmZsjSupplierFirst) {
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
		scmZsjSupplierFirst.setApprovalState("1");
		create(scmZsjSupplierFirst);

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
		ScmZsjSupplierFirst supplierFirstByApprovalId = scmZsjSupplierFirstDao.getSupplierFirstByApprovalId(approvalId);
		if (supplierFirstByApprovalId == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		// 审批状态调整为通过
		supplierFirstByApprovalId.setApprovalState("2");
		scmZsjSupplierFirstDao.update(supplierFirstByApprovalId);
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

	@Override
	public void updateSendApply(ScmZsjSupplierFirst scmZsjSupplierFirst) {
		ScmZsjSupplierFirst scmZsjSupplierFirst2 = scmZsjSupplierFirstDao.get(scmZsjSupplierFirst.getId());
		if ("1".equals(scmZsjSupplierFirst2.getApprovalState())) {
			// 数据在审批中,不可修改编辑
			throw new RuntimeException("当前数据已经在审批中,不可重复提交");
		} else {
			// 修改其他关联性数据
			update(scmZsjSupplierFirst);
			scmZsjSupplierFirst.setApprovalState("1");
			scmZsjSupplierFirstDao.update(scmZsjSupplierFirst);
		}
	}

}
