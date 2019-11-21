package com.glodon.batch.reader;

import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.glodon.entity.CorpInfo;


public class CorpInfoReader extends MyBatisCursorItemReader<CorpInfo>{
	
	@Override
	public CorpInfo read() throws Exception, UnexpectedInputException, ParseException {
		
		return super.read();
	}
}
