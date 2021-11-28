package com.innovance.ziddioglu.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.innovance.ziddioglu.bank.dao.AccountDAO;
import com.innovance.ziddioglu.bank.dao.CustomerDAO;
import com.innovance.ziddioglu.bank.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {
	
	//private CustomerDAO customerDAO;
	private AccountDAO accountDAO;
	
	
	public AccountServiceImpl(CustomerDAO theCustomerDAO, AccountDAO theAccountDAO) {
		//customerDAO = theCustomerDAO;
		accountDAO = theAccountDAO;
	}


	@Override
	@Transactional
	public void save(Account theAccount) {
		accountDAO.save(theAccount);
		
	}


	@Override
	@Transactional
	public List<Account> findAllById(int theCustomerId) {
		
		return accountDAO.findAllById(theCustomerId);
	}


	@Override
	@Transactional
	public void deleteById(int theId) {
		
		accountDAO.deleteById(theId);
		
	}


	@Override
	@Transactional
	public Account findById(int theId) {
		return accountDAO.findById(theId);
	}


	@Override
	@Transactional
	public void transferFunds(String currency, double amount, int fromCustomerId, int toCustomerId) {
		
		accountDAO.adjustBalance((amount*-1), currency, fromCustomerId);
		accountDAO.adjustBalance(amount, currency, toCustomerId);
		
	}


	@Override
	@Transactional
	public double getBalance(int customerId, String currency) {
		
		return accountDAO.getBalance(customerId, currency);
	}


	@Override
	@Transactional
	public List<String> existingCurrencies(int customerId) {
		
		return accountDAO.getExistingCustomerCurrencies(customerId);
		
	}
	
}
