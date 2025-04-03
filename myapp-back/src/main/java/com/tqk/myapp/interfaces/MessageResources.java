package com.tqk.myapp.interfaces;

import com.tqk.myapp.application.MessageService;
import com.tqk.myapp.domain.Message;
import lombok.AllArgsConstructor;
import org.jeasy.random.EasyRandom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Quang-Khai TRAN
 * @date 03/04/2025
 */


@RestController
@AllArgsConstructor
@RequestMapping("/api/messages")
public class MessageResources {

    private final MessageService messageService;

    @GetMapping
    public Page<Message> getMessages(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "createdAt") String sortBy,
                                     @RequestParam(defaultValue = "desc") String sortDir) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return messageService.getMessages(pageable);
    }


}
