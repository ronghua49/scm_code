package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.winway.scm.persistence.dao.ScmZdProvinceDao;
import com.winway.scm.model.ScmZdProvince;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZdBusinessDivisionDao;
import com.winway.scm.model.ScmZdBusinessDivision;
import com.winway.scm.persistence.manager.ScmZdBusinessDivisionManager;

/**
 * 
 * <pre> 
 * 描述：商务分区 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
@Service("scmZdBusinessDivisionManager")
public class ScmZdBusinessDivisionManagerImpl extends AbstractManagerImpl<String, ScmZdBusinessDivision> implements ScmZdBusinessDivisionManager{
	@Resource
	ScmZdBusinessDivisionDao scmZdBusinessDivisionDao;
	@Resource
	ScmZdProvinceDao scmZdProvinceDao;
	@Override
	protected MyBatisDao<String, ScmZdBusinessDivision> getDao() {
		return scmZdBusinessDivisionDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmZdBusinessDivision scmZdBusinessDivision){
    	super.create(scmZdBusinessDivision);
    	String pk=scmZdBusinessDivision.getId();
    	
    	List<ScmZdProvince> scmZdProvinceList=scmZdBusinessDivision.getScmZdProvinceList();
    	
    	for(ScmZdProvince scmZdProvince:scmZdProvinceList){
    		scmZdProvince.setBusinessDivisionId(pk);
    		scmZdProvinceDao.create(scmZdProvince);
    	}
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmZdProvinceDao.delByMainId(entityId);

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
    public ScmZdBusinessDivision get(String entityId){
    	ScmZdBusinessDivision scmZdBusinessDivision=super.get(entityId);
    	List<ScmZdProvince> scmZdProvinceList=scmZdProvinceDao.getByMainId(entityId);
    	scmZdBusinessDivision.setScmZdProvinceList(scmZdProvinceList);
    	return scmZdBusinessDivision;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmZdBusinessDivision scmZdBusinessDivision){
    	super.update(scmZdBusinessDivision);
    	String id=scmZdBusinessDivision.getId();
    	scmZdProvinceDao.delByMainId(id);
    	List<ScmZdProvince> scmZdProvinceList=scmZdBusinessDivision.getScmZdProvinceList();
    	for(ScmZdProvince scmZdProvince:scmZdProvinceList){
    		scmZdProvince.setBusinessDivisionId(id);
    		scmZdProvinceDao.create(scmZdProvince);
    	}
    }
	
}
