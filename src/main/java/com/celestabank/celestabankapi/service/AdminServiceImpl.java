package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Admin;
import com.celestabank.celestabankapi.exeption.AdminNotFoundException;
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
    public Admin addAdmin(Admin admin) {
        log.info("AJout de : "+admin + "comme administrateur effectué avec succès");
         adminRepository.saveAndFlush(admin);
        return admin;
    }

    @Override
    public Admin updateAdmin(long idAdmin, Admin admin) throws AdminNotFoundException {
       if (findAdminById(idAdmin)!=null){
           Admin admin1 =findAdminById(idAdmin);
           admin1.setAdminName(admin.getAdminName());
           admin1.setPassword(admin.getPassword());
           admin1.setUserId(idAdmin);
           adminRepository.save(admin1);
           log.info("Updating  Admin SUCCESSFUL ! "+admin+ " to "+ admin1);
           return admin1;
       }
        return  null;
    }

    @Override
    public boolean deleteAdmin(long adminId) {
        if (findAdminById(adminId)!=null){
            log.info("Supression de l'admin  " +adminId+ " ----> SUCCESSFUL" );
            adminRepository.deleteById(adminId);
            return true;
        }
        return false;
    }

    @Override
    public Admin findAdminById(long adminId) {
        Admin admin =adminRepository.findById(adminId).orElseThrow(()-> new AdminNotFoundException("ADMINISTRATEUR INTROUVABLE !")) ;
        return admin;
    }

    @Override
    public List<Admin> listAllAdmin() {
        return adminRepository.findAll();
    }
}
