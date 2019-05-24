package com.example.jmssubdemo.fibonacci;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class FibonacciService {

    @RabbitListener(queues = "rpc-queue")
    Integer receiveFromRpcQueue(Integer number) {
        log.info("Received number {} from rpc-queue", number);
        return fib(number);
    }

    private int fib(Integer n) {
        return n == 0 ? 0 : n == 1 ? 1 : (fib(n - 1) + fib(n - 2));
    }

}
