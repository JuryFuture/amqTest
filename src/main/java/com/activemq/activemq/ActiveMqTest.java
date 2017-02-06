package com.activemq.activemq;

import org.apache.activemq.ActiveMQConnection;

import javax.jms.JMSException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by tengguodong on 2017/1/4.
 */
public class ActiveMqTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ProducerService producerService = new ProducerService();
                try {
                    producerService.send();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ConsumerService consumerService = new ConsumerService();
                consumerService.receive();
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                PublishService publishService = new PublishService();
                try {
                    publishService.send();
                } catch (JMSException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {
                SubscribService subscribService = new SubscribService();
                try {
                    subscribService.receive();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.shutdown();
    }
}
