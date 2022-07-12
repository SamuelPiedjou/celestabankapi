package com.celestabank.celestabankapi.dto;

import lombok.Data;

@Data
public class AtmDTO {
    private long atmId;
    private String adresse;
    private Long accountNum;
}
