package com.winway.scm.persistence.manager.impl;

import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.winway.scm.model.ScmCgProcurementPlan;
import com.winway.scm.persistence.dao.ScmCgProcurementPlanDao;
import com.winway.scm.persistence.manager.ScmCgProcurementPlanManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <pre> 
 * 描述：采购计划表 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2018-12-26 13:47:44
 * 版权：讯宜捷科技有限公司
 * </pre>
 */
@Service("scmCgProcurementPlanManager")
public class ScmCgProcurementPlanManagerImpl extends AbstractManagerImpl<String, ScmCgProcurementPlan> implements ScmCgProcurementPlanManager{
	@Resource
	ScmCgProcurementPlanDao scmCgProcurementPlanDao;
	@Override
	protected MyBatisDao<String, ScmCgProcurementPlan> getDao() {
		return scmCgProcurementPlanDao;
	}
	@Override
	public void isRemove(String id) {
		// TODO Auto-generated method stub
		ScmCgProcurementPlan scmCgProcurementPlan = scmCgProcurementPlanDao.get(id);
		if ("0".equals(scmCgProcurementPlan.getIsAffirm())) {
			scmCgProcurementPlanDao.remove(id);
		}else{
			new CommonResult<>("数据已确认，无法删除");
		}
		
	}
	@Override
	public void isRemoveByIds(String[] ids) {
		// TODO Auto-generated method stub
		if (ids != null) {
			for(String id : ids){
				ScmCgProcurementPlan scmCgProcurementPlan = scmCgProcurementPlanDao.get(id);
				if ("1".equals(scmCgProcurementPlan.getIsAffirm())) {
					new CommonResult<>("所选数据中存在已确认数据，无法删除");
					return;
				}
			}
			for(String id : ids){
				scmCgProcurementPlanDao.remove(id);
			}
		}
	}
	@Override
	public void toLoad(String filePath, HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		String Path = request.getSession().getServletContext().getRealPath(filePath);
		
		File file = null;
		FileInputStream fs = null;
		List<ScmCgProcurementPlan> list = new ArrayList<ScmCgProcurementPlan>();
		try {
			file = new File(Path);
			fs = new FileInputStream(file);
		} catch (Exception e) {
			throw new RuntimeException("读取文件失败");
		}
		String fileNameAll = file.getName();
		String [] split = fileNameAll.split("\\.");
		if (split.length != 2) {
			throw new RuntimeException("文件名格式错误");
		}
		String formateStr = split[1];
		//读取文件
		if ("xls".equals(formateStr)) {
			// 是 xls 文件 用HSS		
		}else if ("xlsx".equals(formateStr)) {
			// 如果是 xlsx 用XSS
		}
	}
}
