package com.winway.scm.persistence.manager.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.ScmCwReturnMoney;
import com.winway.scm.persistence.dao.ScmCwReturnMoneyDao;
import com.winway.scm.persistence.manager.ScmCwReturnMoneyManager;
import com.winway.scm.util.POIUtil;

/**
 * 
 * <pre> 
 * 描述：商业回款表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwReturnMoneyManager")
public class ScmCwReturnMoneyManagerImpl extends AbstractManagerImpl<String, ScmCwReturnMoney> implements ScmCwReturnMoneyManager{
	@Resource
	ScmCwReturnMoneyDao scmCwReturnMoneyDao;
	@Override
	protected MyBatisDao<String, ScmCwReturnMoney> getDao() {
		return scmCwReturnMoneyDao;
	}
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	@Override
	public String readExcelFile(MultipartFile file, ScmCwReturnMoney product) {
		String msg="导入失败";
		try {
			String businessDivisionId=null;
			String businessDivision=null;
			String provinceId=null;
			String province=null;
			String commerceName=null;
			String commerceId=null;
			String commerceCode=null;
			//查询商业表信息数据
			String res = scmMasterDateFeignService.findByOwnerId(product.getOwnerId());
			JSONObject data = JSON.parseObject(res);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<String[]> list = POIUtil.readExcel(file);
			for (String[] s : list) {
				product.setRemittanceDate(sdf.parse(s[0]));
				String totle = s[1];
				if(data.getString(totle) != null){
					JSONObject object = JSON.parseObject(data.getString(totle));
					 businessDivisionId=object.getString("businessDivisionId");
					 businessDivision=object.getString("businessDivision");
					//省区Id 
					 provinceId=object.getString("provinceId");
					 province=object.getString("province");
					//商业名称
					 commerceName=object.getString("commerceName");
					 commerceId=object.getString("commerceId");
					 commerceCode=object.getString("commerceCode");
				}else{
					product.setMatchResult("商业名称匹配错误！");
				}
				product.setCommerceName(totle);
				product.setCommerceId(commerceId);
				product.setCommerceCode(commerceCode);
				product.setProvince(province);
				product.setProvinceId(provinceId);
				product.setVerifyType("0");
				product.setMatchCommerceName(commerceName);
				product.setBusinessDivisionId(businessDivisionId);
				product.setRemittanceType(s[2]);
				product.setAcceptIsTrue(s[3]);
				product.setAcceptExpire(sdf.parse(s[4]));
				product.setPaymentType(s[5]);
				product.setPrice(Double.valueOf(s[6]));
				product.setBusinessDivision(businessDivision);//商务分区
				product.setMemo(s[7]);
				product.setProvince(province);//省区
				product.setProvinceId(provinceId);
				product.setIsAffirm("0");
				super.create(product);
				msg="导入成功";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("日期格式错误，请设置文件日期格式(2011-11-11)");
			//e.printStackTrace();
		}
		
		return msg;
	}
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String i="2018/01/22";
		System.out.println(sdf.parse(i));
	}
}
