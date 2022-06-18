package com.celestabank.celestabankapi.dto;

import java.io.Serializable;
import java.util.Objects;

public class CurrentAccountDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double initialBalance;
	long customerId;

	public CurrentAccountDTO() {
		super();
	}

	public CurrentAccountDTO(double initialBalance, long customerId) {
		super();
		this.initialBalance = initialBalance;
		this.customerId = customerId;
	}

	public double getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(double initialBalance) {
		this.initialBalance = initialBalance;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, initialBalance);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrentAccountDTO other = (CurrentAccountDTO) obj;
		return customerId == other.customerId
				&& Double.doubleToLongBits(initialBalance) == Double.doubleToLongBits(other.initialBalance);
	}

}
