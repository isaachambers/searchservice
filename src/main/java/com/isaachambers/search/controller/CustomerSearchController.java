package com.isaachambers.search.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaachambers.search.domain.Customer;
import com.isaachambers.search.services.CustomerSearchService;

@RestController
@RequestMapping(path = "customer/search")
public class CustomerSearchController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerSearchController.class);

	private CustomerSearchService customerSearchService;

	@Autowired
	public CustomerSearchController(CustomerSearchService customerSearchService) {
		this.customerSearchService = customerSearchService;
	}

	@PostMapping("")
	public ResponseEntity<Object> saveAll(@RequestBody List<Customer> customers) {
		try {
			long count = customerSearchService.saveCustomers(customers);
			return new ResponseEntity<Object>(count, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex.getCause());
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("")
	public ResponseEntity<Object> getAllCustomers() {
		try {
			List<Customer> customers = customerSearchService.getAllCustomers();
			return new ResponseEntity<Object>(customers, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex.getCause());
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/findbyfname/{fname}")
	public ResponseEntity<Object> findByFirstName(@PathVariable String fname) {
		try {
			List<Customer> customers = customerSearchService.findbyFirstName(fname);
			return new ResponseEntity<Object>(customers, HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex.getCause());
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
