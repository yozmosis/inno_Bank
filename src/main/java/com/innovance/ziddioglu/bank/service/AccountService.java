package com.innovance.ziddioglu.bank.service;

import java.util.List;
import com.innovance.ziddioglu.bank.entity.Account;



public interface AccountService {
	
	public List<Account> findAllById(int theCustomerId);

	public void save(Account theAccount);
	
	public void deleteById(int theId);
	
	public Account findById(int theId);
	
	public void transferFunds(String currency, double amount, int fromCustomerId, int toCustomerId);
	
	public double getBalance(int customerId, String currency);
	
	public List<String> existingCurrencies(int customerId);
}
