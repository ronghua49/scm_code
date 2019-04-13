package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmZdDutyAffirm;
import com.winway.scm.model.ScmZsjProduct;
import com.winway.scm.model.ScmZsjProductFirst;

/**
 * 
 * <pre> 
 * 描述：商品表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjProductDao extends MyBatisDao<String, ScmZsjProduct> {

	List<ScmZsjProduct> getByName(String productName);

	List<ScmZsjProduct> getByProduct(String ownerId);

	List<ScmZdDutyAffirm> getByNameOrId(ScmZsjProduct scmZsjProduct);

	List<ScmZsjProduct> firstList(Map<String, Object> map);

	List<ScmZsjProduct> downBox(String ownerId);

	List<ScmZsjProduct> downBoxApplySuccess(String ownerId, String SupplierId);

	List<ScmZsjProduct> listByLineId(String id);

	List<Map<String, Object>> listAndAchage(String productCode);

	ScmZsjProduct getProductByCode(String productCode);

}
