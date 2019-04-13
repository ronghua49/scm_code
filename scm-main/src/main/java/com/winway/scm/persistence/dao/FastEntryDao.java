package com.winway.scm.persistence.dao;

import java.util.List;

import com.hotent.base.dao.MyBatisDao;
import com.winway.scm.model.FastEntryData;
/**
 * 
* @Description: FMS快速入口Dao
* @author: 贺军
* @date: 2018年10月15日 下午6:08:37 
* @history
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年10月15日     	贺军         			v1.0.0            	 新增
 */
public interface FastEntryDao extends MyBatisDao<String, FastEntryData> {

	List<FastEntryData> queryFastEntryByAccount(String account);

	void deleteByAccount(String account);

	void saveAll(List<FastEntryData> list);
	
}
