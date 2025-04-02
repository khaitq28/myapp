package com.tqk.myapp.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Message {

    private String id;
    private String content;
    private LocalDateTime createdAt;

}
