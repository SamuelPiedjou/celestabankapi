package com.celestabank.celestabankapi.dto;

import lombok.Data;

@Data
public class WithdrawlDTO {
    private long   accountId;
    private double amount;
    private String remark;
}
