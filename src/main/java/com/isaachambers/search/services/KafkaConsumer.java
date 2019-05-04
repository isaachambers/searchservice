package com.isaachambers.search.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isaachambers.search.domain.Customer;
import com.isaachambers.search.feignclients.CustomerServiceFeignClient;
import com.isaachambers.search.model.KafkaMessage;
import com.isaachambers.search.model.MessageType;

@Service
public class KafkaConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private CustomerServiceFeignClient customerServiceFeignClient;

	@Autowired
	private CustomerSearchService customerSearchService;

	@KafkaListener(topics = "${kafka.search.topic}", groupId = "${kafka.search.group.id}")
	public void listen(String message) {

		try {
			KafkaMessage kfm = mapper.readValue(message, KafkaMessage.class);
			logger.info(kfm.toString());
			if (kfm.getMessageType().equals(MessageType.NEW_CUSTOMER)) {
				Customer c = customerServiceFeignClient.getCustomerById(Long.parseLong(kfm.getResourceIdentifier()));
				logger.info(c.getFirstName());
				customerSearchService.saveCustomer(
						new Customer(null, c.getFirstName(), c.getSecondName(), c.getCountry(), c.getAge()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
		}
	}

}
