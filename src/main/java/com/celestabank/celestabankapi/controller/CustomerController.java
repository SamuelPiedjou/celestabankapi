package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.dto.CustomerDto;
import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.DetailsNotFoundException;
import com.celestabank.celestabankapi.exeption.InvalidDetailsException;
import com.celestabank.celestabankapi.service.CustomerServiceImpl;
import com.celestabank.celestabankapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@AllArgsConstructor
@RestController
public class CustomerController {
    private final CustomerServiceImpl customerService;
    @PostMapping("/add")
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto){
        return  customerService.addCustomer(customerDto);
    }
    @PostMapping("/adds")
    public Customer adCustomer(@RequestBody Customer customer){
        return  customerService.addCust(customer);
    }

    @PutMapping("/update/{idCust}")
    public CustomerDto updateCustomer(@PathVariable long idCust,@RequestBody CustomerDto customerDto)  {
        return customerService.updateCustomer(idCust,customerDto);
    }

    @DeleteMapping("/delete/{customerId}")
    public boolean deleteCustomer(@PathVariable long customerId)   {
        customerService.deleteCustomer(customerId);
        return true;
    }
            //   A NE PAS SUPPRIMER
//    @GetMapping("/find/{customerId}")
//    public Customer findCustomerById(@PathVariable long customerId) {
//       return customerService.findCustomerById(customerId);
//    }
    @GetMapping("/find/{customerId}")
    public CustomerDto showCustomerById(@PathVariable long customerId) {
        return customerService.showCustomerDetails(customerId);
    }
    @GetMapping("/allCust")
    public List<CustomerDto> allCust() {
        return  customerService.getAll();
    }
}
