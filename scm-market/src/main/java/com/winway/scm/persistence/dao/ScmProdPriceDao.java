package com.winway.scm.persistence.dao;
import org.apache.ibatis.annotations.Param;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmProdPrice;

/**
 * 
 * <pre> 
 * 描述：商品价格 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-27 15:55:07
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmProdPriceDao extends MyBatisDao<String, ScmProdPrice> {
	
	
	ScmProdPrice	getScmprodprice(@Param("provincecode") String provincecode, @Param("prodcode") String prodcode);
	
	
	ScmProdPrice	getScmprodpriceBycountry(@Param("prodcode")String prodcode);
	
}
