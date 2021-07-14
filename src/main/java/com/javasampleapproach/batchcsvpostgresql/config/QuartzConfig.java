package com.javasampleapproach.batchcsvpostgresql.config;

import com.javasampleapproach.batchcsvpostgresql.job.PrintRandomJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

   /*
   JobDetail jobDetail = buildJobDetail(scheduleEmailRequest);
    Trigger trigger = buildJobTrigger(jobDetail, dateTime);
    scheduler.scheduleJob(jobDetail, trigger);
    */

    public JobDetail printRandomJobDetail() {
        return JobBuilder
                .newJob(PrintRandomJob.class)
                .withIdentity(JobKey.jobKey("printRandom"))
                .storeDurably()
                .build();
    }

    public Trigger printRandomJobTrigger() {
        return TriggerBuilder
                .newTrigger()
                .forJob(printRandomJobDetail())
                .withIdentity(TriggerKey.triggerKey("printRandom"))
                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
                .build();
    }
}