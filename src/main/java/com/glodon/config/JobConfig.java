package com.glodon.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.glodon.job.MyJob;

@Configuration
public class JobConfig {
	
	/*
	//创建工作
    JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
            .withDescription("工作的描述")
            .withIdentity("工作的名称", "工作的组")
            .build()
	
	//创建触发器
 
        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription("触发器的描述")
                .withIdentity("触发器的名称", "触发器的组")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 11 * ? * *")) //"0/5 11 * ? * *"表达式
                .startAt(new Date())//不设置，默认为当前时间
                .build();

	//创建调度器，粘合工作和触发器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
 
        //启动调度器
        scheduler.start();

	
	*/
	
	@Bean
	public JobDetail jobDetail() {
		return JobBuilder.newJob(MyJob.class)
				.withIdentity("myjob1","jobGroup1") //定义name/group
				.usingJobData("name", "myname1")
				.storeDurably()
				.build();
	}

	@Bean
	public Trigger trigger(JobDetail jobDetail) {
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/10 * * * * ?");
	
		return TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.withIdentity("mytrigger1","triggerGroup1")
				.withSchedule(cronScheduleBuilder)
				.build();
	}
}
