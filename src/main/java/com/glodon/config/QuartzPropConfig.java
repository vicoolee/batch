package com.glodon.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.glodon.entity.QuartzBean;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "quartz.config") 
@Data
public class QuartzPropConfig {
	private List<QuartzBean> quartzs = new ArrayList<QuartzBean>();
}

