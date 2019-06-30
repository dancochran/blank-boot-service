package com.dware.kafka.customerservice.service;

import com.dware.kafka.customerservice.model.Customer;
import com.dware.kafka.customerservice.service.exception.ServerErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service; 
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CustomerServiceImpl implements CustomerService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Override
	public Customer getCustomer(String customerId)
	{
		LOGGER.debug("CustomerServiceImpl::getCustomer(): retrieving customer <" + customerId + ">");
		return(new Customer("1234", "Dan", "Manson", "danman@invalidemail.com"));
	}

	@Override
	public Customer updateCustomer(Customer customer)
	{
		LOGGER.debug("CustomerServiceImpl::updateCustomer(): updating customer " + customer.toString());
		return customer;
	}

	@Override
	public Customer addCustomer(Customer customer)
	{
		LOGGER.debug("CustomerServiceImpl::addCustomer(): adding customer " + customer.toString());
		return(new Customer("1234", "Dan", "Manson", "danman@invalidemail.com"));
	}

	@Override
	public void deleteCustomer(String customerId)
	{
		LOGGER.debug("CustomerServiceImpl::getCustomer(): deleting customer <" + customerId + ">");
		throw new ServerErrorException("testing exception chain");
	}

}
