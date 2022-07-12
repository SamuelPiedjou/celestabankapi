package com.celestabank.celestabankapi.dto;

import com.celestabank.celestabankapi.enums.Gender;
import com.celestabank.celestabankapi.enums.Role;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerDto {
    private long UserId;
    private String password;
    private String customerName;
    private String phoneNo;
    private String emailId;
    private Date birthday;
    private Gender gender;
}
