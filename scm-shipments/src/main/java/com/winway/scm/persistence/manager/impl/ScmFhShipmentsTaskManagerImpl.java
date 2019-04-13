package com.winway.scm.persistence.manager.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.google.gson.*;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.query.QueryOP;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.UniqueIdUtil;
import com.winway.scm.model.ScmFhShipmentsTask;
import com.winway.scm.persistence.dao.ScmFhShipmentsTaskDao;
import com.winway.scm.persistence.manager.ScmFhShipmentsControlManager;
import com.winway.scm.persistence.manager.ScmFhShipmentsTaskManager;


/**
 * <pre>
 * 描述：发货任务表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhShipmentsTaskManager")
public class ScmFhShipmentsTaskManagerImpl extends AbstractManagerImpl<String, ScmFhShipmentsTask> implements ScmFhShipmentsTaskManager {
    @Resource
    ScmFhShipmentsTaskDao scmFhShipmentsTaskDao;
    @Resource
    ScmFhShipmentsControlManager scmFhShipmentsControlManager;

    @Override
    protected MyBatisDao<String, ScmFhShipmentsTask> getDao() {
        return scmFhShipmentsTaskDao;
    }

    @Override
    public void theDeliveryTask(String jsonStr) throws ParseException {
        //创建JSON解析器
        JsonParser parser = new JsonParser();
        //解析为数组
        JsonArray array = (JsonArray) parser.parse(jsonStr);
        for (int i = 0; i < array.size(); i++) {
            //创建发货任务对象
            ScmFhShipmentsTask scmFhShipmentsTask = new ScmFhShipmentsTask();
            JsonObject subObject = array.get(i).getAsJsonObject();
            scmFhShipmentsTask.setOwnerId(subObject.get("ownerId").getAsString());
            scmFhShipmentsTask.setType(subObject.get("type").getAsString());
            scmFhShipmentsTask.setTaskType(subObject.get("taskType").getAsString());
            scmFhShipmentsTask.setDateCode(subObject.get("dateCode").getAsString());
            scmFhShipmentsTask.setPaymentType(subObject.get("paymentType").getAsString());
            if(!"null".equals(subObject.get("isSms").toString())){
            	scmFhShipmentsTask.setIsSms(subObject.get("isSms").getAsString());
            }else{
            	scmFhShipmentsTask.setIsSms("0");
            }
            scmFhShipmentsTask.setCommerceName(subObject.get("commerceName").getAsString());
            scmFhShipmentsTask.setCommerceId(subObject.get("commerceId").getAsString());
            scmFhShipmentsTask.setProductName(subObject.get("productName").getAsString());
            scmFhShipmentsTask.setProductNum(subObject.get("productNum").getAsString());
            scmFhShipmentsTask.setMedicineType(subObject.get("MedicineType").getAsString());
            scmFhShipmentsTask.setProductState(subObject.get("productState").getAsString());
            if(!"null".equals(subObject.get("aChange").toString())){
            	scmFhShipmentsTask.setAChange(subObject.get("aChange").getAsString());
            }else{
            	scmFhShipmentsTask.setAChange("0");
            }
            
            if(!"null".equals(subObject.get("packageNum").toString())){
            	scmFhShipmentsTask.setPackageNum(subObject.get("packageNum").getAsInt());
            }else{
            	scmFhShipmentsTask.setPackageNum(0);
            }
            scmFhShipmentsTask.setCommercetype(subObject.get("commercetype").getAsString());
            scmFhShipmentsTask.setMarketType(subObject.get("marketType").getAsString());
            scmFhShipmentsTask.setPrice(subObject.get("price").getAsDouble());
            if(!"null".equals(subObject.get("memo").toString())){
                scmFhShipmentsTask.setMemo(subObject.get("memo").getAsString());
            }else{
                scmFhShipmentsTask.setMemo("");
            }
            scmFhShipmentsTask.setPlanShipmentsSum(subObject.get("planShipmentsSum").getAsInt());
            scmFhShipmentsTask.setContactPersion(subObject.get("contactPersion").getAsString());
            scmFhShipmentsTask.setPhone(subObject.get("phone").getAsString());
            scmFhShipmentsTask.setSumPrice(subObject.get("sumPrice").getAsDouble());
            scmFhShipmentsTask.setFocusShipmentsMonth(subObject.get("focusShipmentsMonth").getAsString());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(!JsonNull.INSTANCE.equals(subObject.get("sendProductDate"))){
            	scmFhShipmentsTask.setSendProductDate(sdf.parse(subObject.get("sendProductDate").getAsString()));
            }else{
            	scmFhShipmentsTask.setSendProductDate(null);
            }
            scmFhShipmentsTask.setTaskCreateDate(sdf.parse(subObject.get("taskCreateDate").getAsString()));
            create(scmFhShipmentsTask);
        }


    }

    @Override
    public PageList<ScmFhShipmentsTask> firstList(QueryFilter queryFilter) throws Exception {
        PageBean pageBean = queryFilter.getPageBean();
        //发货任务完成的不显示
        String commerceId = queryFilter.getParams().get("commerceId").toString();
        if ("".equals(commerceId) || commerceId == null || "null".equals(commerceId)) {
            throw new RuntimeException("商业ID未获取到");
        }
        queryFilter.getParams().remove("commerceId");
        queryFilter.addFilter("type", "2", QueryOP.NOT_EQUAL);
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmFhShipmentsTask> query = scmFhShipmentsTaskDao.query(queryFilter.getParams());

        //判断药品是否禁售或者停售

        //调接口
        Map<String, String> listCommerceBanProduct = scmFhShipmentsControlManager.ListCommerceBanProduct(commerceId);
        Set<String> keySet = listCommerceBanProduct.keySet();
        //用集合中的药品编号和数据库中的对比
        for (ScmFhShipmentsTask scmFhShipmentsTask : query) {
            //遍历获取到的商品列表,把禁止发货信息及状态封装到任务中,
        	if (listCommerceBanProduct != null && !listCommerceBanProduct.isEmpty()) {
                for (String productCode : keySet) {
                    if (productCode.equals(scmFhShipmentsTask.getProductNum())) {
                        //匹配到不可以发货的商品把禁止发货信息及状态添加到列表中
                        if ("0".equals(listCommerceBanProduct.get(productCode))) {
                            scmFhShipmentsTask.setBanDeliveryMessage("发货控制中禁止发货");
                            scmFhShipmentsTask.setBanDelivery("0");
                        }
                    }
                }
        	}
            if (scmFhShipmentsTask.getBanDelivery() == null || "".equals(scmFhShipmentsTask.getBanDelivery()) || "null".equals(scmFhShipmentsTask.getBanDelivery())) {
                scmFhShipmentsTask.setBanDeliveryMessage("可正常发货");
                scmFhShipmentsTask.setBanDelivery("1");
            }
        }
        //如果不为空，说明该药品禁售或者停售
        return new PageList<ScmFhShipmentsTask>(query);
    }

    @Override
    public void create(ScmFhShipmentsTask entity) {
    	entity.setId(UniqueIdUtil.getSuid());
    	scmFhShipmentsTaskDao.create(entity);
    }
    @Override
    public List<Map<String, String>> commerceList(QueryFilter queryFilter) {

        PageBean pageBean = queryFilter.getPageBean();
        if(BeanUtils.isEmpty(pageBean)){
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        }
        else{
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        return scmFhShipmentsTaskDao.commerceList(queryFilter.getParams());
}

    @Override
    public List<String> proList(String ownerId) {
        return scmFhShipmentsTaskDao.proList(ownerId);
    }

    @Override
    public List<String> codeList(String ownerId) {
        return scmFhShipmentsTaskDao.codeList(ownerId);
    }
}
