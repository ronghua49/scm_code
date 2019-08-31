package com.winway.scm.persistence.dao;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsDealerPayStyle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * <pre> 
 * 描述：经销商协议付款方式 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-01 15:24:12
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsDealerPayStyleDao extends MyBatisDao<String, ScmXsDealerPayStyle> {
    List<ScmXsDealerPayStyle> getByMainId(@Param(value = "id") String id);

    void delByMainId(@Param(value = "masterId") String masterId);

    void delLastByMainId(@Param(value = "masterId") String masterId);

    void createLast(ScmXsDealerPayStyle payStyle);
}
