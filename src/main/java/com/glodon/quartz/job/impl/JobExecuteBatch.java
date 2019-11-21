package com.glodon.quartz.job.impl;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

import com.glodon.quartz.job.BaseJob;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JobExecuteBatch implements BaseJob {


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			long time = System.currentTimeMillis();
			log.info("hi {}", time);
			JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
			
			JobLauncher jobLauncher = (JobLauncher) jobDataMap.get("jobLauncher");
	        JobLocator jobLocator = (JobLocator) jobDataMap.get("jobLocator");
	        String jobName = jobDataMap.getString("batchJobName");
			Job job = jobLocator.getJob(jobName);
			
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", time).toJobParameters();
			log.info("{}--{}",jobName,jobParameters);
			jobLauncher.run(job, jobParameters);
		} catch (Exception e) {
			log.info("quartz 执行定时任务异常");
			e.printStackTrace();
		}
	}

}
