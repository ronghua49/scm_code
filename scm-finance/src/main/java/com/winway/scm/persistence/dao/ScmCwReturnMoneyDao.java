package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmCwReturnMoney;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：商业回款表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwReturnMoneyDao extends MyBatisDao<String, ScmCwReturnMoney> {
	/**
	 * 连表查询
	 * @param ownerId 货主Id
	 * @return
	 */
	String findByName(String ownerId);

    List<ScmCwReturnMoney> getByParam(@Param(value = "commerceCode") String commerceCode, @Param(value = "remittanceDate") Date remittanceDate, @Param(value = "price") Double price);

	List<ScmCwReturnMoney> sumList(Map<String, Object> params);
}
