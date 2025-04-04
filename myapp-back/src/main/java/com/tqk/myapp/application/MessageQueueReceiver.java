package com.tqk.myapp.application;

import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;



@Service
@AllArgsConstructor
public class MessageQueueReceiver {

    private final BlockingQueue<String> messageQueue;

    @JmsListener(destination = "${ibm.mq.queue.name}")
    public void receiveMessage(String message) throws InterruptedException {
        //put message to queue
        messageQueue.put(message);
    }

}
