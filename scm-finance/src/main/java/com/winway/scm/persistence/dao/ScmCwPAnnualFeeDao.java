package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwPAnnualFee;

/**
 * 
 * <pre> 
 * 描述：经销商RB支付年费信息管理费 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwPAnnualFeeDao extends MyBatisDao<String, ScmCwPAnnualFee> {
	/**
	 * 根据外键获取子表明细列表
	 * @param masterId
	 * @return
	 */
    List<ScmCwPAnnualFee> getByMainId(String masterId);
	
	/**
	 * 根据外键删除子表记录
	 * @param masterId
	 * @return
	 */
    void delByMainId(String masterId);
	
}
