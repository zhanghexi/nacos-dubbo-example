package org.example.provider.controller;

import org.example.provider.service.ProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName DubboProviderController
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/10/20 14:04
 * @Version 1.0
 */
@RestController
public class ProviderController {

    @Resource
    private ProviderService providerService;

    @GetMapping(value = "/hello/{msg}")
    public String hello(@PathVariable("msg") String msg) {
        return providerService.hello(msg);
    }
}