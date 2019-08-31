package com.winway.scm.persistence.manager;

import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import org.springframework.web.multipart.MultipartFile;

import com.hotent.base.manager.Manager;
import com.winway.scm.model.ScmCwReturnMoney;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：商业回款表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwReturnMoneyManager extends Manager<String, ScmCwReturnMoney>{

	String readExcelFile(MultipartFile file, ScmCwReturnMoney product);

    void downloadExcel(HttpServletResponse response);

	void affirmData(String[] ids, String fullname);

	void updatePrice(String fullname, String id, String updatePrice, String memo);

	PageList<Map<String,Object>> sumList(QueryFilter queryFilter);
}
