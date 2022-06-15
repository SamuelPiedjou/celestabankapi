package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.CustomerAlreadyExistsException;
import com.celestabank.celestabankapi.exeption.NoSuchCustomerExistsException;
import com.celestabank.celestabankapi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository db;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public CustomerServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer addCustomer(Customer customer)  {
        log.info("Suppression du client  : "+customer + "  effectué avec succès");
        Customer existingCustomer
                = db.findById(customer.getUserId())
                .orElse(null);
        if (existingCustomer == null) {
            existingCustomer = customer;
            existingCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
//            existingCustomer.setBirthday(customer.getBirthday());
//            existingCustomer.setEmailId(customer.getEmailId());
//            existingCustomer.setCustomerName(customer.getCustomerName());
//            existingCustomer.setGender(customer.getGender());
//            existingCustomer.setPhoneNo(customer.getPhoneNo());
//            existingCustomer.setRole(customer.getRole());
            db.save(existingCustomer);
            log.info("Suppression du client  : "+customer + "  effectué avec succès");
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
