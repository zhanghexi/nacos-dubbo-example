package org.example.common.aop.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName LogVO
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/10/31 0:37
 * @Version 1.0
 */
@Data
public class LogVO implements Serializable {

    private Long id;
    private String clientIp;
    private String username;
    private Long operType;
    private String operUrl;
    private String operEvent;
    private String reqParam;
    private String reqType;
    private Date operTime;
}