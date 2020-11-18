package org.example.consumer.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.Reference;
import org.example.api.service.RpcRemoteProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName DubboConsumerController
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/10/19 11:31
 * @Version 1.0
 */
@Log4j2
@RestController
@RequestMapping(value = "/remote")
public class RpcRemoteClientController {

    @Reference(version = "1.0.0")
    private RpcRemoteProvider rpcRemoteProvider;

    @GetMapping(value = "/hello/{msg}")
    public String hello(@PathVariable("msg") String msg, HttpServletRequest request) {
        log.info("请求路径:{}", request.getRequestURI());
        return rpcRemoteProvider.hello(msg);
    }
}