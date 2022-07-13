package com.celestabank.celestabankapi.dto;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.enums.Gender;
import com.celestabank.celestabankapi.enums.Role;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
public class CustomerDto {
    private long UserId;
    private String password;
    private String customerName;
    private String phoneNo;
    private String emailId;
    private Date birthday;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public CustomerDto(Account account) {
        UserId = account.getCustomer().getUserId();
        this.password = account.getCustomer().getPassword();
        this.customerName = account.getCustomer().getCustomerName();
        this.phoneNo = account.getCustomer().getPhoneNo();
        this.emailId = account.getCustomer().getEmailId();
        this.birthday = account.getCustomer().getBirthday();
        this.gender = account.getCustomer().getGender();
    }

    public CustomerDto() {

    }
}
