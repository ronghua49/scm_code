package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsAgreementSubsidiary;

/**
 * 
 * <pre> 
 * 描述：商业协议分子公司表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsAgreementSubsidiaryDao extends MyBatisDao<String, ScmXsAgreementSubsidiary> {
	/**
	 * 根据外键获取子表明细列表
	 * @param AgreementId
	 * @return
	 */
	public List<ScmXsAgreementSubsidiary> getByMainId(String AgreementId);
	
	/**
	 * 根据外键删除子表记录
	 * @param AgreementId
	 * @return
	 */
	public void delByMainId(String AgreementId);
	
}
