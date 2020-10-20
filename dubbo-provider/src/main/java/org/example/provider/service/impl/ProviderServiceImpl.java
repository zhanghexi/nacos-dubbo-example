package org.example.provider.service.impl;

import lombok.extern.log4j.Log4j2;
import org.example.provider.service.ProviderService;
import org.springframework.stereotype.Service;

/**
 * @ClassName ProviderServiceImpl
 * @User zhang
 * @Description 内部调用接口实现
 * @Author Lucien
 * @Date 2020/10/20 14:19
 * @Version 1.0
 */
@Log4j2
@Service
public class ProviderServiceImpl implements ProviderService {

    @Override
    public String hello(String msg) {
        log.info(this.getClass().getName() + "方法被调用...");
        return "Hello " + msg;
    }
}