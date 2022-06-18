package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.User;
import com.celestabank.celestabankapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/add")
    public List<User> addNewUser(@RequestBody User user) throws Exception {
        return userService.addNewUser(user);
    }

    @PutMapping("/update")
    public List<User> updateUserInfo(@RequestBody User user) throws Exception {
        return userService.updateUserInfo(user);
    }

    @DeleteMapping("/delete/{userId}")
    public Optional<User> deleteUserInfo(long userId) throws Exception {
        return userService.deleteUserInfo(userId);
    }
}
