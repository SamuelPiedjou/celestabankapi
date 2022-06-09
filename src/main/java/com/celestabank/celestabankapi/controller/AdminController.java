package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Admin;
import com.celestabank.celestabankapi.exeption.AdminNotFoundException;
import com.celestabank.celestabankapi.exeption.DetailsNotFoundException;
import com.celestabank.celestabankapi.exeption.InvalidDetailsException;
import com.celestabank.celestabankapi.service.AdminServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
@AllArgsConstructor
public class AdminController {

    AdminServiceImpl adminService;

    @PostMapping("/add")
    public Admin addAdmin(@RequestBody Admin admin) throws InvalidDetailsException {
        Admin n=null;
        try {
            n=adminService.addAdmin(admin);
        }
        catch(Exception e) {
            throw new InvalidDetailsException("The details given are not valid!");
        }
        return n;
    }
    @PutMapping("/update")
    public Admin updateAdmin(@RequestBody Admin admin, long id) throws InvalidDetailsException, AdminNotFoundException
    {
        Admin n=null;
        try {
            n= adminService.updateAdmin(admin); }
        catch(Exception e) {
           e.getMessage();
        }
        return n;
    }
    @DeleteMapping("/delete/{adminId}")
    public boolean deleteAdmin(@PathVariable long adminId)throws DetailsNotFoundException {
        boolean n= Boolean.parseBoolean(null);
        try {
            n= adminService.deleteAdmin(adminId);
        }
        catch(Exception e) {
            throw new DetailsNotFoundException("The given ID is deleted");
        }
        return true;
    }
    @GetMapping("/find/{adminId}")
    public Admin findAdminById(@PathVariable long adminId)throws DetailsNotFoundException{
        Admin n=null;
        try {
            n=adminService.findAdminById(adminId);
        }
        catch(Exception e) {
            throw new DetailsNotFoundException("The given ID is not found");
        }
        return n;
    }
    @GetMapping("all/{admin}")
    public List<Admin> listAllAdmin(){
        List<Admin> n=null;
        try {
            n=adminService.listAllAdmin();

        }
        catch ( Exception e) {

        }
        return n;
    }

}
