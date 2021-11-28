package com.innovance.ziddioglu.bank.dao;

import java.util.List;

import com.innovance.ziddioglu.bank.entity.Account;


public interface AccountDAO {

	public void save(Account theAccount);
	
	public List<Account> findAllById(int accountId);
	
	public Account findById(int accountId);
	
	public void deleteById(int theId);
	
	public void adjustBalance(double amount, String currency, int customerId);
	
	public double getBalance(int customerId, String currency);
	
	public List<String> getExistingCustomerCurrencies(int customerId);
	
}
