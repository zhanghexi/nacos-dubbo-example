package org.example.common.log.aspect;

import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.common.log.annotation.OperationLog;
import org.example.common.log.service.SysLogService;
import org.example.common.log.util.WebUtils;
import org.example.common.log.vo.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @ClassName LogAspect
 * @User zhang
 * @Description 切面配置类
 * @Author Lucien
 * @Date 2020/10/27 0:43
 * @Version 1.0
 */
@Component
@Aspect
@Log4j2
public class LogAspect {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 配置总的切面路径规则
     */
    @Pointcut("@annotation(org.example.common.log.annotation.OperationLog)")
    public void operationLog() {
    }

    /**
     * 计算方法执行时间
     *
     * @param joinPoint
     * @return
     */
    @Around("operationLog()")
    public Object countMethodExecutionTime(ProceedingJoinPoint joinPoint) {
        log.info("=========================方法开始执行=========================");
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            /*调用 proceed() 方法才会真正的执行实际被代理的方法*/
            result = joinPoint.proceed();
            long endTime = System.currentTimeMillis() - startTime;
            log.info("方法执行时间:" + endTime + "毫秒");
            log.info("=========================方法执行结束=========================");
        } catch (Throwable throwable) {
            log.error("计算方法执行时间:", throwable.getMessage());
        }
        return result;
    }

    /**
     * 配置后置返回通知
     *
     * @param joinPoint
     */
    @AfterReturning(pointcut = "operationLog()", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        /*调用打印日志的方法*/
        invokePrintLog(joinPoint, null, result);
    }

    /**
     * 记录异常信息
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "operationLog()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        invokePrintLog(joinPoint, e, null);
    }

    /**
     * 日志打印，存入数据库
     *
     * @param joinPoint
     * @param ex
     * @param result
     */
    private void invokePrintLog(JoinPoint joinPoint, Exception ex, Object result) {
        try {
            /*1、拼接请求参数*/
            String params = "";
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
                for (int i = 0; i < joinPoint.getArgs().length; i++) {
                    params += JSONUtil.toJsonStr(joinPoint.getArgs()[i]) + ";";
                    if (i == joinPoint.getArgs().length - 1) {
                        /*最后一个分号去掉*/
                        params = params.substring(0, params.length() - 1);
                    }
                }
            }
            /*2、获取日志参数*/
            String className = joinPoint.getTarget().getClass().getName();
            String requestMethod = joinPoint.getSignature().getName();
            String operationType = getAnnotationLog(joinPoint).operationType().getType();
            String operationName = getAnnotationLog(joinPoint).operationName();
            String createdBy = System.getProperty("user.name");
            String requestAddress = WebUtils.getRequest().getServerName();
            String requestIp = ServletUtil.getClientIP(WebUtils.getRequest());
            int requestPort = WebUtils.getRequest().getServerPort();

            String requestURI = WebUtils.getRequest().getRequestURI();
            String requestPath = requestURI.substring(0, requestURI.lastIndexOf("/"));
            String requestType = WebUtils.getRequest().getMethod();
            /*3、输出日志参数*/
            log.info("=========================输出日志参数=========================");
            log.info("请求类:" + className);
            log.info("请求方法:" + requestMethod);
            log.info("方法描述:" + operationName);
            log.info("方法类型:" + operationType);
            log.info("请求人:" + createdBy);
            log.info("请求地址:" + requestAddress);
            log.info("请求IP:" + requestIp);
            log.info("请求端口:" + requestPort);
            log.info("请求路径:" + requestPath);
            log.info("请求方式:" + requestType);
            log.info("请求参数:" + params);
            log.info("返回值:" + JSONUtil.toJsonStr(result));
            /*存入数据库*/
            SystemLog systemLog = new SystemLog();
            systemLog.setId(UUID.randomUUID().toString());
            systemLog.setClassName(className);
            systemLog.setRequestMethod(requestMethod);
            systemLog.setDescription(operationName);
            systemLog.setMethodType(operationType);
            systemLog.setCreatedBy(createdBy);
            systemLog.setRequestAddress(requestAddress);
            systemLog.setRequestIp(requestIp);
            systemLog.setRequestPort(requestPort);
            systemLog.setRequestPath(requestPath);
            systemLog.setRequestType(requestType);
            systemLog.setRequestParams(params);
            sysLogService.saveLog(systemLog);
        } catch (Exception e) {
            log.error("记录日志异常:" + e.getMessage());
        }
    }

    /**
     * 是否存在注解
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private OperationLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(OperationLog.class);
        }
        return null;
    }
}