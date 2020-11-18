package org.example.common.log.service;

import org.example.common.log.config.CommonConfig;
import org.example.common.log.config.LogJdbcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName LogService
 * @User zhang
 * @Description
 * @Author Lucien
 * @Date 2020/11/18 15:57
 * @Version 1.0
 */
@Service
public class CommonService {

    @Autowired
    private CommonConfig commonConfig;

    public String getLog() {
        return commonConfig.getInfo();
    }
}