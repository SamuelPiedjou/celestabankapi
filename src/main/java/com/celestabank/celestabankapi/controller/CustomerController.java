package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.DetailsNotFoundException;
import com.celestabank.celestabankapi.exeption.InvalidDetailsException;
import com.celestabank.celestabankapi.service.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@AllArgsConstructor
@RestController
public class CustomerController {
    CustomerServiceImpl customerService;
    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) throws InvalidDetailsException {
        Customer n = null;
        try {
            n = customerService.addCustomer(customer);
        } catch (Exception e) {
            throw new InvalidDetailsException("The details given are not valid!");
        }
        return n;

    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) throws InvalidDetailsException {
        Customer n = null;
        try {
            n = customerService.updateCustomer(customer);
        } catch (Exception e) {
            throw new InvalidDetailsException("The details given are not valid!");
        }
        return n;
    }

    @DeleteMapping("/delete/{customerId}")
    public Customer deleteCustomer(@PathVariable long customerId) throws DetailsNotFoundException {
        Customer n = null;
        try {
            n = customerService.deleteCustomer(customerId);
        } catch (Exception e) {
            throw new DetailsNotFoundException("The given ID is not found!");
        }
        return n;

    }

    @GetMapping("/find/{customerId}")
    public Customer findCustomerById(@PathVariable long customerId) throws DetailsNotFoundException {
        Customer n = null;
        try {
            n = customerService.findCustomerById(customerId);
        } catch (Exception e) {
            throw new DetailsNotFoundException("The given ID is not found!");
        }
        return n;

    }
}