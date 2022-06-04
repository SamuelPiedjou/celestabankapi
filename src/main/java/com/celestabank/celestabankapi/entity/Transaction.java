package com.celestabank.celestabankapi.entity;

import com.celestabank.celestabankapi.enums.TransactionStatus;
import com.celestabank.celestabankapi.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;
    private double amount;
    private TransactionType transactionType;
    private LocalDateTime dateTime;

    private TransactionStatus transactionStatus;
    private String transactionRemarks;

    @ManyToOne
    @JoinColumn(name = "account_Id")
    @JsonIgnore
    private Account account;
}
