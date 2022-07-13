package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Admin;
import com.celestabank.celestabankapi.exeption.AdminNotFoundException;
import com.celestabank.celestabankapi.exeption.DetailsNotFoundException;
import com.celestabank.celestabankapi.exeption.InvalidDetailsOperation;
import com.celestabank.celestabankapi.service.AdminServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
@AllArgsConstructor
public class AdminController {

    private final AdminServiceImpl adminService;

    @PostMapping("/add")
    public Admin addAdmin(@RequestBody Admin admin) throws InvalidDetailsOperation {
       return  adminService.addAdmin(admin);
    }
    @PutMapping("/update/{id}")
    public Admin updateAdmin(@RequestBody Admin admin,@PathVariable long id) throws InvalidDetailsOperation, AdminNotFoundException{
        return adminService.updateAdmin(id, admin);
    }
    @DeleteMapping("/delete/{adminId}")
    public boolean deleteAdmin(@PathVariable long adminId)throws DetailsNotFoundException {
        adminService.deleteAdmin(adminId);
        return true;
    }
    @GetMapping("/find/{adminId}")
    public Admin findAdminById(@PathVariable long adminId) {
       return  adminService.findAdminById(adminId);
    }
    @GetMapping("/all")
    public List<Admin> listAllAdmin() {
        return  adminService.listAllAdmin();
    }
}
