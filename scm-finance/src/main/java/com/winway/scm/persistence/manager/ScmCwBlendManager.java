package com.winway.scm.persistence.manager;

import java.util.List;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCwBlend;

/**
 * 
 * <pre> 
 * 描述：勾兑记录表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwBlendManager extends Manager<String, ScmCwBlend>{

	String cancel(ScmCwBlend blend);

	String charge(List<ScmCwBlend> scmCwBlend, String fullname);

	String giveCharge(String[] ids, String fullname);

	
	
}
