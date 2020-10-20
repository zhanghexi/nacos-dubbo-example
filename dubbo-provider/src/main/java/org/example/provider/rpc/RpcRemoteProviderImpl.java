package org.example.provider.rpc;

import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.Service;
import org.example.api.service.RpcRemoteProvider;
import org.springframework.stereotype.Component;

/**
 * @ClassName RpcRemoteProviderImpl
 * @User zhang
 * @Description 为了方便服务内部引用，要加上@Component注解
 * @Author Lucien
 * @Date 2020/10/20 14:18
 * @Version 1.0
 */
@Log4j2
@Component
@Service(version = "1.0.0")
public class RpcRemoteProviderImpl implements RpcRemoteProvider {

    @Override
    public String hello(String msg) {
        log.info(this.getClass().getName() + "方法被调用...");
        return "Hello " + msg;
    }
}