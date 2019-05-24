package com.example.jmssubdemo.subscriber;

import com.example.jmssubdemo.subscriber.ChannelMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {

    @Id
    private UUID uuid;
    private String message;
    private String priority;
    private boolean secret;
    private Double measurement;

    public Message(ChannelMsg receivedMsg) {
        this.uuid = UUID.randomUUID();
        this.message = receivedMsg.getMessage();
        this.priority = receivedMsg.getPriority();
        this.secret = receivedMsg.isSecret();
        this.measurement = receivedMsg.getMeasurement();
    }
}
