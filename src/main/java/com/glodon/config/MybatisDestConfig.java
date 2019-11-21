package com.glodon.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
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
* @date 2019年11月15日 下午5:49:26 
*/
@Configuration
@MapperScan(basePackages = { "com.glodon.mapper.dest" }, sqlSessionTemplateRef = "destSqlSessionTemplate")
public class MybatisDestConfig {
	
	@Bean(name = "destDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dest")
    public DataSource destDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

	@Bean(name = "destSqlSessionFactory")
    public SqlSessionFactory destSqlSessionFactory( @Qualifier("destDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/dest/*.xml"));
        sqlSessionFactoryBean.setDataSource(dataSource);
        
        return sqlSessionFactoryBean.getObject();
    }
	
	@Bean(name = "destTransactionManager")
    public DataSourceTransactionManager destTransactionManager(@Qualifier("destDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
    @Bean
    public SqlSessionTemplate destSqlSessionTemplate(@Qualifier("destSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
        return sqlSessionTemplate;
    }
}
