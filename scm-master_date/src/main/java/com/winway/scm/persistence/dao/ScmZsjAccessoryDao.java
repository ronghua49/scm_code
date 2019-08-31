package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZsjAccessory;

/**
 * 
 * <pre> 
 * 描述：附件表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjAccessoryDao extends MyBatisDao<String, ScmZsjAccessory> {

	void delByMainId(String id);
	
	List<ScmZsjAccessory> getByTypeAndDataId(String type,String dataId);
}
