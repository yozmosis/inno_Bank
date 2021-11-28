package com.innovance.ziddioglu.bank.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.innovance.ziddioglu.bank.entity.Account;
import com.innovance.ziddioglu.bank.entity.AccountWithoutCustomer;
import com.innovance.ziddioglu.bank.entity.Customer;
import com.innovance.ziddioglu.bank.entity.Transfer;
import com.innovance.ziddioglu.bank.service.AccountService;
import com.innovance.ziddioglu.bank.service.CustomerService;


@RestController
@RequestMapping("/api")
public class AccountsRestController {
	
	private AccountService accountService;
	private CustomerService customerService;
	
	@Autowired
	public AccountsRestController(AccountService theAccountService, CustomerService theCustomerService) {
		accountService = theAccountService;
		customerService = theCustomerService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/accounts")
	public Account addAccount(@RequestBody AccountWithoutCustomer theAccount) {
		
		 Account newAccount = new Account();
		 
		 Customer accountCustomer = customerService.findById(theAccount.getCustomer_id());
		 
		 if (accountCustomer == null) {
			 throw new
			  RuntimeException("This customer doesn't even exist in the customer table");
		 }
		 
		 List<String> existingCurrenciesForCustomer = accountService.existingCurrencies(theAccount.getCustomer_id());
		 
		 if (existingCurrenciesForCustomer.contains(theAccount.getCurrency())) {
			 throw new
			  RuntimeException("This customer already has an existing " + theAccount.getCurrency() + " account.");
		 }
		 
		 Customer existingCustomer = new Customer();
		  
		 existingCustomer = customerService.findById(theAccount.getCustomer_id());
		  
		 newAccount.setBalance(theAccount.getBalance());
		 newAccount.setCurrency(theAccount.getCurrency());
		 newAccount.setCustomer(existingCustomer);
		  
		 accountService.save(newAccount);
		  
		 return newAccount;
		
	}
	
	@GetMapping("/accounts/{customerId}")
	public List<Account> findAllById(@PathVariable int customerId) {
		return accountService.findAllById(customerId);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/accounts/{accountId}") public String
	deleteCustomerAccount(@PathVariable int accountId) {
	  
	  Account tempAccount = accountService.findById(accountId);
	  
	  if (tempAccount == null) { throw new
	  RuntimeException("Account id not found - " + accountId); }
	  
	  accountService.deleteById(accountId);
	  
	  return "Deleted account id - " + accountId; 
	  
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/transfer/{currency}/{amount}/{fromCustomerId}/{toCustomerId}")
	public String transferFunds(@PathVariable String currency, @PathVariable double amount,  @PathVariable int fromCustomerId, @PathVariable int toCustomerId) {
		
		//try {
	
			Customer originCustomer = customerService.findById(fromCustomerId);
			Customer destinationCustomer = customerService.findById(toCustomerId);
			
			List<String> originCustomerCurrencies = accountService.existingCurrencies(fromCustomerId);
			List<String> destinationCustomerCurrencies = accountService.existingCurrencies(toCustomerId);
			
			if (fromCustomerId == toCustomerId) {
				return "Sender can't send funds to himself";
			}
			
			if (originCustomer == null) {
				return "Sender doesn't exist in customer table";
			}
			
			if (destinationCustomer == null) {
				return "Receiver doesn't exist in customer table";
			}
			
			if (accountService.getBalance(fromCustomerId, currency) < amount) {
				return "Insufficient funds";
			}
			
			if (!originCustomerCurrencies.contains(currency)) {
				throw new
				  RuntimeException("Sender doesn't have a " + currency + " account");
			}
			
			if (!destinationCustomerCurrencies.contains(currency)) {
				throw new
				  RuntimeException("Receiver doesn't have a " + currency + " account");
			}
			
			accountService.transferFunds(currency, amount, fromCustomerId, toCustomerId);
			
			return "Transfer complete between " + originCustomer.getFirstName() + " and " + destinationCustomer.getFirstName() + " for " + amount + " " + currency;
		
		/*} catch (RuntimeException ex) {
			return ex.toString();
		}*/
		
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/transfer")
	public String transferFundsViaBody(@RequestBody Transfer transfer) {
		
		//try {
			
			int fromCustomerId = transfer.getFromCustomerId();
			int toCustomerId = transfer.getToCustomerId();
			String currency = transfer.getCurrency();
			double amount = transfer.getAmount();
	
			Customer originCustomer = customerService.findById(fromCustomerId);
			Customer destinationCustomer = customerService.findById(toCustomerId);
			
			List<String> originCustomerCurrencies = accountService.existingCurrencies(fromCustomerId);
			List<String> destinationCustomerCurrencies = accountService.existingCurrencies(toCustomerId);
			
			if (fromCustomerId == toCustomerId) {
				return "Sender can't send funds to himself";
			}
			
			if (originCustomer == null) {
				return "Sender doesn't exist in customer table";
			}
			
			if (destinationCustomer == null) {
				return "Receiver doesn't exist in customer table";
			}
			
			if (!originCustomerCurrencies.contains(currency)) {
				throw new
				  RuntimeException("Sender doesn't have a " + currency + " account");
			}
			
			if (!destinationCustomerCurrencies.contains(currency)) {
				throw new
				  RuntimeException("Receiver doesn't have a " + currency + " account");
			}
			
			if (accountService.getBalance(fromCustomerId, currency) < amount) {
				return "Insufficient funds";
			}
			
			accountService.transferFunds(currency, amount, fromCustomerId, toCustomerId);
			
			return "Transfer complete between " + originCustomer.getFirstName() + " and " + destinationCustomer.getFirstName() + " for " + amount + " " + currency;
		
			/*
			 * } catch (RuntimeException ex) { return ex.toString(); }
			 */
			
	}
	 

}
