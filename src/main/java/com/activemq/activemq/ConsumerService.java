package com.activemq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

import static com.activemq.activemq.ActiveMQUtils.*;

/**
 * Created by tengguodong on 2016/9/21.
 */
public class ConsumerService {

    public void receive() {

        // 连接工厂
        ConnectionFactory connectionFactory;

        // 连接
        Connection connection;

        // 会话
        Session session = null;

        // 队列
        Destination queue;

        // 消费者
        MessageConsumer queueMessageConsumer = null;

        // 创建连接工厂
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);

        try {
            // 创建连接
            connection = connectionFactory.createConnection();

            // 启动连接
            connection.start();

            // 创建session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 创建消息队列
            queue = session.createQueue("ActiveMQ-Queue");

            // 创建消费者
            queueMessageConsumer = session.createConsumer(queue);

            while (true) {
                TextMessage textMessage = (TextMessage)queueMessageConsumer.receive();
                if (textMessage != null) {
                    System.out.println("收到点对点消息：" + textMessage.getText());
                } else {
                    break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (queueMessageConsumer != null) {
                    queueMessageConsumer.close();
                }

                if (session != null) {
                    session.close();
                }
            }catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

}
