package com.celestabank.celestabankapi.dto;

import lombok.Data;

@Data
public class CurrentAccountDTO extends AccountDto {
    private double overDraft =2000;
}
