package com.winway.purchase.feign.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.hotent.base.exception.BaseException;
import com.winway.purchase.feign.ScmMasterDateFeignService;
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
	public String getProductByCode(String productCode) {
		throw new BaseException(HttpStatus.INTERNAL_SERVER_ERROR, Integer.valueOf(500), "服务器开小差了， 请稍后重试。");
	}


}
