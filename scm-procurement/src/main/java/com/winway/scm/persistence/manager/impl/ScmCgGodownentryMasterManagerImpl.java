package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.winway.scm.persistence.dao.ScmCgGodownentryDao;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.scm.model.ScmCgGodownentry;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCgGodownentryMasterDao;
import com.winway.scm.model.ScmCgGodownentryMaster;
import com.winway.scm.persistence.manager.ScmCgGodownentryManager;
import com.winway.scm.persistence.manager.ScmCgGodownentryMasterManager;

/**
 * 
 * <pre> 
 * 描述：入库单主表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-28 14:54:15
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgGodownentryMasterManager")
public class ScmCgGodownentryMasterManagerImpl extends AbstractManagerImpl<String, ScmCgGodownentryMaster> implements ScmCgGodownentryMasterManager{
	@Resource
	ScmCgGodownentryMasterDao scmCgGodownentryMasterDao;
	@Resource
	ScmCgGodownentryDao scmCgGodownentryDao;
	@Resource
	ScmCgGodownentryManager scmCgGodownentryManager;
	@Override
	protected MyBatisDao<String, ScmCgGodownentryMaster> getDao() {
		return scmCgGodownentryMasterDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmCgGodownentryMaster scmCgGodownentryMaster){
    	super.create(scmCgGodownentryMaster);
    	String pk=scmCgGodownentryMaster.getId();
    	
    	List<ScmCgGodownentry> scmCgGodownentryList=scmCgGodownentryMaster.getList();
    	
    	for(ScmCgGodownentry scmCgGodownentry:scmCgGodownentryList){
    		scmCgGodownentry.setMasterId(pk);
    		scmCgGodownentryManager.create(scmCgGodownentry);
    	}
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmCgGodownentryDao.delByMainId(entityId);
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
    public ScmCgGodownentryMaster get(String entityId){
    	ScmCgGodownentryMaster scmCgGodownentryMaster=super.get(entityId);
    	List<ScmCgGodownentry> scmCgGodownentryList=scmCgGodownentryDao.getByMainId(entityId);
    	scmCgGodownentryMaster.setList(scmCgGodownentryList);
    	return scmCgGodownentryMaster;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmCgGodownentryMaster scmCgGodownentryMaster){
    	super.update(scmCgGodownentryMaster);
    	String id=scmCgGodownentryMaster.getId();
    	scmCgGodownentryDao.delByMainId(id);
    	List<ScmCgGodownentry> scmCgGodownentryList=scmCgGodownentryMaster.getList();
    	for(ScmCgGodownentry scmCgGodownentry:scmCgGodownentryList){
    		scmCgGodownentry.setMasterId(id);
    		scmCgGodownentryDao.create(scmCgGodownentry);
    	}
    }
	@Override
	public ReturnWmsVo acceptDate(ScmCgGodownentryMaster scmCgGodownentryMaster) {
		/*TODO whw 1.3WMS推送入库单至SCM开始*/
		ReturnWmsVo returnWmsVo = new ReturnWmsVo();
		try {
			create(scmCgGodownentryMaster);
			returnWmsVo.setStute("200");
			returnWmsVo.setMsg("订单保存成功");
		}catch(Exception e) {
			returnWmsVo.setStute("500");
			returnWmsVo.setMsg("订单保存失败");
		}
		return returnWmsVo;
		/*1.3WMS推送入库单至SCM结束*/
	}
	
}
