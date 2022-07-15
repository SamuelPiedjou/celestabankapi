package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.dto.CustomerALLDto;
import com.celestabank.celestabankapi.dto.CustomerDto;
import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.CustomerNotFoundException;
import com.celestabank.celestabankapi.exeption.NoSuchCustomerExistsException;
import com.celestabank.celestabankapi.mappers.BankServiceMapper;
import com.celestabank.celestabankapi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository db;
    private final PasswordEncoder passwordEncoder;
    private final BankServiceMapper dtoMappers;

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto)  {
        log.info("Ajout du client  : "+customerDto + "  en cours de traitement");
         Customer customer = dtoMappers.fromCustomerDto(customerDto);
         customer.setDate_Inscription(new Date());
         db.save(customer);
         return dtoMappers.fromCustomer(customer);
    }
    @Override
    public  Customer addCust(Customer customer){
        return  db.save(customer);
    }
    @Override
    public CustomerDto updateCustomer(long idCust, CustomerDto customerDto){
        if (findCustomerById(idCust)!=null){
            Customer exist = findCustomerById(idCust);
            exist.setUserId(idCust);
            exist.setEmailId(customerDto.getEmailId());
            exist.setGender(customerDto.getGender());
            exist.setPassword(customerDto.getPassword());
            exist.setCustomerName(customerDto.getCustomerName());
            exist.setPhoneNo(customerDto.getPhoneNo());
            db.save(exist);
            return  dtoMappers.fromCustomer(exist);
        }
        return null;
    }

    @Override
    public boolean deleteCustomer(long customerId) {
       if (findCustomerById(customerId)!= null){
           db.deleteById(customerId);
           return  true;
       }
       return  false;
    }

    @Override
    public Customer findCustomerById(long customerId) throws NoSuchCustomerExistsException {
        log.info("Recherche du client  : "+customerId + "  effectué avec succès");
        Customer customer = db.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
        return customer;
    }

    public CustomerDto findCustById(long customerId) throws NoSuchCustomerExistsException {
        log.info("Recherche du client  : "+customerId + "  effectué avec succès");
        Customer customer = db.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
        return dtoMappers.fromCustomer(customer);
    }

    @Override
    public CustomerALLDto showCustomerDetails(long customerId) throws NoSuchCustomerExistsException {
        log.info("Recherche du client  : "+customerId + "  effectué avec succès");
        Customer customer = db.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
        CustomerALLDto customerALLDto = new CustomerALLDto(customer);
        return customerALLDto;
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customers = db.findAll();
        List<CustomerDto> customerDtos= customers.stream().map(customer-> dtoMappers.fromCustomer(customer)).collect(Collectors.toList());
        return customerDtos;
    }
    @Override
    public List<CustomerALLDto> getAllCustWithDetailsAcc() {
        List<Customer> customers = db.findAll();
        List<CustomerALLDto> customerALLDtos= customers.stream().map(customer-> new CustomerALLDto(customer)).collect(Collectors.toList());
        return customerALLDtos;
    }
}
