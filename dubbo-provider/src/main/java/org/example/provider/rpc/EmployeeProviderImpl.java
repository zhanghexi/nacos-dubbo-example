package org.example.provider.rpc;

import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.Service;
import org.example.api.dto.EmployeeDTO;
import org.example.api.service.EmployeeRemoteProvider;
import org.example.provider.data.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName EmployeeProviderImpl
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/10/23 22:17
 * @Version 1.0
 */
@Log4j2
@Service(version = "1.0.0")
public class EmployeeProviderImpl implements EmployeeRemoteProvider {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDTO> queryEmployeesByConditions(String empName) {
        log.info("方法入参:{}", empName);
        List<EmployeeDTO> employeeDTOList = employeeMapper.queryEmployeesByConditions(empName);
        log.info("返回的信息:\n{}", JSONUtil.toJsonPrettyStr(employeeDTOList));
        return employeeDTOList;
    }
}