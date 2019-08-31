package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsBigContractProduct;

/**
 * 
 * <pre> 
 * 描述：大合同商品表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsBigContractProductDao extends MyBatisDao<String, ScmXsBigContractProduct> {
	/**
	 * 根据外键获取子表明细列表
	 * @param bigContractId
	 * @return
	 */
    List<ScmXsBigContractProduct> getByMainId(String bigContractId);
	
	/**
	 * 根据外键删除子表记录
	 * @param bigContractId
	 * @return
	 */
    void delByMainId(String bigContractId);

	List<ScmXsBigContractProduct> groupByList(Map<String, Object> params);

	List<ScmXsBigContractProduct> bigcontractProductList(Map<String, Object> params);

	List<ScmXsBigContractProduct> groupList(Map<String, Object> params);

	List<ScmXsBigContractProduct> everyDayContractProductList(Map<String, Object> params);

	Map getScmXsBigcontractPrintDetail(String id);

	List<Map> getDetail(String id);
}
