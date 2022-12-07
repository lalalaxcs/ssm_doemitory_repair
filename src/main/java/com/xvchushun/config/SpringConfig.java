package com.xvchushun.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MybatisConfig.class,JDBCConfig.class})
@ComponentScan({"com.xvchushun.dao","com.xvchushun.service","com.xvchushun.controller"})
public class SpringConfig {
}
