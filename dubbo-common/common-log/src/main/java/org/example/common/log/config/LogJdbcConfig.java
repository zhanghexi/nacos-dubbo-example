package org.example.common.log.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName LogConfig
 * @User zhang
 * @Description 抽取数据库配置(日志信息入库)到nacos配置中心
 * @Author Lucien
 * @Date 2020/11/18 15:56
 * @Version 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jdbc")
public class LogJdbcConfig {

    private String driver;
    private String url;
    private String username;
    private String password;
}