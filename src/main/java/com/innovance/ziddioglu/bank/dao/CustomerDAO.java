package com.innovance.ziddioglu.bank.dao;

import java.util.List;
import com.innovance.ziddioglu.bank.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> findAll();
	
	public Customer findById(int theId);
	
	public void save(Customer theEmployee);
	
	public void deleteById(int theId);
	
	public boolean customerExists(int theId);
	

}
