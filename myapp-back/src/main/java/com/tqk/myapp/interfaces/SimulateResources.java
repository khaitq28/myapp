package com.tqk.myapp.interfaces;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@Slf4j
public class SimulateResources {
    private volatile boolean running = false;
    private final BlockingQueue<String> messageQueue;
    private final AtomicInteger count = new AtomicInteger();

    public SimulateResources(BlockingQueue<String> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10000; i++) {
            String message = "message: " + count.incrementAndGet();
            messageQueue.add(message);
        }
    }

    @GetMapping(path = "/api/simulate", produces = MediaType.TEXT_PLAIN_VALUE)
    public String simulate() {
        long cur = System.currentTimeMillis();
        for(int i = 0; i< 10; i++) {
            for(int j = 0; j < 5000; j++) {
                String message = "message simulate 1: " + count.incrementAndGet();
                messageQueue.add(message);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        long end = System.currentTimeMillis();
        messageList.add("Simulate to push 50k messages during: " + (end - cur) + " ms");

        String summary = "You have simulated " + messageList.size() + " times : \n";
        for (String message : messageList) {
            summary += (message + "\n");
        }
        return summary;
    }


    private Thread simulatorThread = null;

    @GetMapping(path = "/api/simulate2/start", produces = MediaType.TEXT_PLAIN_VALUE)
    public String simulate2() {
        if (running) return "Simulation is already running";
        running = true;
        simulatorThread = new Thread(() -> {
        log.info("Simulation started at: {}", LocalDateTime.now());
        while (running) {
            for (int i = 0; i < 1000; i++) {
                String message = "Simulated message " + count.incrementAndGet();
                if (!messageQueue.offer(message)) {
                    log.warn("Queue full, dropping message: {}", message);
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.info("Simulation interrupted");
                running = false;
            }}
            log.info("Simulation stopped at: {}, Total messages sent: {}", LocalDateTime.now(), count.get());
        });
        simulatorThread.setName("Message-Simulator");
        simulatorThread.start();
        return "Simulation is already running";
    }

    @GetMapping(path = "/api/simulate2/stop", produces = MediaType.TEXT_PLAIN_VALUE)
    public String stopSimulate() {
        running = false;
        if (simulatorThread != null) simulatorThread.interrupt();
        return "Simulation stopped";
    }

    private List<String> messageList = new ArrayList<>();

    @GetMapping(path = "/home")
    public String home() {
        return "Hello from API";
    }

}
