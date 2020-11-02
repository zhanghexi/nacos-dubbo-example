package org.example.common.log.annotation;

import org.example.common.log.enums.OperationTypeEnum;

import java.lang.annotation.*;

/**
 * @ClassName OperationLog
 * @User zhang
 * @Description 记录日志的注解
 * @Author Lucien
 * @Date 2020/10/27 0:45
 * @Version 1.0
 */
@Target({ElementType.METHOD})//注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface OperationLog {

    /* 操作事件(在注解上实际添加的方法描述)*/
    String operationName() default "";

    /*方法类型，主要是select,insert,update,delete(1-查询，2-增删改，默认是1)*/
    OperationTypeEnum operationType() default OperationTypeEnum.OTHER;
}