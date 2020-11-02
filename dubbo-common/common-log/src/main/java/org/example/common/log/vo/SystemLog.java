package org.example.common.log.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SystemLog
 * @User zhang
 * @Description 除了返回值不保存，打印出的日志信息都保存
 * @Author Lucien
 * @Date 2020/11/1 23:24
 * @Version 1.0
 */
@Data
public class SystemLog implements Serializable {

    /**
     * 主键
     */
    private String id;
    /**
     * 请求类
     */
    private String className;
    /**
     * 请求方法
     */
    private String requestMethod;
    /**
     * 方法描述
     */
    private String description;
    /**
     * 方法类型
     */
    private String methodType;
    /**
     * 请求人
     */
    private String createdBy;
    /**
     * 请求地址
     */
    private String requestAddress;
    /**
     * 请求IP
     */
    private String requestIp;
    /**
     * 请求端口
     */
    private Integer requestPort;
    /**
     * 请求路径
     */
    private String requestPath;
    /**
     * 请求方式
     */
    private String requestType;
    /**
     * 请求参数
     */
    private String requestParams;
}