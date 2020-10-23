package org.example.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName EmployeeDTO
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/8/30 0:03
 * @Version 1.0
 */
@Data
public class EmployeeDTO implements Serializable {

    private String name;
    private Long sex;
    private Long role;
    private Long age;
    private BigDecimal salary;
}
