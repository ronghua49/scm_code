package com.winway.scm.persistence.manager;

import java.util.List;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.FastEntryData;
import com.winway.scm.model.FastEntryVo;

/**
 * 
* @Description: FMS 快速入口manager接口类
* @author: 贺军
* @date: 2018年10月15日 下午6:07:25 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年10月15日     	贺军         			v1.0.0            	 修改原因
 */
public interface FastEntryManager  extends Manager<String, FastEntryData> {
	/**
	 * 
	* @Description: 根据账号查询用户的快速入口设置
	* @param: account
	* @return：List
	* @author: 贺军
	* @date: 2018年10月17日 下午1:53:21 
	 */
	List<FastEntryData> queryFastEntryByAccount(String account) throws Exception;
	/**
	 * 
	* @Description: 保存所有的设置
	* @param:List
	* @author: 贺军
	* @date: 2018年10月17日 下午1:53:12 
	 */
	void saveAll(FastEntryVo paramData);
	
}
