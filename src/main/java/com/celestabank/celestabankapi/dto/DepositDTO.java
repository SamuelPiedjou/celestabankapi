package com.celestabank.celestabankapi.dto;

import lombok.Data;

@Data
public class DepositDTO {
    double amount;
    long accountId;
    String remark;
}
