package com.glodon.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyJob implements BaseJob {

	

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
			jobLauncher.run(job, jobParameters);

			
		} catch (Exception e) {
			log.info("执行定时任务异常");
			e.printStackTrace();
		}
	}

}
