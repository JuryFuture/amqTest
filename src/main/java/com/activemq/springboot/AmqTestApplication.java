package com.activemq.springboot;

import com.activemq.springboot.service.ProducerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AmqTestApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AmqTestApplication.class, args);

		ProducerService producerService = (ProducerService)context.getBean("producerService");

		for (int i = 0; i < 10; i++) {
			producerService.sendMsg("test" + i);
			System.out.println("发送消息：" + "test" + i);
		}
	}
}
