package com.demo.rest.controller;

import com.demo.rest.entity.User;
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

    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        final User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        final List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid User user) {
        if(userService.getUserByEmail(user.getEmail())!=null){
            userService.saveUser(user);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            userService.saveUser(user);
            return new ResponseEntity(HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity deleteUser(@PathVariable("email") String email){
        userService.deleteUserByEmail(email);
        return new ResponseEntity(HttpStatus.OK);
    }

}
