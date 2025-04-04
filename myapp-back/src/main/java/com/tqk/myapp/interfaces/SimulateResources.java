package com.tqk.myapp.interfaces;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
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

    @GetMapping(path = "/api/simulate", produces = MediaType.TEXT_PLAIN_VALUE)
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
        messageList.add("Simulate to push 50k messages during: " + (end - cur) + " ms");

        String summary = "You have simulated " + messageList.size() + " times : \n";
        for (String message : messageList) {
            summary += (message + "\n");
        }
        return summary;
    }

    private List<String> messageList = new ArrayList<>();


    @GetMapping(path = "/home")
    public String home() {
        return "Hello from API";
    }

}
