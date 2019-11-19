package com.glodon.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * @author liwk
 * @date 2019年11月15日 下午5:49:13
 */
@Configuration
@MapperScan(basePackages = { "com.glodon.mapper.src" }, sqlSessionTemplateRef = "srcSqlSessionTemplate")
public class MybatisSrcConfig {

	@Bean(name = "srcDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.src")
	@Primary
    public DataSource srcDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

	@Bean(name = "srcSqlSessionFactory")
    @Primary
    public SqlSessionFactory srcSqlSessionFactory(@Qualifier("srcDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/src/*.xml"));
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }
	
	@Bean(name = "srcTransactionManager")
    @Primary
    public DataSourceTransactionManager srcTransactionManager(@Qualifier("srcDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
    @Bean
    @Primary
    public SqlSessionTemplate srcSqlSessionTemplate(@Qualifier("srcSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

}
