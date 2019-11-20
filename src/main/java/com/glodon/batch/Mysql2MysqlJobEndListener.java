package com.glodon.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 结束监听器
 * @author mason
 * @since 2019/6/1
 **/

@Slf4j
public class Mysql2MysqlJobEndListener extends JobExecutionListenerSupport {

	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.STARTED){
            log.debug(" batch job complete!");
        }
	}
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.debug(" batch job complete!");
        }
    }
}
