package org.example.common.aop.aspect;

import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.common.aop.annotation.OperationLog;
import org.example.common.aop.service.SysLogService;
import org.example.common.aop.vo.LogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Arrays;

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
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 配置总的切面路径规则
     */
    @Pointcut("@annotation(org.example.common.aop.annotation.OperationLog)")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) throws SQLException, IOException, ClassNotFoundException {
        /*1、设置请求开始时间*/
        startTime.set(System.currentTimeMillis());
        /*2、获取request对象*/
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        /*3、从切面织入点处通过反射机制获取织入点处的方法*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        /*日志参数记入数据库*/
        LogVO logVO = new LogVO();
        logVO.setClientIp(request.getRemoteAddr());
        /*用户名*/
        logVO.setUsername(System.getProperty("user.name"));
        /*获取注解在方法上的配置值*/
        OperationLog annotation = method.getAnnotation(OperationLog.class);
        if (null != annotation) {
            //保存日志类型
            long operType = annotation.operType();
            logVO.setOperType(operType);
            //保存操作事件
            String operEvent = annotation.operEvent();
            logVO.setOperEvent(operEvent);
        }
        /*请求地址*/
        logVO.setOperUrl(request.getRequestURL().toString());
        /*请求参数信息*/
        logVO.setReqParam(Arrays.toString(joinPoint.getArgs()));
        /*请求方法类型*/
        logVO.setReqType(request.getMethod());
        sysLogService.saveLog(logVO);

        //打印请求内容
        log.info("==================[Before]请求内容start==================");
        log.info("==================[Before]请求地址==================\n{}", request.getRequestURL().toString());
        log.info("==================[Before]请求方式==================\n{}", request.getMethod());
        log.info("==================[Before]请求类方法==================\n{}", joinPoint.getSignature());
        log.info("==================[Before]请求类方法参数==================\n{}", Arrays.toString(joinPoint.getArgs()));
        log.info("==================[Before]请求内容end==================");
    }

    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("==================[Around]方法环绕前置start==================");
        //这句必须有 往下执行方法
        Object result = joinPoint.proceed();
        log.info("==================[Around]方法环绕后置start==================");
        log.info("==================[Around]方法环绕end==================");
        return result;
    }

    @After(value = "pointCut()")
    public void after() {
        log.info("==================[After]切点方法执行后==================");
    }

    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().getName();
        log.error("==================[AfterThrowingMethod]==================\n切点方法{}抛出异常:{}", methodName, ex.getMessage());
    }


    @AfterReturning(value = "pointCut()", returning = "o")
    public void afterReturn(Object o) {
        log.info("==================[AfterReturn]==================\nResponse内容:{}", JSONUtil.toJsonStr(o));
        log.info("==================[AfterReturn]==================\n请求消耗总时间:{}ms", (System.currentTimeMillis() - startTime.get()));
    }
}