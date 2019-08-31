package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCgProRepProduct;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * <pre> 
 * 描述：采购补单商品表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgProRepProductDao extends MyBatisDao<String, ScmCgProRepProduct> {
	/**
	 * 根据外键获取子表明细列表
	 * @param replacementOrderId
	 * @return
	 */
    List<ScmCgProRepProduct> getByMainId(String ReplacementOrderId);
	
	/**
	 * 根据外键删除子表记录
	 * @param replacementOrderId
	 * @return
	 */
    void delByMainId(String replacementOrderId);

	Map listProductByProRepOrderId(String id);
	
	List<ScmCgProRepProduct> listProductByOrderId(@Param(value ="replacementOrderId" ) String replacementOrderId, @Param(value = "code") String code);
	
	List<Map> getScmCgProRepProductListM(String replacementOrderId);
}