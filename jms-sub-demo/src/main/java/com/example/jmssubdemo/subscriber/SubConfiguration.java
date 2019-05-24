package com.example.jmssubdemo.subscriber;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SubConfiguration {

    @Bean(name = "response_channel_messages")
    public Queue responseQueue() {
        return new Queue("response_channel_messages", false);
    }

    @Bean(name = "measurements")
    public Queue measurementsQueue() {
        return new Queue("measurements", false);
    }

}
