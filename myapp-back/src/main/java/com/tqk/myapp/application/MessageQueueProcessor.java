package com.tqk.myapp.application;

import com.tqk.myapp.domain.Message;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;


@Slf4j
@Service
@AllArgsConstructor
public class MessageQueueProcessor {

    private final MessageService messageService;
    private final BlockingQueue<String> messageQueue;
    private final int BATCH_SIZE = 1000;
    private final int FLUSH_INTERVAL = 100;


    @Transactional
    @Scheduled(fixedDelay = FLUSH_INTERVAL)   //run process save batch every 0.1 second with batch size of 1000 max => 10k messages/sec
    public void processBatch() {
        List<String> messageList = new ArrayList<>();
        messageQueue.drainTo(messageList, BATCH_SIZE);
        if (!messageList.isEmpty()) {
            List<Message> messagesToSave = messageList.stream()
                    .map(e -> Message.builder().content(e).build())
                    .collect(Collectors.toList());

            messageService.saveAll(messagesToSave);

            log.debug("Processed {} messages, Queue size: {}, Thread: {}",
                    messageList.size(), messageQueue.size(), Thread.currentThread().getName());
        }
    }

}
