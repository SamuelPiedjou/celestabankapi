package com.celestabank.celestabankapi.entity;

import com.celestabank.celestabankapi.enums.Role;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="FT_USER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long UserId;
    private String password;
    private Role role;
	public long getUserId() {
		return UserId;
	}
	public void setUserId(long userId) {
		UserId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
    
    
}
