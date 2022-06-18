package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Admin;
import com.celestabank.celestabankapi.exeption.AdminNotFoundException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AdminService {
	Admin addAdmin(Admin admin) throws Exception;

	Admin updateAdmin(Admin admin) throws AdminNotFoundException;

	boolean deleteAdmin(long adminId) throws Exception;

	Admin findAdminById(long adminId) throws AccountNotFoundException;

	List<Admin> listAllAdmin() throws Exception;
}
