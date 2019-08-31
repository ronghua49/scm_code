package com.winway.purchase.log;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotent.base.feign.UCFeignService;
import com.hotent.base.util.BeanUtils;
import com.hotent.base.util.JsonUtil;
import com.hotent.base.util.StringUtil;
import com.winway.purchase.model.ScmSysLog;
import com.winway.purchase.persistence.manager.ScmSysLogManager;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {
	@Resource
	UCFeignService ucFeignService;

	@Resource
	ScmSysLogManager scmSysLogManager;

	@Pointcut("execution(* *..*Controller.*(..)) && @annotation(io.swagger.annotations.ApiOperation)")
	public void cutPoint() {
	}

	@Around("cutPoint()")
	public Object sysLogs(ProceedingJoinPoint joinPoint) throws Throwable {
		String account = null;
		String responseState = "请求成功";
		Method method = null;
		ScmSysLog scmSysLog = new ScmSysLog();
		scmSysLog.setCreateDate(new Date());
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				UserDetails ud = (UserDetails) principal;
				account = ud.getUsername();
			}
			JsonNode user = ucFeignService.getUser(account, "");
			String fullname = user.get("fullname").asText();
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();

			scmSysLog.setLoginUser(fullname + "| " + request.getRemoteUser());

			scmSysLog.setIp(request.getRemoteAddr());

			Class<?> targetClass = joinPoint.getTarget().getClass();
			String methodName = joinPoint.getSignature().getName();
			Method[] methods = targetClass.getMethods();

			for (int i = 0; i < methods.length; ++i) {
				if (methods[i].getName() == methodName) {
					method = methods[i];
					break;
				}
			}
			scmSysLog.setMethod(methodName);
			ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
			// 当前请求的描述
			String opeName = apiOperation.value();
			if (StringUtil.isEmpty(opeName)) {
				opeName = apiOperation.notes();
			}
			scmSysLog.setOperation(opeName);
			Object[] args = joinPoint.getArgs();
			// 当前请求方法的 入参 json 字符串
			StringBuffer sb = new StringBuffer();
			if (BeanUtils.isNotEmpty(args)) {
				Object[] var14 = args;
				int var15 = args.length;
				for (int var16 = 0; var16 < var15; ++var16) {
					Object object = var14[var16];
					try {
						String json;
						try {
							json = JsonUtil.toJson(object);
						} catch (Exception e) {
							if(object == null) {
								json = "";
							}else{
								json = object.toString();
							}
						}
						sb.append(json);
					} catch (Exception var19) {
						var19.printStackTrace();
						sb.append(object.toString());
					}
				}
			}
			scmSysLog.setParams(sb.toString());
		} catch (Exception e) {
			scmSysLog.setResponseState("获取日志信息异常");
			scmSysLog.setResult(e.getMessage());
			create(scmSysLog);
		}

		// 获取执行结果
		Object object = null;
		try {
			long l = System.currentTimeMillis();
			object = joinPoint.proceed();
			long l1 = System.currentTimeMillis();
			
			try{
				scmSysLog.setTotalTime((l1 - l));
				scmSysLog.setResponseState(responseState);
				scmSysLog.setResult(JsonUtil.toJson(object));
			}catch(Exception e){
				if(object == null) {
					scmSysLog.setResult("");
				}else{
					scmSysLog.setResult(object.toString());
				}
			}
			create(scmSysLog);
			
		} catch (Throwable throwable) {
			responseState = "请求异常";
			scmSysLog.setExceptionMes(throwable.getMessage());
			scmSysLog.setResponseState(responseState);
			create(scmSysLog);
			throw throwable;
		}
		return object;
	}

	private void create(ScmSysLog scmSysLog) {
		try {
			scmSysLogManager.create(scmSysLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
