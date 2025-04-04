package com.tqk.myapp.application;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
@EnableScheduling
public class MessageQueueConfig {

    @Bean(name = "messageQueue")
    public BlockingQueue<String> messageQueue() {
        return new LinkedBlockingQueue<>(100000); // on creer un queue temporaire avec le size 100k messages
    }

}
