package com.winway.scm.persistence.manager.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.exception.BaseException;
import com.hotent.base.exception.SystemException;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.StringUtil;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmXsDailyContractTask;
import com.winway.scm.model.ScmXsEveryDayContract;
import com.winway.scm.model.ScmXsEveryDayContractProduct;
import com.winway.scm.persistence.dao.ScmXsEveryDayContractDao;
import com.winway.scm.persistence.dao.ScmXsEveryDayContractProductDao;
import com.winway.scm.persistence.manager.ScmXsEveryDayContractManager;
import com.winway.scm.persistence.manager.ScmXsEveryDayContractProductManager;
import com.winway.scm.vo.ScmXsBigContractProductByUpdateVo;
import org.apache.commons.collections.map.HashedMap;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <pre>
 * 描述：日常合同 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmXsEveryDayContractManager")
public class ScmXsEveryDayContractManagerImpl extends AbstractManagerImpl<String, ScmXsEveryDayContract> implements ScmXsEveryDayContractManager {
    @Resource
    ScmXsEveryDayContractDao scmXsEveryDayContractDao;
    @Resource
    ScmXsEveryDayContractProductManager scmXsEveryDayContractProductManager;
    @Resource
    ScmMasterDateFeignService scmMasterDateFeignService;
    @Resource
    ScmXsEveryDayContractProductDao scmXsEveryDayContractProductDao;

    @Override
    protected MyBatisDao<String, ScmXsEveryDayContract> getDao() {
        return scmXsEveryDayContractDao;
    }


    @Override
    public PageList<ScmXsEveryDayContract> query(QueryFilter queryFilter) throws SystemException {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, 2147483647, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmXsEveryDayContract> query = scmXsEveryDayContractDao.query(queryFilter.getParams());
        for (ScmXsEveryDayContract contract : query) {
            List<ScmXsEveryDayContractProduct> productList = scmXsEveryDayContractProductDao.getByMainId(contract.getId());
            contract.setScmXsEveryDayContractProductList(productList);
        }
        return new PageList(query);
    }

    /**
     * 创建实体包含子表实体
     */
    public void create(ScmXsEveryDayContract scmXsEveryDayContract) {
        scmXsEveryDayContract.setEntryDate(new Date());
        scmXsEveryDayContract.setIsDelete("0");
        super.create(scmXsEveryDayContract);
        List<ScmXsEveryDayContractProduct> scmXsEveryDayContractProductList = scmXsEveryDayContract.getScmXsEveryDayContractProductList();
        for (ScmXsEveryDayContractProduct scmXsEveryDayContractProduct : scmXsEveryDayContractProductList) {
            if (scmXsEveryDayContractProduct.getTheNumber() != null && scmXsEveryDayContractProduct.getTheNumber() > 0) {
                scmXsEveryDayContractProduct.setEveryDayContractId(scmXsEveryDayContract.getId());
                scmXsEveryDayContractProductManager.create(scmXsEveryDayContractProduct);
            }
        }
    }

    /**
     * 非物理删除，改变合同状态删除状态为1。只有未提交状态才可以删除
     */
    public void remove(String entityId) {
        ScmXsEveryDayContract scmXsEveryDayContract = scmXsEveryDayContractDao.get(entityId);
        if (ScmXsEveryDayContract.UNAPPROVALED_STATE.equals(scmXsEveryDayContract.getApprovalState())) {
            scmXsEveryDayContract.setIsDelete(ScmXsEveryDayContract.DEL_STATE);
            scmXsEveryDayContractDao.update(scmXsEveryDayContract);
        } else {
            throw new BaseException("该审批状态下不可删除！");
        }
    }

    /**
     * 批量删除包含子表记录
     */
    public void removeByIds(String[] entityIds) {
        for (String id : entityIds) {
            this.remove(id);
        }
    }

    /**
     * 获取实体
     */
    public ScmXsEveryDayContract get(String entityId) {
        ScmXsEveryDayContract scmXsEveryDayContract = super.get(entityId);
        if (scmXsEveryDayContract != null) {
            List<ScmXsEveryDayContractProduct> scmXsEveryDayContractProductList = scmXsEveryDayContractProductDao.getByMainId(entityId);
            scmXsEveryDayContract.setScmXsEveryDayContractProductList(scmXsEveryDayContractProductList);
        }
        return scmXsEveryDayContract;
    }

    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmXsEveryDayContract scmXsEveryDayContract) {
        super.update(scmXsEveryDayContract);
        String id = scmXsEveryDayContract.getId();
        scmXsEveryDayContractProductDao.delByMainId(id);
        List<ScmXsEveryDayContractProduct> scmXsEveryDayContractProductList = scmXsEveryDayContract.getScmXsEveryDayContractProductList();
        for (ScmXsEveryDayContractProduct scmXsEveryDayContractProduct : scmXsEveryDayContractProductList) {
            if (scmXsEveryDayContractProduct.getTheNumber()!=null&&scmXsEveryDayContractProduct.getTheNumber() > 0) {
                scmXsEveryDayContractProduct.setEveryDayContractId(id);
                scmXsEveryDayContractProductManager.create(scmXsEveryDayContractProduct);
            }
        }
    }

    @Override
    public void sendApply(ScmXsEveryDayContract scmXsEveryDayContract) {
        scmXsEveryDayContract.setApprovalState(ScmXsEveryDayContract.APPROVALING_STATE);
        if (scmXsEveryDayContract.getId() == null || get(scmXsEveryDayContract.getId()) == null) {
            scmXsEveryDayContract.setContractCode(QuarterUtil.getCode("RCHT"));
            create(scmXsEveryDayContract);
        } else if (get(scmXsEveryDayContract.getId()) != null) {
            update(scmXsEveryDayContract);
        }
    }

    @Override
    public String save(ScmXsEveryDayContract scmXsEveryDayContract) {
        String msg = "添加日常合同成功";
        if (StringUtil.isEmpty(scmXsEveryDayContract.getId())) {
            scmXsEveryDayContract.setContractCode(QuarterUtil.getCode("RCHT"));
            create(scmXsEveryDayContract);
        } else {
            update(scmXsEveryDayContract);
            msg = "更新日常合同成功";
        }
        return msg;
    }

    @Override
    public void endApply(JsonNode jsonNode) {

        String approvalId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();
        ScmXsEveryDayContract contract = scmXsEveryDayContractDao.getContractByApprovalId(approvalId);
        if (contract == null) {
            throw new RuntimeException("未查询到审批过日常合同数据,处理异常");
        }
        if ("agree".equals(actionName)) {
            //审批状态调整为通过
            contract.setApprovalState(ScmXsEveryDayContract.APPROVAL_DONE_STATE);
            scmXsEveryDayContractDao.update(contract);
            //获取合同发货商品列表明细
            List<ScmXsDailyContractTask> taskInfo = scmXsEveryDayContractProductDao.getTaskInfoByContrctId(contract.getId());
            //加入发货任务
            List<Map<String, Object>> tasks = new ArrayList<>();
            for (ScmXsDailyContractTask task : taskInfo) {
                Map<String, Object> map = new HashedMap();
                map.put("ownerId", task.getOwnerId());
                map.put("type", "0");
                map.put("taskType", "2");
                map.put("dateCode", task.getContractCode());
                map.put("taskCreateDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                //商品信息
                map.put("isSms", task.getIsSMS());
                map.put("productName", task.getProductName());
                map.put("productNum", task.getProductCode());
                map.put("MedicineType", task.getMedicineType());
                map.put("productState", task.getProductGauge());
                map.put("price", task.getUnitPrice());
                map.put("sumPrice", task.getSumPrice());
                map.put("aChange", task.getAchange());
                map.put("packageNum",task.getPackageNum());
                map.put("memo", contract.getSpecialOpinion());
                map.put("planShipmentsSum", task.getTheNumber());
                //获取客户信息
                map.put("commercetype", task.getCommerceState());
                map.put("marketType", task.getFunctionType());
                map.put("contactPersion", task.getConsignee());
                map.put("phone", task.getPhone());
                map.put("commerceName", task.getCommerceName());
                map.put("commerceId", task.getCommerceFirstId());
                map.put("paymentType", task.getMarketingWay());
                map.put("sendProductDate", task.getSendProductDate());
                map.put("focusShipmentsMonth", "");
                tasks.add(map);
            }
            //调取远程处理发货任务
            scmMasterDateFeignService.theDeliveryTask(new JSONArray(tasks).toString());

        }
    }

    @Override
    public List<ScmXsBigContractProductByUpdateVo> getAllProListByContractId(String id, String agreementSummaryId) {

        //根据当前合同id查询该经销商合同商品和该协议下所有商品
        List<ScmXsBigContractProductByUpdateVo> query = scmXsEveryDayContractProductDao.getAllProList(id, agreementSummaryId);

        return query;
    }


	@Override
	public ScmXsEveryDayContract getByApplyId(String applyId) {
		ScmXsEveryDayContract scmXsEveryDayContract = scmXsEveryDayContractDao.getByApplyId(applyId);
		if(scmXsEveryDayContract == null) {
			throw new RuntimeException("未查询到审批数据");
		}
		ScmXsEveryDayContract scmXsEveryDayContract2 = get(scmXsEveryDayContract.getId());
		return scmXsEveryDayContract2;
	}

}
