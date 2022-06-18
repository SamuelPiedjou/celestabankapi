package com.celestabank.celestabankapi.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="FT_ADMIN")
public class Admin extends User {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private String contact;
    private String emailId;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
    
    
}
