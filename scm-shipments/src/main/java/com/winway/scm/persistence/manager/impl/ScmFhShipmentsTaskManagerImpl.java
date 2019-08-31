package com.winway.scm.persistence.manager.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;

import com.hotent.base.query.*;
import com.winway.purchase.util.ExcelExportUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.UniqueIdUtil;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.scm.model.ScmFhShipmentsTask;
import com.winway.scm.persistence.dao.ScmFhShipmentsTaskDao;
import com.winway.scm.persistence.manager.ScmFhShipmentsControlManager;
import com.winway.scm.persistence.manager.ScmFhShipmentsTaskManager;
import org.springframework.util.Assert;

/**
 * <pre>
 * 描述：发货任务表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-02-15 17:31:50
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmFhShipmentsTaskManager")
public class ScmFhShipmentsTaskManagerImpl extends AbstractManagerImpl<String, ScmFhShipmentsTask>
		implements ScmFhShipmentsTaskManager {
	@Resource
	ScmFhShipmentsTaskDao scmFhShipmentsTaskDao;
	@Resource
	ScmFhShipmentsControlManager scmFhShipmentsControlManager;
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;

	@Override
	protected MyBatisDao<String, ScmFhShipmentsTask> getDao() {
		return scmFhShipmentsTaskDao;
	}

	@Override
	public void theDeliveryTask(String jsonStr) throws ParseException {
		// 创建JSON解析器
		JsonParser parser = new JsonParser();
		// 解析为数组
		JsonArray array = (JsonArray) parser.parse(jsonStr);
		for (int i = 0; i < array.size(); i++) {
			// 创建发货任务对象
			ScmFhShipmentsTask scmFhShipmentsTask = new ScmFhShipmentsTask();
			JsonObject subObject = array.get(i).getAsJsonObject();
			
			if(verifyObject(subObject.get("ownerId"))) {
				scmFhShipmentsTask.setOwnerId(subObject.get("ownerId").getAsString());
			}else{
				scmFhShipmentsTask.setOwnerId("");
			}
			
			if (verifyObject(subObject.get("type"))) {
				scmFhShipmentsTask.setType(subObject.get("type").getAsString());
			} else {
				scmFhShipmentsTask.setType("0");
			}
			
			if (verifyObject(subObject.get("taskType"))) {
				scmFhShipmentsTask.setTaskType(subObject.get("taskType").getAsString());
			} else {
				scmFhShipmentsTask.setTaskType("");
			}
			
			if (verifyObject(subObject.get("dateCode"))) {
				scmFhShipmentsTask.setDateCode(subObject.get("dateCode").getAsString());
			} else {
				scmFhShipmentsTask.setDateCode("*");
			}
			
			if (verifyObject(subObject.get("paymentType"))) {
				scmFhShipmentsTask.setPaymentType(subObject.get("paymentType").getAsString());
			} else {
				scmFhShipmentsTask.setPaymentType("");
			}
			
			scmFhShipmentsTask.setIsSms("0");
			if (verifyObject(subObject.get("commerceName"))) {
				scmFhShipmentsTask.setCommerceName(subObject.get("commerceName").getAsString());
			} else {
				scmFhShipmentsTask.setCommerceName("");
			}
			
			if (verifyObject(subObject.get("commerceId"))) {
				scmFhShipmentsTask.setCommerceId(subObject.get("commerceId").getAsString());
			} else {
				scmFhShipmentsTask.setCommerceId("");
			}
			
			if (verifyObject(subObject.get("productName"))) {
				scmFhShipmentsTask.setProductName(subObject.get("productName").getAsString());
			} else {
				scmFhShipmentsTask.setProductName("");
			}
			
			if (verifyObject(subObject.get("productNum"))) {
				scmFhShipmentsTask.setProductNum(subObject.get("productNum").getAsString());
			} else {
				scmFhShipmentsTask.setProductNum("");
			}
			
			if (verifyObject(subObject.get("MedicineType"))) {
				scmFhShipmentsTask.setMedicineType(subObject.get("MedicineType").getAsString());
			} else {
				scmFhShipmentsTask.setMedicineType("");
			}
			
			if (verifyObject(subObject.get("productState"))) {
				scmFhShipmentsTask.setProductState(subObject.get("productState").getAsString());
			} else {
				scmFhShipmentsTask.setProductState("");
			}

			if (verifyObject(subObject.get("aChange"))) {
				scmFhShipmentsTask.setAChange(subObject.get("aChange").getAsString());
			} else {
				scmFhShipmentsTask.setAChange("0");
			}

			if (verifyObject(subObject.get("packageNum"))) {
				scmFhShipmentsTask.setPackageNum(subObject.get("packageNum").getAsInt());
			} else {
				scmFhShipmentsTask.setPackageNum(0);
			}

			if (verifyObject(subObject.get("commercetype"))) {
//				scmFhShipmentsTask.setCommercetype(subObject.get("commercetype").getAsString());
				scmFhShipmentsTask.setCommercetype("经销商");
			} else {
				scmFhShipmentsTask.setCommercetype("经销商");
			}

			if (verifyObject(subObject.get("marketType"))) {
				scmFhShipmentsTask.setMarketType(subObject.get("marketType").getAsString());
			} else {
				scmFhShipmentsTask.setMarketType("*");
			}

			if (verifyObject(subObject.get("price"))) {
				scmFhShipmentsTask.setPrice(subObject.get("price").getAsDouble());
			} else {
				scmFhShipmentsTask.setPrice(0.0d);
			}

			if (verifyObject(subObject.get("memo"))) {
				scmFhShipmentsTask.setMemo(subObject.get("memo").getAsString());
			} else {
				scmFhShipmentsTask.setMemo("");
			}

			if (verifyObject(subObject.get("planShipmentsSum"))) {
				scmFhShipmentsTask.setPlanShipmentsSum(subObject.get("planShipmentsSum").getAsInt());
			} else {
				scmFhShipmentsTask.setPlanShipmentsSum(0);
			}

			if (verifyObject(subObject.get("contactPersion"))) {
				scmFhShipmentsTask.setContactPersion(subObject.get("contactPersion").getAsString());
			} else {
				scmFhShipmentsTask.setContactPersion("");
			}

			if (verifyObject(subObject.get("phone"))) {
				scmFhShipmentsTask.setPhone(subObject.get("phone").getAsString());
			} else {
				scmFhShipmentsTask.setPhone("");
			}

			if (verifyObject(subObject.get("sumPrice"))) {
				scmFhShipmentsTask.setSumPrice(subObject.get("sumPrice").getAsDouble());
			} else {
				scmFhShipmentsTask.setSumPrice(0.0d);
			}

			if (verifyObject(subObject.get("focusShipmentsMonth"))) {
				scmFhShipmentsTask.setFocusShipmentsMonth(subObject.get("focusShipmentsMonth").getAsString());
			} else {
				scmFhShipmentsTask.setFocusShipmentsMonth("");
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (verifyObject(subObject.get("sendProductDate"))) {
				try{
					if(subObject.get("sendProductDate").getAsString().length() > 10) {
						scmFhShipmentsTask.setSendProductDate(sdf1.parse(subObject.get("sendProductDate").getAsString()));
					}else{
						scmFhShipmentsTask.setSendProductDate(sdf.parse(subObject.get("sendProductDate").getAsString()));
					}
				}catch(Exception e){
					scmFhShipmentsTask.setSendProductDate(null);
				}
			} else {
				scmFhShipmentsTask.setSendProductDate(null);
			}

			scmFhShipmentsTask.setTaskCreateDate(new Date());

			if (verifyObject(subObject.get("commonName"))) {
				scmFhShipmentsTask.setCommonName(subObject.get("commonName").getAsString());
			} else {
				scmFhShipmentsTask.setCommonName("");
			}

			if (verifyObject(subObject.get("unit"))) {
				scmFhShipmentsTask.setUnit(subObject.get("unit").getAsString());
			} else {
				scmFhShipmentsTask.setUnit("");
			}

			if (verifyObject(subObject.get("AgentType"))) {
				scmFhShipmentsTask.setAgentType(subObject.get("AgentType").getAsString());
			} else {
				scmFhShipmentsTask.setAgentType("");
			}

			if (verifyObject(subObject.get("manufacturer"))) {
				scmFhShipmentsTask.setManufacturer(subObject.get("manufacturer").getAsString());
			} else {
				scmFhShipmentsTask.setManufacturer("");
			}

			if (verifyObject(subObject.get("manufacturer"))) {
				scmFhShipmentsTask.setManufacturer(subObject.get("manufacturer").getAsString());
			} else {
				scmFhShipmentsTask.setManufacturer("");
			}

			if (verifyObject(subObject.get("approvalCode"))) {
				scmFhShipmentsTask.setApprovalCode(subObject.get("approvalCode").getAsString());
			} else {
				scmFhShipmentsTask.setApprovalCode("");
			}

			if (verifyObject(subObject.get("medicineClassify"))) {
				scmFhShipmentsTask.setMedicineClassify(subObject.get("medicineClassify").getAsString());
			} else {
				scmFhShipmentsTask.setMedicineClassify("");
			}

			if (verifyObject(subObject.get("businessScope"))) {
				scmFhShipmentsTask.setBusinessScope(subObject.get("businessScope").getAsString());
			} else {
				scmFhShipmentsTask.setBusinessScope("");
			}

			if (verifyObject(subObject.get("code"))) {
				scmFhShipmentsTask.setCode(subObject.get("code").getAsString());
			} else {
				scmFhShipmentsTask.setCode("");
			}

			create(scmFhShipmentsTask);
		}
	}

	public boolean verifyObject(JsonElement jsonElement) {
		if (jsonElement == null) {
			return false;
		}
		if ("".equals(jsonElement.toString())) {
			return false;
		}
        return !"null".equals(jsonElement.toString());
    }

	@Override
	public PageList<ScmFhShipmentsTask> firstList(QueryFilter queryFilter) throws Exception {
		PageBean pageBean = queryFilter.getPageBean();
		// 发货任务完成的不显示
		String commerceId = queryFilter.getParams().get("commerceId").toString();
		if ("".equals(commerceId) || commerceId == null || "null".equals(commerceId)) {
			throw new RuntimeException("商业ID未获取到");
		}
		// queryFilter.getParams().remove("commerceId");
		queryFilter.addFilter("type", "2", QueryOP.NOT_EQUAL);
		if (BeanUtils.isEmpty(pageBean)) {
			PageHelper.startPage(1, Integer.MAX_VALUE, false);
		} else {
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
		}
		List<ScmFhShipmentsTask> query = scmFhShipmentsTaskDao.query(queryFilter.getParams());

		// 判断药品是否禁售或者停售

		// 调接口
		Map<String, String> listCommerceBanProduct = new HashMap<>();
		boolean b = true;
		try {
			listCommerceBanProduct = scmFhShipmentsControlManager.ListCommerceBanProduct(commerceId);
		} catch (Exception e) {
			listCommerceBanProduct = new HashMap<>();
			b = false;
		}

		Set<String> keySet = listCommerceBanProduct.keySet();
		// 用集合中的药品编号和数据库中的对比
		for (ScmFhShipmentsTask scmFhShipmentsTask : query) {
			// 遍历获取到的商品列表,把禁止发货信息及状态封装到任务中,
			if (!listCommerceBanProduct.isEmpty()) {
				for (String productCode : keySet) {
					if (productCode.equals(scmFhShipmentsTask.getProductNum())) {
						// 匹配到不可以发货的商品把禁止发货信息及状态添加到列表中
						if ("0".equals(listCommerceBanProduct.get(productCode))) {
							scmFhShipmentsTask.setBanDeliveryMessage("发货控制中禁止发货");
							scmFhShipmentsTask.setBanDelivery("0");
						}
					}
				}
			}
			if (scmFhShipmentsTask.getBanDelivery() == null || "".equals(scmFhShipmentsTask.getBanDelivery())
					|| "null".equals(scmFhShipmentsTask.getBanDelivery())) {
				scmFhShipmentsTask.setBanDeliveryMessage("可正常发货");
				scmFhShipmentsTask.setBanDelivery("1");
			}
		}
		// 判断商业证照是否超期
		boolean commerceStateByFirstId = scmMasterDateFeignService.getCommerceStateByFirstId(commerceId);
		if (!commerceStateByFirstId) {
			for (ScmFhShipmentsTask scmFhShipmentsTask : query) {
				scmFhShipmentsTask.setBanDeliveryMessage("商业证照超期,禁止发货");
				scmFhShipmentsTask.setBanDelivery("0");
			}
		}
		String string = listCommerceBanProduct.get("allBan");
		if ("1".equals(string)) {
			for (ScmFhShipmentsTask scmFhShipmentsTask : query) {
				scmFhShipmentsTask.setBanDeliveryMessage("商业未首营/未认可/无协议，禁止发货");
				scmFhShipmentsTask.setBanDelivery("0");
			}
		}

		// 如果不为空，说明该药品禁售或者停售
		return new PageList<ScmFhShipmentsTask>(query);
	}

	@Override
	public void create(ScmFhShipmentsTask entity) {
		entity.setId(UniqueIdUtil.getSuid());
		scmFhShipmentsTaskDao.create(entity);
	}

	@Override
	public List<Map<String, String>> commerceList(QueryFilter queryFilter) {

		PageBean pageBean = queryFilter.getPageBean();
		if (BeanUtils.isEmpty(pageBean)) {
			PageHelper.startPage(1, Integer.MAX_VALUE, false);
		} else {
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize(), pageBean.showTotal());
		}
		return scmFhShipmentsTaskDao.commerceList(queryFilter.getParams());
	}

	@Override
	public List<String> proList(String ownerId) {
		return scmFhShipmentsTaskDao.proList(ownerId);
	}

	@Override
	public List<String> codeList(String ownerId) {
		return scmFhShipmentsTaskDao.codeList(ownerId);
	}

	@Override
	public void export(QueryFilter queryFilter, boolean isPage, HttpServletResponse response) {
		queryFilter.addFilter("type", "0,1", QueryOP.IN, FieldRelation.AND);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<List<String>> data = new ArrayList<List<String>>();
		String[] tableName = { "货主id", "类型", "任务类型", "数据编号", "任务生成时间", "付款类型", "集中发货分配月", "商业名称", "商品名称", "药品编号",
				"药品类型", "规格型号", "件数", "件装量", "客户类型", "销售类型", "单价", "备注", "计划发货量", "联系人", "联系电话", "总价", "分配编号", "计划发货时间",
				"商品通用名", "单位", "剂型", "生产厂家", "批准文号", "药品分类", "经营范围", "产品子码" };
		PageList<ScmFhShipmentsTask> query = new PageList<>();
		List<FieldSort> sorter = new ArrayList<>();
		FieldSort fieldSort = new FieldSort();
		fieldSort.setProperty("taskCreateDate");
		fieldSort.setDirection(Direction.DESC);
		sorter.add(fieldSort);
		// 如果不分页
		if (!isPage) {
			QueryFilter build = QueryFilter.build();
			build.addFilter("type", "0,1", QueryOP.IN, FieldRelation.AND);
			build.setSorter(sorter);
			PageHelper.startPage(1, Integer.MAX_VALUE, true);
			List<ScmFhShipmentsTask> query1 = scmFhShipmentsTaskDao.query(build.getParams());
			query = new PageList(query1);
		} else {
			queryFilter.setSorter(sorter);
			query = super.query(queryFilter);
		}
		List<ScmFhShipmentsTask> rows = query.getRows();
		int size = rows.size();
		
		for (int i = 0; i < rows.size(); i++) {
			List<String> br = new ArrayList<>();
			ScmFhShipmentsTask task = rows.get(i);
			br.add(task.getOwnerId());
			if (task.getType() == null) {
				br.add("");
			} else {
				switch (task.getType()) {
				case "0":
					br.add("发货任务");
					break;
				case "1":
					br.add("剩余任务");
					break;
				case "2":
					br.add("任务完成");
					break;
				}
			}
			if (task.getTaskType() == null) {
				br.add("");
			} else {
				switch (task.getTaskType()) {
				case "0":
					br.add("集中发货");
					break;
				case "1":
					br.add("分配");
					break;
				case "2":
					br.add("日常合同");
					break;
				case "3":
					br.add("当此退货");
					break;
				case "4":
					br.add("商业退货");
					break;
				}
			}
			br.add(task.getDateCode());
			br.add(task.getTaskCreateDate() == null ? "" : format.format(task.getTaskCreateDate()));
			if (task.getPaymentType() == null||"".equals(task.getPaymentType())) {
				br.add("");
			} else {
				switch (task.getPaymentType()) {
				case "0":
					br.add("预付款");
					break;
				case "1":
					br.add("赊销");
					break;
				}
			}
			br.add(task.getFocusShipmentsMonth());
			br.add(task.getCommerceName());
			br.add(task.getProductName());
			br.add(task.getProductNum());
			br.add(task.getMedicineType());
			br.add(task.getProductState());
			br.add(Double.valueOf(task.getPackageNum()) == null ? "" : String.valueOf(task.getPackageNum()));
			br.add(task.getaChange());
			br.add(task.getCommercetype());
			br.add(task.getMarketType());
			br.add(task.getPrice() == null ? "" : task.getPrice().toString());
			br.add(task.getMemo());
			br.add(task.getPlanShipmentsSum() == null ? "" : task.getPlanShipmentsSum().toString());
			br.add(task.getContactPersion());
			br.add(task.getPhone());
			br.add(task.getSumPrice() == null ? "" : task.getSumPrice().toString());
			br.add(task.getAllocationCode());
			br.add((task.getSendProductDate() == null) ? "" : format.format(task.getSendProductDate()));
			br.add(task.getCommonName());
			br.add(task.getUnit());
			br.add(task.getAgentType());
			br.add(task.getManufacturer());
			br.add(task.getApprovalCode());
			br.add(task.getMedicineClassify());
			br.add(task.getBusinessScope());
			br.add(task.getCode());
			data.add(br);
		}
		ExcelExportUtil.download(response, "销售合同发货数据表", "销售合同发货任务数据", tableName, data);
	}

}
