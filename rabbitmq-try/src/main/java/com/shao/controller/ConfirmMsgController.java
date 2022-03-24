package com.shao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.shao.domain.dto.DirectMsgDto;
import com.shao.utils.MqConnUtils;
import com.shao.utils.PropertyConstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/confirm-msg")
public class ConfirmMsgController {

    /**
     * 发送方确认机制
     *
     * @return 发送方返回的消息
     */
    @GetMapping("send-direct-queue-msg")
    public String getDirectQueueMsg(@NotNull DirectMsgDto directMsgDto) {
        final ConnectionFactory connectionFactory = MqConnUtils.getInstance().getConnectionFactory();
        try (final Connection conn = connectionFactory.newConnection(); final Channel channel = conn.createChannel()) {
            channel.confirmSelect();
            channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
                System.out.println("消息无法被路由");
            });
            for (int i = 0; i < 50; i++) {
                channel.basicPublish(PropertyConstance.getDirectExchange(), "direct-key", true, null, new ObjectMapper().writeValueAsBytes(directMsgDto));
            }
            if (channel.waitForConfirms()) {
                return "发送成功";
            } else {
                return "发送失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    /**
     * 接收方确认
     */
    @GetMapping("consumer-ack")
    public String consumerAck() {

        return null;
    }
}
