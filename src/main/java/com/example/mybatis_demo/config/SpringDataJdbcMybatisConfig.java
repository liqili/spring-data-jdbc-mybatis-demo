package com.example.mybatis_demo.config;

import com.example.mybatis_demo.support.mybatis.MyBatisJdbcRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

/**
 * Created by kunkka on 31/10/24
 */
@Configuration
@EnableJdbcRepositories(basePackages = "com.example.mybatis_demo.repository", repositoryFactoryBeanClass = MyBatisJdbcRepositoryFactoryBean.class)
public class SpringDataJdbcMybatisConfig extends AbstractJdbcConfiguration {
}
