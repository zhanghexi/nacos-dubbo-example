package org.example.common.db.config.mybatis.factory;

import com.github.pagehelper.PageInterceptor;
import lombok.Data;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @ClassName SqlSessionFactory
 * @User zhang
 * @Description 配置SqlSessionFactory
 * @Author Lucien
 * @Date 2020/8/20 0:58
 * @Version 1.0
 */
@Data
@Configuration
@ConditionalOnClass(DataSource.class)
public class OracleSqlSessionFactory {

    @Bean
    public SqlSessionFactory dataSourceSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        /*在此配置pageHelper分页拦截器的参数*/
        Interceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "oracle");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(properties);
        factoryBean.setPlugins(pageHelper);
        return factoryBean.getObject();
    }
}