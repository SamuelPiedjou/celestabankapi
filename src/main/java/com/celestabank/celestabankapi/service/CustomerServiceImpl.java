package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.CustomerAlreadyExistsException;
import com.celestabank.celestabankapi.exeption.NoSuchCustomerExistsException;
import com.celestabank.celestabankapi.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
	private CustomerRepository db;
	private final Logger log = LoggerFactory.getLogger(getClass());

	public CustomerServiceImpl(CustomerRepository db) {
		super();
		this.db = db;
	}

	/**
	 * la methode ci-dessous ne reponds pas au single responsability
	 */
	@Override
	public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException {
		Customer existingCustomer = db.findById(customer.getUserId()).orElse(null);
		if (existingCustomer == null) {
			db.save(customer);
			return customer;
		} else if (existingCustomer.getEmailId().equals(customer.getEmailId()))
			throw new CustomerAlreadyExistsException("Customer already exixts!!");
		else
			throw new CustomerAlreadyExistsException("Customer already exixts!!");
	}

	@Override
	public Customer updateCustomer(Customer customer) throws NoSuchCustomerExistsException {
		Customer existingCustomer = db.findById(customer.getUserId()).orElse(null);
		if (existingCustomer != null) {
			db.save(existingCustomer);
			return existingCustomer;
		}
		throw new NoSuchCustomerExistsException("No Such Customer Exists!!");
	}

	@Override
	public boolean deleteCustomer(long customerId) throws NoSuchCustomerExistsException {
		log.info("Suppression du client  : " + customerId + "  effectué avec succès");
		Customer existingCustomer = db.findById(customerId).orElse(null);
		if (existingCustomer == null) {
			db.deleteById(customerId);
			return true;
		}
		throw new NoSuchCustomerExistsException("No Such Customer Exists!!");

	}

	@Override
	public Customer findCustomerById(long customerId) throws NoSuchCustomerExistsException {
		log.info("Recherche du client  : " + customerId + "  effectué avec succès");
		Customer existingCustomer = db.findById(customerId).orElse(null);
		if (existingCustomer != null)
			return existingCustomer;
		throw new NoSuchCustomerExistsException("No Such Customer Exists!!");
	}

	@Override
	public List<Customer> getAll() {
		return db.findAll();
	}
}
