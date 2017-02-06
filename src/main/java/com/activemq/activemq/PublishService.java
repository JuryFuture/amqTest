package com.activemq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static com.activemq.activemq.ActiveMQUtils.*;
/**
 * Created by tengguodong on 2016/9/22.
 */
public class PublishService {
    public void send() throws JMSException, InterruptedException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        /**创建session
         * queue即可以设置transacted为true，也可以是false
         * 设置为true时，消费者可以收到历史的消息；设为false时，消费者只能收到当前会话消息。
         *
         * topic必须设置为false，消费者才能收到消息
         *
         * 以上结论是实验结果，不保证正确，可能实验方式不对。
         * */
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination topic = session.createTopic("ActiveMQ-Topic");

        MessageProducer messageProducer = session.createProducer(topic);

        messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        sendMsg(session, messageProducer, TOPIC);
    }
}
