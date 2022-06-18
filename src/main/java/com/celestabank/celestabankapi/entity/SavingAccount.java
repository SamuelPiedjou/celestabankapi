package com.celestabank.celestabankapi.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "FT_SAVING_ACC")
public class SavingAccount extends  Account {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double minBalance= 2000;
    private double interestRate =5.5;
	public double getMinBalance() {
		return minBalance;
	}
	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
    
    
}
