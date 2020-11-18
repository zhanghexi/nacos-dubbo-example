package org.example.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @ClassName DubboClientApplication
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/10/19 11:34
 * @Version 1.0
 */
@EnableAsync
@SpringBootApplication
public class DubboClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboClientApplication.class, args);
    }
}
