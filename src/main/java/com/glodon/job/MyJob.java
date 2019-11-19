package com.glodon.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class MyJob extends QuartzJobBean {


	   private String name;

	   public void setName(String name) {
	       this.name = name;
	   }

	   @Override

	   protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

	       log.info("hi {}",this.name);

	   }

	}
