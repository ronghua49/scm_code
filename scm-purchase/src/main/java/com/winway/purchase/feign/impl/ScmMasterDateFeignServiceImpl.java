package com.winway.purchase.feign.impl;

import java.util.List;

import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.purchase.vo.ScmCgGodownentry;
import com.winway.purchase.vo.ScmZsjCommerce;
import com.winway.purchase.vo.ScmZsjProduct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.hotent.base.exception.BaseException;
import com.hotent.base.model.CommonResult;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
public class ScmMasterDateFeignServiceImpl implements ScmMasterDateFeignService {

	@Override
	public boolean getCommerceIsBlack(String id,String ownerId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public void inserScmLogRecord(String updateMassge) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}
	@RequestMapping(value = {"/scm/scmFhFocusShipmentsSet/v1/listFocusShipmentsSetByComm/{commerceId}/{ownerId}"}, method = {RequestMethod.GET})
    @Override
	public String listFocusShipmentsSetByComm(String commerceId, String ownerId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}
	@Override
	public void theDeliveryTask(String updateMassge) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}
	@Override
	public String getcommerceById(String commerceId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public List<String> listAndAchage(String commerceId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String findByOwnerId(String ownerId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
		
	}

	@Override
	public String getByProductCode(String productCode) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public void updatebyParam(String procurementCode, String productCode, String rejectTotal) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String getRejectTotalByParam(String procurementCode, String productCode) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String getProductByCode(String productCode) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public CommonResult<String> getRB(String ownerId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String getShipmentsDatailMasterById(String id) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String getcommerceByCode(String commerceCode) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public boolean getCommerceStateByFirstId(String commerceFirstId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String isInForce(String supplierId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

    @Override
    public String getAccreditProCodes(String supplierId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
    }

	@Override
	public String getAccreditProCodes1(String commerceFirstId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String getScmZsjSupplier(String commerceFirstId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String getShipmentsHistory(String commerceId, String ownerId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String getcommerceFirstByCode(String commerceCode,String ownerId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String getcommerceFirstById(String commerceId) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public CommonResult<String> updateShipmentDatailCanInvoice(String id, String type) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public CommonResult<String> getProductNumByCode(String code) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String getcommerceByName(String commerceName) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String getProductByName(String productName) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String getProductByCommonName(String commonName) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public void saveByContractAndReturnMoney(String dataId, double price, String commerceId, String ownerId,String recordType) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String getPAnnualFee(String businessDivisionId, String provinceId, String year) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public String getProductByCommonNameAndState(String commonName, String productState) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");

	}

	@Override
	public String getGodownentyByCode(String productCode) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");

	}

	@Override
	public PageList<ScmZsjCommerce> commerceList(QueryFilter queryFilter) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public PageList<ScmZsjProduct> productList(QueryFilter queryFilter) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public PageList<ScmCgGodownentry> godownEntryList(QueryFilter queryFilter) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public CommonResult<String> getProductMessageByCode(String code) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public boolean verifExceedTime(String commerceCode) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}

	@Override
	public boolean verifyDeliveryAmount(String commerceFirstId, double priceSum) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}


}
