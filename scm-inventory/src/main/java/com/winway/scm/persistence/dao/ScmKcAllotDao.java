package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmKcAllot;

/**
 * 
 * <pre> 
 * 描述：库存调拨商品表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-23 17:00:46
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmKcAllotDao extends MyBatisDao<String, ScmKcAllot> {
	/**
	 * 根据外键获取子表明细列表
	 * @param masterId
	 * @return
	 */
    List<ScmKcAllot> getByMainId(String masterId);
	
	/**
	 * 根据外键删除子表记录
	 * @param masterId
	 * @return
	 */
    void delByMainId(String masterId);
	
}
