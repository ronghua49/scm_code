package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCgGodownentry;

import java.util.List;

/**
 * 
 * <pre> 
 * 描述：入库单商品表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-28 14:54:15
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgGodownentryManager extends Manager<String, ScmCgGodownentry>{

    List<ScmCgGodownentry> getGodownentyByCode(String productCode);
}
