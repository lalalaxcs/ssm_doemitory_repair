package com.xvchushun.config;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan("com.xvchushun.dao")
public class MybatisConfig {
    @Bean
    public SqlSessionFactoryBean getSqlSessionFactoryBean(@Autowired DataSource dataSource){
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(dataSource);

        PageInterceptor pageInterceptor = new PageInterceptor();
        //创建插件需要的参数集合
        Properties properties = new Properties();
        //配置数据库方言 为oracle
        properties.setProperty("helperDialect", "mysql");
        //配置分页的合理化数据
        properties.setProperty("reasonable", "true");
        pageInterceptor.setProperties(properties);
        //将拦截器设置到sqlSessionFactroy中
        ssfb.setPlugins(new Interceptor[] {pageInterceptor});
        return ssfb;
    }
}
