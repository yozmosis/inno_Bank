package com.innovance.ziddioglu.bank.entity;




public class Transfer {
	
	private String currency;
	private double amount;
	private int fromCustomerId;
	private int toCustomerId;

	public Transfer() {
		
	}

	public Transfer(String currency, double amount, int fromCustomerId, int toCustomerId) {
		this.currency = currency;
		this.amount = amount;
		this.fromCustomerId = fromCustomerId;
		this.toCustomerId = toCustomerId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getFromCustomerId() {
		return fromCustomerId;
	}

	public void setFromCustomerId(int fromCustomerId) {
		this.fromCustomerId = fromCustomerId;
	}

	public int getToCustomerId() {
		return toCustomerId;
	}

	public void setToCustomerId(int toCustomerId) {
		this.toCustomerId = toCustomerId;
	}
	
}
