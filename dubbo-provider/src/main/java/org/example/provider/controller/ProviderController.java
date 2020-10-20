package org.example.provider.controller;

import lombok.extern.log4j.Log4j2;
import org.example.api.service.RpcRemoteProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName DubboProviderController
 * @User zhang
 * @Description 此处内部controller也可以调用暴露出去的接口实现类
 * @Author Lucien
 * @Date 2020/10/20 14:04
 * @Version 1.0
 */
@Log4j2
@RestController
public class ProviderController {

    @Resource
    private RpcRemoteProvider providerService;

    @GetMapping(value = "/hello/{msg}")
    public String hello(@PathVariable("msg") String msg, HttpServletRequest request) {
        log.info("请求路径:{}", request.getRequestURI());
        return providerService.hello(msg);
    }
}