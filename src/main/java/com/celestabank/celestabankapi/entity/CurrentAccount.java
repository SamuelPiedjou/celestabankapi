package com.celestabank.celestabankapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrentAccount extends  Account {

    private double amount;
    private  int months;
    private double penaltyAmount;
}
