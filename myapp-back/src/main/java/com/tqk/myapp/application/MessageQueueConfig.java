package com.tqk.myapp.application;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
@EnableScheduling
public class MessageQueueConfig {

    @Bean(name = "messageQueue")
    public BlockingQueue<String> messageQueue() {
        return new LinkedBlockingQueue<>(200000); // on creer un queue temporaire avec le size 200k messages
    }


    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.setThreadNamePrefix("task-scheduler-name-");
        scheduler.initialize();
        return scheduler;
    }


}
