package com.shao.utils;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PropertyConstance {
    private static String host = null;
    private static Integer port = null;
    private static String username = null;
    private static String password = null;
    private static String directExchange = null;
    private static String directQueue = null;
    private static String fanoutExchange = null;
    private static String fanoutQueue1 = null;
    private static String fanoutQueue2 = null;
    private static String topicExchange = null;
    private static String topicQueue1 = null;
    private static String topicQueue2 = null;

    public PropertyConstance(Environment environment) {
        host = environment.getProperty("rabbitmq.host");
        port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("rabbitmq.port")));
        username = environment.getProperty("rabbitmq.username");
        password = environment.getProperty("rabbitmq.password");
        directExchange = environment.getProperty("rabbitmq.direct-exchange");
        directQueue = environment.getProperty("rabbitmq.direct-queue");
        fanoutExchange = environment.getProperty("rabbitmq.fanout-exchange");
        fanoutQueue1 = environment.getProperty("rabbitmq.fanout-queue1");
        fanoutQueue2 = environment.getProperty("rabbitmq.fanout-queue2");
        topicExchange = environment.getProperty("rabbitmq.topic-exchange");
        topicQueue1 = environment.getProperty("rabbitmq.topic-queue1");
        topicQueue2 = environment.getProperty("rabbitmq.topic-queue2");
    }

    /*static  {
        final Environment environment = ApplicationContextUtils.getBean("environment", Environment.class);
        host = environment.getProperty("rabbitmq.host");
        port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("rabbitmq.port")));
        username = environment.getProperty("rabbitmq.username");
        password = environment.getProperty("rabbitmq.password");
        directExchange = environment.getProperty("rabbitmq.direct-exchange");
        directQueue = environment.getProperty("rabbitmq.direct-queue");
        fanoutExchange = environment.getProperty("rabbitmq.fanout-exchange");
        fanoutQueue1 = environment.getProperty("rabbitmq.fanout-queue1");
        fanoutQueue2 = environment.getProperty("rabbitmq.fanout-queue2");
        topicExchange = environment.getProperty("rabbitmq.topic-exchange");
        topicQueue1 = environment.getProperty("rabbitmq.topic-queue1");
        topicQueue2 = environment.getProperty("rabbitmq.topic-queue2");
    }*/

    public static String getHost() {
        return host;
    }

    public static Integer getPort() {
        return port;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDirectExchange() {
        return directExchange;
    }

    public static String getDirectQueue() {
        return directQueue;
    }

    public static String getFanoutExchange() {
        return fanoutExchange;
    }

    public static String getFanoutQueue1() {
        return fanoutQueue1;
    }

    public static String getFanoutQueue2() {
        return fanoutQueue2;
    }

    public static String getTopicExchange() {
        return topicExchange;
    }

    public static String getTopicQueue1() {
        return topicQueue1;
    }

    public static String getTopicQueue2() {
        return topicQueue2;
    }


}
