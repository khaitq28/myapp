package com.tqk.myapp;

import com.tqk.myapp.application.MessageService;
import com.tqk.myapp.application.PartnerService;
import com.tqk.myapp.domain.Message;
import com.tqk.myapp.domain.Partner;
import lombok.AllArgsConstructor;
import org.jeasy.random.EasyRandom;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final MessageService messageService;
    private final PartnerService partnerService;
    private final EasyRandom easyRandom = new EasyRandom();

    @Override
    public void run(String... args) {

//        List<Message> messageList = easyRandom.objects(String.class, 10000)
//                .map(m -> Message.builder()
//                        .content(m)
//                        .build())
//                .collect(Collectors.toList());
//
//        messageService.saveAll(messageList);


        List<Partner> partners = easyRandom.objects(Partner.class, 100)
                                            .peek(e -> e.setId(null))
                                            .collect(Collectors.toList());

        partnerService.saveAll(partners);

    }
}
