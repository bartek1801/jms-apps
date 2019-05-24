package com.example.jmspubdemo.measurements;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
class ResponseReceiverService {

    private RabbitTemplate rabbitTemplate;

    public ResponseReceiverService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "response_channel_messages")
    void receiveResponseMsg(String responseMsg) {
        log.info("Received response message: {}", responseMsg);
    }

//    @RabbitListener(queues = "rpc-queue")
//    String receiveFromRpcQueue(String msg){
//        log.info("Received msg from rpc-queue");
//        return "msq received " + LocalDateTime.now();
//    }
}
