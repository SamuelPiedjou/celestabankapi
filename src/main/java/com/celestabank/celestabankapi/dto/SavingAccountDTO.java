package com.celestabank.celestabankapi.dto;

import lombok.Data;

@Data
public class SavingAccountDTO extends  AccountDto{
   private double minBalance= 10000;
   private double interestRate =5.5;

}
