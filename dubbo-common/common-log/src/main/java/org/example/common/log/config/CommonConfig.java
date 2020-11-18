package org.example.common.log.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName CommonConfig
 * @User zhang
 * @Description 该配置类所对应的nacos的配置文件的源信息是写在被引用的模块(dubbo - consumer)里面，同样能读取到
 * @Author Lucien
 * @Date 2020/11/18 16:25
 * @Version 1.0
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class CommonConfig {

    private String info;
}
