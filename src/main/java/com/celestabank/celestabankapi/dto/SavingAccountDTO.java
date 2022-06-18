package com.celestabank.celestabankapi.dto;

import java.io.Serializable;
import java.util.Objects;

public class SavingAccountDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double initialBalance;
	private long customerId;

	
	public SavingAccountDTO() {
		super();
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
		SavingAccountDTO other = (SavingAccountDTO) obj;
		return customerId == other.customerId
				&& Double.doubleToLongBits(initialBalance) == Double.doubleToLongBits(other.initialBalance);
	}

	
}
