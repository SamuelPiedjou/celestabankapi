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
    private final CustomerServiceImpl customerService;
    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer){
        Customer n = null;
        try {
            n = customerService.addCustomer(customer);
        } catch (Exception e) {
            e.getMessage();
        }
        return n;

    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer)  {
        Customer n = null;
        try {
            n = customerService.addCustomer(customer);
        } catch (Exception e) {
            e.getMessage();
        }
        return n;
    }

    @DeleteMapping("/delete/{customerId}")
    public boolean deleteCustomer(@PathVariable long customerId)   {
        boolean n = false;
        try {
             customerService.deleteCustomer(customerId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

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
    @GetMapping("/allCust")
    public List<Customer> allCust()  {
        List<Customer> n = null;
        try {
            n = customerService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;

    }

}
