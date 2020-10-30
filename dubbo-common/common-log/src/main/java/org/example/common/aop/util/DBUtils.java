package org.example.common.aop.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

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
    /**
     * properties文件名
     */
    private static String defaultName = "jdbc.properties";

    /**
     * 获取Connection
     *
     * @return connection
     * @throws Exception
     */
    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = getConnection(defaultName);
        return connection;
    }

    /**
     * 通过properties文件名获取Connection
     *
     * @param fileName properties文件名
     * @return Connection
     * @throws Exception
     */
    public static Connection getConnection(String fileName)
            throws ClassNotFoundException, SQLException, IOException {
        //IO流读取jdbc.properties文件
        InputStream in = DBUtils.class.getClassLoader().getResourceAsStream(fileName);
        // 读取参数
        Properties properties = new Properties();
        properties.load(in);
        String driver = properties.getProperty("jdbc.driver");
        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        Class.forName(driver);
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
    public static void closeResources(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection)
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