package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Customer;
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
        log.info("AJout de : "+customer + "comme client effectué avec succès");
        Customer customer1 =db.save(customer);
        return customer1;
    }

    @Override
    public Customer updateCustomer(Customer customer)  {
        log.info("Mise a jour des info du client : "+customer + "  effectué avec succès");
         db.save(customer);
        return customer;
    }

    @Override
    public boolean deleteCustomer(long customerId) {
        log.info("Suppression du client  : "+customerId + "  effectué avec succès");
        db.deleteById(customerId);
        return true;
    }

    @Override
    public Customer findCustomerById(long customerId) {
        log.info("Recherche du client  : "+customerId + "  effectué avec succès");
       Customer customer = db.findById(customerId).orElse(null);
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        return db.findAll();
    }
}
