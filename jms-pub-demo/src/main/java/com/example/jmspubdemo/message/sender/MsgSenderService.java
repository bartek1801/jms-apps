package com.example.jmspubdemo.message.sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.RpcClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class MsgSenderService {
    


    String sendMsg(String message) throws IOException, TimeoutException {

        return MSG_STATUS.SENT.name();
    }
}
