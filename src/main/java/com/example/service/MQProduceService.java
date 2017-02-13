package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

/**
 * Created by nrq on 2017/1/16.
 */
@Service
public class MQProduceService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void send(String msg){
        this.jmsMessagingTemplate.convertAndSend(this.queue,msg);
    }

}
