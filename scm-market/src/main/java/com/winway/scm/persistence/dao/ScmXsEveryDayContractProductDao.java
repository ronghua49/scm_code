package com.winway.scm.persistence.dao;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsDailyContractTask;
import com.winway.scm.model.ScmXsEveryDayContractProduct;
import com.winway.scm.vo.ScmXsBigContractProductByUpdateVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * <pre> 
 * 描述：日常合同商品表 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:44
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsEveryDayContractProductDao extends MyBatisDao<String, ScmXsEveryDayContractProduct> {
	/**
	 * 根据外键获取子表明细列表
	 * @param EveryDayContractId
	 * @return
	 */
	 List<ScmXsEveryDayContractProduct> getByMainId(String EveryDayContractId);
	
	/**
	 * 根据外键删除子表记录
	 * @param EveryDayContractId
	 * @return
	 */
	public void delByMainId(String EveryDayContractId);


	List<ScmXsDailyContractTask> getTaskInfoByContrctId(String id);

    List<ScmXsBigContractProductByUpdateVo> getAllProList(@Param(value = "id") String id, @Param(value = "agreementSummaryId") String agreementSummaryId);
}
