package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Admin;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface AdminService {
    public List<Admin> addAdmin(Admin admin);
    public List<Admin> updateAdmin(Admin admin);
    public List<Admin>deleteAdmin(long adminId);
    public Admin findAdminById(long adminId) throws AccountNotFoundException;
    public List<Admin>listAllAdmin();
}
