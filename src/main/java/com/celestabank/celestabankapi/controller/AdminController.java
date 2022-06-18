package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.Admin;
import com.celestabank.celestabankapi.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

	private final AdminService adminService;

	
	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@PostMapping("/add")
	public Admin addAdmin(@RequestBody Admin admin) throws Exception {
		return adminService.addAdmin(admin);
	}

	@PutMapping("/update")
	public Admin updateAdmin(@RequestBody Admin admin, long id) throws Exception {
		return adminService.updateAdmin(admin);
	}


	@DeleteMapping("/delete/{adminId}")
	public boolean deleteAdmin(@PathVariable long adminId) throws Exception {
		return adminService.deleteAdmin(adminId);
	}

	@GetMapping("/find/{adminId}")
	public Admin findAdminById(@PathVariable long adminId) throws Exception {
		return adminService.findAdminById(adminId);
	}

	@GetMapping("all/{admin}")
	public List<Admin> listAllAdmin() throws Exception {
		return adminService.listAllAdmin();
	}

}
