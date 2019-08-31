package com.winway.scm.persistence.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.exception.SystemException;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.winway.purchase.tool.ScmWmsConfiguration;
import com.winway.purchase.util.HttpClientUtil;
import com.winway.scm.model.ScmZsjAchage;
import com.winway.scm.model.ScmZsjProduct;
import com.winway.scm.model.ScmZsjProductFirst;
import com.winway.scm.model.ScmZsjProductLine;
import com.winway.scm.persistence.dao.ScmZsjAchageDao;
import com.winway.scm.persistence.dao.ScmZsjProductDao;
import com.winway.scm.persistence.dao.ScmZsjProductFirstDao;
import com.winway.scm.persistence.dao.ScmZsjProductLineDao;
import com.winway.scm.persistence.manager.ScmZsjAchageManager;
import com.winway.scm.persistence.manager.ScmZsjProductFirstManager;
import com.winway.scm.persistence.vo.ProductAchange;
import com.winway.scm.persistence.vo.ProductVo;

/**
 * <pre>
 * 描述：件装量 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-18 14:36:01
 * 版权：美达开发小组
 * </pre>
 */
@Service("ScmZsjAchageManager")
public class ScmZsjAchageManagerImpl extends AbstractManagerImpl<String, ScmZsjAchage> implements ScmZsjAchageManager {

    @Resource
    ScmZsjAchageDao scmZsjAchageDao;
    @Resource
    ScmZsjProductDao scmZsjProductDao;
    @Resource
    ScmZsjProductFirstDao scmZsjProductFirstDao;
    @Resource
    ScmZsjProductFirstManager scmZsjProductFirstManager;
    @Resource
    ScmZsjProductLineDao scmZsjProductLineDao;

    @Override
    protected MyBatisDao<String, ScmZsjAchage> getDao() {
        return scmZsjAchageDao;
    }

    @Override
    public void create(ScmZsjAchage scmZsjAchage) {
        String id = scmZsjAchage.getProductId();
        ScmZsjProduct scmZsjProduct = scmZsjProductDao.get(id);
        if (scmZsjProduct == null) {
            throw new RuntimeException("商品不存在");
        }
//        ScmZsjAchage achage = scmZsjAchageDao.getByCode(scmZsjAchage.getCode());
//        if (achage != null) {
//            throw new RuntimeException("件装量编号重复，添加失败");
//        }
        //生成件装量code
        List<ScmZsjAchage> byMainId = scmZsjAchageDao.getAllByMainId(scmZsjAchage.getProductId());
        int a = byMainId.size() + 1;
        if (a < 10) {
            scmZsjAchage.setCode(scmZsjProduct.getProductCode() + "0" + a);
        } else {
            scmZsjAchage.setCode(scmZsjProduct.getProductCode() + a + "");
        }
        scmZsjAchage.setIsDelete("1");
        scmZsjAchage.setProductCode(scmZsjProduct.getProductCode());
        List<ScmZsjAchage> scmZsjAchage1 = scmZsjAchageDao.getByachage(scmZsjAchage.getAchage(), scmZsjAchage.getProductId());
        if (scmZsjAchage1.size() == 0) {
            super.create(scmZsjAchage);
            //与主数据中心同步
            updateSyn(scmZsjAchage);
        } else {
            throw new RuntimeException("数据重复无法保存");
        }
        List<ScmZsjProductFirst> byProId = scmZsjProductFirstDao.getByProId(scmZsjAchage.getProductId());
        try{
        	scmZsjProductFirstManager.productToWms(byProId);
        }catch (Exception e) {
        	
        }
    }


    @Override
    public void remove(String entityId) {
        // TODO Auto-generated method stub
        ScmZsjAchage scmZsjAchage = scmZsjAchageDao.get(entityId);
        if (scmZsjAchage == null) {
            return;
        }
        scmZsjAchage.setIsDelete("0");
        scmZsjAchageDao.update(scmZsjAchage);
        ////与主数据中心同步
        updateSyn(scmZsjAchage);
    }


    @Override
    public void update(ScmZsjAchage entity) {
        entity.setIsDelete("1");
        ScmZsjAchage achage = scmZsjAchageDao.getByCode(entity.getCode());
        if (achage != null && !achage.getId().equals(entity.getId())) {
            throw new RuntimeException("件装量编号重复，添加失败");
        }
        entity.setProductCode(achage.getProductCode());
        entity.setCode(achage.getCode());
        List<ScmZsjAchage> scmZsjAchage1 = scmZsjAchageDao.getByachage(entity.getAchage(), entity.getProductId());
        if (scmZsjAchage1 != null && scmZsjAchage1.size() > 0 && !scmZsjAchage1.get(0).getProductId().equals(entity.getProductId())) {
            throw new RuntimeException("数重复无法保存");
        }
        super.update(entity);
        //与主数据中心同步
        updateSyn(entity);
    }
    
    public void updateSyn(ScmZsjAchage scmZsjAchage) {
    	String productId = scmZsjAchage.getProductId();
    	ScmZsjProduct arg0 = scmZsjProductDao.get(productId);
    	if(arg0 == null) {
    		return;
    	}
    	List<ScmZsjAchage> byMainId = scmZsjAchageDao.getAllByMainId(productId);
    	List<ProductAchange> achageList = new ArrayList<ProductAchange>();
    	for (ScmZsjAchage scmZsjAchage2 : byMainId) {
    		ProductAchange productAchange = new ProductAchange();
    		productAchange.setAchage(scmZsjAchage2.getAchage());
    		productAchange.setCode(scmZsjAchage2.getCode());
    		if("0".equals(scmZsjAchage2.getIsDelete())) {
    			productAchange.setIsDelete("1");
    		}else{
    			productAchange.setIsDelete("0");
    		}
    		productAchange.setProductCode(scmZsjAchage2.getProductCode());
    		productAchange.setProductNum(scmZsjAchage2.getProductNum());
    		productAchange.setProductId(scmZsjAchage2.getProductId());
    		achageList.add(productAchange);
		}
		ScmZsjProductLine scmZsjProductLine = scmZsjProductLineDao.get(arg0.getProductLineId());
		ProductVo productVo = new ProductVo();
		productVo.setId(arg0.getId());
		productVo.setProdCode(arg0.getProductCode());
		productVo.setProdName(arg0.getProductName());
		productVo.setProdCatCode(scmZsjProductLine.getProductLineCode());
		productVo.setProdGenName(arg0.getCommonName());
		productVo.setProdSize(arg0.getProductState());
		productVo.setProdDosage(arg0.getAgentType());
		productVo.setProdUnit(arg0.getUnit());
		productVo.setOTC(arg0.getOtc());
		productVo.setDrugType(arg0.getMedicineType());
		productVo.setDrugCategory(arg0.getMedicineClassify());
		productVo.setStorageRange(arg0.getKeepingCondition());
		productVo.setProducer(arg0.getManufacturer());
		productVo.setStoreId(arg0.getSuggestWarehouse());
		productVo.setInventoryCode(arg0.getInventoryCode());
		productVo.setDrugTypeId(arg0.getMedicineTypeId());
		productVo.setDrugCategoryId(arg0.getMedicineClassifyId());
		productVo.setProdDosageId(arg0.getAgentType());
		productVo.setIsDelete(0);
		productVo.setStatus(1);
		productVo.setAchangeList(achageList);
		List<ProductVo> list = new ArrayList<ProductVo>();
		list.add(productVo);
		String jsonString = JSON.toJSONString(list);
		Map<String,String> header = new HashMap<String,String>();
		header.put("accept", "application/json;charset=UTF-8");
		header.put("Authorization", ScmWmsConfiguration.MASTER_TOKEN);
		header.put("Content-Type", "application/json");
		String returnStr = HttpClientUtil.doPostJson(ScmWmsConfiguration.MASTER_DATE_URL+ "/masterdata/product/v1/saveList", jsonString,header);
		CommonResult parseObject = JSON.parseObject(returnStr, CommonResult.class);
		String code2 = parseObject.getCode();
		if(!"10000".equals(code2)) {
			arg0.setIsToMasterData("0");
			scmZsjProductDao.update(arg0);
		}
    }

	@Override
	public String getProductNumByCode(String code) {
		ScmZsjAchage byCode = scmZsjAchageDao.getByCode(code);
		return byCode.getProductNum();
	}

	@Override
	public String getProductMessageByCode(String code) {
		ScmZsjProduct scmZsjProduct = scmZsjProductDao.getProductMessageByCode(code);
		String jsonString = JSON.toJSONString(scmZsjProduct);
		return jsonString;
	}
}
