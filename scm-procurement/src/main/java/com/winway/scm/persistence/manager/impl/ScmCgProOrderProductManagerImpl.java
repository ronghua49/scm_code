package com.winway.scm.persistence.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.util.StringUtil;
import com.winway.scm.model.ScmCgContractProduct;
import com.winway.scm.model.ScmCgProOrderProduct;
import com.winway.scm.model.ScmCgProcurementOrder;
import com.winway.scm.persistence.dao.ScmCgContractProductDao;
import com.winway.scm.persistence.dao.ScmCgProOrderProductDao;
import com.winway.scm.persistence.manager.ScmCgContractProductManager;
import com.winway.scm.persistence.manager.ScmCgProOrderProductManager;
import com.winway.scm.persistence.manager.ScmCgProcurementOrderManager;

/**
 * <pre>
 * 描述：采购订单商品表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgProOrderProductManager")
public class ScmCgProOrderProductManagerImpl extends AbstractManagerImpl<String, ScmCgProOrderProduct> implements ScmCgProOrderProductManager {
    @Resource
    ScmCgProOrderProductDao scmCgProOrderProductDao;
    @Resource
    ScmCgContractProductDao scmCgContractProductDao;
    @Resource
    ScmCgProcurementOrderManager scmCgProcurementOrderManager;
    @Resource
    ScmCgContractProductManager scmCgContractProductManager;

    @Override
    protected MyBatisDao<String, ScmCgProOrderProduct> getDao() {
        return scmCgProOrderProductDao;
    }

    @Override
    public void update(ScmCgProOrderProduct arg0) {
        ScmCgProOrderProduct scmCgProOrderProduct = scmCgProOrderProductDao.get(arg0.getId());
        if (scmCgProOrderProduct == null) {
            throw new RuntimeException("当前商品不存在或已经被删除");
        }
        scmCgProOrderProduct.setProductSum(arg0.getProductSum());
        scmCgProOrderProduct.setSumPrice(scmCgProOrderProduct.getProductSum() * scmCgProOrderProduct.getUnitPrice());
        super.update(scmCgProOrderProduct);
    }

    @Override
    public void remove(String entityId) {
        ScmCgProOrderProduct scmCgProOrderProduct = scmCgProOrderProductDao.get(entityId);
        ScmCgContractProduct scmCgContractProduct = scmCgContractProductDao.get(scmCgProOrderProduct.getContractProductId());
        scmCgContractProduct.setSurplusAmount(scmCgContractProduct.getSurplusAmount() + scmCgProOrderProduct.getProductSum());
        scmCgContractProductDao.update(scmCgContractProduct);
        super.remove(entityId);
    }

    @Override
    public void listProductByOrderId(ScmCgProOrderProduct scmCgProOrderProduct) {
        List<ScmCgProOrderProduct> scmCgProOrderProducts = scmCgProOrderProductDao.listProductByOrderId(scmCgProOrderProduct);
        if (scmCgProOrderProducts.size() > 0) {
            throw new RuntimeException("当前订单已经存在该商品,请在列表中编辑");
        }
    }

    @Override
    public List<ScmCgProOrderProduct> getByMainId(String id) {
        List<ScmCgProOrderProduct> byMainId = scmCgProOrderProductDao.getByMainId(id);
        for (ScmCgProOrderProduct product : byMainId) {
            ScmCgContractProduct scmCgContractProduct = scmCgContractProductDao.get(product.getContractProductId());
            if (scmCgContractProduct != null) {
                product.setSurplusAmount(scmCgContractProduct.getSurplusAmount());
            } else {
                product.setSurplusAmount(0);
            }
        }
        return byMainId;
    }

    @Override
    public void updatebyParam(String procurementCode, String productCode, String rejectSum) {
        List<ScmCgProOrderProduct> orderProduct = scmCgProOrderProductDao.getByOrderCodeAndProCode(procurementCode, productCode);
        if (orderProduct == null||orderProduct.isEmpty()) {
            throw new RuntimeException("数据错误，没有查询到该订单的商品");
        }
        for(ScmCgProOrderProduct product:orderProduct){

            product.setRejectionTotal(product.getRejectionTotal() + Integer.parseInt(rejectSum));
            if (product.getRejectionTotal() < 0) {
                throw new RuntimeException("数据错误");
            }
            scmCgProOrderProductDao.update(product);
        }
    }

    @Override
    public ScmCgProOrderProduct getbyParam(String procurementCode, String productCode) {
        List<ScmCgProOrderProduct> byOrderCodeAndProCode = scmCgProOrderProductDao.getByOrderCodeAndProCode(procurementCode, productCode);
        if(byOrderCodeAndProCode==null||byOrderCodeAndProCode.isEmpty()){
            return  null;
        }else{
            return byOrderCodeAndProCode.get(0);
        }
    }

    @Override
    public void create(ScmCgProOrderProduct scmCgProOrderProduct) {
        ScmCgProcurementOrder scmCgProcurementOrder = scmCgProcurementOrderManager.get(scmCgProOrderProduct.getProOrderId());
        if (scmCgProcurementOrder == null) {
            throw new RuntimeException("当前订单不存在或已经被删除");
        } else {
            if ("1".equals(scmCgProcurementOrder.getApprovalState()) || "2".equals(scmCgProcurementOrder.getApprovalState())) {
                throw new RuntimeException("当前订单已提交审批或已通过审批,不可修改数据");
            }
        }
        scmCgProOrderProduct.setProcurementCode(scmCgProcurementOrder.getProcurementCode());
        ScmCgContractProduct scmCgContractProduct = scmCgContractProductManager.get(scmCgProOrderProduct.getContractProductId());
        if (scmCgProOrderProduct.getProductSum() == null || scmCgProOrderProduct.getProductSum() <= 0) {
            throw new RuntimeException("盒数不得为空");
        }
        if (StringUtil.isEmpty(scmCgProOrderProduct.getId())) {
            //验证是否已经存在
            listProductByOrderId(scmCgProOrderProduct);
            //验证数量
            if (scmCgContractProduct.getSurplusAmount() - scmCgProOrderProduct.getProductSum() >= 0) {
             
                    super.create(scmCgProOrderProduct);
           
                scmCgContractProduct.setSurplusAmount(scmCgContractProduct.getSurplusAmount() - scmCgProOrderProduct.getProductSum());
                scmCgContractProductDao.update(scmCgContractProduct);
            } else {
                throw new RuntimeException("数量已超出余量,请调整");
            }
        } else {
            //验证数量
            ScmCgProOrderProduct scmCgProOrderProduct2 = get(scmCgProOrderProduct.getId());
            if (scmCgContractProduct.getSurplusAmount() + scmCgProOrderProduct2.getProductSum() - scmCgProOrderProduct.getProductSum() >= 0) {
                update(scmCgProOrderProduct);
                scmCgContractProduct.setSurplusAmount(scmCgContractProduct.getSurplusAmount() + scmCgProOrderProduct2.getProductSum() - scmCgProOrderProduct.getProductSum());
                scmCgContractProductDao.update(scmCgContractProduct);
            } else {
                throw new RuntimeException("数量已超出余量,请调整");
            }
        }
        scmCgProcurementOrderManager.update(scmCgProcurementOrder);
    }
}
