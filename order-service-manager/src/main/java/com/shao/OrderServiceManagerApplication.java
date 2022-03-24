package com.shao;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@EnableAsync
public class OrderServiceManagerApplication {

    public static void main(String[] args) throws IOException, TimeoutException {
        SpringApplication.run(OrderServiceManagerApplication.class, args);

    }
}
