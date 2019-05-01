package com.isaachambers.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.isaachambers.search.domain.Customer;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

	
}
