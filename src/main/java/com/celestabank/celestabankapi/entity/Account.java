package com.celestabank.celestabankapi.entity;

import com.celestabank.celestabankapi.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;
    private double interestRate;
    private double balance;
    private AccountType accountType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfOpening;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Souscrit> nominees;
//
    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Beneficiary> beneficiaries;
//    //
//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
//    private Set<Transaction> transaction;
}
