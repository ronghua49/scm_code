package com.winway.purchase.persistence.manager.impl;



import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hotent.base.exception.BaseException;
import com.hotent.base.feign.BpmRuntimeFeignService;
import com.hotent.base.model.BaseModel;
import com.hotent.base.modelvo.StartFlowParam;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.hotent.base.util.UniqueIdUtil;


@Component
public class WorkflowTemplate {

	@Autowired
	BpmRuntimeFeignService bpmRuntimeFeign;

	/**
	 * 启动工作流
	 * @param flowKey 流程定义key
	 * @param sysCode 业务系统编码
	 * @param instanceIdField 实例ID回填到实体类的哪个字段
	 * @param entity 业务实体
	 * @param varKeys 流程变量keys集合
	 * @throws Exception 
	 */
	public void startWorkflow(String flowKey, String sysCode, String instanceIdField, BaseModel entity, String... varKeys) throws Exception {
		if (BeanUtils.isEmpty(entity)) {
			throw new BaseException("业务实体不能为空");
		}
		if (StringUtil.isEmpty(instanceIdField)) {
			throw new BaseException("工作流参数instanceIdField不能为空");
		}
		if (entity.getId().toString().isEmpty()) {
			/*throw new BaseException("业务实体必须有主键");
		} else if (StringUtil.isEmpty(entity.obtainStringPK())) {*/
			entity.setId(UniqueIdUtil.getSuid());
		}
		Map<String, String> varsMap = null;
		// 构建vars流程变量
		if (BeanUtils.isNotEmpty(varKeys) && varKeys.length > 0) {
			varsMap = new HashMap<>(varKeys.length);
			JsonNode entityJsonNode = null;
			try {
				entityJsonNode = JsonUtil.toJsonNode(entity);
			} catch (IOException e) {
				throw new BaseException("流程业务实体json序列化异常");
			}
			for (String key : varKeys) {
				JsonNode jsonNode = entityJsonNode.get(key);
				varsMap.put(key, jsonNode == null ? null : jsonNode.toString());
			}
		} else {
			varsMap = Collections.emptyMap();
		}
		ObjectNode startResultVO = this.startWorkflow(flowKey, sysCode, entity.getId().toString(), varsMap);
		// 将流程实例ID回填到实体对象中
		BeanUtils.setProperty(entity, instanceIdField, startResultVO.get("instId"));
	}

	/**
	 * 启动工作流
	 * @param flowKey 流程定义key
	 * @param sysCode 业务系统编码
	 * @param entity 业务实体
	 * @param vars 流程变量
	 * @throws Exception 
	 * @throws Throwable
	 */
	public ObjectNode startWorkflow(String flowKey, String sysCode, String pk, Map<String, String> vars) throws Exception {
		if (StringUtil.isEmpty(flowKey) || StringUtil.isEmpty(sysCode)) {
			throw new BaseException("工作流参数flowKey, sysCode不能为空");
		}

		StartFlowParam startFlowParamObject = new StartFlowParam(flowKey, sysCode, pk);
		startFlowParamObject.setFormType("frame");
		startFlowParamObject.setVars(vars);
		ObjectMapper mapper = new ObjectMapper(); 
		String json = mapper.writeValueAsString(startFlowParamObject); 
		ObjectNode  on = (ObjectNode) new ObjectMapper().readTree(json);
		// 调用接口启动流程		
		ObjectNode startResultVO = bpmRuntimeFeign.start(on);
		    
		if (BeanUtils.isNotEmpty(startResultVO) && startResultVO.get("state") != null ) {
			return startResultVO;
		} else {
			String message = "流程启动失败";
			if (BeanUtils.isNotEmpty(startResultVO) && BeanUtils.isNotEmpty(startResultVO.get("message"))) {
				message += ":" + startResultVO.get("message");
			}
			throw new BaseException(message);
		}
	}
}
