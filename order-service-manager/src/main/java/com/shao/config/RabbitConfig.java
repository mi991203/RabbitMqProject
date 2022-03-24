package com.shao.config;

import com.rabbitmq.client.BuiltinExchangeType;
import com.shao.service.OrderMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
@Configuration
public class RabbitConfig {

    @Autowired
    OrderMessageService orderMessageService;

    @Autowired
    public void startListenMessage() throws IOException, TimeoutException, InterruptedException {
        orderMessageService.handleMessage();
    }

    @Autowired
    public void initRabbit() {
        final CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        final RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareExchange(new DirectExchange("exchange.order.restaurant", true, false, null));
        rabbitAdmin.declareQueue(new Queue("queue.order", true, false, false, null));
        rabbitAdmin.declareBinding(new Binding("queue.order", Binding.DestinationType.QUEUE, "exchange.order.restaurant", "key.order", null));

        rabbitAdmin.declareExchange(new DirectExchange("exchange.order.deliveryman", true, false, null));
        rabbitAdmin.declareBinding(new Binding("queue.order", Binding.DestinationType.QUEUE, "exchange.order.deliveryman", "key.order", null));

        rabbitAdmin.declareExchange(new FanoutExchange("exchange.settlement.order", true, false, null));
        rabbitAdmin.declareBinding(new Binding("queue.order", Binding.DestinationType.QUEUE, "exchange.settlement.order", "key.order", null));

        rabbitAdmin.declareExchange(new TopicExchange("exchange.order.reward", true, false, null));
        rabbitAdmin.declareBinding(new Binding("queue.order", Binding.DestinationType.QUEUE, "exchange.order.reward", "key.order", null));

    }

//    @Bean
    public ConnectionFactory connectionFactory() {
        final CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.createConnection();
        return connectionFactory;
    }

//    @Bean
    public RabbitAdmin rabbitAdmin(@Autowired ConnectionFactory connectionFactory) {
        final RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

//    @Bean
    public DirectExchange restaurantDirectExchange() {
        return new DirectExchange("exchange.order.restaurant", true, false, null);
    }

//    @Bean
    public Queue restaurantQueue() {
        return new Queue("queue.order", true, false, false, null);
    }

//    @Bean
    public Binding restaurantBinding() {
        return new Binding("queue.order", Binding.DestinationType.QUEUE, "exchange.order.restaurant", "key.order", null);
    }

}
