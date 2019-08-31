package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmZdDutyAffirm;
import com.winway.scm.model.ScmZsjProduct;
import com.winway.scm.model.ScmZsjProductFirst;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;

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

	List<ScmZsjProduct> downBoxApplySuccess(String ownerId, String SupplierId, Set<String> codes);

	List<ScmZsjProduct> listByLineId(String id);

	List<Map<String, Object>> listAndAchage(String productCode);

	ScmZsjProduct getProductByCode(String productCode);

	List<ScmZsjProduct> getbyMainId(String id);

	List<ScmZsjProduct> listByLineIdAndFirst(String id, String ownerId);

	ScmZsjProduct getByApprovalId(String approvalId);

	String getProductCode();

	ScmZsjProduct getbyProductNum(String productCode);

    List<ScmZsjProduct> productAndAchage(Map<String, Object> params);

    ScmZsjProduct getProductByName(@Param(value = "productName") String productName);

    List<ScmZsjProduct> getProductByCommonName(@Param(value = "commonName") String commonName);

    List<ScmZsjProduct> getProductByCommonNameAndState(@Param(value = "commonName")String commonName, @Param(value = "productState")String productState);

	ScmZsjProduct getProductMessageByCode(String code);
}
