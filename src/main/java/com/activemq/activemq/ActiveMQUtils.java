package com.activemq.activemq;

import org.apache.activemq.ActiveMQConnection;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by tengguodong on 2016/9/21.
 */
public class ActiveMQUtils {
    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;


    public static final int QUEUE = 1;
    public static final int TOPIC = 2;

    /**
     * 发送消息
     * @param session
     * @param messageProducer
     * @throws JMSException
     */
    public static void sendMsg(Session session, MessageProducer messageProducer, int mqType) throws JMSException, InterruptedException {
        for (int i = 0; i< 10; i++) {
            TextMessage textMessage = session.createTextMessage("ActiveMQ消息" + i);
            switch (mqType) {
                case QUEUE:
                    System.out.println("发送点对点消息：" + textMessage.getText());
                    break;
                case TOPIC:
                    System.out.println("发送订阅消息：" + textMessage.getText());
                    break;
                default:
                    break;
            }
            Thread.sleep(1000);
            messageProducer.send(textMessage);
        }
    }
}
