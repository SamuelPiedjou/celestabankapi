package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.dto.CustomerALLDto;
import com.celestabank.celestabankapi.dto.CustomerDto;
import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.NoSuchCustomerExistsException;

import java.util.List;

public interface CustomerService {

    CustomerDto addCustomer(CustomerDto customerDto);

    Customer addCust(Customer customer);

    CustomerDto updateCustomer(long idCust, CustomerDto customerDto);

    boolean deleteCustomer(long customerId);

    Customer findCustomerById(long customerId) throws NoSuchCustomerExistsException;

    CustomerALLDto showCustomerDetails(long customerId) throws NoSuchCustomerExistsException;

    List<CustomerDto> getAll();

    List<CustomerALLDto> getAllCustWithDetailsAcc();
}
