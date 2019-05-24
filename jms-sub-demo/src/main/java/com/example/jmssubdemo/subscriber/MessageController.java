package com.example.jmssubdemo.subscriber;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {
    
    private MessageRepository messageRepository;
    
    

    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    @GetMapping("/messages")
    public List<Message> getMessages(){
        return messageRepository.findAll();
    }

    @GetMapping("/msg-count")
    public long countMessages(){
        return messageRepository.count();
    }
}
