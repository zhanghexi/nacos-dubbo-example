package org.example.common.aop.service;

import lombok.extern.log4j.Log4j2;
import org.example.common.aop.util.DBUtils;
import org.example.common.aop.vo.LogVO;
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

    private static final String SQL = "INSERT INTO OPERATION (CLIENT_IP, USERNAME, OPER_TYPE, OPER_URL, OPER_EVENT, REQ_PARAM, REQ_TYPE) VALUES (?,?,?,?,?,?,?)";

    public void saveLog(LogVO logVO) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = DBUtils.getConnection();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL);
            ps.setString(1, logVO.getClientIp());
            ps.setString(2, logVO.getUsername());
            ps.setLong(3, logVO.getOperType());
            ps.setString(4, logVO.getOperUrl());
            ps.setString(5, logVO.getOperEvent());
            ps.setString(6, logVO.getReqParam());
            ps.setString(7, logVO.getReqType());
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