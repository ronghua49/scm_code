package com.winway.scm.persistence.dao;
import java.util.List;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmFhShipmentsDatail;
import com.winway.scm.vo.ScmFhShipmentsDataiAndMessageVo;

/**
 * 
 * <pre> 
 * 描述：发货明细表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhShipmentsDatailDao extends MyBatisDao<String, ScmFhShipmentsDatail> {
	/**
	 * 根据外键获取子表明细列表
	 * @param masterId
	 * @return
	 */
	public List<ScmFhShipmentsDatail> getByMainId(String masterId);
	
	/**
	 * 根据外键删除子表记录
	 * @param masterId
	 * @return
	 */
	public void delByMainId(String masterId);

	public List<Map<String, Object>> getYear(String org0);


	public List<ScmFhShipmentsDatail> messageList(Map<String, Object> params);

	public List<ScmFhShipmentsDataiAndMessageVo> firstList(Map<String, Object> params);
	
}
