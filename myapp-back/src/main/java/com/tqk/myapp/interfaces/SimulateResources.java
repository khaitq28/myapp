package com.tqk.myapp.interfaces;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@AllArgsConstructor
public class SimulateResources {

    private final BlockingQueue<String> messageQueue;
    private final AtomicInteger count = new AtomicInteger();

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10000; i++) {
            String message = "message: " + count.incrementAndGet();
            messageQueue.add(message);
        }
    }

    @GetMapping(path = "/api/simulate")
    public String simulate() {
        long cur = System.currentTimeMillis();
        for(int i = 0; i< 10; i++) {
            for(int j = 0; j < 5000; j++) {
                String message = "message: " + count.incrementAndGet();
                messageQueue.add(message);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        long end = System.currentTimeMillis();
        return "Simulate to push 50k messages during: " + (end - cur) + " ms";
    }


    @GetMapping(path = "/home")
    public String home() {
        return "Hello from API";
    }

}
