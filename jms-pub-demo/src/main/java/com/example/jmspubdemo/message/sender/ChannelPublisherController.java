package com.example.jmspubdemo.message.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestController
public class ChannelPublisherController {


    private MsgSenderService senderService;

    public ChannelPublisherController(MsgSenderService senderService) {
        this.senderService = senderService;
    }


//    @PostMapping("/addMessage")
//    public String sendMsg(@RequestBody SimpleMessage message) {
//        return senderService.sendMsg(message);
//    }

    @PostMapping("/addMessage")
    public String sendMsg(@RequestBody String message) {
        String status= "";
        try {
            status = senderService.sendMsg(message);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return status;
    }


}
