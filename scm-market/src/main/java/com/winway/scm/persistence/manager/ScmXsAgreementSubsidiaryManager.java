package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmXsAgreementSubsidiary;

/**
 * 
 * <pre> 
 * 描述：商业协议分子公司表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsAgreementSubsidiaryManager extends Manager<String, ScmXsAgreementSubsidiary>{

	void delByMainId(String arg0);
	
}
