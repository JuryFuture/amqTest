package com.activemq.activemq;

import static com.activemq.activemq.ActiveMQUtils.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by tengguodong on 2016/9/21.
 */
public class ProducerService {

    public void send() throws InterruptedException {
        // 连接工厂
        ConnectionFactory connectionFactory;

        // 连接
        Connection connection = null;

        // 会话
        Session session = null;

        // 队列
        Destination queue;

        // 生产者
        MessageProducer queueMessageProducer = null;

        // 创建连接工厂
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);

        try {
            // 创建连接
            connection = connectionFactory.createConnection();

            // 启动连接
            connection.start();

            /**创建session
             * queue即可以设置transacted为true，也可以是false
             * 设置为true时，消费者可以收到历史的消息；设为false时，消费者只能收到当前会话消息。
             *
             * topic必须设置为false，消费者才能收到消息
             *
             * 设置为false时，不需要调用session.commit()
             * 以上结论是实验结果，不保证正确，可能实验方式不对。
             * */
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 创建消息队列
            queue = session.createQueue("ActiveMQ-Queue");

            // 创建生产者
            queueMessageProducer = session.createProducer(queue);

            queueMessageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // 发送消息
            sendMsg(session, queueMessageProducer, QUEUE);

            // 提交session
            //session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (queueMessageProducer != null) {
                    queueMessageProducer.close();
                }

                if (session != null) {
                    session.close();
                }

                if (connection !=null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
