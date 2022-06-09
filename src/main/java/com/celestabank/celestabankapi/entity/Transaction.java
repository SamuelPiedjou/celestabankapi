package com.celestabank.celestabankapi.entity;

import com.celestabank.celestabankapi.enums.TransactionStatus;
import com.celestabank.celestabankapi.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    private String transactionRemarks;
    private  long accountId;
    @ManyToOne
    private Account account;
}
