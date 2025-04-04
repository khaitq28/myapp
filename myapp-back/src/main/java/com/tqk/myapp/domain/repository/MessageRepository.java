package com.tqk.myapp.domain.repository;

import com.tqk.myapp.domain.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MessageRepository {

    void saveAll(List<Message> message);
    Page<Message> findAll(Pageable pageable);

}
