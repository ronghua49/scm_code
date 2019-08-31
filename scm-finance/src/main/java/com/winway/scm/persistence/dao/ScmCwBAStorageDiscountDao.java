package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwBAStorageDiscount;
import com.winway.scm.vo.ScmCwBAPaymentDiscountVo;
import com.winway.scm.vo.ScmCwBAStorageDiscountVo;

/**
 * 
 * <pre> 
 * 描述：经销商RB核算储运折扣 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwBAStorageDiscountDao extends MyBatisDao<String, ScmCwBAStorageDiscount> {
	/**
	 * 根据外键获取子表明细列表
	 * @param masterId
	 * @return
	 */
    List<ScmCwBAStorageDiscount> getByMainId(String masterId);
	
	/**
	 * 根据外键删除子表记录
	 * @param masterId
	 * @return
	 */
    void delByMainId(String masterId);

	List<ScmCwBAStorageDiscount> affirmList(Map<String, Object> params);

	List<ScmCwBAStorageDiscountVo> affirmListSum(Map<String, Object> params);
}
