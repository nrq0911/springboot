package com.example.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * Created by nrq on 2017/1/16.
 */
@Service
public class MQConsumerService {

    private String text;

    @JmsListener(destination = "sample.queue")
    public void receviceQueue(String txt){
        this.text = txt;
    }

    public String recevice(){
        return text;
    }

}
