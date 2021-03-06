package com.celestabank.celestabankapi.mappers;

import com.celestabank.celestabankapi.dto.AccountDto;
import com.celestabank.celestabankapi.dto.AtmDTO;
import com.celestabank.celestabankapi.dto.BeneficiaryDTO;
import com.celestabank.celestabankapi.dto.CustomerDto;
import com.celestabank.celestabankapi.entity.ATM;
import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.Beneficiary;
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
    public AccountDto fromAccount(Account account){
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(account,accountDto);
        return accountDto;
    }
    public Account fromAccountDto(AccountDto accountDto){
        Account account = new Account();
        BeanUtils.copyProperties(accountDto, account);
        return account;
    }
    public BeneficiaryDTO fromBeneficiary(Beneficiary beneficiary){
        BeneficiaryDTO beneficiaryDTO = new BeneficiaryDTO();
        BeanUtils.copyProperties(beneficiary, beneficiaryDTO);
        return beneficiaryDTO;
    }
    public Beneficiary fromBeneficiaryDto(BeneficiaryDTO beneficiaryDTO){
        Beneficiary beneficiary = new Beneficiary();
        BeanUtils.copyProperties(beneficiaryDTO, beneficiary);
        return beneficiary;
    }
}
