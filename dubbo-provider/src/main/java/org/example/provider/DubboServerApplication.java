package org.example.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName DubboServerApplication
 * @User zhang
 * @Description 从Spring Cloud Edgware开始，@EnableDiscoveryClient可省略
 * @Author Lucien
 * @Date 2020/10/19 10:56
 * @Version 1.0
 */
@SpringBootApplication
public class DubboServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboServerApplication.class, args);
    }
}