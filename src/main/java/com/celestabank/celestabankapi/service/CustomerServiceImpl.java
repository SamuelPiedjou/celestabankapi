package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.CustomerAlreadyExistsException;
import com.celestabank.celestabankapi.exeption.NoSuchCustomerExistsException;
import com.celestabank.celestabankapi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository db;
    @Override
    public Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException {
        Customer existingCustomer
                = db.findById(customer.getUserId())
                .orElse(null);
        if (existingCustomer == null) {
            db.save(customer);
            return  customer;
        }else if( existingCustomer.getEmailId().equals(customer.getEmailId())) throw new CustomerAlreadyExistsException("Customer already exixts!!");
        else throw new CustomerAlreadyExistsException("Customer already exixts!!");

    }

    @Override
    public Customer updateCustomer(Customer customer) throws NoSuchCustomerExistsException {
        Customer existingCustomer
                = db.findById(customer.getUserId())
                .orElse(null);
        if (existingCustomer == null)
            throw new NoSuchCustomerExistsException(
                    "No Such Customer Exists!!");
        else {

            db.save(existingCustomer);
            return existingCustomer;
        }
    }

    @Override
    public boolean deleteCustomer(long customerId) throws NoSuchCustomerExistsException {
        log.info("Suppression du client  : "+customerId + "  effectué avec succès");
        Customer existingCustomer
                = db.findById(customerId)
                .orElse(null);
        if (existingCustomer == null)
            throw new NoSuchCustomerExistsException(
                    "No Such Customer Exists!!");
        else {

            db.deleteById(customerId);
            return true;
        }
    }

    @Override
    public Customer findCustomerById(long customerId) throws NoSuchCustomerExistsException {
        log.info("Recherche du client  : "+customerId + "  effectué avec succès");
        Customer existingCustomer
                = db.findById(customerId)
                .orElse(null);
        if (existingCustomer == null)
            throw new NoSuchCustomerExistsException(
                    "No Such Customer Exists!!");
        else {

            db.findById(customerId);
            return existingCustomer;
        }
    }

    @Override
    public List<Customer> getAll() {
        return db.findAll();
    }
}
