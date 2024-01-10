package com.zust;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDubbo
@EnableScheduling
public class TaskApp {
    public static void main(String[] args) {
        SpringApplication.run(TaskApp.class, args);
    }
}
