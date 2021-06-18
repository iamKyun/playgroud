package com.iamkyun.playground.credit.controller;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mq")
public class RocketMQController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @PostMapping("/messages")
    public void sendMessage() {
        //send message synchronously

        //send spring message
        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.convertAndSend("topic-1", "topic-1 " + i);
        }

        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.convertAndSend("topic-2", "topic-2 " + i);
        }

        //rocketMQTemplate.destroy(); // notes:  once rocketMQTemplate be destroyed, you can not send any message again with this rocketMQTemplate

    }

}
