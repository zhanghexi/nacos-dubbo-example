package org.example.common.log.service;

import lombok.extern.log4j.Log4j2;
import org.example.common.log.util.DBUtils;
import org.example.common.log.vo.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName SysLogService
 * @User zhang
 * @Description 日志数据存入数据库表
 * @Author Lucien
 * @Date 2020/10/31 0:38
 * @Version 1.0
 */
@Log4j2
@Component
public class SysLogService {

    @Autowired
    private DBUtils dbUtils;

    private static final String LOG_SQL = "INSERT INTO SYSTEM_LOG (CLASS_NAME,REQUEST_METHOD,DESCRIPTION,METHOD_TYPE," +
            "CREATED_BY,REQUEST_ADDRESS,REQUEST_IP,REQUEST_PORT,REQUEST_PATH,REQUEST_TYPE,REQUEST_PARAMS) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

    @Async
    public void saveLog(SystemLog systemLog) throws SQLException, ClassNotFoundException {
        Connection connection = dbUtils.initConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(LOG_SQL);
            /*赋值*/
            ps.setString(1, systemLog.getClassName());
            ps.setString(2, systemLog.getRequestMethod());
            ps.setString(3, systemLog.getDescription());
            ps.setString(4, systemLog.getMethodType());
            ps.setString(5, systemLog.getCreatedBy());
            ps.setString(6, systemLog.getRequestAddress());
            ps.setString(7, systemLog.getRequestIp());
            ps.setInt(8, systemLog.getRequestPort());
            ps.setString(9, systemLog.getRequestPath());
            ps.setString(10, systemLog.getRequestType());
            ps.setString(11, systemLog.getRequestParams());
            /*update*/
            int i = ps.executeUpdate();
            if (i == 1) {
                log.info("入库成功");
                dbUtils.closeResources(null, ps, connection);
            }
        } catch (Exception e) {
            log.error("日志信息入库异常:{}", e.getMessage());
        }
    }
}