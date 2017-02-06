package com.activemq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static com.activemq.activemq.ActiveMQUtils.*;

/**
 * Created by tengguodong on 2016/9/22.
 */
public class SubscribService {
    public void receive() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination topic = session.createTopic("ActiveMQ-Topic");

        MessageConsumer messageConsumer = session.createConsumer(topic);

        messageConsumer.setMessageListener((Message message) -> {
                TextMessage textMessage = (TextMessage)message;
                if (textMessage != null) {
                    try {
                        System.out.println("收到订阅消息：" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
        });
    }
}
