package com.demo.rest.service;

import com.demo.rest.entity.User;

import java.util.List;

public interface UserService {
    User getUserByEmail(String email);
    void saveUser(User user);
    void deleteUserByEmail(String email);
    List<User> getAllUsers();
}
