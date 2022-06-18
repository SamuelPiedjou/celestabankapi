package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> addNewUser(User user)  throws Exception;;

    List<User> updateUserInfo(User user)  throws Exception;

    Optional<User> deleteUserInfo(long customerId)  throws Exception;
}
