package org.javabrains.SpringBootWithExternalJMSTopicProducer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/webapi")
public class TopicProducer {
	
	@Value("${topic-destination}")
	String topicDestination;
	
	@Autowired
	JmsTemplate jmsTemplate;

	@RequestMapping(method=RequestMethod.GET, value="/{topicString}")
	public void postTopicMessage(@PathVariable("topicString") String topicString) {
		jmsTemplate.convertAndSend(topicDestination, topicString);
	}

}
