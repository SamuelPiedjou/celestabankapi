package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.CustomerNotFoundException;
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
    public Customer addCustomer(Customer customer) {
        Customer customer1 =db.save(customer);
        return customer1;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer customer1 =db.save(customer);
        return customer1;
    }

    @Override
    public Customer deleteCustomer(long customerId) {
        db.deleteById(customerId);
        return db.findById(customerId).get();
    }

    @Override
    public Customer findCustomerById(long customerId) {
       Customer customer = db.findById(customerId).orElse(null);
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        return db.findAll();
    }
}
