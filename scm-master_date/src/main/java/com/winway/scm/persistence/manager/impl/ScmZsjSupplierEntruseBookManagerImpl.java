package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.util.UniqueIdUtil;
import com.winway.scm.persistence.dao.ScmZsjSupplierAccreditDao;
import com.winway.scm.persistence.dao.ScmZsjSupplierEntruseBookDao;
import com.winway.scm.model.ScmZsjCommerceAccredit;
import com.winway.scm.model.ScmZsjSupplierAccredit;
import com.winway.scm.model.ScmZsjSupplierEntruseBook;
import com.winway.scm.persistence.manager.ScmZsjSupplierEntruseBookManager;

/**
 * 
 * <pre> 
 * 描述：供应商委托书 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjSupplierEntruseBookManager")
public class ScmZsjSupplierEntruseBookManagerImpl extends AbstractManagerImpl<String, ScmZsjSupplierEntruseBook> implements ScmZsjSupplierEntruseBookManager{
	@Resource
	ScmZsjSupplierEntruseBookDao scmZsjSupplierEntruseBookDao;
	@Resource
	ScmZsjSupplierAccreditDao scmZsjSupplierAccreditDao;
	
	@Override
	protected MyBatisDao<String, ScmZsjSupplierEntruseBook> getDao() {
		return scmZsjSupplierEntruseBookDao;
	}
	public void create(ScmZsjSupplierEntruseBook scmZsjSupplierEntruseBook){
		super.create(scmZsjSupplierEntruseBook);
		String entruseId=scmZsjSupplierEntruseBook.getId();
		scmZsjSupplierAccreditDao.delByMainId(entruseId);
    	List<ScmZsjSupplierAccredit> scmZsjSupplierAccreditList=scmZsjSupplierEntruseBook.getScmZsjSupplierAccreditList();
    	for(ScmZsjSupplierAccredit scmZsjSupplierAccredit:scmZsjSupplierAccreditList){
    		scmZsjSupplierAccredit.setEntrustId(entruseId);
    		scmZsjSupplierAccredit.setId(UniqueIdUtil.getSuid());
    		scmZsjSupplierAccreditDao.create(scmZsjSupplierAccredit);
    	}
	}

	@Override
	public List<ScmZsjSupplierEntruseBook> getBySupplierId(String supplierId) {
		List<ScmZsjSupplierEntruseBook> byMainId = scmZsjSupplierEntruseBookDao.getByMainId(supplierId);
		for(ScmZsjSupplierEntruseBook book:byMainId){
			List<ScmZsjSupplierAccredit> byMainId1 = scmZsjSupplierAccreditDao.getByMainId(book.getId());
			book.setScmZsjSupplierAccreditList(byMainId1);
		}
		return byMainId;
	}
}
