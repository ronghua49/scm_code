package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmKcWeekInventory;
import com.winway.scm.vo.ScmKcMonthInventory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 
 * <pre> 
 * 描述：scm_kc_weekinventory DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-08 15:15:30
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmKcWeekInventoryDao extends MyBatisDao<String, ScmKcWeekInventory> {

	List<ScmKcMonthInventory> monthList(Map<String, Object> params);
    ScmKcWeekInventory getByInventoryAndBatch(@Param(value = "inventoryDate") Date inventoryDate, @Param(value = "batchNum") String batchNum,@Param(value = "commerceCode")String commerceCode,@Param(value = "productCode")String productCode);
    List<ScmKcWeekInventory> getLastWeekStock(@Param(value = "productCode") String productCode,@Param(value = "commerceCode") String commerceCode, @Param(value = "batchNum") String batchNum,@Param(value = "week") int week);
    void createAll(@Param("weekInventories") List<ScmKcWeekInventory> weekInventories);
    void deleteByWeekAndCommerce(@Param("week") Integer week, @Param("commerces") List<String> commerces);
}
