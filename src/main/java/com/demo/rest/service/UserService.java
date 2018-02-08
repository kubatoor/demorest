package com.demo.rest.service;

import com.demo.rest.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    List<User> getAllUsers();
    User getUserById(String userId);
    void deleteUserById(String userId);
}
