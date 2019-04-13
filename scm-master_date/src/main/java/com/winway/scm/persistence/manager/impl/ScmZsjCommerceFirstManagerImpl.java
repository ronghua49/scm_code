 package com.winway.scm.persistence.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.winway.purchase.feign.ScmMasterDateFeignService;
import com.winway.purchase.util.ContrasUtil;
import com.winway.scm.model.ScmZdPromiseBook;
import com.winway.scm.model.ScmZsjCommerce;
import com.winway.scm.model.ScmZsjCommerceAccredit;
import com.winway.scm.model.ScmZsjCommerceBusiness;
import com.winway.scm.model.ScmZsjCommerceEntruseBook;
import com.winway.scm.model.ScmZsjCommerceFirst;
import com.winway.scm.model.ScmZsjCommerceLicence;
import com.winway.scm.model.ScmZsjCommercePromiseBook;
import com.winway.scm.model.ScmZsjCommerceShipAddress;
import com.winway.scm.model.ScmZsjCommerceWarehouse;
import com.winway.scm.model.ScmZsjGSP;
import com.winway.scm.model.ScmZsjTreatLicence;
import com.winway.scm.persistence.dao.ScmZdPromiseBookDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceAccreditDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceBusinessDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceEntruseBookDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceFirstDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceLicenceDao;
import com.winway.scm.persistence.dao.ScmZsjCommercePromiseBookDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceShipAddressDao;
import com.winway.scm.persistence.dao.ScmZsjCommerceWarehouseDao;
import com.winway.scm.persistence.dao.ScmZsjGSPDao;
import com.winway.scm.persistence.dao.ScmZsjTreatLicenceDao;
import com.winway.scm.persistence.manager.ScmZsjCommerceAccreditManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceBusinessManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceEntruseBookManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceFirstManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceLicenceManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceManager;
import com.winway.scm.persistence.manager.ScmZsjCommercePromiseBookManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceShipAddressManager;
import com.winway.scm.persistence.manager.ScmZsjCommerceWarehouseManager;
import com.winway.scm.persistence.manager.ScmZsjGSPManager;
import com.winway.scm.persistence.manager.ScmZsjTreatLicenceManager;

/**
 * 
 * <pre> 
 * 描述：商业首营记录表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-01-03 09:41:54
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmZsjCommerceFirstManager")
public class ScmZsjCommerceFirstManagerImpl extends AbstractManagerImpl<String, ScmZsjCommerceFirst> implements ScmZsjCommerceFirstManager{
	@Resource
	ScmZsjCommerceFirstDao scmZsjCommerceFirstDao;
	@Resource
	ScmZsjCommerceWarehouseDao scmZsjCommerceWarehouseDao;
	@Resource
	ScmZsjCommerceLicenceDao scmZsjCommerceLicenceDao;
	@Resource
	ScmZsjCommerceBusinessDao scmZsjCommerceBusinessDao;
	@Resource
	ScmZsjTreatLicenceDao scmZsjTreatLicenceDao;
	@Resource
	ScmZsjGSPDao scmZsjGSPDao;
	@Resource
	ScmZsjCommercePromiseBookDao scmZsjCommercePromiseBookDao;
	@Resource
	ScmZsjCommerceShipAddressDao scmZsjCommerceShipAddressDao;
	@Resource
	ScmZdPromiseBookDao scmZdPromiseBookDao;
	@Resource
	ScmZsjCommerceEntruseBookDao scmZsjCommerceEntruseBookDao;
	@Resource
	ScmZsjCommerceAccreditDao scmZsjCommerceAccreditDao;
	@Resource
	ScmZsjCommerceManager scmZsjCommerceManager;
	@Resource
	ScmZsjCommerceLicenceManager scmZsjCommerceLicenceManager;
	
	@Resource
	ScmZsjCommerceBusinessManager scmZsjCommerceBusinessManager;
	
	@Resource
	ScmZsjTreatLicenceManager scmZsjTreatLicenceManager;
	
	@Resource
	ScmZsjGSPManager scmZsjGSPManager;
	@Resource
	ScmZsjCommercePromiseBookManager scmZsjCommercePromiseBookManager;
	
	@Resource
	ScmZsjCommerceAccreditManager scmZsjCommerceAccreditManager;
	
	@Resource
	ScmZsjCommerceWarehouseManager scmZsjCommerceWarehouseManager;
	
	@Resource
	ScmZsjCommerceShipAddressManager scmZsjCommerceShipAddressManager;
	
	@Resource
	ScmZsjCommerceEntruseBookManager scmZsjCommerceEntruseBookManager;
	
	
	@Resource
	ScmMasterDateFeignService scmMasterDateFeignService;
	
	
	@Override
	protected MyBatisDao<String, ScmZsjCommerceFirst> getDao() {
		return scmZsjCommerceFirstDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(ScmZsjCommerceFirst scmZsjCommerceFirst){
    	super.create(scmZsjCommerceFirst);
    	String pk=scmZsjCommerceFirst.getId();
//    	//商业生产/仓库地址
    	List<ScmZsjCommerceWarehouse> scmZsjCommerceWarehouseList=scmZsjCommerceFirst.getScmZsjCommerceWarehouseList();
    	for(ScmZsjCommerceWarehouse scmZsjCommerceWarehouse:scmZsjCommerceWarehouseList){
    		scmZsjCommerceWarehouse.setCommerceFirstId(pk);
    		scmZsjCommerceWarehouseManager.create(scmZsjCommerceWarehouse);
    	}
    	//商业许可证信息
    	List<ScmZsjCommerceLicence> scmZsjCommerceLicenceList=scmZsjCommerceFirst.getScmZsjCommerceLicenceList();
    	for(ScmZsjCommerceLicence scmZsjCommerceLicence:scmZsjCommerceLicenceList){
    		scmZsjCommerceLicence.setCommerceFirstId(pk);
    		scmZsjCommerceLicenceManager.create(scmZsjCommerceLicence);
    	}
    	
    	//商业营业执照信息
    	List<ScmZsjCommerceBusiness> scmZsjCommerceBusinessList=scmZsjCommerceFirst.getScmZsjCommerceBusinessList();
    	for(ScmZsjCommerceBusiness scmZsjCommerceBusiness:scmZsjCommerceBusinessList){
    		scmZsjCommerceBusiness.setCommerceFirstId(pk);
    		scmZsjCommerceBusinessManager.create(scmZsjCommerceBusiness);
    	}
    	
    	//商业医疗许可证
    	List<ScmZsjTreatLicence> scmZsjTreatLicenceList=scmZsjCommerceFirst.getScmZsjTreatLicenceList();
    	for(ScmZsjTreatLicence scmZsjTreatLicence:scmZsjTreatLicenceList){
    		scmZsjTreatLicence.setCommerceFirstId(pk);
    		scmZsjTreatLicenceManager.create(scmZsjTreatLicence);
    	}
    	
    	//商业GSP证书信息
    	List<ScmZsjGSP> scmZsjGSPList=scmZsjCommerceFirst.getScmZsjGSPList();
    	for(ScmZsjGSP scmZsjGSP:scmZsjGSPList){
    		scmZsjGSP.setCommerceFirstId(pk);
    		scmZsjGSPManager.create(scmZsjGSP);
    	}
    	
    	//商业承诺书
    	List<ScmZsjCommercePromiseBook> scmZsjCommercePromiseBookList=scmZsjCommerceFirst.getScmZsjCommercePromiseBookList();
    	for(ScmZsjCommercePromiseBook scmZsjCommercePromiseBook:scmZsjCommercePromiseBookList){
    		scmZsjCommercePromiseBook.setCommerceFirstId(pk);
    		scmZsjCommercePromiseBookManager.create(scmZsjCommercePromiseBook);
    	}
    	
    	//商业收货地址
    	List<ScmZsjCommerceShipAddress> scmZsjCommerceShipAddressList=scmZsjCommerceFirst.getScmZsjCommerceShipAddressList();
    	for(ScmZsjCommerceShipAddress scmZsjCommerceShipAddress:scmZsjCommerceShipAddressList){
    		scmZsjCommerceShipAddress.setCommerceFirstId(pk);
    		scmZsjCommerceShipAddressManager.create(scmZsjCommerceShipAddress);
    	}
    	
    	//商业委托书
    	List<ScmZsjCommerceEntruseBook> scmZsjCommerceEntruseBookList = scmZsjCommerceFirst.getScmZsjCommerceEntruseBookList();
    	for (ScmZsjCommerceEntruseBook scmZsjCommerceEntruseBook : scmZsjCommerceEntruseBookList) {    		
    		String entruseId=scmZsjCommerceEntruseBook.getId();
        	List<ScmZsjCommerceAccredit> scmZsjCommerceAccreditList=scmZsjCommerceEntruseBook.getScmZsjCommerceAccreditList();
        	for(ScmZsjCommerceAccredit scmZsjCommerceAccredit:scmZsjCommerceAccreditList){
        		scmZsjCommerceAccredit.setEntrustId(entruseId);
        		scmZsjCommerceAccreditManager.create(scmZsjCommerceAccredit);
        	}
        	scmZsjCommerceEntruseBook.setCommerceFirstId(pk);
        	scmZsjCommerceEntruseBookManager.create(scmZsjCommerceEntruseBook);
		}
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	scmZsjCommerceWarehouseDao.delByMainId(entityId);
    	scmZsjCommerceLicenceDao.delByMainId(entityId);
    	scmZsjCommerceBusinessDao.delByMainId(entityId);
    	scmZsjTreatLicenceDao.delByMainId(entityId);
    	scmZsjGSPDao.delByMainId(entityId);
    	scmZsjCommercePromiseBookDao.delByMainId(entityId);
    	scmZsjCommerceShipAddressDao.delByMainId(entityId);
	}
	
	/**
	 * 批量删除包含子表记录
	 */
	public void removeByIds(String[] entityIds){
		for(String id:entityIds){
			this.remove(id);
		}
	}
    
	/**
	 * 获取实体
	 */
    public ScmZsjCommerceFirst get(String entityId){
    	ScmZsjCommerceFirst scmZsjCommerceFirst=super.get(entityId);
    	if(scmZsjCommerceFirst == null) {
    		throw new RuntimeException("首营数据不存在或已经被删除");
    	}
    	ScmZsjCommerce scmZsjCommerce = scmZsjCommerceManager.get(scmZsjCommerceFirst.getCommerceId());
    	scmZsjCommerceFirst.setScmZsjCommerce(scmZsjCommerce);
    	//商业生产/仓库地址
    	List<ScmZsjCommerceWarehouse> scmZsjCommerceWarehouseList=scmZsjCommerceWarehouseDao.getByMainId(entityId);
    	scmZsjCommerceFirst.setScmZsjCommerceWarehouseList(scmZsjCommerceWarehouseList);
    	//商业许可证信息
    	List<ScmZsjCommerceLicence> scmZsjCommerceLicenceList=scmZsjCommerceLicenceDao.getByMainId(entityId);
    	scmZsjCommerceFirst.setScmZsjCommerceLicenceList(scmZsjCommerceLicenceList);
    	//商业营业执照信息
    	List<ScmZsjCommerceBusiness> scmZsjCommerceBusinessList=scmZsjCommerceBusinessDao.getByMainId(entityId);
    	scmZsjCommerceFirst.setScmZsjCommerceBusinessList(scmZsjCommerceBusinessList);
    	//商业医疗许可证
    	List<ScmZsjTreatLicence> scmZsjTreatLicenceList=scmZsjTreatLicenceDao.getByMainId(entityId);
    	scmZsjCommerceFirst.setScmZsjTreatLicenceList(scmZsjTreatLicenceList);
    	//商业GSP证书信息
    	List<ScmZsjGSP> scmZsjGSPList=scmZsjGSPDao.getByMainId(entityId);
    	scmZsjCommerceFirst.setScmZsjGSPList(scmZsjGSPList);
    	//商业承诺书
    	List<ScmZsjCommercePromiseBook> scmZsjCommercePromiseBookList=scmZsjCommercePromiseBookDao.getByMainId(entityId);
    	//获取商业承诺书详情
    	List<ScmZsjCommercePromiseBook> list = new ArrayList<ScmZsjCommercePromiseBook>();
    	for (ScmZsjCommercePromiseBook scmZsjCommercePromiseBook : scmZsjCommercePromiseBookList) {
			String promiseId = scmZsjCommercePromiseBook.getPromiseBookId();
			ScmZdPromiseBook scmZdPromiseBook = scmZdPromiseBookDao.get(promiseId);
			scmZsjCommercePromiseBook.setScmZdPromiseBook(scmZdPromiseBook);
			list.add(scmZsjCommercePromiseBook);
		}
    	scmZsjCommerceFirst.setScmZsjCommercePromiseBookList(list);
    	//商业收货地址
    	List<ScmZsjCommerceShipAddress> scmZsjCommerceShipAddressList=scmZsjCommerceShipAddressDao.getByMainId(entityId);
    	scmZsjCommerceFirst.setScmZsjCommerceShipAddressList(scmZsjCommerceShipAddressList);
    	//商业委托书
    	List<ScmZsjCommerceEntruseBook> scmZsjCommerceEntruseBookList = scmZsjCommerceEntruseBookDao.getByMainId(entityId);
    	List<ScmZsjCommerceEntruseBook> entruseList = new ArrayList<ScmZsjCommerceEntruseBook>();
    	for (ScmZsjCommerceEntruseBook scmZsjCommerceEntruseBook : scmZsjCommerceEntruseBookList) {
    		List<ScmZsjCommerceAccredit> scmZsjCommerceAccreditList=scmZsjCommerceAccreditDao.getScmZsjCommerceAccreditList(scmZsjCommerceEntruseBook.getId());
        	scmZsjCommerceEntruseBook.setScmZsjCommerceAccreditList(scmZsjCommerceAccreditList);
        	entruseList.add(scmZsjCommerceEntruseBook);
		}
    	scmZsjCommerceFirst.setScmZsjCommerceEntruseBookList(entruseList);
    	return scmZsjCommerceFirst;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(ScmZsjCommerceFirst scmZsjCommerceFirst){
    	ScmZsjCommerceFirst scmZsjCommerceFirst2 = scmZsjCommerceFirstDao.get(scmZsjCommerceFirst.getId());
    	if("1".equals(scmZsjCommerceFirst2.getApprovalState())) {
    		throw new RuntimeException("审批中数据不可修改");
    	}
    	scmZsjCommerceFirst.setApprovalState("0");
    	super.update(scmZsjCommerceFirst);
    	String id=scmZsjCommerceFirst.getId();
    	List<ScmZsjCommerceBusiness> byMainId = scmZsjCommerceBusinessDao.getByMainId(id);
    	List<ScmZsjCommerceLicence> byMainId2 = scmZsjCommerceLicenceDao.getByMainId(id);
    	List<ScmZsjTreatLicence> byMainId3 = scmZsjTreatLicenceDao.getByMainId(id);
    	List<ScmZsjCommerceLicence> scmZsjCommerceLicenceList=scmZsjCommerceFirst.getScmZsjCommerceLicenceList();
    	List<ScmZsjCommerceBusiness> scmZsjCommerceBusinessList=scmZsjCommerceFirst.getScmZsjCommerceBusinessList();
    	List<ScmZsjTreatLicence> scmZsjTreatLicenceList=scmZsjCommerceFirst.getScmZsjTreatLicenceList();
    	List<ScmZsjGSP> scmZsjGSPList=scmZsjCommerceFirst.getScmZsjGSPList();
    	//对比更新修改记录
    	try {
    		if(scmZsjCommerceLicenceList.size()>0){
    			//商业许可证信息
    	    	ScmZsjCommerceLicence scmZsjCommerceLicence2 = scmZsjCommerceLicenceList.get(0);
    			String contrast = ContrasUtil.Contrast(byMainId2.get(0), scmZsjCommerceLicence2, id, "syxg");
        		if(!StringUtils.isEmpty(contrast)){
        			scmMasterDateFeignService.inserScmLogRecord(contrast);
        		}
    		}
    		if(scmZsjCommerceBusinessList.size()>0){
    			//商业营业执照信息
    	    	ScmZsjCommerceBusiness scmZsjCommerceBusiness2 = scmZsjCommerceBusinessList.get(0);
    			String contrast2 = ContrasUtil.Contrast(byMainId.get(0), scmZsjCommerceBusiness2, id, "syxg");
        		if(!StringUtils.isEmpty(contrast2)){
        			scmMasterDateFeignService.inserScmLogRecord(contrast2);
        		}
    		}
    		if(scmZsjTreatLicenceList.size()>0){
    			//商业医疗许可证
    	    	ScmZsjTreatLicence scmZsjTreatLicence2 = scmZsjTreatLicenceList.get(0);
    			String contrast3 = ContrasUtil.Contrast(byMainId3.get(0), scmZsjTreatLicence2, id, "syxg");
        		if(!StringUtils.isEmpty(contrast3)){
        			scmMasterDateFeignService.inserScmLogRecord(contrast3);
        		}
    		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	//商业生产/仓库地址
    	scmZsjCommerceWarehouseDao.delByMainId(id);
    	List<ScmZsjCommerceWarehouse> scmZsjCommerceWarehouseList=scmZsjCommerceFirst.getScmZsjCommerceWarehouseList();
    	for(ScmZsjCommerceWarehouse scmZsjCommerceWarehouse:scmZsjCommerceWarehouseList){
    		scmZsjCommerceWarehouse.setCommerceFirstId(id);
    		scmZsjCommerceWarehouseManager.create(scmZsjCommerceWarehouse);
    	}
    	//商业许可证信息
    	scmZsjCommerceLicenceDao.delByMainId(id);
    	for(ScmZsjCommerceLicence scmZsjCommerceLicence:scmZsjCommerceLicenceList){
    		scmZsjCommerceLicence.setCommerceFirstId(id);
    		scmZsjCommerceLicenceManager.create(scmZsjCommerceLicence);
    	}
    	//商业营业执照信息
    	scmZsjCommerceBusinessDao.delByMainId(id);

    	for(ScmZsjCommerceBusiness scmZsjCommerceBusiness:scmZsjCommerceBusinessList){
    		scmZsjCommerceBusiness.setCommerceFirstId(id);
    		scmZsjCommerceBusinessManager.create(scmZsjCommerceBusiness);
    	}
    	//商业医疗许可证
    	scmZsjTreatLicenceDao.delByMainId(id);

    	for(ScmZsjTreatLicence scmZsjTreatLicence:scmZsjTreatLicenceList){
    		scmZsjTreatLicence.setCommerceFirstId(id);
    		scmZsjTreatLicenceManager.create(scmZsjTreatLicence);
    	}
    	//商业GSP证书信息
    	scmZsjGSPDao.delByMainId(id);

    	for(ScmZsjGSP scmZsjGSP:scmZsjGSPList){
    		scmZsjGSP.setCommerceFirstId(id);
    		scmZsjGSPManager.create(scmZsjGSP);
    	}
    	//商业承诺书
    	scmZsjCommercePromiseBookDao.delByMainId(id);
    	List<ScmZsjCommercePromiseBook> scmZsjCommercePromiseBookList=scmZsjCommerceFirst.getScmZsjCommercePromiseBookList();
    	for(ScmZsjCommercePromiseBook scmZsjCommercePromiseBook:scmZsjCommercePromiseBookList){
    		scmZsjCommercePromiseBook.setCommerceFirstId(id);
    		scmZsjCommercePromiseBookManager.create(scmZsjCommercePromiseBook);
    	}
    	//商业收货地址
    	scmZsjCommerceShipAddressDao.delByMainId(id);
    	List<ScmZsjCommerceShipAddress> scmZsjCommerceShipAddressList=scmZsjCommerceFirst.getScmZsjCommerceShipAddressList();
    	for(ScmZsjCommerceShipAddress scmZsjCommerceShipAddress:scmZsjCommerceShipAddressList){
    		scmZsjCommerceShipAddress.setCommerceFirstId(id);
    		scmZsjCommerceShipAddressManager.create(scmZsjCommerceShipAddress);
    	}
    	//商业委托书,先删除商业授权销售品种再删
    	List<ScmZsjCommerceEntruseBook> scmZsjCommerceEntruseBookList = scmZsjCommerceEntruseBookDao.getByMainId(id);
        for (ScmZsjCommerceEntruseBook scmZsjCommerceEntruseBook : scmZsjCommerceEntruseBookList) {
			String entrustId = scmZsjCommerceEntruseBook.getId();
			scmZsjCommerceAccreditDao.delByMainId(entrustId);
		}
        scmZsjCommerceEntruseBookDao.delByMainId(id);
        //保存新数据
        List<ScmZsjCommerceEntruseBook> entruseList = scmZsjCommerceFirst.getScmZsjCommerceEntruseBookList();
        for (ScmZsjCommerceEntruseBook scmZsjCommerceEntruseBook : entruseList) {
        	String entruseId=scmZsjCommerceEntruseBook.getId();        	
        	List<ScmZsjCommerceAccredit> scmZsjCommerceAccreditList=scmZsjCommerceEntruseBook.getScmZsjCommerceAccreditList();        	
        	for(ScmZsjCommerceAccredit scmZsjCommerceAccredit:scmZsjCommerceAccreditList){
        		scmZsjCommerceAccredit.setEntrustId(entruseId);
        		scmZsjCommerceAccreditManager.create(scmZsjCommerceAccredit);
        	}
        	scmZsjCommerceEntruseBook.setCommerceFirstId(id);
        	scmZsjCommerceEntruseBookManager.create(scmZsjCommerceEntruseBook);
		}
    }
	@Override
	public void sendApply(ScmZsjCommerceFirst scmZsjCommerceFirst) {
		//审批状态    0:待提交,1:待审批,2:已通过
		//判断提交的商业信息是否存在,如果不存在,在基础数据保存一份
		scmZsjCommerceManager.isExist(scmZsjCommerceFirst.getScmZsjCommerce());	

		scmZsjCommerceFirst.setCommerceId(scmZsjCommerceFirst.getScmZsjCommerce().getId());
		//判断当前货主下是否已经首营过
		List<ScmZsjCommerceFirst> commerceFirstByOwnerIdAndCommerceId = scmZsjCommerceFirstDao.getCommerceFirstByOwnerIdAndCommerceId(scmZsjCommerceFirst);
		if(commerceFirstByOwnerIdAndCommerceId.size() != 0) {
			throw new RuntimeException("当前商业已经发起审批或审批已经通过,请勿重复提交");
		}
		//调整审批状态
		scmZsjCommerceFirst.setApprovalState("1");
		create(scmZsjCommerceFirst);
	}
	@Override
	public void isSave(ScmZsjCommerceFirst scmZsjCommerceFirst) {
		List<ScmZsjCommerceFirst> scmZsjCommerceFirsts =  scmZsjCommerceFirstDao.isSave(scmZsjCommerceFirst);
		if(scmZsjCommerceFirsts.size() > 0) {
			throw new RuntimeException("当前商业首营信息已暂存或已经发起申请,请勿重复提交");
		}
	}
	@Override
	public ScmZsjCommerceFirst getCommerceFirstByApprovalId(String approvalId) {
		ScmZsjCommerceFirst scmZsjCommerceFirst = scmZsjCommerceFirstDao.getCommerceFirstByApprovalId(approvalId);
		if(scmZsjCommerceFirst == null) {
			throw new RuntimeException("查询数据不存在或已经被删除");
		}
		ScmZsjCommerceFirst scmZsjCommerceFirst2 = get(scmZsjCommerceFirst.getId());
		return scmZsjCommerceFirst2;
	}
	@Override
	public void endApply(JsonNode jsonNode) {
    	String approvalId = jsonNode.get("instId").asText();
		ScmZsjCommerceFirst productFirstByApprovalId = scmZsjCommerceFirstDao.getCommerceFirstByApprovalId(approvalId);
		if(productFirstByApprovalId == null) {
			throw new RuntimeException("未查询到业务数据,处理异常");
		}
		//审批状态调整为通过
		productFirstByApprovalId.setApprovalState("2");
		scmZsjCommerceFirstDao.update(productFirstByApprovalId);
	}
	@Override
	public void updateSendApply(ScmZsjCommerceFirst scmZsjCommerceFirst) {
		ScmZsjCommerceFirst scmZsjCommerceFirst2 = scmZsjCommerceFirstDao.get(scmZsjCommerceFirst.getId());
		if("1".equals(scmZsjCommerceFirst2.getApprovalState())){
			//数据在审批中,不可修改编辑
			throw new RuntimeException("当前数据已经在审批中,不可重复提交");
		}else{
			//修改其他关联性数据
			update(scmZsjCommerceFirst);
			scmZsjCommerceFirst.setApprovalState("1");
			scmZsjCommerceFirstDao.update(scmZsjCommerceFirst);
			
		}
	}
	
}
