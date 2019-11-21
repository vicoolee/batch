package com.glodon.batch.writer;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.glodon.entity.Enterprise;

@Component
public class UpdateEnterpriseWriter implements ItemWriter<Enterprise> {
	
	@Autowired
	@Qualifier("destSqlSessionFactory") 
	SqlSessionFactory sqlSessionFactory;
	
	
	@Override
	public void write(List<? extends Enterprise> items) throws Exception {
		
		MyBatisBatchItemWriter<Enterprise> itemWriter = new MyBatisBatchItemWriter<Enterprise>();
		itemWriter.setSqlSessionFactory(sqlSessionFactory);
		itemWriter.setStatementId("com.glodon.mapper.dest.EnterpriseMapper.update");
		itemWriter.afterPropertiesSet();
	}

}
