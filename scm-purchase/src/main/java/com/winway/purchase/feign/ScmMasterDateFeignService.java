package com.winway.purchase.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotent.base.conf.FeignConfig;
import com.winway.purchase.feign.impl.ScmMasterDateFeignServiceImpl;

import io.swagger.annotations.ApiParam;

@FeignClient(name="scm-start",fallback=ScmMasterDateFeignServiceImpl.class,configuration={FeignConfig.class})
public interface ScmMasterDateFeignService {

	  @RequestMapping(value={"/scm/scmZsjCommerceBlackList/v1/getCommerceIsBlack/{id}/{ownerId}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public abstract boolean getCommerceIsBlack(@ApiParam(name="id",value="业务对象主键", required = true)@PathVariable(value = "id")  String id,
			  @ApiParam(name = "ownerId" ,value="货主ID",required=true)@PathVariable(value = "ownerId") String ownerId);
	  //获取集中发货设置
	  @RequestMapping(value = {"/scm/scmFhFocusShipmentsSet/v1/listFocusShipmentsSetByComm/{commerceId}/{ownerId}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
	  public abstract  String  listFocusShipmentsSetByComm(@ApiParam(name = "commerceId", value = "商业ID")@PathVariable("commerceId") String commerceId,
			  @ApiParam(name = "ownerId", value = "货主ID")@PathVariable("ownerId") String ownerId);

	  @RequestMapping(value = {"/scm/scmLogModifyLog/v1/save"},method = {org.springframework.web.bind.annotation.RequestMethod.POST})
	  public abstract  void  inserScmLogRecord(@ApiParam(name = "updateMassge", value = "数据解析字符串") @RequestParam(value = "updateMassge") String updateMassge);
	  
	  //发货任务
	  @RequestMapping(value = {"/scm/scmFhShipmentsTask/v1/theDeliveryTask"},method = {org.springframework.web.bind.annotation.RequestMethod.POST})
	  public abstract  void  theDeliveryTask(@ApiParam(name = "jsonStr", value = "数据解析字符串") @RequestParam(value = "jsonStr") String jsonStr);
	  //根据商业ID获取商业信息
	  @RequestMapping(value = {"/scm/scmZsjCommerce/v1/getcommerceById/{commerceId}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
	  public abstract  String  getcommerceById(@ApiParam(name = "commerceId", value = "商业ID") @PathVariable(value = "commerceId") String commerceId);
	  //根据商品编号获取件装量
	  @RequestMapping(value = {"/scm/scmZsjProduct/v1/listAndAchage"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
	  public abstract List<String> listAndAchage(@ApiParam(name = "productCode", value = "商业ID",required = true) @RequestParam(value = "productCode") String productCode);
	  //根据商品编号获取件装量
	  @RequestMapping(value = {"/scm/scmZsjProduct/v1/getProductByCode"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
	  public abstract String getProductByCode(@ApiParam(name = "productCode", value = "商业ID",required = true) @RequestParam(value = "productCode") String productCode);
	  //根据货主Id联合查询商业表信息
	  @RequestMapping(value = {"/scm/scmZsjCommerce/v1/findByOwnerId"},method = {org.springframework.web.bind.annotation.RequestMethod.POST})
	  public abstract String findByOwnerId(@ApiParam(name = "ownerId", value = "货主Id",required = true) @RequestParam(value = "ownerId") String ownerId);



	  
}
