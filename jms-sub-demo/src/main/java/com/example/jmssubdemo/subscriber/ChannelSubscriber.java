package com.example.jmssubdemo.subscriber;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class ChannelSubscriber {

    private Queue measurementsQueue;
    private MessageRepository messageRepository;
    private ResponseService responseService;

    public ChannelSubscriber(@Qualifier("measurements") Queue measurementsQueue,
                             MessageRepository messageRepository,
                             ResponseService responseService) {
        this.measurementsQueue = measurementsQueue;
        this.messageRepository = messageRepository;
        this.responseService = responseService;
    }

    @RabbitListener(queues = "${sub.messages.queue.name}")
    public void msgListener(ChannelMsg receivedMsg) {
        log.info("Received receivedMsg from {} --> {}", measurementsQueue.getName(), receivedMsg);
        Message msg = new Message(receivedMsg);
        messageRepository.save(msg);
        responseService.sendResponseMsg(msg.getUuid());
    }

}
