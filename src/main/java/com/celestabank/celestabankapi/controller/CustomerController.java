package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.dto.CustomerALLDto;
import com.celestabank.celestabankapi.dto.CustomerDto;
import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.service.CustomerServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@AllArgsConstructor
@RestController
@CrossOrigin(value = "*")
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
    public CustomerALLDto showCustomerById(@PathVariable long customerId) {
        return customerService.showCustomerDetails(customerId);
    }
    @GetMapping("/allCust")
    @ApiOperation(value = "CONSULTER LA LISTE DES CLIENTS ")
    public List<CustomerDto> allCust() {
        return  customerService.getAll();
    }

    @GetMapping("/allCustDetails")
    @ApiOperation(value = "CONSULTER LA LISTE DES CLIENTS AVEC LEURS COMPTES")
    public List<CustomerALLDto> allCustWitDetailsAcc() {
        return  customerService.getAllCustWithDetailsAcc();
    }
}
