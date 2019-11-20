package com.glodon.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.glodon.config.QuartzPropConfig;
import com.glodon.entity.QuartzBean;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class JobInit {
	
	@Autowired
	QuartzPropConfig quartzConfig;
	
	
	@Autowired
	@Qualifier("scheduler")
	Scheduler scheduler;
	
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

	public static BaseJob getClass(String classname) throws Exception {
		Class<?> class1 = Class.forName(classname);
		return (BaseJob) class1.newInstance();
	}
}
