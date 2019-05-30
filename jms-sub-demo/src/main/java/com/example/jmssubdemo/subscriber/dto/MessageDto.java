package com.example.jmssubdemo.subscriber.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MessageDto {

    private UUID uuid;
    private String message;
    private String priority;
    private boolean secret;
    private Double measurement;

}
