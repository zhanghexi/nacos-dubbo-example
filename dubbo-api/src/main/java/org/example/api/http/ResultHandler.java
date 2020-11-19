package org.example.api.http;

import org.example.api.enums.ResultCode;

/**
 * @ClassName ResultHandler
 * @User zhang
 * @Description 封装api接口正常或异常的方法
 * @Author Lucien
 * @Date 2020/11/19 1:04
 * @Version 1.0
 */
public class ResultHandler {

    /**
     * 不带参数返回正常
     *
     * @return
     */
    public static Result success() {
        return result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    /**
     * 带返回值正常返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return result(data, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    /**
     * 业务异常
     *
     * @param code
     * @param message
     * @return
     */
    public static Result fail(int code, String message) {
        return result(code, message);
    }

    /**
     * 系统异常
     *
     * @return
     */
    public static Result systemError() {
        return result(ResultCode.SYSTEM_ERROR.getCode(), ResultCode.SYSTEM_ERROR.getMessage());
    }

    /**
     * 带返回值的接口信息
     *
     * @param data
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Result<T> result(T data, int code, String message) {
        Result<T> result = new Result();
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 不带返回值的接口信息(包括正常和异常返回)
     *
     * @param code
     * @param message
     * @return
     */
    public static Result result(int code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}