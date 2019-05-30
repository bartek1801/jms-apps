package com.example.jmssubdemo.subscriber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class MessageDto {

    private UUID uuid;
    private String message;
    private String priority;
    private boolean secret;
    private Double measurement;
    private BigDecimal temperature;
    private LocalDateTime sendTime;

}
