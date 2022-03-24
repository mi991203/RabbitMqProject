package com.shao.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.shao.domain.dto.DirectMsgDto;
import com.shao.service.ExchangeService;
import com.shao.utils.MqConnUtils;
import com.shao.utils.PropertyConstance;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Override
    public void sendToDirect(DirectMsgDto directMsgDto) {
        final ConnectionFactory connFactory = MqConnUtils.getInstance().getConnectionFactory();
        try (final Connection conn = connFactory.newConnection(); final Channel channel = conn.createChannel()) {
            channel.basicPublish(PropertyConstance.getDirectExchange(), "direct-key", null, new ObjectMapper().writeValueAsBytes(directMsgDto));
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendToFanout(DirectMsgDto directMsgDto) {
        final ConnectionFactory connFactory = MqConnUtils.getInstance().getConnectionFactory();
        try (final Connection conn = connFactory.newConnection(); final Channel channel = conn.createChannel()) {
            channel.basicPublish(PropertyConstance.getFanoutExchange(), "", null, new ObjectMapper().writeValueAsBytes(directMsgDto));
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendToTopic(DirectMsgDto directMsgDto) {
        final ConnectionFactory connFactory = MqConnUtils.getInstance().getConnectionFactory();
        try (final Connection conn = connFactory.newConnection(); final Channel channel = conn.createChannel()) {
            channel.basicPublish(PropertyConstance.getTopicExchange(), "key.12.3", null, new ObjectMapper().writeValueAsBytes(directMsgDto));
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }


}
