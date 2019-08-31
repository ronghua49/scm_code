package com.winway.scm.persistence.dao;
import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmFhShipmentsMessage;

/**
 * 
 * <pre> 
 * 描述：物流发运信息表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmFhShipmentsMessageDao extends MyBatisDao<String, ScmFhShipmentsMessage> {

	List<ScmFhShipmentsMessage> getByMainId(String id);

}
