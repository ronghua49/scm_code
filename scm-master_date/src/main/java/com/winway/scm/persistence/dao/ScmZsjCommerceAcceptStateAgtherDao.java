package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmZsjCommerceAcceptStateAgther;




/**
 * 
 * <pre> 
 * 描述：商业认可状态总表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmZsjCommerceAcceptStateAgtherDao extends MyBatisDao<String, ScmZsjCommerceAcceptStateAgther> {

	ScmZsjCommerceAcceptStateAgther getByApprovalId(String approvalId);

}
