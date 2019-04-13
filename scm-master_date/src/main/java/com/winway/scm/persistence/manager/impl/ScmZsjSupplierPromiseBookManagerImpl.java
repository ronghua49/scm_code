package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.winway.scm.persistence.dao.ScmZsjSupplierAccreditDao;
import com.winway.scm.model.ScmZsjSupplierAccredit;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjSupplierPromiseBookDao;
import com.winway.scm.model.ScmZsjSupplierPromiseBook;
import com.winway.scm.persistence.manager.ScmZsjSupplierPromiseBookManager;

/**
 * 
 * <pre> 
 * 描述：供应商承诺书 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjSupplierPromiseBookManager")
public class ScmZsjSupplierPromiseBookManagerImpl extends AbstractManagerImpl<String, ScmZsjSupplierPromiseBook> implements ScmZsjSupplierPromiseBookManager{
	@Resource
	ScmZsjSupplierPromiseBookDao scmZsjSupplierPromiseBookDao;
	@Resource
	ScmZsjSupplierAccreditDao scmZsjSupplierAccreditDao;
	@Override
	protected MyBatisDao<String, ScmZsjSupplierPromiseBook> getDao() {
		return scmZsjSupplierPromiseBookDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmZsjSupplierPromiseBook scmZsjSupplierPromiseBook){
    	super.create(scmZsjSupplierPromiseBook);
    	String pk=scmZsjSupplierPromiseBook.getId();
    	
/*    	List<ScmZsjSupplierAccredit> scmZsjSupplierAccreditList=scmZsjSupplierPromiseBook.getScmZsjSupplierAccreditList();
    	
    	for(ScmZsjSupplierAccredit scmZsjSupplierAccredit:scmZsjSupplierAccreditList){
    		scmZsjSupplierAccredit.setEntrustId(pk);
    		scmZsjSupplierAccreditDao.create(scmZsjSupplierAccredit);
    	}*/
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmZsjSupplierAccreditDao.delByMainId(entityId);
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
    public ScmZsjSupplierPromiseBook get(String entityId){
    	ScmZsjSupplierPromiseBook scmZsjSupplierPromiseBook=super.get(entityId);
    	List<ScmZsjSupplierAccredit> scmZsjSupplierAccreditList=scmZsjSupplierAccreditDao.getByMainId(entityId);
//    	scmZsjSupplierPromiseBook.setScmZsjSupplierAccreditList(scmZsjSupplierAccreditList);
    	return scmZsjSupplierPromiseBook;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmZsjSupplierPromiseBook scmZsjSupplierPromiseBook){
    	super.update(scmZsjSupplierPromiseBook);
    	String id=scmZsjSupplierPromiseBook.getId();
    	scmZsjSupplierAccreditDao.delByMainId(id);
//    	List<ScmZsjSupplierAccredit> scmZsjSupplierAccreditList=scmZsjSupplierPromiseBook.getScmZsjSupplierAccreditList();
//    	for(ScmZsjSupplierAccredit scmZsjSupplierAccredit:scmZsjSupplierAccreditList){
//    		scmZsjSupplierAccredit.setEntrustId(id);
//    		scmZsjSupplierAccreditDao.create(scmZsjSupplierAccredit);
//    	}
    }
	
}
