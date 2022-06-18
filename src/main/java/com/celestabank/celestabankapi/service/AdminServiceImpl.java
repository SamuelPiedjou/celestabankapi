package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.Admin;
import com.celestabank.celestabankapi.exeption.AdminNotFoundException;
import com.celestabank.celestabankapi.repository.AdminRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
	
    private AdminRepository adminRepository;

	public AdminServiceImpl(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	@Override
	public Admin addAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin updateAdmin(Admin admin) throws AdminNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAdmin(long adminId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Admin findAdminById(long adminId) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> listAllAdmin() throws Exception {
		return adminRepository.findAll();
	}

    
  /*  @Override
    public Admin addAdmin(Admin admin) {
        log.info("AJout de : "+admin + "comme administrateur effectué avec succès");
         adminRepository.saveAndFlush(admin);
        return admin;
    }

    @Override
    public Admin updateAdmin(Admin admin) throws AdminNotFoundException {
        log.info("Admin",admin);
            adminRepository.saveAndFlush(admin);
            return admin;

    }

    @Override
    public boolean deleteAdmin(long adminId) {
        log.info("Supression de l'admin  " +adminId+ " ----> SUCCESSFUL" );
        adminRepository.deleteById(adminId);
        return true;
    }

    @Override
    public Admin findAdminById(long adminId) throws AccountNotFoundException {
        Admin admin =adminRepository.findById(adminId).orElseThrow(()-> new AccountNotFoundException("SESSION INTROUVABLE !")) ;

        return admin;
    }

    @Override
    public List<Admin> listAllAdmin() {

        return adminRepository.findAll();
    }*/
}
