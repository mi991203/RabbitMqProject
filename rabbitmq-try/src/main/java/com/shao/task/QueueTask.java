package com.shao.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueTask {
    public void handleDirectMsg(byte[] messageBody) {
        System.out.println("收到direct消息——" + new String(messageBody));
    }

    public void handFanoutMsg(byte[] messageBody) {
        System.out.println("收到fanout消息——" + new String(messageBody));
    }
}
