package org.example.consumer.controller;

import lombok.extern.log4j.Log4j2;
import org.example.common.log.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CommonLogController
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/11/18 14:56
 * @Version 1.0
 */
@Log4j2
@RestController
public class CommonLogController {

    @Autowired
    private CommonService logService;

    @GetMapping(value = "/getLogInfo")
    public String getLogInfo() {
        log.info("info:" + logService.getLog());
        return logService.getLog();
    }
}