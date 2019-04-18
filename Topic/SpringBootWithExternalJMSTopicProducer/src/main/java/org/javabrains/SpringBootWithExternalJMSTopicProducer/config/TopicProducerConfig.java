package org.javabrains.SpringBootWithExternalJMSTopicProducer.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class TopicProducerConfig {

	@Value("${active-mq-url}")
	String activeMQUrl;

	@Bean
	public ActiveMQConnectionFactory getConnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(activeMQUrl);
		connectionFactory.setUserName("admin");
		connectionFactory.setPassword("admin");
		return connectionFactory;
	}

	@Bean
	@Autowired
	public CachingConnectionFactory cachingConnectionFactory(ConnectionFactory connectionFactory) {
		return new CachingConnectionFactory(connectionFactory);
	}

	@Bean
	@Autowired
	public JmsTemplate getJMSTemplate(CachingConnectionFactory cachingConnectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory);
		jmsTemplate.setPubSubDomain(true); // for Topic, if unset Queue is used
		return jmsTemplate;
	}

}
