package org.javabrains.SpringBootWithExternalJMSTopicConsumer.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@Value("${topic-destination}")
	String topicDestination;

	@JmsListener(destination = "${topic-destination}", containerFactory="getListenerFactory")
	public void getTopicMessage(String topicMessage) {
		System.out.println(topicMessage);
	}
	/*
	@JmsListener(destination = "${topic-destination}")
	public void getTopicMessage1(String topicMessage) {
		System.out.println(topicMessage);
	}*/

}
