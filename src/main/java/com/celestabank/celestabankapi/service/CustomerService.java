package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Customer;

import java.util.List;

public interface CustomerService {
      Customer addCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    boolean deleteCustomer(long customerId);

      Customer findCustomerById(long customerId);

      List<Customer> getAll();
}
