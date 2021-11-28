package com.innovance.ziddioglu.bank.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	
	public Customer() {
		
	}
	
	public Customer(String firstName, String lastName, String email, long tckn) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.tckn = tckn;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="tckn")
	private long tckn;
	
	//@OneToMany(mappedBy="customer", cascade= {CascadeType.REMOVE})
	//ToMany(mappedBy="customer")
	//private List<Account> accounts;
	

	
	/*
	 * public List<Account> getAccounts() { return accounts; }
	 * 
	 * public void setAccounts(List<Account> accounts) { this.accounts = accounts; }
	 */
	 

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getTckn() {
		return tckn;
	}

	public void setTckn(long tckn) {
		this.tckn = tckn;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", tckn=" + tckn + "]";
	}
	
}
