package org.example.consumer.consumer;

import org.apache.dubbo.config.annotation.Reference;
import org.example.api.dto.EmployeeDTO;
import org.example.api.service.EmployeeRemoteProvider;
import org.example.common.log.annotation.OperationLog;
import org.example.common.log.enums.OperationTypeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName EmployeeRemoteClientController
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/10/23 22:30
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/remote")
public class EmployeeRemoteClientController {

    @Reference(version = "1.0.0")
    private EmployeeRemoteProvider employeeRemoteProvider;

    @OperationLog(operationName = "根据员工名字模糊查询员工信息", operationType = OperationTypeEnum.SELECT)
    @GetMapping(value = "/queryEmployeesByConditions/{empName}")
    public List<EmployeeDTO> queryEmployeesByConditions(@PathVariable("empName") String empName) {
        return employeeRemoteProvider.queryEmployeesByConditions(empName);
    }
}