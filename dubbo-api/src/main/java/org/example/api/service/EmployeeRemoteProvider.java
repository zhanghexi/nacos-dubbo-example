package org.example.api.service;

import org.example.api.dto.EmployeeDTO;

import java.util.List;

/**
 * @ClassName EmployeeRemoteProvider
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/10/23 22:18
 * @Version 1.0
 */
public interface EmployeeRemoteProvider {

    /**
     * 根据条件查询符合条件员工信息
     *
     * @return
     */
    List<EmployeeDTO> queryEmployeesByConditions(String empName);
}