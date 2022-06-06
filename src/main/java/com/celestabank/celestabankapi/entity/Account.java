package com.celestabank.celestabankapi.entity;

import com.celestabank.celestabankapi.enums.AccountStatus;
import com.celestabank.celestabankapi.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    private long accountId;
    private double balance;
    private Date createdAt;
    private AccountType accountType;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> accountOperations;
}
