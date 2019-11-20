package com.glodon.entity;

import lombok.Data;

@Data
public class QuartzBean {
	private String batchJobName;
	private String jobName;
	private String jobGroup;
	private String jobClass;
	private String triggerName;
	private String triggerGroup;
	private String cronExpression;
}
