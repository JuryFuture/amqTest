package com.activemq.springboot.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by tengguodong on 2016/9/21.
 */
@Component
public class ConsumerService {

    @JmsListener(destination = "${activemq.destination.name}", containerFactory = "queueJmsListenerContainerFactory")
    public void consumeMsg(String msg){
        System.out.println("收到消息：" + msg);
    }
}
