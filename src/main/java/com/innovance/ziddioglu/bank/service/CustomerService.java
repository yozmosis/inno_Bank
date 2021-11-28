package com.innovance.ziddioglu.bank.service;

import java.util.List;
import com.innovance.ziddioglu.bank.entity.Customer;

public interface CustomerService {
	
	public List<Customer> findAll();
	
	public Customer findById(int theId);
	
	public void save(Customer theCustomer);
	
	public void deleteById(int theId);

}
