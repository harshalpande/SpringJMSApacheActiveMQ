package org.javabrains.SpringBootWithExternalJMSTopicConsumer.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
public class ConsumerConfig {

	@Value("${active-mq-url}")
	String activeMQUrl;

	@Bean
	public ConnectionFactory getConnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(activeMQUrl);
		connectionFactory.setUserName("admin");
		connectionFactory.setPassword("admin");
		return connectionFactory;
	}

	@Bean
	@Autowired
	public DefaultJmsListenerContainerFactory getListenerFactory(ConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory listenerContainerFactory = new DefaultJmsListenerContainerFactory();
		listenerContainerFactory.setConnectionFactory(connectionFactory);
		listenerContainerFactory.setPubSubDomain(true);
		return listenerContainerFactory;
	}
	/*public JmsTemplate getJMSTemplate(ConnectionFactory connectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setPubSubDomain(true);
		return jmsTemplate;
	}*/
}
