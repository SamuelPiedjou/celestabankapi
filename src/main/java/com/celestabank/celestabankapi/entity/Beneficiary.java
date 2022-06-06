package com.celestabank.celestabankapi.entity;

import com.celestabank.celestabankapi.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Beneficiary {
    @Id
    private long beneficiaryId;
    private String beneficiaryName;
    private int beneficiaryAccNo;
    private String IFSC;
    private AccountType accountType;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Account account;

}
