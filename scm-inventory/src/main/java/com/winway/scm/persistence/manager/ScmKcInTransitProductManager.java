package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmKcInTransitProduct;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * <pre> 
 * 描述：在途商品 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-08-17 14:17:53
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmKcInTransitProductManager extends Manager<String, ScmKcInTransitProduct>{

    void leadExcel(MultipartFile file,String fullname);
}
