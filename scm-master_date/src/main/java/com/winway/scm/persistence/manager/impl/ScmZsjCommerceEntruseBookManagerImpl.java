package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.winway.scm.persistence.dao.ScmZsjCommerceAccreditDao;
import com.winway.scm.model.ScmZsjCommerceAccredit;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.util.UniqueIdUtil;
import com.winway.scm.persistence.dao.ScmZsjCommerceEntruseBookDao;
import com.winway.scm.model.ScmZsjCommerceEntruseBook;
import com.winway.scm.persistence.manager.ScmZsjCommerceAccreditManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceEntruseBookManager;

/**
 * 
 * <pre> 
 * 描述：商业委托书 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceEntruseBookManager")
public class ScmZsjCommerceEntruseBookManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceEntruseBook> implements ScmZsjCommerceEntruseBookManager{
	@Resource
	ScmZsjCommerceEntruseBookDao scmZsjCommerceEntruseBookDao;
	@Resource
	ScmZsjCommerceAccreditDao scmZsjCommerceAccreditDao;
	@Resource
	ScmZsjCommerceAccreditManager scmZsjCommerceAccreditManager;
	@Override
	protected MyBatisDao<String, ScmZsjCommerceEntruseBook> getDao() {
		return scmZsjCommerceEntruseBookDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmZsjCommerceEntruseBook scmZsjCommerceEntruseBook){
    	super.create(scmZsjCommerceEntruseBook);
    	String pk=scmZsjCommerceEntruseBook.getId();
    	List<ScmZsjCommerceAccredit> scmZsjCommerceAccreditList=scmZsjCommerceEntruseBook.getScmZsjCommerceAccreditList();
    	for(ScmZsjCommerceAccredit scmZsjCommerceAccredit:scmZsjCommerceAccreditList){
    		scmZsjCommerceAccredit.setEntrustId(pk);
    		scmZsjCommerceAccredit.setId(UniqueIdUtil.getSuid());
    		scmZsjCommerceAccreditManager.create(scmZsjCommerceAccredit);
    	}
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmZsjCommerceAccreditDao.delByMainId(entityId);
	}
	
	/**
	 * 批量删除包含子表记录
	 */
	public void removeByIds(String[] entityIds){
		for(String id:entityIds){
			this.remove(id);
		}
	}
    
	/**
	 * 获取实体
	 */
    public ScmZsjCommerceEntruseBook get(String entityId){
    	ScmZsjCommerceEntruseBook scmZsjCommerceEntruseBook=super.get(entityId);
    	List<ScmZsjCommerceAccredit> scmZsjCommerceAccreditList=scmZsjCommerceAccreditDao.getByMainId(entityId);
    	scmZsjCommerceEntruseBook.setScmZsjCommerceAccreditList(scmZsjCommerceAccreditList);
    	return scmZsjCommerceEntruseBook;
    }
    
    
    public List<ScmZsjCommerceEntruseBook> getByCommerceFirstId(String entityId){
    	List<ScmZsjCommerceEntruseBook> byMainId = scmZsjCommerceEntruseBookDao.getByMainId(entityId);
    	for (ScmZsjCommerceEntruseBook scmZsjCommerceEntruseBook2 : byMainId) {
    		List<ScmZsjCommerceAccredit> scmZsjCommerceAccreditList=scmZsjCommerceAccreditDao.getByMainId(scmZsjCommerceEntruseBook2.getId());
    		
    		scmZsjCommerceEntruseBook2.setScmZsjCommerceAccreditList(scmZsjCommerceAccreditList);
		}
    	return byMainId;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmZsjCommerceEntruseBook scmZsjCommerceEntruseBook){
    	super.update(scmZsjCommerceEntruseBook);
    	String id=scmZsjCommerceEntruseBook.getId();
    	scmZsjCommerceAccreditDao.delByMainId(id);
    	List<ScmZsjCommerceAccredit> scmZsjCommerceAccreditList=scmZsjCommerceEntruseBook.getScmZsjCommerceAccreditList();
    	for(ScmZsjCommerceAccredit scmZsjCommerceAccredit:scmZsjCommerceAccreditList){
    		scmZsjCommerceAccredit.setEntrustId(id);
    		scmZsjCommerceAccreditManager.create(scmZsjCommerceAccredit);
    	}
    }
	
}
