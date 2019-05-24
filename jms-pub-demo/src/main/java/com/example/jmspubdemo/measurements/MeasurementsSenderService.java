package com.example.jmspubdemo.measurements;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
class MeasurementsSenderService {


//    @Value("${pub.measurements.queue.name}")
    @Value("measurements")
    private String queueName;

    private Queue sampleQueue;
    private DirectExchange rpcExchange;

    private RabbitTemplate rabbitTemplate;

    public MeasurementsSenderService(@Qualifier("SAMPLE-QUEUE") Queue sampleQueue,
                                     @Qualifier("rpc-exchange") DirectExchange rpcExchange,
                                     RabbitTemplate rabbitTemplate) {
        this.sampleQueue = sampleQueue;
        this.rpcExchange = rpcExchange;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 100000L)
    public void sendMeasurements() {
        ChannelMsg msg = new ChannelMsg("Hello message", "high", true, new Random().nextDouble(), LocalDateTime.now());
        sendMeasurementsToQueue(queueName, msg);
        sendMeasurementsToQueue(sampleQueue.getName(), msg);
    }

    private void sendMeasurementsToQueue(String queueName, ChannelMsg msg) {
        rabbitTemplate.convertAndSend(queueName, msg);
        log.info("Message sent to {} ->> {} ", queueName, msg.toString());
    }

    @Scheduled(fixedDelay = 1000L)
    public void sendValueToCalc() {
        calculateDoubleValue(new Random().nextInt(20) + 1);
    }

    private void calculateDoubleValue(int valueToCalc) {
        Integer result = (Integer) rabbitTemplate.convertSendAndReceive(rpcExchange.getName(), "rpc", valueToCalc);
        log.info("The {} Fibonacci number is -> {} ", valueToCalc, result);
    }


}
