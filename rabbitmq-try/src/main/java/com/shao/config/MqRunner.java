package com.shao.config;

import com.shao.service.AsyncService;
import com.shao.utils.ApplicationContextUtils;
import com.shao.utils.MqConnUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
@Order(1)
public class MqRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws IOException, TimeoutException {
        MqConnUtils.init();
        final AsyncService asyncService = new AsyncService();
//        asyncService.asyncConsumer();
    }
}
