package com.example.jmspubdemo.measurements;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@ToString
class ChannelMsg implements Serializable {

    private static final long serialVersionUID = -295422703255886286L;

    private String message;
    private String priority;
    private boolean secret;
    private Double measurement;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime sendTime;


    public ChannelMsg(String message,
                      String priority,
                      boolean secret,
                      Double measurement,
                      LocalDateTime sendTime
    ) {
        this.message = message;
        this.priority = priority;
        this.secret = secret;
        this.measurement = measurement;
        this.sendTime = sendTime;
    }
}
