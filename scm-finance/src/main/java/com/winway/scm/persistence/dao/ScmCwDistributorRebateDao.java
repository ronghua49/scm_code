package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwDistributorRebate;

/**
 * 
 * <pre> 
 * 描述：分销商返利明细 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-14 16:38:31
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwDistributorRebateDao extends MyBatisDao<String, ScmCwDistributorRebate> {
	/**
	 * 根据外键获取子表明细列表
	 * @param masterId
	 * @return
	 */
    List<ScmCwDistributorRebate> getByMainId(String masterId);
	
	/**
	 * 根据外键删除子表记录
	 * @param masterId
	 * @return
	 */
    void delByMainId(String masterId);
	
}
