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

    /**
     * Controller method to get a user by userId
     *
     * @param userId
     * @return User ResponseEntity
     */

    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable("userid") String userId){
        final User user = userService.getUserById(userId);
        if(user==null){
            throw new UserNotFoundException("The specified user is not found");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Gets all Users from the DB
     * @return all Users Response Entity
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        final List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    /**
     * Creates a new User in DB. If an User already exists with the same userId in DB
     * then it simply replaces that user with the request user.
     *
     * @param user
     * @return
     */
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

    /**
     * Deletes and existing User. If the user is not found
     * then it lets Spring boot exception handling to return
     * Not found Http Status by throwing a Runtime exception
     *
     * @param userId Id of the User to be deleted
     * @return Successful Response Status or Not Found Status.
     */

    @DeleteMapping("/{userid}")
    public ResponseEntity deleteUser(@PathVariable("userid") String userId){
        if (userService.getUserById(userId) == null) {
            throw new UserNotFoundException("The specified user is not found");
        }

        userService.deleteUserById(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
