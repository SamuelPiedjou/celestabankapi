package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Admin;
import com.celestabank.celestabankapi.exeption.AdminNotFoundException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AdminService {
    public Admin addAdmin(Admin admin);

    Admin updateAdmin(long idAdmin, Admin admin) throws AdminNotFoundException;

    public boolean deleteAdmin(long adminId);
    public Admin findAdminById(long adminId) throws AccountNotFoundException;
    public List<Admin>listAllAdmin();
}
