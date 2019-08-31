package com.winway.scm.persistence.manager.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.winway.scm.persistence.dao.ScmZsjProductLineDao;
import com.winway.purchase.tool.ScmWmsConfiguration;
import com.winway.purchase.util.HttpClientUtil;
import com.winway.scm.model.ScmZsjProductLine;
import com.winway.scm.model.ScmZsjSupplier;
import com.winway.scm.persistence.manager.ScmZsjProductLineManager;
import com.winway.scm.persistence.vo.ProdCategory;
import com.winway.scm.persistence.vo.ProductVo;
import com.winway.scm.persistence.vo.Supplier;

/**
 * 
 * <pre> 
 * 描述：产品系列 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 16:15:43
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjProductLineManager")
public class ScmZsjProductLineManagerImpl extends AbstractManagerImpl<String, ScmZsjProductLine> implements ScmZsjProductLineManager{
	@Resource
	ScmZsjProductLineDao scmZsjProductLineDao;
	@Override
	protected MyBatisDao<String, ScmZsjProductLine> getDao() {
		return scmZsjProductLineDao;
	}
	@Override
	public String changeState(String id) {
		ScmZsjProductLine scmZsjProductLine = scmZsjProductLineDao.get(id);
		if("0".equals(scmZsjProductLine.getProductLineState())) {
			//当前状态为0  调整为启用状态
			scmZsjProductLine.setProductLineState("1");
		}else{
			scmZsjProductLine.setProductLineState("0");
		}
		scmZsjProductLineDao.update(scmZsjProductLine);
		return scmZsjProductLine.getProductLineState();
	}
	@Override
	public void create(ScmZsjProductLine entity) {
		String code = scmZsjProductLineDao.getProductLineCode();
		entity.setProductLineCode(code);
		ProdCategory prodCategory = new ProdCategory();
		prodCategory.setCatName(entity.getProductLineName());
		prodCategory.setCatCode(entity.getProductLineCode());
		prodCategory.setCatShortName(entity.getProductLineAcronym());
		prodCategory.setCatGenName(entity.getProductLineCommon());
		prodCategory.setCatEnglishName(entity.getProductLineEname());
		prodCategory.setDosage(entity.getAgentType());
		prodCategory.setType(entity.getMedicineType());
		prodCategory.setStatus(1);
		prodCategory.setIsDelete(0);
		prodCategory.setLevelHospital(entity.getLevelHospital());
		prodCategory.setCreateName(entity.getCreatePersion());
		List<ProdCategory> list = new ArrayList<ProdCategory>();
		list.add(prodCategory);
		String jsonString = JSON.toJSONString(list);
		Map<String,String> header = new HashMap<String,String>();
		header.put("accept", "application/json;charset=UTF-8");
		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
		header.put("Content-Type", "application/json");
		String returnStr = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL+"/masterdata/prodCategory/v1/saveList", jsonString,header);
		CommonResult parseObject = JSON.parseObject(returnStr, CommonResult.class);
		String code2 = parseObject.getCode();
		if("10000".equals(code2)) {
			entity.setIsToMasterData("1");
			super.create(entity);
		}else{
			entity.setIsToMasterData("0");
			super.create(entity);
		}
	}
	
	@Override
	public void update(ScmZsjProductLine entity) {
		ScmZsjProductLine scmZsjProductLine = scmZsjProductLineDao.get(entity.getId());
		if(scmZsjProductLine == null) {
			throw new RuntimeException("未查询到历史数据");
		}
		entity.setProductLineCode(scmZsjProductLine.getProductLineCode());
		entity.setCreatePersion(scmZsjProductLine.getCreatePersion());
		entity.setCreateDate(scmZsjProductLine.getCreateDate());
		ProdCategory prodCategory = new ProdCategory();
		prodCategory.setCatName(entity.getProductLineName());
		prodCategory.setCatCode(entity.getProductLineCode());
		prodCategory.setCatShortName(entity.getProductLineAcronym());
		prodCategory.setCatGenName(entity.getProductLineCommon());
		prodCategory.setCatEnglishName(entity.getProductLineEname());
		prodCategory.setDosage(entity.getAgentType());
		prodCategory.setType(entity.getMedicineType());
		prodCategory.setStatus(1);
		prodCategory.setIsDelete(0);
		prodCategory.setLevelHospital(entity.getLevelHospital());
		prodCategory.setCreateName(entity.getCreatePersion());
		List<ProdCategory> list = new ArrayList<ProdCategory>();
		list.add(prodCategory);
		String jsonString = JSON.toJSONString(list);
		Map<String,String> header = new HashMap<String,String>();
		header.put("accept", "application/json;charset=UTF-8");
		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
		header.put("Content-Type", "application/json");
		String returnStr = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL+"/masterdata/prodCategory/v1/saveList", jsonString,header);
		CommonResult parseObject = JSON.parseObject(returnStr, CommonResult.class);
		String code2 = parseObject.getCode();
		if("10000".equals(code2)) {
			entity.setIsToMasterData("1");
			super.update(entity);
		}else{
			entity.setIsToMasterData("0");
			super.update(entity);
		}
	}
	
	@Override
	public void updateSyn(String id) {
		ScmZsjProductLine entity = scmZsjProductLineDao.get(id);
		ProdCategory prodCategory = new ProdCategory();
		prodCategory.setCatName(entity.getProductLineName());
		prodCategory.setCatCode(entity.getProductLineCode());
		prodCategory.setCatShortName(entity.getProductLineAcronym());
		prodCategory.setCatGenName(entity.getProductLineCommon());
		prodCategory.setCatEnglishName(entity.getProductLineEname());
		prodCategory.setDosage(entity.getAgentType());
		prodCategory.setType(entity.getMedicineType());
		prodCategory.setStatus(1);
		prodCategory.setIsDelete(0);
		prodCategory.setLevelHospital(entity.getLevelHospital());
		prodCategory.setCreateName(entity.getCreatePersion());
		List<ProdCategory> list = new ArrayList<ProdCategory>();
		list.add(prodCategory);
		String jsonString = JSON.toJSONString(list);
		Map<String,String> header = new HashMap<String,String>();
		header.put("accept", "application/json;charset=UTF-8");
		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
		header.put("Content-Type", "application/json");
		String returnStr = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL+"/masterdata/product/v1/saveList", jsonString,header);
		CommonResult parseObject = JSON.parseObject(returnStr, CommonResult.class);
		String code2 = parseObject.getCode();
		if("10000".equals(code2)) {
			entity.setIsToMasterData("1");
			scmZsjProductLineDao.update(entity);
		}else{
			throw new RuntimeException(parseObject.getMessage());
		}
	}
	
}
