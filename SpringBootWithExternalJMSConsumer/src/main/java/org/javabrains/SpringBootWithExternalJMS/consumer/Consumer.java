package org.javabrains.SpringBootWithExternalJMS.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@JmsListener(destination = "simple-jms-queue")
	public void getMessageFromQueue(String message) {
		System.out.println(message);
	}

}
