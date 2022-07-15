package com.celestabank.celestabankapi.dto;

import com.celestabank.celestabankapi.entity.Account;
import com.celestabank.celestabankapi.entity.Transaction;
import com.celestabank.celestabankapi.enums.TransactionStatus;
import com.celestabank.celestabankapi.enums.TransactionType;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {

    private long transactionId;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    private String transactionRemarks;
    private  long accountId;
    private AccountDto account;
    private String reason;

    public TransactionDTO(Transaction transaction , Account account) {
        this.transactionId = transaction.getTransactionId();
        this.amount = transaction.getAmount();
        this.transactionType = transaction.getTransactionType();
        this.dateTime = transaction.getDateTime();
        this.transactionStatus = transaction.getTransactionStatus();
        this.transactionRemarks = transaction.getTransactionRemarks();
        this.accountId = transaction.getAccountId();
        this.account = new AccountDto(account);
        this.reason = transaction.getReason();
    }
    public TransactionDTO(){

    }
}
