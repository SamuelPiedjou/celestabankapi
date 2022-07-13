package com.celestabank.celestabankapi.dto;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.Customer;
import com.celestabank.celestabankapi.enums.AccountStatus;
import com.celestabank.celestabankapi.enums.AccountType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class AccountDto {
    private long accountId;
    private double balance;
    private Date createdAt;
    private AccountStatus accountStatus;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private CustomerDto customerDto;

    public AccountDto(Account account){
        this.accountId= account.getAccountId();
        this.accountStatus= account.getAccountStatus();
        this.accountType= account.getAccountType();
        this.balance=account.getBalance();
        this.createdAt=account.getCreatedAt();
        this.customerDto =new CustomerDto(account);
    }

    public AccountDto() {

    }
}
