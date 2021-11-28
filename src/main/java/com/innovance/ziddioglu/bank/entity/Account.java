package com.innovance.ziddioglu.bank.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	
	public Account() {
		
	}
	
	public Account(String currency, double balance, Customer customer) {
		this.currency = currency;
		this.balance = balance;
		this.customer = customer;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="balance")
	private double balance;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	@Override
	public String toString() {
		return "account [id=" + id + ", currency=" + currency + ", balance=" + balance + ", customer=" + customer + "]";
	}
	
	

}
