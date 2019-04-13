package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.ContrasUtil;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.model.ScmZsjProduct;
import com.winway.scm.model.ScmZsjProductFirst;
import com.winway.scm.model.ScmZsjSupplierFirst;
import com.winway.scm.persistence.dao.ScmZsjProductDao;
import com.winway.scm.persistence.dao.ScmZsjProductFirstDao;
import com.winway.scm.persistence.manager.ScmZsjProductFirstManager;
import com.winway.scm.persistence.manager.ScmZsjProductManager;

/**
 * 
 * <pre> 
 * 描述：商品首营记录表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjProductFirstManager")
public class ScmZsjProductFirstManagerImpl extends AbstractManagerImpl<String, ScmZsjProductFirst> implements ScmZsjProductFirstManager{
	@Resource
	ScmZsjProductFirstDao scmZsjProductFirstDao;
	@Resource
	ScmZsjProductDao scmZsjProductDao;
	
	@Resource
	ScmZsjProductManager scmZsjProductManager;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	@Override
	protected MyBatisDao<String, ScmZsjProductFirst> getDao() {
		return scmZsjProductFirstDao;
	}
	@Override
	public void sendApply(ScmZsjProductFirst scmZsjProductFirst) {
		//判断商品是否存在
		scmZsjProductManager.isExist(scmZsjProductFirst.getScmZsjProduct());
		//保存首营审批信息
		scmZsjProductFirst.setProductId(scmZsjProductFirst.getScmZsjProduct().getId());
		//判断当前商品是否已经首营
		List<ScmZsjProductFirst> isSave = scmZsjProductFirstDao.isSave(scmZsjProductFirst);
		if(isSave.size() != 0) {
			throw new RuntimeException("当前商品在该供应商已经发起审批或审批已经通过,请勿重复提交");
		}
		scmZsjProductFirst.setApprovalState("1");
		super.create(scmZsjProductFirst);		
	}
	@Override
	public void isSave(ScmZsjProductFirst scmZsjProductFirst) {
		List<ScmZsjProductFirst> scmZsjProductFirsts =  scmZsjProductFirstDao.isSave(scmZsjProductFirst);
		if(scmZsjProductFirsts.size() > 0) {
			throw new RuntimeException("当前商品首营信息已在该供应商暂存或已经发起申请,请勿重复提交");
		}
	}
	@Override
	public void endApply(JsonNode jsonNode) {
		String approvalId = jsonNode.get("instId").asText();
		ScmZsjProductFirst productFirstByApprovalId = scmZsjProductFirstDao.getProductFirstByApprovalId(approvalId);
		if(productFirstByApprovalId == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		//审批状态调整为通过
		productFirstByApprovalId.setApprovalState("2");
		scmZsjProductFirstDao.update(productFirstByApprovalId);
	}
	@Override
	public ScmZsjProductFirst getProductFirstByApprovalId(String approvalId) {
		ScmZsjProductFirst scmZsjProductFirst = scmZsjProductFirstDao.getProductFirstByApprovalId(approvalId);
		if(scmZsjProductFirst == null) {
			throw new RuntimeException("查询数据不存在或已经被删除");
		}
		ScmZsjProductFirst scmZsjProductFirst2 = get(scmZsjProductFirst.getId());
		return scmZsjProductFirst2;
	}
	@Override
	public ScmZsjProductFirst get(String entityId) {
		 ScmZsjProductFirst scmZsjProductFirst = super.get(entityId);
		 scmZsjProductFirst.setScmZsjProduct(scmZsjProductDao.get(scmZsjProductFirst.getProductId()));
		 return scmZsjProductFirst;
	}
	@Override
	public void updateSendApply(ScmZsjProductFirst scmZsjProductFirst) {
		ScmZsjProductFirst scmZsjProductFirst2 = scmZsjProductFirstDao.get(scmZsjProductFirst.getId());
		if("1".equals(scmZsjProductFirst2.getApprovalState())){
			//数据在审批中,不可修改编辑
			throw new RuntimeException("当前数据已经在审批中,不可重复提交");
		}else{
			//修改其他关联性数据
			update(scmZsjProductFirst);
			scmZsjProductFirst.setApprovalState("1");
			scmZsjProductFirstDao.update(scmZsjProductFirst);
		}
	}
	@Override
	public void update(ScmZsjProductFirst product) {
		String id = product.getId();
		List<ScmZsjProductFirst> list=scmZsjProductFirstDao.getByMainId(id);
		try {
			if(list.size()>0){
				String contrast = ContrasUtil.Contrast(list.get(0), product, id, "spxg");
				if (!StringUtils.isEmpty(contrast)) {
					scmMasterDateFeignService.inserScmLogRecord(contrast);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.update(product);
	}
}
