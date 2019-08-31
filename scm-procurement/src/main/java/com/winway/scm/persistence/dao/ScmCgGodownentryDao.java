package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmCgGodownentry;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * <pre> 
 * 描述：入库单商品表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-28 14:54:15
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
public interface ScmCgGodownentryDao extends MyBatisDao<String, ScmCgGodownentry> {
	/**
	 * 根据外键获取子表明细列表
	 * @param masterId
	 * @return
	 */
    List<ScmCgGodownentry> getByMainId(String masterId);
	
	/**
	 * 根据外键删除子表记录
	 * @param masterId
	 * @return
	 */
    void delByMainId(String masterId);

    List<ScmCgGodownentry> getGodownentyByCode(@Param(value = "lotno") String lotno);
}
