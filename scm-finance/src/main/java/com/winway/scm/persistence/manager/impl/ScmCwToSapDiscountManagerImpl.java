package com.winway.scm.persistence.manager.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.impl.httpclient3.HttpTransportPropertiesImpl;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.tool.ScmWmsConfiguration;
import com.winway.scm.model.ScmCwToSapDiscount;
import com.winway.scm.persistence.dao.ScmCwToSapDiscountDao;
import com.winway.scm.persistence.manager.ScmCwToSapDiscountManager;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.Bapiret2;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.Char10;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.Char18;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.Char20;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.Char200;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.Char3;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.Char35;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.Char4;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.Curr112;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.Curr132;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.Date;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.Numeric6;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.Quantum133;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.TableOfZsoDetail;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.ZbfInterfaceResponse;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.ZsoDetail;
import com.winway.scm.persistence.manager.impl.ZBF_INTERFACEStub.ZsoHeader;
import com.winway.scm.vo.SO_DETIAL;
import com.winway.scm.vo.SO_HEADER;

/**
 * 
 * <pre> 
 * 描述：推送sap开票确认表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-05 23:55:03
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmCwToSapDiscountManager")
public class ScmCwToSapDiscountManagerImpl extends AbstractManagerImpl<String, ScmCwToSapDiscount> implements ScmCwToSapDiscountManager{
	@Resource
	ScmCwToSapDiscountDao scmCwToSapDiscountDao;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	@Override
	protected MyBatisDao<String, ScmCwToSapDiscount> getDao() {
		return scmCwToSapDiscountDao;
	}
	
	@Override
	public ScmCwToSapDiscount makeInvoice(String id) {
		ScmCwToSapDiscount scmCwToSapDiscount = scmCwToSapDiscountDao.get(id);
		if(scmCwToSapDiscount == null) {
			scmCwToSapDiscount = scmCwToSapDiscountDao.getByShipId(id);
			if(scmCwToSapDiscount == null) {
				throw new RuntimeException("未查询到票折数据");
			}
		}
		if("1".equals(scmCwToSapDiscount.getIsAffirmToSap())) {
			throw new RuntimeException("当前开票数据已经推送至SAP");
		}
		if("2".equals(scmCwToSapDiscount.getIsAffirmToSap())) {
			throw new RuntimeException("当前开票数据已经被取消");
		}
		DecimalFormat df = new DecimalFormat("###.##");
		String discountJson = scmCwToSapDiscount.getDiscountJson();
		SO_HEADER parseObject = JSON.parseObject(discountJson,SO_HEADER.class);
		List<SO_DETIAL> so_DETIAL = parseObject.getSO_DETIAL();
		
		try {
			ZBF_INTERFACEStub service = new ZBF_INTERFACEStub();
    		//认证代码 start.Authenticator.Authenticator HttpTransportProperties
    		HttpTransportPropertiesImpl.Authenticator auth = new HttpTransportPropertiesImpl.Authenticator();
    		auth.setUsername(ScmWmsConfiguration.passparameter.getUserName());
			auth.setPassword(ScmWmsConfiguration.passparameter.getPassword());
			service._getServiceClient().getOptions().setProperty(HTTPConstants.AUTHENTICATE, auth);
			//认证代码 end
//    		TableOfZsoDetail params = new TableOfZsoDetail();
//    		params.setItem();

			//设置SO_HEADER
			ZBF_INTERFACEStub.ZbfInterface ZbfInterface = new ZBF_INTERFACEStub.ZbfInterface();

			ZsoHeader soHeader = new ZsoHeader();

			Char4 char1 = new Char4();
			char1.setChar4("ZORS");
			soHeader.setDocType(char1);

			Char4 char2 = new Char4();
			char2.setChar4("6888");
			soHeader.setSalesOrg(char2);

			Char35 char3 = new Char35();
			char3.setChar35(parseObject.getPURCH_NO_C());
			soHeader.setPurchNoC(char3);

			Char10 char4 = new Char10();
			char4.setChar10(parseObject.getPARTN_NUMB());
			soHeader.setPartnNumb(char4);

			Char3 char5 = new Char3();
			char5.setChar3("Z04");
			soHeader.setOrdReason(char5);

			Char200 char6 = new Char200();
			char6.setChar200(parseObject.getHEAD_TXT());
			soHeader.setHeadTxt(char6);

			Char20 char7 = new Char20();
			char7.setChar20("SCM推送");
			Char20 char8 = new Char20();
			char8.setChar20(parseObject.getPURCH_NO_C());
			soHeader.setTxt1(char8);
			soHeader.setTxt2(char7);
			soHeader.setTxt3(char7);
			soHeader.setTxt4(char7);
			soHeader.setTxt5(char7);

			java.util.Date pdate = parseObject.getPDATE();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String format = sdf.format(pdate);
			Date date = new Date();
			date.setDate(format);
			soHeader.setPdate(date);
			ZbfInterface.setSoHeader(soHeader);


			//设置ITEM
			TableOfZsoDetail soDetail2 = new TableOfZsoDetail();
			ZsoDetail[] item = new ZsoDetail[so_DETIAL.size()];
			for (int i = 0; i < so_DETIAL.size(); i++) {
				ZsoDetail zsoDetail = new ZsoDetail();
				SO_DETIAL so_DETIAL2 = so_DETIAL.get(i);
				int b = i+1;
				Numeric6 numeric6 = new Numeric6();
				numeric6.setNumeric6(b+"");
				zsoDetail.setPosnr(numeric6);
				
				//根据产品子码获取物料编码
				CommonResult<String> productNumByCode = scmMasterDateFeignService.getProductNumByCode(so_DETIAL2.getMATERIAL());
				String productNum = productNumByCode.getShortMessage();
				Char18 char11 = new Char18();
				char11.setChar18(productNum);
				zsoDetail.setMaterial(char11);

				Char10 char19 = new Char10();
				char19.setChar10(so_DETIAL2.getBATCH());
				zsoDetail.setBatch(char19);

				Char4 char12 = new Char4();
				char12.setChar4(so_DETIAL2.getSTORE_LOC());
				zsoDetail.setStoreLoc(char12);

				BigDecimal bd = new BigDecimal(so_DETIAL2.getREQ_QTY());
				Quantum133 char13 = new Quantum133();
				char13.setQuantum133(bd);
				zsoDetail.setReqQty(char13);

				BigDecimal bd1 = new BigDecimal(so_DETIAL2.getPRICE());
				bd1 = bd1.setScale(2,BigDecimal.ROUND_HALF_UP);
				Curr112 char14 = new Curr112();
				char14.setCurr112(bd1);
				zsoDetail.setPrice(char14);

				BigDecimal bd2 = new BigDecimal(so_DETIAL2.getAMT1());
				bd2 = bd2.setScale(2,BigDecimal.ROUND_HALF_UP);
				Curr132 char15 = new Curr132();
				char15.setCurr132(bd2);
				zsoDetail.setAmt1(char15);

				BigDecimal bd3 = new BigDecimal(so_DETIAL2.getAMT2());
				bd3 = bd3.setScale(2,BigDecimal.ROUND_HALF_UP);
				Curr132 char16 = new Curr132();
				char16.setCurr132(bd3);
				zsoDetail.setAmt2(char16);

				BigDecimal bd4 = new BigDecimal(so_DETIAL2.getAMT3());
				bd4 = bd4.setScale(2,BigDecimal.ROUND_HALF_UP);
				Curr132 char17 = new Curr132();
				char17.setCurr132(bd4);
				zsoDetail.setAmt3(char17);

				BigDecimal bd5 = new BigDecimal(0.0);
				bd5 =  bd5.setScale(2,BigDecimal.ROUND_HALF_UP);
				Curr132 char21 = new Curr132();
				char21.setCurr132(bd5);
				zsoDetail.setAmt4(char21);
				zsoDetail.setAmt5(char21);

				Char20 char18 = new Char20();
				char18.setChar20(so_DETIAL2.getTXT1());
				zsoDetail.setTxt1(char18);


				Char20 char20 = new Char20();
				char20.setChar20(so_DETIAL2.getTXT2());
				zsoDetail.setTxt2(char20);

				Char20 char22 = new Char20();
				char22.setChar20("SCM推送");
				zsoDetail.setTxt3(char22);
				zsoDetail.setTxt4(char22);
				zsoDetail.setTxt5(char22);

				item[i] = zsoDetail;
			}
			soDetail2.setItem(item);
			ZbfInterface.setSoDetail(soDetail2);
			//设置ZTYPE
			Char4 ztype = new Char4();
			ztype.setChar4("F001");
			ZbfInterface.setZtype(ztype);
			System.out.println("推送至sap**************************************************");
			ZbfInterfaceResponse response = service.zbfInterface(ZbfInterface);
			int subrc = response.getSubrc();
			System.out.println(subrc);
			if(subrc == 0) {
				//sap返回成功
				System.out.println("sap返回**************************************************");
				String char10 = response.getSoNumber().getChar10();
				scmCwToSapDiscount.setSapCode(char10);
				System.out.println(char10);
				scmCwToSapDiscount.setIsToSap("1");
				scmCwToSapDiscountDao.update(scmCwToSapDiscount);
				return scmCwToSapDiscount;
			}else{
				StringBuffer sb = new StringBuffer();
				if(response.getReturn() != null) {
					Bapiret2[] item2 = response.getReturn().getItem();
					System.out.println("开始输出Return******************");
					for (Bapiret2 bapiret2 : item2) {
						if(bapiret2 == null) {
							System.out.println("item为null");
							continue;
						}
						System.out.println("Type:"+bapiret2.getType());
						System.out.println("Id:"+bapiret2.getId());
						System.out.println("Number:"+bapiret2.getNumber());
						System.out.println("Message:"+bapiret2.getMessage());
						if("E".equals(bapiret2.getType())) {
							sb.append(bapiret2.getMessage());
						}
						System.out.println("LogNo:"+bapiret2.getLogNo());
						System.out.println("LogMsgNo:"+bapiret2.getMessageV1());
						System.out.println("MessageV1:"+bapiret2.getMessageV2());
						System.out.println("MessageV2:"+bapiret2.getMessageV3());
						System.out.println("MessageV3:"+bapiret2.getMessageV4());
						System.out.println("MessageV4:"+bapiret2.getMessage());
						System.out.println("Parameter:"+bapiret2.getParameter());
						System.out.println("Row:"+bapiret2.getRow());
						System.out.println("Field:"+bapiret2.getField());
						System.out.println("System:"+bapiret2.getSystem());
					}
					System.out.println("结束输出Return******************");
				}else{
					System.out.println("return 为null");
				}
				System.out.println("sap结束**************************************************");
				//sap返回失败
				throw new RuntimeException("开票失败:"+sb.toString());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	@Override
	public ScmCwToSapDiscount getByShipId(String id) {
		ScmCwToSapDiscount ScmCwToSapDiscount = scmCwToSapDiscountDao.getByShipId(id);
		return ScmCwToSapDiscount;
	}
}
