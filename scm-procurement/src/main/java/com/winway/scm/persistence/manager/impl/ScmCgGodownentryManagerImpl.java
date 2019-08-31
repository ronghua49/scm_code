package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmCgGodownentryDao;
import com.winway.scm.persistence.dao.ScmCgProReplacementOrderDao;
import com.winway.scm.persistence.dao.ScmCgProcurementOrderDao;
import com.winway.scm.model.ScmCgGodownentry;
import com.winway.scm.model.ScmCgProReplacementOrder;
import com.winway.scm.model.ScmCgProcurementOrder;
import com.winway.scm.persistence.manager.ScmCgGodownentryManager;

import java.util.List;

/**
 * 
 * <pre> 
 * 描述：入库单商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-28 14:54:15
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgGodownentryManager")
public class ScmCgGodownentryManagerImpl extends AbstractManagerImpl<String, ScmCgGodownentry> implements ScmCgGodownentryManager{
	@Resource
	ScmCgGodownentryDao scmCgGodownentryDao;
	@Resource
	ScmCgProcurementOrderDao scmCgProcurementOrderDao;
	@Resource
	ScmCgProReplacementOrderDao scmCgProReplacementOrderDao;
	@Override
	protected MyBatisDao<String, ScmCgGodownentry> getDao() {
		return scmCgGodownentryDao;
	}

	@Override
	public List<ScmCgGodownentry> getGodownentyByCode(String productCode) {
		return scmCgGodownentryDao.getGodownentyByCode(productCode);
	}
	@Override
	public void create(ScmCgGodownentry arg0) {
		super.create(arg0);
		String billid = arg0.getBillid();
		if(billid == null || "".equals(billid)) {
			throw new RuntimeException("请回传采购单编号");
		}
		if(billid.startsWith("CGDD")) {
			//采购订单
			ScmCgProcurementOrder byOrdercode = scmCgProcurementOrderDao.getByOrdercode(billid);
			if(byOrdercode == null) {
				throw new RuntimeException("未查询到采购订单,请验证采购单号");
			}
			if("2".equals(byOrdercode.getIsAffirmWms())) {
				//已经完成
				return;
			}
			byOrdercode.setIsAffirmWms("1");
			scmCgProcurementOrderDao.update(byOrdercode);
		}else if(billid.startsWith("CGBD")) {
			//采购补单
			ScmCgProReplacementOrder proOrderFirstByCode = scmCgProReplacementOrderDao.getProOrderFirstByCode(billid);
			if(proOrderFirstByCode == null) {
				throw new RuntimeException("未查询到采购订单,请验证采购单号");
			}
			if("2".equals(proOrderFirstByCode.getIsAffirmWms())) {
				//已经完成
				return;
			}
			proOrderFirstByCode.setIsAffirmWms("1");
			scmCgProReplacementOrderDao.update(proOrderFirstByCode);
		}else{
			throw new RuntimeException("请传递正确的采购单编号");
		}
	}
}
