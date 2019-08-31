package com.winway.scm.persistence.manager;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjProduct;
import com.winway.scm.model.ScmZsjProductFirst;

/**
 * 
 * <pre> 
 * 描述：商品表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjProductManager extends Manager<String, ScmZsjProduct>{

	List<ScmZsjProduct> getByProduct(String ownerId);

	void getByNameOrId(ScmZsjProduct scmZsjProduct);

	PageList<ScmZsjProduct> firstList(QueryFilter queryFilter);

	List<ScmZsjProduct> downBox(String ownerId);

	void isExist(ScmZsjProduct scmZsjProduct);

	List<ScmZsjProduct> downBoxApplySuccess(String ownerId, String supplierId);

	List<Map<String, Object>> LineAndProductDownBox();

	List<String> listAndAchage(String productCode);

	ScmZsjProduct getProductByCode(String productCode);

	List<Map<String, Object>> LineAndProductDownBoxByfirst(String ownerId);

	void sendApply(ScmZsjProduct scmZsjProduct);

	void endApply(JsonNode jsonNode);

	ScmZsjProduct getByApprovalId(String approvalId);

    List<ScmZsjProduct> productAndAchage(QueryFilter queryFilter);

	void updateSyn(String id);

    ScmZsjProduct getProductByName(String productName);

    List<ScmZsjProduct> getProductByCommonName(String commonName);

    List<ScmZsjProduct> getProductByCommonNameAndState(String commonName, String productState);
}
