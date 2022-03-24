package com.shao.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.shao.utils.MqConnUtils;
import com.shao.utils.PropertyConstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/queue")
public class QueueController {

    @GetMapping("create-direct-queue")
    public String createDirectQueue() {
        final ConnectionFactory connectionFactory = MqConnUtils.getInstance().getConnectionFactory();
        try (final Connection conn = connectionFactory.newConnection(); final Channel channel = conn.createChannel()) {
            final Map<String, Object> args = new HashMap<>(16);
            channel.queueDeclare(PropertyConstance.getDirectQueue(), true, false, false, args);
            channel.queueBind(PropertyConstance.getDirectQueue(), PropertyConstance.getDirectExchange(), "direct-key");
        } catch (Exception e) {
            e.printStackTrace();
            return "create error";
        }
        return "create success";
    }
}
