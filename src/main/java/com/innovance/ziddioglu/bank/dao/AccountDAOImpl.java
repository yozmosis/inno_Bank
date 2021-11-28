package com.innovance.ziddioglu.bank.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.innovance.ziddioglu.bank.entity.Account;
import com.innovance.ziddioglu.bank.entity.Customer;


@Repository
public class AccountDAOImpl implements AccountDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public AccountDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public void save(Account theAccount) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(theAccount);

	}

	@Override
	public List<Account> findAllById(int theCustomerId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		
		Query<Account> theQuery =
						currentSession.createQuery("from Account where customer_id=:customerId", Account.class);
				
		theQuery.setParameter("customerId", theCustomerId);
		
		List<Account> accounts = theQuery.getResultList();
						
		return accounts;
	}

	@Override
	public void deleteById(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		
		Query theQuery = 
						currentSession.createQuery(
								"delete from Account where id=:accountId");
		
		theQuery.setParameter("accountId", theId);
				
		theQuery.executeUpdate();
		
	}

	@Override
	public Account findById(int accountId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		
		Account theAccount =
						currentSession.get(Account.class, accountId);
				
				
		return theAccount;
	}

	@Override
	public void adjustBalance(double amount, String currency, int customerId) {
		
		Session currentSession = entityManager.unwrap(Session.class);

		
		Query theQuery =  
						currentSession.createQuery(
								"update Account set balance = balance + :theAmount where (currency=:theCurrency and customer_id=:theCustomerId)");
		
		theQuery.setParameter("theAmount", amount);
		theQuery.setParameter("theCurrency", currency);
		theQuery.setParameter("theCustomerId", customerId);
				
		theQuery.executeUpdate();
		
		
	}

	@Override
	public double getBalance(int customerId, String currency) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		
		Query<Double> theQuery =
						currentSession.createQuery("select balance from Account where customer_id=:customerId and currency=:theCurrency", Double.class);
				
		theQuery.setParameter("customerId", customerId);
		theQuery.setParameter("theCurrency", currency);
		
		List<Double> customerBalance = theQuery.getResultList();
		
		if (customerBalance.size() == 0) {
			return 0;
		}
		
		return customerBalance.get(0);
	}

	@Override
	public List<String> getExistingCustomerCurrencies(int customerId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		
		Query theQuery =
						currentSession.createQuery("select currency from Account where customer_id=:theCustomerId", String.class);
				
		theQuery.setParameter("theCustomerId", customerId);
		
		List<String> existingCurrencies = theQuery.getResultList();
						
		return existingCurrencies;
	}

	

	

}
