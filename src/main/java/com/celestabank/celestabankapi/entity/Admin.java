package com.celestabank.celestabankapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin extends User {
    private String adminName;
    private String adminContact;
    private String adminEmailId;
}
