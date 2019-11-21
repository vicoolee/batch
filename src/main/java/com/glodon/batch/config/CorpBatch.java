package com.glodon.batch.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.glodon.batch.writer.CorpClassifier;
import com.glodon.entity.CorpInfo;
import com.glodon.entity.Enterprise;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CorpBatch {
	
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
    public Job corpJob(Step corpStep,JobExecutionListener corpJobListener){
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
		return stepBuilderFactory.get(funcName).
				<CorpInfo, Enterprise>chunk(10).
				reader(corpItemReader).
				processor(corpProcessor)
				.writer(enterpriseCompositeItemWriter)
				.build();
	}
	
	
	@Bean
	public ItemReader<CorpInfo> corpItemReader(
			@Qualifier("srcSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		Map<String, Object> parameterValues = new HashMap<String, Object>();
		parameterValues.put("queryId", "com.glodon.mapper.src.CorpInfoMapper.selectAllCorpInfo");
		MyBatisCursorItemReader<CorpInfo> reader = new MyBatisCursorItemReader<CorpInfo>();

		reader.setSqlSessionFactory(sqlSessionFactory);
		reader.setParameterValues(parameterValues);
		reader.setQueryId(parameterValues.get("queryId").toString());
		return reader;
	}


	
	@Autowired
	private CorpClassifier corpClassifier;
	
	@Bean
    public ClassifierCompositeItemWriter<Enterprise> enterpriseCompositeItemWriter() throws Exception {
       
        ClassifierCompositeItemWriter<Enterprise> itemWriter = new ClassifierCompositeItemWriter<>();
        itemWriter.setClassifier(corpClassifier);
        return itemWriter;
    }
	


}
