package com.celestabank.celestabankapi.entity;

import com.celestabank.celestabankapi.enums.Relation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Souscrit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
