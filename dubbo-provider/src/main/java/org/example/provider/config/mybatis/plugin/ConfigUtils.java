package org.example.provider.config.mybatis.plugin;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;

import java.util.Properties;

/**
 * @ClassName ConfigUtils
 * @User zhang
 * @Description 配置分页插件
 * @Author Lucien
 * @Date 2020/10/26 1:17
 * @Version 1.0
 */
public class ConfigUtils {

    public static void getPlugins(SqlSessionFactoryBean sqlSessionFactoryBean) {
        Interceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "oracle");
        properties.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(properties);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper, new SQLInterceptor()});
    }
}