package com.celestabank.celestabankapi.dto;

import lombok.Data;

@Data
public class TransferDTO {
    long receiver;
    long sender;
    double amount;
}
