package org.example.provider.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.Service;
import org.example.api.service.DubboProviderService;

/**
 * @ClassName DubboProviderService
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/10/19 11:00
 * @Version 1.0
 */
@Log4j2
@Service(version = "1.0.0")
public class DubboProviderServiceImpl implements DubboProviderService {

    @Override
    public String hello(String msg) {
        log.info(this.getClass().getName() + "方法被调用...");
        return "Hello " + msg;
    }
}