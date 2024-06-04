package com.management.user.job;


import com.management.user.service.UpdateAuctionStatusService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Component
@ConditionalOnProperty(value = "updatestatus.schedulting.enabled", havingValue = "true")
public class UpdateStatusSchedulerJob implements  Runnable {

    protected static final Logger LOGGER = LoggerFactory.getLogger(UpdateStatusSchedulerJob.class);

    private static final String CRON_EXPRESSION = "0 0 * * * *";

    private final String cronExpression;

    private final TaskScheduler taskScheduler;

    private ScheduledFuture scheduledFuture;

    private final UpdateAuctionStatusService updateAuctionStatusService;

    @Autowired
    public UpdateStatusSchedulerJob( UpdateAuctionStatusService updateAuctionStatusService) {
        this.cronExpression = CRON_EXPRESSION;
        this.taskScheduler = new ConcurrentTaskScheduler();
        this.updateAuctionStatusService = updateAuctionStatusService;
    }

    @Override
    public void run() {
        LOGGER.info("starting update auction status job --> ");
        updateAuctionStatusService.updateAuctionStatus();
    }

    @PostConstruct
    public void initializeScheduler() {
        this.reschedule(cronExpression);
    }

    public void reschedule(String cronExpression) {
        if(this.scheduledFuture != null) {
            this.scheduledFuture.cancel(true);
        }
        this.scheduledFuture = this.taskScheduler
                .schedule(this, new CronTrigger(cronExpression, TimeZone.getTimeZone("Asia/Kolkata")));
    }
}
