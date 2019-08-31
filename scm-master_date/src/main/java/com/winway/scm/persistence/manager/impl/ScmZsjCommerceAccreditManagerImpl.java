package com.winway.scm.persistence.manager.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmZsjCommerceAccreditDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceEntruseBookDao;
import com.winway.scm.model.ScmZsjCommerceAccredit;
import com.winway.scm.persistence.manager.ScmZsjCommerceAccreditManager;


/**
 * 
 * <pre> 
 * 描述：商业授权销售品种 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceAccreditManager")
public class ScmZsjCommerceAccreditManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceAccredit> implements ScmZsjCommerceAccreditManager{
	@Resource
	ScmZsjCommerceAccreditDao scmZsjCommerceAccreditDao;
	@Resource
	ScmZsjCommerceEntruseBookDao scmZsjCommerceEntruseBookDao;
	@Override
	protected MyBatisDao<String, ScmZsjCommerceAccredit> getDao() {
		return scmZsjCommerceAccreditDao;
	}
	
	@Override
	public void saveList(ScmZsjCommerceAccredit[] scmZsjCommerceAccredit) {
		List<ScmZsjCommerceAccredit> asList = Arrays.asList(scmZsjCommerceAccredit);
		ScmZsjCommerceAccredit scmZsjCommerceAccredit2 = asList.get(0);
		if(scmZsjCommerceAccredit2 == null) {
			throw new RuntimeException("未获取到授权销售品种数据");
		}else{
			String entrustId = scmZsjCommerceAccredit2.getEntrustId();
			if("".equals(entrustId) || "null".equals(entrustId) || entrustId == null) {
				throw new RuntimeException("未获取到委托书信息");
			}
			//删除原来的数据信息
			scmZsjCommerceAccreditDao.delByMainId(entrustId);
		}
		for (ScmZsjCommerceAccredit scmZsjCommerceAccredit3 : asList) {
			super.create(scmZsjCommerceAccredit3);
		}
		
	}
}
