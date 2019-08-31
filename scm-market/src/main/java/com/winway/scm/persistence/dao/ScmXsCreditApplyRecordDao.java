package com.winway.scm.persistence.dao;
import java.util.Map;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsCreditApplyRecord;

/**
 * 
 * <pre> 
 * 描述：Scm_Xs_CreditApplyRecord DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-16 11:39:59
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsCreditApplyRecordDao extends MyBatisDao<String, ScmXsCreditApplyRecord> {

	Map<String, Object> getResidueCreditByCommerceCode(String commerceCode, String ownerId, String format);

	void removeByDataIdAndType(String id, String string);

	ScmXsCreditApplyRecord getByDataId(String dataId, String ownerId, String recordType);
}
