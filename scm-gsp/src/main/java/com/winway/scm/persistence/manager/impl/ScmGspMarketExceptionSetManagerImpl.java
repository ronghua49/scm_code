package com.winway.scm.persistence.manager.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.scm.persistence.dao.ScmGspMarketExceptionSetDao;
import com.winway.scm.model.ScmGspMarketExceptionSet;
import com.winway.scm.persistence.manager.ScmGspMarketExceptionSetManager;
import com.winway.scm.vo.ScmGspMarketExceptionMonitorListVo;

/**
 * 
 * <pre> 
 * 描述：销售异动商品设置表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-04-24 12:00:21
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmGspMarketExceptionSetManager")
public class ScmGspMarketExceptionSetManagerImpl extends AbstractManagerImpl<String, ScmGspMarketExceptionSet> implements ScmGspMarketExceptionSetManager{
	@Resource
	ScmGspMarketExceptionSetDao scmGspMarketExceptionSetDao;
	@Override
	protected MyBatisDao<String, ScmGspMarketExceptionSet> getDao() {
		return scmGspMarketExceptionSetDao;
	}
	@Override
	public List<ScmGspMarketExceptionMonitorListVo> monitorList(String ownerId) {
		Calendar cale = null;  
        cale = Calendar.getInstance();  
        int year = cale.get(Calendar.YEAR);  
        int month = cale.get(Calendar.MONTH) + 1;
        String oneMonth = "";
        String towMonth = "";
		//获取当前月
        if(month == 1) {
        	//当前月是1月
        	oneMonth = year + "-01";
        	towMonth = (year - 1) + "-12";
        }else{
        	 if(month < 10) {
             	oneMonth = year + "-0" + month;
             	//获取上个月
             	towMonth = year + "-0" + (month-1);
             }else{
            	 if(month == 10) {
            		 oneMonth = year + "-" + month;
            		 //获取上个月
            		 towMonth = year + "-09";
            	 }
             	oneMonth = year + "-" + month;
             	//获取上个月
             	towMonth = year + "-" + (month-1);
             }
        }
       
		List<ScmGspMarketExceptionMonitorListVo> list = scmGspMarketExceptionSetDao.monitorList(oneMonth,towMonth,ownerId);
		return list;
	}
	
}
