package com.celestabank.celestabankapi.entity;

import com.celestabank.celestabankapi.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long beneficiaryId;
    private String beneficiaryName;
    private long beneficiaryAccNo;
    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
