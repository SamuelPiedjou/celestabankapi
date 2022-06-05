package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Admin;
import com.celestabank.celestabankapi.repository.AdminRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;

    @Override
    public List<Admin> addAdmin(Admin admin) {
        Admin admins =adminRepository.saveAndFlush(admin);
        return (List<Admin>) admins;
    }

    @Override
    public List<Admin> updateAdmin(Admin admin) {
        Admin admins =adminRepository.saveAndFlush(admin);
        return (List<Admin>) admins;
    }

    @Override
    public List<Admin> deleteAdmin(long adminId) {
        adminRepository.deleteById(adminId);
        return adminRepository.findAll();
    }

    @Override
    public Admin findAdminById(long adminId) throws AccountNotFoundException {
        Admin admin =adminRepository.findById(adminId).orElseThrow(()-> new AccountNotFoundException("SESSION INTROUVABLE !")) ;
        return admin;
    }

    @Override
    public List<Admin> listAllAdmin() {

        return adminRepository.findAll();
    }
}
