package com.winway.scm.persistence.manager.impl;

import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.hotent.base.query.PageBean;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.purchase.util.QuarterUtil;
import com.winway.scm.model.ScmFhShipmentsDatail;
import com.winway.scm.model.ScmFhShipmentsMessage;
import com.winway.scm.model.ScmFhShipmentsTask;
import com.winway.scm.persistence.dao.ScmFhShipmentsDatailDao;
import com.winway.scm.persistence.dao.ScmFhShipmentsMessageDao;
import com.winway.scm.persistence.dao.ScmFhShipmentsTaskDao;
import com.winway.scm.persistence.manager.ScmFhShipmentsDatailManager;
import com.winway.scm.vo.ScmFhShipmentsDataiAndMessageVo;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 描述：发货明细表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhShipmentsDatailManager")
public class ScmFhShipmentsDatailManagerImpl extends AbstractManagerImpl<String, ScmFhShipmentsDatail> implements ScmFhShipmentsDatailManager {
    @Resource
    ScmFhShipmentsDatailDao scmFhShipmentsDatailDao;
    @Resource
    ScmFhShipmentsTaskDao scmFhShipmentsTaskDao;
    @Resource
    ScmFhShipmentsMessageDao scmFhShipmentsMessageDao;

    @Override
    protected MyBatisDao<String, ScmFhShipmentsDatail> getDao() {
        return scmFhShipmentsDatailDao;
    }

    @Override
    public List<Map<String, Object>> getYears(String id) {
        // TODO Auto-generated method stub
        List<Map<String, Object>> list = scmFhShipmentsDatailDao.getYear(id);
        return list;
    }

    public void create(ScmFhShipmentsDatail datail) {
        //根据任务id  和 主表id 查询发货详情
//		ScmFhShipmentsDatail sfsd = scmFhShipmentsDatailDao.getBymainIdAndShipmentsTaskId(datail.getShipmentsTaskId(),datail.getMasterId());
        if(datail==null|| StringUtils.isEmpty(datail.getShipmentsTaskId())||datail.getDeliveringAmount()==null||datail.getPlanShipmentsSum()==null){
            throw  new RuntimeException("必传字段不得为空");
        }
        ScmFhShipmentsTask scmFhShipmentsTask = scmFhShipmentsTaskDao.get(datail.getShipmentsTaskId());
        if (scmFhShipmentsTask == null) {
            throw new RuntimeException("数据传输错误，根据发货详情未查询到发货任务。");
        }

        datail.setType(scmFhShipmentsTask.getType());
        datail.setTaskType(scmFhShipmentsTask.getTaskType());
        datail.setDateCode(scmFhShipmentsTask.getDateCode());
        datail.setPaymentType(scmFhShipmentsTask.getPaymentType());
        datail.setFocusShipmentsMonth(scmFhShipmentsTask.getFocusShipmentsMonth());
        datail.setIsSms(scmFhShipmentsTask.getIsSms());
        datail.setCommerceId(scmFhShipmentsTask.getCommerceId());
        datail.setCommerceName(scmFhShipmentsTask.getCommerceName());
        datail.setProductName(scmFhShipmentsTask.getProductName());
        datail.setMedicineType(scmFhShipmentsTask.getMedicineType());
        datail.setProductState(scmFhShipmentsTask.getProductState());
        datail.setAChange(scmFhShipmentsTask.getAChange());
        datail.setCommercetype(scmFhShipmentsTask.getCommercetype());
        datail.setMarketType(scmFhShipmentsTask.getMarketType());
        datail.setPrice(scmFhShipmentsTask.getPrice());
        datail.setPlanShipmentsSum(scmFhShipmentsTask.getPlanShipmentsSum());
        datail.setPhone(scmFhShipmentsTask.getPhone());
        datail.setContactPersion(scmFhShipmentsTask.getContactPersion());
        datail.setPackageSum(scmFhShipmentsTask.getPackageNum());
        datail.setShipmentsAffirmDate(new Date());
        if (datail.getDeliveringAmount() != null && datail.getDeliveringAmount() > 0) {
            //实际量不得大于计划发货量
            if (datail.getDeliveringAmount() > scmFhShipmentsTask.getPlanShipmentsSum()) {
                throw new RuntimeException("实际量不得大于计划量");
            }
            //说明已经有实际的发货量，对实际发货量进行了修改，用数据库中的数量-前端传过来的数量，加入到发货任务中
            if (datail.getDeliveringAmount() < scmFhShipmentsTask.getPlanShipmentsSum()) {
                //修改发货任务状态为剩余
                scmFhShipmentsTask.setType("1");
            } else if (datail.getDeliveringAmount().equals(scmFhShipmentsTask.getPlanShipmentsSum())) {
                //修改发货状态为已完成
                scmFhShipmentsTask.setType("2");
            }
            //修改发货任务计划发货量
            scmFhShipmentsTask.setPlanShipmentsSum(scmFhShipmentsTask.getPlanShipmentsSum() - datail.getDeliveringAmount());
        }
        scmFhShipmentsTaskDao.update(scmFhShipmentsTask);
        super.create(datail);
    }


    public void update(ScmFhShipmentsDatail datail) {
        ScmFhShipmentsDatail scmFhShipmentsDatail = get(datail.getId());
        ScmFhShipmentsTask scmFhShipmentsTask;
        if (scmFhShipmentsDatail != null && (scmFhShipmentsTask = scmFhShipmentsTaskDao.get(scmFhShipmentsDatail.getShipmentsTaskId())) != null) {
            if (datail.getDeliveringAmount() != null && datail.getDeliveringAmount()> 0) {
                //实际量不得大于计划发货量
                if (datail.getDeliveringAmount() > scmFhShipmentsTask.getPlanShipmentsSum()) {
                    throw new RuntimeException("实际量不得大于计划量");
                }
                //说明已经有实际的发货量，对实际发货量进行了修改，用数据库中的数量-前端传过来的数量，加入到发货任务中
                if (datail.getDeliveringAmount().equals(scmFhShipmentsTask.getPlanShipmentsSum())) {
                    //修改发货状态为已完成
                    scmFhShipmentsTask.setType("2");
                }
                //修改发货任务计划发货量
                scmFhShipmentsTask.setPlanShipmentsSum(scmFhShipmentsTask.getPlanShipmentsSum() - (datail.getDeliveringAmount() - scmFhShipmentsDatail.getDeliveringAmount()));
                scmFhShipmentsTaskDao.update(scmFhShipmentsTask);
            }
            super.update(datail);
        } else {
            throw new RuntimeException("数据错误！未查询到需要更新的发货详情数据");
        }


    }

    @Override
    public PageList<ScmFhShipmentsDatail> messageList(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmFhShipmentsDatail> query = scmFhShipmentsDatailDao.messageList(queryFilter.getParams());
        //查询物流承运信息
        for (ScmFhShipmentsDatail scmFhShipmentsDatail : query) {
            List<ScmFhShipmentsMessage> list = scmFhShipmentsMessageDao.getByMainId(scmFhShipmentsDatail.getId());
            scmFhShipmentsDatail.setScmFhShipmentsMessageList(list);
        }
        return new PageList<ScmFhShipmentsDatail>(query);
    }

    @Override
    public PageList<ScmFhShipmentsDataiAndMessageVo> firstList(QueryFilter queryFilter) {
        PageBean pageBean = queryFilter.getPageBean();
        if (BeanUtils.isEmpty(pageBean)) {
            PageHelper.startPage(1, Integer.MAX_VALUE, false);
        } else {
            PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
        }
        List<ScmFhShipmentsDataiAndMessageVo> firstList = scmFhShipmentsDatailDao.firstList(queryFilter.getParams());
        return new PageList<ScmFhShipmentsDataiAndMessageVo>(firstList);
    }

}
