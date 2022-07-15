package com.celestabank.celestabankapi.entity;

import com.celestabank.celestabankapi.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends  User {
    private String customerName;
    private String phoneNo;
    private String emailId;
    private Date birthday;
    private Date date_Inscription;
    /*@Enumerated(EnumType.STRING)*/
    private Gender gender;
    @OneToMany(mappedBy = "customer" , fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Account> account;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Beneficiary> beneficiaries =new HashSet<>();
}