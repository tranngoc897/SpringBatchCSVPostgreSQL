package com.javasampleapproach.batchcsvpostgresql.job;

import com.javasampleapproach.batchcsvpostgresql.service.PrintRandomService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class PrintRandomJob extends QuartzJobBean {

    @Autowired
    private PrintRandomService printRandomService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        printRandomService.printRandom();
    }
}