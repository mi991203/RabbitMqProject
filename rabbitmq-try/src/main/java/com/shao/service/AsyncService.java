package com.shao.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.shao.utils.MqConnUtils;
import com.shao.utils.PropertyConstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AsyncService {
    private final Channel channel = MqConnUtils.getInstance().getConnectionFactory().newConnection().createChannel();

    public AsyncService() throws IOException, TimeoutException {
    }

    @Async
    public void asyncConsumer() {
        while (true) {
            final ConnectionFactory connectionFactory = MqConnUtils.getInstance().getConnectionFactory();
            try (final Connection conn = connectionFactory.newConnection()) {
                channel.basicQos(2);
                channel.basicConsume(PropertyConstance.getDirectQueue(), false, (consumerTag, message) -> {
                    System.out.println(new String(message.getBody()) + "耗时1秒");
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, (consumerTag) -> {
                    System.out.println("异常回调: consumerTag=" + consumerTag);
                });
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("消息消费异常");
            }
        }
    }
}
