package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwAnnualFee;

/**
 * 
 * <pre> 
 * 描述：年费信息管理费详情 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-28 09:38:34
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwAnnualFeeDao extends MyBatisDao<String, ScmCwAnnualFee> {
	/**
	 * 根据外键获取子表明细列表
	 * @param masterId
	 * @return
	 */
    List<ScmCwAnnualFee> getByMainId(String masterId);
	
	/**
	 * 根据外键删除子表记录
	 * @param masterId
	 * @return
	 */
    void delByMainId(String masterId);
	
}
