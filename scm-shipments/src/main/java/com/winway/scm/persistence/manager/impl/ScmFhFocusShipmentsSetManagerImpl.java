package com.winway.scm.persistence.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmFhFocusShipmentsSetDao;
import com.winway.scm.model.ScmFhFocusShipmentsSet;
import com.winway.scm.persistence.manager.ScmFhFocusShipmentsSetManager;

/**
 * 
 * <pre> 
 * 描述：集中发货设置表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhFocusShipmentsSetManager")
public class ScmFhFocusShipmentsSetManagerImpl extends AbstractManagerImpl<String, ScmFhFocusShipmentsSet> implements ScmFhFocusShipmentsSetManager{
	@Resource
	ScmFhFocusShipmentsSetDao scmFhFocusShipmentsSetDao;
	@Override
	protected MyBatisDao<String, ScmFhFocusShipmentsSet> getDao() {
		return scmFhFocusShipmentsSetDao;
	}
	@Override
	public void saves(List<ScmFhFocusShipmentsSet> scmFhFocusShipmentsSets, String userName) {
		for (ScmFhFocusShipmentsSet scmFhFocusShipmentsSet : scmFhFocusShipmentsSets) {
			ScmFhFocusShipmentsSet find = scmFhFocusShipmentsSetDao.getByProIdAndComId(scmFhFocusShipmentsSet.getCommerceId(),scmFhFocusShipmentsSet.getProductId());
			if(find!=null){
				continue;
			}
			scmFhFocusShipmentsSet.setCreateDate(new Date());
			scmFhFocusShipmentsSet.setStartUsingDate(new Date());
			scmFhFocusShipmentsSet.setCreatePersion(userName);
			create(scmFhFocusShipmentsSet);
		}
	}
	@Override
	public String startAndStop(String id) {
		ScmFhFocusShipmentsSet scmFhFocusShipmentsSet = get(id);
		if(scmFhFocusShipmentsSet == null) {
			throw new RuntimeException("数据不存在");
		}
		String isStartUsing = scmFhFocusShipmentsSet.getIsStartUsing();
		if("0".equals(isStartUsing)) {
			scmFhFocusShipmentsSet.setIsStartUsing("1");
			scmFhFocusShipmentsSet.setStartUsingDate(new Date());
		}else{
			scmFhFocusShipmentsSet.setIsStartUsing("0");
		}
		update(scmFhFocusShipmentsSet);
		return scmFhFocusShipmentsSet.getIsStartUsing();
	}
	@Override
	public String 	listFocusShipmentsSetByComm(String commerceId, String ownerId) throws JsonProcessingException {
		List<ScmFhFocusShipmentsSet> listFocusShipmentsSetByComm = scmFhFocusShipmentsSetDao.listFocusShipmentsSetByComm(commerceId,ownerId);
		ObjectMapper objectMapper = new ObjectMapper();
		String fileJson = objectMapper.writeValueAsString(listFocusShipmentsSetByComm);
		return fileJson;
	}
	
	
}
