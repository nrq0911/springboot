package com.example.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * Created by nrq on 2017/1/16.
 */
@EnableJms
@Configuration
public class ActiveMQConfig {

    @Bean  //配置一个消息队列
    public Queue queue() {
        return new ActiveMQQueue("sample.queue");
    }
}
