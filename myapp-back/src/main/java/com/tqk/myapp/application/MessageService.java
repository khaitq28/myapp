package com.tqk.myapp.application;

import com.tqk.myapp.domain.Message;
import com.tqk.myapp.domain.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Quang-Khai TRAN
 * @date 03/04/2025
 */

@Service
@AllArgsConstructor
public class MessageService {

     private final MessageRepository messageRepository;

     public Page<Message> getMessages(Pageable pageable) {
         return messageRepository.findAll(pageable);
     }

     public void saveAll(List<Message> messages) {
        messageRepository.saveAll(messages);
     }


}
