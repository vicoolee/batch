package com.glodon.batch.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CorpJobListener extends JobExecutionListenerSupport{
	@Override
	public void beforeJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.STARTED){
			log.info("------------------------start----------------------------");
            log.debug(" batch job start!");
        }
	}
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.debug(" batch job complete!");
            log.info("-------------------------end-----------------------------");
        }
    }
}
