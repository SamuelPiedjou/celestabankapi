package com.celestabank.celestabankapi.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FT_CURRENT_ACC")
public class CurrentAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double overDraft = 2000;

	public double getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(double overDraft) {
		this.overDraft = overDraft;
	}

}
