package com.winway.scm.persistence.manager.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.hotent.base.query.*;
import com.winway.purchase.util.ExcelExportUtil;
import com.winway.scm.model.ScmCwBAStorageDiscount;
import com.winway.scm.persistence.dao.ScmCwBAStorageDiscountDao;
import com.winway.scm.persistence.manager.ScmCwBAStorageDiscountManager;
import com.winway.scm.vo.ScmCwBAPaymentDiscountVo;
import com.winway.scm.vo.ScmCwBAStorageDiscountVo;
import com.winway.scm.vo.VInvoice;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.util.BeanUtils;
import com.winway.scm.model.ScmCwBAPaymentDiscount;
import com.winway.scm.persistence.dao.ScmCwBAPaymentDiscountDao;
import com.winway.scm.persistence.manager.ScmCwBAPaymentDiscountManager;

/**
 * <pre>
 * 描述：经销商RB核算付款折扣 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-05-09 18:18:05
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwBAPaymentDiscountManager")
public class ScmCwBAPaymentDiscountManagerImpl extends AbstractManagerImpl<String, ScmCwBAPaymentDiscount> implements ScmCwBAPaymentDiscountManager {
    @Resource
    ScmCwBAPaymentDiscountDao scmCwBAPaymentDiscountDao;

    @Resource
    ScmCwBAStorageDiscountDao scmCwBAStorageDiscountDao;


    @Override
    protected MyBatisDao<String, ScmCwBAPaymentDiscount> getDao() {
        return scmCwBAPaymentDiscountDao;
    }

    @Override
    public PageList<ScmCwBAPaymentDiscount> affirmList(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmCwBAPaymentDiscount> query = scmCwBAPaymentDiscountDao.affirmList(queryFilter.getParams());
        return new PageList<ScmCwBAPaymentDiscount>(query);
    }

    @Override
    public void exportCheckSum(QueryFilter queryFilter, HttpServletResponse response) {
        List<List<String>> data1 = new ArrayList<List<String>>();
        String[] tableName1 = {"流程状态", "年度", "季度", "商务分区", "省区", "商业编码", "商业名称",
                "商业级别", "回款总金额", "计算RB汇总", "应发RB汇总"};
        // 导出全部
        PageHelper.startPage(1, Integer.MAX_VALUE, false);
        List<ScmCwBAPaymentDiscountVo> query = scmCwBAPaymentDiscountDao.affirmListSum(queryFilter.getParams());
        List<ScmCwBAPaymentDiscountVo> rows = query;
        for (int i = 0; i < rows.size(); i++) {
            List<String> br = new ArrayList<>();
            ScmCwBAPaymentDiscountVo discountVo = rows.get(i);
            String approvalState = discountVo.getApprovalState();
            if (approvalState == null) {
                br.add("未提交");
            } else {
                switch (approvalState) {
                    case "0":
                        br.add("未提交");
                        break;
                    case "1":
                        br.add("审批中");
                        break;
                    case "2":
                        br.add("已通过");
                        break;
                    case "3":
                        br.add("已驳回");
                        break;
                }
            }
            br.add(discountVo.getYear());
            br.add(discountVo.getQuarter());
            br.add(discountVo.getBusinessDivision());
            br.add(discountVo.getProvince());
            br.add(discountVo.getCommerceCode());
            br.add(discountVo.getCommerceName());
            br.add(discountVo.getCommerceLevel());
            br.add(discountVo.getPrice() == null ? "" : discountVo.getPrice().toString());
            br.add(discountVo.getThisCalculationRb() == null ? "" : discountVo.getThisCalculationRb().toString());
            br.add(discountVo.getThisShouldSendRb() == null ? "" : discountVo.getThisShouldSendRb().toString());
            data1.add(br);
        }
        List<List<String>> data2 = new ArrayList<List<String>>();
        String[] tableName2 = {"流程状态", "年度", "季度", "商务分区", "省区", "商业编码", "商业名称",
                "商品总数量", "商品总金额", "计算RB汇总"};
        // 导出全部
        PageHelper.startPage(1, Integer.MAX_VALUE, false);
        List<ScmCwBAStorageDiscountVo> query2 = scmCwBAStorageDiscountDao.affirmListSum(queryFilter.getParams());
        List<ScmCwBAStorageDiscountVo> rows2 = query2;
        for (int i = 0; i < rows2.size(); i++) {
            List<String> br = new ArrayList<>();
            ScmCwBAStorageDiscountVo discountVo = rows2.get(i);
            String approvalState = discountVo.getApprovalState();
            if (approvalState == null) {
                br.add("未提交");
            } else {
                switch (approvalState) {
                    case "0":
                        br.add("未提交");
                        break;
                    case "1":
                        br.add("审批中");
                        break;
                    case "2":
                        br.add("已通过");
                        break;
                    case "3":
                        br.add("已驳回");
                        break;
                }
            }
            br.add(discountVo.getYear());
            br.add(discountVo.getQuarter());
            br.add(discountVo.getBusinessDivision());
            br.add(discountVo.getProvince());
            br.add(discountVo.getCommerceCode());
            br.add(discountVo.getCommerceName());
            br.add(discountVo.getProductNum() == null ? "" : discountVo.getProductNum().toString());
            br.add(discountVo.getPrice() == null ? "" : discountVo.getPrice().toString());
            br.add(discountVo.getThisCalculationRb() == null ? "" : discountVo.getThisCalculationRb().toString());
            data2.add(br);
        }
        List<List<List<String>>> datas = new ArrayList<>();
        datas.add(data1);
        datas.add(data2);
        List<String[]> tableNames = new ArrayList<>();
        tableNames.add(tableName1);
        tableNames.add(tableName2);
        ExcelExportUtil.downloadSheets(response, new int[]{0, 1}, "经销商rb核算确认", new String[]{"付款折扣", "储运管理折扣"}, tableNames, datas);
    }
}
