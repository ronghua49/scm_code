package com.winway.scm.persistence.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.util.UniqueIdUtil;
import com.winway.scm.model.FastEntryData;
import com.winway.scm.model.FastEntryVo;
import com.winway.scm.persistence.dao.FastEntryDao;
import com.winway.scm.persistence.manager.FastEntryManager;
/**
 * 
* @Description: FMS 快速入口manager接口实现类
* @author: 贺军
* @date: 2018年10月15日 下午6:08:05 
* @history
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年10月15日     	贺军         			v1.0.0            	新增
 */
@Service("fastEntryManager")
public class FastEntryManagerImpl  extends AbstractManagerImpl<String, FastEntryData> implements FastEntryManager {
	@Autowired
	FastEntryDao fastEntryDao;
	
	@Override
	protected MyBatisDao<String, FastEntryData> getDao() {
		// TODO Auto-generated method stub
		return fastEntryDao;
	}

	@Override
	public List<FastEntryData> queryFastEntryByAccount(String account) throws Exception {
		// TODO Auto-generated method stub
		return fastEntryDao.queryFastEntryByAccount(account);
	}
	
	@Override
	@Transactional
	public void saveAll(FastEntryVo paramData) {
		fastEntryDao.deleteByAccount(paramData.getAccount());
		List<FastEntryData> datas = paramData.getDatas();
		for(FastEntryData data : datas) {
			data.setId(UniqueIdUtil.getSuid());
		}
		fastEntryDao.saveAll(datas);
	}
	
	

}
