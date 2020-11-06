package com.example.mqdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mq")
public class ProviderController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Value("${mq.config.exchange: }")
    private String exchange;

    @GetMapping
    public String send(@RequestParam("p") String param) {
        rabbitTemplate.convertAndSend(exchange, "", "test-fanout:"+param);
        log.info(">>>>> fanout msg send success!");
        return param+", send success!";
    }

    @GetMapping("/topic")
    public String sendTopic(@RequestParam("p") String param) {
        rabbitTemplate.convertAndSend(exchange, "mq.topic", "test-topic:"+param);
        log.error(">>>>> topic msg send success!");
        return param+", send success!";
    }
}
