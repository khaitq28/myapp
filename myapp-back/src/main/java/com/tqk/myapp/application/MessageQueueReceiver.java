package com.tqk.myapp.application;

import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;



@Component
@AllArgsConstructor
public class MessageQueueReceiver {

    private final BlockingQueue<String> messageQueue;

    @JmsListener(destination = "IBM_QUEUE")
    public void receiveMessage(String message) throws InterruptedException {
        //put message to queue
        messageQueue.put(message);
    }

}
