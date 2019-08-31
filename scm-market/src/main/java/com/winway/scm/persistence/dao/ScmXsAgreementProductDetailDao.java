package com.winway.scm.persistence.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.ScmXsAgreementProductDetail;
import com.winway.scm.vo.ScmXsBigContractProductByUpdateVo;

/**
 * 
 * <pre> 
 * 描述：协议品规明细 DAO接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-09 09:35:43
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmXsAgreementProductDetailDao extends MyBatisDao<String, ScmXsAgreementProductDetail> {
	/**
	 * 根据外键获取子表明细列表
	 * @param AgreementSummaryId
	 * @return
	 */
    List<ScmXsAgreementProductDetail> getByMainId(String arg0);


	List<ScmXsAgreementProductDetail> getLastByMainId(String arg0);
	
	/**
	 * 根据外键删除子表记录
	 * @param AgreementSummaryId
	 * @return
	 */
    void delByMainId(String arg0);


	void delLastByMainId(String arg0);

	List<ScmXsAgreementProductDetail> getProductDetailByOrovinceId(String arg0, String arg1,
                                                                   String arg2);
	
	List<ScmXsAgreementProductDetail> getProductDetailByOrovinceId1(String arg0, String arg1,
                                                                    String arg2, String arg3);
	
	ScmXsAgreementProductDetail  getProductDetailBycode(String arg0, String arg1,
                                                        String arg2, String arg3, String arg4);
	
	
	List<ScmXsBigContractProductByUpdateVo> listAgrProductByBigUpdate(String id, String agreementSummaryId);

	void createLast(ScmXsAgreementProductDetail scmXsAgreementProductDetail);


}
