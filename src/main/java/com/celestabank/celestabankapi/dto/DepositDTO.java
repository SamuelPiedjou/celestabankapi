package com.celestabank.celestabankapi.dto;

import java.util.Objects;


public class DepositDTO  {
    double amount;
    long accountId;
    String remark;
    
	public DepositDTO(double amount, long accountId, String remark) {
		super();
		this.amount = amount;
		this.accountId = accountId;
		this.remark = remark;
	}

	public DepositDTO() {
		super();
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, amount, remark);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepositDTO other = (DepositDTO) obj;
		return accountId == other.accountId && Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(remark, other.remark);
	}
    
	
    
}
