package com.celestabank.celestabankapi.dto;

import java.io.Serializable;
import java.util.Objects;

public class WithdrawlDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long accountId;
	private double amount;
	private String remark;

	public WithdrawlDTO() {
		super();
	}

	public WithdrawlDTO(long accountId, double amount, String remark) {
		super();
		this.accountId = accountId;
		this.amount = amount;
		this.remark = remark;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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
		WithdrawlDTO other = (WithdrawlDTO) obj;
		return accountId == other.accountId && Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(remark, other.remark);
	}

	
}
