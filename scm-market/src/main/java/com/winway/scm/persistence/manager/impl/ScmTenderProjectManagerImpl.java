package com.winway.scm.persistence.manager.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.dao.MyBatisDao;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.base.model.CommonResult;
import com.hotent.base.model.WinwayResult;
import com.hotent.base.modelvo.AgreeFlowParam;
import com.hotent.base.modelvo.CustomStartResult;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.query.FieldSort;
import com.hotent.base.query.QueryFilter;
import com.hotent.base.util.BeanUtils;
import com.winway.scm.model.ScmTenderPrice;
import com.winway.scm.model.ScmTenderProject;
import com.winway.scm.persistence.dao.ScmTenderPriceDao;
import com.winway.scm.persistence.dao.ScmTenderProjectDao;
import com.winway.scm.persistence.manager.ScmTenderProjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <pre>
 * 描述：招标项目管理 处理实现类
 * 构建组：x7
 * 作者:原浩
 * 邮箱:PRD-jun.he@winwayworld.com
 * 日期:2019-06-25 11:09:05
 * 版权：美达开发小组
 * </pre>
 */
@Service("scmTenderProjectManager")
public class ScmTenderProjectManagerImpl extends AbstractManagerImpl<String, ScmTenderProject> implements ScmTenderProjectManager {

    private Logger logger = LoggerFactory.getLogger(ScmTenderProjectManagerImpl.class);

    @Resource
    ScmTenderProjectDao scmTenderProjectDao;

    @Resource
    ScmTenderPriceDao scmTenderPriceDao;

    @Resource
    BpmRuntimeFeignService bpmRuntimeFeignService;

    @Override
    protected MyBatisDao<String, ScmTenderProject> getDao() {
        return scmTenderProjectDao;
    }

    /**
     * 创建实体包含子表实体
     *
     * @Author FangGan
     * @Date 2019/8/15 17:40
     **/
    public void create(ScmTenderProject scmTenderProject) {
        // 根据项目编号倒序排序，来获取当前最大编码
        QueryFilter queryFilter = QueryFilter.build().withDefaultPage()
                .withSorter(FieldSort.create("tender_project_code", "DESC"));
        List<ScmTenderProject> query = scmTenderProjectDao.query(queryFilter.getParams());

        String projectCode = "30000";// 默认起始项目编号
        if (BeanUtils.isNotEmpty(query)) {
            // 获取最后一个项目编号
            String lastProjectCodeStr = query.get(0).getTenderProjectCode();
            int lastProjectCode = Integer.parseInt(lastProjectCodeStr);
            // 编号大于30000则加1并转为字符串，否则置为30000
            projectCode = String.valueOf(lastProjectCode < 30000 ? projectCode : (lastProjectCode + 1));
        }
        scmTenderProject.setTenderProjectCode(projectCode);

        // 发起工作流
        StartFlowParam flowParam = new StartFlowParam("zbxmsq", "SCM", "flowId");
        flowParam.setFormType("frame");

        try {
            // 启动工作流
            CustomStartResult customStartResult = bpmRuntimeFeignService.customStart(flowParam);
            if (customStartResult.getState()) {
                // 工作流启动成功，则获取工作流Id，并配置记录申请状态
                String instId = customStartResult.getInstId();
                scmTenderProject.setFlowId(instId);
                scmTenderProject.setStatus(0);
                scmTenderProject.setFlowStatus(2);

                super.create(scmTenderProject);

                // 工作流启动成功记录申请详细信息
                String pk = scmTenderProject.getId();

                // the fl
                List<ScmTenderPrice> scmTenderPriceList = scmTenderProject.getScmTenderPriceList();

                for (ScmTenderPrice scmTenderPrice : scmTenderPriceList) {
                    scmTenderPrice.setTenderProjectCode(pk);
                    scmTenderPriceDao.create(scmTenderPrice);
                }
            }
        } catch (Exception e) {
            logger.debug(String.valueOf(e));
        }
    }

    /**
     * 删除记录包含子表记录
     */
    public void remove(String entityId) {
        super.remove(entityId);
        scmTenderPriceDao.delByMainId(entityId);
    }

    /**
     * 批量删除包含子表记录
     */
    public void removeByIds(String[] entityIds) {
        for (String id : entityIds) {
            this.remove(id);
        }
    }

    /**
     * 获取实体
     */
    public ScmTenderProject get(String entityId) {
        ScmTenderProject scmTenderProject = super.get(entityId);
        List<ScmTenderPrice> scmTenderPriceList = scmTenderPriceDao.getByMainId(entityId);
        if (scmTenderProject != null) {
            scmTenderProject.setScmTenderPriceList(scmTenderPriceList);
        }
        return scmTenderProject;
    }

    /**
     * 更新关联子表记录
     *
     * @param scmTenderProject
     */
    public void update(ScmTenderProject scmTenderProject) {
        super.update(scmTenderProject);
        String id = scmTenderProject.getId();
        scmTenderPriceDao.delByMainId(id);
        List<ScmTenderPrice> scmTenderPriceList = scmTenderProject.getScmTenderPriceList();
        for (ScmTenderPrice scmTenderPrice : scmTenderPriceList) {
            scmTenderPrice.setTenderProjectCode(id);
            scmTenderPriceDao.create(scmTenderPrice);
        }
    }

    /**
     * 根据流程Id获取招标详情
     *
     * @param flowId
     * @return
     * @throws Exception
     *
     * @Author FangGan
     * @Date 2019/8/15 16:41
     */
    @Override
    public ScmTenderProject getByFlowId(String flowId) throws Exception {

        return scmTenderProjectDao.getByFlowId(flowId);
    }

    /**
     * 招标项目审批
     *
     * @param scmTenderProject
     * @param actionName
     * @param opinion
     * @throws Exception
     *
     * @Author FangGan
     * @Date 2019/8/15 16:41
     */
    @Override
    public void approval(ScmTenderProject scmTenderProject, String actionName, String opinion) throws Exception {
        try {
            // 获取TaskID
            WinwayResult result = bpmRuntimeFeignService.getTaskIdByInstId(scmTenderProject.getFlowId());
            String taskId = "";
            if ("success".equals(result.getShortMessage())) {
                // 返回结果成功时，配置TaskId，并使用TaskId和FlowId请求获取审批历史数据
                taskId = result.getCode();
            }
            AgreeFlowParam agreeFlowParam = new AgreeFlowParam(opinion, actionName, taskId, "free", "", "");
            bpmRuntimeFeignService.autoAgree(agreeFlowParam);

        } catch (Exception e) {
            logger.debug(String.valueOf(e));
        }
    }

    /**
     * 审批回调，修改审批状态
     *
     * @Param jsonNode
     * @return
     *
     * @Author FangGan
     * @Date 2019/8/15 16:41
     **/
    @Override
    public void endApply(JsonNode jsonNode) throws Exception {
        String flowId = jsonNode.get("instId").asText();
        String actionName = jsonNode.get("actionName").asText();

        ScmTenderProject scmTenderProject = this.getByFlowId(flowId);
        if (scmTenderProject == null) {
            throw new RuntimeException("未查询到业务数据,处理异常");
        }

        // 根据返回状态判断是否修改招标项目数据
        // agree则修改状态为1，reject则修改为-1
        String endEvent = jsonNode.get("eventType").asText();
        if ("agree".equals(actionName) && "endEvent".equals(endEvent)) {
            scmTenderProject.setStatus(1);
            scmTenderProject.setFlowStatus(1);
        } else {
            scmTenderProject.setStatus(-1);
            scmTenderProject.setFlowStatus(-1);
        }
        super.update(scmTenderProject);
    }
}
