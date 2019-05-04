package com.isaachambers.search.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isaachambers.search.domain.Customer;

@FeignClient(name = "CUSTOMERSERVICE")
public interface CustomerServiceFeignClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/customer/{customerId}")
	Customer getCustomerById(@PathVariable("customerId") Long customerId);

}
