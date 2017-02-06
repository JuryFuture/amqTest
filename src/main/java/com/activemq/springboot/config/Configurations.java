package com.activemq.springboot.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

/**
 * Created by tengguodong on 2016/9/21.
 */
@Configuration
public class Configurations {
    @Value("${activemq.destination.name}")
    public String destination;

    @Bean
    public Destination queue() {
        return new ActiveMQQueue(destination);
    }

    @Bean
    public DefaultJmsListenerContainerFactory topicJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        jmsListenerContainerFactory.setPubSubDomain(true);
        return jmsListenerContainerFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory queueJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory jmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        jmsListenerContainerFactory.setPubSubDomain(false);
        return jmsListenerContainerFactory;
    }
}
