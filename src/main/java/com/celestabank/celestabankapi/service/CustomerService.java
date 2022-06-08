package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.CustomerAlreadyExistsException;
import com.celestabank.celestabankapi.exeption.NoSuchCustomerExistsException;

import java.util.List;

public interface CustomerService {
      Customer addCustomer(Customer customer) throws CustomerAlreadyExistsException;

    Customer updateCustomer(Customer customer) throws NoSuchCustomerExistsException;

    boolean deleteCustomer(long customerId) throws NoSuchCustomerExistsException;

      Customer findCustomerById(long customerId) throws NoSuchCustomerExistsException;

      List<Customer> getAll();
}
