package org.example.common.log.service;

import lombok.extern.log4j.Log4j2;
import org.example.common.log.util.DBUtils;
import org.example.common.log.vo.SystemLog;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

    private static final String LOG_SQL = "INSERT INTO OPERATION (ID,CLASS_NAME,REQUEST_METHOD,DESCRIPTION,METHOD_TYPE," +
            "CREATED_BY,REQUEST_ADDRESS,REQUEST_IP,REQUEST_PORT,REQUEST_PATH,REQUEST_TYPE,REQUEST_PARAMS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    @Async
    public void saveLog(SystemLog systemLog) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(LOG_SQL);
            ps.setString(1, systemLog.getId());
            ps.setString(2, systemLog.getClassName());
            ps.setString(3, systemLog.getRequestMethod());
            ps.setString(4, systemLog.getDescription());
            ps.setString(5, systemLog.getMethodType());
            ps.setString(6, systemLog.getCreatedBy());
            ps.setString(7, systemLog.getRequestAddress());
            ps.setString(8, systemLog.getRequestIp());
            ps.setInt(9, systemLog.getRequestPort());
            ps.setString(10, systemLog.getRequestPath());
            ps.setString(11, systemLog.getRequestType());
            ps.setString(12, systemLog.getRequestParams());
            int i = ps.executeUpdate();
            if (i == 1) {
                log.info("入库成功");
                DBUtils.closeResources(null, ps, connection);
            }
        } catch (Exception e) {
            log.error("日志信息入库异常:{}", e.getMessage());
        }
    }
}