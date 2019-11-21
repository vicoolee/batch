package com.glodon.quartz.config;

import java.io.IOException;
import java.util.Properties;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class SchedulerConfig {

	@Autowired
	QuartzPropConfig quartzConfig;

	@Bean(name = "properties")
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}

	@Bean(name = "schedulerFactory")
	public SchedulerFactory SchedulerFactory(Properties properties) throws IOException, SchedulerException {
		SchedulerFactory schedulerFactory = new StdSchedulerFactory(properties);
		return schedulerFactory;
	}

	/**
	 * 通过SchedulerFactory获取Scheduler的实例
	 */
	@Bean(name = "scheduler")
	public Scheduler scheduler(SchedulerFactory schedulerFactory) throws IOException, SchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.start();
		return scheduler;
	}

	
}
