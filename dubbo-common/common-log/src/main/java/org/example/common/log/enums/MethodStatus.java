package org.example.common.log.enums;

import lombok.Getter;

/**
 * @ClassName MethodStatus
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/11/2 1:42
 * @Version 1.0
 */
@Getter
public enum MethodStatus {

    SUCCESS("success", "成功"),
    FAIL("fail", "失败");

    private String type;
    private String desc;

    MethodStatus(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}