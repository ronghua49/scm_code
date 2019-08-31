package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmGspMarketExceptionSet;
import com.winway.scm.vo.ScmGspMarketExceptionMonitorListVo;

/**
 * 
 * <pre> 
 * 描述：销售异动商品设置表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmGspMarketExceptionSetDao extends MyBatisDao<String, ScmGspMarketExceptionSet> {

	List<ScmGspMarketExceptionMonitorListVo> monitorList(String oneMonth, String towMonth, String ownerId);
}
