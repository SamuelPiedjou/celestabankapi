package com.celestabank.celestabankapi.entity;

import com.celestabank.celestabankapi.enums.Relation;
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
public class Souscrit {
    @Id
    private int nomineeId;
    private String name;
    private String govtId;
    private String govtIdType;
    private String phoneNo;
    private Relation relation;

    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "accountId")
    private Account account;
}
