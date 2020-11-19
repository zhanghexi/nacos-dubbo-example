package org.example.api.http;

import cn.hutool.json.JSONUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ClassName Result
 * @User zhang
 * @Description api接口统一返回值封装
 * @Author Lucien
 * @Date 2020/11/19 0:52
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class Result<T> implements Serializable {

    private int code;
    private String message;
    private T data;

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}