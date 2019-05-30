package com.example.jmssubdemo.subscriber;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class Message {

    @Id
    private UUID uuid;
    private String message;
    private String priority;
    private boolean secret;
    private Double measurement;
    private BigDecimal temperature;
    private LocalDateTime sendTime;

    Message(MeasurementsMsg receivedMsg) {
        this.uuid = UUID.randomUUID();
        this.message = receivedMsg.getMessage();
        this.priority = receivedMsg.getPriority();
        this.secret = receivedMsg.isSecret();
        this.measurement = receivedMsg.getMeasurement();
        this.temperature = receivedMsg.getTemperature();
        this.sendTime = receivedMsg.getSendTime();
    }
}
