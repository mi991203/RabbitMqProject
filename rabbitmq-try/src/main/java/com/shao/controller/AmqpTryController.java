package com.shao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shao.domain.dto.DirectMsgDto;
import com.shao.utils.PropertyConstance;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/amqp-try")
public class AmqpTryController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("send-direct-msg")
    public String sendDirectMsg(DirectMsgDto directMsgDto) throws JsonProcessingException {
        final MessageProperties messageProperties = new MessageProperties();
        final Message message = new Message(new ObjectMapper().writeValueAsBytes(directMsgDto), messageProperties);
        for (int i = 0; i < 50; i++) {
            final CorrelationData correlationData = new CorrelationData("id:" + i);
            rabbitTemplate.send(PropertyConstance.getDirectExchange(), "direct-key", message, correlationData);
        }
        return "success";
    }

    @GetMapping("send-fanout-msg")
    public String sendFanoutMsg(DirectMsgDto directMsgDto) throws JsonProcessingException {
        final MessageProperties messageProperties = new MessageProperties();
        final Message message = new Message(new ObjectMapper().writeValueAsBytes(directMsgDto), messageProperties);
        for (int i = 0; i < 50; i++) {
            final CorrelationData correlationData = new CorrelationData("id:" + i);
            rabbitTemplate.send(PropertyConstance.getFanoutExchange(), "fanout-key", message, correlationData);
        }
        return "success";
    }

}
