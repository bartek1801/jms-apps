package com.example.jmssubdemo.subscriber;

import com.example.jmssubdemo.elastic.ElasticFacade;
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
    private ElasticFacade elasticFacade;

    public ChannelSubscriber(@Qualifier("measurements") Queue measurementsQueue,
                             MessageRepository messageRepository,
                             ResponseService responseService,
                             ElasticFacade elasticFacade) {
        this.measurementsQueue = measurementsQueue;
        this.messageRepository = messageRepository;
        this.responseService = responseService;
        this.elasticFacade = elasticFacade;
    }

    @RabbitListener(queues = "measurements")
    public void msgListener(MeasurementsMsg receivedMsg) {
        log.info("Received receivedMsg from {} --> {}", measurementsQueue.getName(), receivedMsg);
        Message msg = new Message(receivedMsg);
        messageRepository.save(msg);
        elasticFacade.saveMsg(msg.toDto());
        responseService.sendResponseMsg(msg.getUuid());
    }

}
