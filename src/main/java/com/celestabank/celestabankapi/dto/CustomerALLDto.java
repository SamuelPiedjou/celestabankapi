package com.celestabank.celestabankapi.dto;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.enums.Gender;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
public class CustomerALLDto {
    private long UserId;
    private String password;
    private String customerName;
    private String phoneNo;
    private String emailId;
    private Date birthday;
    private Date date_inscription;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private List<AccountALLDto> accountALLDtos;
    private List<BeneficiaryDTO> beneficiaryDTOS;


    public CustomerALLDto(Customer customer) {
        UserId = customer.getUserId();
        this.password = customer.getPassword();
        this.customerName = customer.getCustomerName();
        this.phoneNo = customer.getPhoneNo();
        this.emailId = customer.getEmailId();
        this.birthday = customer.getBirthday();
        this.gender = customer.getGender();
        this.date_inscription= customer.getDate_Inscription();
        this.accountALLDtos= customer.getAccount().stream().map(AccountALLDto::new).collect(Collectors.toList());
        this.beneficiaryDTOS= customer.getBeneficiaries().stream().map(beneficiary -> new BeneficiaryDTO(beneficiary,UserId)).collect(Collectors.toList());
    }


    public CustomerALLDto() {

    }


}
