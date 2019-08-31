package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmZsjTXCompany;

/**
 * 
 * <pre> 
 * 描述：天祥信息 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjTXCompanyManager extends Manager<String, ScmZsjTXCompany>{

	ScmZsjTXCompany getByOwnerId(String ownerId);

    ScmZsjTXCompany getRB(String ownerId);

    void updateRB(ScmZsjTXCompany scmZsjTXCompany);

	String getFignRB(String ownerId);
}
