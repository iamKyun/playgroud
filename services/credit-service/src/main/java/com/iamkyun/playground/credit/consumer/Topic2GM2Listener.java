package com.iamkyun.playground.credit.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(topic = "topic-2", consumerGroup = "consumerGroup-2")
public class Topic2GM2Listener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        log.info("consumerGroup-2: {}", message);
    }
}
