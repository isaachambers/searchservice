package com.isaachambers.search.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaachambers.search.domain.Customer;
import com.isaachambers.search.repository.es.CustomerRepository;

@Service
public class CustomerSearchService {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerSearchService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public long saveCustomers(List<Customer> customers) {
		this.customerRepository.saveAll(customers);
		return this.customerRepository.count();
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		this.customerRepository.findAll().forEach(customer -> {
			customers.add(customer);
		});
		return customers;
	}

	public List<Customer> findbyFirstName(String name) {
		List<Customer> customers = new ArrayList<>();
		this.customerRepository.findByFirstName(name).forEach(customer -> {
			customers.add(customer);
		});
		return customers;
	}

}
