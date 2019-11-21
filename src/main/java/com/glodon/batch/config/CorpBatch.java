package com.glodon.batch.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.glodon.batch.listener.CorpJobListener;
import com.glodon.batch.writer.CorpClassifier;
import com.glodon.batch.writer.InsertEnterpriseWriter;
import com.glodon.batch.writer.UpdateEnterpriseWriter;
import com.glodon.entity.CorpInfo;
import com.glodon.entity.Enterprise;
import com.glodon.service.EnterpriseService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CorpBatch {
	
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
    public Job corpJob(Step corpStep,CorpJobListener corpJobListener){
        String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("funcName1:::::: {}",funcName);
        return jobBuilderFactory.get(funcName)
                .listener(corpJobListener)
                .flow(corpStep)
                .end().build();
    }
	
	@Bean
	public Step corpStep(ItemReader<CorpInfo> corpItemReader, ItemProcessor<CorpInfo, Enterprise> corpProcessor,ItemWriter<Enterprise> enterpriseCompositeItemWriter) {
		String funcName = Thread.currentThread().getStackTrace()[1].getMethodName();
		log.info("funcName2:::::: {}",funcName);
		return stepBuilderFactory.get(funcName)
				.<CorpInfo, Enterprise>chunk(1000)
				.reader(corpItemReader)
				.processor(corpProcessor)
				.writer(enterpriseCompositeItemWriter)
				.build();
	}
	
	
	@Bean
	@StepScope
	public ItemReader<CorpInfo> corpItemReader(
			@Qualifier("srcSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		Map<String, Object> parameterValues = new HashMap<String, Object>();
		parameterValues.put("queryId", "com.glodon.mapper.src.CorpInfoMapper.selectAllCorpInfo");
		MyBatisPagingItemReader<CorpInfo> reader = new MyBatisPagingItemReader<CorpInfo>();
		reader.setSqlSessionFactory(sqlSessionFactory);
		reader.setPageSize(1000);
		reader.setParameterValues(parameterValues);
		reader.setQueryId(parameterValues.get("queryId").toString());
		reader.afterPropertiesSet();
	
		return reader;
	}

	
	@Bean
	@StepScope
    public ClassifierCompositeItemWriter<Enterprise> enterpriseCompositeItemWriter(CorpClassifier corpClassifier) throws Exception {
       
        ClassifierCompositeItemWriter<Enterprise> itemWriter = new ClassifierCompositeItemWriter<>();
        itemWriter.setClassifier(corpClassifier);
        return itemWriter;
    }
	
	@Bean
	public CorpClassifier corpClassifier(EnterpriseService enterpriseService,ItemWriter<Enterprise> insertWriter,ItemWriter<Enterprise> updateWriter) {
		return new CorpClassifier(enterpriseService, insertWriter, updateWriter);
	}
	
	
	@Bean 
	@StepScope
	public ItemWriter<Enterprise> insertWriter(@Qualifier("destSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
		return new InsertEnterpriseWriter(sqlSessionFactory);
	}
	
	@Bean 
	@StepScope
	public ItemWriter<Enterprise> updateWriter(@Qualifier("destSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
		return new UpdateEnterpriseWriter(sqlSessionFactory);
	}
}
