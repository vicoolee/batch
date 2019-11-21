package com.glodon.quartz.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import com.glodon.batch.config.BatchConfiguration;
import com.glodon.quartz.entity.QuartzBean;
import com.glodon.quartz.job.BaseJob;

import lombok.extern.slf4j.Slf4j;

@Configuration
@AutoConfigureAfter(BatchConfiguration.class)
@Slf4j
public class JobInit {
	
	@Autowired
	QuartzPropConfig quartzConfig;
	
	
	@Autowired
	@Qualifier("scheduler")
	Scheduler scheduler;
	
	/* 作业调度器，用来启动job,引用作业仓库 */
	@Autowired
    private JobLauncher jobLauncher; 
	
    @Autowired
    private JobLocator jobLocator;
	

    
	@PostConstruct
	public void init() {
		List<QuartzBean> quartzs = quartzConfig.getQuartzs();
		quartzs.stream().forEach((quartzBean) -> {
			log.info("获取定时任务配置信息进行初始化 {} ", quartzBean);
			try {
				Map<String, Object> map = new HashMap<>();
		        map.put("batchJobName", quartzBean.getBatchJobName());
		        map.put("jobLauncher", jobLauncher);
		        map.put("jobLocator", jobLocator);

				JobDetail jobDetail = JobBuilder.newJob(getClass(quartzBean.getJobClass()).getClass())
						.withIdentity(quartzBean.getJobName(), quartzBean.getJobGroup())
						.setJobData(new JobDataMap(map))
						.build();
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzBean.getCronExpression());
				CronTrigger trigger = TriggerBuilder.newTrigger()
						.withIdentity(quartzBean.getTriggerName(), quartzBean.getTriggerGroup())
						.withSchedule(scheduleBuilder).build();
				scheduler.scheduleJob(jobDetail, trigger);
			} catch (Exception e) {
				log.info("quartz 定时任务初始化失败");
				e.printStackTrace();
			}
		});
	}
	@PreDestroy
    public void destory() {
		List<QuartzBean> quartzs = quartzConfig.getQuartzs();
		quartzs.stream().forEach((quartzBean) -> {
			log.info("获取定时任务配置信息退出前销毁 {} ", quartzBean);
			try {
				scheduler.pauseTrigger(TriggerKey.triggerKey(quartzBean.getTriggerName(), quartzBean.getTriggerGroup()));
				scheduler.unscheduleJob(TriggerKey.triggerKey(quartzBean.getTriggerName(), quartzBean.getTriggerGroup()));
				scheduler.deleteJob(JobKey.jobKey(quartzBean.getJobClass(), quartzBean.getJobGroup()));		
			} catch (SchedulerException e) {
				log.error("quartz 定时任务销毁失败");
				e.printStackTrace();
			}
			
			
			
			
		});
			
       
    }

	public static BaseJob getClass(String classname) throws Exception {
		Class<?> class1 = Class.forName(classname);
		return (BaseJob) class1.newInstance();
	}
}
