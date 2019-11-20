package com.glodon.batch;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.glodon.entity.Teacher;
import com.glodon.entity.User;

import lombok.extern.slf4j.Slf4j;



@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	/* 用来注册job */
	/* JobRegistry会自动注入进来 */
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
		return jobRegistryBeanPostProcessor;
	}

	/* 创建job */
	@Bean
	public Job jobMethod() {
		return jobBuilderFactory.get("batchJob1").start(stepMethod()).build();
	}

	/* 创建step */
	@Bean
	public Step stepMethod() {
		return stepBuilderFactory.get("batchJob1Step1").<User, User>chunk(3).reader(new MyReader())
				.processor(new MyProcessor()).writer(new MyWriter()).allowStartIfComplete(true).build();
	}
	
	
	
	@Bean
    public Job batchJob2(Step batchJob2Step1,JobExecutionListener mysql2MysqlJobEndListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("funcName1:::::: {}",funcName);
        return jobBuilderFactory.get(funcName)
                .listener(mysql2MysqlJobEndListener)
                .flow(batchJob2Step1)
                .end().build();
    }
	
	
	@Bean
	public Step batchJob2Step1(ItemReader<User> itemReader1, ItemProcessor<User, Teacher> mysql2MysqlProcessor,ItemWriter<Teacher> itemWriter1) {
		String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("funcName2:::::: {}",funcName);
		return stepBuilderFactory.get(funcName).<User, Teacher>chunk(10).reader(itemReader1).processor(mysql2MysqlProcessor)
				.writer(itemWriter1).build();
	}
	
	@Bean
	public ItemReader<User> itemReader1(
			@Qualifier("srcSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		Map<String, Object> parameterValues = new HashMap<String, Object>();
		parameterValues.put("queryId", "com.glodon.mapper.src.UserMapper.selectAllUser");
		MyBatisCursorItemReader<User> reader = new MyBatisCursorItemReader<User>();

		reader.setSqlSessionFactory(sqlSessionFactory);
		reader.setParameterValues(parameterValues);
		reader.setQueryId(parameterValues.get("queryId").toString());
		return reader;
	}

	@Bean
	public ItemWriter<Teacher> itemWriter1(
			@Qualifier("destSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		MyBatisBatchItemWriter<Teacher> writer = new MyBatisBatchItemWriter<>();
		writer.setSqlSessionFactory(sqlSessionFactory);
		writer.setStatementId("com.glodon.mapper.dest.TeacherMapper.insert");
		return writer;
	}
	@Bean
    public ItemProcessor<User, Teacher> mysql2MysqlProcessor(){
        return new Mysql2MysqlProcessor();
    }

    @Bean
    public JobExecutionListener mysql2MysqlJobEndListener(){
        return new Mysql2MysqlJobEndListener();
    }
}
