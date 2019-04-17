package org.javabrains.SpringBootWithExternalJMS.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {

	@Autowired
	public JmsTemplate jmsTemplate;
	
	@Value("${queue.name}")
	String queueName;

	@RequestMapping(method = RequestMethod.GET, path = "jms/{message}")
	public String sendMessageToQueue(@PathVariable(name = "message") String message) {
		jmsTemplate.convertAndSend(queueName, message);
		return "Your message <b>" + message + "</b> published successfully";
	}

}
