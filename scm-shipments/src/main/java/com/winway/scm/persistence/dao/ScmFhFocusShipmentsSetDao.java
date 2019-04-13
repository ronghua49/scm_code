package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmFhFocusShipmentsSet;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * <pre> 
 * 描述：集中发货设置表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhFocusShipmentsSetDao extends MyBatisDao<String, ScmFhFocusShipmentsSet> {

	List<ScmFhFocusShipmentsSet> listFocusShipmentsSetByComm(String commerceId, String ownerId);

    ScmFhFocusShipmentsSet getByProIdAndComId(@Param(value = "commerceId") String commerceId,@Param(value = "productId") String productId);
}
