package org.example.provider.data.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.api.dto.EmployeeDTO;
import org.example.provider.data.entity.Employee;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EmployeeMapper extends Mapper<Employee> {

    /**
     * 根据员工姓名模糊查询员工信息
     *
     * @param empName
     * @return
     */
    List<EmployeeDTO> queryEmployeesByConditions(@Param("empName") String empName);
}