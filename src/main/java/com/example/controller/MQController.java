package com.example.controller;

import com.example.service.MQConsumerService;
import com.example.service.MQProduceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nrq on 2017/1/16.
 */
@RestController
public class MQController {

    @Autowired
    private MQProduceService produceService;
    @Autowired
    private MQConsumerService consumerService;

    @RequestMapping("/send")
    public String send(){
        produceService.send("this is active message");
        return "send";
    }

    @RequestMapping("/receive")
    public String receive(){
        String str = consumerService.recevice();
        return str;
    }

}
