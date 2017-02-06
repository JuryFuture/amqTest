package com.activemq.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * Created by tengguodong on 2016/9/21.
 */
@Component
public class ProducerService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    Destination queue;

    public void sendMsg(String msg) {
        jmsMessagingTemplate.convertAndSend(queue, msg);
    }
}
