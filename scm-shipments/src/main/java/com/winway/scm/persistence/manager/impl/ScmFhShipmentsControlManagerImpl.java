package com.winway.scm.persistence.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.ScmFhShipmentsControl;
import com.winway.scm.model.ScmFhShipmentsControlLog;
import com.winway.scm.persistence.dao.ScmFhShipmentsControlDao;
import com.winway.scm.persistence.manager.ScmFhShipmentsControlLogManager;
import com.winway.scm.persistence.manager.ScmFhShipmentsControlManager;

/**
 * <pre>
 * 描述：发货控制 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhShipmentsControlManager")
public class ScmFhShipmentsControlManagerImpl extends AbstractManagerImpl<String, ScmFhShipmentsControl> implements ScmFhShipmentsControlManager {
    @Resource
    ScmFhShipmentsControlDao scmFhShipmentsControlDao;
    @Resource
    ScmFhShipmentsControlLogManager scmFhShipmentsControlLogManager;
    @Resource
    ScmMasterDateFeignService scmMasterDateFeignService;

    @Resource
    UCFeignService ucFeignService;

    @Override
    protected MyBatisDao<String, ScmFhShipmentsControl> getDao() {
        return scmFhShipmentsControlDao;
    }

    @Override
    public void create(ScmFhShipmentsControl arg0) {
        // TODO Auto-generated method stub
        super.create(arg0);
        //添加日志
        ScmFhShipmentsControlLog scmFhShipmentsControlLog = new ScmFhShipmentsControlLog();
        scmFhShipmentsControlLog.setOperatorName(arg0.getCreateName());
        scmFhShipmentsControlLog.setOperatorDate(arg0.getCreateDate());
        scmFhShipmentsControlLog.setOwnerId(arg0.getOwnerId());
        scmFhShipmentsControlLog.setOperatormemo("添加禁止发货" + arg0.getProductLineName() + arg0.getProductNum() + "," + arg0.getProductName() + "的药品");
        scmFhShipmentsControlLogManager.create(scmFhShipmentsControlLog);
    }

    @Override
    public void update(ScmFhShipmentsControl arg0) {
        // TODO Auto-generated method stub
        ScmFhShipmentsControl scmFhShipmentsControl = scmFhShipmentsControlDao.get(arg0.getId());
        if (scmFhShipmentsControl == null) {
            arg0.setId(null);
            create(arg0);
        }
        super.update(arg0);
        //添加日志
        ScmFhShipmentsControlLog scmFhShipmentsControlLog = new ScmFhShipmentsControlLog();
        scmFhShipmentsControlLog.setOperatorName(arg0.getCreateName());
        scmFhShipmentsControlLog.setOperatorDate(arg0.getCreateDate());
        scmFhShipmentsControlLog.setOwnerId(arg0.getOwnerId());
        scmFhShipmentsControlLog.setOperatormemo("修改禁止发货" + arg0.getProductLineName() + arg0.getProductNum() + "," + arg0.getProductName() + "的药品");
        scmFhShipmentsControlLogManager.create(scmFhShipmentsControlLog);
    }

    @Override
    public void remove(String entityId, String fullname) {
        // TODO Auto-generated method stub
        ScmFhShipmentsControl scmFhShipmentsControl = scmFhShipmentsControlDao.get(entityId);
        ScmFhShipmentsControlLog scmFhShipmentsControlLog = new ScmFhShipmentsControlLog();
        scmFhShipmentsControlLog.setOperatorName(fullname);
        scmFhShipmentsControlLog.setOperatorDate(new Date());
        scmFhShipmentsControlLog.setOperatormemo("解除禁止发货" + scmFhShipmentsControl.getProductLineName() + scmFhShipmentsControl.getProductNum() + "," + scmFhShipmentsControl.getProductName() + "的药品");
        scmFhShipmentsControlLogManager.create(scmFhShipmentsControlLog);
        super.remove(entityId);
    }

    @Override
    public Map<String, String> ListCommerceBanProduct(String commerceId) throws Exception {
        //commerceId 此刻为commerceFirstId   根据首营id 获取企业所在区域  businessDivisionId  provinceId
    	Map<String, String> areaMap = scmFhShipmentsControlDao.getCurrentArea(commerceId);
        String businessDivisionId = "-1";
        String provinceId ="-1";
        Map<String, String> map = new HashMap<String, String>();
        if(areaMap != null) {
	        if(areaMap.get("businessDivisionId") == null) {
	        	 businessDivisionId = "-1";
	        }else{
	        	 businessDivisionId = areaMap.get("businessDivisionId");
	        }
	        if(areaMap.get("provinceId") == null) {
	        	 provinceId ="-1";
	        }else{
	        	 provinceId = areaMap.get("provinceId");
	        }
    		map.put("allBan", "0");
        }else{
        	map.put("allBan", "1");
        	return map;

        }
//        for (Map.Entry<String, String> area : areaMap.entrySet()) {
//            businessDivisionId = area.getKey();
//            provinceId = area.getValue();
//            break;
//        }
        //查询禁止发货的全部列表,商务分区,省区,经销商为空的
        List<ScmFhShipmentsControl> scmFhShipmentsControls = scmFhShipmentsControlDao.ListShipmentsByAll();
        //查询该商务大区下的禁止发货列表, 商务分区不为空,省区,经销商为空
        List<ScmFhShipmentsControl> scmFhShipmentsControls1 = scmFhShipmentsControlDao.ListShipmentsByBusinessDivisionId(businessDivisionId);
        //查询省区下禁止发货列表,商务分区,省区不为空,经销商为空
        List<ScmFhShipmentsControl> scmFhShipmentsControls2 = scmFhShipmentsControlDao.ListShipmentsByProvinceId(businessDivisionId, provinceId);
        //查询经销商禁止发货列表,商务分区,省区,经销商不为空
        List<ScmFhShipmentsControl> scmFhShipmentsControls3 = scmFhShipmentsControlDao.ListShipmentsByCommerceId(businessDivisionId, provinceId, commerceId);

        for (ScmFhShipmentsControl scmFhShipmentsControl : scmFhShipmentsControls) {
            map.put(scmFhShipmentsControl.getProductNum(), "0");
        }
        for (ScmFhShipmentsControl scmFhShipmentsControl : scmFhShipmentsControls1) {
            map.put(scmFhShipmentsControl.getProductNum(), "0");
        }
        for (ScmFhShipmentsControl scmFhShipmentsControl : scmFhShipmentsControls2) {
            map.put(scmFhShipmentsControl.getProductNum(), "0");
        }
        for (ScmFhShipmentsControl scmFhShipmentsControl : scmFhShipmentsControls3) {
            map.put(scmFhShipmentsControl.getProductNum(), "0");
        }
        return map;
    }
}
