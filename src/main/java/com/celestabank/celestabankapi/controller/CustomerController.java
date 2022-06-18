package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.exeption.DetailsNotFoundException;
import com.celestabank.celestabankapi.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
public class CustomerController {
	private final CustomerService customerService;

	
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@PostMapping("/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@PutMapping("/update")
	public Customer updateCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@DeleteMapping("/delete/{customerId}")
	public boolean deleteCustomer(@PathVariable long customerId) {
		customerService.deleteCustomer(customerId);
		return true;
	}

	@GetMapping("/find/{customerId}")
	public Customer findCustomerById(@PathVariable long customerId) throws DetailsNotFoundException {
		return customerService.findCustomerById(customerId);
	}

	@GetMapping("/allCust")
	public List<Customer> allCust() throws Exception {
		return customerService.getAll();
	}

}
