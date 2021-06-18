package com.iamkyun.playground.credit.controller;

import com.iamkyun.playground.credit.model.OrderPaidEvent;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("/mq")
public class RocketMQController {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource

    @PostMapping("/messages")
    public void sendMessage() {
        //send message synchronously
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        //send spring message
        for (int i = 0; i < 1000; i++) {
            rocketMQTemplate
                    .send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        }
        //send message asynchronously
        rocketMQTemplate
                .asyncSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")), new SendCallback() {
                    @Override
                    public void onSuccess(SendResult var1) {
                        System.out.printf("async onSucess SendResult=%s %n", var1);
                    }

                    @Override
                    public void onException(Throwable var1) {
                        System.out.printf("async onException Throwable=%s %n", var1);
                    }

                });
        //Send messages orderly
        rocketMQTemplate
                .syncSendOrderly("orderly_topic", MessageBuilder.withPayload("Hello, World").build(), "hashkey");

        //rocketMQTemplate.destroy(); // notes:  once rocketMQTemplate be destroyed, you can not send any message again with this rocketMQTemplate

    }

}
