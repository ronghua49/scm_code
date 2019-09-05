package com.winway.purchase.feign;

import java.util.List;

import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.purchase.vo.ScmCgGodownentry;
import com.winway.purchase.vo.ScmZsjCommerce;
import com.winway.purchase.vo.ScmZsjProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.hotent.base.conf.FeignConfig;
import com.hotent.base.model.CommonResult;
import com.winway.purchase.feign.impl.ScmMasterDateFeignServiceImpl;

import io.swagger.annotations.ApiParam;

@FeignClient(name="scm-start",fallback=ScmMasterDateFeignServiceImpl.class,configuration={FeignConfig.class})
public interface ScmMasterDateFeignService {

    @RequestMapping(value={"/scm/scmZsjCommerceBlackList/v1/getCommerceIsBlack/{id}/{ownerId}"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    boolean getCommerceIsBlack(@ApiParam(name = "id", value = "业务对象主键", required = true) @PathVariable(value = "id") String id,
                               @ApiParam(name = "ownerId", value = "货主ID", required = true) @PathVariable(value = "ownerId") String ownerId);
    //获取集中发货设置
    @RequestMapping(value = {"/scm/scmFhFocusShipmentsSet/v1/listFocusShipmentsSetByComm/{commerceId}/{ownerId}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String  listFocusShipmentsSetByComm(@ApiParam(name = "commerceId", value = "商业ID") @PathVariable("commerceId") String commerceId,
                                        @ApiParam(name = "ownerId", value = "货主ID") @PathVariable("ownerId") String ownerId);

    @RequestMapping(value = {"/scm/scmLogModifyLog/v1/save"},method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    void  inserScmLogRecord(@ApiParam(name = "updateMassge", value = "数据解析字符串") @RequestParam(value = "updateMassge") String updateMassge);

    //发货任务
    @RequestMapping(value = {"/scm/scmFhShipmentsTask/v1/theDeliveryTask"},method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    void  theDeliveryTask(@ApiParam(name = "jsonStr", value = "数据解析字符串") @RequestParam(value = "jsonStr") String jsonStr);
    //根据商业ID获取商业信息
    @RequestMapping(value = {"/scm/scmZsjCommerce/v1/getcommerceById/{commerceId}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String  getcommerceById(@ApiParam(name = "commerceId", value = "商业ID") @PathVariable(value = "commerceId") String commerceId);
    //根据商品编号获取件装量
    @RequestMapping(value = {"/scm/scmZsjProduct/v1/listAndAchage"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    List<String> listAndAchage(@ApiParam(name = "productCode", value = "商业ID", required = true) @RequestParam(value = "productCode") String productCode);
    //根据商品编号获取件装量
    @RequestMapping(value = {"/scm/scmZsjProduct/v1/getProductByCode"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String getProductByCode(@ApiParam(name = "productCode", value = "商业ID", required = true) @RequestParam(value = "productCode") String productCode);
    //根据货主Id联合查询商业表信息
    @RequestMapping(value = {"/scm/scmZsjCommerce/v1/findByOwnerId"},method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    String findByOwnerId(@ApiParam(name = "ownerId", value = "货主Id", required = true) @RequestParam(value = "ownerId") String ownerId);
    //根据商品编码获取商品对象信息
    @RequestMapping(value = {"/scm/scmZsjProduct/v1/getByProductCode"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String getByProductCode(@ApiParam(name = "productCode", value = "商品编号", required = true) @RequestParam(value = "productCode") String productCode);
    //根据订单号，商品编号，已拒数量 更新订单商品中的 已拒数量字段
    @RequestMapping(value = {"/scm/scmCgProOrderProduct/v1/update"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    void updatebyParam(@ApiParam(name = "procurementCode", value = "采购单号", required = true) @RequestParam(value = "procurementCode") String procurementCode, @ApiParam(name = "productCode", value = "商品编号", required = true) @RequestParam(value = "productCode") String productCode, @ApiParam(name = "rejectSum", value = "拒收数量", required = true) @RequestParam(value = "rejectSum") String rejectSum);
    //根据订单号，商品编号，获取订单商品已拒数量字段
    @RequestMapping(value = {"/scm/scmCgProOrderProduct/v1/get/{procurementCode}/{productCode}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String getRejectTotalByParam(@ApiParam(name = "procurementCode", value = "采购单号", required = true) @PathVariable(value = "procurementCode") String procurementCode, @ApiParam(name = "productCode", value = "商品编号", required = true) @PathVariable(value = "productCode") String productCode);

    @RequestMapping(value = {"/scm/scmZsjTXCompany/v1/getRb/{ownerId}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    CommonResult<String> getRB(@ApiParam(name = "ownerId", value = "货主ID",required = true) @PathVariable(value = "ownerId") String ownerId);

    //根据发货单ID 获取发货单数据
    @RequestMapping(value = {"/scm/scmFhShipmentsDatailMaster/v1/getShipmentsDatailMasterById/{id}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String getShipmentsDatailMasterById(@ApiParam(name = "id", value = "发货单主表ID", required = true) @PathVariable(value = "id") String id);

    @RequestMapping(value = {"/scm/scmZsjCommerce/v1/getcommerceByCode/{commerceCode}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String getcommerceByCode(@PathVariable(value = "commerceCode") String commerceCode);

    @RequestMapping(value = {"/scm/scmZsjCommerceFirst/v1/getCommerceStateByFirstId/{commerceFirstId}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    boolean getCommerceStateByFirstId(@PathVariable(value = "commerceFirstId") String commerceFirstId);


    @RequestMapping(value = {"/scm/scmZsjSupplierFirst/v1/isInForce/{supplierId}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String isInForce(@PathVariable(value = "supplierId") String supplierId);


    @RequestMapping(value = {"/scm/scmZsjSupplierFirst/v1/getAccreditProCodes/{supplierId}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String getAccreditProCodes(@PathVariable(value = "supplierId") String supplierId);

    @RequestMapping(value = {"/scm/scmZsjCommerceFirst/v1/getAccreditProCodes/{commerceFirstId}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String getAccreditProCodes1(@PathVariable(value = "commerceFirstId") String commerceFirstId);

    @RequestMapping(value = {"/scm/scmZsjSupplier/v1/get/{id}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String getScmZsjSupplier(@PathVariable(value = "id") String id);

    @RequestMapping(value = {"/scm/scmFhShipmentsDatailMaster/v1/getShipmentsHistory/{commerceId}/{ownerId}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String getShipmentsHistory(@PathVariable(value = "commerceId")String commerceId, @PathVariable(value = "ownerId")String ownerId);

    @RequestMapping(value = {"/scm/scmZsjCommerce/v1/getFirstList/{commerceCode}/{ownerId}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String getcommerceFirstByCode(@PathVariable(value = "commerceCode")String commerceCode,@PathVariable(value = "ownerId")String ownerId);

    @RequestMapping(value = {"/scm/scmZsjCommerceFirst/v1/getById/{id}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String getcommerceFirstById(@PathVariable(value = "id") String id);

    @RequestMapping(value = {"/scm/scmFhShipmentsDatailMaster/v1/updateShipmentDatailCanInvoice/{id}/{type}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    CommonResult<String> updateShipmentDatailCanInvoice(@PathVariable(value = "id") String id, @PathVariable(value = "type") String type);
    //根据商品子码获取商品物料编码
    @RequestMapping(value = {"/scm/ScmZsjAchage/v1/getProductNumByCode/{code}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    CommonResult<String> getProductNumByCode(@PathVariable(value = "code") String code);
    //根据商业名称获取商业
    @RequestMapping(value = {"/scm/scmZsjCommerce/v1/getcommerceByName"},method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    String getcommerceByName(@RequestParam(value = "commerceName") String commerceName);

    //根据商品名称获取商品
    @RequestMapping(value = {"/scm/scmZsjProduct/v1/getProductByName"},method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    String getProductByName(@RequestParam(value = "productName") String productName);

    //根据商品通用名获取商品
    @RequestMapping(value = {"/scm/scmZsjProduct/v1/getProductByCommonName"},method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    String getProductByCommonName(@RequestParam(value = "commonName") String commonName);




    //回款插入记录
    @RequestMapping(value = {"/scm/scmXsCreditApplyRecord/v1/saveByContractAndReturnMoney"},method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    void saveByContractAndReturnMoney(
            @RequestParam(value = "dataId") String dataId,
            @RequestParam(value = "price") double price,
            @RequestParam(value = "commerceId") String commerceId,
            @RequestParam(value = "ownerId") String ownerId,
            @RequestParam(value = "recordType") String recordType);


    //查询商务分区和省区下 经销商协议 年费信息
	@RequestMapping(value = {"/scm/scmXsAgreementSummary/v1/aunualFee"},method = {RequestMethod.POST})
    String getPAnnualFee(@ApiParam(name = "businessDivisionId", value = "商务分区id",required = true) @RequestParam (value = "businessDivisionId")String businessDivisionId,  
    		@ApiParam(name = "provinceId", value = "商务省区id",required = true) @RequestParam(value = "provinceId")  String provinceId,
    		@ApiParam(name = "year", value = "当前年份",required = true) @RequestParam (value = "year")String year);




    //根据商品名称和规格获取商品
    @RequestMapping(value = {"/scm/scmZsjProduct/v1/getProductByCommonNameAndState"},method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    String getProductByCommonNameAndState(@RequestParam(value = "commonName")String commonName,@RequestParam(value = "productState") String productState);

    //根据批号获取入有效期
    @RequestMapping(value = {"/scm/scmCgGodownentry/v1/getExpdate/{productCode}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    String getGodownentyByCode(@PathVariable(value = "productCode") String  productCode);
    
    //验证商业回款是否超期
    @RequestMapping(value = {"/scm/scmCwInvoice/v1/verifExceedTime/{commerceCode}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    boolean verifExceedTime(@PathVariable(value = "commerceCode") String  commerceCode);

    //获取商业列表
    @RequestMapping(value = {"/scm/scmZsjCommerce/v1/list"},method = {org.springframework.web.bind.annotation.RequestMethod.POST} )
    PageList<ScmZsjCommerce> commerceList(@RequestBody QueryFilter queryFilter);

    //获取商品列表
    @RequestMapping(value = {"/scm/scmZsjProduct/v1/list"},method = {org.springframework.web.bind.annotation.RequestMethod.POST} )
    PageList<ScmZsjProduct> productList(@RequestBody QueryFilter queryFilter);

    //获取入库单列表
    @RequestMapping(value = {"/scm/scmCgGodownentry/v1/list"},method = {org.springframework.web.bind.annotation.RequestMethod.POST} )
    PageList<ScmCgGodownentry> godownEntryList(@RequestBody QueryFilter queryFilter);
    
    //根据商品子码获取商品信息
    @RequestMapping(value = {"/scm/ScmZsjAchage/v1/getProductMessageByCode/{code}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    CommonResult<String> getProductMessageByCode(@PathVariable(value = "code") String code);

    //根据商品子码获取商品信息
    @RequestMapping(value = {"/scm/scmFhShipmentsDatail/v1/verifyDeliveryAmount/{commerceFirstId}/{priceSum}"},method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    boolean verifyDeliveryAmount(@PathVariable(value = "commerceFirstId") String commerceFirstId,@PathVariable(value = "priceSum") double priceSum);


}
