package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCwBAPaymentDiscount;
import com.winway.scm.vo.ScmCwBAPaymentDiscountVo;

/**
 * 
 * <pre> 
 * 描述：经销商RB核算付款折扣 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwBAPaymentDiscountDao extends MyBatisDao<String, ScmCwBAPaymentDiscount> {
	/**
	 * 根据外键获取子表明细列表
	 * @param masterId
	 * @return
	 */
    List<ScmCwBAPaymentDiscount> getByMainId(String masterId);
	
	/**
	 * 根据外键删除子表记录
	 * @param masterId
	 * @return
	 */
    void delByMainId(String masterId);

	List<ScmCwBAPaymentDiscount> affirmList(Map<String, Object> params);

    List<ScmCwBAPaymentDiscountVo> affirmListSum(Map<String, Object> params);
}
