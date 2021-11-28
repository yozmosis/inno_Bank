package com.innovance.ziddioglu.bank.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.innovance.ziddioglu.bank.entity.Customer;


@Repository
public class CustomerDAOImpl implements CustomerDAO {

	
	private EntityManager entityManager;
	
	@Autowired
	public CustomerDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	
	@Override
	public List<Customer> findAll() {
		
		Session currentSession = entityManager.unwrap(Session.class);
				
				
		Query<Customer> theQuery =
						currentSession.createQuery("from Customer", Customer.class);
				
				
		List<Customer> customers = theQuery.getResultList();
				
					
		return customers;
	}

	@Override
	public Customer findById(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
				
				
		Customer theCustomer =
						currentSession.get(Customer.class, theId);
				
				
		return theCustomer;
	}

	@Override
	public void save(Customer theCustomer) {
		
		Session currentSession = entityManager.unwrap(Session.class);
				
		currentSession.clear(); //iffy line
		
		currentSession.saveOrUpdate(theCustomer);

	}

	@Override
	public void deleteById(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
						
				
		Query theQuery = 
						currentSession.createQuery(
								"delete from Customer where id=:customerId");
		
		theQuery.setParameter("customerId", theId);
				
		theQuery.executeUpdate();

	}


	@Override
	public boolean customerExists(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		
		Customer theCustomer =
						currentSession.get(Customer.class, theId);
				
		if (theCustomer == null) {
			return false;
		}else {
			return true;
		}
		
	}

}
