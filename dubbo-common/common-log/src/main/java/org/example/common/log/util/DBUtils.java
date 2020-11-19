package org.example.common.log.util;

import lombok.extern.log4j.Log4j2;
import org.example.common.log.config.LogJdbcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * @ClassName DBUtils
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/10/31 1:23
 * @Version 1.0
 */
@Log4j2
@Component
public class DBUtils {

    @Autowired
    private LogJdbcConfig logJdbcConfig;

    /**
     * 初始化数据库连接
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection initConnection() throws ClassNotFoundException, SQLException {
        String driver = logJdbcConfig.getDriver();
        String url = logJdbcConfig.getUrl();
        String user = logJdbcConfig.getUsername();
        String password = logJdbcConfig.getPassword();
        Class.forName(driver);
        /*4、建立连接*/
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /**
     * 关闭资源
     *
     * @param resultSet
     * @param preparedStatement
     * @param connection
     * @throws SQLException
     */
    public void closeResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection)
            throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}