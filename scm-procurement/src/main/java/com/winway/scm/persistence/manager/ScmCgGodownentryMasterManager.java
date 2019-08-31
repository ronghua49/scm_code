package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.purchase.vo.ReturnWmsVo;
import com.winway.scm.model.ScmCgGodownentryMaster;

/**
 * 
 * <pre> 
 * 描述：入库单主表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-28 14:54:15
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgGodownentryMasterManager extends Manager<String, ScmCgGodownentryMaster>{

	ReturnWmsVo acceptDate(ScmCgGodownentryMaster scmCgGodownentryMaster);
	
}
