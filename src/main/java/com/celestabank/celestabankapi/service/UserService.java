package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> addNewUser(User user);

    public List<User> updateUserInfo(User user);

    public Optional<User> deleteUserInfo(long customerId);
}
