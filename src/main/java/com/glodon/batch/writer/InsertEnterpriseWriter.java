package com.glodon.batch.writer;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;

import com.glodon.entity.Enterprise;


public class InsertEnterpriseWriter extends MyBatisBatchItemWriter<Enterprise> {
	
	public InsertEnterpriseWriter( SqlSessionFactory sqlSessionFactory) {
		super();
		
		this.setSqlSessionFactory(sqlSessionFactory);
		this.setStatementId("com.glodon.mapper.dest.EnterpriseMapper.insertSelective");
		this.setAssertUpdates(false);
		this.afterPropertiesSet();
		
	}
	


}
