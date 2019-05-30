package com.example.jmspubdemo.measurements;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
class MeasurementsSenderService {

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

    @Scheduled(fixedDelay = 1000L)
    public void sendMeasurements() {
        ReadingsMsg msg = createRandomReadingsMsg();
        sendMeasurementsToQueue(queueName, msg);
//        sendMeasurementsToQueue(sampleQueue.getName(), msg);
    }

    private ReadingsMsg createRandomReadingsMsg() {
        return new ReadingsMsg(
                getRandomMessage(),
                Priority.values()[new Random().nextInt(3)],
                getRandomBoolean(),
                createRandomDouble(1, 5),
                BigDecimal.valueOf(createRandomDouble(100, 2)),
                LocalDateTime.now()
        );
    }

    private String getRandomMessage() {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL("https://whatthecommit.com");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("<p>")) {
                    sb.append(line.substring(3));
                }
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private double createRandomDouble(int decimalMultiplier, int decimalPlaces) {
        double random = Math.random() * decimalMultiplier;
        return Precision.round(random, decimalPlaces);

    }

    private boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    private void sendMeasurementsToQueue(String queueName, ReadingsMsg msg) {
        rabbitTemplate.convertAndSend(queueName, msg);
        log.info("Message sent to {} ->> {} ", queueName, msg.toString());
    }


    @Scheduled(fixedDelay = 10000000L)
    public void sendValueToCalcFibonacciNo() {
        int numberOfFibonacciSeqElement = new Random().nextInt(20) + 1;
        Integer result = (Integer) rabbitTemplate.convertSendAndReceive(rpcExchange.getName(), "rpc", numberOfFibonacciSeqElement);
        log.info("The {} Fibonacci number is -> {} ", numberOfFibonacciSeqElement, result);
    }

}
