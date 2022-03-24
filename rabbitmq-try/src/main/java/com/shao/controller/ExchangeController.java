package com.shao.controller;

import com.shao.domain.dto.DirectMsgDto;
import com.shao.service.ExchangeService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    @Resource
    private ExchangeService exchangeService;

    @GetMapping("direct")
    public String sendToDirectExchange(DirectMsgDto directMsgDto) {
        try {
            exchangeService.sendToDirect(directMsgDto);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @GetMapping("fanout")
    public String sendToFanoutExchange(DirectMsgDto directMsgDto) {
        try {
            exchangeService.sendToFanout(directMsgDto);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @GetMapping("topic")
    public String sendToTopicExchange(DirectMsgDto directMsgDto) {
        try {
            exchangeService.sendToTopic(directMsgDto);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
