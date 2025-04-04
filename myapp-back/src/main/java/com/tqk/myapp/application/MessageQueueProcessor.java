package com.tqk.myapp.application;

import com.tqk.myapp.domain.Message;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class MessageQueueProcessor {

    private final MessageService messageService;
    private final BlockingQueue<String> messageQueue;
    private final int BATCH_SIZE = 5000;
    private final int FLUSH_INTERVAL = 500;


    @Transactional
    @Scheduled(fixedDelay = FLUSH_INTERVAL)   //run batch every 0.5 second with batch size of 5000 max
    public void processBatch() {
        List<String> messageList = new ArrayList<>();
        messageQueue.drainTo(messageList, BATCH_SIZE);
        if (!messageList.isEmpty()) {
            List<Message> messagesToSave = messageList.stream()
                    .map(e -> Message.builder().content(e).build())
                    .collect(Collectors.toList());

            messageService.saveAll(messagesToSave);
        }
    }

}
