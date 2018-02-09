package com.demo.rest.controller;

import com.demo.rest.entity.User;
import com.demo.rest.exception.UserNotFoundException;
import com.demo.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable("userid") String userId){
        final User user = userService.getUserById(userId);
        if(user==null){
            throw new UserNotFoundException("The specified user is not found");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        final List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid User user) {
        if(userService.getUserById(user.getUserId())!=null){
            userService.saveUser(user);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            userService.saveUser(user);
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity deleteUser(@PathVariable("userid") String userId){
        if (userService.getUserById(userId) == null) {
            throw new UserNotFoundException("The specified user is not found");
        }

        userService.deleteUserById(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
