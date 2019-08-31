package com.winway.scm.persistence.manager;

import com.hotent.base.manager.Manager;
import com.hotent.base.query.PageList;
import com.hotent.base.query.QueryFilter;
import com.winway.scm.model.ScmKcWeekInventory;
import com.winway.scm.vo.ScmKcMonthInventory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre> 
 * 描述：scm_kc_weekinventory 处理接口
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-07-08 15:15:30
 * 版权：美达开发小组
 * </pre>
 */
public interface ScmKcWeekInventoryManager extends Manager<String, ScmKcWeekInventory>{

	PageList<ScmKcMonthInventory> monthList(QueryFilter queryFilter);


    List<Map<String, Object>>   base() throws ParseException;



    Map<String,String> readExcelFile(MultipartFile file, ScmKcWeekInventory weekInventory) throws IOException;
}
