package com.celestabank.celestabankapi.dto;

import lombok.Data;

@Data
public class CurrentAccountDTO extends AccountDto {
    long customerId;
    private double overDraft =2000;
}
