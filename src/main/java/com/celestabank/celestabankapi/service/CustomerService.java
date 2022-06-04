package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    public Customer addCustomer(Customer customer);

    public Customer updateCustomer(Customer customer);

    public Customer deleteCustomer(long customerId);

    public Customer findCustomerById(long customerId);

    public List<Customer> getAll();
}
