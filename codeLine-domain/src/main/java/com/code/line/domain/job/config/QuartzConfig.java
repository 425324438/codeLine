package com.code.line.domain.job.config;

import com.code.line.domain.job.ActionEngineJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: syl
 * @Date: 2022/7/17 23:55
 * @Description:
 */
@Configuration
public class QuartzConfig {

    @Value("${quartz.actionEngineCron}")
    private String actionEngineCron; // corn表达式

    @Bean
    public JobDetail ActionEngineJob() {
        return JobBuilder.newJob(ActionEngineJob.class).withIdentity("ActionEngineJob").storeDurably().build();
    }

    @Bean
    public Trigger actionEngineTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(actionEngineCron);
        return TriggerBuilder.newTrigger().forJob(ActionEngineJob())
                .withIdentity("ActionEngineJob").withSchedule(scheduleBuilder).build();
    }
}
