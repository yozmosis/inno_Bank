package com.innovance.ziddioglu.bank.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.innovance.ziddioglu.bank.entity.Customer;
import com.innovance.ziddioglu.bank.service.CustomerService;



@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
private CustomerService customerService;
	
	@Autowired
	public CustomerRestController(CustomerService theCustomerService) {
		customerService = theCustomerService;
	}
	
	
	@GetMapping("/customers")
	public List<Customer> findAll() {
		return customerService.findAll();
	}

	
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer theCustomer = customerService.findById(customerId);
		
		if (theCustomer == null) {
			throw new RuntimeException("Customer id not found - " + customerId);
		}
		
		return theCustomer;
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		
		//set id to 0
		// this is to force a save of new item ... instead of update
		
		theCustomer.setId(0);
		
		customerService.save(theCustomer);
		
		return theCustomer;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		
		Customer existingCustomer = customerService.findById(theCustomer.getId());
		
		if (existingCustomer == null) {
			throw new RuntimeException("No existing customer with that id");
		}
		
		theCustomer.setTckn(existingCustomer.getTckn());
		
		customerService.save(theCustomer);
		
		return theCustomer;
	}
	

}
