package com.example.jmssubdemo.elastic;

import com.example.jmssubdemo.subscriber.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ElasticFacade {


    private MsgRepository msgRepository;

    public ElasticFacade(MsgRepository msgRepository) {
        this.msgRepository = msgRepository;
    }


    public void saveMsg(MessageDto messageDto) {
        Message message = new Message(messageDto);
        Message savedMsg = msgRepository.save(message);
        log.info("Message saved in ElasticSearch {}", savedMsg.getUuid());
    }

    @EventListener(MeasurementsReceivedEvent.class)
    public void measurementReceived(MeasurementsReceivedEvent event) {
        log.info("Received {}", event.getClass().toString());
        Message message = new Message(event);
        Message savedMsg = msgRepository.save(message);
        log.info("Message saved in ElasticSearch {}", savedMsg.getUuid());
    }
}
