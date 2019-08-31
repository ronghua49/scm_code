package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjTXCompanyDao;
import com.winway.scm.persistence.dao.ScmZsjTxCompanyWarehouseDao;
import com.winway.scm.model.ScmZsjTXCompany;
import com.winway.scm.model.ScmZsjTxCompanyWarehouse;
import com.winway.scm.persistence.manager.ScmZsjTXCompanyManager;

/**
 * 
 * <pre> 
 * 描述：天祥信息 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjTXCompanyManager")
public class ScmZsjTXCompanyManagerImpl extends AbstractManagerImpl<String, ScmZsjTXCompany> implements ScmZsjTXCompanyManager{
	@Resource
	ScmZsjTXCompanyDao scmZsjTXCompanyDao;
	@Resource
	ScmZsjTxCompanyWarehouseDao scmZsjTxCompanyWarehouseDao;
	@Override
	protected MyBatisDao<String, ScmZsjTXCompany> getDao() {
		return scmZsjTXCompanyDao;
	}
	@Override
	public ScmZsjTXCompany getByOwnerId(String ownerId) {
		ScmZsjTXCompany scmZsjTXCompany = scmZsjTXCompanyDao.getByOwnerId(ownerId);
		if (scmZsjTXCompany == null) {
			return null;
		}
		
		List<ScmZsjTxCompanyWarehouse> list =  scmZsjTxCompanyWarehouseDao.getByOwnerId(ownerId);
		scmZsjTXCompany.setScmZsjTxCompanyWarehouseList(list);
		return scmZsjTXCompany;
	}

	@Override
	public ScmZsjTXCompany getRB(String ownerId) {
		ScmZsjTXCompany byOwnerId = scmZsjTXCompanyDao.getByOwnerId(ownerId);
		if(byOwnerId==null){
			throw  new RuntimeException("没有查询到货主信息");
		}
		return byOwnerId;
	}

	@Override
	public void updateRB(ScmZsjTXCompany scmZsjTXCompany) {
		ScmZsjTXCompany byOwnerId = scmZsjTXCompanyDao.getByOwnerId(scmZsjTXCompany.getOwnerId());
		if(byOwnerId==null){
			throw  new RuntimeException("没有查询到货主信息");
		}
		byOwnerId.setTaxPoint(scmZsjTXCompany.getTaxPoint());
		byOwnerId.setInformationCost(scmZsjTXCompany.getInformationCost());
		byOwnerId.setFlowDiscount(scmZsjTXCompany.getFlowDiscount());
		byOwnerId.setStorageManager(scmZsjTXCompany.getStorageManager());
		scmZsjTXCompanyDao.update(byOwnerId);
	}

	@Override
	public void create(ScmZsjTXCompany arg0) {
		//判断当前货主ID数据是否存在,如果存在,表示数据已经存在,调用更新方法,更新数据
		ScmZsjTXCompany byOwnerId = scmZsjTXCompanyDao.getByOwnerId(arg0.getOwnerId());
		if(byOwnerId == null) {
			super.create(arg0);
		}else{
			byOwnerId.setName(arg0.getName());
			byOwnerId.setAddress(arg0.getAddress());
			byOwnerId.setOpenBank(arg0.getOpenBank());
			byOwnerId.setBankCode(arg0.getBankCode());
			byOwnerId.setDutyParagraph(arg0.getDutyParagraph());
			byOwnerId.setPhone(arg0.getPhone());
			byOwnerId.setPostcode(arg0.getPostcode());
			byOwnerId.setOwnerId(arg0.getOwnerId());
			byOwnerId.setManagementScope(arg0.getManagementScope());
			byOwnerId.setContactPersion(arg0.getContactPersion());
			byOwnerId.setContactPhone(arg0.getContactPhone());
			scmZsjTXCompanyDao.update(byOwnerId);
		}
	}
	
	@Override
	public void update(ScmZsjTXCompany arg0) {
		ScmZsjTXCompany byOwnerId = scmZsjTXCompanyDao.getByOwnerId(arg0.getOwnerId());
		byOwnerId.setName(arg0.getName());
		byOwnerId.setAddress(arg0.getAddress());
		byOwnerId.setOpenBank(arg0.getOpenBank());
		byOwnerId.setBankCode(arg0.getBankCode());
		byOwnerId.setDutyParagraph(arg0.getDutyParagraph());
		byOwnerId.setPhone(arg0.getPhone());
		byOwnerId.setPostcode(arg0.getPostcode());
		byOwnerId.setManagementScope(arg0.getManagementScope());
		byOwnerId.setContactPersion(arg0.getContactPersion());
		byOwnerId.setContactPhone(arg0.getContactPhone());
		super.update(byOwnerId);
	}
	@Override
	public String getFignRB(String ownerId) {
		ScmZsjTXCompany byOwnerId = scmZsjTXCompanyDao.getByOwnerId(ownerId);
		if(byOwnerId==null){
			throw  new RuntimeException("没有查询到货主信息");
		}
		if(byOwnerId.getTaxPoint() == null) {
			throw  new RuntimeException("没有查询到RB信息");
		}
		return byOwnerId.getTaxPoint().toString();
	}
}
