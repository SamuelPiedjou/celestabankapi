package com.celestabank.celestabankapi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SavingAccount extends  Account {
    private double minBalance= 2000;
    private double interestRate =5.5;
}
