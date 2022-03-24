package com.shao.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MessageProcessor {

    @RabbitListener(queues = "direct-queue")
    public void processHandler(Channel channel, @Payload Message message) {
        System.out.println("收到消息——" + new String(message.getBody()));
        try {
            System.out.println("拒收消息");
            Thread.sleep(1000);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


