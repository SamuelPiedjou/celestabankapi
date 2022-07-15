package com.celestabank.celestabankapi.entity;

import com.celestabank.celestabankapi.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CurrentAccount extends  Account {
    private double overDraft =2000;
}
