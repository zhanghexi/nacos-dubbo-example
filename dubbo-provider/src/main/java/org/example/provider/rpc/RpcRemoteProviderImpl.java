package org.example.provider.rpc;

import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.Service;
import org.example.api.service.RpcRemoteProvider;

/**
 * @ClassName RpcRemoteProviderImpl
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/10/20 14:18
 * @Version 1.0
 */
@Log4j2
@Service(version = "1.0.0")
public class RpcRemoteProviderImpl implements RpcRemoteProvider {

    @Override
    public String hello(String msg) {
        log.info(this.getClass().getName() + "方法被调用...");
        return "Hello " + msg;
    }
}