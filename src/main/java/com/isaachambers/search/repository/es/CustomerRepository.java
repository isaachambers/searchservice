package com.isaachambers.search.repository.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.isaachambers.search.domain.Customer;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

	Iterable<Customer> findByFirstName(String name);

}
