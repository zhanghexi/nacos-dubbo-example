package org.example.common.db.config.mybatis.interceptor;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName MyBatisListener
 * @User zhang
 * @Description 解决因pageHelper插件导致的MyBatis自定义拦截器不生效(打印不了完整SQL语句)的问题
 * @Author Lucien
 * @Date 2020/11/17 18:02
 * @Version 1.0
 */
@Log4j2
@Component
public class SQLInterceptorListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SQLInterceptor sqlInterceptor;
    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.addSqlInterceptor();
    }

    private void addSqlInterceptor() {
        log.info("添加自定义Mybatis SQL拦截器");
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(sqlInterceptor);
        }
    }
}