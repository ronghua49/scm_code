package com.winway.scm.persistence.manager;

import java.util.List;
import java.util.Map;

import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmCwInvoice;
import com.winway.scm.vo.VInvoice;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <pre> 
 * 描述：商业发票表 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-03-23 18:19:25
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmCwInvoiceManager extends Manager<String, ScmCwInvoice>{

	PageList<ScmCwInvoice> findAll(QueryFilter queryFilter);

	PageList<ScmCwInvoice> affirmData(String[] ids);

	PageList<VInvoice> history(QueryFilter queryFilter);

    void export(QueryFilter queryFilter, boolean b, HttpServletResponse response);

    PageList<Map<String, Object>> sumList(QueryFilter queryFilter);

	void export2(QueryFilter queryFilter, boolean b, HttpServletResponse response);

    PageList<ScmCwInvoice> checklist(QueryFilter queryFilter);

	void export3(QueryFilter queryFilter, boolean b, HttpServletResponse response);

	boolean verifExceedTime(String commerceCode);

    String readExcelFile(MultipartFile file, ScmCwInvoice invoice);
}
