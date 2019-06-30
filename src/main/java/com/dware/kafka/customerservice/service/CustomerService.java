package com.dware.kafka.customerservice.service;

import com.dware.kafka.customerservice.model.Customer;

public interface CustomerService
{
	Customer getCustomer(String customerId);
	Customer updateCustomer(Customer customer);
	Customer addCustomer(Customer customer);
	void deleteCustomer(String customerId);
}
