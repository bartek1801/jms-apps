package com.example.jmssubdemo.elastic;

import com.example.jmssubdemo.subscriber.dto.MessageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "measurements", type = "measurement")
class Message {

    @Id
    private UUID uuid;
    private String message;
    private String priority;
    private boolean secret;
    private Double measurement;
    private BigDecimal temperature;
    @Field(type = FieldType.Date, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date sendTime;

    Message(MessageDto receivedMsg) {
        this.uuid = UUID.randomUUID();
        this.message = receivedMsg.getMessage();
        this.priority = receivedMsg.getPriority();
        this.secret = receivedMsg.isSecret();
        this.measurement = receivedMsg.getMeasurement();
        this.temperature = receivedMsg.getTemperature();
        this.sendTime = convertToDate(receivedMsg.getSendTime());
    }

    private Date convertToDate(LocalDateTime sendTime) {
        return java.sql.Timestamp.valueOf(sendTime);
    }
}
