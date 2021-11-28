package com.innovance.ziddioglu.bank.entity;



public class AccountWithoutCustomer {

	
	private String currency;
	private long balance;
	private int customer_id;
	
	public AccountWithoutCustomer() {
		
	}

	public AccountWithoutCustomer(String currency, long balance, int customer_id) {
		this.currency = currency;
		this.balance = balance;
		this.customer_id = customer_id;
	}


	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	@Override
	public String toString() {
		return "AccountWithoutCustomer [currency=" + currency + ", balance=" + balance + ", customer_id=" + customer_id
				+ "]";
	}

	
}
