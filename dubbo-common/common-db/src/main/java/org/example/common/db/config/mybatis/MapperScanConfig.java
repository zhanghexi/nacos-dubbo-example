package org.example.common.db.config.mybatis;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * @ClassName MapperScanConfig
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/10/24 0:59
 * @Version 1.0
 */
@Data
@Configuration
public class MapperScanConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        /*定义要扫描的包路径*/
        configurer.setBasePackage("org.example.provider.data.mapper");
        Properties properties = new Properties();
        /*注册tk.mybatis的Mapper类*/
        properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
        configurer.setProperties(properties);
        configurer.setSqlSessionFactoryBeanName("dataSourceSessionFactory");
        return configurer;
    }
}