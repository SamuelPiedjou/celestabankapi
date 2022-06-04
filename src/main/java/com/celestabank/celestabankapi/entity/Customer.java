package com.celestabank.celestabankapi.entity;

import com.celestabank.celestabankapi.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends  User {
    private String customerName;
    private String phoneNo;
    private String emailId;
    private int age;
    private Gender gender;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Account> account;
}