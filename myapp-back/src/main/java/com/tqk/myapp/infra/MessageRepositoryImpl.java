package com.tqk.myapp.infra;

import com.tqk.myapp.domain.Message;
import com.tqk.myapp.domain.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

    private final MessageJpaRepository messageJpaRepository;

    @Override
    public void saveAll(List<Message> messages) {
        messageJpaRepository.saveAll(
                messages.stream()
                        .map(m -> MessageEntity.builder()
                                .content(m.getContent()).createdAt(LocalDateTime.now())
                                .build()).collect(Collectors.toList()));
    }

    @Override
    public Page<Message> findAll(Pageable pageable) {
        return messageJpaRepository.findAll(pageable)
                .map(m -> Message.builder()
                        .id(m.getId())
                        .content(m.getContent())
                        .createdAt(m.getCreatedAt())
                        .build());
    }

}
