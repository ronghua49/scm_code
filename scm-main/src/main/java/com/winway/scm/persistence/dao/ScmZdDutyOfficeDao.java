package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZdDutyOffice;

/**
 * 
 * <pre> 
 * 描述：责任办事处 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
public interface ScmZdDutyOfficeDao extends MyBatisDao<String, ScmZdDutyOffice> {

	List<ScmZdDutyOffice> getByNameAndCodeOrId(ScmZdDutyOffice scmZdDutyOffice);
}
