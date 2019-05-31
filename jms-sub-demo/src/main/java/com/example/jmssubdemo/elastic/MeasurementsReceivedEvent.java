package com.example.jmssubdemo.elastic;

import com.example.jmssubdemo.subscriber.dto.MessageDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class MeasurementsReceivedEvent {

    private UUID uuid;
    private String message;
    private String priority;
    private boolean secret;
    private Double measurement;
    private BigDecimal temperature;
    private LocalDateTime sendTime;

    public MeasurementsReceivedEvent(MessageDto receivedMsg) {
        this.uuid = UUID.randomUUID();
        this.message = receivedMsg.getMessage();
        this.priority = receivedMsg.getPriority();
        this.secret = receivedMsg.isSecret();
        this.measurement = receivedMsg.getMeasurement();
        this.temperature = receivedMsg.getTemperature();
        this.sendTime = receivedMsg.getSendTime();
    }

}
