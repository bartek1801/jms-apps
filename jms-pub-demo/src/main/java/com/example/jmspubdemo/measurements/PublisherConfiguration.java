package com.example.jmspubdemo.measurements;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
class PublisherConfiguration {


    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(5672);
    }

//    @Bean
//    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }

    @Bean
    public Jackson2JsonMessageConverter producerMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerMessageConverter());
        return rabbitTemplate;
    }


    @Bean(name = "measurements")
    public Queue measurementsQueue() {
        return new Queue("measurements", false);
    }

    @Bean(name = "SAMPLE-QUEUE")
    public Queue sampleQueue() {
        return new Queue("SAMPLE-QUEUE", false);
    }

    @Bean(name = "response_channel_messages")
    public Queue ResponseQueue() {
        return new Queue("response_channel_messages", false);
    }

    @Bean(name = "rpc-queue")
    public Queue rpcQueue() {
        return new Queue("rpc-queue", false);
    }

    @Bean(name = "rpc-exchange")
    DirectExchange rpcExchange(){
        return new DirectExchange("rpc-exchange");
    }

    @Bean
    Binding binding(@Qualifier("rpc-queue") Queue rpcQueue,
                    @Qualifier("rpc-exchange") DirectExchange rpcTopicExchange){
        return BindingBuilder.bind(rpcQueue)
                .to(rpcTopicExchange)
                .with("rpc");
    }

}
