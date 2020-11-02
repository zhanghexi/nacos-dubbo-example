package org.example.common.log.enums;

import lombok.Getter;

/**
 * @ClassName OperationType
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/11/1 23:56
 * @Version 1.0
 */
@Getter
public enum OperationTypeEnum {

    OTHER("other", "其他操作"),
    SELECT("select", "查询"),
    INSERT("insert", "新增"),
    UPDATE("update", "修改"),
    DELETE("delete", "删除");

    private String type;
    private String desc;

    OperationTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}