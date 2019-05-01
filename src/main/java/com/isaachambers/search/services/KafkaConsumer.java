package com.isaachambers.search.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	
	@KafkaListener(topics = "${kafka.search.topic}", groupId = "${kafka.search.group.id}")
	public void listen(String message) {
		System.out.println(message);
	}

}
