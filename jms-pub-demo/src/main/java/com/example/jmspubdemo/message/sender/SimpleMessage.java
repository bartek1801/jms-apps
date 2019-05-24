package com.example.jmspubdemo.message.sender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMessage {
    
    private String channelName;
    private String text;
    private String from;
    private String to;
    
}
