package com.celestabank.celestabankapi.controller;

import com.celestabank.celestabankapi.entity.User;
import com.celestabank.celestabankapi.exeption.DetailsNotFoundException;
import com.celestabank.celestabankapi.exeption.InvalidDetailsOperation;
import com.celestabank.celestabankapi.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserServiceImpl userService;


    @PostMapping("/add")
    public List<User> addNewUser(@RequestBody User user) throws InvalidDetailsOperation {
        List<User> n = null;
        try {
            n = userService.addNewUser(user);
        } catch (Exception e) {
            throw new InvalidDetailsOperation("Invalid Details!!!");

        }
        return n;
    }

    @PutMapping("/update")
    public List<User> updateUserInfo(@RequestBody User user) throws InvalidDetailsOperation {
        List<User> n = null;
        try {
            n = userService.updateUserInfo(user);
        } catch (Exception e) {
            throw new InvalidDetailsOperation("Invalid Details!!!");

        }
        return n;
    }

    @DeleteMapping("/delete/{userId}")
    public Optional<User> deleteUserInfo(long userId) throws DetailsNotFoundException {
        Optional<User> n = null;
        try {
            n = userService.deleteUserInfo(userId);
        } catch (Exception e) {
            throw new DetailsNotFoundException("The given ID could not be deleted!");
        }
        return n;
    }
}
