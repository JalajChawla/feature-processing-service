package com.up42.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
/**
 * @author jalajchawla
 */
public class JobMonitoringListener implements JobExecutionListener {
    private long start,end;
    @Override
    public void beforeJob(JobExecution jobExecution) {
        start=System.currentTimeMillis();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        end=System.currentTimeMillis();
    }

}
