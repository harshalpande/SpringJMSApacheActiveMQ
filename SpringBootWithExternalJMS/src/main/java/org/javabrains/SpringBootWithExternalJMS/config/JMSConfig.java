package org.javabrains.SpringBootWithExternalJMS.config;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JMSConfig {
	
	@Value("${active-mq-url}")
	String activeMQURL;
	
	@Value("${queue.name}")
	String queueName;
	
	@Bean
	public Queue queue() {
		return new ActiveMQQueue(queueName);
	}
	
	@Bean
	public ActiveMQConnectionFactory getConnectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(activeMQURL);
		return factory;
	}
	
	@Bean
	public JmsTemplate getJMSTemplate() {
		return new JmsTemplate(getConnectionFactory());
	}

}
