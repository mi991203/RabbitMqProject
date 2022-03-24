package com.shao.utils;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.core.env.Environment;

import java.util.Objects;

public class MqConnUtils {
    private static class Singleton {
        private static MqConnUtils mqConnUtils;

        static  {
            mqConnUtils = new MqConnUtils();
        }

        public static MqConnUtils getMqConnUtils() {
            return mqConnUtils;
        }
    }

    public static MqConnUtils getInstance() {
        return Singleton.getMqConnUtils();
    }

    public static void init() {
        getInstance();
    }

    private final ConnectionFactory connectionFactory;

    public MqConnUtils() {
        // 创建连接
        final ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(PropertyConstance.getHost());
        connectionFactory.setPort(PropertyConstance.getPort());
        connectionFactory.setUsername(PropertyConstance.getUsername());
        connectionFactory.setPassword(PropertyConstance.getPassword());
        this.connectionFactory = connectionFactory;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

}
