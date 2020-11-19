package org.example.api.enums;

import lombok.Getter;

/**
 * @ClassName ResultCode
 * @User zhang
 * @Description 接口返回状态码、具体信息枚举类
 * @Author Lucien
 * @Date 2020/11/19 0:44
 * @Version 1.0
 */
@Getter
public enum ResultCode {
    /*成功状态码*/
    SUCCESS(0, "成功"),
    /*参数错误*/
    PARAM_IS_EMPTY(1001, "参数为空"),
    PARAM_ERROR(1002, "参数错误"),
    /*用户级别错误*/
    USER_LOGIN_ERROR(2001, "账号不存在或密码错误"),
    USER_NOT_EXIST(2002, "用户不存在"),
    USER_HAS_EXISTED(2003, "用户已存在"),
    /*系统异常*/
    SYSTEM_ERROR(-1, "系统异常，请与系统管理员联系");

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}