package com.example.jmssubdemo.subscriber;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
//@NoArgsConstructor
@ToString
//@JsonIgnoreProperties(ignoreUnknown = true)
class ChannelMsg implements Serializable {

    private static final long serialVersionUID = -295422703255886286L;

    private String message;
    private String priority;
    private boolean secret;
    private Double measurement;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime sendTime;

    public ChannelMsg(@JsonProperty("mesage") String message,
                      @JsonProperty("priority") String priority,
                      @JsonProperty("secret") boolean secret,
                      @JsonProperty("measurement") Double measurement,
                      @JsonProperty("sendTime") LocalDateTime sendTime) {
        this.message = message;
        this.priority = priority;
        this.secret = secret;
        this.measurement = measurement;
        this.sendTime = sendTime;
    }

    @Configuration
    public static class JsonConfiguration {


        @Bean
        public Jackson2JsonMessageConverter producerMessageConverter(){
            ObjectMapper mapper = new ObjectMapper();
    //        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            return new Jackson2JsonMessageConverter(mapper);
        }

        @Bean
        public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
            RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setMessageConverter(producerMessageConverter());
            return rabbitTemplate;
        }

    }
}