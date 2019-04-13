package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.winway.scm.persistence.dao.ScmZdFunctionTypeDao;
import com.winway.scm.model.ScmZdFunctionType;
import com.winway.scm.model.ScmZdMedicineType;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdCommerceLevelDao;
import com.winway.scm.model.ScmZdCommerceLevel;
import com.winway.scm.persistence.manager.ScmZdCommerceLevelManager;

/**
 * 
 * <pre> 
 * 描述：scm_zd_commercelevel 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-16 10:34:19
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdCommerceLevelManager")
public class ScmZdCommerceLevelManagerImpl extends AbstractManagerImpl<String, ScmZdCommerceLevel> implements ScmZdCommerceLevelManager{
	@Resource
	ScmZdCommerceLevelDao scmZdCommerceLevelDao;
	@Resource
	ScmZdFunctionTypeDao scmZdFunctionTypeDao;
	@Override
	protected MyBatisDao<String, ScmZdCommerceLevel> getDao() {
		return scmZdCommerceLevelDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	/*public void create(ScmZdCommerceLevel scmZdCommerceLevel){
    	super.create(scmZdCommerceLevel);
    	String pk=scmZdCommerceLevel.getId();
    	
    	List<ScmZdFunctionType> scmZdFunctionTypeList=scmZdCommerceLevel.getScmZdFunctionTypeList();
    	
    	for(ScmZdFunctionType scmZdFunctionType:scmZdFunctionTypeList){
    		scmZdFunctionType.setCommerceLevelId(pk);
    		scmZdFunctionTypeDao.create(scmZdFunctionType);
    	}
    }*/
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmZdFunctionTypeDao.delByMainId(entityId);
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
    public ScmZdCommerceLevel get(String entityId){
    	ScmZdCommerceLevel scmZdCommerceLevel=super.get(entityId);
    	List<ScmZdFunctionType> scmZdFunctionTypeList=scmZdFunctionTypeDao.getByMainId(entityId);
    	scmZdCommerceLevel.setScmZdFunctionTypeList(scmZdFunctionTypeList);
    	return scmZdCommerceLevel;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmZdCommerceLevel scmZdCommerceLevel){
    	super.update(scmZdCommerceLevel);
    	String id=scmZdCommerceLevel.getId();
    	scmZdFunctionTypeDao.delByMainId(id);
    	List<ScmZdFunctionType> scmZdFunctionTypeList=scmZdCommerceLevel.getScmZdFunctionTypeList();
    	for(ScmZdFunctionType scmZdFunctionType:scmZdFunctionTypeList){
    		scmZdFunctionType.setCommerceLevelId(id);
    		scmZdFunctionTypeDao.create(scmZdFunctionType);
    	}
    }
    
    /**
     * 商业级别下拉框
     */
	@Override
	public List<ScmZdCommerceLevel> downBoxList() {
		List<ScmZdCommerceLevel> listScmZdCommerceLevel = scmZdCommerceLevelDao.listScmZdCommerceLevel();
		for (ScmZdCommerceLevel scmZdCommerceLevel : listScmZdCommerceLevel) {
			List<ScmZdFunctionType> scmZdFunctionTypes =  scmZdFunctionTypeDao.ListScmZdFunctionTypeByLevelId(scmZdCommerceLevel.getId());
			scmZdCommerceLevel.setScmZdFunctionTypeList(scmZdFunctionTypes);
		}
		return listScmZdCommerceLevel;
	}
	@Override
	public void getByNameAndCodeOrId(ScmZdCommerceLevel scmZdCommerceLevel) {
		// TODO Auto-generated method stub
		//根据名称与ID判断是否已经存在当前使用名称
		List<ScmZdCommerceLevel> sdas = scmZdCommerceLevelDao.getByNameAndCodeOrId(scmZdCommerceLevel);
		if(sdas.size() > 0) {
			throw new RuntimeException("当前名称或编号已经被使用,请修改后提交");
		}
		
	}
	
}
