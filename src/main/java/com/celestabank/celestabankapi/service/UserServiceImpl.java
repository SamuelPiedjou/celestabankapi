package com.celestabank.celestabankapi.service;

import com.celestabank.celestabankapi.entity.User;
import com.celestabank.celestabankapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<User> addNewUser(User user) {
        userRepository.saveAndFlush(user);
        return userRepository.findAll();
    }

    @Override
    public List<User> updateUserInfo(User user) {
        userRepository.saveAndFlush(user);
        return userRepository.findAll();
    }

    @Override
    public Optional<User> deleteUserInfo(long customerId) {
        Optional<User> user = userRepository.findById((int) customerId);
        if(user!= null)
            userRepository.deleteById((int) customerId);
        return user;
    }
}
