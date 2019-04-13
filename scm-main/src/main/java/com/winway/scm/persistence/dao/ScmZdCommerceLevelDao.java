package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZdCommerceLevel;

/**
 * 
 * <pre> 
 * 描述：scm_zd_commercelevel DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-16 10:34:19
 * 版权：美达项目组
 * </pre>
 */
public interface ScmZdCommerceLevelDao extends MyBatisDao<String, ScmZdCommerceLevel> {

	List<ScmZdCommerceLevel> listScmZdCommerceLevel();

	List<ScmZdCommerceLevel> getByNameAndCodeOrId(ScmZdCommerceLevel scmZdCommerceLevel);
}
