package com.celestabank.celestabankapi.dto;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.Beneficiary;
import com.celestabank.celestabankapi.entity.User;
import com.celestabank.celestabankapi.enums.Gender;
import com.celestabank.celestabankapi.enums.Role;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CustomerDto {
    private long UserId;
    private String password;
    private String customerName;
    private String phoneNo;
    private String emailId;
    private Date birthday;
    private Date date_Inscription;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private long accountId;

    public CustomerDto(Account account) {
        UserId = account.getCustomer().getUserId();
        this.password = account.getCustomer().getPassword();
        this.customerName = account.getCustomer().getCustomerName();
        this.phoneNo = account.getCustomer().getPhoneNo();
        this.emailId = account.getCustomer().getEmailId();
        this.birthday = account.getCustomer().getBirthday();
        this.gender = account.getCustomer().getGender();
        this.date_Inscription= account.getCustomer().getDate_Inscription();
        this.accountId= getAccountId();
    }


    public CustomerDto() {

    }


}
