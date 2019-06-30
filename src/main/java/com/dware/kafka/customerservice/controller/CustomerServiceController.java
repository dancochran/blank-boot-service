package com.dware.kafka.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid; 

import com.dware.kafka.customerservice.model.Customer;
import com.dware.kafka.customerservice.service.CustomerService;
import com.dware.kafka.customerservice.service.exception.ServerErrorException;

@RestController
public class CustomerServiceController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceController.class);
	private final CustomerService customerService;
	
	@Autowired
	public CustomerServiceController(final CustomerService customerService)
	{
		this.customerService = customerService;
	}
	
	@ApiOperation(value = "Retrieve a single customer by id",
			response = Customer.class)	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = {
			"application/json" })
	public Customer getCustomer(@PathVariable String id)
	{
		LOGGER.debug("Received request to fetch customer <" + id + ">");
		Customer customerval = customerService.getCustomer(id);
		
		return customerval;
	}

	@ApiOperation(value = "Update a customer")
	@RequestMapping(value = "/customer", method = RequestMethod.PUT, consumes = {
			"application/json" })
	// DSC - also consider PATCH for updates of only sent fields/keys
	public Customer updateCustomer(@RequestBody @Valid final Customer customer)
	{
		LOGGER.debug("Received request to update customer {}", customer);
		try
		{
			Customer customerval = customerService.updateCustomer(customer);
			return customerval;
		}
		catch (RuntimeException rex)
		{
			LOGGER.error(rex.toString());
			throw rex;
		}
	}
	
	@ApiOperation(value = "Delete a single customer by id.")	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable String id)
	{
		LOGGER.debug("Received request to delete customer <" + id + ">");
		customerService.deleteCustomer(id);
	}

	@ApiOperation(value = "Add a new customer.",
			response = Customer.class)
	@RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = {
			"application/json" })
	public Customer saveCustomer(@RequestBody @Valid final Customer customer)
	{
		LOGGER.debug("Received request to add new customer {}", customer);
		
		return customerService.addCustomer(customer);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleServerErrorException(ServerErrorException e)
	{
		return e.getMessage();
	}	

}
