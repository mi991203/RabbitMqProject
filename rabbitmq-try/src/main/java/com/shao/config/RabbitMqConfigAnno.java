package com.shao.config;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitMqConfigAnno {

    @Autowired
    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue(
                                    name = "direct-queue"
                                    /*arguments = {
                                            @Argument(
                                                    name = "x-message-ttl",
                                                    value = "15000",
                                                    type = "java.lang.Integer"
                                            )
                                    }*/
                            ),
                            exchange = @Exchange(name = "direct-exchange", type = ExchangeTypes.DIRECT),
                            key = {"direct-key"}
                    )
            }
    )
    public void init() {
        System.out.println("创建exchange/queue/binding_key");
    }
}
