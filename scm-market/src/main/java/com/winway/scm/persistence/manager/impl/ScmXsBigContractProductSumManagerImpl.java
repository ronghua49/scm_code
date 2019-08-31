package com.winway.scm.persistence.manager.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.hotent.base.query.*;
import com.winway.purchase.util.DateFormatter;
import com.winway.scm.model.ScmXsBigContract;
import com.winway.scm.persistence.dao.ScmXsBigContractDao;
import com.winway.scm.persistence.dao.ScmXsBigContractProductDao;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.UniqueIdUtil;
import com.winway.scm.persistence.dao.ScmXsBigContractProductSumDao;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.ScmXsBigContractProduct;
import com.winway.scm.model.ScmXsBigContractProductSum;
import com.winway.scm.persistence.manager.ScmXsBigContractProductSumManager;

/**
 * <pre>
 * 描述：scm_xs_bigcontractproductsum 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-14 18:06:05
 * 版权：美达项目组
 * </pre>
 */
@Service("scmXsBigContractProductSumManager")
@EnableScheduling
public class ScmXsBigContractProductSumManagerImpl extends AbstractManagerImpl<String, ScmXsBigContractProductSum> implements ScmXsBigContractProductSumManager {
    @Resource
    ScmXsBigContractProductSumDao scmXsBigContractProductSumDao;

    @Resource
    ScmXsBigContractProductDao scmXsBigContractProductDao;
    @Resource
    ScmXsBigContractDao bigContractDao;

    @Resource
    ScmMasterDateFeignService scmMasterDateFeignService;

    @Override
    protected MyBatisDao<String, ScmXsBigContractProductSum> getDao() {
        return scmXsBigContractProductSumDao;
    }

    @Override
    public PageList<ScmXsBigContractProductSum> listConProductSumBySummaryID(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        queryFilter.getParams().remove("commerceFirstId");
        List<FieldSort> sorter = queryFilter.getSorter();
        List<QueryField> querys = queryFilter.getQuerys();
    	for (int i = 0; i < querys.size(); i++) {
			QueryField queryField = querys.get(i);
			if("commerceFirstId".equals(queryField.getProperty())) {
				querys.remove(i);
			}
		}
         QueryFilter build = QueryFilter.build();
         build.setQuerys(querys);
         build.setSorter(sorter);
//        bigContractProductSumRefresh(ownerId, agreementSummaryId, commerceFirstId);
        List<ScmXsBigContractProductSum> listConProductSumBySummaryID = scmXsBigContractProductSumDao.listConProductSumBySummaryID(build.getParams());
        return new PageList<ScmXsBigContractProductSum>(listConProductSumBySummaryID);
    }

    @Override
    public List<ScmXsBigContractProductSum> listConProductSumByUpdate(String ownerId, String bigContractAllotId, String agreementSummaryId, String medicineType, String commerceFirstId) {
//        bigContractProductSumRefresh(ownerId, agreementSummaryId, commerceFirstId);
        List<ScmXsBigContractProductSum> listConProductSumBySummaryID = scmXsBigContractProductSumDao.listConProductSumByUpdate(bigContractAllotId, agreementSummaryId, medicineType);
        return listConProductSumBySummaryID;
    }

    @Override
    public void create(ScmXsBigContractProductSum entity) {
        entity.setId(UniqueIdUtil.getSuid());
        scmXsBigContractProductSumDao.create(entity);
    }


    /**
     * 大合同三个月失效，更新大合同汇总商品剩余可发货商品数量
     *
     * @param ownerId
     * @param dealerClauseId
     */
    private void bigContractProductSumRefresh(String ownerId, String dealerClauseId, String commerceFirstId) {
        // 获取之前判断该商业下所有大合同是否失效
        List<ScmXsBigContract> bigContractList = bigContractDao.getByOwnerIdAndClauseId(ownerId, dealerClauseId, commerceFirstId);
        for (ScmXsBigContract bigContract : bigContractList) {
            Date dateOfSigning = bigContract.getDateOfSigning();
            boolean b = DateFormatter.expiredMonth(dateOfSigning, new Date(), 3);
            List<ScmXsBigContractProduct> scmXsBigContractProductList = bigContract.getScmXsBigContractProductList();
            for (ScmXsBigContractProduct product : scmXsBigContractProductList) {
                // 如果经历了三个月，则失效
                if (b) {
                    List<ScmXsBigContractProductSum> productSumList = scmXsBigContractProductSumDao.getThisContractProduct(ownerId, dealerClauseId, commerceFirstId, product.getProductCode());
                    // 减去该合同失效商品的数量
                    for (ScmXsBigContractProductSum productSum : productSumList) {
                        Integer surplusSUm = productSum.getSurplusSUm();
                        productSum.setSurplusSUm(surplusSUm - product.getTheNumber());
                        scmXsBigContractProductSumDao.update(productSum);
                    }
                }
            }
        }
    }

    // 每天凌晨计算失效大合同
    @Override
//    @Scheduled(cron = "0 0 0 * * ?")
    public void bigContractProductInvalid() {
        // 获取所有大合同
        List<ScmXsBigContract> bigContractList = bigContractDao.getAll();
        for (ScmXsBigContract bigContract : bigContractList) {
            Date dateOfSigning = bigContract.getDateOfSigning();
            boolean b = DateFormatter.expiredMonth(dateOfSigning, new Date(), 3);
            List<ScmXsBigContractProduct> scmXsBigContractProductList = bigContract.getScmXsBigContractProductList();
            for (ScmXsBigContractProduct product : scmXsBigContractProductList) {
                // 对于未失效的合同商品判断
                if (!"1".equals(product.getInvalid())) {
                    // 如果经历了三个月，则失效
                    if (b) {
                        product.setInvalid("1");
                        scmXsBigContractProductDao.update(product);
                        List<ScmXsBigContractProductSum> productSumList = scmXsBigContractProductSumDao.getThisContractProduct(bigContract.getOwnerId(), bigContract.getDealerClauseId(), bigContract.getCommerceFirstId(), product.getProductCode());
                        // 统计有效的汇总商品数量
                        for (ScmXsBigContractProductSum productSum : productSumList) {
//                            Integer surplusSUm = productSum.getSurplusSUm();
//                            productSum.setSurplusSUm(surplusSUm - product.getTheNumber());
//                            scmXsBigContractProductSumDao.update(productSum);
                        }
                    }
                }
            }
        }
    }
}
