package org.example.consumer.consumer;

import org.apache.dubbo.config.annotation.Reference;
import org.example.api.service.DubboProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DubboConsumerController
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/10/19 11:31
 * @Version 1.0
 */
@RestController
public class DubboConsumerController {

    @Reference(version = "1.0.0")
    private DubboProviderService dubboProviderService;

    @GetMapping(value = "/hello/{msg}")
    public String hello(@PathVariable("msg") String msg) {
        return dubboProviderService.hello(msg);
    }
}