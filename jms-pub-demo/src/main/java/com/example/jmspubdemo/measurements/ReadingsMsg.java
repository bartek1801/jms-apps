package com.example.jmspubdemo.measurements;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@ToString
class ReadingsMsg implements Serializable {

    private static final long serialVersionUID = -295422703255886286L;

    private String message;
    private Priority priority;
    private boolean secret;
    private Double measurement;
    private BigDecimal temperature;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime sendTime;


    ReadingsMsg(String message,
                Priority priority,
                boolean secret,
                Double measurement,
                BigDecimal temperature,
                LocalDateTime sendTime
    ) {
        this.message = message;
        this.priority = priority;
        this.secret = secret;
        this.measurement = measurement;
        this.temperature = temperature;
        this.sendTime = sendTime;
    }
}
