package com.tqk.myapp.interfaces;

import com.tqk.myapp.domain.Message;
import org.jeasy.random.EasyRandom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Quang-Khai TRAN
 * @date 03/04/2025
 */


@RestController
@RequestMapping("/api/messages")
public class MessageResources {

    private final EasyRandom easyRandom = new EasyRandom();

    @GetMapping
    public List<Message> getMessages() {
        return easyRandom.objects(Message.class, 10).collect(Collectors.toList());
    }


}
