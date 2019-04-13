package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZdProvince;

/**
 * 
 * <pre> 
 * 描述：省区 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-29 10:31:24
 * 版权：美达项目组
 * </pre>
 */
public interface ScmZdProvinceDao extends MyBatisDao<String, ScmZdProvince> {
	/**
	 * 根据外键获取子表明细列表
	 * @param businessDivisionId
	 * @return
	 */
	public List<ScmZdProvince> getByMainId(String businessDivisionId);
	
	/**
	 * 根据外键删除子表记录
	 * @param businessDivisionId
	 * @return
	 */
	public void delByMainId(String businessDivisionId);
	
}
