package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Admin;
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
    public List<Admin> addAdmin(@RequestBody Admin admin) throws InvalidDetailsException {
        List<Admin> n=null;
        try {
            n=adminService.addAdmin(admin);
        }
        catch(Exception e) {
            throw new InvalidDetailsException("The details given are not valid!");
        }
        return n;
    }
    @PutMapping("/update")
    public List<Admin> updateAdmin(@RequestBody Admin admin) throws InvalidDetailsException
    {
        List<Admin> n=null;
        try {
            n= adminService.updateAdmin(admin); }
        catch(Exception e) {
            throw new InvalidDetailsException("The details given are not valid");
        }
        return n;
    }
    @DeleteMapping("/delete/{adminId}")
    public List<Admin> deleteAdmin(@PathVariable long adminId)throws DetailsNotFoundException {
        List<Admin> n=null;
        try {
            n= adminService.deleteAdmin(adminId);
        }
        catch(Exception e) {
            throw new DetailsNotFoundException("The given ID is deleted");
        }
        return n;
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