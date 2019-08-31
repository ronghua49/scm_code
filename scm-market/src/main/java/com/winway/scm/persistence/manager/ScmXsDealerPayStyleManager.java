package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmXsDealerPayStyle;

import java.util.List;

/**
 * 
 * <pre> 
 * 描述：经销商协议付款方式 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-01 15:24:12
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsDealerPayStyleManager extends Manager<String, ScmXsDealerPayStyle>{

    List<ScmXsDealerPayStyle> getByMainId(String id);
}
