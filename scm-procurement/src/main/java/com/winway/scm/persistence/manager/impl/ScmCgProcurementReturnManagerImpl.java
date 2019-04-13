package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.winway.scm.persistence.dao.ScmCgReturnProductDao;
import com.winway.scm.model.ScmCgReturnProduct;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCgProcurementReturnDao;
import com.winway.scm.model.ScmCgProcurementReturn;
import com.winway.scm.persistence.manager.ScmCgProcurementReturnManager;

/**
 * 
 * <pre> 
 * 描述：采购退货表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgProcurementReturnManager")
public class ScmCgProcurementReturnManagerImpl extends AbstractManagerImpl<String, ScmCgProcurementReturn> implements ScmCgProcurementReturnManager{
	@Resource
	ScmCgProcurementReturnDao scmCgProcurementReturnDao;
	@Resource
	ScmCgReturnProductDao scmCgReturnProductDao;
	@Override
	protected MyBatisDao<String, ScmCgProcurementReturn> getDao() {
		return scmCgProcurementReturnDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmCgProcurementReturn scmCgProcurementReturn){
    	super.create(scmCgProcurementReturn);
    	String pk=scmCgProcurementReturn.getId();
    	
    	List<ScmCgReturnProduct> scmCgReturnProductList=scmCgProcurementReturn.getScmCgReturnProductList();
    	
    	for(ScmCgReturnProduct scmCgReturnProduct:scmCgReturnProductList){
    		scmCgReturnProduct.setProReturnId(pk);
    		scmCgReturnProductDao.create(scmCgReturnProduct);
    	}
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmCgReturnProductDao.delByMainId(entityId);
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
    public ScmCgProcurementReturn get(String entityId){
    	ScmCgProcurementReturn scmCgProcurementReturn=super.get(entityId);
    	List<ScmCgReturnProduct> scmCgReturnProductList=scmCgReturnProductDao.getByMainId(entityId);
    	scmCgProcurementReturn.setScmCgReturnProductList(scmCgReturnProductList);
    	return scmCgProcurementReturn;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmCgProcurementReturn scmCgProcurementReturn){
    	super.update(scmCgProcurementReturn);
    	String id=scmCgProcurementReturn.getId();
    	scmCgReturnProductDao.delByMainId(id);
    	List<ScmCgReturnProduct> scmCgReturnProductList=scmCgProcurementReturn.getScmCgReturnProductList();
    	for(ScmCgReturnProduct scmCgReturnProduct:scmCgReturnProductList){
    		scmCgReturnProduct.setProReturnId(id);
    		scmCgReturnProductDao.create(scmCgReturnProduct);
    	}
    }
	@Override
	public void sendApply(ScmCgProcurementReturn scmCgProcurementReturn) {
		// TODO Auto-generated method stub
		scmCgProcurementReturn.setApprovalState('1');
		scmCgProcurementReturnDao.update(scmCgProcurementReturn);
	}
	
}
