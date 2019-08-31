package com.winway.scm.persistence.manager;

import java.util.List;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCwDiscountPond;
import com.winway.scm.model.ScmCwDiscountPondPriceChange;
import com.winway.scm.model.ScmCwToSapDiscount;
import com.winway.scm.vo.SO_HEADER;
import com.winway.scm.vo.ScmCwCompensationVo;
import com.winway.scm.vo.ScmCwInvoiceVo;

/**
 * 
 * <pre> 
 * 描述：票折池表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwDiscountPondManager extends Manager<String, ScmCwDiscountPond>{

	String freezeManage(String id);

	ScmCwToSapDiscount autoDiscount(String id, String discountTypeId);

	List<ScmCwCompensationVo> saveByCompensation(List<ScmCwCompensationVo> scmCwCompensationVo);

	void changePoodPrice(ScmCwDiscountPondPriceChange scmCwDiscountPondPriceChange);
	
}
