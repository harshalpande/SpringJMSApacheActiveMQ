package org.javabrains.SpringBootWithExternalJMS.config;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JMSConfig {

	@Value("${active-mq-url}")
	private String activeMQURL;

	@Bean
	public ActiveMQConnectionFactory getConnectionFactory() {
		return new ActiveMQConnectionFactory(activeMQURL);
	}

	@Bean
	@Autowired
	public JmsTemplate getJMSTemplate(ConnectionFactory connectionFactory) {
		return new JmsTemplate(connectionFactory);
	}

}
