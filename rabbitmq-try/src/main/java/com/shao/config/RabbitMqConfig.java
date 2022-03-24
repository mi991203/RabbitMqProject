package com.shao.config;

import com.shao.task.QueueTask;
import com.shao.utils.PropertyConstance;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {
//    @Bean
//    public CachingConnectionFactory cachingConnectionFactory() {
//        final CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//        cachingConnectionFactory.setHost("localhost");
//        cachingConnectionFactory.setPort(5672);
//        cachingConnectionFactory.setUsername("guest");
//        cachingConnectionFactory.setPassword("guest");
//        cachingConnectionFactory.setPublisherConfirms(true);
//        cachingConnectionFactory.setPublisherReturns(true);
//        cachingConnectionFactory.createConnection();
//        return cachingConnectionFactory;
//    }
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(@Autowired CachingConnectionFactory connectionFactory) {
//        final RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
//        rabbitAdmin.setAutoStartup(true);
//        return rabbitAdmin;
//    }
//
    @Bean
    public DirectExchange testDirectExchange() {
        return new DirectExchange(PropertyConstance.getDirectExchange(), true, false);
    }

    @Bean
    public Queue testQueue() {
        return new Queue(PropertyConstance.getDirectQueue(), true, false, false);
    }

    @Bean
    public Binding testDirectBinding() {
        return new Binding(PropertyConstance.getDirectQueue(), Binding.DestinationType.QUEUE, PropertyConstance.getDirectExchange(), "direct-key", null);
    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//            System.out.println("message peplyText = " + replyText);
//        });
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//            System.out.println("message confired acked");
//        });
//        return rabbitTemplate;
//    }
//
//    @Autowired
//    private QueueTask queueTask;
//
//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory) {
//        final SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
//        simpleMessageListenerContainer.setQueueNames("direct-queue", "fanout-queue1");
//        // 设置多少个线程同时处理消息
//        simpleMessageListenerContainer.setConcurrentConsumers(3);
//        // 最大多少个线程处理消息
//        simpleMessageListenerContainer.setMaxConcurrentConsumers(5);
//        // 消费端限流，每次只取多少条消息
//        simpleMessageListenerContainer.setPrefetchCount(3);
//        final MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter();
//        messageListenerAdapter.setDelegate(queueTask);
//        Map<String, String> methodMap = new HashMap<>(16);
//        methodMap.put("direct-queue", "handleDirectMsg");
//        methodMap.put("fanout-queue1", "handFanoutMsg");
//        messageListenerAdapter.setQueueOrTagToMethodName(methodMap);
//
//        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
//        return simpleMessageListenerContainer;
//    }
}
