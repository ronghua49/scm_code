package com.winway.scm.persistence.manager.impl;

import javax.annotation.Resource;

import com.winway.purchase.util.POIUtil;
import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmKcInTransitProductDao;
import com.winway.scm.model.ScmKcInTransitProduct;
import com.winway.scm.persistence.manager.ScmKcInTransitProductManager;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 描述：在途商品 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-08-17 14:17:53
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmKcInTransitProductManager")
public class ScmKcInTransitProductManagerImpl extends AbstractManagerImpl<String, ScmKcInTransitProduct> implements ScmKcInTransitProductManager {
    @Resource
    ScmKcInTransitProductDao scmKcInTransitProductDao;

    @Override
    protected MyBatisDao<String, ScmKcInTransitProduct> getDao() {
        return scmKcInTransitProductDao;
    }

    @Override
    public void leadExcel(MultipartFile file, String fullname) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            List<String[]> data = POIUtil.readExcel(file);
            data.forEach(s -> {
                ScmKcInTransitProduct inTransitProduct = new ScmKcInTransitProduct();
                inTransitProduct.setCreatePersion(fullname);
                inTransitProduct.setCreateDate(new Date());
                inTransitProduct.setIsDelete("0");
                inTransitProduct.setTaskId(s[0]);
                if (s[1].contains("/")) {
                    s[1] = s[1].replaceAll("/", "-");
                }
                if (s[2].contains("/")) {
                    s[2] = s[2].replaceAll("/", "-");
                }
                try {
                    inTransitProduct.setEtd(format.parse(s[1]));
                    inTransitProduct.setEta(format.parse(s[2]));
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException("解析日期格式错误");
                }
                inTransitProduct.setCustomsLiquidationCompany(s[3]);
                inTransitProduct.setProductNum(s[4]);
                inTransitProduct.setProductName(s[5]);
                inTransitProduct.setProductState(s[6]);
                inTransitProduct.setBatchNumber(s[7]);
                inTransitProduct.setPackageSum(Double.valueOf(s[8]));
                inTransitProduct.setFinishedSum(Double.valueOf(s[9]));
                inTransitProduct.setArkNumber(Double.valueOf(s[10]));
                inTransitProduct.setProductLicence(s[11]);
                super.create(inTransitProduct);
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取文件失败");
        }
    }
}
