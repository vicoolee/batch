package com.glodon.batch.writer;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;

import com.glodon.entity.Enterprise;


public class UpdateEnterpriseWriter extends MyBatisBatchItemWriter<Enterprise> {
	
	

	public UpdateEnterpriseWriter(SqlSessionFactory sqlSessionFactory) {
		super();
		
		this.setSqlSessionFactory(sqlSessionFactory);
		this.setStatementId("com.glodon.mapper.dest.EnterpriseMapper.update");
		this.setAssertUpdates(false);
		this.afterPropertiesSet();
	}
	
	
	

}
