package com.example.jmssubdemo.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
class ResponseService {
    
    private RabbitTemplate rabbitTemplate;

    ResponseService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    void sendResponseMsg(UUID uuid) {
        log.info("sended response messages");
        rabbitTemplate.convertAndSend("response_channel_messages","Received and saved message with id: " + uuid);
    }
}
