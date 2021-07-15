package com.javasampleapproach.batchcsvpostgresql.config;

import com.javasampleapproach.batchcsvpostgresql.job.PrintRandomJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.PostConstruct;

@Configuration
public class QuartzConfig {

    @PostConstruct
    private void initialize() throws Exception {

    }

   /*
    JobDetail jobDetail = buildJobDetail(scheduleEmailRequest);
    Trigger trigger = buildJobTrigger(jobDetail, dateTime);

      Scheduler scheduler = new StdSchedulerFactory().getScheduler();
      scheduler.start();
      scheduler.scheduleJob(jobDetail, trigger);

    */

   @Bean
    public JobDetail printRandomJobDetail() {
        return JobBuilder
                .newJob(PrintRandomJob.class)
                .withIdentity(JobKey.jobKey("printRandom"))
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger printRandomJobTrigger() {
        return TriggerBuilder
                .newTrigger()
                .forJob(printRandomJobDetail())
                .withIdentity(TriggerKey.triggerKey("printRandom"))
                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
                .build();
    }
}