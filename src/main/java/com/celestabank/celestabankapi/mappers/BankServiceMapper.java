package com.celestabank.celestabankapi.mappers;

import com.celestabank.celestabankapi.dto.AtmDTO;
import com.celestabank.celestabankapi.dto.CustomerDto;
import com.celestabank.celestabankapi.entity.ATM;
import com.celestabank.celestabankapi.entity.Customer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankServiceMapper {

    public AtmDTO fromATM(ATM atm){
        AtmDTO atmDTO = new AtmDTO();
        BeanUtils.copyProperties(atm, atmDTO);
        return atmDTO;
    }

    public ATM fromAtmDTO(AtmDTO atmDTO){
        ATM atm = new ATM();
        BeanUtils.copyProperties(atmDTO, atm);
        return atm;
    }
    public CustomerDto fromCustomer(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer,customerDto);
        return customerDto;
    }
    public Customer fromCustomerDto(CustomerDto customerDto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto,customer);
        return customer;
    }
}
