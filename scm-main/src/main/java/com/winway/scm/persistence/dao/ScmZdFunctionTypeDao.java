package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZdFunctionType;

/**
 * 
 * <pre> 
 * 描述：scm_zd_functiontype DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-16 10:34:19
 * 版权：美达项目组
 * </pre>
 */
public interface ScmZdFunctionTypeDao extends MyBatisDao<String, ScmZdFunctionType> {
	/**
	 * 根据外键获取子表明细列表
	 * @param commerceLevelId
	 * @return
	 */
	public List<ScmZdFunctionType> getByMainId(String commerceLevelId);
	
	/**
	 * 根据外键删除子表记录
	 * @param commerceLevelId
	 * @return
	 */
	public void delByMainId(String commerceLevelId);

	public List<ScmZdFunctionType> ListScmZdFunctionTypeByLevelId(String id);

	public List<ScmZdFunctionType> getByNameAndCodeOrId(ScmZdFunctionType scmZdFunctionType);
	
}
