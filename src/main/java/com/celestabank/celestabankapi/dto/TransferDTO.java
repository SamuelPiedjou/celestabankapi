package com.celestabank.celestabankapi.dto;

import java.io.Serializable;
import java.util.Objects;


public class TransferDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long receiver;
    long sender;
    double amount;
    
	public TransferDTO() {
		super();
	}
	public TransferDTO(long receiver, long sender, double amount) {
		super();
		this.receiver = receiver;
		this.sender = sender;
		this.amount = amount;
	}
	public long getReceiver() {
		return receiver;
	}
	public void setReceiver(long receiver) {
		this.receiver = receiver;
	}
	public long getSender() {
		return sender;
	}
	public void setSender(long sender) {
		this.sender = sender;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, receiver, sender);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransferDTO other = (TransferDTO) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount) && receiver == other.receiver
				&& sender == other.sender;
	}
    
    
}
